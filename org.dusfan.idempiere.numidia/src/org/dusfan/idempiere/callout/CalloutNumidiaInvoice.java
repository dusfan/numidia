package org.dusfan.idempiere.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class CalloutNumidiaInvoice implements IColumnCallout{

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
							mTab.setValue("C_BPartnerRelation_ID", c_bpartner_id); // Mettre le code client
					}
				}
			}
		}
		return null;
	}

	

}
