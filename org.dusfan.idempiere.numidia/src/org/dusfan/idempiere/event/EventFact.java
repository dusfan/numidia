package org.dusfan.idempiere.event;

import java.util.Properties;

import org.compiere.acct.FactLine;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class EventFact {

	public static void SetActivity (PO po, Properties ctx, String trxName) {
		FactLine fact = (FactLine)po;
		if (fact.getAD_Table_ID() == MBankStatement.Table_ID && fact.getLine_ID() > 0) {
			MBankStatementLine line = new MBankStatementLine(ctx, fact.getLine_ID(), trxName);
			if (line.getC_Payment_ID() > 0 ) {
				MPayment pay = new MPayment(ctx, line.getC_Payment_ID(), trxName);
				if (pay.getC_Activity_ID() > 0)
					DB.executeUpdate("Update Fact_Acct set "
						+ "c_activity_id ="+ pay.getC_Activity_ID() 
						+ " where Fact_Acct_ID = "+fact.getFact_Acct_ID(), trxName);
			}
			else if (line.getC_Charge_ID() > 0) {
				// TODO
			}
		};
	}
}
