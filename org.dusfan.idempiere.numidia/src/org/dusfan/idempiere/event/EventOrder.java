package org.dusfan.idempiere.event;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MCharge;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.model.X_C_BP_Relation;
import org.compiere.process.DocAction;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MPOSAR;
import org.dusfan.idempiere.model.MPOSARLine;
import org.dusfan.idempiere.model.MVAllocationLine;
import org.dusfan.idempiere.model.MVol;
import org.dusfan.idempiere.model.MVolLine;

public class EventOrder {

	// Set activity from head document but exclude OOMRA AND LOTS
	public static void setC_Activity_ID(String type, PO po, Properties ctx, String trxName) {
		if (type.equals(MOrderLine.Table_Name)) {
			MOrderLine line = (MOrderLine) po;
			MOrder order = new MOrder(ctx, line.getC_Order_ID(), trxName);
			line.setC_Activity_ID(order.getC_Activity_ID());
		} else if (type.equals(MInvoiceLine.Table_Name)) {
			MInvoiceLine line = (MInvoiceLine) po;
			MInvoice invoice = new MInvoice(ctx, line.getC_Invoice_ID(), trxName);
			line.setC_Activity_ID(invoice.getC_Activity_ID());
		}
	}

	// Set INFO Package
	public static void setPackage(PO po, Properties ctx, String trxName) {
		MOrderLine line = (MOrderLine) po;
		if (line.getM_Product_ID() > 0 ) {
			MProduct pr = new MProduct(ctx, line.getM_Product_ID(), trxName);
			if (pr.get_ValueAsString("TypeService").equals("0")) {
				line.set_ValueNoCheck("M_Sejour_ID", pr.getM_Product_ID());
				line.set_ValueNoCheck("TypeRoom", pr.get_ValueAsString("TypeRoom"));
				MOrder ord = new MOrder(ctx, line.getC_Order_ID(), trxName);
				ord.set_ValueNoCheck("TypeRoom", pr.get_ValueAsString("TypeRoom"));
				ord.set_ValueNoCheck("DU_Hotel_ID", pr.get_Value("DU_Hotel_ID"));
				ord.set_ValueNoCheck("ClassHotel", pr.get_ValueAsString("ClassHotel"));
				ord.set_ValueNoCheck("Saison_Omra", pr.get_ValueAsString("Saison_Omra"));
				ord.saveEx();
			}
		}
	}
	
	// Add line ticket for each package
	public static void addLineTicket (MOrder po, Properties ctx, String trxName) {
		int countpk = DB.getSQLValue(trxName, "Select coalesce(count(1),0) from c_orderline where c_order_id = ? "
				+ "and m_product_id in (Select m_product_id from m_product where typeservice='0')", po.getC_Order_ID());
		if (countpk > 0) {
			int count_tk = DB.getSQLValue(trxName, "Select coalesce(count(1),0) from c_orderline where c_order_id = ? "
					+ "and m_product_id in (Select m_product_id from m_product where typeservice='2')", po.getC_Order_ID());
			if (count_tk == 0)
			{
				MOrderLine l = new MOrderLine(po);
				if (po.getAD_Org_ID() == 1000002)
					l.setM_Product_ID(1000240); // set ticket line
				if (po.getAD_Org_ID() == 1000004)
					l.setM_Product_ID(1000800);
				l.setQty(Env.ONE);
				l.saveEx();
			}
		}
		
		
	}
	
	// Check Duplicate Package
	public static boolean checkDuplicatePackage (PO po, Properties ctx, String trxName) {
		MOrderLine line = (MOrderLine) po;
		MBPartner bp = new MBPartner(ctx,line.getC_BPartner_ID(),trxName);
		if (!bp.getValue().toUpperCase().equals("STANDARD")) {
			int count = DB.getSQLValue(trxName,"Select count(1) "
					+ " from c_orderline where c_order_id = ? "
					+ " and m_product_id in (select m_product_id from m_product where TypeService='0'"
					+ " and M_Product_Category_ID=1000001)",line.getC_Order_ID());
			if (count > 1)
				return false;
		}
		return true;
	}

	// Set remise for code clients by type of commission
	public static void setRemiseMargeCodeClient(PO po, Properties ctx, String trxName) {
		MOrderLine line = (MOrderLine) po;
		MOrder ord = new MOrder(ctx, line.getC_Order_ID(), trxName);
		MBPartner bp = new MBPartner(ctx, ord.get_ValueAsInt("C_BPartnerRelation_ID"), trxName);
		MProduct pr = null;
		if (line.getM_Product_ID() > 0)
		 pr = new MProduct(ctx, line.getM_Product_ID(), trxName);
		
		if (ord.getC_DocTypeTarget_ID() == 1000048 && pr!=null
				&& pr.get_ValueAsString("TypeService").equals("0") && bp.get_ValueAsString("TypeCodeClient").equals("1"))
		{
			String typemarge = pr.get_ValueAsString("TypeMarge");
			BigDecimal remiseMarge;
			BigDecimal price ;
			if (typemarge.equals("1")) {
				remiseMarge = bp.get_Value("RemiseMarge") != null ? (BigDecimal) bp.get_Value("RemiseMarge")
						: Env.ZERO;
				price = line.getPriceList().subtract(remiseMarge);
				line.setPriceActual(price);
				line.setPriceEntered(price);
				line.setLineNetAmt();
			}
			else if (typemarge.equals("2")) {
				remiseMarge = bp.get_Value("margevip") != null ? (BigDecimal) bp.get_Value("margevip")
						: Env.ZERO;
				price = line.getPriceList().subtract(remiseMarge);
				line.setPriceActual(price);
				line.setPriceEntered(price);
				line.setLineNetAmt();
			}
			else if (typemarge.equals("3")) {
				remiseMarge = bp.get_Value("margeeco") != null ? (BigDecimal) bp.get_Value("margeeco")
						: Env.ZERO;
				price = line.getPriceList().subtract(remiseMarge);
				line.setPriceActual(price);
				line.setPriceEntered(price);
				line.setLineNetAmt();
			}
			else if (typemarge.equals("4")) {
				remiseMarge = bp.get_Value("RemiseMargeSP") != null ? (BigDecimal) bp.get_Value("RemiseMargeSP")
						: Env.ZERO;
				price = line.getPriceList().subtract(remiseMarge);
				line.setPriceActual(price);
				line.setPriceEntered(price);
				line.setLineNetAmt();
			}
			else if (typemarge.equals("5")) {
				remiseMarge = bp.get_Value("margeother") != null ? (BigDecimal) bp.get_Value("margeother")
						: Env.ZERO;
				price = line.getPriceList().subtract(remiseMarge);
				line.setPriceActual(price);
				line.setPriceEntered(price);
				line.setLineNetAmt();
			}
				
		}

	}

	// if product is not ticket reset ticket line
	public static void setremiseBillet(PO po, Properties ctx, String trxName) {
		
		MOrderLine line = (MOrderLine) po;
		MOrder ord = new MOrder(ctx, line.getC_Order_ID(), trxName);
		int c_doctype_id = ord.getC_DocTypeTarget_ID();
		if (c_doctype_id == 1000047 || c_doctype_id == 1000048) {
			if (line.getM_Product_ID() > 0) {
				MProduct pr = new MProduct(ctx, line.getM_Product_ID(), trxName);
				int du_Vol_ID = DB.getSQLValue(null,
						"select du_vol_id from c_order where c_order_id =" + line.getC_Order_ID());
				if (du_Vol_ID > 0) {
					MVol vol = new MVol(ctx, du_Vol_ID, null);
					if (!pr.get_ValueAsString("TypeService").equals("2") || vol.getDU_Compa_ID() != 1000000) {
						line.set_ValueNoCheck("DU_RemiseMoudj_ID", null);
						line.set_ValueNoCheck("DU_RemiseGP_ID", null);
					}
				}
				else {
					line.set_ValueNoCheck("DU_RemiseMoudj_ID", null);
					line.set_ValueNoCheck("DU_RemiseGP_ID", null);
				}
				if (!pr.get_ValueAsString("TypeService").equals("2")) {
					line.set_ValueNoCheck("DU_RemiseChd_ID", null);
				}
			}
		}
		
	}

	// Set code client related to type document
	public static void setCodeClient(PO po, String type, Properties ctx, String trxName) {
		if (type.equals(MOrder.Table_Name)) {
			MOrder ord = (MOrder) po;
			MBPartner bp = new MBPartner(ctx, ord.getC_BPartner_ID(), trxName);
			if (ord.getC_DocTypeTarget_ID() == 1000047 
					&& bp.get_ValueAsInt("C_BPartnerRelation_ID")!=1000147) { // si le type de document comptoir mettre le code client particulier
				int c_BPartnerRelation_ID = DB.getSQLValue(null,
						"select c_bpartner_id from c_bpartner where value = '470200'");
				ord.set_ValueNoCheck("C_BPartnerRelation_ID", c_BPartnerRelation_ID);
			}
		} else if (type.equals(MInvoice.Table_Name)) {
			MInvoice inv = (MInvoice) po;
			if (inv.getAD_Org_ID() == 1000002) {
				if (inv.getC_DocType_ID() == 1000051 && inv.getC_Order_ID() > 0) {
					MOrder ord = new MOrder(ctx, inv.getC_Order_ID(), trxName);
					inv.set_ValueNoCheck("C_BPartnerRelation_ID", ord.get_Value("C_BPartnerRelation_ID"));
				}
				else
				{
					int c_bpartner_id = inv.getC_BPartner_ID();
					int codeclient = DB.getSQLValue(null, 
							"Select C_BPartnerRelation_ID from c_bpartner where c_bpartner_id ="+ c_bpartner_id);
					if (codeclient > 0) {
						if (codeclient != 1000000)
							inv.set_ValueNoCheck("C_BPartnerRelation_ID", codeclient);
						else
							inv.set_ValueNoCheck("C_BPartnerRelation_ID", c_bpartner_id); // mettre le code client
					}
				}
			}

    	}
	}

	// Mettre la date de l ordre de vente paraport a la date de depart de vol
	public static void setDateOrderedByFlight(PO po, Properties ctx, String trxName) {
		MOrder ord = (MOrder) po;
		// SET cause annulation
		if (ord.isSOTrx()) {
			ord.set_ValueNoCheck("CancelCause", null);
			ord.set_ValueNoCheck("MountOther", null);
		}
		if (ord.getC_DocTypeTarget_ID() == 1000048) {
			MVol vol = new MVol(ctx, ord.get_ValueAsInt("DU_Vol_ID"), trxName);
			ord.setDateOrdered(vol.getDepartDateTime_Direct());
		}
	}

	// when the order is prepare add entry to vol line
	public static void addFlightLine(PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder) po;
		
		// add ticket line
		addLineTicket(order, ctx, trxName);
		
		if (order.isSOTrx() && order.get_ValueAsInt("DU_Vol_ID") > 0
				&& (order.getAD_Org_ID() == 1000002 || order.getAD_Org_ID() == 1000004)) {
			String sql = "Select du_volLine_id from du_volLine where c_order_id =" + order.getC_Order_ID();
			int du_mvolline_id = DB.getSQLValue(trxName, sql);
			if (du_mvolline_id > 0) {
				// delete else affectation line
				String sqld = "Select DU_VAllocationLine_ID from DU_VAllocationLine where du_volline_id = "
						+ du_mvolline_id;
				int DU_VAllocationLine_ID = DB.getSQLValue(trxName, sqld);
				if (DU_VAllocationLine_ID > 0) {
					MVAllocationLine vline = new MVAllocationLine(ctx, DU_VAllocationLine_ID, trxName);
					vline.deleteEx(true);
				}
				MVolLine vl = new MVolLine(ctx, du_mvolline_id, trxName);
				vl.deleteEx(true);
			}
			if (order.get_ValueAsInt("DU_Vol_ID") > 0) {
				MVolLine line = new MVolLine(ctx, 0, trxName);
				line.set_ValueNoCheck("AD_Client_ID", order.getAD_Client_ID());
				line.setAD_Org_ID(Env.getAD_Org_ID(ctx));
				line.setDU_Vol_ID(order.get_ValueAsInt("DU_Vol_ID"));
				line.setC_Order_ID(order.getC_Order_ID());
				line.setC_BPartner_ID(order.getC_BPartner_ID());
				line.setIsPrinted(setIsPrinted(trxName, order.getC_Order_ID()));
				line.setIsInclude(setIsInclude(trxName, order.getC_Order_ID()));
				line.saveEx();
			}
		}
	}

	public static void deleteFlight(PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder) po;
		if (order.isSOTrx() && (order.getAD_Org_ID() == 1000002 || order.getAD_Org_ID() == 1000004)) {
			// Delete line in vol
			String sql = "Select du_volLine_id from du_volLine where c_order_id = " + order.getC_Order_ID();
			int du_mvolline_id = DB.getSQLValue(trxName, sql);
			if (du_mvolline_id > 0) {
				// delete alse affectation line
				String sqld = "Select DU_VAllocationLine_ID from DU_VAllocationLine where du_volline_id = "
						+ du_mvolline_id;
				int DU_VAllocationLine_ID = DB.getSQLValue(trxName, sqld);
				if (DU_VAllocationLine_ID > 0) {
					MVAllocationLine vline = new MVAllocationLine(ctx, DU_VAllocationLine_ID, trxName);
					vline.deleteEx(true);
				}
				MVolLine vl = new MVolLine(ctx, du_mvolline_id, trxName);
				vl.deleteEx(true);
			}

		}
	}

	// Add flight in vol line if not yet add at prepare, do it at complete
	// if the document don't contain flight return error for only omra organization
	public static void AddAndCheckFlightBeforeComplete(PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder) po;
		if (order.isSOTrx() && (order.getAD_Org_ID() == 1000002 || order.getAD_Org_ID() == 1000004)) {
			if (order.get_ValueAsInt("DU_Vol_ID") > 0) {
				String sql = "Select du_volLine_id from du_volLine where c_order_id = " + order.getC_Order_ID();
				int du_mvolline_id = DB.getSQLValue(trxName, sql);
				if (du_mvolline_id > 0) {
					;
				} else { // ajouter le vol après traitement de l'ordre de vente
					MVolLine line = new MVolLine(ctx, 0, trxName);
					line.set_ValueNoCheck("AD_Client_ID", order.getAD_Client_ID());
					line.setAD_Org_ID(Env.getAD_Org_ID(ctx));
					line.setDU_Vol_ID(order.get_ValueAsInt("DU_Vol_ID"));
					line.setC_Order_ID(order.getC_Order_ID());
					line.setC_BPartner_ID(order.getC_BPartner_ID());
					line.setIsPrinted(setIsPrinted(trxName, order.getC_Order_ID()));
					line.setIsInclude(setIsInclude(trxName, order.getC_Order_ID()));
					line.saveEx();
				}
			} else {
				if (order.getAD_Org_ID() == 1000002)
					throw new AdempiereUserError("Vous ne pouvez traiter"
							+ " le document sans avoir renseigner le VOL");
			}
		}
	}
	
	// verifier si l'ordre de vente contient un billet pour l'inclure dans le manifest
	public static boolean setIsPrinted (String trxName, int c_order_id) {
		int count = DB.getSQLValue(trxName, "Select count(1) from c_orderline where c_order_id = ? and m_product_id in "
				+ "(select m_product_id from m_product where typeservice='2')", c_order_id);
		if (count > 0)
			return true;
		return false;
	}
	
	// Verifier si l'ordre de vente contient un sejour pour l'inclure dans l'hebergement
	public static boolean setIsInclude (String trxName, int c_order_id) {
		int count = DB.getSQLValue(trxName, "Select count(1) from c_orderline where c_order_id = ? and m_sejour_id in "
				+ "(select m_product_id from m_product where typeservice='0')", c_order_id);
		if (count > 0)
			return true;
		return false;
	}
	
	
	// Set tiere facturation auto après l'annulation et la création d'un ordre de vente
	public static void setRelation (PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder) po;
		if (order.isSOTrx() && (order.getC_DocTypeTarget_ID() == 1000048 || 
				order.getC_DocTypeTarget_ID() == 1000060)) {
			int c_bpartner_id = order.getC_BPartner_ID();
			int c_bpartnerRelation_id = order.get_Value("C_BPartnerRelation_ID")!=null
					? order.get_ValueAsInt("C_BPartnerRelation_ID") : 0;
			if (c_bpartnerRelation_id > 0) {
				int rela_id = DB.getSQLValue(trxName, 
						"Select C_BP_Relation_ID "
						+ "from C_BP_Relation where C_bpartner_id ="+ c_bpartner_id
								+ " and C_BPartnerRelation_ID ="+ c_bpartnerRelation_id);
				if (rela_id > 0) {
					X_C_BP_Relation rel = new X_C_BP_Relation(ctx, rela_id, trxName);
					order.setBill_BPartner_ID(c_bpartnerRelation_id);
					order.setBill_Location_ID(rel.getC_BPartnerRelation_Location_ID());
					DB.executeUpdateEx("Update C_Order set "
							+ "bill_Bpartner_id ="+c_bpartnerRelation_id +" , bill_location_id ="+ rel.getC_BPartnerRelation_Location_ID()
							+" where C_Order_ID ="+order.getC_Order_ID(), trxName);
				}
			}
		}
	}
	
	// Check if doctype is hadj and code client is hadj
	public static boolean checkHadjClient (PO po, Properties ctx, String trxName) {
		MOrder ord = (MOrder)po;
		if (ord.getAD_Org_ID() == 1000002 && ord.isSOTrx()) {
			if (ord.getC_DocTypeTarget_ID()==1000057 && ord.get_ValueAsInt("C_BPartnerRelation_ID")!=1002081) {
				return false;
			}
			if (ord.getC_DocTypeTarget_ID()!=1000057 && ord.get_ValueAsInt("C_BPartnerRelation_ID")==1002081) {
				return false;
			}
		}
		return true;
	}
	
	
	// Check if doctype is hadj and exist two record in same year (duplicate)
	public static boolean checkHadjDuplicate(PO po, Properties ctx, String trxName) {
		MOrder ord = (MOrder) po;
		if (ord.getAD_Org_ID() == 1000002 && ord.isSOTrx() & ord.getC_DocTypeTarget_ID() == 1000057) {
			int count = DB.getSQLValue(trxName, 
					"Select count(1) from c_order where docstatus in ('DR','IP','CO','CL') and C_DocTypeTarget_ID = 1000057 "
					+ " and extract(year from created)= extract(year from current_date) and c_bpartner_id = ?", 
					ord.getC_BPartner_ID());
			if (count > 0)
				return false;
					}
		return true;
	}
	
	// Gestion de la caisse en arabie saoudi
	public static boolean addSarPrice(PO po, Properties ctx, String trxName) {
		MOrder ord = (MOrder) po;
		if (!ord.isSOTrx() && ord.getC_DocTypeTarget_ID() == 1000016) {
			MPOSAR csar = new MPOSAR(ctx, ord.get_ValueAsInt("DU_POSAR_ID"), trxName);
			BigDecimal totalSar = DB.getSQLValueBD(trxName,
					"Select sum(lineNetAmt) from C_OrderLine "
							+ " where c_order_id=? and m_product_id in (Select m_product_id from m_product where value='SAR')",
					ord.getC_Order_ID());
			BigDecimal totalused = csar.getAmtSubtract(); // get amt used from caisse sar
			BigDecimal totalCaisse = csar.getAmt(); // get total in caisse sar
			BigDecimal totalNow = totalused.add(totalSar); // get current transaction
			if (totalNow.compareTo(totalCaisse) > 0)
				return false;
			csar.setAmtSubtract(totalNow);
			csar.saveEx();
			// Add line expense to sar caisse
			MPOSARLine line = new MPOSARLine(ctx, 0, trxName);
			line.setDU_POSAR_ID(csar.getDU_POSAR_ID());
			line.setC_Order_ID(ord.getC_Order_ID());
			line.setAmt(totalSar);
			line.saveEx();
		}
		return true;
	}
	// Gestion de la caisse en arabie saoudi
	public static void subSarPrice(PO po, Properties ctx, String trxName) {
		MOrder ord = (MOrder) po;
		if (!ord.isSOTrx() && ord.getC_DocTypeTarget_ID() == 1000016) {
			MPOSAR csar = new MPOSAR(ctx, ord.get_ValueAsInt("DU_POSAR_ID"), trxName);
			BigDecimal totalSar = DB.getSQLValueBD(trxName,
					"Select sum(lineNetAmt) from C_OrderLine where c_order_id=? and "
					+ " m_product_id in (Select m_product_id from m_product where value='SAR')",
					ord.getC_Order_ID());
			BigDecimal totalused = csar.getAmtSubtract(); // get amt used from
															// caisse sar
			BigDecimal totalNow = totalused.subtract(totalSar);
			csar.setAmtSubtract(totalNow);
			csar.saveEx();
			// Delete from line
			DB.executeUpdate("Delete from DU_POSARLine where C_Order_ID =" + ord.getC_Order_ID(), trxName);
		}
	}
	


	public static boolean checkClosedFlight(PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder)po;
		int volID = order.get_ValueOldAsInt("DU_Vol_ID");
		if(volID>0){
			MVol vol = new MVol(ctx, volID, null);
			if (vol.isClosed())
				return true;
		}
		return false;
		
	}
	
	// Check duplicate order from same vol
	public static boolean checkDuplicateVol (PO po, Properties ctx, String trxName, int test) {
		MOrder order = (MOrder)po;
		if (order.isSOTrx() && !order.getDocStatus().equals("CO") && !order.getDocStatus().equals("VO")) {
			String sql = "Select count(1) from c_order where" + 
					" docstatus <> 'VO' and du_vol_id =" + order.get_Value("DU_Vol_ID") + 
					" and c_bpartner_id ="+order.getC_BPartner_ID();
			int co =  DB.getSQLValue(trxName, sql);
			if (co > test )
				return false;
		}
		
		return true;
	}
	
	public static boolean checkinvoiceexist (PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder)po;
		MBPartner code = new MBPartner(ctx, order.get_ValueAsInt("C_BPartnerRelation_ID"), trxName);
		if (order.getC_DocTypeTarget_ID() == 1000048 && 
				code.get_ValueAsString("TypeClient").equals("2")) {
			int exist = DB.getSQLValue(trxName, "Select count(1) from c_orderline where "
					+ " c_order_id = ? and c_orderline_id in (Select c_orderline_id from c_invoiceline"
					+ " where c_invoice_id in (Select c_invoice_id from c_invoice where "
					+ " docstatus in ('CO','CL')))", order.getC_Order_ID());
			if (exist > 0)
				return false;
		}
		return true;
	}
	
	// Add Cancel cause for omra
	public static  boolean CheckCancelCause (PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder)po;
		if(order.isSOTrx() && (order.getC_DocTypeTarget_ID() == 1000047
				|| order.getC_DocTypeTarget_ID() == 1000048)) {
			String cancelcause = order.get_ValueAsString("CancelCause");
			BigDecimal mount = (BigDecimal) order.get_Value("MountOther");
			if ((cancelcause == null || cancelcause.length() == 0) ||
					((cancelcause!=null || cancelcause.length()>0) 
					&& (cancelcause.equals("3") && mount.compareTo(Env.ZERO)==0)))
				return false;
			else {
				if (mount != null) {
					MInvoice inv =  new MInvoice(ctx, 0, trxName);
					inv.setAD_Org_ID(order.getAD_Org_ID());
					inv.setC_BPartner_ID(order.getBill_BPartner_ID());
					if (order.get_Value("DU_Vol_ID")!=null) {
						inv.setC_DocTypeTarget_ID(1000051);
						inv.set_ValueNoCheck("DU_Vol_ID", order.get_Value("DU_Vol_ID"));
					} else
						inv.setC_DocTypeTarget_ID(1000056);
					MBPartner bp = new MBPartner(ctx, order.getC_BPartner_ID(), trxName);
					inv.setDescription(bp.getName() + " " + bp.getName2());
					inv.saveEx();
					MInvoiceLine line = new MInvoiceLine(inv);
					line.setC_Charge_ID(1000005);
					line.setQty(Env.ONE);
					line.setPrice(mount);
					line.saveEx();
					inv.processIt(DocAction.ACTION_Complete);
				}
			}
		}
		return true;
	}
	
	public static boolean checkExitRelation (PO po, Properties ctx, String trxName) {
		MOrder ord = (MOrder) po;
		if (ord.getC_DocTypeTarget_ID() == 1000048) {
			MBPartner bp = new MBPartner(ctx, ord.get_ValueAsInt("C_BPartnerRelation_ID"), trxName);
			if (bp.get_Value("TypeCodeClient").equals("1")) {
				String whereC = "C_BPartnerRelation_ID = "+ord.get_ValueAsInt("C_BPartnerRelation_ID")+
						" AND C_BPartner_ID = "+ord.getC_BPartner_ID();
				int [] rel_id = X_C_BP_Relation.
						getAllIDs(X_C_BP_Relation.Table_Name, whereC, trxName);
				if (rel_id.length == 0)
					return false;
			}
		}
		return true;
	}
	
	public static void addExistTax (PO po, Properties ctx, String trxName) {
		MOrderLine line = (MOrderLine) po;
		MOrder od = new MOrder(ctx, line.getC_Order_ID(), trxName);
		MBPartner bp = new MBPartner(ctx,od.getC_BPartner_ID(),trxName);
//		if (od.getC_DocTypeTarget_ID() == 1000047 || od.getC_DocTypeTarget_ID() == 1000048) {//check only for direct sales
		if (od.getC_DocTypeTarget_ID() == 1000047) {
			MProduct pr = new MProduct(ctx, line.getM_Product_ID(), trxName);
			if (pr.getM_Product_Category_ID()==1000001
					&& pr.get_ValueAsString("TypeService").equals("0")) {
				int l [] = PO.getAllIDs(MOrderLine.Table_Name, 
						"C_Charge_ID =1000000 AND C_Order_ID ="+od.getC_Order_ID(), trxName);
				int exitTax = DB.getSQLValue(null, "Select count(1) from I_DU_CheckVisa "
						+ " where trim(value)='"+bp.getValue().trim()+"'");
				if (l.length == 0 && exitTax > 0) {
					MOrderLine l2 = new MOrderLine(od);
					l2.setC_Charge_ID(1000000);
					MCharge ch = new MCharge(ctx, 1000000, trxName);
					l2.setQty(Env.ONE);
					l2.setPrice(ch.getChargeAmt());
					l2.saveEx();
				}
			}
		}
	}
}
