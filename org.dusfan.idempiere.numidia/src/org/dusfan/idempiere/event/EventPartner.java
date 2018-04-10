package org.dusfan.idempiere.event;

import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MImage;
import org.compiere.model.MLocation;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class EventPartner {

	public EventPartner() {
		// TODO Auto-generated constructor stub
	}

	public static void setAdresse (MBPartner bp, String trxName, Properties ctx) {
		if (bp.getC_BP_Group_ID() == 1000001 && bp.get_ValueAsString("TypeClient").equals("2")) {
			int c_BPartner_Location_ID = DB.getSQLValue(trxName,
					"Select C_BPartner_Location_ID from C_BPartner_Location where C_BPartner_ID = ?",
					bp.getC_BPartner_ID());
			MBPartnerLocation mbploc = null;
			MLocation loc = null;
			if (c_BPartner_Location_ID > 0) { // check if exist location
				mbploc = new MBPartnerLocation(ctx, c_BPartner_Location_ID, trxName);
				loc = new MLocation(ctx, mbploc.getC_Location_ID(), trxName);
			} else {
				mbploc = new MBPartnerLocation(bp);
				loc = new MLocation(ctx, 0, trxName);
				loc.setAD_Org_ID(bp.getAD_Org_ID());
			}

			mbploc.setName("Algerie");
			mbploc.setIsBillTo(true);

			loc.setAddress1("Algerie");
			loc.setAddress2("Algerie");
			loc.setC_Country_ID(112);
			loc.saveEx();
			mbploc.setC_Location_ID(loc.getC_Location_ID());
			mbploc.saveEx();
		}
	}
	
	public static void setImage (PO po, String trxName, Properties ctx) {
		MBPartner bp = (MBPartner) po;
		if (bp.getC_BP_Group_ID() == 1000001) {
			String valuebp =  bp.getValue().trim();
			String imageUrl = DB.getSQLValueString(trxName, 
					"Select value from du_imageurl where ad_client_id =?",Env.getAD_Client_ID(ctx));
			imageUrl = "http://192.168.0.80/img/SCANMAWLED2018/"+valuebp + ".jpg";
//			imageUrl = "Refresh24.png"; // test
			MImage img = null;
			if (bp.getLogo_ID() > 0) {
				img = new MImage(ctx, bp.getLogo_ID(), trxName);
				if (!img.getImageURL().contains(valuebp)) {
					DB.executeUpdate("Update ad_image set imageurl = '"+ imageUrl 
							+"' where ad_image_id ="+bp.getLogo_ID(),trxName);
				}
			}
			else {
				img = new MImage(ctx, 0, trxName);
				img.set_ValueNoCheck("AD_Client_ID", Env.getAD_Client_ID(ctx));
				img.setName(valuebp);
				img.setEntityType("U");
				img.setImageURL(imageUrl);
//				img.setImageURL("Archive24.png"); // test
				img.saveEx();
				DB.executeUpdate("Update C_BPartner set Logo_ID="+ img.getAD_Image_ID() +" where C_BPartner_ID ="
						+ bp.getC_BPartner_ID(), trxName);
			}
		}
		
	}
	
	public static void deleteSpace (PO po, String trxName, Properties ctx) {
		MBPartner bp = (MBPartner) po;
		DB.executeUpdate("Update C_BPartner set value='"+ bp.getValue().trim() +"' where C_BPartner_ID ="
				+ bp.getC_BPartner_ID(), trxName);
	}
	
	public static boolean checkCodeClient (PO po, String trxName, Properties ctx) {
//		MBPartner bp = (MBPartner) po;
//		if (bp.get_Value("TypeClient").equals("2") && (Env.getAD_Role_ID(ctx)!=1000013 
//				&& Env.getAD_Role_ID(ctx)!=1000012 && Env.getAD_User_ID(ctx)!=100)) {
//			return false;
//		} else 
//			return true;
		return true;
	}
}
