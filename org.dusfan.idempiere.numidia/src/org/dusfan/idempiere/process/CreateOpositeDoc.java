package org.dusfan.idempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.dusfan.idempiere.model.MBooking;

public class CreateOpositeDoc extends SvrProcess {
	
	private int record_id = 0;
	/**	Process Message 			*/
	private String		m_processMsg = null;

	@Override
	protected void prepare() {
		record_id = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		MBooking booking = new MBooking(getCtx(), record_id, getName());
		MInvoice salesInvoice = (MInvoice) booking.getC_SalesInvoice();
		MInvoice purchaseInvoice = (MInvoice) booking.getC_PurchaseInvoice();
		
		if (!booking.isPaidByCard() || booking.getReceiptAmount().compareTo(Env.ZERO)<=0 || purchaseInvoice == null)
			throw new IllegalStateException("Erreur d executé le processus vérifier les données!");
		else{
//			Create counter doc, it's reversal with DocStatus CO
			if (createCounter(salesInvoice) != null)
				return m_processMsg;
			if (createCounter(purchaseInvoice)  != null)
				return m_processMsg;;
		}
			
		return "Processus exécuté avec succès";
	}

	private String createCounter(MInvoice invoice) {
		Timestamp reversalDate = invoice.getDateInvoiced();
		MInvoice counterInvoice = null;
		counterInvoice = MInvoice.copyFrom (invoice, reversalDate, reversalDate, invoice.getC_DocType_ID(), invoice.isSOTrx(), false, get_TrxName(), true, invoice.getDocumentNo()+"-CC");
			if (counterInvoice == null)
			{
				m_processMsg = "Could not create Invoice Reversal";
				return m_processMsg;
			}
//			Reverse Line Qty
			MInvoiceLine[] rLines = counterInvoice.getLines(true);
			for (int i = 0; i < rLines.length; i++)
			{
				MInvoiceLine rLine = rLines[i];
				rLine.setQtyEntered(rLine.getQtyEntered().negate());
				rLine.setQtyInvoiced(rLine.getQtyInvoiced().negate());
				rLine.setLineNetAmt(rLine.getLineNetAmt().negate());
				if (rLine.getTaxAmt() != null && rLine.getTaxAmt().compareTo(Env.ZERO) != 0)
					rLine.setTaxAmt(rLine.getTaxAmt().negate());
				if (rLine.getLineTotalAmt() != null && rLine.getLineTotalAmt().compareTo(Env.ZERO) != 0)
					rLine.setLineTotalAmt(rLine.getLineTotalAmt().negate());
				if (!rLine.save(get_TrxName()))
				{
					m_processMsg = "Could not correct Invoice Reversal Line";
					return m_processMsg;
				}
			}
			StringBuilder msgadd = new StringBuilder("Payed for the ").append(invoice.getDocumentNo()).append(" Booking)");
			counterInvoice.addDescription(msgadd.toString());
			counterInvoice.saveEx(get_TrxName());
			
			//
			if (!counterInvoice.processIt(DocAction.ACTION_Complete))
			{
				m_processMsg = "Reversal ERROR: " + counterInvoice.getProcessMsg();
				return m_processMsg;
			}
			
			counterInvoice.setC_Payment_ID(0);
			counterInvoice.saveEx(get_TrxName());
			
			invoice.setC_Payment_ID(0);
			invoice.saveEx(get_TrxName());
			
//			Create Allocation
			StringBuilder msgall = new StringBuilder().append(Msg.translate(getCtx(), "C_Invoice_ID")).append(": ").append(invoice.getDocumentNo()).append("/").append(counterInvoice.getDocumentNo());
			MAllocationHdr alloc = new MAllocationHdr(getCtx(), false, reversalDate,
					invoice.getC_Currency_ID(),
				msgall.toString(),
				get_TrxName());
			alloc.setAD_Org_ID(invoice.getAD_Org_ID());
			alloc.saveEx();
			//	Amount
			BigDecimal gt = invoice.getGrandTotal(true);
			if (!invoice.isSOTrx())
				gt = gt.negate();
			//	Orig Line
			MAllocationLine aLine = new MAllocationLine (alloc, gt,
					Env.ZERO, Env.ZERO, Env.ZERO);
			aLine.setC_Invoice_ID(invoice.getC_Invoice_ID());
			aLine.saveEx();
			//	Reversal Line
			MAllocationLine rLine = new MAllocationLine (alloc, gt.negate(),
					Env.ZERO, Env.ZERO, Env.ZERO);
			rLine.setC_Invoice_ID(counterInvoice.getC_Invoice_ID());
			rLine.saveEx();
			// added AdempiereException by zuhri
			if (!alloc.processIt(DocAction.ACTION_Complete))
				throw new AdempiereException("Failed when processing document - " + alloc.getProcessMsg());
			// end added
			alloc.saveEx();
		return null;
	}

}
