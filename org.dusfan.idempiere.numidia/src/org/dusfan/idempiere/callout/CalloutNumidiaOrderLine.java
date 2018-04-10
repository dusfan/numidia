package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MRemiseChd;
import org.dusfan.idempiere.model.MRemiseGP;
import org.dusfan.idempiere.model.MRemiseMoudj;
import org.dusfan.idempiere.model.MVol;

public class CalloutNumidiaOrderLine implements IColumnCallout {

	public CalloutNumidiaOrderLine() {
		
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (mField.getColumnName().equals("M_Product_ID")) {
			mTab.setValue("RemiseCompt", Env.ZERO);
			mTab.setValue("DU_RemiseChd_ID", null);
			mTab.setValue("DU_RemiseMoudj_ID", null);
		} else if (mField.getColumnName().equals("DU_RemiseChd_ID")) { 
			setRemiseCHD(mTab, ctx);
		} 
		else if (mField.getColumnName().equals("DU_RemiseMoudj_ID")) { 
			setRemiseMoudjahidine(mTab, ctx);
		} else if (mField.getColumnName().equals("RemiseCompt")) { 
			setRemiseComptoire(mTab, ctx);
		} else if (mField.getColumnName().equals("DU_RemiseGP_ID")) { 
			setRemiseGP(mTab, ctx);
		} 
		return null;
	}
			
	
	private void setRemiseCHD (GridTab mTab, Properties ctx) {
		int du_RemiseChd_ID = mTab.getValue("DU_RemiseChd_ID")!=null ? (int) mTab.getValue("DU_RemiseChd_ID"): 0;
		int m_product_id = mTab.getValue("M_Product_ID")!=null ? (int) mTab.getValue("M_Product_ID"): 0;
		if (m_product_id > 0) {
			MProduct pr = new MProduct(ctx, m_product_id, null);
			if (!pr.get_ValueAsString("TypeService").equals("2")) {
				mTab.fireDataStatusEEvent ("Il faut que l article soit de type billet pour cette remise",
						"Remise CHD", false);
				return ;
			} 
		}
		if (du_RemiseChd_ID > 0 && m_product_id > 0) {
			MRemiseChd chd = new MRemiseChd(ctx, du_RemiseChd_ID, null);
			BigDecimal PriceList = (BigDecimal) mTab.getValue("PriceList");
			BigDecimal priceActual = PriceList.subtract(chd.getPrice());
			mTab.setValue("PriceEntered", priceActual);
			mTab.setValue("PriceActual", priceActual);
		} else {
			mTab.setValue("PriceEntered", mTab.getValue("PriceList"));
			mTab.setValue("PriceActual", mTab.getValue("PriceList"));
		}
	}
	
	private void setRemiseMoudjahidine (GridTab mTab, Properties ctx) {
		int du_RemiseMoudj_ID = mTab.getValue("DU_RemiseMoudj_ID")!=null ? (int) mTab.getValue("DU_RemiseMoudj_ID"): 0;
		int m_product_id = mTab.getValue("M_Product_ID")!=null ? (int) mTab.getValue("M_Product_ID"): 0;
		if (m_product_id > 0) {
			int du_Vol_ID  = DB.getSQLValue(null, "select du_vol_id from c_order where c_order_id =" + (int) mTab.getValue("C_Order_ID"));
			MVol vol = new MVol(ctx, du_Vol_ID, null);
			MProduct pr = new MProduct(ctx, m_product_id, null);
			if (!pr.get_ValueAsString("TypeService").equals("2") || vol.getDU_Compa_ID()!=1000000) {
				mTab.fireDataStatusEEvent ("Il faut que l article soit de type billet Air Algerie pour cette remise",
						"Remise Moudj", false);
				return ;
			} 
		}
		if (du_RemiseMoudj_ID > 0 && m_product_id > 0) {
			MRemiseMoudj mdj = new MRemiseMoudj(ctx, du_RemiseMoudj_ID, null);
			BigDecimal PriceList = (BigDecimal) mTab.getValue("PriceList");
			BigDecimal priceActual = PriceList.subtract(mdj.getPrice());
			mTab.setValue("PriceEntered", priceActual);
			mTab.setValue("PriceActual", priceActual);
		} else {
			mTab.setValue("PriceEntered", mTab.getValue("PriceList"));
			mTab.setValue("PriceActual", mTab.getValue("PriceList"));
		}
	}
	
	private void setRemiseComptoire (GridTab mTab, Properties ctx) {
		int m_product_id = mTab.getValue("M_Product_ID")!=null ? (int) mTab.getValue("M_Product_ID"): 0;
		if (m_product_id > 0) {
			MProduct pr = new MProduct(ctx, m_product_id, null);
			if (!pr.get_ValueAsString("TypeService").equals("5")) {
				mTab.fireDataStatusEEvent ("Il faut que l article soit de type MARGE pour cette remise",
						"Marge", false);
				return;
			}
		}

		BigDecimal remise = mTab.getValue("RemiseCompt")!=null ? (BigDecimal) mTab.getValue("RemiseCompt") : Env.ZERO;
		BigDecimal PriceList = (BigDecimal) mTab.getValue("PriceList");
		BigDecimal priceActual = PriceList.subtract(remise);
		mTab.setValue("PriceEntered", priceActual);
		mTab.setValue("PriceActual", priceActual);
	}
	
	private void setRemiseGP (GridTab mTab, Properties ctx) {
		int du_RemiseGP_ID = mTab.getValue("DU_RemiseGP_ID")!=null ? (int) mTab.getValue("DU_RemiseGP_ID"): 0;
		int m_product_id = mTab.getValue("M_Product_ID")!=null ? (int) mTab.getValue("M_Product_ID"): 0;
		if (m_product_id > 0) {
			int du_Vol_ID  = DB.getSQLValue(null, "select du_vol_id from c_order where c_order_id =" + (int) mTab.getValue("C_Order_ID"));
			MVol vol = new MVol(ctx, du_Vol_ID, null);
			MProduct pr = new MProduct(ctx, m_product_id, null);
			if (!pr.get_ValueAsString("TypeService").equals("2") || vol.getDU_Compa_ID()!=1000000) {
				mTab.fireDataStatusEEvent ("Il faut que l article soit de type billet Air Algerie pour cette remise",
						"Remise GP", false);
				return ;
			} 
		}
		if (du_RemiseGP_ID > 0 && m_product_id > 0) {
			MRemiseGP rgp = new MRemiseGP(ctx, du_RemiseGP_ID, null);
			BigDecimal PriceList = (BigDecimal) mTab.getValue("PriceList");
			BigDecimal priceActual = PriceList.subtract(rgp.getPrice());
			mTab.setValue("PriceEntered", priceActual);
			mTab.setValue("PriceActual", priceActual);
		} else {
			mTab.setValue("PriceEntered", mTab.getValue("PriceList"));
			mTab.setValue("PriceActual", mTab.getValue("PriceList"));
		}
	}

}
