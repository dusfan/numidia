package org.dusfan.idempiere.event;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.dusfan.idempiere.model.MPOSAR;
import org.dusfan.idempiere.model.MPOSARLine;

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
	
//	public static boolean addSarPrice (PO po, Properties ctx, String trxName) {
//		MInvoice inv = (MInvoice)po;
//		if (!inv.isSOTrx() && inv.getC_DocTypeTarget_ID()==1000058) {
//			MPOSAR csar = new MPOSAR(ctx, inv.get_ValueAsInt("DU_POSAR_ID"), trxName);
//			BigDecimal totalSar = DB.getSQLValueBD(trxName, 
//					"Select sum(priceactual) from c_invoiceline "
//					+ " where c_invoice_id=? and m_product_id in (Select m_product_id from m_product where value='SAR')", inv.getC_Invoice_ID());
//			BigDecimal totalused = csar.getAmtSubtract(); // get amt used from caisse sar
//			BigDecimal totalCaisse = csar.getAmt(); // get total in caisse sar
//			BigDecimal totalNow = totalused.add(totalSar); // get current transaction
//			if (totalNow.compareTo(totalCaisse) > 0)
//				return false;
//			csar.setAmtSubtract(totalNow);
//			csar.saveEx();
//			// Add line expense to sar caisse
//			MPOSARLine line = new MPOSARLine(ctx, 0, trxName);
//			line.setDU_POSAR_ID(csar.getDU_POSAR_ID());
//			line.setC_Invoice_ID(inv.getC_Invoice_ID());
//			line.setAmt(totalSar);
//			line.saveEx();
//		}
//		return true;
//	}
//	
//	public static void subSarPrice (PO po, Properties ctx, String trxName) {
//		MInvoice inv = (MInvoice)po;
//		if (!inv.isSOTrx() && inv.getC_DocTypeTarget_ID()==1000058) {
//			MPOSAR csar = new MPOSAR(ctx, inv.get_ValueAsInt("DU_POSAR_ID"), trxName);
//			BigDecimal totalSar = DB.getSQLValueBD(trxName,
//					"Select sum(priceactual) from c_invoiceline "
//					+ " where c_invoice_id=? and m_product_id=1000646", inv.getC_Invoice_ID());
//			BigDecimal totalused = csar.getAmtSubtract(); // get amt used from caisse sar
//			BigDecimal totalNow = totalused.subtract(totalSar);
//			csar.setAmtSubtract(totalNow);
//			csar.saveEx();
//			// Delete from line
//			DB.executeUpdate("Delete from DU_POSARLine where C_Invoice_ID ="+inv.getC_Invoice_ID(), trxName);
//			
//		}
//	}
}
