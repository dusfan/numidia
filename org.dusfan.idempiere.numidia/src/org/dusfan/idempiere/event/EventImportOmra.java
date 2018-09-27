package org.dusfan.idempiere.event;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;

public class EventImportOmra {
	
	// Set package info
	public static void setPackageFromImport (PO po, Properties ctx, String trxName) {
		X_I_ImportOmraBP imp = (X_I_ImportOmraBP)po;
		// Set price
		BigDecimal price = Env.ZERO, pDP = Env.ZERO, ptaxe  = Env.ZERO;
		if (imp.getM_Product_ID() > 0)
			price = DB.getSQLValueBD(null, "SELECT bomPriceStd(?,1000003) "
					+ " from m_product where m_product_id ="+imp.getM_Product_ID(), imp.getM_Product_ID());
		if (imp.getDU_Presta_ID() > 0)
			pDP = DB.getSQLValueBD(null, "SELECT bomPriceStd(?,1000003) "
					+ " from m_product where m_product_id ="+imp.getDU_Presta_ID(), imp.getDU_Presta_ID());
		if (imp.getC_Charge_ID() > 0)
			ptaxe = DB.getSQLValueBD(null, "Select ChargeAmt from C_Charge where c_charge_id = ?", imp.getC_Charge_ID());
		po.set_ValueNoCheck("Price", price.add(pDP).add(ptaxe));
		
		if (imp.getM_Product_ID() > 0) {
			MProduct pr = new MProduct(ctx, imp.getM_Product_ID(), trxName);
			po.set_ValueNoCheck("TypeRoom", pr.get_Value("TypeRoom"));
			po.set_ValueNoCheck("DU_Hotel_ID", pr.get_Value("DU_Hotel_ID"));
			po.set_ValueNoCheck("Saison_Omra", pr.get_Value("Saison_Omra"));
			po.set_ValueNoCheck("ClassHotel", pr.get_Value("ClassHotel"));
		}
		else
		{
			po.set_ValueNoCheck("TypeRoom", null);
			po.set_ValueNoCheck("DU_Hotel_ID", null);
			po.set_ValueNoCheck("Saison_Omra", null);
			po.set_ValueNoCheck("ClassHotel", null);
		}
	}

}
