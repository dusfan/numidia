/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.dusfan.idempiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for I_ImportOmraBP
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_I_ImportOmraBP extends PO implements I_I_ImportOmraBP, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180327L;

    /** Standard Constructor */
    public X_I_ImportOmraBP (Properties ctx, int I_ImportOmraBP_ID, String trxName)
    {
      super (ctx, I_ImportOmraBP_ID, trxName);
      /** if (I_ImportOmraBP_ID == 0)
        {
			setI_ImportOmraBP_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_ImportOmraBP (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_I_ImportOmraBP[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AFatherName.
		@param AFatherName AFatherName	  */
	public void setAFatherName (String AFatherName)
	{
		set_Value (COLUMNNAME_AFatherName, AFatherName);
	}

	/** Get AFatherName.
		@return AFatherName	  */
	public String getAFatherName () 
	{
		return (String)get_Value(COLUMNNAME_AFatherName);
	}

	/** Set AFirstName.
		@param AFirstName AFirstName	  */
	public void setAFirstName (String AFirstName)
	{
		set_Value (COLUMNNAME_AFirstName, AFirstName);
	}

	/** Get AFirstName.
		@return AFirstName	  */
	public String getAFirstName () 
	{
		return (String)get_Value(COLUMNNAME_AFirstName);
	}

	/** Set ALastName.
		@param ALastName ALastName	  */
	public void setALastName (String ALastName)
	{
		set_Value (COLUMNNAME_ALastName, ALastName);
	}

	/** Get ALastName.
		@return ALastName	  */
	public String getALastName () 
	{
		return (String)get_Value(COLUMNNAME_ALastName);
	}

	/** Set AMiddleName.
		@param AMiddleName AMiddleName	  */
	public void setAMiddleName (String AMiddleName)
	{
		set_Value (COLUMNNAME_AMiddleName, AMiddleName);
	}

	/** Get AMiddleName.
		@return AMiddleName	  */
	public String getAMiddleName () 
	{
		return (String)get_Value(COLUMNNAME_AMiddleName);
	}

	/** Set BirthCity.
		@param BirthCity BirthCity	  */
	public void setBirthCity (String BirthCity)
	{
		set_Value (COLUMNNAME_BirthCity, BirthCity);
	}

	/** Get BirthCity.
		@return BirthCity	  */
	public String getBirthCity () 
	{
		return (String)get_Value(COLUMNNAME_BirthCity);
	}

	/** Set BirthCountry.
		@param BirthCountry BirthCountry	  */
	public void setBirthCountry (String BirthCountry)
	{
		set_Value (COLUMNNAME_BirthCountry, BirthCountry);
	}

	/** Get BirthCountry.
		@return BirthCountry	  */
	public String getBirthCountry () 
	{
		return (String)get_Value(COLUMNNAME_BirthCountry);
	}

	/** Set BirthDate.
		@param BirthDate BirthDate	  */
	public void setBirthDate (String BirthDate)
	{
		set_Value (COLUMNNAME_BirthDate, BirthDate);
	}

	/** Get BirthDate.
		@return BirthDate	  */
	public String getBirthDate () 
	{
		return (String)get_Value(COLUMNNAME_BirthDate);
	}

	/** Set CAddaCity.
		@param CAddaCity CAddaCity	  */
	public void setCAddaCity (String CAddaCity)
	{
		set_Value (COLUMNNAME_CAddaCity, CAddaCity);
	}

	/** Get CAddaCity.
		@return CAddaCity	  */
	public String getCAddaCity () 
	{
		return (String)get_Value(COLUMNNAME_CAddaCity);
	}

	/** Set CAddAStreet.
		@param CAddAStreet CAddAStreet	  */
	public void setCAddAStreet (String CAddAStreet)
	{
		set_Value (COLUMNNAME_CAddAStreet, CAddAStreet);
	}

	/** Get CAddAStreet.
		@return CAddAStreet	  */
	public String getCAddAStreet () 
	{
		return (String)get_Value(COLUMNNAME_CAddAStreet);
	}

	/** Set CAddCity.
		@param CAddCity CAddCity	  */
	public void setCAddCity (String CAddCity)
	{
		set_Value (COLUMNNAME_CAddCity, CAddCity);
	}

	/** Get CAddCity.
		@return CAddCity	  */
	public String getCAddCity () 
	{
		return (String)get_Value(COLUMNNAME_CAddCity);
	}

	/** Set CAddCountry.
		@param CAddCountry CAddCountry	  */
	public void setCAddCountry (String CAddCountry)
	{
		set_Value (COLUMNNAME_CAddCountry, CAddCountry);
	}

	/** Get CAddCountry.
		@return CAddCountry	  */
	public String getCAddCountry () 
	{
		return (String)get_Value(COLUMNNAME_CAddCountry);
	}

	/** Set CAddTel.
		@param CAddTel CAddTel	  */
	public void setCAddTel (String CAddTel)
	{
		set_Value (COLUMNNAME_CAddTel, CAddTel);
	}

	/** Get CAddTel.
		@return CAddTel	  */
	public String getCAddTel () 
	{
		return (String)get_Value(COLUMNNAME_CAddTel);
	}

	/** Set CAddZip.
		@param CAddZip CAddZip	  */
	public void setCAddZip (String CAddZip)
	{
		set_Value (COLUMNNAME_CAddZip, CAddZip);
	}

	/** Get CAddZip.
		@return CAddZip	  */
	public String getCAddZip () 
	{
		return (String)get_Value(COLUMNNAME_CAddZip);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartnerRelation() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartnerRelation_ID(), get_TrxName());	}

	/** Set Related Partner.
		@param C_BPartnerRelation_ID 
		Related Business Partner
	  */
	public void setC_BPartnerRelation_ID (int C_BPartnerRelation_ID)
	{
		if (C_BPartnerRelation_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerRelation_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerRelation_ID, Integer.valueOf(C_BPartnerRelation_ID));
	}

	/** Get Related Partner.
		@return Related Business Partner
	  */
	public int getC_BPartnerRelation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerRelation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BP_Mohrem() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BP_Mohrem_ID(), get_TrxName());	}

	/** Set Mohrem.
		@param C_BP_Mohrem_ID Mohrem	  */
	public void setC_BP_Mohrem_ID (int C_BP_Mohrem_ID)
	{
		if (C_BP_Mohrem_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Mohrem_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Mohrem_ID, Integer.valueOf(C_BP_Mohrem_ID));
	}

	/** Get Mohrem.
		@return Mohrem	  */
	public int getC_BP_Mohrem_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Mohrem_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BP_Relation getC_BP_Relation() throws RuntimeException
    {
		return (org.compiere.model.I_C_BP_Relation)MTable.get(getCtx(), org.compiere.model.I_C_BP_Relation.Table_Name)
			.getPO(getC_BP_Relation_ID(), get_TrxName());	}

	/** Set Partner Relation.
		@param C_BP_Relation_ID 
		Business Partner Relation
	  */
	public void setC_BP_Relation_ID (int C_BP_Relation_ID)
	{
		if (C_BP_Relation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BP_Relation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BP_Relation_ID, Integer.valueOf(C_BP_Relation_ID));
	}

	/** Get Partner Relation.
		@return Business Partner Relation
	  */
	public int getC_BP_Relation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Relation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException
    {
		return (org.compiere.model.I_C_Country)MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_Name)
			.getPO(getC_Country_ID(), get_TrxName());	}

	/** Set Country.
		@param C_Country_ID 
		Country 
	  */
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Country_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CNationality.
		@param CNationality CNationality	  */
	public void setCNationality (String CNationality)
	{
		set_Value (COLUMNNAME_CNationality, CNationality);
	}

	/** Get CNationality.
		@return CNationality	  */
	public String getCNationality () 
	{
		return (String)get_Value(COLUMNNAME_CNationality);
	}

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create lines from.
		@param CreateFrom 
		Process which will generate a new document lines based on an existing document
	  */
	public void setCreateFrom (String CreateFrom)
	{
		set_Value (COLUMNNAME_CreateFrom, CreateFrom);
	}

	/** Get Create lines from.
		@return Process which will generate a new document lines based on an existing document
	  */
	public String getCreateFrom () 
	{
		return (String)get_Value(COLUMNNAME_CreateFrom);
	}

	/** Set CustomerCode.
		@param CustomerCode CustomerCode	  */
	public void setCustomerCode (String CustomerCode)
	{
		set_Value (COLUMNNAME_CustomerCode, CustomerCode);
	}

	/** Get CustomerCode.
		@return CustomerCode	  */
	public String getCustomerCode () 
	{
		return (String)get_Value(COLUMNNAME_CustomerCode);
	}

	/** Set CustomerName.
		@param CustomerName CustomerName	  */
	public void setCustomerName (String CustomerName)
	{
		set_Value (COLUMNNAME_CustomerName, CustomerName);
	}

	/** Get CustomerName.
		@return CustomerName	  */
	public String getCustomerName () 
	{
		return (String)get_Value(COLUMNNAME_CustomerName);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		throw new IllegalArgumentException ("Description is virtual column");	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Dpn_Serial_No.
		@param Dpn_Serial_No Dpn_Serial_No	  */
	public void setDpn_Serial_No (BigDecimal Dpn_Serial_No)
	{
		set_Value (COLUMNNAME_Dpn_Serial_No, Dpn_Serial_No);
	}

	/** Get Dpn_Serial_No.
		@return Dpn_Serial_No	  */
	public BigDecimal getDpn_Serial_No () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Dpn_Serial_No);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set PartnerCode.
		@param DU_PartnerCode_ID PartnerCode	  */
	public void setDU_PartnerCode_ID (int DU_PartnerCode_ID)
	{
		if (DU_PartnerCode_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_PartnerCode_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_PartnerCode_ID, Integer.valueOf(DU_PartnerCode_ID));
	}

	/** Get PartnerCode.
		@return PartnerCode	  */
	public int getDU_PartnerCode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_PartnerCode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getDU_Presta() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getDU_Presta_ID(), get_TrxName());	}

	/** Set Prestation.
		@param DU_Presta_ID Prestation	  */
	public void setDU_Presta_ID (int DU_Presta_ID)
	{
		if (DU_Presta_ID < 1) 
			set_Value (COLUMNNAME_DU_Presta_ID, null);
		else 
			set_Value (COLUMNNAME_DU_Presta_ID, Integer.valueOf(DU_Presta_ID));
	}

	/** Get Prestation.
		@return Prestation	  */
	public int getDU_Presta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Presta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DU_Visa_Group getDU_Visa_Group() throws RuntimeException
    {
		return (I_DU_Visa_Group)MTable.get(getCtx(), I_DU_Visa_Group.Table_Name)
			.getPO(getDU_Visa_Group_ID(), get_TrxName());	}

	/** Set Visa.
		@param DU_Visa_Group_ID Visa	  */
	public void setDU_Visa_Group_ID (int DU_Visa_Group_ID)
	{
		if (DU_Visa_Group_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Visa_Group_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Visa_Group_ID, Integer.valueOf(DU_Visa_Group_ID));
	}

	/** Get Visa.
		@return Visa	  */
	public int getDU_Visa_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Visa_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DU_Vol getDU_Vol() throws RuntimeException
    {
		return (I_DU_Vol)MTable.get(getCtx(), I_DU_Vol.Table_Name)
			.getPO(getDU_Vol_ID(), get_TrxName());	}

	/** Set Vol.
		@param DU_Vol_ID Vol	  */
	public void setDU_Vol_ID (int DU_Vol_ID)
	{
		if (DU_Vol_ID < 1) 
			set_Value (COLUMNNAME_DU_Vol_ID, null);
		else 
			set_Value (COLUMNNAME_DU_Vol_ID, Integer.valueOf(DU_Vol_ID));
	}

	/** Get Vol.
		@return Vol	  */
	public int getDU_Vol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Vol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** بدون تعليم = 1 */
	public static final String EDUCATION_بدونتعليم = "1";
	/** تعليم ابتدائي = 2 */
	public static final String EDUCATION_تعليمابتدائي = "2";
	/** تعليم ثانوي = 3 */
	public static final String EDUCATION_تعليمثانوي = "3";
	/** تعليم عالي = 4 */
	public static final String EDUCATION_تعليمعالي = "4";
	/** اخرى = 99 */
	public static final String EDUCATION_اخرى = "99";
	/** Set Education.
		@param Education Education	  */
	public void setEducation (String Education)
	{

		set_Value (COLUMNNAME_Education, Education);
	}

	/** Get Education.
		@return Education	  */
	public String getEducation () 
	{
		return (String)get_Value(COLUMNNAME_Education);
	}

	/** Set ENUMBER.
		@param ENUMBER ENUMBER	  */
	public void setENUMBER (String ENUMBER)
	{
		set_Value (COLUMNNAME_ENUMBER, ENUMBER);
	}

	/** Get ENUMBER.
		@return ENUMBER	  */
	public String getENUMBER () 
	{
		return (String)get_Value(COLUMNNAME_ENUMBER);
	}

	/** Set FatherName.
		@param FatherName FatherName	  */
	public void setFatherName (String FatherName)
	{
		set_Value (COLUMNNAME_FatherName, FatherName);
	}

	/** Get FatherName.
		@return FatherName	  */
	public String getFatherName () 
	{
		return (String)get_Value(COLUMNNAME_FatherName);
	}

	/** Set FirstName.
		@param FirstName FirstName	  */
	public void setFirstName (String FirstName)
	{
		set_Value (COLUMNNAME_FirstName, FirstName);
	}

	/** Get FirstName.
		@return FirstName	  */
	public String getFirstName () 
	{
		return (String)get_Value(COLUMNNAME_FirstName);
	}

	/** Set GroupCode.
		@param GroupCode GroupCode	  */
	public void setGroupCode (String GroupCode)
	{
		set_Value (COLUMNNAME_GroupCode, GroupCode);
	}

	/** Get GroupCode.
		@return GroupCode	  */
	public String getGroupCode () 
	{
		return (String)get_Value(COLUMNNAME_GroupCode);
	}

	/** Set GroupName.
		@param GroupName GroupName	  */
	public void setGroupName (String GroupName)
	{
		set_Value (COLUMNNAME_GroupName, GroupName);
	}

	/** Get GroupName.
		@return GroupName	  */
	public String getGroupName () 
	{
		return (String)get_Value(COLUMNNAME_GroupName);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Import Omra Partner.
		@param I_ImportOmraBP_ID Import Omra Partner	  */
	public void setI_ImportOmraBP_ID (int I_ImportOmraBP_ID)
	{
		if (I_ImportOmraBP_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_ImportOmraBP_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_ImportOmraBP_ID, Integer.valueOf(I_ImportOmraBP_ID));
	}

	/** Get Import Omra Partner.
		@return Import Omra Partner	  */
	public int getI_ImportOmraBP_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_ImportOmraBP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set job.
		@param job job	  */
	public void setjob (String job)
	{
		set_Value (COLUMNNAME_job, job);
	}

	/** Get job.
		@return job	  */
	public String getjob () 
	{
		return (String)get_Value(COLUMNNAME_job);
	}

	/** Set LastName.
		@param LastName LastName	  */
	public void setLastName (String LastName)
	{
		set_Value (COLUMNNAME_LastName, LastName);
	}

	/** Get LastName.
		@return LastName	  */
	public String getLastName () 
	{
		return (String)get_Value(COLUMNNAME_LastName);
	}

	/** اخرى = 99 */
	public static final String MARITALSTATUS_اخرى = "99";
	/** (مطلق (ة = 3 */
	public static final String MARITALSTATUS_مطلقة = "3";
	/** (ارمل (ة = 4 */
	public static final String MARITALSTATUS_ارملة = "4";
	/** (متزوج (ة = 2 */
	public static final String MARITALSTATUS_متزوجة = "2";
	/** (اعزب (ة = 1 */
	public static final String MARITALSTATUS_اعزبة = "1";
	/** Set MaritalStatus.
		@param MaritalStatus MaritalStatus	  */
	public void setMaritalStatus (String MaritalStatus)
	{

		set_Value (COLUMNNAME_MaritalStatus, MaritalStatus);
	}

	/** Get MaritalStatus.
		@return MaritalStatus	  */
	public String getMaritalStatus () 
	{
		return (String)get_Value(COLUMNNAME_MaritalStatus);
	}

	/** Set MiddleName.
		@param MiddleName MiddleName	  */
	public void setMiddleName (String MiddleName)
	{
		set_Value (COLUMNNAME_MiddleName, MiddleName);
	}

	/** Get MiddleName.
		@return MiddleName	  */
	public String getMiddleName () 
	{
		return (String)get_Value(COLUMNNAME_MiddleName);
	}

	/** Set MOUFA.
		@param MOUFA MOUFA	  */
	public void setMOUFA (String MOUFA)
	{
		set_Value (COLUMNNAME_MOUFA, MOUFA);
	}

	/** Get MOUFA.
		@return MOUFA	  */
	public String getMOUFA () 
	{
		return (String)get_Value(COLUMNNAME_MOUFA);
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set No_Of_Relatives.
		@param No_Of_Relatives No_Of_Relatives	  */
	public void setNo_Of_Relatives (BigDecimal No_Of_Relatives)
	{
		set_Value (COLUMNNAME_No_Of_Relatives, No_Of_Relatives);
	}

	/** Get No_Of_Relatives.
		@return No_Of_Relatives	  */
	public BigDecimal getNo_Of_Relatives () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_No_Of_Relatives);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Online Processing.
		@param OProcessing 
		This payment can be processed online
	  */
	public void setOProcessing (String OProcessing)
	{
		set_Value (COLUMNNAME_OProcessing, OProcessing);
	}

	/** Get Online Processing.
		@return This payment can be processed online
	  */
	public String getOProcessing () 
	{
		return (String)get_Value(COLUMNNAME_OProcessing);
	}

	/** Set PilgrimID.
		@param PilgrimID PilgrimID	  */
	public void setPilgrimID (String PilgrimID)
	{
		set_Value (COLUMNNAME_PilgrimID, PilgrimID);
	}

	/** Get PilgrimID.
		@return PilgrimID	  */
	public String getPilgrimID () 
	{
		return (String)get_Value(COLUMNNAME_PilgrimID);
	}

	/** Set PPExpDate.
		@param PPExpDate PPExpDate	  */
	public void setPPExpDate (String PPExpDate)
	{
		set_Value (COLUMNNAME_PPExpDate, PPExpDate);
	}

	/** Get PPExpDate.
		@return PPExpDate	  */
	public String getPPExpDate () 
	{
		return (String)get_Value(COLUMNNAME_PPExpDate);
	}

	/** Set PPIssueCity.
		@param PPIssueCity PPIssueCity	  */
	public void setPPIssueCity (String PPIssueCity)
	{
		set_Value (COLUMNNAME_PPIssueCity, PPIssueCity);
	}

	/** Get PPIssueCity.
		@return PPIssueCity	  */
	public String getPPIssueCity () 
	{
		return (String)get_Value(COLUMNNAME_PPIssueCity);
	}

	/** Set PPIssueCountry.
		@param PPIssueCountry PPIssueCountry	  */
	public void setPPIssueCountry (String PPIssueCountry)
	{
		set_Value (COLUMNNAME_PPIssueCountry, PPIssueCountry);
	}

	/** Get PPIssueCountry.
		@return PPIssueCountry	  */
	public String getPPIssueCountry () 
	{
		return (String)get_Value(COLUMNNAME_PPIssueCountry);
	}

	/** Set PPIssueDate.
		@param PPIssueDate PPIssueDate	  */
	public void setPPIssueDate (String PPIssueDate)
	{
		set_Value (COLUMNNAME_PPIssueDate, PPIssueDate);
	}

	/** Get PPIssueDate.
		@return PPIssueDate	  */
	public String getPPIssueDate () 
	{
		return (String)get_Value(COLUMNNAME_PPIssueDate);
	}

	/** Set PPMRZ.
		@param PPMRZ PPMRZ	  */
	public void setPPMRZ (String PPMRZ)
	{
		set_Value (COLUMNNAME_PPMRZ, PPMRZ);
	}

	/** Get PPMRZ.
		@return PPMRZ	  */
	public String getPPMRZ () 
	{
		return (String)get_Value(COLUMNNAME_PPMRZ);
	}

	/** Set PPNo.
		@param PPNo PPNo	  */
	public void setPPNo (String PPNo)
	{
		set_Value (COLUMNNAME_PPNo, PPNo);
	}

	/** Get PPNo.
		@return PPNo	  */
	public String getPPNo () 
	{
		return (String)get_Value(COLUMNNAME_PPNo);
	}

	/** Normale = 1 */
	public static final String PPTYPE_Normale = "1";
	/** Diplomatique = 2 */
	public static final String PPTYPE_Diplomatique = "2";
	/** Set PPType.
		@param PPType PPType	  */
	public void setPPType (String PPType)
	{

		set_Value (COLUMNNAME_PPType, PPType);
	}

	/** Get PPType.
		@return PPType	  */
	public String getPPType () 
	{
		return (String)get_Value(COLUMNNAME_PPType);
	}

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** الابن = 1 */
	public static final String RELATIONWITHSPONSER_الابن = "1";
	/** اﻷب = 3 */
	public static final String RELATIONWITHSPONSER_اﻷب = "3";
	/** اﻷخ = 5 */
	public static final String RELATIONWITHSPONSER_اﻷخ = "5";
	/** الزوج = 7 */
	public static final String RELATIONWITHSPONSER_الزوج = "7";
	/** الجد = 9 */
	public static final String RELATIONWITHSPONSER_الجد = "9";
	/** ابن اﻷخ = 11 */
	public static final String RELATIONWITHSPONSER_ابناﻷخ = "11";
	/** ابن اﻷخت = 12 */
	public static final String RELATIONWITHSPONSER_ابناﻷخت = "12";
	/** صهر الزوج = 14 */
	public static final String RELATIONWITHSPONSER_صهرالزوج = "14";
	/** صهر الزوجة = 13 */
	public static final String RELATIONWITHSPONSER_صهرالزوجة = "13";
	/** عصبة نساء = 15 */
	public static final String RELATIONWITHSPONSER_عصبةنساء = "15";
	/** الحفيد = 16 */
	public static final String RELATIONWITHSPONSER_الحفيد = "16";
	/** الخال = 18 */
	public static final String RELATIONWITHSPONSER_الخال = "18";
	/** العم = 20 */
	public static final String RELATIONWITHSPONSER_العم = "20";
	/** الحمو = 27 */
	public static final String RELATIONWITHSPONSER_الحمو = "27";
	/** زوج البنت = 23 */
	public static final String RELATIONWITHSPONSER_زوجالبنت = "23";
	/** زوج اﻷب = 24 */
	public static final String RELATIONWITHSPONSER_زوجاﻷب = "24";
	/** أخرى = 99 */
	public static final String RELATIONWITHSPONSER_أخرى = "99";
	/** Set RelationWithSponser.
		@param RelationWithSponser RelationWithSponser	  */
	public void setRelationWithSponser (String RelationWithSponser)
	{

		set_Value (COLUMNNAME_RelationWithSponser, RelationWithSponser);
	}

	/** Get RelationWithSponser.
		@return RelationWithSponser	  */
	public String getRelationWithSponser () 
	{
		return (String)get_Value(COLUMNNAME_RelationWithSponser);
	}

	/** Homme = 1 */
	public static final String SEX_Homme = "1";
	/** Femme = 2 */
	public static final String SEX_Femme = "2";
	/** Set Sex.
		@param Sex Sex	  */
	public void setSex (String Sex)
	{

		set_Value (COLUMNNAME_Sex, Sex);
	}

	/** Get Sex.
		@return Sex	  */
	public String getSex () 
	{
		return (String)get_Value(COLUMNNAME_Sex);
	}

	/** Set SponserID.
		@param SponserID SponserID	  */
	public void setSponserID (String SponserID)
	{
		set_Value (COLUMNNAME_SponserID, SponserID);
	}

	/** Get SponserID.
		@return SponserID	  */
	public String getSponserID () 
	{
		return (String)get_Value(COLUMNNAME_SponserID);
	}

	/** الدكتور = 4 */
	public static final String TITLE_الدكتور = "4";
	/** صاحب الفخامة = 5 */
	public static final String TITLE_صاحبالفخامة = "5";
	/** صاحب السمو = 6 */
	public static final String TITLE_صاحبالسمو = "6";
	/** اخرى = 99 */
	public static final String TITLE_اخرى = "99";
	/** الانسة = 2 */
	public static final String TITLE_الانسة = "2";
	/** السيدة = 3 */
	public static final String TITLE_السيدة = "3";
	/** السيد = 1 */
	public static final String TITLE_السيد = "1";
	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{

		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
	}

	/** Set UmrahCompanyCode.
		@param UmrahCompanyCode UmrahCompanyCode	  */
	public void setUmrahCompanyCode (String UmrahCompanyCode)
	{
		set_Value (COLUMNNAME_UmrahCompanyCode, UmrahCompanyCode);
	}

	/** Get UmrahCompanyCode.
		@return UmrahCompanyCode	  */
	public String getUmrahCompanyCode () 
	{
		return (String)get_Value(COLUMNNAME_UmrahCompanyCode);
	}

	/** Set UmrahCompanyName.
		@param UmrahCompanyName UmrahCompanyName	  */
	public void setUmrahCompanyName (String UmrahCompanyName)
	{
		set_Value (COLUMNNAME_UmrahCompanyName, UmrahCompanyName);
	}

	/** Get UmrahCompanyName.
		@return UmrahCompanyName	  */
	public String getUmrahCompanyName () 
	{
		return (String)get_Value(COLUMNNAME_UmrahCompanyName);
	}
}