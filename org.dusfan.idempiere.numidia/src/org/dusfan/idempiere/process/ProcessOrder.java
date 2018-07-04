/** * Licensed under the KARMA v.1 Law of Sharing. As others have shared freely to you, so shall you share freely back to us. * If you shall try to cheat and find a loophole in this license, then KARMA will exact your share, * and your worldly gain shall come to naught and those who share shall gain eventually above you. * In compliance with previous GPLv2.0 works of Jorg Janke, Low Heng Sin, Carlos Ruiz and contributors. * This Module Creator is an idea put together and coded by Redhuan D. Oon (red1@red1.org) */package org.dusfan.idempiere.process;
import java.util.List;import org.compiere.model.MOrder;
import org.compiere.model.Query;import org.compiere.process.DocAction;import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class ProcessOrder extends SvrProcess {		private int cpt_completed;	private String p_DocAction;
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (ProcessInfoParameter p:para) {
			String name = p.getParameterName();
			if (p.getParameter() == null)				;
			else if(name.equals("DocAction")){
				p_DocAction = p.getParameterAsString();
			}
		}
	}
	protected String doIt() {
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=C_Order.C_Order_ID)";

		List<MOrder> lines = new Query(Env.getCtx(),MOrder.Table_Name,whereClause,get_TrxName())
				.setParameters(getAD_PInstance_ID()).list();

		for (MOrder order:lines){
			String a = order.getDocumentNo();						if (p_DocAction.equals(DocAction.ACTION_Complete)){				completeOrder(order);			}			if (p_DocAction.equals(DocAction.ACTION_ReActivate)){				reActivateOrder(order);			}
			if (p_DocAction.equals(DocAction.ACTION_Prepare)){				prpareOrder(order);			}
			log.info("Selected order ID = "+a);

		}		return "Order processed : "+ cpt_completed;

	}	private void completeOrder (MOrder order){		if (order.getDocStatus().equals(DocAction.ACTION_Complete))			return;		else {			order.setDocAction(DocAction.STATUS_Closed);			if (order.processIt(DocAction.ACTION_Complete)){				order.saveEx();				cpt_completed ++;			}		}			}		private void prpareOrder(MOrder order) {		if (order.getDocStatus().equals(DocAction.STATUS_Drafted) || order.getDocStatus().equals(DocAction.STATUS_InProgress))			if (order.processIt(DocAction.ACTION_Prepare))				if ( order.save()){					cpt_completed ++;				}	}	private void reActivateOrder(MOrder order) {		if (!order.getDocStatus().equals(DocAction.ACTION_Complete))			return;		else {			if (order.processIt(DocAction.ACTION_ReActivate))				if ( order.save()){					cpt_completed ++;				}		}	}
}
