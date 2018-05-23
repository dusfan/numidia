package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPOSAR extends X_DU_POSAR {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MPOSAR(Properties ctx, int DU_POSAR_ID, String trxName) {
		super(ctx, DU_POSAR_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPOSAR(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
