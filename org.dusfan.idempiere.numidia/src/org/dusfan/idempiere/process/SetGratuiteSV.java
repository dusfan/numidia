package org.dusfan.idempiere.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.dusfan.idempiere.model.MVolLine;

public class SetGratuiteSV extends SvrProcess {


	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= "
				+ getAD_PInstance_ID() + " AND T_Selection.T_Selection_ID = du_volline.du_volline_ID)";
		int ids [] = MVolLine.getAllIDs(MVolLine.Table_Name, whereClause, get_TrxName());
		if (ids==null || ids.length == 0)
			throw new AdempiereUserError("Merci de selectionner des lignes");
		for (int id : ids) {
			MVolLine vl = new MVolLine(getCtx(), id, get_TrxName());
			vl.setisFree(true);
			vl.saveEx();
		}
		return "Nombre gratuité selectionner = " + ids.length;
	}

}
