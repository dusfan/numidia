package org.dusfan.idempiere.event;

import java.util.Properties;
import org.compiere.model.MBPartner;
import org.compiere.model.MBankAccount;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.util.Env;

public class EventPayment {

	public static boolean CheckPaymentRules (PO po, Properties ctx, String trxName) {
		MPayment pay = (MPayment)po;
		if (pay.getAD_Client_ID() == 1000002) {
			if (pay.getTenderType().equals("X") && (pay.getC_BankAccount_ID()==1000001 || 
					pay.getC_BankAccount_ID()==1000013)) {
				return false;
			}
			if (pay.getTenderType().equals("K") && (pay.getC_BankAccount_ID() == 1000000
					|| pay.getC_BankAccount_ID() == 1000006 || pay.getC_BankAccount_ID() == 1000007
					|| pay.getC_BankAccount_ID() == 1000004 || pay.getC_BankAccount_ID() == 1000005
					|| pay.getC_BankAccount_ID() == 1000011 || pay.getC_BankAccount_ID() == 1000012)) {
				return false;
			}
			if ((pay.getC_DocType_ID() == 1000049 || pay.getC_DocType_ID()==1000009) && 
					(pay.getC_BankAccount_ID()==1000001 || pay.getC_BankAccount_ID()==1000013)
					&& (Env.getAD_Role_ID(Env.getCtx())==1000007 || Env.getAD_Role_ID(Env.getCtx())==1000016
					|| Env.getAD_Role_ID(Env.getCtx())==1000020 || Env.getAD_Role_ID(Env.getCtx())==1000022)) {
				return false;
			}
			if ((pay.getC_DocType_ID() == 1000053 || pay.getC_DocType_ID()==1000050) && 
					(pay.getC_BankAccount_ID()==1000008 || pay.getC_BankAccount_ID()==1000005
					|| pay.getC_BankAccount_ID()==1000000 || pay.getC_BankAccount_ID()==1000004 || pay.getC_BankAccount_ID()==1000007
					|| pay.getC_BankAccount_ID()==1000006 || pay.getC_BankAccount_ID()==1000009 || pay.getC_BankAccount_ID()==1000010
					|| pay.getC_BankAccount_ID()==1000012 || pay.getC_BankAccount_ID()==1000011)
					&& (Env.getAD_Role_ID(Env.getCtx())==1000007 || Env.getAD_Role_ID(Env.getCtx())==1000016
					|| Env.getAD_Role_ID(Env.getCtx())==1000020 || Env.getAD_Role_ID(Env.getCtx())==1000022)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkCaisseCurrency (PO po, Properties ctx, String trxName) {
		MPayment pay = (MPayment)po;
		MBankAccount bk = new MBankAccount(ctx, pay.getC_BankAccount_ID(), trxName);
		if (pay.getC_Currency_ID()!=bk.getC_Currency_ID()) {
				return false;
		}
		return true;
		
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
		if (bp.get_Value("TypeCodeClient").equals("2") && (current_role_id==1000007 || current_role_id==1000016))
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
