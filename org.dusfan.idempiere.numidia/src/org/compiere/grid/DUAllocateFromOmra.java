package org.compiere.grid;

import org.compiere.apps.IStatusBar;
import org.compiere.grid.CreateFrom;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;

/**
 * 
 * @author Elaine
 *
 */
public abstract class DUAllocateFromOmra extends CreateFrom 
{
	public DUAllocateFromOmra(GridTab gridTab) 
	{
		super(gridTab);
	}
	
	public void info(IMiniTable miniTable, IStatusBar statusBar)
	{		
		int rows = miniTable.getRowCount();
		int count = 0;
		for(int i = 0; i < rows; i++)
		{
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue())
			{
				count++;
			}
		}
		statusBar.setStatusLine("Selectionner = " + count);
	}
}