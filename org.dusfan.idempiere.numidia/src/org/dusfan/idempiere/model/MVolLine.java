package org.dusfan.idempiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrder;
import org.compiere.model.MProduct;
import org.compiere.model.MProductBOM;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MVolLine extends X_DU_VolLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MVolLine(Properties ctx, int DU_VolLine_ID, String trxName) {
		super(ctx, DU_VolLine_ID, trxName);
	}

	public MVolLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	protected boolean beforeSave(boolean newRecord) {
		if (getC_Order_ID() > 0 && newRecord) {
			MOrder ord = new MOrder(getCtx(), getC_Order_ID(), get_TrxName());
			setC_BPartnerRelation_ID(ord.get_ValueAsInt("C_BPartnerRelation_ID"));
			int m_package_id = getPackage(getC_Order_ID());
			if (m_package_id > 0)
				setM_Product_ID(m_package_id);
			if (getTypeRoom(getC_Order_ID()) != null && getTypeRoom(getC_Order_ID()).length() > 0)
				setTypeRoom(getTypeRoom(getC_Order_ID()));
			if (ord.get_ValueAsInt("C_BP_Mohrem_ID") > 0)
				setC_BP_Mohrem_ID(ord.get_ValueAsInt("C_BP_Mohrem_ID"));
			if (ord.get_ValueAsString("LinkMohrem")!=null && ord.get_ValueAsString("LinkMohrem").length() > 0)
				setLinkMohrem(ord.get_ValueAsString("LinkMohrem"));
			setNbrDayMekha(getNbDays(m_package_id, true));
			setNbrDayMedina(getNbDays(m_package_id, false));
			
		}
		return true;
	}

	int getPackage (int c_order_id) {
		int m_product_id =DB.getSQLValue(get_TrxName(),
				"Select m_sejour_id from c_orderline where m_sejour_id > 0 and c_order_id = " + c_order_id);
		return m_product_id;
	}
	String getTypeRoom (int c_order_id) {
		String typeroom =DB.getSQLValueString(get_TrxName(),
				"Select typeroom from c_orderline where m_sejour_id > 0 and c_order_id = " + c_order_id);
		return typeroom;
	}
	
	BigDecimal getNbDays (int m_product_id, Boolean isMekha) {
		if (m_product_id > 0) {
			MProduct product = new MProduct(getCtx(), m_product_id, get_TrxName());
			for (MProductBOM bom : MProductBOM.getBOMLines(product)) {
				MProduct pr = new MProduct(getCtx(), bom.getM_ProductBOM_ID(), get_TrxName());
				if (pr.get_ValueAsString("TypeHotel").equals("1") && isMekha) {
					return bom.getBOMQty();
				} else if (pr.get_ValueAsString("TypeHotel").equals("2") && !isMekha) {
					return bom.getBOMQty();
				}
			}
		}
		return Env.ZERO;
	}
	
	
	

}
