package org.dusfan.idempiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.dusfan.idempiere.model.X_I_MarginSharing;

public class ImportMarginSharing extends SvrProcess {

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Organization to be imported to		*/
	private int				m_AD_Org_ID = 0;
	/**	Delete old Imported				*/
	private boolean				m_deleteOldImported = false;


	/** Effective						*/
	private Timestamp		m_DateValue = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = new Timestamp (System.currentTimeMillis());

	}

	@Override
	protected String doIt() throws Exception {
		StringBuilder sql = null;
		int no = 0;
		StringBuilder clientCheck = new StringBuilder(" AND AD_Client_ID=").append(m_AD_Client_ID);

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuilder ("DELETE I_InvoiceBooking ")
					.append("WHERE I_IsImported='Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (log.isLoggable(Level.FINE)) log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder ("UPDATE I_MarginSharing ")
				.append("SET AD_Client_ID = COALESCE (AD_Client_ID,").append (m_AD_Client_ID).append ("),")
				.append(" AD_Org_ID = COALESCE (AD_Org_ID,").append (m_AD_Org_ID).append ("),")
				.append(" IsActive = COALESCE (IsActive, 'Y'),")
				.append(" Created = COALESCE (Created, SysDate),")
				.append(" CreatedBy = COALESCE (CreatedBy, 0),")
				.append(" Updated = COALESCE (Updated, SysDate),")
				.append(" UpdatedBy = COALESCE (UpdatedBy, 0),")
				.append(" I_ErrorMsg = '',")
				.append(" I_IsImported = 'N' ")
				.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.INFO)) log.info ("Reset=" + no);

		sql = new StringBuilder ("UPDATE I_MarginSharing o ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '")
				.append("WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0")
				.append(" OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))")
				.append(" AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Org=" + no);

		// delete record where there no amount neither booking refrence
		sql = new StringBuilder ("delete from I_MarginSharing o ")
				.append("WHERE DocumentNo is NULL OR DocumentNo ='Booking ref.'")
				.append(" AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("No amount record Deleted=" + no);

		// Set Error for not imported booking
		sql = new StringBuilder ("UPDATE I_MarginSharing o ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=La facture du vente existe pas, '")
				.append(" WHERE NOT EXISTS (SELECT documentno FROM 	DU_Booking oo WHERE oo.documentno = o.documentno) ")
				.append(" AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Booking Not Exists =" + no);

		// set the currency_id
		sql = new StringBuilder ("UPDATE I_MarginSharing o ")
				.append(" set C_Currency_ID = (SELECT C_Currency_ID FROM C_Currency WHERE o.CURRENCY = C_Currency.ISO_Code) ")
				.append(" WHERE Currency IS not NULL and C_Currency_ID IS NULL")
				.append(" AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Currency Set=" + no);

		commitEx();
		//		-- Update the existing imported booking -----------------------------------------------------

		int noUpdated = 0;

		//		Go through Invoice Records w/o
		sql = new StringBuilder ("SELECT * FROM I_MarginSharing ")
				.append("WHERE I_IsImported='N'").append (clientCheck);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			rs = pstmt.executeQuery ();

			while (rs.next ())
			{
				X_I_MarginSharing imp = new X_I_MarginSharing(getCtx(), rs, null);
				updateBooking(imp);
				imp.setI_IsImported(true);
				imp.setProcessed(true);
				if (imp.save())
					noUpdated++;
			}
		}


		catch (Exception e)
		{
			log.log(Level.SEVERE, "UpdateBooking", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}


		//		Set Error to indicator to not imported
		sql = new StringBuilder ("UPDATE I_MarginSharing ")
				.append("SET I_IsImported='N', Updated=SysDate ")
				.append("WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		//
		addLog (0, null, new BigDecimal (noUpdated), "Margin Sharing Set ");
		return "";
	}

	public  void updateBooking (X_I_MarginSharing imp){
		String sql = " update du_booking set C_PurchaseCurrency_ID = ?, ReceiptAmount = ?, "
				+ " PriceActualNet=?, NetSalesPriceUsd = ?, "
				+ " CostPrice=?, GeneralProfit=?, AgencyProfit = ?"
				+ " where documentno = ?";

		Object[] obj = new Object[]{new Integer(imp.getC_Currency_ID()), imp.getReceiptAmount(), 
				imp.getPriceActualNet(), imp.getNetSalesPriceUsd(), imp.getCostPrice(), 
				imp.getGeneralProfit(), imp.getGeneralProfit().divide(new BigDecimal(2)), imp.getDocumentNo()};
		int no = DB.executeUpdate(sql, obj, false,null);
		if (no<0) log.warning("Booking didn't updated =" + imp.getDocumentNo());
	}

}
