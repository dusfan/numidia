package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CalloutNumidiaPackage implements IColumnCallout {

	public CalloutNumidiaPackage() {
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if ( mField.getColumnName().equals("M_Product_ID") || mField.getColumnName().equals("M_ProductMD_ID")
				|| mField.getColumnName().equals("NbrDayMekha") || mField.getColumnName().equals("NbrDayMedina")
				|| mField.getColumnName().equals("M_ProductBI_ID") || mField.getColumnName().equals("M_ProductV_ID")
				|| mField.getColumnName().equals("M_ProductDO_ID") || mField.getColumnName().equals("Rate")
				|| mField.getColumnName().equals("PriceStd")) {
			
			setPricePackage(mTab);
		}
		
		return null;
	}
	
	private void setPricePackage (GridTab mTab) {
		// Get po Price from hotel mekha
		BigDecimal PriceHoMekha = mTab.getValue("M_Product_ID") != null ? getPricePO((int)mTab.getValue("M_Product_ID"))
				: Env.ZERO;
		mTab.setValue("PriceMK", PriceHoMekha);
		// Get po proce from Hotel medina
		BigDecimal PriceHoMedina = mTab.getValue("M_ProductMD_ID") != null ? getPricePO((int)mTab.getValue("M_ProductMD_ID"))
				: Env.ZERO;
		mTab.setValue("PriceMD", PriceHoMedina);
		// get Total mekha
		BigDecimal NbrDayMekha = mTab.getValue("NbrDayMekha") != null ? (BigDecimal) mTab.getValue("NbrDayMekha")
				: Env.ZERO;
		BigDecimal totalmekha = PriceHoMekha.multiply(NbrDayMekha);
		mTab.setValue("TotalMK", totalmekha);
		// get Total medina
		BigDecimal NbrDayMedina = mTab.getValue("NbrDayMedina") != null ? (BigDecimal) mTab.getValue("NbrDayMedina")
				: Env.ZERO;
		BigDecimal totalmedina = PriceHoMedina.multiply(NbrDayMedina);
		mTab.setValue("TotalMD", totalmedina);
		
		// getPriceBillet
		BigDecimal Pricebi = mTab.getValue("M_ProductBI_ID") != null ? getPricePO((int)mTab.getValue("M_ProductBI_ID"))
				: Env.ZERO;
		mTab.setValue("PriceBi", Pricebi);
		// getpriceVISA
		BigDecimal Pricev = mTab.getValue("M_ProductV_ID") != null ? getPricePO((int)mTab.getValue("M_ProductV_ID"))
				: Env.ZERO;
		mTab.setValue("PriceVisa", Pricev);
		// getPriceDouane
		BigDecimal Pricediane = mTab.getValue("M_ProductDO_ID") != null ? getPricePO((int)mTab.getValue("M_ProductDO_ID"))
				: Env.ZERO;
		mTab.setValue("PriceDouane", Pricediane);
		
		// Total (Prix dde revien)
		BigDecimal rate = mTab.getValue("Rate") != null ? (BigDecimal) mTab.getValue("Rate"): Env.ZERO;
		BigDecimal totalsar =  totalmekha.add(totalmedina).add(Pricev);
		BigDecimal totalsarMultiplayrate = totalsar.multiply(rate);
		BigDecimal priceRevient = totalsarMultiplayrate.add(Pricebi).add(Pricediane);
		mTab.setValue("PriceList", priceRevient);
		
		// Marge du Package
		BigDecimal priceStd = mTab.getValue("PriceStd") != null ? (BigDecimal) mTab.getValue("PriceStd") : Env.ZERO;
		mTab.setValue("PriceLimit", priceStd.subtract(priceRevient));
	}
	
	// get po price for given product
	private BigDecimal getPricePO (int m_product) {
		BigDecimal price = DB.getSQLValueBD(null, "Select min(PricePO) from M_Product_PO where m_product_id = ?",m_product);
		return price!=null ? price : Env.ZERO;
	}

}
