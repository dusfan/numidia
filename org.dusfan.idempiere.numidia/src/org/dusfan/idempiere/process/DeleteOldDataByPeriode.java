package org.dusfan.idempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class DeleteOldDataByPeriode extends SvrProcess {

	private Timestamp   p_DateTo = null;
	private int nbr_fact = 0;
	private int nbr_changelog = 0;
	private int nbr_ad_pinstance = 0;
	private int nbr_deleteinvoiceLine = 0;
	private int nbr_allLine = 0;
	private int nbr_allocation = 0;
	private int nbr_updateBankLine = 0;
	private int nbr_updatePayment = 0;
	private int nbr_deleteinvoice = 0;
	private int nbr_deleteMinout = 0;
	private int nbr_order = 0;
	private int nbr_transaction = 0;
	private int nbr_imported = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DateTo"))
				p_DateTo = (Timestamp)para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		Object[] para = new Object[] {p_DateTo};
		String sql = "";
		try {
			// Delete fact_acct from organization OMRA
			sql = "Delete from fact_acct where ad_org_id = 1000002 and created <= ?";
			nbr_fact = DB.executeUpdate(sql, para,false, get_TrxName());
			if (nbr_fact >= 0)
				commitEx();
			
			// Delete audit information where organization OMRA
			sql = "Delete from ad_changelog where ad_org_id = 1000002 and created <= ?";
			nbr_changelog = DB.executeUpdate(sql, para,false, get_TrxName());
			if (nbr_changelog>=0)
				commitEx();
			
			// Delete ad_issue
			sql = "Delete from ad_issue where ad_org_id = 1000002 and created <= ?";
			nbr_changelog = DB.executeUpdate(sql, para,false, get_TrxName());
			if (nbr_changelog>=0)
				commitEx();
		
			// Delete audit de processus
			sql = "Delete from ad_pinstance_para where ad_org_id = 1000002 and created <= ?";
			nbr_ad_pinstance = DB.executeUpdate(sql, para,false, get_TrxName());
			if (nbr_ad_pinstance>=0)
				commitEx();
			
			sql = "Delete from ad_pinstance where ad_org_id = 1000002 and created <= ?";
			nbr_ad_pinstance = DB.executeUpdate(sql, para,false, get_TrxName());
			if (nbr_ad_pinstance>=0)
				commitEx();
			
			// Delete invoiceline where organization OMRA and document statut reverse
			sql = "delete from c_invoiceline where c_invoice_id in ("
					+ " Select c_invoice_id from c_invoice where ad_org_id = 1000002 and docstatus = 'RE' and created <= ?)";
			nbr_deleteinvoiceLine = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_deleteinvoiceLine>=0)
				commitEx();
			
			// Delete allocationLine where organization OMRA and doc status reverse and existe invoice reverse
			sql = "Delete from c_allocationline where c_allocationhdr_id in " +
				" (Select c_allocationhdr_id from c_allocationhdr where ad_org_id=1000002 and docstatus = 'RE' and created <= ?) "
				+ " OR c_invoice_id in (select c_invoice_id from c_invoice where ad_org_id = 1000002 and docstatus = 'RE' and created <= ?) ";
			nbr_allLine = DB.executeUpdate(sql, new Object[] {p_DateTo,p_DateTo}, false, get_TrxName());
			if (nbr_allLine>=0)
				commitEx();
			
			// Delete allocation where organization OMRA and doc status reverse and existe allocation without Line
			sql = "delete from c_allocationhdr where (ad_org_id=1000002 and docstatus = 'RE' and created <= ?) OR " 
				+ " c_allocationhdr_ID not in (select c_allocationhdr_id from c_allocationline)";
			nbr_allocation = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_allocation>=0)
				commitEx();
			
			// Edit line C_BankStatementLine invoice = null where doc status reverse
			sql = "Update C_BankStatementLine set c_invoice_id = null where c_invoice_id in "
				+ " (Select c_invoice_id from c_invoice where ad_org_id = 1000002 and docstatus = 'RE' and created <= ?)";
			nbr_updateBankLine = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_updateBankLine>=0)
				commitEx();
			
			// Update C_payment invoice = null where doc status reverse
			sql = "Update C_Payment set c_invoice_id = null where c_invoice_id in " +
				  " (Select c_invoice_id from c_invoice where ad_org_id = 1000002 and docstatus = 'RE' and created <= ?)";
			nbr_updatePayment = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_updatePayment>=0)
				commitEx();
			
			// Delete from c_invoice where organization OMRA and doc status reverse
			sql = "Update c_invoice set reversal_id=null where ad_org_id = 1000002 and docstatus = 'RE' and created <= ?";
			nbr_deleteinvoice = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_deleteinvoice>=0)
				commitEx();
			
			sql = " Delete from c_invoice where ad_org_id = 1000002 and docstatus = 'RE' "
					+ "and c_invoice_id not in (select reversal_id from c_invoice) and created <= ?";
			nbr_deleteinvoice = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_deleteinvoice>=0)
				commitEx();
			
			sql = " Delete from m_transaction where ad_org_id = 1000002 and created <= ?";
			nbr_transaction = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_transaction>=0)
				commitEx();
			
			// Update C_invoiceLine set m_inoutline_id to null where organization omra
			sql = "update c_invoiceline set m_inoutline_id = null where ad_org_id = 1000002";
			nbr_deleteMinout = DB.executeUpdate(sql, false, get_TrxName());
			if (nbr_deleteMinout>=0)
				commitEx();

			// Delete expedition line where organization omra
			sql = "update m_inoutline set reversalline_id=null where ad_org_id = 1000002";
			nbr_deleteMinout = DB.executeUpdate(sql, false, get_TrxName());
			if (nbr_deleteMinout>=0)
				commitEx();
			
			sql = "delete from m_inoutline where ad_org_id = 1000002";
			nbr_deleteMinout = DB.executeUpdate(sql, false, get_TrxName());
			if (nbr_deleteMinout>=0)
				commitEx();
			
			sql ="Update m_inout set reversal_id=null where ad_org_id = 1000002";
			nbr_deleteMinout = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_deleteMinout>=0)
				commitEx();
			
			sql ="Delete from m_inout where ad_org_id = 1000002";
			nbr_deleteMinout = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_deleteMinout>=0)
				commitEx();
			
			// Delete c_orderline and c_order where docstatus Voided
			sql = "Update m_inout set c_order_id = null where ad_org_id = 1000002";
			nbr_order = DB.executeUpdate(sql, get_TrxName());
			if (nbr_order>=0)
				commitEx();
			
			sql = "Update m_inoutline set c_orderline_id = null where ad_org_id = 1000002";
			nbr_order = DB.executeUpdate(sql, get_TrxName());
			if (nbr_order>=0)
				commitEx();
			
			sql = "update c_allocationline set c_order_id = null where c_order_id in "
					+ " (select C_Order_id from C_Order where ad_org_id=1000002 and docstatus = 'VO' and issotrx='Y' and created <= ?)";
			nbr_order = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_order>=0)
				commitEx();
			
			sql = "update c_orderline set ref_orderline_id=null where c_order_id "+
				" in (select C_Order_id from c_order where ad_org_id=1000002 and docstatus = 'VO' and issotrx='Y' and created <= ?) ";
			nbr_order = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_order>=0)
				commitEx();
			
			sql = "Delete from c_orderline where c_order_id "+
				" in (select C_Order_id from c_order where ad_org_id=1000002 and docstatus = 'VO' and issotrx='Y' and created <= ?)"
				+ " AND c_order_id not in (select c_order_id from c_invoice)";
			nbr_order = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_order>=0)
				commitEx();
			
			sql = "update c_invoice set c_order_id = null where c_order_id in " 
					+ "(select C_Order_id from c_order where ad_org_id=1000002 and docstatus = 'VO' and issotrx='Y' and created <= ?)";
			nbr_order = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_order>=0)
				commitEx();
			
			sql = "update i_importomrabp set c_order_id = null where c_order_id in " 
				+ " (select C_Order_id from c_order where ad_org_id=1000002 and docstatus = 'VO' and issotrx='Y' and created <= ?)";
			nbr_order = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_order>=0)
				commitEx();
			
			sql = "Delete from i_importomrabp where ad_org_id = 1000002";
			nbr_imported = DB.executeUpdate(sql, false, get_TrxName());
			
			sql = "Delete from c_order where ad_org_id=1000002 and docstatus = 'VO' and issotrx='Y' and created <= ? "
					+ "AND c_order_id not in (select c_order_id from c_invoice)";
			nbr_order = DB.executeUpdate(sql, para, false, get_TrxName());
			if (nbr_order>=0)
				commitEx();
			
//			commitEx();
		
		} catch (Exception e) {
			throw new AdempiereException(e);
		}finally {
			addLog (0, null, new BigDecimal (nbr_fact), "Nombre d'imputation supprimer");
			addLog (0, null, new BigDecimal (nbr_changelog), "Nombre de suivie de modification supprimer");
			addLog (0, null, new BigDecimal (nbr_ad_pinstance), "Nombre d audit de processus supprimer");
			addLog (0, null, new BigDecimal (nbr_deleteinvoiceLine), "Nombre de ligne de facture supprimer");
			addLog (0, null, new BigDecimal (nbr_allLine), "Nombre de ligne d'affectation supprimer");
			addLog (0, null, new BigDecimal (nbr_allocation), "Nombre d'affectation supprimer");
			addLog (0, null, new BigDecimal (nbr_updateBankLine), "Nombre de ligne d'extrat modifier");
			addLog (0, null, new BigDecimal (nbr_updatePayment), "Nombre de paiement modifier");
			addLog (0, null, new BigDecimal (nbr_deleteinvoice), "Nombre de facture supprimer");
			addLog (0, null, new BigDecimal (nbr_deleteMinout), "Nombre d'expedition supprimer");
			addLog (0, null, new BigDecimal (nbr_order), "Nombre d'ordre de vente supprimer");
			addLog (0, null, new BigDecimal (nbr_imported), "Nombre de donnees importer supprimer");
		}
		
		return "OK";
	}

}
