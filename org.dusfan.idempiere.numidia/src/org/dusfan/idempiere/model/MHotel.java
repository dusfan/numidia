package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHotel extends X_DU_Hotel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MHotel(Properties ctx, int DU_Hotel_ID, String trxName) {
		super(ctx, DU_Hotel_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHotel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
