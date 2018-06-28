package org.dusfan.idempiere.callout;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MOrder;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CalloutNumidiaPartner implements IColumnCallout {


	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (mField.getColumnName().equals("birth_date")) { // mettre l'age depuis la date de naissance
			Timestamp birth_date = (Timestamp) value;
			if (birth_date != null) {
				if (birth_date.after(new Timestamp(System.currentTimeMillis()))) {
					mTab.fireDataStatusEEvent("La date de naissance est au future", null, false);
				} else {
					String convertdate = "to_date('" + birth_date + "', 'YYYY-MM-DD')";
					int age = DB.getSQLValue(null, "select COALESCE(EXTRACT(YEAR from AGE(current_date," + convertdate
							+ ")),0) " + "from ad_client where ad_client_id = ?", Env.getAD_Client_ID(ctx));
					mTab.setValue("Age", age);

					int month = DB
							.getSQLValue(
									null, "select COALESCE(EXTRACT(month from AGE(current_date," + convertdate
											+ ")),0) " + "from ad_client where ad_client_id = ?",
									Env.getAD_Client_ID(ctx));
					mTab.setValue("AgeMonth", month);

					int day = DB.getSQLValue(null, "select COALESCE(EXTRACT(day from AGE(current_date," + convertdate
							+ ")),0) " + "from ad_client where ad_client_id = ?", Env.getAD_Client_ID(ctx));
					mTab.setValue("AgeDay", day);
				}

			} else {
				mTab.setValue("Age", null);
				mTab.setValue("AgeMonth", null);
				mTab.setValue("AgeDay", null);
			}
				
		} else if (mField.getColumnName().equals("TypeGender")) { // mettre le Sex de puis le titre
			String typeGender = (String) value;
			mTab.setValue("Sexe", getSex(typeGender));
			
		} else if (mField.getColumnName().equals("C_BP_Group_ID")) { // affecter les paramètres de facturation et paiement
			 setRules(mTab);
		} else if (mField.getColumnName().equals("DateStart")) {
			Timestamp startDate = (Timestamp) value;
			if (startDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.YEAR, 10);
				cal.add(Calendar.DATE, -1);
				startDate = new Timestamp(cal.getTime().getTime());
				mTab.setValue("EndDate", startDate);
			} else
				mTab.setValue("EndDate", null);

		} else if (mField.getColumnName().equals("C_BP_Mohrem_ID")) {
			if (value !=null) {
				int C_BP_Mohrem_ID = (int) value;
				int c_bpartner_id = DB.getSQLValue(null,
						"select c_bpartner_id from c_bpartner where value = 'groupfemme'");
				if (C_BP_Mohrem_ID > 0 && C_BP_Mohrem_ID == c_bpartner_id )
					mTab.setValue("LinkMohrem", "15");
			}
		} else if (mField.getColumnName().equals("C_BPartnerRelation_ID")) {
			int c_bp_group_id = mTab.getValue("C_BP_Group_ID")!=null ? (int) mTab.getValue("C_BP_Group_ID") : 0;
			if (c_bp_group_id > 0 && c_bp_group_id == 1000001) {
				if (value != null) {
					int c_bpartnerRelation_id = (int) value;
					int M_DiscountSchema_ID = DB.getSQLValue(null, "Select M_DiscountSchema_ID from c_bpartner where c_bpartner_id = ?",
							c_bpartnerRelation_id);
					if (M_DiscountSchema_ID > 0)
						mTab.setValue("M_DiscountSchema_ID", M_DiscountSchema_ID);
				} else {
					mTab.setValue("M_DiscountSchema_ID", null);
				}
			}
		}

		return null;
	}

	
	private String getSex (String typeGender) {
		if (typeGender == null)
			return null;
		if (typeGender.equalsIgnoreCase("2") || typeGender.equalsIgnoreCase("3"))
			return "2";
		else
			return "1";
		
	}
	
	private void setRules (GridTab mTab) {
		int c_bp_group_id = mTab.getValue("C_BP_Group_ID")!= null ? (int)mTab.getValue("C_BP_Group_ID") : 0 ;
		if (c_bp_group_id > 0 && (c_bp_group_id == 1000001 || c_bp_group_id==1000000)) {
			mTab.setValue("InvoiceRule", MOrder.INVOICERULE_Immediate);
			mTab.setValue("DeliveryRule", MOrder.DELIVERYRULE_Availability);
			mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_OnCredit);
			mTab.setValue("PaymentRulePO", MOrder.PAYMENTRULE_OnCredit);
			mTab.setValue("C_PaymentTerm_ID", 1000001);
			mTab.setValue("PO_PaymentTerm_ID", 1000001);
			mTab.setValue("M_PriceList_ID", 1000002);
			mTab.setValue("Invoice_PrintFormat_ID", 1000018);
		} else if (c_bp_group_id == 1000003) {
			mTab.setValue("InvoiceRule", MOrder.INVOICERULE_Immediate);
			mTab.setValue("DeliveryRule", MOrder.DELIVERYRULE_Availability);
			mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_OnCredit);
			mTab.setValue("PaymentRulePO", MOrder.PAYMENTRULE_OnCredit);
			mTab.setValue("C_PaymentTerm_ID", 1000004);
			mTab.setValue("PO_PaymentTerm_ID", 1000004);
			mTab.setValue("M_PriceList_ID", 1000006);
		}
	}
	

}
