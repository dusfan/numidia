package org.dusfan.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MInvoice;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;

public class ExtourneOmraInvoiceByflight extends SvrProcess {

	// vol
	private int m_DU_Vol_ID = 0;
	// tiers
	private int m_C_BPartner_ID = 0;
	// Org
	private int m_AD_Org_ID = 0;
	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("C_BPartner_ID"))
				m_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("DU_Vol_ID")) {
				m_DU_Vol_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("AD_Org_ID")) {
				m_AD_Org_ID = para[i].getParameterAsInt();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		
		String whereClause = "DU_Vol_ID ="+m_DU_Vol_ID + " AND C_BPartner_ID = "
				+ m_C_BPartner_ID + " AND AD_Org_ID="+m_AD_Org_ID + " AND DocStatus = 'CO'";
		
		int [] IDinvoice = MInvoice.getAllIDs(MInvoice.Table_Name, whereClause, get_TrxName());
		if (IDinvoice.length == 0)
			throw new AdempiereUserError("Attention il n y a pas de facture pour ce client");
		int nbr = 0;
		for (int i = 0; i < IDinvoice.length; i++) {
			MInvoice inv = new MInvoice(getCtx(), IDinvoice[i], get_TrxName());
			inv.processIt(DocAction.ACTION_Reverse_Correct);
			inv.saveEx();
			nbr++;
		}
		return "Nombre de facture annuler = "+nbr + " N'oubier pas de les regénérer";
	}

}
