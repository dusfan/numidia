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

/** Generated Model for DU_VAllocation
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_VAllocation extends PO implements I_DU_VAllocation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180302L;

    /** Standard Constructor */
    public X_DU_VAllocation (Properties ctx, int DU_VAllocation_ID, String trxName)
    {
      super (ctx, DU_VAllocation_ID, trxName);
      /** if (DU_VAllocation_ID == 0)
        {
			setDU_VAllocation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_VAllocation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_VAllocation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Montant VOOCHER.
		@param AmoutVH Montant VOOCHER	  */
	public void setAmoutVH (BigDecimal AmoutVH)
	{
		set_Value (COLUMNNAME_AmoutVH, AmoutVH);
	}

	/** Get Montant VOOCHER.
		@return Montant VOOCHER	  */
	public BigDecimal getAmoutVH () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmoutVH);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Date entree Medina.
		@param DateEntreMD Date entree Medina	  */
	public void setDateEntreMD (Timestamp DateEntreMD)
	{
		set_Value (COLUMNNAME_DateEntreMD, DateEntreMD);
	}

	/** Get Date entree Medina.
		@return Date entree Medina	  */
	public Timestamp getDateEntreMD () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateEntreMD);
	}

	/** Set Date entree Mekha.
		@param DateEntreMK Date entree Mekha	  */
	public void setDateEntreMK (Timestamp DateEntreMK)
	{
		set_Value (COLUMNNAME_DateEntreMK, DateEntreMK);
	}

	/** Get Date entree Mekha.
		@return Date entree Mekha	  */
	public Timestamp getDateEntreMK () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateEntreMK);
	}

	/** Set Date sortie Medina.
		@param DateSortieMD Date sortie Medina	  */
	public void setDateSortieMD (Timestamp DateSortieMD)
	{
		set_Value (COLUMNNAME_DateSortieMD, DateSortieMD);
	}

	/** Get Date sortie Medina.
		@return Date sortie Medina	  */
	public Timestamp getDateSortieMD () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateSortieMD);
	}

	/** Set Date sortie Mekha.
		@param DateSortieMK Date sortie Mekha	  */
	public void setDateSortieMK (Timestamp DateSortieMK)
	{
		set_Value (COLUMNNAME_DateSortieMK, DateSortieMK);
	}

	/** Get Date sortie Mekha.
		@return Date sortie Mekha	  */
	public Timestamp getDateSortieMK () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateSortieMK);
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

	public I_DU_Hotel getDU_Hotel() throws RuntimeException
    {
		return (I_DU_Hotel)MTable.get(getCtx(), I_DU_Hotel.Table_Name)
			.getPO(getDU_Hotel_ID(), get_TrxName());	}

	/** Set Hotel.
		@param DU_Hotel_ID Hotel	  */
	public void setDU_Hotel_ID (int DU_Hotel_ID)
	{
		if (DU_Hotel_ID < 1) 
			set_Value (COLUMNNAME_DU_Hotel_ID, null);
		else 
			set_Value (COLUMNNAME_DU_Hotel_ID, Integer.valueOf(DU_Hotel_ID));
	}

	/** Get Hotel.
		@return Hotel	  */
	public int getDU_Hotel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Hotel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DU_Hotel getDU_HotelMD() throws RuntimeException
    {
		return (I_DU_Hotel)MTable.get(getCtx(), I_DU_Hotel.Table_Name)
			.getPO(getDU_HotelMD_ID(), get_TrxName());	}

	/** Set Hôtel Medina.
		@param DU_HotelMD_ID Hôtel Medina	  */
	public void setDU_HotelMD_ID (int DU_HotelMD_ID)
	{
		if (DU_HotelMD_ID < 1) 
			set_Value (COLUMNNAME_DU_HotelMD_ID, null);
		else 
			set_Value (COLUMNNAME_DU_HotelMD_ID, Integer.valueOf(DU_HotelMD_ID));
	}

	/** Get Hôtel Medina.
		@return Hôtel Medina	  */
	public int getDU_HotelMD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_HotelMD_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Afféctation Pélrin.
		@param DU_VAllocation_ID Afféctation Pélrin	  */
	public void setDU_VAllocation_ID (int DU_VAllocation_ID)
	{
		if (DU_VAllocation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_VAllocation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_VAllocation_ID, Integer.valueOf(DU_VAllocation_ID));
	}

	/** Get Afféctation Pélrin.
		@return Afféctation Pélrin	  */
	public int getDU_VAllocation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_VAllocation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DU_VAllocation_UU.
		@param DU_VAllocation_UU DU_VAllocation_UU	  */
	public void setDU_VAllocation_UU (String DU_VAllocation_UU)
	{
		set_Value (COLUMNNAME_DU_VAllocation_UU, DU_VAllocation_UU);
	}

	/** Get DU_VAllocation_UU.
		@return DU_VAllocation_UU	  */
	public String getDU_VAllocation_UU () 
	{
		return (String)get_Value(COLUMNNAME_DU_VAllocation_UU);
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

	/** Set VIP.
		@param isVIP VIP	  */
	public void setisVIP (boolean isVIP)
	{
		set_Value (COLUMNNAME_isVIP, Boolean.valueOf(isVIP));
	}

	/** Get VIP.
		@return VIP	  */
	public boolean isVIP () 
	{
		Object oo = get_Value(COLUMNNAME_isVIP);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set N° Réservation Medina.
		@param NRVD N° Réservation Medina	  */
	public void setNRVD (String NRVD)
	{
		set_Value (COLUMNNAME_NRVD, NRVD);
	}

	/** Get N° Réservation Medina.
		@return N° Réservation Medina	  */
	public String getNRVD () 
	{
		return (String)get_Value(COLUMNNAME_NRVD);
	}

	/** Set N° Réservation Mekha.
		@param NRVK N° Réservation Mekha	  */
	public void setNRVK (String NRVK)
	{
		set_Value (COLUMNNAME_NRVK, NRVK);
	}

	/** Get N° Réservation Mekha.
		@return N° Réservation Mekha	  */
	public String getNRVK () 
	{
		return (String)get_Value(COLUMNNAME_NRVK);
	}

	/** Chambre double = 20 */
	public static final String TYPEROOM_ChambreDouble = "20";
	/** Chambre cinq = 50 */
	public static final String TYPEROOM_ChambreCinq = "50";
	/** Chambre quadruple = 40 */
	public static final String TYPEROOM_ChambreQuadruple = "40";
	/** Chambre six = 60 */
	public static final String TYPEROOM_ChambreSix = "60";
	/** Chambre single = 10 */
	public static final String TYPEROOM_ChambreSingle = "10";
	/** Chambre triple = 30 */
	public static final String TYPEROOM_ChambreTriple = "30";
	/** Chambre seven = 70 */
	public static final String TYPEROOM_ChambreSeven = "70";
	/** Chambre huite = 80 */
	public static final String TYPEROOM_ChambreHuite = "80";
	/** INF = 90 */
	public static final String TYPEROOM_INF = "90";
	/** CHD sans LIT = 91 */
	public static final String TYPEROOM_CHDSansLIT = "91";
	/** Guide Numidia = 92 */
	public static final String TYPEROOM_GuideNumidia = "92";
	/** Guide Sous Traitons = 93 */
	public static final String TYPEROOM_GuideSousTraitons = "93";
	/** Gratuite = 94 */
	public static final String TYPEROOM_Gratuite = "94";
	/** Set Type de chambre.
		@param TypeRoom Type de chambre	  */
	public void setTypeRoom (String TypeRoom)
	{

		set_Value (COLUMNNAME_TypeRoom, TypeRoom);
	}

	/** Get Type de chambre.
		@return Type de chambre	  */
	public String getTypeRoom () 
	{
		return (String)get_Value(COLUMNNAME_TypeRoom);
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
}