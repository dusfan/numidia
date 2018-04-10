package org.dusfan.idempiere.process;

import java.awt.Dialog;
import java.util.logging.Level;

import org.adempiere.webui.window.FDialog;
import org.compiere.model.MBPartner;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.dusfan.idempiere.model.MVAllocation;
import org.dusfan.idempiere.model.MVAllocationLine;
import org.dusfan.idempiere.model.MVolLine;

public class UpdateAffectationLineVol extends SvrProcess {


	private int DU_VAllocation_ID = 0;
	private int DU_VolLine_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("DU_VAllocation_ID"))
				DU_VAllocation_ID = para[i].getParameterAsInt();
			else if (name.equals("DU_VolLine_ID"))
				DU_VolLine_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}


	}

	@Override
	protected String doIt() throws Exception {
		
		MVAllocation alloca = new MVAllocation(getCtx(), DU_VAllocation_ID, get_TrxName());
		MVolLine volL = new MVolLine(getCtx(), DU_VolLine_ID, getName());
		MBPartner bp = new MBPartner(getCtx(), volL.getC_BPartner_ID(), get_TrxName());
		MVAllocationLine valine = new MVAllocationLine(getCtx(), 0, get_TrxName());
		valine.setDU_VAllocation_ID(DU_VAllocation_ID);
		valine.setDU_VolLine_ID(volL.getDU_VolLine_ID());
		valine.saveEx();
		return "Pelerin "+bp.getValue()+" "+bp.getName()+" affecter a = "+ alloca.getValue();
	}

}
