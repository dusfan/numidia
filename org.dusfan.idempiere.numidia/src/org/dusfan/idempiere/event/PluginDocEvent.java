package org.dusfan.idempiere.event;

import java.util.Properties;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.base.event.LoginEventData;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.acct.FactLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MFactAcct;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;
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
		
		// MOrder
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_VOID, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_PREPARE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_VOID, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_REACTIVATE, MOrder.Table_Name);
		
		// MOrderLine
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_DELETE, MOrderLine.Table_Name);
		
		// Minvoice
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_DELETE, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_REVERSECORRECT, MInvoice.Table_Name);

		// MInvoiceLine
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInvoiceLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MInvoiceLine.Table_Name);
		
		// MPayment
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MPayment.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MPayment.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MPayment.Table_Name);
		
		// MBpartner
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MBPartner.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MBPartner.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MBPartner.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MBPartner.Table_Name);
		
		// MProduct
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MProduct.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MProduct.Table_Name);
		
		// MFactacct
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MFactAcct.Table_Name);
		
		// X_I_ImportOmraBP
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, X_I_ImportOmraBP.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, X_I_ImportOmraBP.Table_Name);
		
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
			
			// BEFORE NEW
			if (type.equals(IEventTopics.PO_BEFORE_NEW)) {
				if (po instanceof MOrderLine) {
					if (po.getAD_Org_ID()!=1000002 && po.getAD_Client_ID() == 1000002)
						EventOrder.setC_Activity_ID(MOrderLine.Table_Name, po, ctx,trxName);
					if (po.getAD_Org_ID()==1000002) {
						EventOrder.setRemiseMargeCodeClient(po,ctx,trxName);
						EventOrder.setremiseBillet(po,ctx,trxName);
						EventOrder.setPackage(po, ctx, trxName);
//						EventOrder.setRemise(po, ctx, trxName);
					}
				} else if (po instanceof MInvoiceLine) {
					if (po.getAD_Org_ID()!=1000002 && po.getAD_Client_ID() == 1000002)
						EventOrder.setC_Activity_ID(MInvoiceLine.Table_Name, po, ctx,trxName);
					if (po.getAD_Org_ID()==1000004)
						EventInvoice.checkDateInvoiced(MInvoiceLine.Table_Name, po, ctx,trxName);
					
				} else if (po instanceof MOrder) {
					
					if (po.getAD_Org_ID() == 1000002) {
						if (!EventOrder.checkHadjClient(po, ctx, trxName))
							throw new AdempiereUserError("Le type de document et "
									+ "le code client ne sont pas compatible");
						
						if (!EventOrder.checkHadjDuplicate(po, ctx, trxName))
							throw new AdempiereUserError("Il exite un Ordre de vente HADJ pour ce client");
						EventOrder.setCodeClient(po, MOrder.Table_Name,ctx,trxName);
						
						EventOrder.setDateOrderedByFlight(po, ctx, trxName);
					}
					
					if (po.getAD_Org_ID() == 1000002 || po.getAD_Org_ID() == 1000004) {
						if (!EventOrder.checkDuplicateVol(po, ctx, null,0))
							throw new AdempiereUserError("Il exite Deja un Ordre de vente pour ce client");
					}
				} else if (po instanceof MPayment) {
					if (!EventPayment.CheckPaymentRules(po, ctx, trxName)) {
						throw new AdempiereUserError("Attention "
								+ "Le mode de paiement et la banque ne sont pas compatible");
					}
					if (!EventPayment.checkCaisseCurrency(po, ctx, trxName)) {
						throw new AdempiereUserError("Attention "
								+ "La caisse et la devise du paiement ne sont pas compatible");
					}
					if (!EventPayment.checkHadjPayment(po, ctx, trxName))
						throw new AdempiereUserError("Attention "
								+ "Le code client Hadj et la caisse ne sont pas compatible");
					if (po.getAD_Org_ID() == 1000002)
						if (!EventPayment.checkindividualPayment(po, ctx, trxName))
						throw new AdempiereUserError("le code client du tiers n est pas individuel, "
								+ "vous ne pouvez pas effectuer ce paiement/Contacter Admin");
				}
				else if (po instanceof X_I_ImportOmraBP) {
					EventImportOmra.setPackageFromImport(po, ctx, trxName);
					
				}
				
			} // End BEFORE NEW
			
			// After NEw
			if (type.equals(IEventTopics.PO_AFTER_NEW)) {
				if (po instanceof MBPartner) {
					EventPartner.setAdresse((MBPartner)po, trxName, ctx);
					EventPartner.deleteSpace(po, trxName, ctx);
					EventPartner.setImage(po, trxName, ctx);
				}
				else if (po instanceof MOrder) {
					EventOrder.setRelation(po, ctx, trxName); // a voir avec le tourisme
					if (po.getAD_Org_ID()==1000002){
						if (EventOrder.checkClosedFlight(po,ctx,trxName))
							throw new AdempiereException("Le vol est Clôturé par l'administrateur, veuillez le contacter!");;
					}
				}
				else if (po instanceof MOrderLine) {
					if (po.getAD_Org_ID() == 1000002) {
						if (!EventOrder.checkDuplicatePackage(po, ctx, trxName))
							throw new AdempiereUserError("Il y a deux package pour le meme client");
					}
				}
				else if (po instanceof MProduct) {
					EventProduct.setProductVendor(po, ctx, trxName);
				}
				else if (po instanceof FactLine) {
					if (po.getAD_Org_ID()!=1000002 && po.getAD_Client_ID() == 1000002)
						EventFact.SetActivity(po, ctx, trxName);
				}
			}
			// End After new 
			
			// before change
			if (type.equals(IEventTopics.PO_BEFORE_CHANGE)) {
				if (po instanceof MOrderLine) {
					if (po.getAD_Org_ID()!=1000002 && po.getAD_Client_ID() == 1000002)
						EventOrder.setC_Activity_ID(MOrderLine.Table_Name, po, ctx,trxName);
					if (po.getAD_Org_ID() == 1000002) {
//						EventOrder.setRemiseMargeCodeClient(po,ctx,trxName);
						EventOrder.setremiseBillet(po,ctx,trxName);
						EventOrder.setPackage(po, ctx, trxName);
//						EventOrder.setRemise(po, ctx, trxName);
					}
				} else if (po instanceof MInvoiceLine) {
					if (po.getAD_Org_ID()!=1000002 && po.getAD_Client_ID() == 1000002)
						EventOrder.setC_Activity_ID(MInvoiceLine.Table_Name, po, ctx,trxName);
					else
						;
				} else if (po instanceof MOrder) {
					if (po.getAD_Org_ID()==1000002){
						EventOrder.setCodeClient(po,MOrder.Table_Name,ctx,trxName);
						if (EventOrder.checkClosedFlight(po,ctx,trxName))
							throw new AdempiereException("Le vol est Clôturé par l'administrateur, veuillez le contacter!");;
					}
					
					if (po.getAD_Org_ID() == 1000002 || po.getAD_Org_ID() == 1000004) {
						if (!EventOrder.checkDuplicateVol(po, ctx, null,1))
							throw new AdempiereUserError("Il exite Deja un Ordre de vente pour ce client pour le même vol");
					}
					
				}
				else if (po instanceof X_I_ImportOmraBP) {
					EventImportOmra.setPackageFromImport(po, ctx, trxName);
				}

			} // before change
			
			// After change
			if (type.equals(IEventTopics.PO_AFTER_CHANGE)) {
				if (po instanceof MBPartner) {
					EventPartner.setAdresse((MBPartner)po, trxName, ctx);
					EventPartner.deleteSpace(po, trxName, ctx);
					EventPartner.setImage(po, trxName, ctx);
				}
				else if (po instanceof MProduct) {
					EventProduct.setProductVendor(po, ctx, trxName);
				}
			}
			// End After change 
			
			// BEFORE_PREPARE
			if (type.equals(IEventTopics.DOC_BEFORE_PREPARE)) {
				if (po instanceof MOrder) {
					if (po.getAD_Org_ID() == 1000002) {
						if (!EventOrder.checkExitRelation(po, ctx, trxName))
							throw new AdempiereUserError("Attention la relation tiers n'existe pas, il faut la créer");
						EventOrder.addExistTax(po, ctx, trxName);
					}
				}
			}
			// End BEFORE_PREPARE
			
			// Before Complete
			if (type.equals(IEventTopics.DOC_BEFORE_COMPLETE)) {
				if (po instanceof MInvoice) {
					if (po.getAD_Org_ID() == 1000002){
						EventOrder.setCodeClient(po, MInvoice.Table_Name,ctx,trxName);
						EventInvoice.SetDU_Vol_ID(po, ctx, trxName);
					}
				} else if (po instanceof MOrder) {
					EventOrder.AddAndCheckFlightBeforeComplete(po, ctx, trxName);
				}
			}
			// End Before Complete
			
			// After Complete
			if (type.equals(IEventTopics.DOC_AFTER_COMPLETE)) {
				if (po instanceof MOrder) {
					if (!EventOrder.addSarPrice(po, ctx, trxName))
						throw new AdempiereUserError("Erreur la caisse est epuisee");
				}
			}
			// End After Complete
			
			// After Prepare
			if (type.equals(IEventTopics.DOC_AFTER_PREPARE)) {
				if(po instanceof MOrder) {
					EventOrder.addFlightLine(po,ctx, trxName);
				}
			}
			// End After Prepare
			
			// Before Void
			if (type.equals(IEventTopics.DOC_BEFORE_VOID)) {
				if (po instanceof MOrder) {
					EventOrder.subSarPrice(po, ctx, trxName);
					if (po.getAD_Org_ID() == 1000002) {
						if (!EventOrder.checkinvoiceexist(po, ctx, trxName))
							throw new AdempiereUserError("cette ordre de vente est facturé deja,"
									+ " il faut annuler d'abord ça facture");
						if (!EventOrder.CheckCancelCause(po, ctx, trxName))
							throw new AdempiereUserError("Il faut mettre la cause d'annulation ou le montant de frais d'annulation");
					}
				}
			}
			// End Before Void
			
			// After Void
			if (type.equals(IEventTopics.DOC_AFTER_VOID)) {
				if(po instanceof MOrder)
					EventOrder.deleteFlight(po, ctx, trxName);
			}
			// End After Void
			
			// Before REVERSECORRECT
			if (type.equals(IEventTopics.DOC_BEFORE_REVERSECORRECT)) {
				if (po instanceof MInvoice) {
						if (po.getAD_Org_ID() == 1000005){
							EventInvoice.changeDocumentNo(po, ctx, trxName);
							EventInvoice.checkAllocation(po, ctx, trxName);
							EventInvoice.DeleteBooking(po, ctx, trxName);
						}
				}
			}

			// BEFOR REACTIVATE
			if (type.equals(IEventTopics.DOC_BEFORE_REACTIVATE)) {
				if (po instanceof MOrder) {
					if (po.getAD_Org_ID() == 1000002) {
						if (!EventOrder.checkinvoiceexist(po, ctx, trxName))
							throw new AdempiereUserError("cette ordre de vente est facturé deja,"
									+ " il faut annuler d'abord ça facture");
					}
				}
			}
			// BEFOR DELETE
			if (type.equals(IEventTopics.PO_BEFORE_DELETE)) {
				if (po instanceof MInvoice) {
					if (!EventOrder.checkBeforeDelete(po, ctx, trxName))
						throw new AdempiereUserError("Vous ne pouvez pas supprimer la facture,"
								+ " montant > 0");
				}
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
