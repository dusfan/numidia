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

/** Generated Interface for DU_VAllocation
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DU_VAllocation 
{

    /** TableName=DU_VAllocation */
    public static final String Table_Name = "DU_VAllocation";

    /** AD_Table_ID=1000020 */
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

    /** Column name AmoutVH */
    public static final String COLUMNNAME_AmoutVH = "AmoutVH";

	/** Set Montant VOOCHER	  */
	public void setAmoutVH (BigDecimal AmoutVH);

	/** Get Montant VOOCHER	  */
	public BigDecimal getAmoutVH();

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

    /** Column name DateEntreMD */
    public static final String COLUMNNAME_DateEntreMD = "DateEntreMD";

	/** Set Date entree Medina	  */
	public void setDateEntreMD (Timestamp DateEntreMD);

	/** Get Date entree Medina	  */
	public Timestamp getDateEntreMD();

    /** Column name DateEntreMK */
    public static final String COLUMNNAME_DateEntreMK = "DateEntreMK";

	/** Set Date entree Mekha	  */
	public void setDateEntreMK (Timestamp DateEntreMK);

	/** Get Date entree Mekha	  */
	public Timestamp getDateEntreMK();

    /** Column name DateSortieMD */
    public static final String COLUMNNAME_DateSortieMD = "DateSortieMD";

	/** Set Date sortie Medina	  */
	public void setDateSortieMD (Timestamp DateSortieMD);

	/** Get Date sortie Medina	  */
	public Timestamp getDateSortieMD();

    /** Column name DateSortieMK */
    public static final String COLUMNNAME_DateSortieMK = "DateSortieMK";

	/** Set Date sortie Mekha	  */
	public void setDateSortieMK (Timestamp DateSortieMK);

	/** Get Date sortie Mekha	  */
	public Timestamp getDateSortieMK();

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

    /** Column name DU_Hotel_ID */
    public static final String COLUMNNAME_DU_Hotel_ID = "DU_Hotel_ID";

	/** Set Hotel	  */
	public void setDU_Hotel_ID (int DU_Hotel_ID);

	/** Get Hotel	  */
	public int getDU_Hotel_ID();

	public I_DU_Hotel getDU_Hotel() throws RuntimeException;

    /** Column name DU_HotelMD_ID */
    public static final String COLUMNNAME_DU_HotelMD_ID = "DU_HotelMD_ID";

	/** Set Hôtel Medina	  */
	public void setDU_HotelMD_ID (int DU_HotelMD_ID);

	/** Get Hôtel Medina	  */
	public int getDU_HotelMD_ID();

	public I_DU_Hotel getDU_HotelMD() throws RuntimeException;

    /** Column name DU_VAllocation_ID */
    public static final String COLUMNNAME_DU_VAllocation_ID = "DU_VAllocation_ID";

	/** Set Afféctation Pélrin	  */
	public void setDU_VAllocation_ID (int DU_VAllocation_ID);

	/** Get Afféctation Pélrin	  */
	public int getDU_VAllocation_ID();

    /** Column name DU_VAllocation_UU */
    public static final String COLUMNNAME_DU_VAllocation_UU = "DU_VAllocation_UU";

	/** Set DU_VAllocation_UU	  */
	public void setDU_VAllocation_UU (String DU_VAllocation_UU);

	/** Get DU_VAllocation_UU	  */
	public String getDU_VAllocation_UU();

    /** Column name DU_Vol_ID */
    public static final String COLUMNNAME_DU_Vol_ID = "DU_Vol_ID";

	/** Set Vol	  */
	public void setDU_Vol_ID (int DU_Vol_ID);

	/** Get Vol	  */
	public int getDU_Vol_ID();

	public I_DU_Vol getDU_Vol() throws RuntimeException;

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

    /** Column name isVIP */
    public static final String COLUMNNAME_isVIP = "isVIP";

	/** Set VIP	  */
	public void setisVIP (boolean isVIP);

	/** Get VIP	  */
	public boolean isVIP();

    /** Column name NRVD */
    public static final String COLUMNNAME_NRVD = "NRVD";

	/** Set N° Réservation Medina	  */
	public void setNRVD (String NRVD);

	/** Get N° Réservation Medina	  */
	public String getNRVD();

    /** Column name NRVK */
    public static final String COLUMNNAME_NRVK = "NRVK";

	/** Set N° Réservation Mekha	  */
	public void setNRVK (String NRVK);

	/** Get N° Réservation Mekha	  */
	public String getNRVK();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
