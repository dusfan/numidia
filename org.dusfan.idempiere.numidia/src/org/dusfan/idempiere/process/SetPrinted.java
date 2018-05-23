package org.dusfan.idempiere.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.dusfan.idempiere.model.MVolLine;

public class SetPrinted extends SvrProcess {

	private boolean	m_isPrinted = false;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("IsPrinted"))
				m_isPrinted = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

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
			vl.setIsPrinted(m_isPrinted);
			vl.saveEx();
		}
		return "Nombre imprimer selectionner = " + ids.length;
	}

}
