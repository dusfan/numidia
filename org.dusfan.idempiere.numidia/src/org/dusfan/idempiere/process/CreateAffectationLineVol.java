package org.dusfan.idempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MVAllocation;
import org.dusfan.idempiere.model.MVAllocationLine;
import org.dusfan.idempiere.model.MVolLine;

public class CreateAffectationLineVol extends SvrProcess {
	
	private int du_Hotel_ID = 0;
	private String typeRoom = "";
	private BigDecimal amoutVH = Env.ZERO;
	private boolean isVIP = false;
	private int du_HotelMD_ID = 0;
	private String NRVK = "";
	private String NRVD = "";
	
	private Timestamp DateEntreMD = null;
	private Timestamp DateEntreMK = null;
	private Timestamp DateSortieMD = null;
	private Timestamp DateSortieMK = null;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("DU_Hotel_ID"))
				du_Hotel_ID = para[i].getParameterAsInt();
			else if (name.equals("TypeRoom"))
				typeRoom= para[i].getParameterAsString();
			else if (name.equals("AmoutVH")) {
				amoutVH = para[i].getParameterAsBigDecimal();
			}
			else if (name.equals("isVIP"))
				isVIP = "Y".equals(para[i].getParameter());
			else if (name.equals("DU_HotelMD_ID")) {
				du_HotelMD_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("NRVK")) {
				NRVK = para[i].getParameterAsString();
			}
			else if (name.equals("NRVD")) {
				NRVD = para[i].getParameterAsString();
			}
			else if (name.equals("DateEntreMD")) {
				DateEntreMD = para[i].getParameterAsTimestamp();
			}
			else if (name.equals("DateEntreMK")) {
				DateEntreMK = para[i].getParameterAsTimestamp();
			}
			else if (name.equals("DateSortieMD")) {
				DateSortieMD = para[i].getParameterAsTimestamp();
			}
			else if (name.equals("DateSortieMK")) {
				DateSortieMK = para[i].getParameterAsTimestamp();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= "
				+ getAD_PInstance_ID() + " AND T_Selection.T_Selection_ID = du_volline.du_volline_ID)";
		int ids [] = MVolLine.getAllIDs(MVolLine.Table_Name, whereClause, get_TrxName());
		if (ids==null || ids.length == 0)
			throw new AdempiereUserError("Merci de selectionner des lignes");
		int du_vol_id = DB.getSQLValue(get_TrxName(),
				"Select du_vol_id from du_volline where du_volline_id = "+ ids[0]);
		MVAllocation mvl = new MVAllocation(getCtx(), 0, get_TrxName());
		mvl.setDU_Vol_ID(du_vol_id);
		mvl.setDU_Hotel_ID(du_Hotel_ID);
		mvl.setTypeRoom(typeRoom);
		mvl.setAmoutVH(amoutVH);
		mvl.setisVIP(isVIP);
		mvl.setDU_HotelMD_ID(du_HotelMD_ID);
		mvl.setNRVK(NRVK);
		mvl.setNRVD(NRVD);
		mvl.setDateEntreMD(DateEntreMD);
		mvl.setDateEntreMK(DateEntreMK);
		mvl.setDateSortieMD(DateSortieMD);
		mvl.setDateSortieMK(DateSortieMK);
		mvl.saveEx();
		
		for (int id : ids) {
			MVAllocationLine valine = new MVAllocationLine(getCtx(), 0, get_TrxName());
			valine.setDU_VAllocation_ID(mvl.getDU_VAllocation_ID());
			valine.setDU_VolLine_ID(id);
			valine.saveEx();
		}
		
		return "Affectation créer = " + mvl.getValue();
	}

}
