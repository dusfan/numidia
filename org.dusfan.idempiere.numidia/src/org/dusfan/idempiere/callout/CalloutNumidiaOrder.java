package org.dusfan.idempiere.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MVol;

public class CalloutNumidiaOrder implements IColumnCallout {

	public CalloutNumidiaOrder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (mField.getColumnName().equals("C_BPartner_ID")) {
			if (mTab.getValue("C_BPartner_ID") == null) {
				mTab.setValue("Client_Type", null);
				mTab.setValue("C_BPartnerRelation_ID", null);
				mTab.setValue("LinkMohrem", null);
				mTab.setValue("C_BP_Mohrem_ID", null);
			} else {
				int c_BPartner_ID = (int) mTab.getValue("C_BPartner_ID");
				MBPartner bp = new MBPartner(Env.getCtx(), c_BPartner_ID, null);
				mTab.setValue("Client_Type", bp.get_Value("Client_Type")); // recuperer le type de client du partner
				mTab.setValue("C_BPartnerRelation_ID", bp.get_Value("C_BPartnerRelation_ID")); // recuperer code client du partner
				mTab.setValue("C_BP_Mohrem_ID", bp.get_Value("C_BP_Mohrem_ID")); // recuperer le  mohrem 
				mTab.setValue("LinkMohrem", bp.get_Value("LinkMohrem")); // recuperer le link du mohrem du partner
//				chackDoubleOrder (c_BPartner_ID, mTab); // verifier les doubles
			}

		} else if (mField.getColumnName().equals("C_DocTypeTarget_ID")) {
			if (mTab.getValue("C_DocTypeTarget_ID") != null) {
				int c_doctype_id = (int) mTab.getValue("C_DocTypeTarget_ID");
				if(c_doctype_id == 1000057)
					mTab.setValue("C_BPartnerRelation_ID", 1002081);
			}
				
		} else if (mField.getColumnName().equals("DU_Vol_ID")) { // Link Dateordered to flight depart
			int c_doctype_id = mTab.getValue("C_DocTypeTarget_ID") != null?
					(int) mTab.getValue("C_DocTypeTarget_ID") : 0;
			int du_vol_id = mTab.getValue("DU_Vol_ID") != null?
					(int) mTab.getValue("DU_Vol_ID") : 0;
			if (c_doctype_id == 1000047 || c_doctype_id == 1000048 || c_doctype_id == 1000057) {
				if (du_vol_id > 0) {
					MVol vl = new MVol(ctx, du_vol_id, null);
					mTab.setValue("DateOrdered", vl.getDepartDateTime_Direct());
				} else
					mTab.setValue("DateOrdered", null);
			}
		}
		return null;
	}
	
	
//	private void chackDoubleOrder (int C_Bpartner_ID, GridTab mTab) {
//		int count = DB.getSQLValue(null, "Select count(1) from c_order where "
//				+ " docstatus in ('IP','DR','CO','CL') and c_bpartner_id = ?", C_Bpartner_ID);
//		if (count > 0) {
//			MBPartner bp = new MBPartner(Env.getCtx(), C_Bpartner_ID, null);
//			mTab.fireDataStatusEEvent("Attention il existe un ordre de vente pour = " 
//			+ bp.getValue(), null, false);
//		}
//	}
	
	
}
