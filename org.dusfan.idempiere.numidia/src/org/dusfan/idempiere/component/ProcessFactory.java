package org.dusfan.idempiere.component;
import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.dusfan.idempiere.process.AddUpdateRemise;
import org.dusfan.idempiere.process.ChangeVolFromTo;
import org.dusfan.idempiere.process.ConfirmAchatSar;
import org.dusfan.idempiere.process.CreateAffectationLineVol;
import org.dusfan.idempiere.process.CreatePackage;
import org.dusfan.idempiere.process.DUInvoiceGenerate;
import org.dusfan.idempiere.process.ExploseBom;
import org.dusfan.idempiere.process.ExportSrdox;
import org.dusfan.idempiere.process.ExportSrdox2;
import org.dusfan.idempiere.process.ImportBPartnerOmra;
import org.dusfan.idempiere.process.ImportBPartnerOmraGuide;
import org.dusfan.idempiere.process.ImportPartnerOnly;
import org.dusfan.idempiere.process.ProcessOrder;
import org.dusfan.idempiere.process.SetDateRetourVisa;
import org.dusfan.idempiere.process.SetGratuiteSV;
import org.dusfan.idempiere.process.SetPrinted;
import org.dusfan.idempiere.process.SetTagPasseport;
import org.dusfan.idempiere.process.UpdateAffectationLineVol;

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
		else if (className.equals(AddUpdateRemise.class.getName())) {
			return new AddUpdateRemise ();
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
		return null;
	}
}
