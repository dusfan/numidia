package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.util.Env;

public class CalloutNumidiaProductPrice implements IColumnCallout {

	public CalloutNumidiaProductPrice() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
//		if (mTab.getValueAsBoolean("isHotel") && (mField.getColumnName().equals("PriceFlight") || mField.getColumnName().equals("PriceFood")
//				|| mField.getColumnName().equals("PriceHoMedina") || mField.getColumnName().equals("PriceHoMekha")
//				|| mField.getColumnName().equals("PriceTaxDioune") || mField.getColumnName().equals("PriceTransport")
//				|| mField.getColumnName().equals("PriceVisa") || mField.getColumnName().equals("Percent")
//				|| mField.getColumnName().equals("PriceMarge") || mField.getColumnName().equals("")
//				|| mField.getColumnName().equals("NbjMK") || mField.getColumnName().equals("NbjMD")
//				|| mField.getColumnName().equals("Taux") || mField.getColumnName().equals("M_PriceList_Version_ID"))) {
//			
//			setTarif(mTab);
//			setSumSarDZD(mTab);
//		}
		
		return null;
	}

//	private void setSumSarDZD(GridTab mTab) {
//		
//		// Total nuite Mekha
//		BigDecimal PriceHoMekha = mTab.getValue("PriceHoMekha") != null ? (BigDecimal) mTab.getValue("PriceHoMekha")
//				: Env.ZERO;
//		int NbjMK = mTab.getValue("NbjMK") != null ? (int) mTab.getValue("NbjMK") : 0;
//		BigDecimal TotalMK = PriceHoMekha.multiply(new BigDecimal(NbjMK));
//		mTab.setValue("TotalMK", TotalMK);
//
//		// Total nuite Medina
//		BigDecimal PriceHoMedina = mTab.getValue("PriceHoMedina") != null ? (BigDecimal) mTab.getValue("PriceHoMedina")
//				: Env.ZERO;
//		int NbjMD = mTab.getValue("NbjMD") != null ? (int) mTab.getValue("NbjMD") : 0;
//		BigDecimal TotalMD = PriceHoMedina.multiply(new BigDecimal(NbjMD));
//		mTab.setValue("TotalMD", TotalMD);
//		
//		// Total Mekha + Medina
//		BigDecimal totalMekhaMedina = TotalMK.add(TotalMD);
//		mTab.setValue("TotalMKMD", totalMekhaMedina);
//		
//		// Pax
//		String typeRoom = mTab.get_ValueAsString("TypeRoom");
//		BigDecimal pax = getTypeRoom(typeRoom, totalMekhaMedina);
//		mTab.setValue("PricePax", pax);
//		
//		// priceVisa + TransportVIP + ITHAM
//		BigDecimal PriceVisa = mTab.getValue("PriceVisa") != null ? (BigDecimal) mTab.getValue("PriceVisa") : Env.ZERO;
//		BigDecimal PriceTransport = mTab.getValue("PriceTransport") != null ? (BigDecimal) mTab.getValue("PriceTransport") : Env.ZERO;
//		BigDecimal PriceFood = mTab.getValue("PriceFood") != null ? (BigDecimal) mTab.getValue("PriceFood") : Env.ZERO;
//		BigDecimal totalVisaIthamTransp = PriceVisa.add(PriceTransport).add(PriceFood);
//		
//		// Total en SAR
//		BigDecimal totalSAR = pax.add(totalVisaIthamTransp);
//		mTab.setValue("TotalSar", totalSAR); // Total en SAR
//		mTab.setValue("PriceLimit", totalSAR);
//
//		// Total en Dinars
//		BigDecimal taux = mTab.getValue("Taux") != null ? (BigDecimal) mTab.getValue("Taux") : Env.ZERO;
//		BigDecimal totalDZD = totalSAR.multiply(taux); // Total en Dinar
//		mTab.setValue("TotalDZD", totalDZD);
//		
//		
//		// Prix Vol et Diouane
//		BigDecimal PriceFlight = mTab.getValue("PriceFlight") != null ? (BigDecimal) mTab.getValue("PriceFlight"): Env.ZERO;
//		BigDecimal PriceTaxDioune = mTab.getValue("PriceTaxDioune") != null ? (BigDecimal) mTab.getValue("PriceTaxDioune") : Env.ZERO;
//		BigDecimal flightDiaoune = PriceFlight.add(PriceTaxDioune);
//		
//		// Prix de revien
//		BigDecimal priceRevien = totalDZD.add(flightDiaoune);
//		mTab.setValue("PriceRevien", priceRevien);
//		
//		// Prix Marge
//		BigDecimal PriceMarge = mTab.getValue("PriceMarge") != null ? (BigDecimal) mTab.getValue("PriceMarge") : Env.ZERO;
//		
//		// Total
//		BigDecimal grandTotal = priceRevien.add(PriceMarge);
//
//		mTab.setValue("PriceList", totalDZD);
//		mTab.setValue("PriceStd", grandTotal);
//		mTab.setValue("PriceLimit", priceRevien);
//		
//	}
//	
//	private BigDecimal getTypeRoom(String room, BigDecimal totalMekhaMedina) {
//		if (room != null) {
//			if (room.equalsIgnoreCase("10"))
//				return totalMekhaMedina;
//			if (room.equalsIgnoreCase("20"))
//				return totalMekhaMedina.divide(new BigDecimal("2"));
//			if (room.equalsIgnoreCase("30"))
//				return totalMekhaMedina.divide(new BigDecimal("3"));
//			if (room.equalsIgnoreCase("40"))
//				return totalMekhaMedina.divide(new BigDecimal("4"));
//			if (room.equalsIgnoreCase("50"))
//				return totalMekhaMedina.divide(new BigDecimal("5"));
//			if (room.equalsIgnoreCase("60"))
//				return totalMekhaMedina.divide(new BigDecimal("6"));
//		}
//		return Env.ZERO;
//	}
//
//	private void setTarif (GridTab mTab) {
//		int mPriceListVersion_ID = mTab.getValue("M_PriceList_Version_ID")!=null ? (int) mTab.getValue("M_PriceList_Version_ID") : 0;
//		if (mPriceListVersion_ID == 0)
//			mTab.setValue("TypeRoom", null);
//		else {
//			MPriceListVersion v = new MPriceListVersion(Env.getCtx(), mPriceListVersion_ID, null);
//			MPriceList price = new MPriceList(Env.getCtx(), v.getM_PriceList_ID(), null);
//			mTab.setValue("TypeRoom", price.get_Value("TypeRoom"));
//		}
//	}
}
