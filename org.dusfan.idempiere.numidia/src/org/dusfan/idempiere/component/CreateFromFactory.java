package org.dusfan.idempiere.component;

import org.compiere.grid.ICreateFrom;
import org.compiere.grid.ICreateFromFactory;
import org.compiere.model.GridTab;
import org.dusfan.idempiere.form.DUWAllocateFromImportUI;
import org.dusfan.idempiere.form.WDUSetGuides;
import org.dusfan.idempiere.model.I_I_ImportOmraBP;
import org.dusfan.idempiere.model.MVol;

/**
 * 
 * @author Elaine
 *
 */
public class CreateFromFactory implements ICreateFromFactory 
{

	@Override
	public ICreateFrom create(GridTab mTab) 
	{
		String tableName = mTab.getTableName();
		 if (tableName.equals(I_I_ImportOmraBP.Table_Name))
			return new DUWAllocateFromImportUI(mTab);
		 else if (tableName.equals(MVol.Table_Name))
			return new WDUSetGuides(mTab);
		
		return null;
	}

}