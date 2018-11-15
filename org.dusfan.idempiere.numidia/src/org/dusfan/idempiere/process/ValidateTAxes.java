package org.dusfan.idempiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;

public class ValidateTAxes extends SvrProcess  {


	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		String sql = "Select distinct value from I_DU_CheckVisa";
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
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
				}
			}
			
			DB.close(rs, pstmt);
		} catch (Exception e) {
			DB.close(rs, pstmt);
			log.log(Level.SEVERE,e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return null;
	}
	

}
