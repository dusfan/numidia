package org.dusfan.idempiere.component;
import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.dusfan.idempiere.process.ChangeVolFromTo;
import org.dusfan.idempiere.process.ConfirmAchatSar;
import org.dusfan.idempiere.process.CreateAffectationLineVol;
import org.dusfan.idempiere.process.CreatePackage;
import org.dusfan.idempiere.process.DUInvoiceGenerate;
import org.dusfan.idempiere.process.DeleteOldDataByPeriode;
import org.dusfan.idempiere.process.DeleteUnusedData;
import org.dusfan.idempiere.process.ExploseBom;
import org.dusfan.idempiere.process.ExportSrdox;
import org.dusfan.idempiere.process.ExportSrdox2;
import org.dusfan.idempiere.process.GenerateInvoiceTourisme;
import org.dusfan.idempiere.process.ImportBPartnerOmra;
import org.dusfan.idempiere.process.ImportBPartnerOmraGuide;
import org.dusfan.idempiere.process.ImportInvoiceBooking;
import org.dusfan.idempiere.process.ImportInvoicePurchase;
import org.dusfan.idempiere.process.ImportPartnerOnly;
import org.dusfan.idempiere.process.ImportTourismeBP;
import org.dusfan.idempiere.process.ProcessOrder;
import org.dusfan.idempiere.process.SetDateRetourVisa;
import org.dusfan.idempiere.process.SetGratuiteSV;
import org.dusfan.idempiere.process.SetPrinted;
import org.dusfan.idempiere.process.SetTagPasseport;
import org.dusfan.idempiere.process.UpdateAffectationLineVol;
import org.dusfan.idempiere.process.VoidOrderByGroup;

/**
 *	ProcessFactory
 *
 *  @author AKA
 */

public class ProcessFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		if (className.equals(ChangeVolFromTo.class.getName()))
			return new ChangeVolFromTo();
		else if (className.equals(ImportBPartnerOmra.class.getName()))
			return new ImportBPartnerOmra();
		else if (className.equals(DUInvoiceGenerate.class.getName()))
			return new DUInvoiceGenerate();
		else if (className.equals(ImportBPartnerOmraGuide.class.getName())) {
			return new ImportBPartnerOmraGuide();
		}
		else if (className.equals(GenerateInvoiceTourisme.class.getName())) {
			return new GenerateInvoiceTourisme ();
		}
		else if (className.equals(CreateAffectationLineVol.class.getName())) {
			return new CreateAffectationLineVol ();
		}
		else if (className.equals(UpdateAffectationLineVol.class.getName())) {
			return new UpdateAffectationLineVol ();
		}
		else if (className.equals(SetGratuiteSV.class.getName())) {
			return new SetGratuiteSV ();
		}
		else if (className.equals(SetDateRetourVisa.class.getName())) {
			return new SetDateRetourVisa ();
		}
		else if (className.equals(ExploseBom.class.getName())) {
			return new ExploseBom ();
		}
		else if (className.equals(CreatePackage.class.getName())) {
			return new CreatePackage();
		}
		else if (className.equals(SetTagPasseport.class.getName())) {
			return new SetTagPasseport();
		}
		else if (className.equals(ExportSrdox.class.getName())) {
			return new ExportSrdox();
		}
		else if (className.equals(ExportSrdox2.class.getName())) {
			return new ExportSrdox2();
		}
		else if (className.equals(ImportPartnerOnly.class.getName())){
			return new ImportPartnerOnly();
		}
		else if (className.equals(ConfirmAchatSar.class.getName()))
			return new ConfirmAchatSar();
		else if (className.equals(SetPrinted.class.getName()))
			return new SetPrinted();
		else if (className.equals(ProcessOrder.class.getName()))
			return new ProcessOrder();	
		else if (className.equals(VoidOrderByGroup.class.getName()))
			return new VoidOrderByGroup();
		else if (className.equals(DeleteOldDataByPeriode.class.getName()))
			return new DeleteOldDataByPeriode();
		else if (className.equals(ImportTourismeBP.class.getName()))
			return new ImportTourismeBP ();
		else if (className.equals(DeleteUnusedData.class.getName()))
			return new DeleteUnusedData ();
		else if (className.equals(ImportInvoiceBooking.class.getName()))
			return new ImportInvoiceBooking();
		else if (className.equals(ImportInvoicePurchase.class.getName()))
			return new ImportInvoicePurchase();
		
		return null;
	}
}
