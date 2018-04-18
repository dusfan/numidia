package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CalloutNumidiaImportOmra implements IColumnCallout {

	public CalloutNumidiaImportOmra() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
//		if (mField.getColumnName().equals("M_Product_ID") || mField.getColumnName().equals("M_PriceList_ID")) {
//			int m_product_id = mTab.getValue("M_Product_ID")!=null ? (int) mTab.getValue("M_Product_ID"): 0;
//			int m_pricelist_id = mTab.getValue("M_PriceList_ID")!=null ? (int) mTab.getValue("M_PriceList_ID") : 0;
//			if (m_product_id ==0 || m_pricelist_id ==0)
//				mTab.setValue("Price", Env.ZERO);
//			else {
//				Timestamp orderDate = new Timestamp(System.currentTimeMillis());
//				String sql = "SELECT plv.M_PriceList_Version_ID "
//						+ "FROM M_PriceList_Version plv "
//						+ "WHERE plv.M_PriceList_ID=? "						//	1
//						+ " AND plv.ValidFrom <= ? "
//						+ "ORDER BY plv.ValidFrom DESC";
//					//	Use newest price list - may not be future
//
//				int m_PriceList_Version_ID = DB.getSQLValueEx(null, sql, m_pricelist_id, orderDate);
//				BigDecimal price = DB.getSQLValueBD(null, 
//						"Select pricestd from M_ProductPrice where M_PriceList_Version_ID = ?", m_PriceList_Version_ID);
//				mTab.setValue("Price", price);
//			}
//		}
		return null;
	}

}
