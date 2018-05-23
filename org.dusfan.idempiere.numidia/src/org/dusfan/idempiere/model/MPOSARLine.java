package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPOSARLine extends X_DU_POSARLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MPOSARLine(Properties ctx, int DU_POSARLine_ID, String trxName) {
		super(ctx, DU_POSARLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPOSARLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
