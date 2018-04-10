package org.dusfan.idempiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MDiscountSchema;
import org.compiere.model.MDiscountSchemaBreak;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class AddUpdateRemise extends SvrProcess {

	BigDecimal rate = null;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("BreakDiscount"))
				rate = para[i].getParameterAsBigDecimal();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		MDiscountSchema sch = new MDiscountSchema(getCtx(), getRecord_ID(), get_TrxName());
		
		if (!sch.getDiscountType().equals("B"))
			throw new AdempiereUserError("Changer le type de remise pour Ruptures");
		if (rate.compareTo(Env.ZERO)==0)
			throw new AdempiereUserError("le taux est 0");
		String sql = "Delete from M_DiscountSchemaBreak where M_DiscountSchema_ID = " + sch.getM_DiscountSchema_ID()
				+ " and m_product_id is not null and m_product_id in"
				+ " (select m_product_id from m_product where M_Product_Category_ID=1000001 "
				+ " and TypeService in ('1','3'))";
		int no = DB.executeUpdateEx(sql, get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Delete Old product from remise =" + no);
		
		commitEx();
		
		try {
			int ids[] = MProduct.getAllIDs(MProduct.Table_Name,
					"M_Product.M_Product_Category_ID=1000001 " + "and M_Product.TypeService in ('1','3') ", get_TrxName());
			
			int seqNo = DB.getSQLValue(get_TrxName(), "select coalesce(max(SeqNo),0) from M_DiscountSchemaBreak where M_DiscountSchema_ID = " + sch.getM_DiscountSchema_ID());
			
			for (int id : ids)
			{
				seqNo += 10;
				MDiscountSchemaBreak sbr = new MDiscountSchemaBreak(getCtx(), 0, get_TrxName());
				sbr.setAD_Org_ID(sch.getAD_Org_ID());
				sbr.setM_DiscountSchema_ID(sch.getM_DiscountSchema_ID());
				sbr.setM_Product_ID(id);
				sbr.setBreakValue(Env.ONE);
				sbr.setBreakDiscount(rate);
				sbr.setSeqNo(seqNo);
				sbr.saveEx();
			}
		} catch (Exception e) {
			rollback();
		}
	
		
		return null;
	}

}
