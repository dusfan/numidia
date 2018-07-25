package org.dusfan.idempiere.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBankAccount;
import org.compiere.util.Env;

public class CalloutNumidiaBankStatment implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if (mField.getColumnName().equals("C_BankAccount_ID")) {
			if (mTab.getValue("C_BankAccount_ID")!=null)
			{
				int C_BankAccount_ID = (int) mTab.getValue("C_BankAccount_ID");
				MBankAccount bk = new MBankAccount(Env.getCtx(), C_BankAccount_ID, null);
				mTab.setValue("AD_Org_ID", bk.getAD_Org_ID());
			}
		}
		
		return null;
	}
	

}
