package org.dusfan.idempiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MOrder;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class VoidOrderByGroup extends SvrProcess {

	/** Group Visa    */
	private int m_DU_Visa_Group_ID = 0;
	private int nb_Voided = 0;
	private int nb_deleteFromGroup = 0;
	private int nb_reimpoted = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("DU_Visa_Group_ID"))
				m_DU_Visa_Group_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		// First get all order not voided related to this group
		StringBuilder sql = null;
		//	Go through Records
		sql = new StringBuilder("SELECT * FROM C_Order ").append(" WHERE DocStatus not in ('VO') ")
				.append(" AND DU_Visa_Group_ID = " + m_DU_Visa_Group_ID);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MOrder ord = new MOrder(getCtx(), rs, get_TrxName());
				// Void Order
				ord.processIt(DocAction.ACTION_Void);
				ord.saveEx();
				nb_Voided++;
				// Delete from groupe line if exist
				int co = DB.executeUpdate("Delete from DU_Visa_GroupLine "
						+ " where DU_Visa_Group_ID ="+m_DU_Visa_Group_ID + " AND C_BPartner_ID = "+ord.getC_BPartner_ID(), get_TrxName());
				if (co > 0)
					nb_deleteFromGroup++;
				// Enable import from import table
				co = DB.executeUpdate("Update I_ImportOmraBP set I_IsImported='N', Processed='N', M_Product_ID=null, DU_Presta_ID=null "
						+ " where DU_Visa_Group_ID =" + m_DU_Visa_Group_ID + " AND C_Order_ID = "+
						ord.getC_Order_ID(),get_TrxName());
				if (co > 0)
					nb_reimpoted++;
			} 
			DB.close(rs, pstmt);
		} catch (SQLException e) {
			rollback();
			nb_deleteFromGroup = 0;
			nb_reimpoted = 0;
			nb_Voided = 0;
			throw new DBException(e, sql.toString());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return "Ordre Annule= " + nb_Voided + ", ligne de groupe supprimer= "+ nb_deleteFromGroup
				+ ", ligne reimporter= "+ nb_reimpoted;
	}
	

}
