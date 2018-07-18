package org.dusfan.idempiere.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CalloutNumidiaInvoiceLine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
	
		if (mField.getColumnName().equals("T_PriceVente")) {
			if ((int)mTab.getValue("AD_Org_ID") == 1000004) {
				BigDecimal T_PriceVente = (BigDecimal) mTab.getValue("T_PriceVente");
				T_PriceVente = T_PriceVente.divide(new BigDecimal("1.09"), 2, BigDecimal.ROUND_HALF_UP);
				mTab.setValue("PriceEntered", T_PriceVente);
				mTab.setValue("PriceActual", T_PriceVente);
				mTab.setValue("PriceList", T_PriceVente);
			}
		}
		return null;
	}

	

}
