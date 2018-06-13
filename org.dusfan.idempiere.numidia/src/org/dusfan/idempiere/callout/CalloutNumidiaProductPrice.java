package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CalloutNumidiaProductPrice implements IColumnCallout {

	public CalloutNumidiaProductPrice() {
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int ad_Org_id = (int) mTab.getValue("AD_Org_ID");
		if (ad_Org_id == 1000004) {
			if ( mField.getColumnName().equals("Rate") || mField.getColumnName().equals("T_OtherDZD")
				|| mField.getColumnName().equals("T_PriceAssur") || mField.getColumnName().equals("T_PriceBillet")
				|| mField.getColumnName().equals("T_PriceHotel") || mField.getColumnName().equals("T_PriceVente")
				|| mField.getColumnName().equals("T_PriceOtherD") ) {
			
				setPriceTourisme(mTab);
			}
		}
		
		return null;
	}

	private void setPriceTourisme(GridTab mTab) {
		// Price Hotel devise
		BigDecimal T_PriceHotel = mTab.getValue("T_PriceHotel") != null ? (BigDecimal) mTab.getValue("T_PriceHotel")
				: Env.ZERO;
		// Autre prix en devise
		BigDecimal T_PriceOtherD = mTab.getValue("T_PriceOtherD") != null ? (BigDecimal) mTab.getValue("T_PriceOtherD")
				: Env.ZERO;
		// Taux
		BigDecimal rate = mTab.getValue("Rate") != null ? (BigDecimal) mTab.getValue("Rate") : Env.ZERO;
		// Total devise
		BigDecimal totalDevise = (T_PriceHotel.add(T_PriceOtherD)).multiply(rate);
		mTab.setValue("T_SumDevise", totalDevise);
		
		// Billet
		BigDecimal T_PriceBillet = mTab.getValue("T_PriceBillet") != null ? (BigDecimal) mTab.getValue("T_PriceBillet")
				: Env.ZERO;
		// Assurance
		BigDecimal T_PriceAssur = mTab.getValue("T_PriceAssur") != null ? (BigDecimal) mTab.getValue("T_PriceAssur")
				: Env.ZERO;
		// Autre prix de dinars
		BigDecimal T_OtherDZD = mTab.getValue("T_OtherDZD") != null ? (BigDecimal) mTab.getValue("T_OtherDZD")
				: Env.ZERO;
		// Prix de revien
		BigDecimal T_PriceCost = T_PriceBillet.add(T_PriceAssur).add(T_OtherDZD).add(totalDevise);
		mTab.setValue("T_PriceCost", T_PriceCost);
		
		// Prix de vente
		BigDecimal T_PriceVente = mTab.getValue("T_PriceVente") != null ? (BigDecimal) mTab.getValue("T_PriceVente")
				: Env.ZERO;
		// Marge
		mTab.setValue("T_Marge", T_PriceVente.subtract(T_PriceCost));
		// Set price 
		mTab.setValue("PriceList", T_PriceVente);
		mTab.setValue("PriceStd", T_PriceVente);
		mTab.setValue("PriceLimit", T_PriceVente);
		
	}
	

}
