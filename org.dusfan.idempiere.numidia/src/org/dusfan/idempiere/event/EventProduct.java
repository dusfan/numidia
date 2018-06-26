package org.dusfan.idempiere.event;

import java.util.Properties;

import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.PO;
import org.compiere.util.Env;

public class EventProduct {
	
	
	public static void setProductVendor(PO po, Properties ctx, String trxName) {
		if (Env.getAD_Org_ID(ctx) != 1000004)
			return;
		MProduct product = (MProduct) po;
		int product_id = product.getM_Product_ID();
		int c_BPartner_id = po.get_ValueAsInt("C_BPartner_ID");
		if (c_BPartner_id>0){
			MProductPO[] list = MProductPO.getOfProduct(ctx, product_id, trxName);
			for (MProductPO mpo:list){
				mpo.deleteEx(true);
			}
			MProductPO productPo = new MProductPO(ctx, 0, trxName);
			productPo.setM_Product_ID(product_id);
			productPo.setC_BPartner_ID(c_BPartner_id);
			productPo.setC_Currency_ID(235);
			productPo.setC_UOM_ID(100);
			productPo.setVendorProductNo(product.getValue());
			productPo.save(trxName);
		}
		
	}

}
