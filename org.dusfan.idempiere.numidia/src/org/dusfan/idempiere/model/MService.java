package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/*
 * 
 */

public class MService extends X_DU_Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = -181552360296396240L;
	
	public MService(Properties ctx, int DU_ServiceType_ID, String trxName) {
		super(ctx, DU_ServiceType_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MService(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
