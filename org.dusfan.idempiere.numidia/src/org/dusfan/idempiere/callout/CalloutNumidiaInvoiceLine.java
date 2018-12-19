package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MInvoice;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MService;

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

			if (mField.getColumnName().equals("DateFrom") || mField.getColumnName().equals("DateTo")) {
				Timestamp date1 = (Timestamp) mTab.getValue("DateFrom");
				Timestamp date2 = (Timestamp) mTab.getValue("DateTo");
				if (date1!=null && date2!=null){
					LocalDate dateFrom = date1.toLocalDateTime().toLocalDate();
					LocalDate dateTo = date2.toLocalDateTime().toLocalDate();
					Period period = Period.between(dateFrom, dateTo);
					int diff = period.getDays();
					if (diff <= 0 )
						throw new AdempiereException("Vérifier la date entrée et la date sortie!");
					mTab.setValue("QtyEntered", BigDecimal.valueOf(diff));
					mTab.setValue("QtyInvoiced", BigDecimal.valueOf(diff));
				}
			}
			
			if (mField.getColumnName().equals("DU_Service_ID")) {
				Integer du_Service_ID = (Integer) mTab.getValue("DU_Service_ID");
				if (du_Service_ID != null){
					MService service = new MService(Env.getCtx(), du_Service_ID, null);
					MInvoice invoice = new MInvoice(Env.getCtx(), (int) mTab.getValue("C_Invoice_ID"), null);
					BigDecimal taxEUR = Env.ZERO;
					BigDecimal taxDZD = Env.ZERO;
					if (invoice.get_ValueAsBoolean("IsInclude")){
						MProduct product = new MProduct(Env.getCtx(), (int) mTab.getValue("M_Product_ID"), null);
						taxEUR = (BigDecimal) product.get_Value("PriceEuro");
						taxDZD = (BigDecimal) product.get_Value("PriceActual");
					}
					//					mTab.setValue("C_Charge_ID", 1000012);
					mTab.setValue("PriceEuro", service.getPriceEuro().add(taxEUR));
					mTab.setValue("AgencyProfit", service.getAgencyProfit());
					mTab.setValue("PriceEntered", service.getPriceActual().add(taxDZD));
					mTab.setValue("PriceActual", service.getPriceActual().add(taxDZD));
				}
			}
		}
		return null;
	}

}
