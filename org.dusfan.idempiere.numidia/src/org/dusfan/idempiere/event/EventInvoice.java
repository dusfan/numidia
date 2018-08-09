package org.dusfan.idempiere.event;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class EventInvoice {

	public static void SetDU_Vol_ID (PO po, Properties ctx, String trxName) {
		MInvoice inv = (MInvoice)po;
		if (inv.isSOTrx() && inv.getC_DocTypeTarget_ID() == 1000051 && inv.getC_Order_ID() > 0) {
			MOrder ord = new MOrder(ctx, inv.getC_Order_ID(), trxName);
			inv.set_ValueNoCheck("DU_Vol_ID", ord.get_ValueAsInt("DU_Vol_ID"));
		}
	}

	public static void checkAllocation(PO po, Properties ctx, String trxName) {
		String sql = "select 1 from C_AllocationLine al "
				+" where exists (select 1 from C_AllocationHdr where C_AllocationHdr.C_AllocationHdr_ID = al.C_AllocationHdr_ID and docstatus in('CO','CL'))"
				+" and ad_org_id = ? and C_Invoice_ID = ?";
		int no = DB.getSQLValue(trxName, sql, new Object[]{po.getAD_Org_ID(),po.get_ID()});
		if (no > 0)
			throw new  AdempiereException("@ValidationError@ @C_Invoice_ID@ @IsPaid@");
	}

	public static void DeleteBooking(PO po, Properties ctx, String trxName) {
		MInvoice invoice = (MInvoice) po;
		if (invoice.isSOTrx())
			DB.executeUpdate("DELETE FROM DU_Booking where C_SalesInvoice_ID = " + invoice.getC_Invoice_ID(), null);
	}
}
