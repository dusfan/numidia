package org.dusfan.idempiere.callout;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MPriceList;

public class CalloutNumidiaMPriceVersion implements IColumnCallout {

	public CalloutNumidiaMPriceVersion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (mField.getColumnName().equals("ValidFrom")) {
			Timestamp valid = (Timestamp) value;
			int m_PriceList_ID = (int) mTab.getValue("M_PriceList_ID");
			if (m_PriceList_ID==1000002) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				mTab.setValue("Name",formatter.format(valid));
			}
		}
		return null;
	}

}
