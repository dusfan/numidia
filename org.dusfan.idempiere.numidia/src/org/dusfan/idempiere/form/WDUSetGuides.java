package org.dusfan.idempiere.form;


import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.adempiere.webui.window.FDialog;
import org.compiere.grid.DUSetGuides;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.dusfan.idempiere.model.MVolLine;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Separator;

public class WDUSetGuides extends DUSetGuides implements EventListener<Event>{

	private DUWCreateFromWindow window;
	int ad_user_id;
	String whereClause;
	
	public WDUSetGuides(GridTab tab) 
	{
		super(tab);
		log.info(getGridTab().toString());
		ad_user_id = Env.getAD_User_ID(Env.getCtx());
		whereClause = " not exists " + 
				" (select DU_Visa_Group_ID from DU_Visa_GroupLine l where " + 
				" l.DU_Visa_Group_ID = DU_Visa_Group.DU_Visa_Group_ID) and " +
				"(DU_Visa_Group.Createdby =" + ad_user_id + " or " + ad_user_id + " in (100,1000005))";
		
		window = new DUWCreateFromWindow(this, getGridTab().getWindowNo());
		
		p_WindowNo = getGridTab().getWindowNo();

		try
		{
			if (!dynInit())
				return;
			zkInit();
			setInitOK(true);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			setInitOK(false);
		}
		AEnv.showWindow(window);
	}
	
	/** Window No               */
	private int p_WindowNo;

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());

	private Label lBPartnerCodeClient = new Label();
	private WSearchEditor fBPartnerCodeClient;
	
	
	protected Label lBPartnerGuide = new Label("GUIDE");
	protected WSearchEditor fBPartnerGuide;
	
	protected Label showListLabel = new Label("Afficher");
	protected Listbox  showList = ListboxFactory.newDropdownListbox();

	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	public boolean dynInit() throws Exception
	{
		log.config("");
		
		super.dynInit();
		
		//Refresh button
		Button refreshButton = window.getConfirmPanel().createButton(ConfirmPanel.A_REFRESH);
		refreshButton.addEventListener(Events.ON_CLICK, this);
		window.getConfirmPanel().addButton(refreshButton);
		
		window.setTitle(getTitle());
		
		MLookup bpL = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 2762, DisplayType.Search);
		fBPartnerCodeClient = new WSearchEditor ("C_BPartner_ID", false, false, true, bpL);
		
		fBPartnerGuide = new WSearchEditor ("C_BPartner_ID", false, false, true, bpL);
		
		showList.addItem(new KeyNamePair(1, "Tous"));
		showList.addItem(new KeyNamePair(2, "Nom Affecter"));
		showList.setSelectedIndex(1);
		
		loadlineVolData(null);
		
		return true;
	}   //  dynInit
	
	protected void zkInit() throws Exception
	{
		lBPartnerCodeClient.setText("Code Client");
    	
    	Borderlayout parameterLayout = new Borderlayout();
    	ZKUpdateUtil.setHeight(parameterLayout, "90px");
    	ZKUpdateUtil.setWidth(parameterLayout, "100%");
    	Panel parameterPanel = window.getParameterPanel();
		parameterPanel.appendChild(parameterLayout);
		
		Grid parameterBankLayout = GridFactory.newGridLayout();
    	Panel parameterBankPanel = new Panel();
    	parameterBankPanel.appendChild(parameterBankLayout);

		Center center = new Center();
		parameterLayout.appendChild(center);
		center.appendChild(parameterBankPanel);
		
		Columns columns = new Columns();
		parameterBankLayout.appendChild(columns);
		Column column = new Column();
		columns.appendChild(column);		
		column = new Column();
		ZKUpdateUtil.setWidth(column, "15%");
		columns.appendChild(column);
		ZKUpdateUtil.setWidth(column, "35%");
		column = new Column();
		ZKUpdateUtil.setWidth(column, "15%");
		columns.appendChild(column);
		column = new Column();
		ZKUpdateUtil.setWidth(column, "35%");
		columns.appendChild(column);
		
		Rows rows = (Rows) parameterBankLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(lBPartnerCodeClient.rightAlign());
		row.appendChild(fBPartnerCodeClient.getComponent());
		row.appendChild(lBPartnerGuide.rightAlign());
		row.appendChild(fBPartnerGuide.getComponent());
		
		
		
		row = rows.newRow();
		row.appendCellChild(new Separator());
		
		
		row = rows.newRow();
		row.appendChild(showListLabel.rightAlign());
		row.appendCellChild(showList);
		showList.setWidth(fBPartnerGuide.getComponent().getWidth());
				
		
	}

	/**
	 *  Action Listener
	 *  @param e event
	 * @throws Exception 
	 */
	public void onEvent(Event e) throws Exception
	{
		if (log.isLoggable(Level.CONFIG)) log.config("Action=" + e.getTarget().getId());
		if(e.getTarget().equals(window.getConfirmPanel().getButton(ConfirmPanel.A_REFRESH)))
		{
			loadlineVolData(null);
			window.tableChanged(null);
		}
	}
	
	protected void loadlineVolData(String trxName)
	{
		int c_bpartnerRelation_id = fBPartnerCodeClient.getValue() != null ? (int) fBPartnerCodeClient.getValue() : 0;
		loadTableOIS(getImportData(trxName, getisDisplayAll(), c_bpartnerRelation_id));
	}
	
	protected void loadTableOIS (Vector<?> data)
	{
		window.getWListbox().clear();
		
		//  Remove previous listeners
		window.getWListbox().getModel().removeTableModelListener(window);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(window);
		window.getWListbox().setData(model, getOISColumnNames());
		//
		
		configureMiniTable(window.getWListbox());
	}
	
	@Override
	public boolean save(IMiniTable miniTable, String trxName)
	{
		int c_bp_guide_id = fBPartnerGuide.getValue() != null ? (int) fBPartnerGuide.getValue() : 0;
		
		if (c_bp_guide_id==0) {
			FDialog.warn(p_WindowNo, "Vous devez choisir le guide");
			return false;
		}
		//  Lines
		for(int i = 0; i < miniTable.getRowCount(); i++)
		{
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue())
			{
				KeyNamePair pp = (KeyNamePair) miniTable.getValueAt(i, 1);   //  -du_volLine_id
				int du_volLine_id = pp.getKey();
				setGuides(du_volLine_id, c_bp_guide_id, trxName);
				
			}   //   if selected
		}   //  for all rows

		loadlineVolData(trxName);
		window.tableChanged(null);
		
		return true;
	}   //  save
	
	// set record selected to guide selected
	private void setGuides (int du_volLine_id, int c_bp_guide_id, String trxName) {
		MVolLine vl = new MVolLine(Env.getCtx(), du_volLine_id, trxName);
		if (c_bp_guide_id > 0) {
			MOrder ord = new MOrder(Env.getCtx(), vl.getC_Order_ID(), trxName);
			ord.set_ValueNoCheck("C_BP_Guide_ID", c_bp_guide_id);
			ord.saveEx();
//			MBPartner bp = new MBPartner(Env.getCtx(), ord.getC_BPartner_ID(), trxName);
//			bp.set_ValueNoCheck("isTaxSAR", "Y");
//			bp.saveEx();
		}
	}

	
	public boolean getisDisplayAll () {
		ListItem li = showList.getSelectedItem();
		if (li != null && li.getValue() != null) {
			int type =((Integer) li.getValue()).intValue();
			if (type == 1)
				return true;
		}
		return false;
	}
	
	public void showWindow()
	{
		window.setVisible(true);
	}
	
	public void closeWindow()
	{
		window.dispose();
	}

	@Override
	public Object getWindow() 
	{
		return window;
	}

}
