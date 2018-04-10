package org.dusfan.idempiere.event;

import java.util.Properties;

import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.PO;

public class EventInvoice {

	public static void setDateAcct (PO po) {
		MInvoice inv = (MInvoice)po;
		if (inv.getC_Activity_ID() == 1000001) {
			inv.setDateAcct(inv.getDateInvoiced());
		}
	}
	
	public static void SetDU_Vol_ID (PO po, Properties ctx, String trxName) {
		MInvoice inv = (MInvoice)po;
		if (inv.isSOTrx() && inv.getC_DocTypeTarget_ID() == 1000051 && inv.getC_Order_ID() > 0) {
			MOrder ord = new MOrder(ctx, inv.getC_Order_ID(), trxName);
			inv.set_ValueNoCheck("DU_Vol_ID", ord.get_ValueAsInt("DU_Vol_ID"));
		}
	}
}
