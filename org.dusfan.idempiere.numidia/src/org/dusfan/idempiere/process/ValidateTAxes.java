package org.dusfan.idempiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;

public class ValidateTAxes extends SvrProcess  {


	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		// Delete Duplicate rows with DELETE USING
		int deleteRows = DB.executeUpdate("DELETE FROM I_DU_CheckVisa a USING I_DU_CheckVisa b "
										+ " WHERE a.I_DU_CheckVisa_id > b.I_DU_CheckVisa_id AND a.Value = b.Value", null);
		if (log.isLoggable(Level.FINE)) log.fine("Delete rows from I_DU_CheckVisa = " + deleteRows);
		String sql = "Select distinct value from I_DU_CheckVisa";
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String ppno = rs.getString(1).trim();
				int I_ImportOmraBP_ID = DB.getSQLValue(get_TrxName(), 
						"Select I_ImportOmraBP_ID from I_ImportOmraBP where trim(PPno) ='"+ppno+"' "
								+ " AND I_Isimported<>'Y'");
				if (I_ImportOmraBP_ID > 0) {
					X_I_ImportOmraBP imp = new X_I_ImportOmraBP(getCtx(), I_ImportOmraBP_ID, get_TrxName());
					imp.setC_Charge_ID(1000000);
					imp.saveEx();
					count ++;
				}
			}
			
			DB.close(rs, pstmt);
		} catch (Exception e) {
			DB.close(rs, pstmt);
			log.log(Level.SEVERE,e.getMessage());
			count = 0;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return count + " Taxes generer";
	}
	

}
