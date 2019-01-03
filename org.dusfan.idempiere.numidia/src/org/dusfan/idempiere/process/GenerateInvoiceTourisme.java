package org.dusfan.idempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPayment;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class GenerateInvoiceTourisme extends SvrProcess {

	int record_id = 0;
	/**	Allocation				*/
	private MAllocationHdr	m_allocation = null;
	
	@Override
	protected void prepare() {
		record_id = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		MPayment pay = new MPayment(getCtx(), record_id, get_TrxName());
		String status = DB.getSQLValueString(get_TrxName(), "Select Docstatus from C_Invoice where C_Invoice_ID = ?", 
				pay.getC_Invoice_ID());
		if (status!=null && !status.equals("RE"))
			throw new AdempiereUserError("Une facture acheve existe deja");
		
		 if ( ((BigDecimal)pay.get_Value("T_PriceHotel")).compareTo(Env.ZERO)==0 )
			 throw new AdempiereUserError("Le prix d'achat est obligatoire");
		 if ( ((BigDecimal)pay.get_Value("T_PriceOtherD")).compareTo(Env.ZERO)==0 )
			 throw new AdempiereUserError("La commession est obligatoire");
		 if ( ((BigDecimal)pay.get_Value("Rate")).compareTo(Env.ZERO)==0 )
			 throw new AdempiereUserError("Le taux est obligatoire");
		 
		else if (pay.getDocStatus().equals("CO")) {
			// Create invoice
			MInvoice inv = new MInvoice(getCtx(), 0, get_TrxName());
			inv.setAD_Org_ID(pay.getAD_Org_ID());
			inv.setC_DocTypeTarget_ID(1000059);
			inv.setC_BPartner_ID(pay.getC_BPartner_ID());
			inv.setSalesRep_ID(pay.getCreatedBy());
			inv.setC_Activity_ID(pay.getC_Activity_ID());
			inv.setC_ConversionType_ID(114);
			inv.set_ValueNoCheck("C_BPartnerRelation_ID", pay.get_Value("C_BPartnerRelation_ID"));
			inv.set_ValueNoCheck("C_BPartner_PR_ID", pay.get_Value("C_BPartner_PR_ID"));
			inv.set_ValueNoCheck("C_T_Curr_ID", pay.get_Value("C_T_Curr_ID"));
			inv.setDescription(pay.getDescription());
			inv.setDateInvoiced(pay.getDateTrx());
			// Prix achat
			inv.set_ValueNoCheck("T_SumDevise", pay.get_Value("T_PriceHotel"));
			// Remise
			inv.set_ValueNoCheck("T_PriceCost", pay.get_Value("T_OtherDZD"));
			// Prix de vente 
			inv.set_ValueNoCheck("T_PriceVente", pay.get_Value("T_SumDevise"));
			// Comission
			inv.set_ValueNoCheck("T_Marge", pay.get_Value("T_Marge"));
			inv.saveEx();
			if (pay.get_ValueAsInt("C_T_Curr_ID") == 235)
				inv.setC_Currency_ID(235); // les plateformes de dinars
			else
				inv.setC_Currency_ID(pay.getC_Currency_ID());
			inv.saveEx();
			
			// Create line
			MInvoiceLine line = new MInvoiceLine(inv);
			line.setC_Charge_ID(1000009);
			line.setQty(Env.ONE);
			line.setPrice((BigDecimal)pay.get_Value("T_SumDevise"));
			line.setDescription(pay.getDescription());
			line.setLineNetAmt();
			line.saveEx();
			// Complete Invoice
			// set date checkIN
			inv.setDateInvoiced((Timestamp) pay.get_Value("ChekInDate"));
			inv.setDateAcct((Timestamp) pay.get_Value("ChekInDate"));
			inv.processIt(DocAction.ACTION_Complete);
			inv.saveEx();
			pay.setC_Invoice_ID(inv.getC_Invoice_ID());
			pay.saveEx();
			
			// Create Affectattion
			createAllocation(pay.getC_Currency_ID(), pay.getDescription(), pay.getDateAcct(), pay.getPayAmt(),
					pay.getDiscountAmt(), pay.getWriteOffAmt(), pay.getOverUnderAmt(), pay.getC_BPartner_ID(),
					pay.getC_Payment_ID(), inv.getC_Invoice_ID(), pay.getAD_Org_ID());
			
			// Allocation
			if (m_allocation != null)
				m_allocation.processIt(MAllocationHdr.DOCACTION_Complete);
			
		}
		
		return "Traitement terminer";
	}

	
	private void createAllocation(int C_Currency_ID, String description, Timestamp dateAcct, BigDecimal Amount,
			BigDecimal DiscountAmt, BigDecimal WriteOffAmt, BigDecimal OverUnderAmt, int C_BPartner_ID,
			int C_Payment_ID, int C_Invoice_ID, int AD_Org_ID) {

		// New Allocation
		m_allocation = new MAllocationHdr(getCtx(), false, dateAcct, // automatic
					C_Currency_ID, "Auto " + description, get_TrxName());
		m_allocation.setAD_Org_ID(AD_Org_ID);
		m_allocation.saveEx();

		// New Allocation Line
		MAllocationLine aLine = new MAllocationLine(m_allocation, Amount, DiscountAmt, WriteOffAmt, OverUnderAmt);
		aLine.setC_BPartner_ID(C_BPartner_ID);
		aLine.setC_Payment_ID(C_Payment_ID);
		aLine.setC_Invoice_ID(C_Invoice_ID);
		aLine.saveEx();
		
	} // CreateAllocation
}
