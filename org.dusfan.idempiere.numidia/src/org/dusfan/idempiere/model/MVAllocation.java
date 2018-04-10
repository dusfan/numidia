package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MVAllocation extends X_DU_VAllocation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MVAllocation(Properties ctx, int DU_VAllocation_ID, String trxName) {
		super(ctx, DU_VAllocation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVAllocation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
