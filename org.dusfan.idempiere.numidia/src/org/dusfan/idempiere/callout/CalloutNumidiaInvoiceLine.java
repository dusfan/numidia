package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CalloutNumidiaInvoiceLine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {

		if ((int) mTab.getValue("AD_Org_ID") == 1000004) {
			if (mField.getColumnName().equals("T_PriceVente") || mField.getColumnName().equals("M_Product_ID")) {
				int m_product_id = 0;
				if (mTab.getValue("M_Product_ID")!=null){
					m_product_id = (int) mTab.getValue("M_Product_ID");
				}
				if (m_product_id == 1000654 || m_product_id == 1000653) {
					MProduct pr = new MProduct(Env.getCtx(), m_product_id, null);
					MTax tax = new Query(Env.getCtx(), MTax.Table_Name, "C_TaxCategory_ID=?", null)
							.setParameters(new Object[] { pr.getC_TaxCategory_ID() }).first();
					BigDecimal divider = tax.getRate();
					divider = (divider.divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_UP)).add(Env.ONE);
					BigDecimal T_PriceVente = (BigDecimal) mTab.getValue("T_PriceVente");
					T_PriceVente = T_PriceVente.divide(divider, 2, BigDecimal.ROUND_HALF_UP);
					mTab.setValue("PriceEntered", T_PriceVente);
					mTab.setValue("PriceActual", T_PriceVente);
					mTab.setValue("PriceList", T_PriceVente);
				}
			}
			
			if (mField.getColumnName().equals("AgencyProfit") || mField.getColumnName().equals("M_Product_ID")) {
				int m_product_id = 0;
				if (mTab.getValue("M_Product_ID")!=null){
					m_product_id = (int) mTab.getValue("M_Product_ID");
				}
				if (m_product_id == 1000654 || m_product_id == 1000653) {
					MProduct pr = new MProduct(Env.getCtx(), m_product_id, null);
					MTax tax = new Query(Env.getCtx(), MTax.Table_Name, "C_TaxCategory_ID=?", null)
							.setParameters(new Object[] { pr.getC_TaxCategory_ID() }).first();
					BigDecimal divider = tax.getRate();
					divider = (divider.divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_UP)).add(Env.ONE);
					BigDecimal AgencyProfit = (BigDecimal) mTab.getValue("AgencyProfit");
					AgencyProfit = AgencyProfit.divide(divider, 2, BigDecimal.ROUND_HALF_UP);
					mTab.setValue("T_OtherDZD", AgencyProfit);
				}
			}

			if (mField.getColumnName().equals("DateFrom") || mField.getColumnName().equals("DateTo") || mField.getColumnName().equals("M_Pax")) {
				Timestamp date1 = (Timestamp) mTab.getValue("DateFrom");
				Timestamp date2 = (Timestamp) mTab.getValue("DateTo");
				if (date1!=null && date2!=null && mTab.get_ValueAsString("M_Pax")!=""){
					BigDecimal nbrGroup =  new BigDecimal(mTab.get_ValueAsString("M_Pax"));
					LocalDate dateFrom = date1.toLocalDateTime().toLocalDate();
					LocalDate dateTo = date2.toLocalDateTime().toLocalDate();
					Period period = Period.between(dateFrom, dateTo);
					int diff = period.getDays();
					if (diff < 0 )
						throw new AdempiereException("Vérifier la date entrée et la date sortie!");
					mTab.setValue("NBR", BigDecimal.valueOf(diff));
					mTab.setValue("QtyEntered", BigDecimal.valueOf(diff).multiply(nbrGroup));
					mTab.setValue("QtyInvoiced", BigDecimal.valueOf(diff).multiply(nbrGroup));
					setPurchaseSalesPrice(mTab);
				}
			}
			//T_Marge tax de séjour
			if (mField.getColumnName().equals("T_PriceHotel") || mField.getColumnName().equals("T_PriceVente")
					|| mField.getColumnName().equals("T_Marge") || mField.getColumnName().equals("T_OtherDZD")) {
				setPurchaseSalesPrice(mTab);
			}
			if (mField.getColumnName().equals("NBR")){
				BigDecimal nbrGroup =  new BigDecimal(mTab.get_ValueAsString("M_Pax"));
				BigDecimal diff = new BigDecimal(mTab.get_ValueAsString("NBR"));
				mTab.setValue("QtyEntered", diff.multiply(nbrGroup));
				mTab.setValue("QtyInvoiced", diff.multiply(nbrGroup));
				setPurchaseSalesPrice(mTab);
			}
			//Calculer le prix automatique si le service sous l'article est parametré
			if(mField.getColumnName().equals("IsCalculated") && (boolean) mTab.getValue("IsCalculated")==true){
				StringBuilder sql;
				List<Object> rowsArray;
				sql = new StringBuilder("SELECT PriceActual, SalesPrice, PriceEuro FROM DU_Service WHERE M_Product_ID = ? and DU_ServiceType_ID = ? AND Board = ?");
				if(mTab.getValue("View")==null){
					sql.append(" AND View is null");
					rowsArray = DB.getSQLValueObjectsEx(null, sql.toString(), mTab.getValue("M_Product_ID"), mTab.getValue("DU_ServiceType_ID"), mTab.getValue("Board"));
				}else{
					sql.append(" AND View = ?");
					rowsArray = DB.getSQLValueObjectsEx(null, sql.toString(), mTab.getValue("M_Product_ID"), mTab.getValue("DU_ServiceType_ID"), mTab.getValue("Board"), mTab.getValue("View"));
				}
				if(rowsArray!=null){
					mTab.setValue("T_PriceHotel", (BigDecimal)rowsArray.get(0));
					mTab.setValue("T_PriceVente", (BigDecimal)rowsArray.get(1));
					mTab.setValue("PriceEuro", (BigDecimal)rowsArray.get(2));
					setPurchaseSalesPrice(mTab);
				}
			}
		}
		return null;
	}

	private void setPurchaseSalesPrice(GridTab mTab) {
		//Prix d'achat 
		BigDecimal purchasePrice = mTab.getValue("T_PriceHotel") != null ? (BigDecimal)mTab.getValue("T_PriceHotel")
				: Env.ZERO;
		//Prix De vente 
		BigDecimal salesPrice = mTab.getValue("T_PriceVente") != null ? (BigDecimal)mTab.getValue("T_PriceVente")
				: Env.ZERO;
		//Taxe de séjours 
		BigDecimal taxSej = mTab.getValue("T_Marge") != null ? (BigDecimal)mTab.getValue("T_Marge")
				: Env.ZERO;
		//Autre frais 
		BigDecimal other = mTab.getValue("T_OtherDZD") != null ? (BigDecimal)mTab.getValue("T_OtherDZD")
				: Env.ZERO;
		//commission agence
		BigDecimal commission = (salesPrice.subtract(purchasePrice).add(other)).multiply((BigDecimal)mTab.getValue("QtyEntered"));

		//Total achat
		BigDecimal totalAchat = purchasePrice.add(taxSej);

		//Total vente
		BigDecimal totalVente = (salesPrice.add(taxSej.add(other))).multiply((BigDecimal)mTab.getValue("QtyEntered"));

		//Les champs customizer
		mTab.setValue("AgencyProfit", commission);

		//Les champs customizer
		mTab.setValue("SalesPrice", totalVente);

		//Les Valeur standard du system
		mTab.setValue("PriceEntered", totalAchat);
		mTab.setValue("PriceActual", totalAchat);
		mTab.setValue("PriceList", totalAchat);
	}

}
