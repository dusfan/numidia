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

/** Generated Interface for DU_Vol
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DU_Vol 
{

    /** TableName=DU_Vol */
    public static final String Table_Name = "DU_Vol";

    /** AD_Table_ID=1000016 */
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

    /** Column name Capacite */
    public static final String COLUMNNAME_Capacite = "Capacite";

	/** Set Capacité	  */
	public void setCapacite (BigDecimal Capacite);

	/** Get Capacité	  */
	public BigDecimal getCapacite();

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

	/** Set Affecter les guides.
	  * Affecter les guides
	  */
	public void setCreateFrom (String CreateFrom);

	/** Get Affecter les guides.
	  * Affecter les guides
	  */
	public String getCreateFrom();

    /** Column name DepartDateTime_Direct */
    public static final String COLUMNNAME_DepartDateTime_Direct = "DepartDateTime_Direct";

	/** Set Date et heur de départ	  */
	public void setDepartDateTime_Direct (Timestamp DepartDateTime_Direct);

	/** Get Date et heur de départ	  */
	public Timestamp getDepartDateTime_Direct();

    /** Column name DepartDateTime_Escale */
    public static final String COLUMNNAME_DepartDateTime_Escale = "DepartDateTime_Escale";

	/** Set Date et heur Déaprt-Escale	  */
	public void setDepartDateTime_Escale (Timestamp DepartDateTime_Escale);

	/** Get Date et heur Déaprt-Escale	  */
	public Timestamp getDepartDateTime_Escale();

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

    /** Column name DU_Compa_ID */
    public static final String COLUMNNAME_DU_Compa_ID = "DU_Compa_ID";

	/** Set Company	  */
	public void setDU_Compa_ID (int DU_Compa_ID);

	/** Get Company	  */
	public int getDU_Compa_ID();


    /** Column name DU_Vol_ID */
    public static final String COLUMNNAME_DU_Vol_ID = "DU_Vol_ID";

	/** Set Vol	  */
	public void setDU_Vol_ID (int DU_Vol_ID);

	/** Get Vol	  */
	public int getDU_Vol_ID();

    /** Column name DU_Vol_UU */
    public static final String COLUMNNAME_DU_Vol_UU = "DU_Vol_UU";

	/** Set DU_Vol_UU	  */
	public void setDU_Vol_UU (String DU_Vol_UU);

	/** Get DU_Vol_UU	  */
	public String getDU_Vol_UU();

    /** Column name FlightType */
    public static final String COLUMNNAME_FlightType = "FlightType";

	/** Set Type de Vol	  */
	public void setFlightType (String FlightType);

	/** Get Type de Vol	  */
	public String getFlightType();

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

    /** Column name NumDepartFlight_Direct */
    public static final String COLUMNNAME_NumDepartFlight_Direct = "NumDepartFlight_Direct";

	/** Set Numéro de vol de Départ	  */
	public void setNumDepartFlight_Direct (String NumDepartFlight_Direct);

	/** Get Numéro de vol de Départ	  */
	public String getNumDepartFlight_Direct();

    /** Column name NumDepartFlight_Escale */
    public static final String COLUMNNAME_NumDepartFlight_Escale = "NumDepartFlight_Escale";

	/** Set Numéro de vol Départ-Escale	  */
	public void setNumDepartFlight_Escale (String NumDepartFlight_Escale);

	/** Get Numéro de vol Départ-Escale	  */
	public String getNumDepartFlight_Escale();

    /** Column name NumReturnFlight_Direct */
    public static final String COLUMNNAME_NumReturnFlight_Direct = "NumReturnFlight_Direct";

	/** Set Numéro du vol de retour	  */
	public void setNumReturnFlight_Direct (String NumReturnFlight_Direct);

	/** Get Numéro du vol de retour	  */
	public String getNumReturnFlight_Direct();

    /** Column name NumReturnFlight_Escale */
    public static final String COLUMNNAME_NumReturnFlight_Escale = "NumReturnFlight_Escale";

	/** Set Numéro de vol Retour-Escale	  */
	public void setNumReturnFlight_Escale (String NumReturnFlight_Escale);

	/** Get Numéro de vol Retour-Escale	  */
	public String getNumReturnFlight_Escale();

    /** Column name PlaceDepart_Direct */
    public static final String COLUMNNAME_PlaceDepart_Direct = "PlaceDepart_Direct";

	/** Set lieu de départ	  */
	public void setPlaceDepart_Direct (String PlaceDepart_Direct);

	/** Get lieu de départ	  */
	public String getPlaceDepart_Direct();

    /** Column name PlaceDepart_Escale */
    public static final String COLUMNNAME_PlaceDepart_Escale = "PlaceDepart_Escale";

	/** Set Lieu de Départ Escale	  */
	public void setPlaceDepart_Escale (String PlaceDepart_Escale);

	/** Get Lieu de Départ Escale	  */
	public String getPlaceDepart_Escale();

    /** Column name PlaceReturn_Direct */
    public static final String COLUMNNAME_PlaceReturn_Direct = "PlaceReturn_Direct";

	/** Set Lieu de retour	  */
	public void setPlaceReturn_Direct (String PlaceReturn_Direct);

	/** Get Lieu de retour	  */
	public String getPlaceReturn_Direct();

    /** Column name PlaceReturn_Escale */
    public static final String COLUMNNAME_PlaceReturn_Escale = "PlaceReturn_Escale";

	/** Set Lieu de retour Escale	  */
	public void setPlaceReturn_Escale (String PlaceReturn_Escale);

	/** Get Lieu de retour Escale	  */
	public String getPlaceReturn_Escale();

    /** Column name PrintFlightSum */
    public static final String COLUMNNAME_PrintFlightSum = "PrintFlightSum";

	/** Set Imprimer Etat Recapitulatif	  */
	public void setPrintFlightSum (String PrintFlightSum);

	/** Get Imprimer Etat Recapitulatif	  */
	public String getPrintFlightSum();

    /** Column name PrintManifest */
    public static final String COLUMNNAME_PrintManifest = "PrintManifest";

	/** Set Imprimer Manifest	  */
	public void setPrintManifest (String PrintManifest);

	/** Get Imprimer Manifest	  */
	public String getPrintManifest();

    /** Column name PrintStateReser */
    public static final String COLUMNNAME_PrintStateReser = "PrintStateReser";

	/** Set Imprimer etat réservation	  */
	public void setPrintStateReser (String PrintStateReser);

	/** Get Imprimer etat réservation	  */
	public String getPrintStateReser();

    /** Column name PrintVoocher */
    public static final String COLUMNNAME_PrintVoocher = "PrintVoocher";

	/** Set Imprimer Voocher	  */
	public void setPrintVoocher (String PrintVoocher);

	/** Get Imprimer Voocher	  */
	public String getPrintVoocher();

    /** Column name PrintVoocherVIP */
    public static final String COLUMNNAME_PrintVoocherVIP = "PrintVoocherVIP";

	/** Set Imprimer Voocher VIP	  */
	public void setPrintVoocherVIP (String PrintVoocherVIP);

	/** Get Imprimer Voocher VIP	  */
	public String getPrintVoocherVIP();

    /** Column name QtyAvailable */
    public static final String COLUMNNAME_QtyAvailable = "QtyAvailable";

	/** Set Available Quantity.
	  * Available Quantity (On Hand - Reserved)
	  */
	public void setQtyAvailable (BigDecimal QtyAvailable);

	/** Get Available Quantity.
	  * Available Quantity (On Hand - Reserved)
	  */
	public BigDecimal getQtyAvailable();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Quantity.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Quantity.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name ReturnDateTime_Direct */
    public static final String COLUMNNAME_ReturnDateTime_Direct = "ReturnDateTime_Direct";

	/** Set Date et heur de retour	  */
	public void setReturnDateTime_Direct (Timestamp ReturnDateTime_Direct);

	/** Get Date et heur de retour	  */
	public Timestamp getReturnDateTime_Direct();

    /** Column name ReturnDateTime_Escale */
    public static final String COLUMNNAME_ReturnDateTime_Escale = "ReturnDateTime_Escale";

	/** Set Date et Heur Retour-Escale	  */
	public void setReturnDateTime_Escale (Timestamp ReturnDateTime_Escale);

	/** Get Date et Heur Retour-Escale	  */
	public Timestamp getReturnDateTime_Escale();

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
