package org.dusfan.idempiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.adempiere.process.ImportProcess;
import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.X_C_BP_Relation;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;

public class ImportPartnerOnly extends SvrProcess implements ImportProcess {

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/** Group Visa    */
	private int m_DU_Visa_Group_ID = 0;
	// product valo
	/** Effective						*/
	private boolean			p_IsValidateOnly = false;
	private boolean			m_deleteOldImported = false;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("IsValidateOnly"))
				p_IsValidateOnly = para[i].getParameterAsBoolean();
			else if (name.equals("AD_Client_ID")) {
				m_AD_Client_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("DU_Visa_Group_ID"))
				m_DU_Visa_Group_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception {
		StringBuilder sql = null;
		int no = 0;
		String clientCheck = getWhereClause();
		String groupCheck = getWhereGroupClause();
		if (m_AD_Client_ID == 0) {
			throw new AdempiereUserError("Vous ne pouvez pas importer les données avec cette société");
		}
		
		// Delete 2 lines that without data
		sql = new StringBuilder ("DELETE I_ImportOmraBP ")
				.append("WHERE upper(FirstName) = upper('FirstName') or "
						+ "upper(AFirstName) = upper('AFirstName') ").append(clientCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE)) log.fine("Delete lines without data =" + no);
		
		// Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder("UPDATE I_ImportOmraBP ").append("SET AD_Client_ID = COALESCE (AD_Client_ID, ")
				.append(m_AD_Client_ID).append("),").append(" AD_Org_ID = COALESCE (AD_Org_ID, 0),")
				.append(" IsActive = COALESCE (IsActive, 'Y'),").append(" Created = COALESCE (Created, SysDate),")
				.append(" CreatedBy = COALESCE (CreatedBy, 0),").append(" Updated = COALESCE (Updated, SysDate),")
				.append(" UpdatedBy = COALESCE (UpdatedBy, 0),").append(" I_ErrorMsg = ' ',")
				.append(" I_IsImported = 'N' ").append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE))
			log.fine("Reset=" + no);
		
		// Code Client
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET C_BPartnerRelation_ID=(select C_BPartner_ID from C_BPartner where value = i.CustomerCode)")
				.append("WHERE I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE))
			log.fine("Set Code client=" + no);

		// Erreur le code client est obligatoire
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Le code client est obligatoire, ' ")
				.append("WHERE C_BPartnerRelation_ID IS NULL").append(" AND I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Le code client est obligatoire=" + no);
		
		// Set Code Partner
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET DU_PartnerCode_ID=(select DU_PartnerCode_ID from DU_PartnerCode where value = i.UmrahCompanyCode)")
				.append("WHERE I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE))
			log.fine("Set Code Partner=" + no);

		// Set country
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET C_Country_ID=(select C_Country_ID from c_country where phonecode = i.birthcountry)")
				.append("WHERE I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE))
			log.fine("Set Country=" + no);
		//
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Le paye est obligatoire, ' ")
				.append("WHERE C_Country_ID IS NULL").append(" AND I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Merci de mettre le pays=" + no);
		
		// set group name exist
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET DU_Visa_Group_ID=(select DU_Visa_Group_ID from DU_Visa_Group where value = i.GroupName) ")
				.append(" WHERE I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE))
			log.fine("Set Group name=" + no);
		// chek if group name exist
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Le group visa n existe pas, ' ")
				.append("WHERE DU_Visa_Group_ID IS NULL").append(" AND I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Le group visa n existe pas=" + no);

		// Passeport No is mandatory error
		sql = new StringBuilder("UPDATE I_ImportOmraBP ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Le No de passeport est obligatoire, ' ")
				.append("WHERE ppno IS NULL ").append(" AND I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("ppno is mandatory=" + no);
		
		// Delete duplicate record with same ppno and group Name
		sql = new StringBuilder("DELETE From I_ImportOmraBP o ")
				.append(" where exists ( select 'x' from i_importomrabp i where i.ppno = o.ppno and ")
				.append(" o.du_visa_group_id = i.du_visa_group_id and i.i_importomrabp_id < o.i_importomrabp_id) ")
				.append(" AND I_IsImported<>'Y'")
				.append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
		log.config("Enregistrement en double supprimer =" + no);
		
		// If any records of the same groupe have error then call of the import process
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=il y a un enregistrment de ce groupe en erreur, ' ")
				.append(" Where DU_Visa_Group_ID in (select DU_Visa_Group_ID "
				 + " from I_ImportOmraBP where I_IsImported='E' AND DU_Visa_Group_ID = " + m_DU_Visa_Group_ID + ")")
				.append(" AND I_IsImported<>'Y'")
				.append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
		log.config("il y a un enregistrment de ce groupe en erreur =" + no);
		
			
		commitEx();
		if (p_IsValidateOnly) {
			return "Validated";
		}
		
		int noInsert = 0;
		int noUpdate = 0;

		//	Go through Records
		sql = new StringBuilder ("SELECT * FROM I_ImportOmraBP ")
				.append("WHERE I_IsImported='N' ").append(clientCheck).append(groupCheck);
		// gody: 20070113 - Order so the same values are consecutive.
		sql.append(" ORDER BY SponserID desc");
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();

			// Remember Previous BP Value BP is only first one, others are contacts.
			// All contacts share BP location.
			// bp and bpl declarations before loop, we need them for data.
			MBPartner bp = null;
			X_C_BP_Relation bprel = null;
			MBPartnerLocation bpl = null;
			while (rs.next()) {
				
				// Remember Value - only first occurance of the value is BP
				String BPValue = rs.getString("PPNO") ;
				X_I_ImportOmraBP imp = new X_I_ImportOmraBP(getCtx(), rs, get_TrxName());
				
				StringBuilder msglog = new StringBuilder("I_ImportOmraBP_iD=") .append(imp.getI_ImportOmraBP_ID())
						.append(", C_BPartner_ID=").append(imp.getC_BPartner_ID())
						.append(", AD_User_ID=").append(imp.getCreatedBy());
				if (log.isLoggable(Level.FINE)) log.fine(msglog.toString());
				
				bp = createBPartner(BPValue, imp);
				bpl = createLocation(bp, imp);
				
				imp.setC_BPartner_ID(bp.getC_BPartner_ID());
				if (bp.get_ValueAsInt("C_BP_Mohrem_ID") > 0)
					imp.setC_BP_Mohrem_ID(bp.get_ValueAsInt("C_BP_Mohrem_ID"));
				
				imp.saveEx();
				noInsert++;
				
				
			}	//	for all I_Product
			DB.close(rs, pstmt);
		}
		catch (SQLException e)
		{
			rollback();
			noInsert = 0;
			throw new DBException(e, sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
			//	Set Error to indicator to not imported
			sql = new StringBuilder ("UPDATE I_ImportOmraBP ")
					.append("SET I_IsImported='N', Updated=SysDate ")
					.append("WHERE I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
			no = DB.executeUpdateEx(sql.toString(), get_TrxName());
			addLog (0, null, new BigDecimal (noInsert), "@C_BPartner_ID@: @Inserted@");
			addLog (0, null, new BigDecimal (noUpdate), "@C_BPartner_ID@: @Updated@");
		}
		return null;
	}
	
	
	@Override
	public String getImportTableName() {
		return X_I_ImportOmraBP.Table_Name;
	}

	@Override
	public String getWhereClause() {
		StringBuilder msgreturn = new StringBuilder(" AND AD_Client_ID=").append(m_AD_Client_ID);
		return msgreturn.toString();
	}
	
	public String getWhereGroupClause () {
		StringBuilder msgreturn = new StringBuilder(" AND DU_Visa_Group_ID=").append(m_DU_Visa_Group_ID);
		return msgreturn.toString();
	}
	
	private MBPartner createBPartner (String value, X_I_ImportOmraBP imp) {
		int c_bpartner_id = DB.getSQLValue(get_TrxName(), "Select c_bpartner_id from c_bpartner where value = '"+value+"'");
		
		// If Partner exist update info
		MBPartner bp = null;
		if (c_bpartner_id > 0 || imp.getC_BPartner_ID() > 0) 
			bp = new MBPartner(getCtx(), c_bpartner_id, get_TrxName());
		else { // Create new Bpartner 
			bp = new MBPartner(getCtx(), 0, get_TrxName());
			bp.setValue(imp.getPPNo());
		}
		
		// set m_pricelist to null in bp group in that case with omra
		MBPGroup grp = new MBPGroup(getCtx(), 1000001, get_TrxName());
		if (grp.getM_PriceList_ID() > 0) {
			grp.setM_PriceList_ID(0);
			grp.saveEx();
		}
		bp.setClientOrg(imp.getAD_Client_ID(), imp.getAD_Org_ID());
		bp.setC_BP_Group_ID(1000001); // set omra group default
		bp.set_ValueNoCheck("TypeClient", "1"); // set type client 
		bp.set_ValueNoCheck("C_BPartnerRelation_ID", imp.getC_BPartnerRelation_ID());
		bp.set_ValueNoCheck("TypeGender", imp.getTitle());
		bp.set_ValueNoCheck("Sexe", imp.getSex());
		// Set mohrem group de femme si le cas
		if (imp.getRelationWithSponser()!=null && imp.getRelationWithSponser().equalsIgnoreCase("15"))
			bp.set_ValueOfColumn("C_BP_Mohrem_ID",DB.getSQLValue(get_TrxName(), "select c_bpartner_id from c_bpartner where value = 'groupfemme'"));
		bp.set_ValueNoCheck("LinkMohrem", imp.getRelationWithSponser());
		bp.set_ValueNoCheck("C_Country_ID", imp.getC_Country_ID());
		bp.setName2(imp.getFirstName()); // prénom
		bp.setName(imp.getLastName()); // Nom
		bp.set_ValueNoCheck("Father_name", imp.getFatherName());
		bp.set_ValueNoCheck("name_ar", imp.getAFirstName());
		bp.set_ValueNoCheck("name2_ar", imp.getALastName());
		bp.set_ValueNoCheck("Father_name_ar", imp.getAFatherName());
		bp.set_ValueNoCheck("PPIssueCountry", DB.getSQLValueString(null, "Select name from c_country where phonecode = '" + imp.getPPIssueCountry() +"'"));
		bp.set_ValueNoCheck("MiddleName", imp.getMiddleName());
		bp.set_ValueNoCheck("AMiddleName", imp.getAMiddleName());
		bp.set_ValueNoCheck("Phone", imp.getCAddTel());
		bp.set_ValueNoCheck("birth_date", convertDate(imp.getBirthDate()));
		bp.set_ValueNoCheck("BirthCity", imp.getBirthCity());
		bp.set_ValueNoCheck("MaritalStatus", imp.getMaritalStatus());
		bp.set_ValueNoCheck("Education", imp.getEducation());
		bp.set_ValueNoCheck("birth_country", DB.getSQLValueString(null, "Select name from c_country where phonecode = '" + imp.getBirthCountry() +"'"));
		bp.set_ValueNoCheck("JobCant", imp.getjob());
		bp.set_ValueNoCheck("TypePasseport", imp.getPPType());
		bp.set_ValueNoCheck("DelivredBY", imp.getPPIssueCity());
		bp.set_ValueNoCheck("DateStart", convertDate(imp.getPPIssueDate()));
		bp.set_ValueNoCheck("EndDate", convertDate(imp.getPPExpDate()));
		bp.set_ValueNoCheck("mrz", imp.getPPMRZ());
		bp.set_ValueNoCheck("Phone", "--");
		
		bp.setIsCustomer(true);
		bp.setM_PriceList_ID(1000002);
		bp.setInvoiceRule(MOrder.INVOICERULE_Immediate);
		bp.setDeliveryRule(MOrder.DELIVERYRULE_Availability);
		bp.setPaymentRule(MOrder.PAYMENTRULE_OnCredit);
		bp.setC_PaymentTerm_ID(1000001); // set immediatly
		bp.setInvoice_PrintFormat_ID(1000018); // set print format invoice
		
		// set remise from code client
		MBPartner codeclient = new MBPartner(getCtx(), imp.getC_BPartnerRelation_ID(), get_TrxName());
		if (codeclient.getM_DiscountSchema_ID() > 0)
			bp.setM_DiscountSchema_ID(codeclient.getM_DiscountSchema_ID());
		
		bp.saveEx();
		
		return bp;
	}
	
	private MBPartnerLocation createLocation (MBPartner bp, X_I_ImportOmraBP imp) {
		int c_BPartner_Location_ID = DB.getSQLValue(get_TrxName(),
				"Select C_BPartner_Location_ID from C_BPartner_Location where C_BPartner_ID = ?",
				bp.getC_BPartner_ID());
		MBPartnerLocation mbploc = null;
		MLocation loc = null;
		if (c_BPartner_Location_ID > 0) { // check if exist location
			mbploc = new MBPartnerLocation(getCtx(), c_BPartner_Location_ID, get_TrxName());
			loc = new MLocation(getCtx(), mbploc.getC_Location_ID(), get_TrxName());
		} else {
			mbploc = new MBPartnerLocation(bp);
			loc = new MLocation(getCtx(), 0, get_TrxName());
			loc.set_ValueNoCheck("AD_Client_ID", imp.getAD_Client_ID());
			loc.setAD_Org_ID(imp.getAD_Org_ID());
		}

		mbploc.setName(imp.getFirstName());
		
		mbploc.setIsBillTo(true);
		
		loc.setAddress1(DB.getSQLValueString(get_TrxName(),
				"Select name from c_country where phonecode ='" + imp.getBirthCountry() + "'"));
		loc.setAddress2(imp.getBirthCity());
		loc.setPostal(imp.getCAddZip());
		loc.setCity(imp.getBirthCity());
		loc.setC_Country_ID(DB.getSQLValue(get_TrxName(),
				"Select c_country_id from c_country where phonecode ='" + imp.getCAddCountry() + "'"));
		loc.saveEx();
		mbploc.setC_Location_ID(loc.getC_Location_ID());
		mbploc.saveEx();
		return mbploc;

	}
	
	
	private Timestamp convertDate (String s_date) {
		if (s_date == null)
			return null;
		Timestamp dateStamp = null;
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = df.parse(s_date);
			dateStamp = new Timestamp(date.getTime());
			return dateStamp;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
