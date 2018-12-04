package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CalloutNumidiaPayment implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if (mField.getColumnName().equals("C_BPartner_ID")) {
			if ((int)mTab.getValue("AD_Org_ID") == 1000002) {
				if (mTab.getValue("C_BPartner_ID") == null) {
					mTab.setValue("C_BPartnerRelation_ID", null);
				}
				else {
					int c_bpartner_id = (int) mTab.getValue("C_BPartner_ID");
					
					int codeclient = DB.getSQLValue(null, "Select C_BPartnerRelation_ID from c_bpartner where c_bpartner_id ="+ c_bpartner_id);
					if (codeclient > 0) {
						if (codeclient != 1000000)
							mTab.setValue("C_BPartnerRelation_ID", codeclient);
						else
							mTab.setValue("C_BPartnerRelation_ID", c_bpartner_id); // mettre le code client
					}
					
					MBPartner bp = new MBPartner(ctx, c_bpartner_id, null);
					if (bp.get_Value("TypeCodeClient")!=null && bp.get_Value("TypeCodeClient").equals("2"))
						mTab.fireDataStatusEEvent("ATTENTION Le tiers est code client individuel", null, false);
	 				
				}
			}
		}
		// Prix de devis
		int ad_Org_id = (int) mTab.getValue("AD_Org_ID");
		if (ad_Org_id == 1000004) {
			if ( mField.getColumnName().equals("Rate") || mField.getColumnName().equals("T_OtherDZD")
				|| mField.getColumnName().equals("T_PriceHotel") || mField.getColumnName().equals("T_PriceOtherD") ) {
			
				setPriceTourisme(mTab);
			}
			
			if (mField.getColumnName().equals("C_Currency_ID") || mField.getColumnName().equals("C_T_Curr_ID")) {
				int c_currency_id_so = (int) mTab.getValue("C_Currency_ID");
				int c_currency_id_po = (int) mTab.getValue("C_T_Curr_ID");
				if (c_currency_id_po > 0 && c_currency_id_so > 0 && 
						c_currency_id_so!=235 && c_currency_id_po==c_currency_id_so) {
					mTab.setValue("Rate", Env.ONE);
					setPriceTourisme(mTab);
				}
					
			}
		}
		
		return null;
	}
	
	
	private void setPriceTourisme(GridTab mTab) {
		// Price achat plateforme
		BigDecimal T_PriceHotel = mTab.getValue("T_PriceHotel") != null ? (BigDecimal) mTab.getValue("T_PriceHotel")
				: Env.ZERO;
		// Prix de vente plateforme
		BigDecimal T_PriceOtherD = mTab.getValue("T_PriceOtherD") != null ? (BigDecimal) mTab.getValue("T_PriceOtherD")
				: Env.ZERO;
		// Taux
		BigDecimal rate = mTab.getValue("Rate") != null ? (BigDecimal) mTab.getValue("Rate") : Env.ZERO;
		
		// Prix de vente client
		BigDecimal pricevente = T_PriceOtherD.multiply(rate);
		mTab.setValue("T_PriceVente", pricevente);
		// Remise
		BigDecimal T_OtherDZD = mTab.getValue("T_OtherDZD") != null ? (BigDecimal) mTab.getValue("T_OtherDZD")
				: Env.ZERO;
		
		// Final Price
		BigDecimal finalPrice = pricevente.subtract(T_OtherDZD);
		mTab.setValue("T_SumDevise", finalPrice);
		
		// Comission
		mTab.setValue("T_Marge", (finalPrice.subtract(T_PriceHotel.multiply(rate))));
		
	}

}
