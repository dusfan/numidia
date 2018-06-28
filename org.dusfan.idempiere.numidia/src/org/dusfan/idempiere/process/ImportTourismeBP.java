package org.dusfan.idempiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.process.ImportProcess;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.X_C_BP_Relation;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.X_DU_IOrderAgence;

public class ImportTourismeBP extends SvrProcess implements ImportProcess {
	
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/** Effective						*/
	private Timestamp		m_DateValue = null;

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		if (m_DateValue == null)
			m_DateValue = new Timestamp (System.currentTimeMillis());
		m_AD_Client_ID = Env.getAD_Client_ID(getCtx());
	}

	@Override
	protected String doIt() throws Exception {
		StringBuilder sql = null;
		int no = 0;
		String clientCheck = getWhereClause();
		if (m_AD_Client_ID == 0) {
			throw new AdempiereUserError("Vous ne pouvez pas importer les données avec cette société");
		}
		
		// Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder("UPDATE DU_IOrderAgence ").append("SET AD_Client_ID = COALESCE (AD_Client_ID, ")
				.append(m_AD_Client_ID).append("),").append(" AD_Org_ID = COALESCE (AD_Org_ID, 0),")
				.append(" IsActive = COALESCE (IsActive, 'Y'),").append(" Created = COALESCE (Created, SysDate),")
				.append(" CreatedBy = COALESCE (CreatedBy, 0),").append(" Updated = COALESCE (Updated, SysDate),")
				.append(" UpdatedBy = COALESCE (UpdatedBy, 0),").append(" I_ErrorMsg = ' ',")
				.append(" I_IsImported = 'N' ").append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE))
			log.fine("Reset=" + no);
		
		// Erreur le code agence est obligatoire
		sql = new StringBuilder("UPDATE DU_IOrderAgence i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Le code agence est obligatoire, ' ")
				.append("WHERE C_BPartnerRelation_ID IS NULL").append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Le code agence est obligatoire=" + no);
		
		// Set name upper
		sql = new StringBuilder("UPDATE DU_IOrderAgence ")
				.append("SET name = upper(name) ")
				.append("WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Le nom est obligatoire=" + no);
		
		// Erreur le nom est obligatoire
		sql = new StringBuilder("UPDATE DU_IOrderAgence ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Erreur le nom est obligatoire, ' ")
				.append("WHERE name IS NULL ").append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Le nom est obligatoire=" + no);
		
		// Check if M_product_ID exist if is null
		sql = new StringBuilder("UPDATE DU_IOrderAgence i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Merci de mettre le package/article, ' ")
				.append(" WHERE M_Product_ID IS NULL").append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Merci de mettre le package =" + no);
		
//		// Check Doublon
//		sql = new StringBuilder("UPDATE DU_IOrderAgence ")
//				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Erreur la cle est double, ' ")
//				.append("WHERE value IN (Select value from (Select count(value), value from DU_IOrderAgence "
//						+ " group by value HAVING COUNT(value) > 1) dbl ) ")
//				.append(" AND I_IsImported<>'Y'").append(clientCheck);
//		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
//		if (log.isLoggable(Level.CONFIG))
//			log.config("La cle est obligatoire=" + no);
		
		commitEx();
		
		int noInsert = 0;
		int noUpdate = 0;
		
//		Go through Records
		sql = new StringBuilder("SELECT * FROM DU_IOrderAgence ").append("WHERE I_IsImported='N' ").append(clientCheck);
		sql.append(" ORDER BY C_BPartnerrelation_ID");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			
			MBPartner bp = null;
			X_C_BP_Relation bprel = null;
			MOrder order = null;
			while (rs.next()) {
				int c_BPartnerRelation_ID = rs.getInt("C_BPartnerRelation_ID");
				X_DU_IOrderAgence imp = new X_DU_IOrderAgence(getCtx(), rs, get_TrxName());
				
				bp = createBPartner(imp);
				bprel = createBpRelation(bp, c_BPartnerRelation_ID);
				order = createOrderWithLIne(bp, bprel, imp);
				
				imp.setC_BPartner_ID(bp.getC_BPartner_ID());
				if(order != null)
					imp.setC_Order_ID(order.getC_Order_ID());
				
				// Complete orders
				if (order != null) {
					order.setDocAction(DocAction.STATUS_Closed);
					order.processIt(DocAction.ACTION_Complete);
					order.saveEx();
				}
				
				//imported ok
				imp.setI_IsImported(true);
				imp.setProcessed(true);
				imp.setProcessing(false);
				imp.saveEx();
				noInsert++;
				
			}
			DB.close(rs, pstmt);
			
		} catch (Exception e) {
			rollback();
			noInsert = 0;
			System.out.println(e.getStackTrace()+ "--" +sql.toString());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			// Set Error to indicator to not imported
			sql = new StringBuilder("UPDATE DU_IOrderAgence ").append("SET I_IsImported='N', Updated=SysDate ")
					.append("WHERE I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdateEx(sql.toString(), get_TrxName());
			addLog(0, null, new BigDecimal(no), "@Errors@");
			addLog(0, null, new BigDecimal(noInsert), "@C_Order_ID@: @Inserted@");
		}
		
		return null;
	}
	
	@Override
	public String getWhereClause() {
		StringBuilder msgreturn = new StringBuilder(" AND AD_Client_ID=").append(m_AD_Client_ID);
		return msgreturn.toString();
	}

	@Override
	public String getImportTableName() {
		return null;
	}
	
	private MBPartner createBPartner (X_DU_IOrderAgence imp) {
		// If Partner exist update info
		MBPartner bp = null;
		// Create new Bpartner 
		bp = new MBPartner(getCtx(), 0, get_TrxName());
		
		bp.setClientOrg(imp.getAD_Client_ID(), imp.getAD_Org_ID());
		bp.setC_BP_Group_ID(1000003); // set tourisme group default
		bp.set_ValueNoCheck("C_BPartnerRelation_ID", imp.getC_BPartnerRelation_ID());
		bp.setName2(imp.getName2()); // prénom
		bp.setName(imp.getName()); // Nom
		bp.set_ValueNoCheck("Phone", imp.getPhone());
		bp.set_ValueNoCheck("Phone2", imp.getPhone2());
		
		bp.setIsCustomer(true);
		bp.setM_PriceList_ID(1000006);
		bp.setInvoiceRule(MOrder.INVOICERULE_Immediate);
		bp.setDeliveryRule(MOrder.DELIVERYRULE_Availability);
		bp.setPaymentRule(MOrder.PAYMENTRULE_OnCredit);
		bp.setC_PaymentTerm_ID(1000001); // set immediatly
		bp.setInvoice_PrintFormat_ID(1000018); // set print format invoice
		
		bp.saveEx();
		
		return bp;
	}
	
	private X_C_BP_Relation createBpRelation (MBPartner bp , int c_BPartnerRelation_ID) {
		
		// Dans le cas d'un client rabatteur qui veut des factures individuels
		MBPartner clientCode = new MBPartner(getCtx(), c_BPartnerRelation_ID, get_TrxName());
	    // créer la relation tiers 
		X_C_BP_Relation bprel = new X_C_BP_Relation(getCtx(), 0, get_TrxName());
		bprel.setAD_Org_ID(bp.getAD_Org_ID());
		bprel.set_ValueNoCheck("AD_Client_ID", bp.getAD_Client_ID());
		bprel.setName(clientCode.getName() + "-" + bp.getName());
		bprel.setC_BPartner_ID(bp.getC_BPartner_ID());
		bprel.setC_BPartnerRelation_ID(c_BPartnerRelation_ID);
		bprel.setIsBillTo(true);
		int locbp_id = DB.getSQLValue(get_TrxName(),
				"Select C_BPartner_Location_ID from C_BPartner_Location where c_bpartner_id = ?",
				c_BPartnerRelation_ID);
		bprel.setC_BPartnerRelation_Location_ID(locbp_id);
		bprel.saveEx();
		return bprel;
		
	}
	
	
	private MOrder createOrderWithLIne(MBPartner bp, X_C_BP_Relation bprel, X_DU_IOrderAgence imp) {
		bp.load(get_TrxName());
		MOrder order = new MOrder(getCtx(), 0, get_TrxName());
		order.setClientOrg(imp.getAD_Client_ID(), imp.getAD_Org_ID());
		order.setC_DocTypeTarget_ID(1000048);
		if (imp.getDU_Vol_ID() > 0)
			order.set_ValueNoCheck("DU_Vol_ID", imp.getDU_Vol_ID());
		order.setDateOrdered(m_DateValue);
		order.setDateAcct(m_DateValue);
		order.setC_BPartner_ID(bp.getC_BPartner_ID());
		int c_bplocation_id = DB.getSQLValue(get_TrxName(), "Select C_BPartner_Location_ID "
				+ " from C_BPartner_Location where isactive='Y' and c_bpartner_id = ? and limit 1", bp.getC_BPartner_ID());
		order.setC_BPartner_Location_ID(c_bplocation_id);
		
		if (bprel != null) {
			order.setBill_BPartner_ID(bprel.getC_BPartnerRelation_ID());
			order.setBill_Location_ID(bprel.getC_BPartnerRelation_Location_ID());
		}
	
		order.set_ValueNoCheck("C_BPartnerRelation_ID", imp.getC_BPartnerRelation_ID());
		order.setSalesRep_ID(order.getCreatedBy());
		
		order.setM_PriceList_ID(bp.getM_PriceList_ID());
		order.setInvoiceRule(bp.getInvoiceRule());
		order.setDeliveryRule(bp.getDeliveryRule());
		order.setPaymentRule(bp.getPaymentRule());
		order.setC_PaymentTerm_ID(bp.getC_PaymentTerm_ID());
		order.setIsSOTrx(true);
		order.setC_Activity_ID(1000003);
		order.saveEx();
		
		// Create lines for the package
		MOrderLine l1 = new MOrderLine(order);
		l1.setM_Product_ID(imp.getM_Product_ID());
		l1.setQty(Env.ONE);
		l1.setC_Activity_ID(1000003);
		l1.saveEx();
		
		// Add Prestation if exist 
		if (imp.getDU_Presta_ID() > 0) {
			MOrderLine l3 = new MOrderLine(order);
			l3.setM_Product_ID(imp.getDU_Presta_ID());
			l3.setQty(Env.ONE);
			l3.setC_Activity_ID(1000003);
			l3.setLineNetAmt(); // Update for price
			l3.saveEx();
		}
		
		return order;
	}

}
