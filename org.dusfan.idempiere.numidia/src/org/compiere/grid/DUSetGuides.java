package org.compiere.grid;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

public class DUSetGuides extends DUAllocateFromOmra {

	public DUSetGuides(GridTab mTab) {
		super(mTab);
		if (log.isLoggable(Level.INFO)) log.info(mTab.toString());
	}

	public boolean dynInit() throws Exception
	{
		log.config("");
		setTitle("Affecter les guides");
		
		return true;
	}
	
	protected Vector<Vector<Object>> getImportData(String trxName, boolean isdiplayAll, int c_bpartnerRelation_id)
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		int du_Vol_ID = ((Integer) getGridTab().getValue("DU_Vol_ID")).intValue();
		
		StringBuilder sql = new StringBuilder();
		sql.append("Select (SELECT value from du_vol where du_vol_id = vl.du_vol_id) as vol,");
		sql.append(" (select name_ar ||'-'|| name2_ar from c_bpartner where c_bpartner_id = vl.c_bpartner_id) as namebp "
				+ ",(select name from c_bpartner where c_bpartner_id = o.c_bpartnerrelation_id) as codeclient,");
		sql.append(" o.dateordered,");
		sql.append(" (select name from c_bpartner where c_bpartner_id = o.c_bp_guide_id) as guide, vl.du_volLine_id");
		sql.append(" FROM DU_VolLine vl");
		sql.append(" inner join c_order o on o.c_order_id = vl.c_order_id ");
		sql.append(" where vl.isInclude='Y' AND vl.DU_VOL_ID=" + du_Vol_ID);
		if (c_bpartnerRelation_id > 0)
			sql.append(" AND o.c_bpartnerRelation_id in (" + c_bpartnerRelation_id + ")");
		if (!isdiplayAll)
			sql.append(" AND o.c_bp_guide_id is null");
		sql.append(" AND vl.AD_Client_id = " + Env.getAD_Client_ID(Env.getCtx()));
		sql.append(" ORDER BY 3");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				
				Vector<Object> line = new Vector<Object>(5);
				line.add(new Boolean(false));       //  0-Selection
				KeyNamePair pp = new KeyNamePair(rs.getInt(6), rs.getString(1));
				line.add(pp);                       //  1-Vol
				line.add(rs.getString(2));          //  2-Nom tiers
				line.add(rs.getString(3));          //  3-Code Client
				line.add(rs.getTimestamp(4));       //  4-Date
				line.add(rs.getString(5));          //  5-Guide
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return data;
	}
	
	protected void configureMiniTable(IMiniTable miniTable)
	{
		miniTable.setColumnClass(0, Boolean.class, false);   //  0-Selection
		miniTable.setColumnClass(1, String.class, true);     //  1-Vol
		miniTable.setColumnClass(2, String.class, true);     //  2-Tiers
		miniTable.setColumnClass(3, String.class, true);     //  3-Code Client
		miniTable.setColumnClass(4, Timestamp.class, true);  //  4-Date
		miniTable.setColumnClass(5, String.class, true);     //  5-Guide
		//  Table UI
		miniTable.autoSize();
	}

	public boolean save(IMiniTable miniTable, String trxName)
	{
		//  Lines
		for(int i = 0; i < miniTable.getRowCount(); i++)
		{
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue())
			{
				Timestamp trxDate = (Timestamp) miniTable.getValueAt(i, 1);  //  1-DateTrx
				KeyNamePair pp = (KeyNamePair) miniTable.getValueAt(i, 2);   //  2-C_Payment_ID
				int C_Payment_ID = pp.getKey();
				pp = (KeyNamePair) miniTable.getValueAt(i, 3);               //  3-Currency
				int C_Currency_ID = pp.getKey();
				BigDecimal TrxAmt = (BigDecimal) miniTable.getValueAt(i, 5); //  5- Conv Amt

				if (log.isLoggable(Level.FINE)) log.fine("Line Date=" + trxDate
					+ ", Payment=" + C_Payment_ID + ", Currency=" + C_Currency_ID + ", Amt=" + TrxAmt);
				
			}   //   if selected
		}   //  for all rows
		return true;
	}   //  save
	
	protected Vector<String> getOISColumnNames()
	{
		//  Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add("VOL");
		columnNames.add("Tiers");
		columnNames.add("Code Client");
		columnNames.add("Date");
		columnNames.add("Guide");
	    
	    return columnNames;
	}

	@Override
	public Object getWindow() {
		// TODO Auto-generated method stub
		return null;
	}

}
