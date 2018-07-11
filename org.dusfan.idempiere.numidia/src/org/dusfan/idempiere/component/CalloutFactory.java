package org.dusfan.idempiere.component;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProductPrice;
import org.dusfan.idempiere.callout.CalloutNumidiaImportOmra;
import org.dusfan.idempiere.callout.CalloutNumidiaInvoice;
import org.dusfan.idempiere.callout.CalloutNumidiaInvoiceLine;
import org.dusfan.idempiere.callout.CalloutNumidiaMPriceVersion;
import org.dusfan.idempiere.callout.CalloutNumidiaOrder;
import org.dusfan.idempiere.callout.CalloutNumidiaOrderLine;
import org.dusfan.idempiere.callout.CalloutNumidiaPackage;
import org.dusfan.idempiere.callout.CalloutNumidiaPartner;
import org.dusfan.idempiere.callout.CalloutNumidiaPayment;
import org.dusfan.idempiere.callout.CalloutNumidiaProductPrice;
import org.dusfan.idempiere.model.MCreatePackage;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;

public class CalloutFactory implements IColumnCalloutFactory {

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		List<IColumnCallout> callout = new ArrayList<IColumnCallout>();
		
		if (tableName.equals(MBPartner.Table_Name))
			callout.add(new CalloutNumidiaPartner());
		else if (tableName.equals(MOrder.Table_Name))
			callout.add(new CalloutNumidiaOrder());
		else if (tableName.equals(MInvoice.Table_Name))
			callout.add(new CalloutNumidiaInvoice());
		else if (tableName.equals(MPayment.Table_Name))
			callout.add(new CalloutNumidiaPayment());
		else if (tableName.equals(MProductPrice.Table_Name))
			callout.add(new CalloutNumidiaProductPrice());
		else if (tableName.equals(X_I_ImportOmraBP.Table_Name)) 
			callout.add(new CalloutNumidiaImportOmra());
		else if (tableName.equals(MOrderLine.Table_Name)) 
			callout.add(new CalloutNumidiaOrderLine());
		else if (tableName.equals(MPriceListVersion.Table_Name)) {
			callout.add(new CalloutNumidiaMPriceVersion());
		}
		else if (tableName.equals(MCreatePackage.Table_Name)) {
			callout.add(new CalloutNumidiaPackage());
		}
		else if (tableName.equals(MInvoiceLine.Table_Name)) {
			callout.add(new CalloutNumidiaInvoiceLine());
		}

		return callout != null ? callout.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
