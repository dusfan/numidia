package org.dusfan.idempiere.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class DeleteUnusedData extends SvrProcess {

	private int p_AD_Org_ID;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		String sql = "";
		// Delete audit de processus
		sql = "Delete from fact_acct where ad_org_id = " + p_AD_Org_ID;
		int no = DB.executeUpdate(sql.toString(), get_TrxName());
		StringBuilder msgreturn = new StringBuilder("@Deleted@ = ").append(no);
		return msgreturn.toString();
	}

}
