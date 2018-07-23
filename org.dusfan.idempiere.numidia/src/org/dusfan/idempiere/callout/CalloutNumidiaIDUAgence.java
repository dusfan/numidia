package org.dusfan.idempiere.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CalloutNumidiaIDUAgence implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if (mField.getColumnName().equals("C_BPartnerRelation_ID")) {
			if ((int)mTab.getValue("AD_Org_ID") == 1000004) {
				int c_BPartnerRelation_ID = 
						mTab.getValue("C_BPartnerRelation_ID")!=null ? (int) mTab.getValue("C_BPartnerRelation_ID"): 0;
				if (c_BPartnerRelation_ID > 0) 
					mTab.setValue("T_Marge", null);
			}
		}
		
		return null;
	}
	

}
