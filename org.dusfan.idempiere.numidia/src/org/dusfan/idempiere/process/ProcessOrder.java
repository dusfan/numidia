/**

import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class ProcessOrder extends SvrProcess {


		ProcessInfoParameter[] para = getParameter();
		for (ProcessInfoParameter p:para) {
			String name = p.getParameterName();
			if (p.getParameter() == null)
			else if(name.equals("DocAction")){
				p_DocAction = p.getParameterAsString();
			}
		}
	}

		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=C_Order.C_Order_ID)";

		List<MOrder> lines = new Query(Env.getCtx(),MOrder.Table_Name,whereClause,get_TrxName())
				.setParameters(getAD_PInstance_ID()).list();

		for (MOrder order:lines){
			String a = order.getDocumentNo();
			if (p_DocAction.equals(ACTION_Void)){
			log.info("Selected order ID = "+a);

		}

	}
}