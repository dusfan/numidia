package org.dusfan.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MOrder;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.event.EventOrder;
import org.dusfan.idempiere.model.MVAllocationLine;
import org.dusfan.idempiere.model.MVol;
import org.dusfan.idempiere.model.MVolLine;

public class ChangeVolFromTo extends SvrProcess {
	
	// vol
	private int m_DU_Vol_ID = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("DU_Vol_ID")) {
				m_DU_Vol_ID = para[i].getParameterAsInt();
			}
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
		MVol v = new MVol(getCtx(), m_DU_Vol_ID, get_TrxName());
		
		for (int id : ids) {
			// First delete old vol
			String sql = "Select du_volLine_id from du_volLine where c_order_id =" + id;
			int du_mvolline_id = DB.getSQLValue(get_TrxName(), sql);
			if (du_mvolline_id > 0) {
				// delete else affectation line
				String sqld = "Select DU_VAllocationLine_ID from DU_VAllocationLine where du_volline_id = "
						+ du_mvolline_id;
				int DU_VAllocationLine_ID = DB.getSQLValue(get_TrxName(), sqld);
				if (DU_VAllocationLine_ID > 0) {
					MVAllocationLine vline = new MVAllocationLine(getCtx(), DU_VAllocationLine_ID, get_TrxName());
					vline.deleteEx(true);
				}
				MVolLine vl = new MVolLine(getCtx(), du_mvolline_id, get_TrxName());
				vl.deleteEx(true);
			}
			// Add the new Vol
			MOrder order = new MOrder(getCtx(), id, get_TrxName());
			order.set_ValueNoCheck("DU_Vol_ID", m_DU_Vol_ID);
			order.setDateOrdered(v.getDepartDateTime_Direct());
			order.saveEx();
			if (order.get_ValueAsInt("DU_Vol_ID") > 0) {
				MVolLine line = new MVolLine(getCtx(), 0, get_TrxName());
				line.set_ValueNoCheck("AD_Client_ID", order.getAD_Client_ID());
				line.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
				line.setDU_Vol_ID(order.get_ValueAsInt("DU_Vol_ID"));
				line.setC_Order_ID(order.getC_Order_ID());
				line.setC_BPartner_ID(order.getC_BPartner_ID());
				line.setIsPrinted(EventOrder.setIsPrinted(get_TrxName(), order.getC_Order_ID()));
				line.setIsInclude(EventOrder.setIsInclude(get_TrxName(), order.getC_Order_ID()));
				line.saveEx();
			}
		}
		return ids.length +" Enregistrements affecter au VOL => " 
				 + v.getValue();
	}

}
