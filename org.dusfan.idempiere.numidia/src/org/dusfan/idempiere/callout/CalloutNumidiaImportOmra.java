package org.dusfan.idempiere.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;

public class CalloutNumidiaImportOmra implements IColumnCallout {

	public CalloutNumidiaImportOmra() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (mField.getColumnName().equals("M_Product_ID")) {
			int m_product_id = mTab.getValue("M_Product_ID")!=null ? (int) mTab.getValue("M_Product_ID"): 0;
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
