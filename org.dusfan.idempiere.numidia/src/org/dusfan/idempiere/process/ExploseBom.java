package org.dusfan.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.X_C_BP_Relation;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class ExploseBom extends SvrProcess {

	private int c_Order_ID = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("C_Order_ID")) {
				c_Order_ID = para[i].getParameterAsInt();
			}

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		if (c_Order_ID > 0) {
			MOrder ord = new MOrder(getCtx(), c_Order_ID, get_TrxName());
			MBPartner clientCode = new MBPartner(getCtx(), ord.get_ValueAsInt("C_BPartnerRelation_ID"), get_TrxName());
			if (clientCode.get_Value("TypeCodeClient").equals("1")) {
				String whereC = "C_BPartnerRelation_ID = "+ord.get_ValueAsInt("C_BPartnerRelation_ID")+
						" AND C_BPartner_ID = "+ord.getC_BPartner_ID();
				int [] rel_id = X_C_BP_Relation.
						getAllIDs(X_C_BP_Relation.Table_Name, whereC, get_TrxName());
				
				// Create relation
				if (rel_id.length == 0) {
					// créer la relation tiers 
					X_C_BP_Relation bprel = new X_C_BP_Relation(getCtx(), 0, get_TrxName());
					bprel.setAD_Org_ID(ord.getAD_Org_ID());
					bprel.set_ValueNoCheck("AD_Client_ID", ord.getAD_Client_ID());
					bprel.setName(clientCode.getName() + "-" + ord.getC_BPartner_ID());
					bprel.setC_BPartner_ID(ord.getC_BPartner_ID());
					bprel.setC_BPartnerRelation_ID(clientCode.getC_BPartner_ID());
					bprel.setIsBillTo(true);
					int locbp_id = DB.getSQLValue(get_TrxName(), "Select C_BPartner_Location_ID from C_BPartner_Location where c_bpartner_id = ?",
							clientCode.getC_BPartner_ID());
					bprel.setC_BPartnerRelation_Location_ID(locbp_id);
					bprel.saveEx();
					// Mettre la relation tiers
					ord.setBill_BPartner_ID(clientCode.getC_BPartner_ID());
					ord.saveEx();
				}
			}
		}
		
		return "Traitement terminer >>>>>>>";
		
	}
	

}
