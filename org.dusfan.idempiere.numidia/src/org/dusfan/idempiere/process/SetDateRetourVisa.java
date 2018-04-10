package org.dusfan.idempiere.process;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import org.compiere.model.MOrder;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;

public class SetDateRetourVisa extends SvrProcess {

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
				+ getAD_PInstance_ID() + " AND T_Selection.T_Selection_ID = c_order.c_order_id)";
		int ids [] = MOrder.getAllIDs(MOrder.Table_Name, whereClause, get_TrxName());
		if (ids==null || ids.length == 0)
			throw new AdempiereUserError("Merci de selectionner des lignes");
		for (int id : ids) {
			MOrder ord = new MOrder(getCtx(), id, get_TrxName());
			ord.set_ValueNoCheck("DateDeposit", dateDeposit);
			ord.saveEx();
		}
		return ids.length +" Enregistrements affecter avec la date =" 
				 + new SimpleDateFormat("dd/MM/yyyy").format(dateDeposit);
	}

}
