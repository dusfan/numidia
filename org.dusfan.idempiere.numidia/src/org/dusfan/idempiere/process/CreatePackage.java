package org.dusfan.idempiere.process;

import org.compiere.model.MProduct;
import org.compiere.model.MProductBOM;
import org.compiere.model.MProductPO;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MCreatePackage;

public class CreatePackage extends SvrProcess {

	private int record_id = 0;

	@Override
	protected void prepare() {
		record_id = getRecord_ID();

	}
	MCreatePackage pack = null;
	@Override
	protected String doIt() throws Exception {
		pack = new MCreatePackage(getCtx(), record_id, get_TrxName());
		int count = DB.getSQLValue(get_TrxName(), "select count(1) from m_product where value = '" + pack.getValue() +"'");
		if (count > 0) 
			throw new AdempiereUserError("Le package = "+ pack.getValue() +" existe deja");
		MProduct prd = new MProduct(getCtx(), 0, get_TrxName());
		prd.setAD_Org_ID(pack.getAD_Org_ID());
		prd.setValue(pack.getValue());
		prd.setName(pack.getName());
		prd.setM_Product_Category_ID(1000001);
		prd.setC_TaxCategory_ID(1000000);
		prd.set_ValueNoCheck("TypeRoom", pack.getTypeRoom());
		prd.setDescription(pack.getDescription());
		prd.setProductType("S");
		prd.set_ValueNoCheck("TypeService", "0");
		prd.setC_UOM_ID(100);
		prd.setIsSold(true);
		prd.setIsPurchased(true);
		prd.setIsBOM(true);
		prd.saveEx();
		MProductPO prpo = new MProductPO(getCtx(), 0, get_TrxName());
		prpo.setAD_Org_ID(pack.getAD_Org_ID());
		prpo.setM_Product_ID(prd.getM_Product_ID());
		prpo.setC_BPartner_ID(1000180);
		prpo.setC_Currency_ID(235);
		prpo.setC_UOM_ID(100);
		prpo.setPriceList(Env.ZERO);
		prpo.setPricePO(Env.ZERO);
		prpo.setVendorProductNo(prd.getValue());
		prpo.saveEx();
		
		createMekhaH(prd.getM_Product_ID(), pack.getAD_Org_ID(),10);
		createMekhaMed(prd.getM_Product_ID(), pack.getAD_Org_ID(),20);
		createBillet(prd.getM_Product_ID(), pack.getAD_Org_ID(),30);
		createVisa(prd.getM_Product_ID(), pack.getAD_Org_ID(),40);
		createDouane(prd.getM_Product_ID(), pack.getAD_Org_ID(),50);
		String msg = createMarge(prd.getM_Product_ID(), pack.getAD_Org_ID(),60);
		pack.setM_Sejour_ID(prd.getM_Product_ID());
		pack.setIsActive(false);
		pack.saveEx();
		addLog(msg);
		return msg;
	}
	
	
	private void createMekhaH (int m_product_id, int ad_org_id, int line) {
		MProductBOM bom = new MProductBOM(getCtx(), 0, get_TrxName());
		bom.setAD_Org_ID(ad_org_id);
		bom.setM_Product_ID(m_product_id);
		bom.setBOMType("P");
		bom.setM_ProductBOM_ID(pack.getM_Product_ID());
		bom.setBOMQty(pack.getNbrDayMekha());
		bom.setLine(line);
		bom.saveEx();
		
	}
	
	private void createMekhaMed (int m_product_id, int ad_org_id,int line) {
		MProductBOM bom = new MProductBOM(getCtx(), 0, get_TrxName());
		bom.setAD_Org_ID(ad_org_id);
		bom.setM_Product_ID(m_product_id);
		bom.setBOMType("P");
		bom.setM_ProductBOM_ID(pack.getM_ProductMD_ID());
		bom.setBOMQty(pack.getNbrDayMedina());
		bom.setLine(line);
		bom.saveEx();
	}
	
	private void createBillet (int m_product_id, int ad_org_id,int line) {
		MProductBOM bom = new MProductBOM(getCtx(), 0, get_TrxName());
		bom.setAD_Org_ID(ad_org_id);
		bom.setM_Product_ID(m_product_id);
		bom.setBOMType("P");
		bom.setM_ProductBOM_ID(pack.getM_ProductBI_ID());
		bom.setBOMQty(Env.ONE);
		bom.setLine(line);
		bom.saveEx();
	}
	
	private void createVisa (int m_product_id, int ad_org_id, int line) {
		MProductBOM bom = new MProductBOM(getCtx(), 0, get_TrxName());
		bom.setAD_Org_ID(ad_org_id);
		bom.setM_Product_ID(m_product_id);
		bom.setBOMType("P");
		bom.setM_ProductBOM_ID(pack.getM_ProductV_ID());
		bom.setBOMQty(Env.ONE);
		bom.setLine(line);
		bom.saveEx();
	}
	
	private void createDouane (int m_product_id, int ad_org_id,int line) {
		MProductBOM bom = new MProductBOM(getCtx(), 0, get_TrxName());
		bom.setAD_Org_ID(ad_org_id);
		bom.setM_Product_ID(m_product_id);
		bom.setBOMType("P");
		bom.setM_ProductBOM_ID(pack.getM_ProductDO_ID());
		bom.setBOMQty(Env.ONE);
		bom.setLine(line);
		bom.saveEx();
	}
	
	// in this product we should create purchase and set value correctly
	private String createMarge (int m_product_id, int ad_org_id,int line) {
		MProduct prd = new MProduct(getCtx(), 0, get_TrxName());
		prd.setAD_Org_ID(pack.getAD_Org_ID());
		prd.setValue("marge"+pack.getValue());
		prd.setName("marge"+pack.getName());
		prd.setM_Product_Category_ID(1000001);
		prd.setC_TaxCategory_ID(1000000);
		prd.setProductType("S");
		prd.set_ValueNoCheck("TypeService", "5");
		prd.setC_UOM_ID(100);
		prd.setIsSold(true);
		prd.setIsPurchased(true);
		prd.saveEx();
		MProductPO prpo = new MProductPO(getCtx(), 0, get_TrxName());
		prpo.setAD_Org_ID(pack.getAD_Org_ID());
		prpo.setM_Product_ID(prd.getM_Product_ID());
		prpo.setC_BPartner_ID(1000180);
		prpo.setC_Currency_ID(235);
		prpo.setC_UOM_ID(100);
		prpo.setPriceList(pack.getPriceLimit());
		prpo.setPricePO(pack.getPriceLimit());
		prpo.setVendorProductNo(prd.getValue());
		prpo.saveEx();
		
		MProductBOM bom = new MProductBOM(getCtx(), 0, get_TrxName());
		bom.setAD_Org_ID(ad_org_id);
		bom.setM_Product_ID(m_product_id);
		bom.setBOMType("P");
		bom.setM_ProductBOM_ID(prd.getM_Product_ID());
		bom.setBOMQty(Env.ONE);
		bom.setLine(line);
		bom.saveEx();
		
		return "Package creer = "+pack.getName()+ " avec la marge = " + prd.getValue();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
