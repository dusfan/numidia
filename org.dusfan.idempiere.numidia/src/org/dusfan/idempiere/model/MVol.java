package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MVol extends X_DU_Vol {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MVol(Properties ctx, int DU_Vol_ID, String trxName) {
		super(ctx, DU_Vol_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVol(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeDelete() {
		int count = DB.getSQLValue(get_TrxName(), "select du_vol_id from du_volline where du_vol_id ="+ getDU_Vol_ID());
		if (count > 0) {
			log.saveError("Pour supprimer le vol il faut annuler tous ses réservations", count+""); 
			return false;
		}
		return super.beforeDelete();
	}
	
	

}
