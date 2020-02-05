package org.dusfan.idempiere.event;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBankStatement;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class EventBankStatement {


	public static void checkExistingStatement (PO po, Properties ctx, String trxName) {
		MBankStatement mBankStatement = (MBankStatement) po;
		String sql = "select 1 from C_BankStatement bs "
				+" where DocStatus = 'DR' AND C_BankAccount_ID = ?"
				+" and ad_org_id = ?";
		int no = DB.getSQLValue(trxName, sql, new Object[]{mBankStatement.getC_BankAccount_ID(),po.getAD_Org_ID()});
		if (no > 0)
			throw new  AdempiereException("il exist dèja un extrait brouillon!");
	}

}