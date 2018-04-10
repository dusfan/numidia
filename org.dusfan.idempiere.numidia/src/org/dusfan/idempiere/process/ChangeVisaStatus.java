package org.dusfan.idempiere.process;

import org.compiere.process.SvrProcess;
import org.dusfan.idempiere.model.MVisaGroup;

public class ChangeVisaStatus extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MVisaGroup vg = new MVisaGroup(getCtx(), getRecord_ID(), get_TrxName());
		vg.setProcessed(true);
		vg.saveEx();
		return null;
	}

}
