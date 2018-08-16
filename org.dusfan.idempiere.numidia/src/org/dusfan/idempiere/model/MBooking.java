package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MBooking extends X_DU_Booking {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4092598192243244676L;
	
	public MBooking(Properties ctx, int DU_Booking_ID, String trxName) {
		super(ctx, DU_Booking_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MBooking(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBooking (X_I_InvoiceBooking impBooking){
		this (impBooking.getCtx(), 0, impBooking.get_TrxName());
		
		if (impBooking.get_ID() == 0)
			throw new IllegalArgumentException("Booking not Saved");
		setClientOrg(impBooking.getAD_Client_ID(), impBooking.getAD_Org_ID());
		setBooking(impBooking);
	}

	private void setBooking(X_I_InvoiceBooking impBooking) {
		setDocumentNo(impBooking.getDocumentNo());
		setPriceActual(impBooking.getPriceActual());
		setLeadPassenger(impBooking.getLeadPassenger());
		setC_Currency_ID(impBooking.getC_Currency_ID());
		setDateInvoiced(impBooking.getDateInvoiced());
		setC_BPartner_ID(impBooking.getC_BPartner_ID());
		setC_SalesInvoice_ID(impBooking.getC_Invoice_ID());
	}
	public static void updateBooking (X_I_InvoicePurchase i_invoice){
		int no = DB.executeUpdate("update du_booking set C_PurchaseInvoice_ID = ? , PendingPayment = ?, ReceiptAmount = ? where documentno = ?",
					new Object[]{new Integer(i_invoice.getC_Invoice_ID()), i_invoice.getPriceActual(), i_invoice.getReceiptAmount(), i_invoice.getDocumentNo()}, false,null);
		System.out.println(no);
	}

}