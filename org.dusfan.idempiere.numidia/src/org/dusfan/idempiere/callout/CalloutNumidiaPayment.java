package org.dusfan.idempiere.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MInvoice;
import org.compiere.util.DB;

public class CalloutNumidiaPayment implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (mField.getColumnName().equals("C_DocType_ID")) {
			if (mTab.getValue("C_DocType_ID") == null)
				mTab.setValue("C_Activity_ID", null);
			else {
				int c_doctype_id = (int) mTab.getValue("C_DocType_ID");
				if (c_doctype_id == 1000009 || c_doctype_id == 1000049 || c_doctype_id == 1000050 || c_doctype_id == 1000053)
					{
						mTab.setValue("C_Activity_ID", 1000001);
					}
			}

		} else if (mField.getColumnName().equals("C_BPartner_ID")) {
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
 				
			}
		}
//		else if (mField.getColumnName().equals("C_Invoice_ID")) {
//			if (value!=null) {
//				MInvoice inv = new MInvoice(ctx, (int) value, null);
//				mTab.setValue("DateAcct", inv.getDateInvoiced());
//			}
//		}
		return null;
	}

}
