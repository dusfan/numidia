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
package org.dusfan.idempiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_ImportOmraBP
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_I_ImportOmraBP 
{

    /** TableName=I_ImportOmraBP */
    public static final String Table_Name = "I_ImportOmraBP";

    /** AD_Table_ID=1000014 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AFatherName */
    public static final String COLUMNNAME_AFatherName = "AFatherName";

	/** Set AFatherName	  */
	public void setAFatherName (String AFatherName);

	/** Get AFatherName	  */
	public String getAFatherName();

    /** Column name AFirstName */
    public static final String COLUMNNAME_AFirstName = "AFirstName";

	/** Set AFirstName	  */
	public void setAFirstName (String AFirstName);

	/** Get AFirstName	  */
	public String getAFirstName();

    /** Column name ALastName */
    public static final String COLUMNNAME_ALastName = "ALastName";

	/** Set ALastName	  */
	public void setALastName (String ALastName);

	/** Get ALastName	  */
	public String getALastName();

    /** Column name AMiddleName */
    public static final String COLUMNNAME_AMiddleName = "AMiddleName";

	/** Set AMiddleName	  */
	public void setAMiddleName (String AMiddleName);

	/** Get AMiddleName	  */
	public String getAMiddleName();

    /** Column name BirthCity */
    public static final String COLUMNNAME_BirthCity = "BirthCity";

	/** Set BirthCity	  */
	public void setBirthCity (String BirthCity);

	/** Get BirthCity	  */
	public String getBirthCity();

    /** Column name BirthCountry */
    public static final String COLUMNNAME_BirthCountry = "BirthCountry";

	/** Set BirthCountry	  */
	public void setBirthCountry (String BirthCountry);

	/** Get BirthCountry	  */
	public String getBirthCountry();

    /** Column name BirthDate */
    public static final String COLUMNNAME_BirthDate = "BirthDate";

	/** Set BirthDate	  */
	public void setBirthDate (String BirthDate);

	/** Get BirthDate	  */
	public String getBirthDate();

    /** Column name CAddaCity */
    public static final String COLUMNNAME_CAddaCity = "CAddaCity";

	/** Set CAddaCity	  */
	public void setCAddaCity (String CAddaCity);

	/** Get CAddaCity	  */
	public String getCAddaCity();

    /** Column name CAddAStreet */
    public static final String COLUMNNAME_CAddAStreet = "CAddAStreet";

	/** Set CAddAStreet	  */
	public void setCAddAStreet (String CAddAStreet);

	/** Get CAddAStreet	  */
	public String getCAddAStreet();

    /** Column name CAddCity */
    public static final String COLUMNNAME_CAddCity = "CAddCity";

	/** Set CAddCity	  */
	public void setCAddCity (String CAddCity);

	/** Get CAddCity	  */
	public String getCAddCity();

    /** Column name CAddCountry */
    public static final String COLUMNNAME_CAddCountry = "CAddCountry";

	/** Set CAddCountry	  */
	public void setCAddCountry (String CAddCountry);

	/** Get CAddCountry	  */
	public String getCAddCountry();

    /** Column name CAddTel */
    public static final String COLUMNNAME_CAddTel = "CAddTel";

	/** Set CAddTel	  */
	public void setCAddTel (String CAddTel);

	/** Get CAddTel	  */
	public String getCAddTel();

    /** Column name CAddZip */
    public static final String COLUMNNAME_CAddZip = "CAddZip";

	/** Set CAddZip	  */
	public void setCAddZip (String CAddZip);

	/** Get CAddZip	  */
	public String getCAddZip();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartnerRelation_ID */
    public static final String COLUMNNAME_C_BPartnerRelation_ID = "C_BPartnerRelation_ID";

	/** Set Related Partner.
	  * Related Business Partner
	  */
	public void setC_BPartnerRelation_ID (int C_BPartnerRelation_ID);

	/** Get Related Partner.
	  * Related Business Partner
	  */
	public int getC_BPartnerRelation_ID();

	public org.compiere.model.I_C_BPartner getC_BPartnerRelation() throws RuntimeException;

    /** Column name C_BP_Mohrem_ID */
    public static final String COLUMNNAME_C_BP_Mohrem_ID = "C_BP_Mohrem_ID";

	/** Set Mohrem	  */
	public void setC_BP_Mohrem_ID (int C_BP_Mohrem_ID);

	/** Get Mohrem	  */
	public int getC_BP_Mohrem_ID();

	public org.compiere.model.I_C_BPartner getC_BP_Mohrem() throws RuntimeException;

    /** Column name C_BP_Relation_ID */
    public static final String COLUMNNAME_C_BP_Relation_ID = "C_BP_Relation_ID";

	/** Set Partner Relation.
	  * Business Partner Relation
	  */
	public void setC_BP_Relation_ID (int C_BP_Relation_ID);

	/** Get Partner Relation.
	  * Business Partner Relation
	  */
	public int getC_BP_Relation_ID();

	public org.compiere.model.I_C_BP_Relation getC_BP_Relation() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException;

    /** Column name CNationality */
    public static final String COLUMNNAME_CNationality = "CNationality";

	/** Set CNationality	  */
	public void setCNationality (String CNationality);

	/** Get CNationality	  */
	public String getCNationality();

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CreateFrom */
    public static final String COLUMNNAME_CreateFrom = "CreateFrom";

	/** Set Create lines from.
	  * Process which will generate a new document lines based on an existing document
	  */
	public void setCreateFrom (String CreateFrom);

	/** Get Create lines from.
	  * Process which will generate a new document lines based on an existing document
	  */
	public String getCreateFrom();

    /** Column name CustomerCode */
    public static final String COLUMNNAME_CustomerCode = "CustomerCode";

	/** Set CustomerCode	  */
	public void setCustomerCode (String CustomerCode);

	/** Get CustomerCode	  */
	public String getCustomerCode();

    /** Column name CustomerName */
    public static final String COLUMNNAME_CustomerName = "CustomerName";

	/** Set CustomerName	  */
	public void setCustomerName (String CustomerName);

	/** Get CustomerName	  */
	public String getCustomerName();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Dpn_Serial_No */
    public static final String COLUMNNAME_Dpn_Serial_No = "Dpn_Serial_No";

	/** Set Dpn_Serial_No	  */
	public void setDpn_Serial_No (BigDecimal Dpn_Serial_No);

	/** Get Dpn_Serial_No	  */
	public BigDecimal getDpn_Serial_No();

    /** Column name DU_PartnerCode_ID */
    public static final String COLUMNNAME_DU_PartnerCode_ID = "DU_PartnerCode_ID";

	/** Set PartnerCode	  */
	public void setDU_PartnerCode_ID (int DU_PartnerCode_ID);

	/** Get PartnerCode	  */
	public int getDU_PartnerCode_ID();

    /** Column name DU_Presta_ID */
    public static final String COLUMNNAME_DU_Presta_ID = "DU_Presta_ID";

	/** Set Prestation	  */
	public void setDU_Presta_ID (int DU_Presta_ID);

	/** Get Prestation	  */
	public int getDU_Presta_ID();

	public org.compiere.model.I_M_Product getDU_Presta() throws RuntimeException;

    /** Column name DU_Visa_Group_ID */
    public static final String COLUMNNAME_DU_Visa_Group_ID = "DU_Visa_Group_ID";

	/** Set Visa	  */
	public void setDU_Visa_Group_ID (int DU_Visa_Group_ID);

	/** Get Visa	  */
	public int getDU_Visa_Group_ID();

	public I_DU_Visa_Group getDU_Visa_Group() throws RuntimeException;

    /** Column name DU_Vol_ID */
    public static final String COLUMNNAME_DU_Vol_ID = "DU_Vol_ID";

	/** Set Vol	  */
	public void setDU_Vol_ID (int DU_Vol_ID);

	/** Get Vol	  */
	public int getDU_Vol_ID();

	public I_DU_Vol getDU_Vol() throws RuntimeException;

    /** Column name Education */
    public static final String COLUMNNAME_Education = "Education";

	/** Set Education	  */
	public void setEducation (String Education);

	/** Get Education	  */
	public String getEducation();

    /** Column name ENUMBER */
    public static final String COLUMNNAME_ENUMBER = "ENUMBER";

	/** Set ENUMBER	  */
	public void setENUMBER (String ENUMBER);

	/** Get ENUMBER	  */
	public String getENUMBER();

    /** Column name FatherName */
    public static final String COLUMNNAME_FatherName = "FatherName";

	/** Set FatherName	  */
	public void setFatherName (String FatherName);

	/** Get FatherName	  */
	public String getFatherName();

    /** Column name FirstName */
    public static final String COLUMNNAME_FirstName = "FirstName";

	/** Set FirstName	  */
	public void setFirstName (String FirstName);

	/** Get FirstName	  */
	public String getFirstName();

    /** Column name GroupCode */
    public static final String COLUMNNAME_GroupCode = "GroupCode";

	/** Set GroupCode	  */
	public void setGroupCode (String GroupCode);

	/** Get GroupCode	  */
	public String getGroupCode();

    /** Column name GroupName */
    public static final String COLUMNNAME_GroupName = "GroupName";

	/** Set GroupName	  */
	public void setGroupName (String GroupName);

	/** Get GroupName	  */
	public String getGroupName();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_ImportOmraBP_ID */
    public static final String COLUMNNAME_I_ImportOmraBP_ID = "I_ImportOmraBP_ID";

	/** Set Import Omra Partner	  */
	public void setI_ImportOmraBP_ID (int I_ImportOmraBP_ID);

	/** Get Import Omra Partner	  */
	public int getI_ImportOmraBP_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name job */
    public static final String COLUMNNAME_job = "job";

	/** Set job	  */
	public void setjob (String job);

	/** Get job	  */
	public String getjob();

    /** Column name LastName */
    public static final String COLUMNNAME_LastName = "LastName";

	/** Set LastName	  */
	public void setLastName (String LastName);

	/** Get LastName	  */
	public String getLastName();

    /** Column name MaritalStatus */
    public static final String COLUMNNAME_MaritalStatus = "MaritalStatus";

	/** Set MaritalStatus	  */
	public void setMaritalStatus (String MaritalStatus);

	/** Get MaritalStatus	  */
	public String getMaritalStatus();

    /** Column name MiddleName */
    public static final String COLUMNNAME_MiddleName = "MiddleName";

	/** Set MiddleName	  */
	public void setMiddleName (String MiddleName);

	/** Get MiddleName	  */
	public String getMiddleName();

    /** Column name MOUFA */
    public static final String COLUMNNAME_MOUFA = "MOUFA";

	/** Set MOUFA	  */
	public void setMOUFA (String MOUFA);

	/** Get MOUFA	  */
	public String getMOUFA();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name No_Of_Relatives */
    public static final String COLUMNNAME_No_Of_Relatives = "No_Of_Relatives";

	/** Set No_Of_Relatives	  */
	public void setNo_Of_Relatives (BigDecimal No_Of_Relatives);

	/** Get No_Of_Relatives	  */
	public BigDecimal getNo_Of_Relatives();

    /** Column name OProcessing */
    public static final String COLUMNNAME_OProcessing = "OProcessing";

	/** Set Online Processing.
	  * This payment can be processed online
	  */
	public void setOProcessing (String OProcessing);

	/** Get Online Processing.
	  * This payment can be processed online
	  */
	public String getOProcessing();

    /** Column name PilgrimID */
    public static final String COLUMNNAME_PilgrimID = "PilgrimID";

	/** Set PilgrimID	  */
	public void setPilgrimID (String PilgrimID);

	/** Get PilgrimID	  */
	public String getPilgrimID();

    /** Column name PPExpDate */
    public static final String COLUMNNAME_PPExpDate = "PPExpDate";

	/** Set PPExpDate	  */
	public void setPPExpDate (String PPExpDate);

	/** Get PPExpDate	  */
	public String getPPExpDate();

    /** Column name PPIssueCity */
    public static final String COLUMNNAME_PPIssueCity = "PPIssueCity";

	/** Set PPIssueCity	  */
	public void setPPIssueCity (String PPIssueCity);

	/** Get PPIssueCity	  */
	public String getPPIssueCity();

    /** Column name PPIssueCountry */
    public static final String COLUMNNAME_PPIssueCountry = "PPIssueCountry";

	/** Set PPIssueCountry	  */
	public void setPPIssueCountry (String PPIssueCountry);

	/** Get PPIssueCountry	  */
	public String getPPIssueCountry();

    /** Column name PPIssueDate */
    public static final String COLUMNNAME_PPIssueDate = "PPIssueDate";

	/** Set PPIssueDate	  */
	public void setPPIssueDate (String PPIssueDate);

	/** Get PPIssueDate	  */
	public String getPPIssueDate();

    /** Column name PPMRZ */
    public static final String COLUMNNAME_PPMRZ = "PPMRZ";

	/** Set PPMRZ	  */
	public void setPPMRZ (String PPMRZ);

	/** Get PPMRZ	  */
	public String getPPMRZ();

    /** Column name PPNo */
    public static final String COLUMNNAME_PPNo = "PPNo";

	/** Set PPNo	  */
	public void setPPNo (String PPNo);

	/** Get PPNo	  */
	public String getPPNo();

    /** Column name PPType */
    public static final String COLUMNNAME_PPType = "PPType";

	/** Set PPType	  */
	public void setPPType (String PPType);

	/** Get PPType	  */
	public String getPPType();

    /** Column name Price */
    public static final String COLUMNNAME_Price = "Price";

	/** Set Price.
	  * Price
	  */
	public void setPrice (BigDecimal Price);

	/** Get Price.
	  * Price
	  */
	public BigDecimal getPrice();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name RelationWithSponser */
    public static final String COLUMNNAME_RelationWithSponser = "RelationWithSponser";

	/** Set RelationWithSponser	  */
	public void setRelationWithSponser (String RelationWithSponser);

	/** Get RelationWithSponser	  */
	public String getRelationWithSponser();

    /** Column name Sex */
    public static final String COLUMNNAME_Sex = "Sex";

	/** Set Sex	  */
	public void setSex (String Sex);

	/** Get Sex	  */
	public String getSex();

    /** Column name SponserID */
    public static final String COLUMNNAME_SponserID = "SponserID";

	/** Set SponserID	  */
	public void setSponserID (String SponserID);

	/** Get SponserID	  */
	public String getSponserID();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

    /** Column name UmrahCompanyCode */
    public static final String COLUMNNAME_UmrahCompanyCode = "UmrahCompanyCode";

	/** Set UmrahCompanyCode	  */
	public void setUmrahCompanyCode (String UmrahCompanyCode);

	/** Get UmrahCompanyCode	  */
	public String getUmrahCompanyCode();

    /** Column name UmrahCompanyName */
    public static final String COLUMNNAME_UmrahCompanyName = "UmrahCompanyName";

	/** Set UmrahCompanyName	  */
	public void setUmrahCompanyName (String UmrahCompanyName);

	/** Get UmrahCompanyName	  */
	public String getUmrahCompanyName();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
