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

/** Generated Model for DU_POSARLine
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_POSARLine extends PO implements I_DU_POSARLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180524L;

    /** Standard Constructor */
    public X_DU_POSARLine (Properties ctx, int DU_POSARLine_ID, String trxName)
    {
      super (ctx, DU_POSARLine_ID, trxName);
      /** if (DU_POSARLine_ID == 0)
        {
			setDU_POSARLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_POSARLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_POSARLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amt 
		Amount
	  */
	public void setAmt (BigDecimal Amt)
	{
		set_Value (COLUMNNAME_Amt, Amt);
	}

	/** Get Amount.
		@return Amount
	  */
	public BigDecimal getAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public I_DU_POSAR getDU_POSAR() throws RuntimeException
    {
		return (I_DU_POSAR)MTable.get(getCtx(), I_DU_POSAR.Table_Name)
			.getPO(getDU_POSAR_ID(), get_TrxName());	}

	/** Set Caisse SAR.
		@param DU_POSAR_ID Caisse SAR	  */
	public void setDU_POSAR_ID (int DU_POSAR_ID)
	{
		if (DU_POSAR_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_POSAR_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_POSAR_ID, Integer.valueOf(DU_POSAR_ID));
	}

	/** Get Caisse SAR.
		@return Caisse SAR	  */
	public int getDU_POSAR_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_POSAR_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set POSARLine.
		@param DU_POSARLine_ID POSARLine	  */
	public void setDU_POSARLine_ID (int DU_POSARLine_ID)
	{
		if (DU_POSARLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_POSARLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_POSARLine_ID, Integer.valueOf(DU_POSARLine_ID));
	}

	/** Get POSARLine.
		@return POSARLine	  */
	public int getDU_POSARLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_POSARLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}