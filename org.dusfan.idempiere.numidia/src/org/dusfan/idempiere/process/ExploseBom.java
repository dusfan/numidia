package org.dusfan.idempiere.process;

import java.util.logging.Level;

import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductBOM;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class ExploseBom extends SvrProcess {

	private int c_Order_ID = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("C_Order_ID")) {
				c_Order_ID = para[i].getParameterAsInt();
			}

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		MProduct pr ;
		if (c_Order_ID > 0) {
			String sql = "Select M_product_ID from C_OrderLine where c_order_id =? and m_product_id in "
					+ "(select m_product_id from m_product where typeService='0' and isbom='Y' AND IsVerified='Y' AND IsStocked='N')";
			int m_product_id = DB.getSQLValue(get_TrxName(), sql, c_Order_ID);
			if (m_product_id > 0) {
				pr = new MProduct(getCtx(), m_product_id, get_TrxName());
				explodeBOM(c_Order_ID, m_product_id);
			} else
				throw new AdempiereUserError("il n y pas des lignes qui ont un package verifier");
		}
		else {
			throw new AdempiereUserError("Merci de vérifier la commande si "
					+ "en statut Brouillon ou qu'elle est enregisree");
		}
		return "Packege exploser => " + pr.getName();
		
	}
	
	/**
	 * 	Explode non stocked BOM.
	 * 	@return true if bom exploded
	 */
	protected boolean explodeBOM(int c_order_id, int m_product_id)
	{
		boolean retValue = false;
		String where = "AND IsActive='Y' AND EXISTS "
			+ "(SELECT * FROM M_Product p WHERE C_OrderLine.M_Product_ID=p.M_Product_ID"
			+ " AND	p.IsBOM='Y' AND p.IsVerified='Y' AND p.IsStocked='N' AND p.m_product_id ="+ m_product_id +")";
		//
		String sql = "SELECT COUNT(*) FROM C_OrderLine "
			+ "WHERE C_Order_ID=? " + where; 
		MOrder ord = new MOrder(getCtx(), c_order_id, get_TrxName());
		int count = DB.getSQLValue(get_TrxName(), sql, c_order_id);
		while (count != 0)
		{
			retValue = true;
			ord.renumberLines (1000);		//	max 999 bom items	

			//	Order Lines with non-stocked BOMs
			MOrderLine[] lines = ord.getLines (where, MOrderLine.COLUMNNAME_Line);
			for (int i = 0; i < lines.length; i++)
			{
				MOrderLine line = lines[i];
				MProduct product = MProduct.get (getCtx(), line.getM_Product_ID());
				if (log.isLoggable(Level.FINE)) log.fine(product.getName());
				//	New Lines
				int lineNo = line.getLine ();
				//find default BOM with valid dates and to this product
				/*/MPPProductBOM bom = MPPProductBOM.get(product, getAD_Org_ID(),getDatePromised(), get_TrxName());
				if(bom != null)
				{	
					MPPProductBOMLine[] bomlines = bom.getLines(getDatePromised());
					for (int j = 0; j < bomlines.length; j++)
					{
						MPPProductBOMLine bomline = bomlines[j];
						MOrderLine newLine = new MOrderLine (this);
						newLine.setLine (++lineNo);
						newLine.setM_Product_ID (bomline.getM_Product_ID ());
						newLine.setC_UOM_ID (bomline.getC_UOM_ID ());
						newLine.setQty (line.getQtyOrdered ().multiply (
							bomline.getQtyBOM()));
						if (bomline.getDescription () != null)
							newLine.setDescription (bomline.getDescription ());
						//
						newLine.setPrice ();
						newLine.saveEx(get_TrxName());
					}
				}	*/

				for (MProductBOM bom : MProductBOM.getBOMLines(product))
				{
					MOrderLine newLine = new MOrderLine(ord);
					newLine.setLine(++lineNo);
					newLine.setM_Product_ID(bom.getM_ProductBOM_ID(), true);
					newLine.setQty(line.getQtyOrdered().multiply(bom.getBOMQty()));
					if (bom.getDescription() != null)
						newLine.setDescription(bom.getDescription());
					newLine.setPrice();
					newLine.saveEx(get_TrxName());
				}
				
				//	Convert into Comment Line
				line.setM_Product_ID (0);
				line.setM_AttributeSetInstance_ID (0);
				line.setPrice (Env.ZERO);
				line.setPriceLimit (Env.ZERO);
				line.setPriceList (Env.ZERO);
				line.setLineNetAmt (Env.ZERO);
				line.setFreightAmt (Env.ZERO);
				//
				String description = product.getName ();
				if (product.getDescription () != null)
					description += " " + product.getDescription ();
				if (line.getDescription () != null)
					description += " " + line.getDescription ();
				line.setDescription (description);
				line.saveEx(get_TrxName());
			}	//	for all lines with BOM
			count = DB.getSQLValue (get_TrxName(), sql, ord.getC_Invoice_ID ());
			ord.renumberLines (10);
		}	//	while count != 0
		return retValue;
	}	//	explodeBOM

}
