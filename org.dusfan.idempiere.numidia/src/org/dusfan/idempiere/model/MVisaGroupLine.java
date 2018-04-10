package org.dusfan.idempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MVisaGroupLine extends X_DU_Visa_GroupLine {

	private static final long serialVersionUID = 1L;

	public MVisaGroupLine(Properties ctx, int DU_Visa_GroupLine_ID, String trxName) {
		super(ctx, DU_Visa_GroupLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MVisaGroupLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if ((this.get_ValueAsString("SponserID") != null && this.get_ValueAsString("SponserID").length() > 0)
				&& (this.get_Value("C_BP_Mohrem_ID") == null || this.get_ValueAsInt("C_BP_Mohrem_ID") <= 0)) {

			int c_bp_mohrem_id = DB.getSQLValue(get_TrxName(),
					"Select c_bpartner_id from du_visa_groupline where du_visa_group_id = ? AND PilgrimID ='"
							+ this.get_ValueAsString("SponserID") + "'",
					getDU_Visa_Group_ID());
			if (c_bp_mohrem_id > 0) {
				this.set_ValueNoCheck("C_BP_Mohrem_ID", c_bp_mohrem_id);
			}
		}

		return super.beforeSave(newRecord);
	}
	
	
	
	

}
