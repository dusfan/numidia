package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CalloutNumidiaImportOmra implements IColumnCallout {

	public CalloutNumidiaImportOmra() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (mField.getColumnName().equals("M_Product_ID") || mField.getColumnName().equals("DU_Presta_ID")
				|| mField.getColumnName().equals("C_Charge_ID")) {
			int m_product_id = mTab.getValue("M_Product_ID")!=null ? (int) mTab.getValue("M_Product_ID"): 0;
			int dU_Presta_ID = mTab.getValue("DU_Presta_ID")!=null ? (int) mTab.getValue("DU_Presta_ID"): 0;
			int c_Charge_ID = mTab.getValue("C_Charge_ID")!=null ? (int) mTab.getValue("C_Charge_ID"): 0;
			BigDecimal price = Env.ZERO, pDP = Env.ZERO, ptaxe  = Env.ZERO;
			// Add price
			if (m_product_id > 0)
				price = DB.getSQLValueBD(null, "SELECT bomPriceStd(?,1000003) "
						+ " from m_product where m_product_id ="+m_product_id, m_product_id);
			if (dU_Presta_ID > 0)
				pDP = DB.getSQLValueBD(null, "SELECT bomPriceStd(?,1000003) "
						+ " from m_product where m_product_id ="+dU_Presta_ID, dU_Presta_ID);
			if (c_Charge_ID > 0)
				ptaxe = DB.getSQLValueBD(null, "Select ChargeAmt from C_Charge where c_charge_id = ?", c_Charge_ID);
			mTab.setValue("Price", price.add(pDP).add(ptaxe));
				
			if (m_product_id > 0)
				;
			if (m_product_id > 0)
				;
			if (m_product_id > 0) {
				MProduct pr = new MProduct(ctx, m_product_id, null);
				mTab.setValue("TypeRoom", pr.get_Value("TypeRoom"));
				mTab.setValue("DU_Hotel_ID", pr.get_Value("DU_Hotel_ID"));
				mTab.setValue("Saison_Omra", pr.get_Value("Saison_Omra"));
				mTab.setValue("ClassHotel", pr.get_Value("ClassHotel"));
			} else {
				mTab.setValue("TypeRoom", null);
				mTab.setValue("DU_Hotel_ID", null);
				mTab.setValue("Saison_Omra", null);
				mTab.setValue("ClassHotel", null);
			}
		}
		return null;
	}

}
