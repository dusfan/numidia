package org.dusfan.idempiere.process;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;

public class SetTagPasseport extends SvrProcess {

	private Timestamp dateDeposit = null;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("DateDeposit"))
				dateDeposit = para[i].getParameterAsTimestamp();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= "
				+ getAD_PInstance_ID() + " AND T_Selection.T_Selection_ID = I_ImportOmraBP.I_ImportOmraBP_ID)";
		int ids [] = X_I_ImportOmraBP.getAllIDs(X_I_ImportOmraBP.Table_Name, whereClause, get_TrxName());
		if (ids==null || ids.length == 0)
			throw new AdempiereUserError("Merci de selectionner des lignes");
		for (int id : ids) {
			X_I_ImportOmraBP imp = new X_I_ImportOmraBP(getCtx(), id, get_TrxName());
			imp.set_ValueNoCheck("DateDeposit", dateDeposit);
			imp.saveEx();
		}
		return ids.length +" Enregistrements affecter avec la date =" 
				 + new SimpleDateFormat("dd/MM/yyyy").format(dateDeposit);
	}

}
