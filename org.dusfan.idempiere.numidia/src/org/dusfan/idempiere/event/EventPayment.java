package org.dusfan.idempiere.event;

import java.util.Properties;
import org.compiere.model.MBPartner;
import org.compiere.model.MBankAccount;
import org.compiere.model.MDocType;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.util.Env;

public class EventPayment {

	public static int getCodeError (PO po, String trxName, Properties ctx) {
		if (po instanceof MPayment) {
			MPayment pay = (MPayment)po;
			MBankAccount bk = new MBankAccount(ctx, pay.getC_BankAccount_ID(), trxName);
			MDocType doc = new MDocType(ctx, pay.getC_DocType_ID(), trxName);
			if (!pay.getTenderType().equals(doc.get_ValueAsString("TenderType")) || 
					!pay.getTenderType().equals(bk.get_ValueAsString("TenderType")))
				return 1;
			if (pay.getC_Currency_ID() != bk.getC_Currency_ID())
				return 2;
		}
		return 0;
	}
	
	public static boolean checkHadjPayment (PO po, Properties ctx, String trxName) {
		MPayment pay = (MPayment)po;
		if (pay.getAD_Org_ID() == 1000002 && pay.isReceipt() && pay.getTenderType().equals("X")) {
			if (pay.getC_BankAccount_ID()==1000009 && pay.get_ValueAsInt("C_BPartnerRelation_ID")!=1002081) {
				return false;
			}
			if (pay.getC_BankAccount_ID()!=1000009 && pay.get_ValueAsInt("C_BPartnerRelation_ID")==1002081) {
				return false;
			}
		}
		return true;
	}
	
	// check payment individual for global client (omra)
	public static boolean checkindividualPayment (PO po, Properties ctx, String trxName) {
		MPayment pay = (MPayment)po;
		MBPartner bp = new MBPartner(ctx, pay.getC_BPartner_ID(), trxName);
		int current_role_id = Env.getAD_Role_ID(Env.getCtx());
		if ((bp.get_Value("TypeCodeClient")!=null && bp.get_Value("TypeCodeClient").equals("2")) && (current_role_id==1000007 || current_role_id==1000016))
			return false; // don't let payment for individual if comptoir agent
		
		if (bp.getAD_Org_ID() == 1000002 && bp.get_Value("TypeClient")!=null
				&& bp.get_ValueAsString("TypeClient").equals("1")
				&& bp.get_Value("C_BPartnerRelation_ID")!=null) {
			MBPartner code = new MBPartner(ctx, bp.get_ValueAsInt("C_BPartnerRelation_ID"), trxName);
			if (code.get_ValueAsString("TypeCodeClient").equals("1"))
				return false;
		}
		return true;
	}
}
