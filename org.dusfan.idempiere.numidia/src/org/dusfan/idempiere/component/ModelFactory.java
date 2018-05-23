package org.dusfan.idempiere.component;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
//import org.compiere.model.MInOutLine;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MCreatePackage;
import org.dusfan.idempiere.model.MHotel;
import org.dusfan.idempiere.model.MPOSAR;
import org.dusfan.idempiere.model.MPOSARLine;
import org.dusfan.idempiere.model.MRemiseChd;
import org.dusfan.idempiere.model.MRemiseGP;
import org.dusfan.idempiere.model.MRemiseMoudj;
import org.dusfan.idempiere.model.MVAllocation;
import org.dusfan.idempiere.model.MVAllocationLine;
import org.dusfan.idempiere.model.MVisaGroup;
//import org.compiere.util.Env;
import org.dusfan.idempiere.model.MVisaGroupLine;
import org.dusfan.idempiere.model.MVol;
import org.dusfan.idempiere.model.MVolLine;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;

public class ModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		if(tableName.equals(MVisaGroup.Table_Name))
			return MVisaGroup.class;
		else if(tableName.equals(MVisaGroupLine.Table_Name))
			return MVisaGroupLine.class;
		else if (tableName.equals(X_I_ImportOmraBP.Table_Name))
			return X_I_ImportOmraBP.class;
		else if (tableName.equals(MVol.Table_Name)) {
			return MVol.class;
		}
		else if (tableName.equals(MVolLine.Table_Name)) {
			return MVolLine.class;
		}else if (tableName.equals(MRemiseChd.Table_Name)) {
			return MRemiseChd.class;
		} else if (tableName.equals(MRemiseMoudj.Table_Name)) {
			return MRemiseMoudj.class;
		}
		else if (tableName.equals(MRemiseGP.Table_Name)) {
			return MRemiseGP.class;
		}
		else if (tableName.equals(MVAllocation.Table_Name)) {
			return MVAllocation.class;
		}
		else if (tableName.equals(MVAllocationLine.Table_Name)) {
			return MVAllocationLine.class;
		}
		else if (tableName.equals(MHotel.Table_Name)) {
			return MHotel.class;
		}
		else if (tableName.equals(MCreatePackage.Table_Name)) {
			return MCreatePackage.class;
		}
		else if (tableName.equals(MPOSAR.Table_Name))
			return MPOSAR.class;
		else if (tableName.equals(MPOSARLine.Table_Name))
			return MPOSAR.class;
		
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		if(tableName.equals(MVisaGroup.Table_Name))
			return new MVisaGroup(Env.getCtx(), Record_ID, trxName);
		else if(tableName.equals(MVisaGroupLine.Table_Name))
			return new MVisaGroupLine(Env.getCtx(), Record_ID, trxName);
		else if (tableName.equals(X_I_ImportOmraBP.Table_Name))
			return new X_I_ImportOmraBP(Env.getCtx(), Record_ID, trxName);
		else if (tableName.equals(MVol.Table_Name)) {
			return new MVol(Env.getCtx(), Record_ID, trxName);
		}else if (tableName.equals(MVolLine.Table_Name)) {
			return new MVolLine(Env.getCtx(), Record_ID, trxName);
		}else if (tableName.equals(MRemiseChd.Table_Name)) {
			return new MRemiseChd(Env.getCtx(), Record_ID, trxName);
		}else if (tableName.equals(MRemiseMoudj.Table_Name)) {
			return new MRemiseMoudj(Env.getCtx(), Record_ID, trxName);
		} else if (tableName.equals(MRemiseGP.Table_Name)) {
			return new MRemiseGP(Env.getCtx(), Record_ID, trxName);
		} else if (tableName.equals(MVAllocation.Table_Name)) {
			return new MVAllocation(Env.getCtx(), Record_ID, trxName);
		}
		else if (tableName.equals(MVAllocationLine.Table_Name)) {
			return new MVAllocationLine(Env.getCtx(), Record_ID, trxName);
		}
		else if (tableName.equals(MHotel.Table_Name)) {
			return new MHotel(Env.getCtx(), Record_ID, trxName);
		}
		else if (tableName.equals(MCreatePackage.Table_Name)) {
			return new MCreatePackage(Env.getCtx(), Record_ID, trxName);
		}
		else if (tableName.equals(MPOSAR.Table_Name))
			return new MPOSAR(Env.getCtx(), Record_ID, trxName);
		else if (tableName.equals(MPOSARLine.Table_Name))
			return new MPOSARLine(Env.getCtx(), Record_ID, trxName);
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		if(tableName.equals(MVisaGroup.Table_Name))
			return new MVisaGroup(Env.getCtx(), rs, trxName);
		else if(tableName.equals(MVisaGroupLine.Table_Name))
			return new MVisaGroupLine(Env.getCtx(), rs, trxName);
		else if (tableName.equals(X_I_ImportOmraBP.Table_Name))
			return new X_I_ImportOmraBP(Env.getCtx(), rs, trxName);
		else if (tableName.equals(MVol.Table_Name)) {
			return new MVol(Env.getCtx(), rs, trxName);
		} else if (tableName.equals(MVolLine.Table_Name)) {
			return new MVolLine(Env.getCtx(), rs, trxName);
		} else if (tableName.equals(MRemiseChd.Table_Name)) {
			return new MRemiseChd(Env.getCtx(), rs, trxName);
		} else if (tableName.equals(MRemiseMoudj.Table_Name)) {
			return new MRemiseMoudj(Env.getCtx(), rs, trxName);
		} else if (tableName.equals(MRemiseGP.Table_Name)) {
			return new MRemiseGP(Env.getCtx(), rs, trxName);
		} else if (tableName.equals(MVAllocation.Table_Name)) {
			return new MVAllocation(Env.getCtx(), rs, trxName);
		}
		else if (tableName.equals(MVAllocationLine.Table_Name)) {
			return new MVAllocationLine(Env.getCtx(), rs, trxName);
		}
		else if (tableName.equals(MHotel.Table_Name)) {
			return new MHotel(Env.getCtx(), rs, trxName);
		}
		else if (tableName.equals(MCreatePackage.Table_Name)) {
			return new MCreatePackage(Env.getCtx(), rs, trxName);
		}
		else if (tableName.equals(MPOSAR.Table_Name))
			return new MPOSAR(Env.getCtx(), rs, trxName);
		else if (tableName.equals(MPOSARLine.Table_Name))
			return new MPOSARLine(Env.getCtx(), rs, trxName);
		return null;
	}

}
