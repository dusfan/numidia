package org.dusfan.idempiere.component;
import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.dusfan.idempiere.process.AddUpdateRemise;
import org.dusfan.idempiere.process.ChangeVisaStatus;
import org.dusfan.idempiere.process.CreateAffectationLineVol;
import org.dusfan.idempiere.process.CreatePackage;
import org.dusfan.idempiere.process.DUInvoiceGenerate;
import org.dusfan.idempiere.process.ExploseBom;
import org.dusfan.idempiere.process.ImportBPartnerOmra;
import org.dusfan.idempiere.process.ImportBPartnerOmraGuide;
import org.dusfan.idempiere.process.SetDateRetourVisa;
import org.dusfan.idempiere.process.SetGratuiteSV;
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
		if (className.equals(ChangeVisaStatus.class.getName()))
			return new ChangeVisaStatus();
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
     		
		return null;
	}
}
