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

/** Generated Interface for DU_VolLine
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DU_VolLine 
{

    /** TableName=DU_VolLine */
    public static final String Table_Name = "DU_VolLine";

    /** AD_Table_ID=1000015 */
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

    /** Column name C_BP_Guide_ID */
    public static final String COLUMNNAME_C_BP_Guide_ID = "C_BP_Guide_ID";

	/** Set Guide	  */
	public void setC_BP_Guide_ID (int C_BP_Guide_ID);

	/** Get Guide	  */
	public int getC_BP_Guide_ID();

	public org.compiere.model.I_C_BPartner getC_BP_Guide() throws RuntimeException;

    /** Column name C_BP_Mohrem_ID */
    public static final String COLUMNNAME_C_BP_Mohrem_ID = "C_BP_Mohrem_ID";

	/** Set Mohrem	  */
	public void setC_BP_Mohrem_ID (int C_BP_Mohrem_ID);

	/** Get Mohrem	  */
	public int getC_BP_Mohrem_ID();

	public org.compiere.model.I_C_BPartner getC_BP_Mohrem() throws RuntimeException;

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

    /** Column name DU_Vol_ID */
    public static final String COLUMNNAME_DU_Vol_ID = "DU_Vol_ID";

	/** Set Vol	  */
	public void setDU_Vol_ID (int DU_Vol_ID);

	/** Get Vol	  */
	public int getDU_Vol_ID();

	public I_DU_Vol getDU_Vol() throws RuntimeException;

    /** Column name DU_VolLine_ID */
    public static final String COLUMNNAME_DU_VolLine_ID = "DU_VolLine_ID";

	/** Set VolLine	  */
	public void setDU_VolLine_ID (int DU_VolLine_ID);

	/** Get VolLine	  */
	public int getDU_VolLine_ID();

    /** Column name DU_VolLine_UU */
    public static final String COLUMNNAME_DU_VolLine_UU = "DU_VolLine_UU";

	/** Set DU_VolLine_UU	  */
	public void setDU_VolLine_UU (String DU_VolLine_UU);

	/** Get DU_VolLine_UU	  */
	public String getDU_VolLine_UU();

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

    /** Column name isFree */
    public static final String COLUMNNAME_isFree = "isFree";

	/** Set Gratuitee	  */
	public void setisFree (boolean isFree);

	/** Get Gratuitee	  */
	public boolean isFree();

    /** Column name IsInclude */
    public static final String COLUMNNAME_IsInclude = "IsInclude";

	/** Set Included.
	  * Defines whether this content / template is included into another one
	  */
	public void setIsInclude (boolean IsInclude);

	/** Get Included.
	  * Defines whether this content / template is included into another one
	  */
	public boolean isInclude();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name LinkMohrem */
    public static final String COLUMNNAME_LinkMohrem = "LinkMohrem";

	/** Set Type de parenté	  */
	public void setLinkMohrem (String LinkMohrem);

	/** Get Type de parenté	  */
	public String getLinkMohrem();

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

    /** Column name NbrDayMedina */
    public static final String COLUMNNAME_NbrDayMedina = "NbrDayMedina";

	/** Set Nbr Jours à Medina	  */
	public void setNbrDayMedina (BigDecimal NbrDayMedina);

	/** Get Nbr Jours à Medina	  */
	public BigDecimal getNbrDayMedina();

    /** Column name NbrDayMekha */
    public static final String COLUMNNAME_NbrDayMekha = "NbrDayMekha";

	/** Set Nbr Jours à Mekha	  */
	public void setNbrDayMekha (BigDecimal NbrDayMekha);

	/** Get Nbr Jours à Mekha	  */
	public BigDecimal getNbrDayMekha();

    /** Column name SetAffectation */
    public static final String COLUMNNAME_SetAffectation = "SetAffectation";

	/** Set Afféctation Pélrin	  */
	public void setSetAffectation (String SetAffectation);

	/** Get Afféctation Pélrin	  */
	public String getSetAffectation();

    /** Column name SetGratuiteSV */
    public static final String COLUMNNAME_SetGratuiteSV = "SetGratuiteSV";

	/** Set Choir les gratuitées	  */
	public void setSetGratuiteSV (String SetGratuiteSV);

	/** Get Choir les gratuitées	  */
	public String getSetGratuiteSV();

    /** Column name TypeRoom */
    public static final String COLUMNNAME_TypeRoom = "TypeRoom";

	/** Set Type de chambre	  */
	public void setTypeRoom (String TypeRoom);

	/** Get Type de chambre	  */
	public String getTypeRoom();

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
