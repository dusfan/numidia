package org.dusfan.idempiere.event;

import java.util.Properties;

import org.compiere.model.MPayment;
import org.compiere.model.PO;

public class EventPayment {

	public static boolean CheckPaymentRules (PO po, Properties ctx, String trxName) {
		MPayment pay = (MPayment)po;
		if (pay.getAD_Client_ID() == 1000002) {
			if (pay.getTenderType().equals("X") && (pay.getC_BankAccount_ID()==1000001 || 
					pay.getC_BankAccount_ID()==1000003)) {
				return false;
			}
			if (pay.getTenderType().equals("K") && pay.getC_BankAccount_ID()==1000000) {
				return false;
			}
		}
		return true;
	}
	
}
