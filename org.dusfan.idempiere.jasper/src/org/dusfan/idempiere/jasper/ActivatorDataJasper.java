package org.dusfan.idempiere.jasper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Ini;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ActivatorDataJasper implements BundleActivator {

	protected final static CLogger log = CLogger
			.getCLogger(ActivatorDataJasper.class.getName());
	private BundleContext context;
	private File jasperHome;
	private File jasperHomeImg;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;

		// Set directory for jasper in idempeire home *reports*
		String jasperHomePath = Ini.getAdempiereHome() + "/reports";
		String jasperImgPath = Ini.getAdempiereHome() + "/reports/img";
		jasperHome = new File(jasperHomePath);
		jasperHomeImg = new File(jasperImgPath);
		if (!jasperHome.exists())
			jasperHome.mkdir();
		if (!jasperHomeImg.exists())
			jasperHomeImg.mkdir();

		// first copy img
		copyRessource(jasperImgPath, "/META-INF/data/img", false);
		// copy jasper
		copyRessource(jasperHomePath, "/META-INF/data/jasper", true);

		if (log.isLoggable(Level.INFO))
			log.info(getName() + " " + getVersion() + " ready.");
	}

	public String getName() {
		return context.getBundle().getSymbolicName();
	}

	public String getVersion() {
		return (String) context.getBundle().getHeaders().get("Bundle-Version");
	}

	public String getDescription() {
		return getName();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

	private void copyRessource (String homePath, String entrypath, boolean sort) {
		// Get all entrys  locate at bundles in path entrypath(img, jasper,..)
				Enumeration<String> e = context.getBundle().getEntryPaths(entrypath);
				// Iterate list enum  for add..
				if (sort) {
					// Sort entrys enum for understandable and organize info log
					if (e.hasMoreElements()) {
						List<String> list = Collections.list(e);
						Collections.sort(list);
						e = Collections.enumeration(list);
					}
				}
				while (e.hasMoreElements()) {
					// Get element
					String nextF = (String) e.nextElement();
					if (!nextF.contains("."))
						continue;
					// Get extention
					String extention = nextF.substring(nextF.indexOf("."),nextF.length());
					// Get element name without extention
					String name = nextF.substring(nextF.lastIndexOf("/") + 1, nextF.indexOf("."));
					FileOutputStream zipstream = null;
					File ciblefile = null;
					try {
						// copy the resource to a temporary file to process it
						InputStream stream = context.getBundle().getEntry(nextF).openStream();
						ciblefile = File.createTempFile(name, extention);
						zipstream = new FileOutputStream(ciblefile);
						byte[] buffer = new byte[4 * 1024];
						int read;
						// write file 
						while ((read = stream.read(buffer)) != -1) {
							zipstream.write(buffer, 0, read);
						}
						//Copy file to target directory in home idempiere 
						FileUtils.copyFile(ciblefile, new File(homePath
								+ "/" + name + extention));
						// Log info files successfully add
						log.log(Level.INFO, "Files "+ nextF.substring(nextF.lastIndexOf("/") + 1)
										+ " successfully add...");
					} catch (Throwable ex) {
						log.log(Level.SEVERE,"Copy Faild from : " + ciblefile.getPath() + " To :"
										+ homePath + "/" + name + extention, ex);
					} finally {
						if (zipstream != null) {
							try {
								zipstream.close();
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					}
				}
	}
	
}
