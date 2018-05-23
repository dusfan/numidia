package org.dusfan.idempiere.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class ConfirmAchatSar extends SvrProcess {

	

	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		int co = DB.executeUpdate("Update DU_POSAR set processed='Y' "
				+ "where DU_POSAR_ID="+getRecord_ID(), get_TrxName());
		if (co > 0)
			addLog("Caisse Confirmer");
		return null;
	}

}
