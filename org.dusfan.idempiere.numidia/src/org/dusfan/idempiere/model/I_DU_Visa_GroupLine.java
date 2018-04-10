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

/** Generated Interface for DU_Visa_GroupLine
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DU_Visa_GroupLine 
{

    /** TableName=DU_Visa_GroupLine */
    public static final String Table_Name = "DU_Visa_GroupLine";

    /** AD_Table_ID=1000010 */
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

    /** Column name Code_VISA_E */
    public static final String COLUMNNAME_Code_VISA_E = "Code_VISA_E";

	/** Set Code_VISA_E	  */
	public void setCode_VISA_E (String Code_VISA_E);

	/** Get Code_VISA_E	  */
	public String getCode_VISA_E();

    /** Column name Code_VISA_M */
    public static final String COLUMNNAME_Code_VISA_M = "Code_VISA_M";

	/** Set Code_VISA_M	  */
	public void setCode_VISA_M (String Code_VISA_M);

	/** Get Code_VISA_M	  */
	public String getCode_VISA_M();

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

    /** Column name DU_Visa_Group_ID */
    public static final String COLUMNNAME_DU_Visa_Group_ID = "DU_Visa_Group_ID";

	/** Set Visa	  */
	public void setDU_Visa_Group_ID (int DU_Visa_Group_ID);

	/** Get Visa	  */
	public int getDU_Visa_Group_ID();

	public I_DU_Visa_Group getDU_Visa_Group() throws RuntimeException;

    /** Column name DU_Visa_GroupLine_ID */
    public static final String COLUMNNAME_DU_Visa_GroupLine_ID = "DU_Visa_GroupLine_ID";

	/** Set Visa	  */
	public void setDU_Visa_GroupLine_ID (int DU_Visa_GroupLine_ID);

	/** Get Visa	  */
	public int getDU_Visa_GroupLine_ID();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

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

    /** Column name PilgrimID */
    public static final String COLUMNNAME_PilgrimID = "PilgrimID";

	/** Set PilgrimID	  */
	public void setPilgrimID (String PilgrimID);

	/** Get PilgrimID	  */
	public String getPilgrimID();

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

    /** Column name SponserID */
    public static final String COLUMNNAME_SponserID = "SponserID";

	/** Set SponserID	  */
	public void setSponserID (String SponserID);

	/** Get SponserID	  */
	public String getSponserID();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

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
