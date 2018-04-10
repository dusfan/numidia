package org.dusfan.idempiere.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MRequest;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Center;
import org.zkoss.zul.North;
import org.zkoss.zul.Separator;
import org.zkoss.zul.South;
import org.zkoss.zul.Space;

/**
 * Generate claims change their status from open to in progress
 * @author aka
 *
 */
public class WRequestChangeStatus extends ADForm implements
		ValueChangeListener, EventListener<Event>, WTableModelListener {

	private static final long serialVersionUID = 1L;
	private static final CLogger log = CLogger.getCLogger(WRequestChangeStatus.class);
	public Properties ctx = Env.getCtx();
	private int p_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	protected int p_WindowNo;
	private String  m_sql;
	
	// North
	private WSearchEditor editorBPartner;
	private Combobox fRequestType = new Combobox();
	private Combobox fC_Activity = new Combobox();
	private WSearchEditor editorUser;
	private Label lRequest = new Label(Msg.translate(Env.getCtx(), "R_RequestType_ID"));
	private Label lActivity = new Label(Msg.translate(Env.getCtx(), "C_Activity_ID"));
	private Panel northPan = new Panel();
	
	// Center
	private Borderlayout mainLayout = new Borderlayout();
	private Panel mainPanel = new Panel();
	private Panel centerpanale = new Panel();
	private WListbox miniTable = ListboxFactory.newDataTable();
	
	// South
	private Panel southPanel = new Panel();
	private ConfirmPanel commandPanel = new ConfirmPanel(false, true, false,
			false, false, false, true);
	private Button bRefresh = commandPanel.getButton(ConfirmPanel.A_REFRESH);
	private Button bOk = commandPanel.getButton(ConfirmPanel.A_OK);
	private Label status = new Label();
	private Label select = new Label();

	@Override
	protected void initForm() {
		p_WindowNo = getWindowNo();
		if (p_WindowNo <= 0)
			p_WindowNo = SessionManager.getAppDesktop().registerWindow(this);
		try {
			zkInit();
			dynInit();
			log.log(Level.INFO, "Form WRequestChangeStatus loaded");
		} catch (Exception ex) {
			log.log(Level.SEVERE, "init", ex);
			ex.printStackTrace();
		}
	}

	
	private void zkInit() throws Exception {
		fillRequestType(fRequestType);
		fillActivity(fC_Activity);
		
		MColumn column = new MColumn(ctx, 2762, null);
		MLookup lookup = MLookupFactory.get(ctx, p_WindowNo,
				column.getAD_Column_ID(), column.getAD_Reference_ID(),
				Env.getLanguage(ctx), column.getColumnName(),
				column.getAD_Reference_Value_ID(), column.isParent(),
				"IsCustomer='Y'");
		editorBPartner = new WSearchEditor(lookup, Msg.translate(
				Env.getCtx(), "C_BPartner_ID"), "", true, false, true);
		editorBPartner.addValueChangeListener(this);
		
		column = new MColumn(ctx, 3513, null);
		lookup = MLookupFactory.get(ctx, p_WindowNo,
				column.getAD_Column_ID(), column.getAD_Reference_ID(),
				Env.getLanguage(ctx), column.getColumnName(),
				column.getAD_Reference_Value_ID(), column.isParent(),
				null);
		editorUser = new WSearchEditor(lookup, Msg.translate(
				Env.getCtx(), "AD_User_ID"), "", true, false, true);
		editorUser.addValueChangeListener(this);
		
		fRequestType.addEventListener(Events.ON_SELECT, this);
		fC_Activity.addEventListener(Events.ON_SELECT, this);
		
		bRefresh.addEventListener(Events.ON_CLICK, this);
		bOk.addEventListener(Events.ON_CLICK, this);
		
		prepareTable(miniTable);
		loadTableInfo(miniTable);
		miniTable.getModel().addTableModelListener(this);
	}
	
	private void dynInit() throws Exception {
		
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		this.setHeight("100%");
		this.setWidth("100%");
		this.appendChild(mainPanel);
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		
		// North
		North north = new North();
		north.setVflex("min");
		Grid gridNorth = GridFactory.newGridLayout();
		Rows rows = new Rows();
		gridNorth.appendChild(rows);
		
		Row row = new Row();
		rows.appendChild(row);
		row.setAlign("right");
		row.appendChild(lRequest);
		lRequest.setHflex("0");
		row.appendChild(fRequestType);
		fRequestType.setHflex("0");
		row.appendChild(editorBPartner.getLabel());
		row.appendChild(editorBPartner.getComponent());
		editorBPartner.getComponent().setHflex("0");
		row.appendChild(new Space());
		
		row = new Row();
		rows.appendChild(row);
		row.setAlign("right");
		row.appendChild(lActivity);
		lActivity.setHflex("0");
		row.appendChild(fC_Activity);
		fC_Activity.setHflex("0");
		row.appendChild(editorUser.getLabel());
		row.appendChild(editorUser.getComponent());
		editorUser.getComponent().setHflex("0");
		row.appendChild(new Space());
		
		northPan.appendChild(gridNorth);
		north.appendChild(northPan);
		mainLayout.appendChild(north);
		
		// Center 
		Center center = new Center();
		centerpanale.appendChild(miniTable);
		centerpanale.setHeight("100%");
		center.appendChild(centerpanale);
		mainLayout.appendChild(center);
		
		
		// South
		South south = new South();
		south.appendChild(southPanel);
		southPanel.appendChild(commandPanel);
		southPanel.appendChild(status);
		southPanel.appendChild(new Space());
		southPanel.appendChild(select);
		southPanel.appendChild(new Separator());
		mainLayout.appendChild(south);
		
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		
		if (event.getName().equals(Events.ON_SELECT) || (event.getName().equals(Events.ON_CLICK) 
				&& event.getTarget() == bRefresh)) {
			loadTableInfo(miniTable);
		}
		else if (event.getName().equals(Events.ON_CLICK) && event.getTarget() == bOk) {
			generateClaims ();
			loadTableInfo(miniTable);
		}
	}
	
	@Override
	public void valueChange(ValueChangeEvent e) {
		if (e.getSource() == editorBPartner)
		{
			editorBPartner.setValue(e.getNewValue());
		}
		
		if (e.getSource() == editorUser)
		{
			editorUser.setValue(e.getNewValue());
		}
		
		loadTableInfo(miniTable);

	}
	
	private void fillRequestType (Combobox field) {
		String sql = "SELECT name, R_RequestType_ID FROM R_RequestType where isactive='Y' "
                     + " and ad_client_id="+ p_AD_Client_ID + " order by name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				String value = rs.getString(1);
				field.appendItem(value, rs.getString(2));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}	
	}

	private void fillActivity (Combobox field) {
		String sql = "SELECT name, C_Activity_ID FROM C_Activity where isactive='Y' "
				+ " AND IsSummary='N' AND (C_Activity_ID in (select c_activity_id "
				+ " from c_activity where 1 = ? ) OR 100 = ?) "
                + " and ad_client_id= ? order by name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_User_ID(ctx));
			pstmt.setInt(2, Env.getAD_User_ID(ctx));
			pstmt.setInt(3, p_AD_Client_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				String value = rs.getString(1);
				field.appendItem(value, rs.getString(2));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}
	

	private void prepareTable(IMiniTable miniTable) {
		
		m_sql = miniTable.prepareTable(new ColumnInfo[] {
				//  0..4
				new ColumnInfo(" ", "R_Request_ID", String.class, true, false, null),
				new ColumnInfo("DocumentNo", "DocumentNo", String.class, false,false,null),
				new ColumnInfo(Msg.translate(ctx, "AD_User_ID"), "u.Name", String.class),
				new ColumnInfo(Msg.translate(ctx, "C_BPartner_ID"), "b.Name", String.class),
				new ColumnInfo(Msg.translate(ctx, "Summary"), "Summary", String.class),
				new ColumnInfo(Msg.getMsg(ctx, "Created"), "req.Created", Timestamp.class)
				,
				},
				//	FROM
				"R_Request req LEFT OUTER JOIN AD_User u on u.ad_user_id = req.salesrep_id "
				+ " LEFT OUTER JOIN c_bpartner b on b.c_bpartner_id = req.c_bpartner_id ",
				//	WHERE
				" req.ad_client_id = ? AND req.ad_org_id = ? AND R_Status_ID = ? ",
				true, "req");
	}
	
	private void loadTableInfo (IMiniTable miniTable)  {
		
		if (m_sql == null)
			return;
		int ad_Org_ID = Env.getAD_Org_ID(ctx);
		String sql = m_sql;
		
		int r_RequestType_ID = fRequestType.getValue().length() > 0 ? 
				Integer.parseInt(fRequestType.getSelectedItem().getValue().toString()) : -1;
		int c_Activity_ID = fC_Activity.getValue().length() > 0 ?
				Integer.parseInt(fC_Activity.getSelectedItem().getValue().toString()) : -1;
				
		if (r_RequestType_ID > 0)
			sql += " AND req.R_RequestType_ID = ? ";
		if (editorBPartner.getValue() != null)
			sql += " AND req.C_bpartner_id = ? ";
		if (c_Activity_ID > 0)
			sql += " AND req.C_Activity_ID = ? ";
		if (editorUser.getValue() != null)
			sql += " AND req.SalesRep_ID = ? ";
		
		sql += " ORDER BY req.created desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			int index = 1;
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(index++, p_AD_Client_ID);		//	Client
			pstmt.setInt(index++, ad_Org_ID);// Org
			pstmt.setInt(index++, getOpenR_Status_ID()); // Open Status
			if (r_RequestType_ID > 0)
				pstmt.setInt(index++, r_RequestType_ID); // Request Type
			if (editorBPartner.getValue() != null)
				pstmt.setInt(index++, (Integer)editorBPartner.getValue()); // Partner
			if (c_Activity_ID > 0)            // Branch
				pstmt.setInt(index++, c_Activity_ID);
			if (editorUser.getValue() != null) // User
				pstmt.setInt(index++, (Integer)editorUser.getValue());
			//
			rs = pstmt.executeQuery();
			miniTable.loadTable(rs);
			status.setText("Total = " + miniTable.getRowCount());
			select.setText("Selected = " + getSelection());
//			bOk.setEnabled(getSelection() > 0);
		}
		catch (SQLException e)
		{
			throw new DBException(e);
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}
	
	private void generateClaims () {
		
			int rows = miniTable.getRowCount();
			Trx trx = Trx.get(Trx.createTrxName("ReqChange"), true);
			for (int i = 0; i < rows; i++)
			{
				IDColumn id = (IDColumn)miniTable.getValueAt(i, 0);     //  ID in column 0
				if (id != null && id.isSelected()) {
					try {
						MRequest re = new MRequest(ctx, id.getRecord_ID(), trx.getTrxName());
						re.setR_Status_ID(getWaitingR_Status_ID()); // Change to inprogress
						if (re.save())
							trx.commit();
						else
							trx.rollback();
						
					} catch (Exception e) {
						if (trx != null) {
							trx.rollback();
							trx.close();
							trx = null;
						}
					} finally {
						if (trx != null) {
							trx.close();
							trx = null;
						}
					}
				}
			}
		}
	
	private int getOpenR_Status_ID () {
		String sql = "Select R_Status_ID from R_Status where ad_client_id = " + p_AD_Client_ID +
				" AND upper(value) = 'OPEN'";
		int r_Status_ID = DB.getSQLValue(null, sql);
		return r_Status_ID;
	}
	
	private int getWaitingR_Status_ID () {
		String sql = "Select R_Status_ID from R_Status where ad_client_id = " + p_AD_Client_ID +
				" AND upper(value) = 'WAITING'";
		int r_Status_ID = DB.getSQLValue(null, sql);
		return r_Status_ID;
	}
	
	private int getSelection () {
		int select = miniTable.getSelectedCount();
		if (select > 0)
			return select;
		return 0;
	}


	@Override
	public void tableChanged(WTableModelEvent e) {
		if (e.getColumn() == 0) {
			select.setText("Selected = " + getSelection()); // a tester
//			bOk.setEnabled(getSelection() > 0);
		}
	}

	
}
