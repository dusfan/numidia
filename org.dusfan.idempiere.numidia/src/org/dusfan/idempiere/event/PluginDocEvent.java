package org.dusfan.idempiere.event;

import java.util.Properties;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.base.event.LoginEventData;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.PO;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

public class PluginDocEvent extends AbstractEventHandler {

	private static CLogger log = CLogger.getCLogger(PluginDocEvent.class);

	private String trxName = "";
	private Properties ctx = null;
	private PO po = null;

	@Override
	protected void initialize() {
		// register EventTopics and TableNames
		registerEvent(IEventTopics.AFTER_LOGIN);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MPayment.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MPayment.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInvoiceLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MInvoiceLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MBPartner.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MBPartner.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MPayment.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MBPartner.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MBPartner.Table_Name);
		// DOC
		// Order
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_PREPARE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_VOID, MOrder.Table_Name);
		// Invoice
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MInvoice.Table_Name);
		// Payment
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MPayment.Table_Name);
		
		log.info("PluginDocEvent .. IS NOW INITIALIZED");
	}

	@Override
	protected void doHandleEvent(Event event) {
		String type = event.getTopic();
		// testing that it works at login

		if (type.equals(IEventTopics.AFTER_LOGIN)) {
			LoginEventData eventData = getEventData(event);
			log.fine(" topic=" + event.getTopic() + " AD_Client_ID=" + eventData.getAD_Client_ID() + " AD_Org_ID="
					+ eventData.getAD_Org_ID() + " AD_Role_ID=" + eventData.getAD_Role_ID() + " AD_User_ID="
					+ eventData.getAD_User_ID());
			
		} else {
			setPo(getPO(event));
			setTrxName(po.get_TrxName());
			setCtx();
			log.info(" topic=" + event.getTopic() + " po=" + po);
			// before new
			if (type.equals(IEventTopics.PO_BEFORE_NEW)) {
				if (po instanceof MOrderLine) {
					EventOrder.setC_Activity_ID(MOrderLine.Table_Name, po, ctx,trxName);
					EventOrder.setPackageAndSarTax(po,ctx,trxName);
					EventOrder.setRemiseMargeCodeClient(po,ctx,trxName);
					EventOrder.setremiseBillet(po,ctx,trxName);
				} else if (po instanceof MInvoiceLine) {
					EventOrder.setC_Activity_ID(MInvoiceLine.Table_Name, po, ctx,trxName);
				} else if (po instanceof MOrder) {
					EventOrder.setC_Activity_ID(MOrder.Table_Name, po, ctx,trxName);
					EventOrder.setCodeClient(po, MOrder.Table_Name,ctx,trxName);
					EventOrder.setDateOrderedByFlight(po, ctx, trxName);
				} else if (po instanceof MInvoice) {
					EventOrder.setC_Activity_ID(MInvoice.Table_Name, po, ctx,trxName);
					EventInvoice.setDateAcct (po);
				} else if (po instanceof MPayment) {
					if (!EventPayment.CheckPaymentRules(po, ctx, trxName)) {
						throw new AdempiereUserError("Attention "
								+ "Le mode de paiement et la banque ne sont pas compatible");
					}
					if (!EventPayment.checkCaisseCurrency(po, ctx, trxName)) {
						throw new AdempiereUserError("Attention "
								+ "La caisse et la devise du paiement ne sont pas compatible");
					}
					EventOrder.setC_Activity_ID(MPayment.Table_Name, po, ctx,trxName);
				} else if (po instanceof MBPartner ) {
//					if (!EventPartner.checkCodeClient(po, trxName, ctx))
//						throw new AdempiereUserError("Pour creer/modifier un code client ou rabatteur,"
//								+ " contacter l administrateur");
				}				
				
			} // end before new
			
			// After NEw
			if (type.equals(IEventTopics.PO_AFTER_NEW)) {
				if (po instanceof MBPartner) {
					EventPartner.setAdresse((MBPartner)po, trxName, ctx);
					EventPartner.deleteSpace(po, trxName, ctx);
					EventPartner.setImage(po, trxName, ctx);
				}
				else if (po instanceof MOrder) {
					EventOrder.setRelation(po, ctx, trxName);
				}
			}
			// End After new 
			// before change
			if (type.equals(IEventTopics.PO_BEFORE_CHANGE)) {
				if (po instanceof MOrderLine) {
					EventOrder.setC_Activity_ID(MOrderLine.Table_Name, po, ctx,trxName);
					EventOrder.setremiseBillet(po,ctx,trxName);
					EventOrder.setPackageAndSarTax(po,ctx,trxName);
				} else if (po instanceof MInvoiceLine) {
					EventOrder.setC_Activity_ID(MInvoiceLine.Table_Name, po, ctx,trxName);
				} else if (po instanceof MOrder) {
					EventOrder.setC_Activity_ID(MOrder.Table_Name, po, ctx,trxName);
					EventOrder.setCodeClient(po,MOrder.Table_Name,ctx,trxName);
				} else if (po instanceof MInvoice) {
					EventOrder.setC_Activity_ID(MInvoice.Table_Name, po, ctx,trxName);
				} else if (po instanceof MPayment) {
					EventOrder.setC_Activity_ID(MPayment.Table_Name, po, ctx,trxName);
				}
				else if (po instanceof MBPartner ) {
//					if (!EventPartner.checkCodeClient(po, trxName, ctx))
//						throw new AdempiereUserError("Pour creer/modifier un code client ou rabatteur,"
//								+ " contacter l administrateur");
				}
			} // before change
			// After change
			if (type.equals(IEventTopics.PO_AFTER_CHANGE)) {
				if (po instanceof MBPartner) {
					EventPartner.setAdresse((MBPartner)po, trxName, ctx);
					EventPartner.deleteSpace(po, trxName, ctx);
					EventPartner.setImage(po, trxName, ctx);
				}
			}
			// End After change 
			if (type.equals(IEventTopics.DOC_BEFORE_COMPLETE)) {
				if (po instanceof MInvoice) {
					EventOrder.setCodeClient(po, MInvoice.Table_Name,ctx,trxName);
					EventInvoice.SetDU_Vol_ID(po, ctx, trxName);
				} else if (po instanceof MPayment) {
					EventOrder.setCodeClient(po, MPayment.Table_Name,ctx,trxName);
				} else if (po instanceof MOrder) {
					EventOrder.AddAndCheckFlightBeforeComplete(po, ctx, trxName);
				}
			}
			if (type.equals(IEventTopics.DOC_AFTER_PREPARE)) {
				if(po instanceof MOrder)
					EventOrder.addFlightLine(po,ctx, trxName);
			}
			if (type.equals(IEventTopics.DOC_AFTER_VOID)) {
				if(po instanceof MOrder)
					EventOrder.deleteFlight(po, ctx, trxName);
			}
		}

	}
	
	
	private void setPo(PO eventPO) {
		po = eventPO;
	}

	private void setTrxName(String get_TrxName) {
		trxName = get_TrxName;
	}

	private void setCtx() {
		ctx = Env.getCtx();
	}

}
