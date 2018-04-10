package org.dusfan.idempiere.component;

import java.util.logging.Level;

import org.adempiere.webui.factory.IFormFactory;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.util.CLogger;
import org.dusfan.idempiere.form.DUWFileImportNumidia;


public class FormFactory implements IFormFactory {

	private static final CLogger log = CLogger.getCLogger(FormFactory.class);
	@Override
	public ADForm newFormInstance(String formName) {
		Object form = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class<?> clazz = null; 
		if (loader != null) {
    		try
    		{
        		clazz = loader.loadClass(formName);
    		}
    		catch (Exception e)
    		{
    			if (log.isLoggable(Level.INFO))
    				log.log(Level.INFO, e.getLocalizedMessage(), e);
    		}
		}
		if (clazz == null) {
			loader = this.getClass().getClassLoader();
			try
    		{
    			//	Create instance w/o parameters
        		clazz = loader.loadClass(formName);
    		}
    		catch (Exception e)
    		{
    			if (log.isLoggable(Level.INFO))
    				log.log(Level.INFO, e.getLocalizedMessage(), e);
    		}
		}
		if (clazz != null) {
			try
    		{
    			form = clazz.newInstance();
    		}
    		catch (Exception e)
    		{
    			if (log.isLoggable(Level.WARNING))
    				log.log(Level.WARNING, e.getLocalizedMessage(), e);
    		}
		}
		
		if (form != null) {
			if (form instanceof ADForm) {
				return (ADForm)form;
			} else if (form instanceof IFormController) {
				IFormController controller = (IFormController) form;
				ADForm adForm = controller.getForm();
				adForm.setICustomForm(controller);
				return adForm;
			}

		}
		// Feature 
		if (formName.contains("ImportDataTayssir")) {
			log.log(Level.INFO, "Load ImportDataTayssir terminate");
			return new DUWFileImportNumidia();
		}
		// #

		return null;
	}
}
