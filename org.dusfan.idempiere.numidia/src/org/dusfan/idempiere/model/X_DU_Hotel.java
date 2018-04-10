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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for DU_Hotel
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_Hotel extends PO implements I_DU_Hotel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180302L;

    /** Standard Constructor */
    public X_DU_Hotel (Properties ctx, int DU_Hotel_ID, String trxName)
    {
      super (ctx, DU_Hotel_ID, trxName);
      /** if (DU_Hotel_ID == 0)
        {
			setDU_Hotel_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_Hotel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_Hotel[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Hotel.
		@param DU_Hotel_ID Hotel	  */
	public void setDU_Hotel_ID (int DU_Hotel_ID)
	{
		if (DU_Hotel_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Hotel_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Hotel_ID, Integer.valueOf(DU_Hotel_ID));
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

	/** Set DU_Hotel_UU.
		@param DU_Hotel_UU DU_Hotel_UU	  */
	public void setDU_Hotel_UU (String DU_Hotel_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DU_Hotel_UU, DU_Hotel_UU);
	}

	/** Get DU_Hotel_UU.
		@return DU_Hotel_UU	  */
	public String getDU_Hotel_UU () 
	{
		return (String)get_Value(COLUMNNAME_DU_Hotel_UU);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Start No.
		@param StartNo 
		Starting number/position
	  */
	public void setStartNo (int StartNo)
	{
		set_Value (COLUMNNAME_StartNo, Integer.valueOf(StartNo));
	}

	/** Get Start No.
		@return Starting number/position
	  */
	public int getStartNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StartNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getStartNo()));
    }

	/** Mekha = 1 */
	public static final String TYPEHOTEL_Mekha = "1";
	/** Medina = 2 */
	public static final String TYPEHOTEL_Medina = "2";
	/** Set Type d'hôtel.
		@param TypeHotel Type d'hôtel	  */
	public void setTypeHotel (String TypeHotel)
	{

		set_Value (COLUMNNAME_TypeHotel, TypeHotel);
	}

	/** Get Type d'hôtel.
		@return Type d'hôtel	  */
	public String getTypeHotel () 
	{
		return (String)get_Value(COLUMNNAME_TypeHotel);
	}
}