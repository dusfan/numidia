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

/**
 * 
 * @author Elaine
 *
 */
public abstract class DUAllocateFromImport extends DUAllocateFromOmra 
{
	public DUAllocateFromImport(GridTab mTab) 
	{
		super(mTab);
		if (log.isLoggable(Level.INFO)) log.info(mTab.toString());
	}

	public boolean dynInit() throws Exception
	{
		log.config("");
		setTitle("Affecter les package et les VOL");
		
		return true;
	}
	
	protected Vector<Vector<Object>> getImportData(String groupe,String trxName, int du_Visa_Group_ID, boolean isdiplayAll)
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		String grp = groupe.length()==0 ? "-1" : groupe;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT AfirstName,AlastName,CustomerCode,");
		sql.append("(select name from m_product p where p.m_product_id = I_ImportOmraBP.m_product_id) as product,");
		sql.append("(select value from du_vol v where v.du_vol_id = I_ImportOmraBP.du_vol_id) as vol, I_ImportOmraBP_ID,");
		sql.append("(select name from m_product p where p.m_product_id = I_ImportOmraBP.du_presta_id) as presta");
		sql.append(" FROM I_ImportOmraBP");
		sql.append(" where DU_Visa_Group_ID in (" + grp + ")");
		sql.append(" AND I_IsImported<>'Y' ");
		if (!isdiplayAll)
			sql.append(" AND (M_product_id is null or du_vol_id is null)");
		if (du_Visa_Group_ID > 0)
			sql.append(" AND DU_Visa_Group_ID = " + du_Visa_Group_ID);
		sql.append(" AND AD_Client_id = " + Env.getAD_Client_ID(Env.getCtx()));
		sql.append(" ORDER BY DU_Visa_Group_ID");

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
				line.add(pp);                       //  1-Nom
				line.add(rs.getString(2));          //  2-Prenom
				line.add(rs.getString(3));          //  3-Code Client
				line.add(rs.getString(4));          //  4-Package
				line.add(rs.getString(5));          //  5-Vol
				line.add(rs.getString(7));          //  7-Prestation
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
		miniTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		miniTable.setColumnClass(1, String.class, true);     //  1-Nom
		miniTable.setColumnClass(2, String.class, true);        //  2-Prenom
		miniTable.setColumnClass(3, String.class, true);        //  3-Code Client
		miniTable.setColumnClass(4, String.class, true);    //  4-Package
		miniTable.setColumnClass(5, String.class, true);    //  5-Vol
		miniTable.setColumnClass(6, String.class, true);    //  6-Prestation
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
		Vector<String> columnNames = new Vector<String>(8);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add("Nom");
		columnNames.add("Prenom");
		columnNames.add("Code Client");
		columnNames.add("Package");
		columnNames.add("VOL");
		columnNames.add("Prestation");
	    
	    return columnNames;
	}
}