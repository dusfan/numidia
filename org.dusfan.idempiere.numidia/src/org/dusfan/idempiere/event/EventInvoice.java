package org.dusfan.idempiere.event;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class EventInvoice {

	public static void SetDU_Vol_ID (PO po, Properties ctx, String trxName) {
		MInvoice inv = (MInvoice)po;
		if (inv.isSOTrx() && inv.getAD_Client_ID()==1000002 && inv.getC_Order_ID() > 0) {
			MOrder ord = new MOrder(ctx, inv.getC_Order_ID(), trxName);
			if (ord.get_ValueAsInt("DU_Vol_ID") > 0)
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
		if (invoice.isSOTrx()){
			DB.executeUpdate("DELETE FROM DU_Booking where C_PurchaseInvoice_ID is null and C_SalesInvoice_ID = " + invoice.getC_Invoice_ID(), null);
		}
	}

	public static void changeDocumentNo(PO po, Properties ctx, String trxName) {
		MInvoice invoice = (MInvoice) po;
		invoice.setDocumentNo(invoice.getDocumentNo().concat("-RE"));
		invoice.saveEx();
	}

	public static void checkDateInvoiced(String tableName, PO po, Properties ctx, String trxName) {
		MInvoiceLine invoiceLine = (MInvoiceLine) po;
		if (invoiceLine.get_Value("T_PriceHotel")!=null && ((BigDecimal) invoiceLine.get_Value("T_PriceHotel")).compareTo(Env.ZERO)>0 ) { // only with service is mentioned
			String sql = "Select DateInvoiced from C_Invoice where C_Invoice_ID = ?";
			Timestamp date =  DB.getSQLValueTS(null, sql, invoiceLine.getC_Invoice_ID());
			LocalDate dateInvoiced = date.toLocalDateTime().toLocalDate();
			LocalDate dateBooking = ((Timestamp) invoiceLine.get_Value("DateFrom")).toLocalDateTime().toLocalDate();
			if (!dateInvoiced.equals(dateBooking)){
				throw new AdempiereException( "la date d'entrée est différente de la date de facturation!");
			}
		}
		
	}
}
