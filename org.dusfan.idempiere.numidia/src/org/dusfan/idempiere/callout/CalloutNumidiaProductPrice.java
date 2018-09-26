package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
		else if (ad_Org_id == 1000002) {
			if ( mField.getColumnName().equals("M_PriceHmedina") || mField.getColumnName().equals("M_PriceHmekha")
					|| mField.getColumnName().equals("M_Pax") || mField.getColumnName().equals("M_Rate")
					|| mField.getColumnName().equals("M_PriceTicket") || mField.getColumnName().equals("M_PriceSale")
					|| mField.getColumnName().equals("M_PriceVisa")) {
				
				setPriceOmra(mTab);
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
	
	private void setPriceOmra (GridTab mTab) {
		// Price Hotel medina
		BigDecimal M_PriceHmedina = mTab.getValue("M_PriceHmedina") != null ? (BigDecimal) mTab.getValue("M_PriceHmedina")
				: Env.ZERO;
		// Price Hotel mekha
		BigDecimal M_PriceHmekha = mTab.getValue("M_PriceHmekha") != null ? (BigDecimal) mTab.getValue("M_PriceHmekha")
				: Env.ZERO;
		// Pax
		BigDecimal M_Pax = mTab.getValue("M_Pax") != null ? (BigDecimal) mTab.getValue("M_Pax") : Env.ZERO;
		// VISA
		BigDecimal M_PriceVisa = mTab.getValue("M_PriceVisa") != null ? (BigDecimal) mTab.getValue("M_PriceVisa") : Env.ZERO;
		// Taux
		BigDecimal M_Rate = mTab.getValue("M_Rate") != null ? (BigDecimal) mTab.getValue("M_Rate") : Env.ZERO;
		// Billet + Douane
		BigDecimal M_PriceTicket = mTab.getValue("M_PriceTicket") != null ? (BigDecimal) mTab.getValue("M_PriceTicket")
						: Env.ZERO;
		// Prix de vente
		BigDecimal T_PriceVente = mTab.getValue("M_PriceSale") != null ? (BigDecimal) mTab.getValue("M_PriceSale")
				: Env.ZERO;
		
		// Total Hotel
		BigDecimal totalHotel;
		if (M_Pax.compareTo(Env.ZERO) == 0)
			totalHotel = Env.ZERO;
		else
			totalHotel= (M_PriceHmedina.add(M_PriceHmekha)).divide(M_Pax,2, RoundingMode.HALF_UP);
		mTab.setValue("M_TotalHotel", totalHotel);
		
		// Total SAR
		BigDecimal M_TotalSar = totalHotel.add(M_PriceVisa);
		mTab.setValue("M_TotalSar", M_TotalSar);
		
		// M_TotalSarToDZD
		BigDecimal M_TotalSarToDZD = M_TotalSar.multiply(M_Rate);
		mTab.setValue("M_TotalSarToDZD", M_TotalSarToDZD);
		
		
		// Prix de revient
		BigDecimal M_PriceCost = M_PriceTicket.add(M_TotalSarToDZD);
		mTab.setValue("M_PriceCost", M_PriceCost);
		
		// Marge
		mTab.setValue("M_Marge", T_PriceVente.subtract(M_PriceCost));
		// Set price 
		mTab.setValue("PriceList", T_PriceVente);
		mTab.setValue("PriceStd", T_PriceVente);
		mTab.setValue("PriceLimit", T_PriceVente);
	}
	

}
