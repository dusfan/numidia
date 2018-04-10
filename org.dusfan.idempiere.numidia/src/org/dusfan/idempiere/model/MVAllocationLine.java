package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MVAllocationLine extends X_DU_VAllocationLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MVAllocationLine(Properties ctx, int DU_VAllocationLine_ID, String trxName) {
		super(ctx, DU_VAllocationLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVAllocationLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		
		return true;
	}


	protected boolean beforeSave(boolean newRecord) {
		if (getDU_VolLine_ID() > 0) {
			MVolLine vl = new MVolLine(getCtx(), getDU_VolLine_ID(), get_TrxName());
			setC_BPartner_ID(vl.getC_BPartner_ID());
			setC_BPartnerRelation_ID(vl.getC_BPartnerRelation_ID());
			setC_Order_ID(vl.getC_Order_ID());
			setDU_Vol_ID(vl.getDU_Vol_ID());
			if (vl.getM_Product_ID() > 0)
				setM_Product_ID(vl.getM_Product_ID());
			if (vl.getTypeRoom()!=null && vl.getTypeRoom().length() > 0)
				setTypeRoom(vl.getTypeRoom());
			if (vl.getC_BP_Mohrem_ID() > 0)
				setC_BP_Mohrem_ID(vl.getC_BP_Mohrem_ID());
			if (vl.getLinkMohrem()!=null && vl.getLinkMohrem().length() > 0)
				setLinkMohrem(vl.getLinkMohrem());
			setNbrDayMekha(vl.getNbrDayMekha());
			setNbrDayMedina(vl.getNbrDayMedina());
		}
		MVAllocation all = new MVAllocation(getCtx(), getDU_VAllocation_ID(), get_TrxName());
		set_Value("Value", all.getValue()); // Ajouter la colonne value sur les lignes d'affectations
		return true;
	}

	@Override
	protected boolean afterDelete(boolean success) {
		int count = DB.getSQLValue(get_TrxName(), "Select count(1) from DU_VAllocationLine where DU_VAllocation_ID = ? ", 
				getDU_VAllocation_ID());
		// Supprimer les afféctations orphelin
		if (count == 0) {
			MVAllocation all = new MVAllocation(getCtx(), getDU_VAllocation_ID(), get_TrxName());
			all.delete(true);
		}
		return super.afterDelete(success);
	}
	
	
	
}
