package org.dusfan.idempiere.form;

import static org.compiere.model.SystemIDs.COLUMN_C_INVOICELINE_M_PRODUCT_ID;

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
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.adempiere.webui.window.FDialog;
import org.compiere.grid.DUAllocateFromImport;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.dusfan.idempiere.model.MVisaGroup;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Separator;

/**
 * 
 * @author Elaine
 *
 */
public class DUWAllocateFromImportUI extends DUAllocateFromImport implements EventListener<Event>
{
	private DUWCreateFromWindow window;
	int ad_user_id;
	int ad_role_id ;
	String whereClause;
	
	public DUWAllocateFromImportUI(GridTab tab) 
	{
		super(tab);
		log.info(getGridTab().toString());
		ad_user_id = Env.getAD_User_ID(Env.getCtx());
		ad_role_id = Env.getAD_Role_ID(Env.getCtx());
		whereClause = " exists " + 
				" (select 1 from DU_Visa_Group v " + 
				" inner join i_importomrabp i on i.du_visa_group_id = v.du_visa_group_id " +
				" where i.i_isimported='N' and DU_Visa_Group.DU_Visa_Group_ID = v.DU_Visa_Group_ID) " +
				" AND (DU_Visa_Group.Createdby =" + ad_user_id + " or " + ad_user_id + " in (100,1000005)"
						+ " OR "+ ad_role_id + "=" + 1000017 + ")";
		
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

	// Package
	protected Label productLabel = new Label();
	protected WSearchEditor productField;
	
	// vol
	protected Label volLabel = new Label("VOL");
	protected WTableDirEditor volField;
	
	protected Label showListLabel = new Label("Afficher");
	protected Listbox  showList = ListboxFactory.newDropdownListbox();
	
	protected Label groupLabel = new Label("Groupe");
	protected WTableDirEditor groupField;
	
	// Prestation
	protected Label prestaLabel = new Label("Prestation");
	protected WTableDirEditor prestaField;
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
		
		// Package
		int AD_Column_ID = COLUMN_C_INVOICELINE_M_PRODUCT_ID;       
		MLookup lookup = MLookupFactory.get(Env.getCtx(), p_WindowNo, DisplayType.Search ,
				AD_Column_ID, Env.getLanguage(Env.getCtx()), "M_Product_ID", -1 ,
				false, " M_Product.isActive='Y' AND (M_Product.TypeService in ('0','2') OR M_Product.isbom = 'Y')");
		productField = new WSearchEditor ("M_Product_ID", true, false, true, lookup);
		
		//  VOL		
		lookup = MLookupFactory.get(Env.getCtx(), p_WindowNo, DisplayType.TableDir ,
				1000425, Env.getLanguage(Env.getCtx()), "DU_Vol_ID", -1 ,
				false, " DU_Vol.isActive='Y' AND trunc(DU_Vol.departdatetime_direct) >= trunc(current_date-10)");
		volField = new WTableDirEditor ("DU_Vol_ID", true, false, true, lookup);
		
		// Prestation
		lookup = MLookupFactory.get(Env.getCtx(), p_WindowNo, DisplayType.TableDir ,
				AD_Column_ID, Env.getLanguage(Env.getCtx()), "M_Product_ID", -1 ,
				false, " M_Product.isActive='Y' AND M_product.TypeService = '6'");
		prestaField = new WTableDirEditor ("M_Product_ID", true, false, true, lookup);
		
		// Group Visa
		lookup =  MLookupFactory.get(Env.getCtx(), p_WindowNo, DisplayType.TableDir ,
				1000425, Env.getLanguage(Env.getCtx()), "DU_Visa_Group_ID", -1 ,
				false, whereClause);
		groupField = new WTableDirEditor ("DU_Visa_Group_ID", true, false, true, lookup);
		
		showList.addItem(new KeyNamePair(1, "Tous"));
		showList.addItem(new KeyNamePair(2, "Nom Affecter"));
		showList.setSelectedIndex(1);
		
		loadImportData(null);
		
		return true;
	}   //  dynInit
	
	protected void zkInit() throws Exception
	{
		productLabel.setText("Package");
    	
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
		row.appendChild(productLabel.rightAlign());
		row.appendChild(productField.getComponent());
		row.appendChild(volLabel.rightAlign());
		row.appendChild(volField.getComponent());
		
		row = rows.newRow();
		row.appendChild(new Label(""));
		row.appendChild(new Label(""));
		row.appendChild(prestaLabel.rightAlign());
		row.appendChild(prestaField.getComponent());
		
		row = rows.newRow();
		row.appendCellChild(new Separator());
		
		
		row = rows.newRow();
		row.appendChild(showListLabel.rightAlign());
		row.appendCellChild(showList);
		showList.setWidth(groupField.getComponent().getWidth());
		row.appendChild(groupLabel.rightAlign());
		row.appendChild(groupField.getComponent());
				
		
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
			loadImportData(null);
			window.tableChanged(null);
		}
	}
	
	protected void loadImportData(String trxName)
	{
		int ids [] = MVisaGroup.getAllIDs(MVisaGroup.Table_Name, whereClause, null);
		String groupe = "";
		for (int id : ids)
		{
			groupe += id + ",";
		}
		if (groupe.length() > 0)
			groupe = groupe.substring(0,groupe.length()-1);
		loadTableOIS(getImportData(groupe, trxName, getDU_Visa_Group_ID(), getisDisplayAll()));
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
		int m_product_id = productField.getValue() != null ? (int) productField.getValue() : 0;
		int du_vol_id = volField.getValue() != null ? (int) volField.getValue() : 0;
		int du_prestat_id = prestaField.getValue() != null ? (int)prestaField.getValue() : 0;
		
		if (m_product_id ==0 && du_vol_id==0) {
			FDialog.warn(p_WindowNo, "Vous devez mettre le vol ou le package");
			return false;
		}
		//  Lines
		for(int i = 0; i < miniTable.getRowCount(); i++)
		{
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue())
			{
				KeyNamePair pp = (KeyNamePair) miniTable.getValueAt(i, 1);   //  -I_ImportOmraBp_id
				int I_ImportOmraBp_id = pp.getKey();
				setPackageVol(I_ImportOmraBp_id, m_product_id, du_vol_id,du_prestat_id, trxName);
				
			}   //   if selected
		}   //  for all rows

		loadImportData(trxName);
		window.tableChanged(null);
		
		return true;
	}   //  save
	
	// set record selected to package and vol
	private void setPackageVol (int I_ImportOmraBp_id, int m_product_id, int du_vol_id,int du_presta_id,
			String trxName) {
		X_I_ImportOmraBP imp = new X_I_ImportOmraBP(Env.getCtx(), I_ImportOmraBp_id, trxName);
		if (m_product_id > 0)
			imp.setM_Product_ID(m_product_id);
		if (du_vol_id > 0)
			imp.setDU_Vol_ID(du_vol_id);
		if (du_presta_id > 0)
			imp.set_ValueNoCheck("DU_Presta_ID", du_presta_id);
		imp.saveEx();
	}
	
	public int getDU_Visa_Group_ID () {
		int du_Visa_Group_ID = groupField.getValue()!=null ? (int) groupField.getValue() : 0;
		return du_Visa_Group_ID;
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