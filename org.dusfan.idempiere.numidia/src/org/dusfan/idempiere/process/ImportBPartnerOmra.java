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
import org.compiere.model.MCharge;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.X_C_BP_Relation;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dusfan.idempiere.model.MVisaGroup;
import org.dusfan.idempiere.model.MVisaGroupLine;
import org.dusfan.idempiere.model.MVol;
import org.dusfan.idempiere.model.X_I_ImportOmraBP;

public class ImportBPartnerOmra extends SvrProcess implements ImportProcess {

	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/** Group Visa    */
	private int m_DU_Visa_Group_ID = 0;
	// product valo
	private int m_M_Product_ID = 0;
	// vol
	private int m_DU_Vol_ID = 0;
	/** Effective						*/
	private Timestamp		m_DateValue = null;
	private boolean			p_IsValidateOnly = false;
	private boolean			m_deleteOldImported = false;
	private boolean			m_IsReValidate = false;

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
			else if (name.equals("IsReValidate"))
				m_IsReValidate = "Y".equals(para[i].getParameter()); // Tester le code Client
			else if (name.equals("AD_Client_ID")) {
				m_AD_Client_ID = para[i].getParameterAsInt();
			}
			else if (name.equals("DU_Visa_Group_ID"))
				m_DU_Visa_Group_ID = para[i].getParameterAsInt();
			else if (name.equals("DateOrdered")) {
				m_DateValue = para[i].getParameterAsTimestamp();
			} else if (name.equals("DU_Vol_ID")) {
				m_DU_Vol_ID = para[i].getParameterAsInt();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = new Timestamp (System.currentTimeMillis());

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
		
		// Delete duplicate record with same ppno and group Name
		sql = new StringBuilder("DELETE From I_ImportOmraBP o ")
				.append(" where exists ( select 'x' from i_importomrabp i where i.ppno = o.ppno and ")
				.append(" o.du_visa_group_id = i.du_visa_group_id and i.i_importomrabp_id < o.i_importomrabp_id) ")
				.append(" AND I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Enregistrement en double supprimer =" + no);
		
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
		
//		// check if code client is guide
//		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
//				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Vous ne pouvez pas importer les guides ici, ' ")
//				.append("WHERE CustomerCode='470299'").append(" AND I_IsImported<>'Y'").append(clientCheck)
//				.append(groupCheck);
//		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
//		if (log.isLoggable(Level.CONFIG))
//			log.config("Vous ne pouvez pas importer les guides ici=" + no);
		
		// Code Client
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET C_BPartnerRelation_ID=(select C_BPartner_ID from C_BPartner where value = i.CustomerCode)")
				.append("WHERE I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE))
			log.fine("Set Code client=" + no);
		
		// update the lines with taxes
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append(" SET C_Charge_ID= 1000000 where exists (select 1 from I_DU_CheckVisa where I_DU_CheckVisa.value = i.ppno) ")
				.append(" AND I_IsImported<>'Y'").append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.FINE))
			log.fine("Set Taxes=" + no);
		
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

		// Erreur dans le cas d une femme et il n y a pas de Mohrem
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=C une femme le mohrem est obligatoire, ' ")
				.append(" WHERE sex = '2' AND RelationWithSponser IS NULL").append(" AND I_IsImported<>'Y'")
				.append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("C une femme le Mohrem est obligatoire =" + no);
		
		// Check if M_product_ID exist if is null
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Merci de mettre le package, ' ")
				.append(" WHERE M_Product_ID IS NULL").append(" AND I_IsImported<>'Y'")
				.append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Merci de mettre l hotel =" + no);
		
		// Check if DU_VOL_ID exist if is null
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Merci de mettre le vol, ' ")
				.append(" WHERE DU_VOL_ID IS NULL").append(" AND I_IsImported<>'Y'")
				.append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Merci de mettre le vol =" + no);
		
		// Exist Partner in this group if the case set exist to i_is_imported = 'Y'
		sql = new StringBuilder("UPDATE I_ImportOmraBP i ")
				.append(" Set I_IsImported='Y', processed = 'Y', processing= 'Y', I_ErrorMsg='Deja Importer' ")
				.append(" where i.ppno in (select bp.value from c_bpartner bp ")
				.append(" inner join DU_Visa_GroupLine vl on vl.c_bpartner_id = bp.c_bpartner_id ")
				.append(" where bp.value = i.ppno and vl.DU_Visa_Group_ID = i.DU_Visa_Group_ID) ").append(" AND I_IsImported<>'Y' ")
				.append(clientCheck).append(groupCheck);
		no = DB.executeUpdateEx(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.CONFIG))
			log.config("Nombre d'enregistrement deja importer =" + no);
		
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

		
		// Tester la présende de deux code client pour le même groupe
		StringBuilder testSql = new StringBuilder(
				"Select count(1), customercode " + " from I_ImportOmraBP where I_IsImported='N'").append(clientCheck)
						.append(" AND DU_Visa_Group_ID= " + m_DU_Visa_Group_ID).append(" Group by customercode");
		PreparedStatement pstmtS = null;
		ResultSet rsS = null;
		int countS = 0;
		String ClientC = "";
		try {
			pstmtS = DB.prepareStatement(testSql.toString(), get_TrxName());
			rsS = pstmtS.executeQuery();
			while (rsS.next()) {
				countS += 1;
				ClientC += rsS.getString(2) + ", ";
				
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			DB.close(rsS, pstmtS);
			rsS = null; pstmtS = null;
		} 
		if (!m_IsReValidate && countS > 1) {
			throw new AdempiereUserError("Il existe plus d un code client pour le meme groupe => "+
					ClientC);
		}
		// Fin du test
		
		
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
			MBPartnerLocation bpl = null;
			X_C_BP_Relation bprel = null;
			MVisaGroupLine visal = null;
			MOrder order = null;
			while (rs.next()) {
				
				// Remember Value - only first occurance of the value is BP
				String BPValue = rs.getString("PPNO") ;
				int c_BPartnerRelation_ID = rs.getInt("C_BPartnerRelation_ID");
				int dU_Visa_Group_ID = rs.getInt("DU_Visa_Group_ID");
				X_I_ImportOmraBP imp = new X_I_ImportOmraBP(getCtx(), rs, get_TrxName());
				
				StringBuilder msglog = new StringBuilder("I_ImportOmraBP_iD=") .append(imp.getI_ImportOmraBP_ID())
						.append(", C_BPartner_ID=").append(imp.getC_BPartner_ID())
						.append(", AD_User_ID=").append(imp.getCreatedBy());
				if (log.isLoggable(Level.FINE)) log.fine(msglog.toString());
				
				bp = createBPartner(BPValue, imp);
				bpl = createLocation(bp, imp);
				bprel = createBpRelation(bp, c_BPartnerRelation_ID);
				visal = createBPGroupVisaLines(dU_Visa_Group_ID, bp, c_BPartnerRelation_ID, imp);
				order = createOrderWithLIne(bp, bpl, bprel, imp, visal);
				
				imp.setC_BPartner_ID(bp.getC_BPartner_ID());
				if (bp.get_ValueAsInt("C_BP_Mohrem_ID") > 0)
					imp.setC_BP_Mohrem_ID(bp.get_ValueAsInt("C_BP_Mohrem_ID"));
				if (bprel!=null)
					imp.setC_BP_Relation_ID(bprel.getC_BP_Relation_ID());
				if(order != null)
					imp.setC_Order_ID(order.getC_Order_ID());
				
				// Complete orders
				if (order != null) {
					order.setDocAction(DocAction.STATUS_Closed);
					order.processIt(DocAction.ACTION_Complete);
					order.saveEx();
				}
				
				//imported ok
				imp.setI_IsImported(true);
				imp.setProcessed(true);
				imp.setProcessing(false);
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
			addLog (0, null, new BigDecimal (no), "@Errors@");
			addLog (0, null, new BigDecimal (noInsert), "@C_Order_ID@: @Inserted@");
			addLog (0, null, new BigDecimal (noUpdate), "@C_Order_ID@: @Updated@");
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
		StringBuilder msgreturn = new StringBuilder(" AND DU_Visa_Group_ID=").append(m_DU_Visa_Group_ID).
				append(" AND DU_Vol_ID = ").append(m_DU_Vol_ID);
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
	
	private X_C_BP_Relation createBpRelation (MBPartner bp , int c_BPartnerRelation_ID) {
		
		// Dans le cas d'un client rabatteur qui veut des factures individuels
		MBPartner clientCode = new MBPartner(getCtx(), c_BPartnerRelation_ID, get_TrxName());
		int comptoir_id = DB.getSQLValue(null, "Select c_bpartner_id from c_bpartner where value='470200'");
		if (bp.get_ValueAsInt("C_BPartnerRelation_ID")==comptoir_id)
			return null; // Check if comptoir don't create relation, alreday existe.
		if (clientCode.get_ValueAsString("TypeCodeClient")==null || 
				clientCode.get_ValueAsString("TypeCodeClient").length()==0 || 
				clientCode.get_ValueAsString("TypeCodeClient").equals("2"))
			return null;
		else if (clientCode.get_ValueAsString("TypeCodeClient").equals("1")) {
			int c_BP_Relation_ID = DB.getSQLValue(get_TrxName(),
					"Select c_BP_Relation_ID from c_BP_Relation where C_Bpartner_id = ? AND C_BPartnerRelation_ID =" + c_BPartnerRelation_ID , bp.getC_BPartner_ID());
			// Check if the relation exit 
			if (c_BP_Relation_ID > 0) {
				X_C_BP_Relation bprel = new X_C_BP_Relation(getCtx(), c_BP_Relation_ID, get_TrxName());
				bprel.setIsBillTo(true);
				bprel.saveEx();
				return bprel;
			}
			else { // créer la relation tiers 
				X_C_BP_Relation bprel = new X_C_BP_Relation(getCtx(), 0, get_TrxName());
				bprel.setAD_Org_ID(bp.getAD_Org_ID());
				bprel.set_ValueNoCheck("AD_Client_ID", bp.getAD_Client_ID());
				bprel.setName(clientCode.getName() + "-" + bp.getName());
				bprel.setC_BPartner_ID(bp.getC_BPartner_ID());
				bprel.setC_BPartnerRelation_ID(c_BPartnerRelation_ID);
				bprel.setIsBillTo(true);
				int locbp_id = DB.getSQLValue(get_TrxName(), "Select C_BPartner_Location_ID from C_BPartner_Location where c_bpartner_id = ?",
						c_BPartnerRelation_ID);
				bprel.setC_BPartnerRelation_Location_ID(locbp_id);
				bprel.saveEx();
				return bprel;
			}
		}
		else
			return null;
		
	}
	
	
	// Set each bpartner to his group visa
	private MVisaGroupLine createBPGroupVisaLines (int dU_Visa_Group_ID ,
			MBPartner bp, int c_BPartnerRelation_ID, X_I_ImportOmraBP imp) {
		MVisaGroup group = new MVisaGroup(getCtx(), dU_Visa_Group_ID, get_TrxName());
		if (group.get_Value("C_BPartnerRelation_ID") == null) {
			group.set_ValueNoCheck("C_BPartnerRelation_ID", c_BPartnerRelation_ID);
			group.saveEx();
		}
		MVisaGroupLine line = new MVisaGroupLine(getCtx(), 0 , get_TrxName());
		line.set_ValueNoCheck("AD_Client_ID", bp.getAD_Client_ID());
		line.setAD_Org_ID(bp.getAD_Org_ID());
		line.setDU_Visa_Group_ID(dU_Visa_Group_ID);
		line.setC_BPartner_ID(bp.getC_BPartner_ID());
		line.set_ValueNoCheck("C_BPartnerRelation_ID", c_BPartnerRelation_ID);
		line.set_ValueNoCheck("PilgrimID", imp.getPilgrimID());
		line.set_ValueNoCheck("SponserID", imp.getSponserID());
		line.set_ValueNoCheck("DU_Visa_Group_ID", dU_Visa_Group_ID);
		if (imp.getRelationWithSponser()!=null && imp.getRelationWithSponser().equalsIgnoreCase("15"))
			line.set_ValueOfColumn("C_BP_Mohrem_ID",DB.getSQLValue(get_TrxName(), "select c_bpartner_id from c_bpartner where value = 'groupfemme'"));
		line.saveEx();
		
		if (line.get_Value("C_BP_Mohrem_ID")!=null && line.get_ValueAsInt("C_BP_Mohrem_ID") > 0) {
			bp.set_ValueNoCheck("C_BP_Mohrem_ID", line.get_Value("C_BP_Mohrem_ID"));
			bp.saveEx();
		}
		
		return line;
	}
	
	private MOrder createOrderWithLIne(MBPartner bp, MBPartnerLocation bpl, X_C_BP_Relation bprel, X_I_ImportOmraBP imp,
			MVisaGroupLine vline) {
		int comptoir_id = DB.getSQLValue(null, "Select c_bpartner_id from c_bpartner where value='470200'");
		if (bp.get_ValueAsInt("C_BPartnerRelation_ID")==comptoir_id)
			return null; // Check if comptoir don't create order, alreday existe.
		bp.load(get_TrxName());
		MOrder order = new MOrder(getCtx(), 0, get_TrxName());
		order.setClientOrg(imp.getAD_Client_ID(), imp.getAD_Org_ID());
		order.setC_DocTypeTarget_ID(1000048);
		MVol vl = new MVol(getCtx(), imp.getDU_Vol_ID(), get_TrxName());
		order.setDateOrdered(vl.getDepartDateTime_Direct());
		order.setDateAcct(m_DateValue);
		order.setC_BPartner_ID(bpl.getC_BPartner_ID());
		order.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
		if (imp.getDU_PartnerCode_ID() > 0)
			order.set_ValueNoCheck("DU_PartnerCode_ID", imp.getDU_PartnerCode_ID());
		
		if (bprel != null) {
			order.setBill_BPartner_ID(bprel.getC_BPartnerRelation_ID());
			order.setBill_Location_ID(bprel.getC_BPartnerRelation_Location_ID());
		}
	
		order.set_ValueNoCheck("C_BPartnerRelation_ID", imp.getC_BPartnerRelation_ID());
		order.set_ValueNoCheck("C_BP_Mohrem_ID", bp.get_Value("C_BP_Mohrem_ID"));
		order.set_ValueNoCheck("LinkMohrem", bp.get_Value("LinkMohrem"));
		order.set_ValueNoCheck("DU_Visa_Group_ID", imp.getDU_Visa_Group_ID());
		order.setSalesRep_ID(order.getCreatedBy());
		
		order.setM_PriceList_ID(bp.getM_PriceList_ID());
		order.setInvoiceRule(bp.getInvoiceRule());
		order.setDeliveryRule(bp.getDeliveryRule());
		order.setPaymentRule(bp.getPaymentRule());
		order.setC_PaymentTerm_ID(bp.getC_PaymentTerm_ID());
		order.setIsSOTrx(true);
//		order.setC_Activity_ID(1000001);
		order.set_ValueNoCheck("DU_Vol_ID", imp.getDU_Vol_ID()); // set vol
		if (imp.get_Value("DateDeposit") != null)
			order.set_ValueNoCheck("DateDeposit", imp.get_Value("DateDeposit")); // Add date retour visa
		order.saveEx();
		
		// Create lines for the package
		MOrderLine l1 = new MOrderLine(order);
		l1.setM_Product_ID(imp.getM_Product_ID());
		l1.setQty(Env.ONE);
//		l1.setC_Activity_ID(1000001);
		l1.saveEx();
		
		// create tax if exist
		if (imp.getC_Charge_ID() > 0) {
			MOrderLine l2 = new MOrderLine(order);
			l2.setC_Charge_ID(imp.getC_Charge_ID());
			MCharge ch = new MCharge(getCtx(), imp.getC_Charge_ID(), get_TrxName());
			l2.setQty(Env.ONE);
			l2.setPrice(ch.getChargeAmt());
//			l2.setC_Activity_ID(1000001);
			l2.saveEx();
		}
		
		// Add Prestation if exist 
		if (imp.getDU_Presta_ID() > 0) {
			MOrderLine l3 = new MOrderLine(order);
			l3.setM_Product_ID(imp.getDU_Presta_ID());
			l3.setQty(Env.ONE);
			l3.setC_Activity_ID(1000001);
			l3.setLineNetAmt(); // Update for price
			l3.saveEx();
		}
		
		return order;
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
