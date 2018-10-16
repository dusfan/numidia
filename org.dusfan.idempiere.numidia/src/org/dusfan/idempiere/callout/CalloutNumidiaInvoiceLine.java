package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class CalloutNumidiaInvoiceLine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
	
		if ((int) mTab.getValue("AD_Org_ID") == 1000004) {
			if (mField.getColumnName().equals("T_PriceVente") || mField.getColumnName().equals("M_Product_ID")) {
				int m_product_id = (int) mTab.getValue("M_Product_ID");
				if (m_product_id == 1000654 || m_product_id == 1000653) {
					MProduct pr = new MProduct(Env.getCtx(), m_product_id, null);
					MTax tax = new Query(Env.getCtx(), MTax.Table_Name, "C_TaxCategory_ID=?", null)
							.setParameters(new Object[] { pr.getC_TaxCategory_ID() }).first();
					BigDecimal divider = tax.getRate();
					divider = (divider.divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_UP)).add(Env.ONE);
					BigDecimal T_PriceVente = (BigDecimal) mTab.getValue("T_PriceVente");
					T_PriceVente = T_PriceVente.divide(divider, 2, BigDecimal.ROUND_HALF_UP);
					mTab.setValue("PriceEntered", T_PriceVente);
					mTab.setValue("PriceActual", T_PriceVente);
					mTab.setValue("PriceList", T_PriceVente);
				}
			}
		}
		return null;
	}

	

}
