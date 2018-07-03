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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for DU_Vol
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_Vol extends PO implements I_DU_Vol, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180703L;

    /** Standard Constructor */
    public X_DU_Vol (Properties ctx, int DU_Vol_ID, String trxName)
    {
      super (ctx, DU_Vol_ID, trxName);
      /** if (DU_Vol_ID == 0)
        {
			setDU_Vol_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_DU_Vol (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_Vol[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Capacité.
		@param Capacite Capacité	  */
	public void setCapacite (BigDecimal Capacite)
	{
		set_Value (COLUMNNAME_Capacite, Capacite);
	}

	/** Get Capacité.
		@return Capacité	  */
	public BigDecimal getCapacite () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Capacite);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Date et heur de départ.
		@param DepartDateTime_Direct Date et heur de départ	  */
	public void setDepartDateTime_Direct (Timestamp DepartDateTime_Direct)
	{
		set_Value (COLUMNNAME_DepartDateTime_Direct, DepartDateTime_Direct);
	}

	/** Get Date et heur de départ.
		@return Date et heur de départ	  */
	public Timestamp getDepartDateTime_Direct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DepartDateTime_Direct);
	}

	/** Set Date et heur Déaprt-Escale.
		@param DepartDateTime_Escale Date et heur Déaprt-Escale	  */
	public void setDepartDateTime_Escale (Timestamp DepartDateTime_Escale)
	{
		set_Value (COLUMNNAME_DepartDateTime_Escale, DepartDateTime_Escale);
	}

	/** Get Date et heur Déaprt-Escale.
		@return Date et heur Déaprt-Escale	  */
	public Timestamp getDepartDateTime_Escale () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DepartDateTime_Escale);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public I_DU_Compa getDU_Compa() throws RuntimeException
    {
		return (I_DU_Compa)MTable.get(getCtx(), I_DU_Compa.Table_Name)
			.getPO(getDU_Compa_ID(), get_TrxName());	}

	/** Set Company.
		@param DU_Compa_ID Company	  */
	public void setDU_Compa_ID (int DU_Compa_ID)
	{
		if (DU_Compa_ID < 1) 
			set_Value (COLUMNNAME_DU_Compa_ID, null);
		else 
			set_Value (COLUMNNAME_DU_Compa_ID, Integer.valueOf(DU_Compa_ID));
	}

	/** Get Company.
		@return Company	  */
	public int getDU_Compa_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Compa_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vol.
		@param DU_Vol_ID Vol	  */
	public void setDU_Vol_ID (int DU_Vol_ID)
	{
		if (DU_Vol_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Vol_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Vol_ID, Integer.valueOf(DU_Vol_ID));
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

	/** Set DU_Vol_UU.
		@param DU_Vol_UU DU_Vol_UU	  */
	public void setDU_Vol_UU (String DU_Vol_UU)
	{
		set_Value (COLUMNNAME_DU_Vol_UU, DU_Vol_UU);
	}

	/** Get DU_Vol_UU.
		@return DU_Vol_UU	  */
	public String getDU_Vol_UU () 
	{
		return (String)get_Value(COLUMNNAME_DU_Vol_UU);
	}

	/** Set Export SRDOCS.
		@param ESRDOCS Export SRDOCS	  */
	public void setESRDOCS (String ESRDOCS)
	{
		set_Value (COLUMNNAME_ESRDOCS, ESRDOCS);
	}

	/** Get Export SRDOCS.
		@return Export SRDOCS	  */
	public String getESRDOCS () 
	{
		return (String)get_Value(COLUMNNAME_ESRDOCS);
	}

	/** Direct = DI */
	public static final String FLIGHTTYPE_Direct = "DI";
	/** Escale = ES */
	public static final String FLIGHTTYPE_Escale = "ES";
	/** Set Type de Vol.
		@param FlightType Type de Vol	  */
	public void setFlightType (String FlightType)
	{

		set_Value (COLUMNNAME_FlightType, FlightType);
	}

	/** Get Type de Vol.
		@return Type de Vol	  */
	public String getFlightType () 
	{
		return (String)get_Value(COLUMNNAME_FlightType);
	}

	/** Set Closed Status.
		@param IsClosed 
		The status is closed
	  */
	public void setIsClosed (boolean IsClosed)
	{
		set_Value (COLUMNNAME_IsClosed, Boolean.valueOf(IsClosed));
	}

	/** Get Closed Status.
		@return The status is closed
	  */
	public boolean isClosed () 
	{
		Object oo = get_Value(COLUMNNAME_IsClosed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Numéro de vol de Départ.
		@param NumDepartFlight_Direct Numéro de vol de Départ	  */
	public void setNumDepartFlight_Direct (String NumDepartFlight_Direct)
	{
		set_Value (COLUMNNAME_NumDepartFlight_Direct, NumDepartFlight_Direct);
	}

	/** Get Numéro de vol de Départ.
		@return Numéro de vol de Départ	  */
	public String getNumDepartFlight_Direct () 
	{
		return (String)get_Value(COLUMNNAME_NumDepartFlight_Direct);
	}

	/** Set Numéro de vol Départ-Escale.
		@param NumDepartFlight_Escale Numéro de vol Départ-Escale	  */
	public void setNumDepartFlight_Escale (String NumDepartFlight_Escale)
	{
		set_Value (COLUMNNAME_NumDepartFlight_Escale, NumDepartFlight_Escale);
	}

	/** Get Numéro de vol Départ-Escale.
		@return Numéro de vol Départ-Escale	  */
	public String getNumDepartFlight_Escale () 
	{
		return (String)get_Value(COLUMNNAME_NumDepartFlight_Escale);
	}

	/** Set Numéro du vol de retour.
		@param NumReturnFlight_Direct Numéro du vol de retour	  */
	public void setNumReturnFlight_Direct (String NumReturnFlight_Direct)
	{
		set_Value (COLUMNNAME_NumReturnFlight_Direct, NumReturnFlight_Direct);
	}

	/** Get Numéro du vol de retour.
		@return Numéro du vol de retour	  */
	public String getNumReturnFlight_Direct () 
	{
		return (String)get_Value(COLUMNNAME_NumReturnFlight_Direct);
	}

	/** Set Numéro de vol Retour-Escale.
		@param NumReturnFlight_Escale Numéro de vol Retour-Escale	  */
	public void setNumReturnFlight_Escale (String NumReturnFlight_Escale)
	{
		set_Value (COLUMNNAME_NumReturnFlight_Escale, NumReturnFlight_Escale);
	}

	/** Get Numéro de vol Retour-Escale.
		@return Numéro de vol Retour-Escale	  */
	public String getNumReturnFlight_Escale () 
	{
		return (String)get_Value(COLUMNNAME_NumReturnFlight_Escale);
	}

	/** Set lieu de départ.
		@param PlaceDepart_Direct lieu de départ	  */
	public void setPlaceDepart_Direct (String PlaceDepart_Direct)
	{
		set_Value (COLUMNNAME_PlaceDepart_Direct, PlaceDepart_Direct);
	}

	/** Get lieu de départ.
		@return lieu de départ	  */
	public String getPlaceDepart_Direct () 
	{
		return (String)get_Value(COLUMNNAME_PlaceDepart_Direct);
	}

	/** Set Lieu de Départ Escale.
		@param PlaceDepart_Escale Lieu de Départ Escale	  */
	public void setPlaceDepart_Escale (String PlaceDepart_Escale)
	{
		set_Value (COLUMNNAME_PlaceDepart_Escale, PlaceDepart_Escale);
	}

	/** Get Lieu de Départ Escale.
		@return Lieu de Départ Escale	  */
	public String getPlaceDepart_Escale () 
	{
		return (String)get_Value(COLUMNNAME_PlaceDepart_Escale);
	}

	/** Set Lieu de retour.
		@param PlaceReturn_Direct Lieu de retour	  */
	public void setPlaceReturn_Direct (String PlaceReturn_Direct)
	{
		set_Value (COLUMNNAME_PlaceReturn_Direct, PlaceReturn_Direct);
	}

	/** Get Lieu de retour.
		@return Lieu de retour	  */
	public String getPlaceReturn_Direct () 
	{
		return (String)get_Value(COLUMNNAME_PlaceReturn_Direct);
	}

	/** Set Lieu de retour Escale.
		@param PlaceReturn_Escale Lieu de retour Escale	  */
	public void setPlaceReturn_Escale (String PlaceReturn_Escale)
	{
		set_Value (COLUMNNAME_PlaceReturn_Escale, PlaceReturn_Escale);
	}

	/** Get Lieu de retour Escale.
		@return Lieu de retour Escale	  */
	public String getPlaceReturn_Escale () 
	{
		return (String)get_Value(COLUMNNAME_PlaceReturn_Escale);
	}

	/** Set Imprimer Etat Recapitulatif.
		@param PrintFlightSum Imprimer Etat Recapitulatif	  */
	public void setPrintFlightSum (String PrintFlightSum)
	{
		set_Value (COLUMNNAME_PrintFlightSum, PrintFlightSum);
	}

	/** Get Imprimer Etat Recapitulatif.
		@return Imprimer Etat Recapitulatif	  */
	public String getPrintFlightSum () 
	{
		return (String)get_Value(COLUMNNAME_PrintFlightSum);
	}

	/** Set Imprimer Manifest.
		@param PrintManifest Imprimer Manifest	  */
	public void setPrintManifest (String PrintManifest)
	{
		set_Value (COLUMNNAME_PrintManifest, PrintManifest);
	}

	/** Get Imprimer Manifest.
		@return Imprimer Manifest	  */
	public String getPrintManifest () 
	{
		return (String)get_Value(COLUMNNAME_PrintManifest);
	}

	/** Set Imprimer Manifest SV.
		@param PrintManifestSV Imprimer Manifest SV	  */
	public void setPrintManifestSV (String PrintManifestSV)
	{
		set_Value (COLUMNNAME_PrintManifestSV, PrintManifestSV);
	}

	/** Get Imprimer Manifest SV.
		@return Imprimer Manifest SV	  */
	public String getPrintManifestSV () 
	{
		return (String)get_Value(COLUMNNAME_PrintManifestSV);
	}

	/** Set Imprimer etat réservation.
		@param PrintStateReser Imprimer etat réservation	  */
	public void setPrintStateReser (String PrintStateReser)
	{
		set_Value (COLUMNNAME_PrintStateReser, PrintStateReser);
	}

	/** Get Imprimer etat réservation.
		@return Imprimer etat réservation	  */
	public String getPrintStateReser () 
	{
		return (String)get_Value(COLUMNNAME_PrintStateReser);
	}

	/** Set Imprimer Voocher.
		@param PrintVoocher Imprimer Voocher	  */
	public void setPrintVoocher (String PrintVoocher)
	{
		set_Value (COLUMNNAME_PrintVoocher, PrintVoocher);
	}

	/** Get Imprimer Voocher.
		@return Imprimer Voocher	  */
	public String getPrintVoocher () 
	{
		return (String)get_Value(COLUMNNAME_PrintVoocher);
	}

	/** Set Imprimer Voocher VIP.
		@param PrintVoocherVIP Imprimer Voocher VIP	  */
	public void setPrintVoocherVIP (String PrintVoocherVIP)
	{
		set_Value (COLUMNNAME_PrintVoocherVIP, PrintVoocherVIP);
	}

	/** Get Imprimer Voocher VIP.
		@return Imprimer Voocher VIP	  */
	public String getPrintVoocherVIP () 
	{
		return (String)get_Value(COLUMNNAME_PrintVoocherVIP);
	}

	/** Set Available Quantity.
		@param QtyAvailable 
		Available Quantity (On Hand - Reserved)
	  */
	public void setQtyAvailable (BigDecimal QtyAvailable)
	{
		throw new IllegalArgumentException ("QtyAvailable is virtual column");	}

	/** Get Available Quantity.
		@return Available Quantity (On Hand - Reserved)
	  */
	public BigDecimal getQtyAvailable () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyAvailable);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		throw new IllegalArgumentException ("QtyOrdered is virtual column");	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Date et heur de retour.
		@param ReturnDateTime_Direct Date et heur de retour	  */
	public void setReturnDateTime_Direct (Timestamp ReturnDateTime_Direct)
	{
		set_Value (COLUMNNAME_ReturnDateTime_Direct, ReturnDateTime_Direct);
	}

	/** Get Date et heur de retour.
		@return Date et heur de retour	  */
	public Timestamp getReturnDateTime_Direct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ReturnDateTime_Direct);
	}

	/** Set Date et Heur Retour-Escale.
		@param ReturnDateTime_Escale Date et Heur Retour-Escale	  */
	public void setReturnDateTime_Escale (Timestamp ReturnDateTime_Escale)
	{
		set_Value (COLUMNNAME_ReturnDateTime_Escale, ReturnDateTime_Escale);
	}

	/** Get Date et Heur Retour-Escale.
		@return Date et Heur Retour-Escale	  */
	public Timestamp getReturnDateTime_Escale () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ReturnDateTime_Escale);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}