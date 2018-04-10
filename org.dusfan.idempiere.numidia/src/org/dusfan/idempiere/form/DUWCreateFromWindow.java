package org.dusfan.idempiere.form;

import org.adempiere.webui.apps.form.WCreateFromWindow;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.window.FDialog;
import org.compiere.grid.CreateFrom;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.zkoss.zk.ui.event.Event;

public class DUWCreateFromWindow extends WCreateFromWindow 
{
	private static final long serialVersionUID = 6750121735083748182L;

	private int windowNo;
	
	public DUWCreateFromWindow(CreateFrom createFrom, int windowNo)
	{
		super(createFrom, windowNo);
    }
	

	@Override
	public void onEvent(Event e) throws Exception
	{
		
		//  OK - Save
		if (e.getTarget().getId().equals(ConfirmPanel.A_OK))
		{
			super.isCancel();
			try
			{
				Trx.run(new TrxRunnable()
				{
					public void run(String trxName)
					{
						save(trxName);
					}
				});
//				dispose();
			}
			catch (Exception ex)
			{
				FDialog.error(windowNo, this, "Error", ex.getLocalizedMessage());
			}
		}
		//  Cancel
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			super.onEvent(e);
		}
		// Select All
		// Trifon
		else if (e.getTarget().getId().equals(SELECT_DESELECT_ALL)) {
			super.onEvent(e);
		}
		
	}
}