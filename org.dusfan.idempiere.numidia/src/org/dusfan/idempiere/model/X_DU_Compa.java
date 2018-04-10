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

/** Generated Model for DU_Compa
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_Compa extends PO implements I_DU_Compa, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180302L;

    /** Standard Constructor */
    public X_DU_Compa (Properties ctx, int DU_Compa_ID, String trxName)
    {
      super (ctx, DU_Compa_ID, trxName);
      /** if (DU_Compa_ID == 0)
        {
			setDU_Compa_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DU_Compa (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_Compa[")
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

	/** Set Company.
		@param DU_Compa_ID Company	  */
	public void setDU_Compa_ID (int DU_Compa_ID)
	{
		if (DU_Compa_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Compa_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Compa_ID, Integer.valueOf(DU_Compa_ID));
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

	/** Set DU_Compa_UU.
		@param DU_Compa_UU DU_Compa_UU	  */
	public void setDU_Compa_UU (String DU_Compa_UU)
	{
		set_Value (COLUMNNAME_DU_Compa_UU, DU_Compa_UU);
	}

	/** Get DU_Compa_UU.
		@return DU_Compa_UU	  */
	public String getDU_Compa_UU () 
	{
		return (String)get_Value(COLUMNNAME_DU_Compa_UU);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Name 2.
		@param Name2 
		Additional Name
	  */
	public void setName2 (String Name2)
	{
		set_Value (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2 () 
	{
		return (String)get_Value(COLUMNNAME_Name2);
	}
}