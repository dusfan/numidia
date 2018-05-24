package org.dusfan.idempiere.event;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.model.X_C_BP_Relation;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MPOSAR;
import org.dusfan.idempiere.model.MPOSARLine;
import org.dusfan.idempiere.model.MVAllocationLine;
import org.dusfan.idempiere.model.MVol;
import org.dusfan.idempiere.model.MVolLine;

public class EventOrder {

	public static void setC_Activity_ID(String type, PO po, Properties ctx, String trxName) {
		if (type.equals(MOrder.Table_Name)) {
			MOrder ord = (MOrder) po;
			int c_doctype_id = ord.getC_DocTypeTarget_ID();
			if (c_doctype_id == 1000047 || c_doctype_id == 1000048)
				ord.setC_Activity_ID(1000001);
		} else if (type.equals(MOrderLine.Table_Name)) {
			MOrderLine line = (MOrderLine) po;
			MOrder order = new MOrder(ctx, line.getC_Order_ID(), trxName);
			line.setC_Activity_ID(order.getC_Activity_ID());
		} else if (type.equals(MInvoiceLine.Table_Name)) {
			MInvoiceLine line = (MInvoiceLine) po;
			MInvoice invoice = new MInvoice(ctx, line.getC_Invoice_ID(), trxName);
			line.setC_Activity_ID(invoice.getC_Activity_ID());
		} else if (type.equals(MInvoice.Table_Name)) {
			MInvoice inv = (MInvoice) po;
			int c_doctype_id = inv.getC_DocTypeTarget_ID();
			if (c_doctype_id == 1000051 || c_doctype_id == 1000052)
				inv.setC_Activity_ID(1000001);
		} else if (type.equals(MPayment.Table_Name)) {
			MPayment pay = (MPayment) po;
			int c_doctype_id = pay.getC_DocType_ID();
			if (c_doctype_id == 1000049 || c_doctype_id == 1000050)
				pay.setC_Activity_ID(1000001);
		}
	}

	// Set Package and Taxe
	public static void setPackageAndSarTax(PO po, Properties ctx, String trxName) {
		MOrderLine line = (MOrderLine) po;
		if (line.getM_Product_ID() > 0) {
			MProduct pr = new MProduct(ctx, line.getM_Product_ID(), trxName);
			if (pr.get_ValueAsString("TypeService").equals("0")) {
				line.set_ValueNoCheck("M_Sejour_ID", pr.getM_Product_ID());
				if (pr.get_ValueAsString("TypeRoom") != null && pr.get_ValueAsString("TypeRoom").length() > 0) {
					line.set_ValueNoCheck("TypeRoom", pr.get_ValueAsString("TypeRoom"));
				}
				MBPartner bp = new MBPartner(ctx, line.getC_BPartner_ID(), trxName);
				MOrder ord = new MOrder(ctx, line.getC_Order_ID(), trxName);
//				if (bp.get_ValueAsString("isTaxSAR").equals("Y") && ord.getC_Activity_ID() == 1000001) {
//					int no = DB.getSQLValue(trxName, "Select c_charge_id from c_orderline where c_order_id =" + 1000000
//							+ " and c_orderline_id = " + ord.getC_Order_ID());
//					if (no > 0)
//						return;
//					else {
//						MOrderLine chline = new MOrderLine(ord);
//						chline.setC_Charge_ID(1000000);
//						chline.setQty(Env.ONE);
//						chline.saveEx();
//					}
//
//				}
			} else {
				line.set_ValueNoCheck("M_Sejour_ID", null);
				line.set_ValueNoCheck("TypeRoom", null);
			}
		}
	}

	// Set remise for code clients
	public static void setRemiseMargeCodeClient(PO po, Properties ctx, String trxName) {
		MOrderLine line = (MOrderLine) po;
		if (line.getM_Product_ID() > 0) {
			MProduct pr = new MProduct(ctx, line.getM_Product_ID(), trxName);
			if (pr.get_ValueAsString("TypeService").equals("5")) {
				int c_bpartnerRelation_id = DB.getSQLValue(trxName,
						"select c_bpartnerrelation_id from c_order where c_order_id =" + line.getC_Order_ID());
				MBPartner bp = new MBPartner(ctx, c_bpartnerRelation_id, trxName);
				BigDecimal remiseMarge = bp.get_Value("RemiseMarge") != null ? (BigDecimal) bp.get_Value("RemiseMarge")
						: Env.ZERO;
				BigDecimal remiseMargeSP = bp.get_Value("RemiseMargeSP") != null
						? (BigDecimal) bp.get_Value("RemiseMargeSP") : Env.ZERO;
				BigDecimal margeVIP = bp.get_Value("margevip") != null
								? (BigDecimal) bp.get_Value("margevip") : Env.ZERO;
				
				if (pr.get_ValueAsString("VIP")!=null && pr.get_ValueAsString("VIP").equals("Y") ) {
					BigDecimal price = line.getPriceActual().subtract(margeVIP);
					line.setPriceActual(price);
					line.setPriceEntered(price);
					line.setLineNetAmt();
				} else {
					BigDecimal price = line.getPriceActual().subtract(remiseMarge);
					line.setPriceActual(price);
					line.setPriceEntered(price);
					line.setLineNetAmt();
				}

				if (remiseMargeSP.compareTo(Env.ZERO) > 0) {
					line.setPriceActual(remiseMargeSP);
					line.setPriceEntered(remiseMargeSP);
					line.setLineNetAmt();
				}
			}
		}
	}

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
				if (!pr.get_ValueAsString("TypeService").equals("5")) {
					line.set_ValueNoCheck("RemiseCompt", Env.ZERO);
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
			//else if (type.equals(MPayment.Table_Name)) {
//			MPayment pay = (MPayment) po;
//			if (pay.getC_DocType_ID() == 1000049) {
//				int codeclient = DB.getSQLValue(trxName, "select c_bpartner_id from c_bpartner where value='470200'");
//				pay.set_ValueNoCheck("C_BPartnerRelation_ID", codeclient);
//			} else if (pay.getC_DocType_ID() == 1000050) {
//				pay.set_ValueNoCheck("C_BPartnerRelation_ID", pay.getC_BPartner_ID());
//			}
//		}
	}

	// Mettre la date de l ordre de vente paraport a la date de depart de vol
	public static void setDateOrderedByFlight(PO po, Properties ctx, String trxName) {
		MOrder ord = (MOrder) po;
		if (ord.getC_DocTypeTarget_ID() == 1000048) {
			MVol vol = new MVol(ctx, ord.get_ValueAsInt("DU_Vol_ID"), trxName);
			ord.setDateOrdered(vol.getDepartDateTime_Direct());
		}
	}

	// when the order is prepare add entry to vol line
	public static void addFlightLine(PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder) po;
		if (order.isSOTrx() && order.get_ValueAsInt("DU_Vol_ID") > 0
				&& (order.getC_DocType_ID() == 1000047 || order.getC_DocType_ID() == 1000048 || order.getC_DocType_ID()==1000057)) {
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
		if (order.isSOTrx() && (order.getC_DocType_ID()==1000057
				|| order.getC_DocType_ID() == 1000047 || order.getC_DocType_ID() == 1000048)) {
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
	// document
	// if the document don't contain flight return error
	public static void AddAndCheckFlightBeforeComplete(PO po, Properties ctx, String trxName) {
		MOrder order = (MOrder) po;
		if (order.isSOTrx() && (order.getC_DocType_ID() == 1000047 || order.getC_DocType_ID() == 1000048 || 
				order.getC_DocType_ID() == 1000057)) {
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
			} else
				throw new AdempiereUserError("Vous ne pouvez traiter le document sans avoir renseigner le VOL");
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
		if (order.isSOTrx() && order.getC_DocTypeTarget_ID() == 1000048) {
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
}
