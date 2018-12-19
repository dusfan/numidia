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

/** Generated Model for DU_Service
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_Service extends PO implements I_DU_Service, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181206L;

    /** Standard Constructor */
    public X_DU_Service (Properties ctx, int DU_Service_ID, String trxName)
    {
      super (ctx, DU_Service_ID, trxName);
      /** if (DU_Service_ID == 0)
        {
			setDU_Service_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_Service (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_Service[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Agency Profit.
		@param AgencyProfit 
		Agency Profit
	  */
	public void setAgencyProfit (BigDecimal AgencyProfit)
	{
		set_Value (COLUMNNAME_AgencyProfit, AgencyProfit);
	}

	/** Get Agency Profit.
		@return Agency Profit
	  */
	public BigDecimal getAgencyProfit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AgencyProfit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** RO = RO */
	public static final String BOARD_RO = "RO";
	/** BB = BB */
	public static final String BOARD_BB = "BB";
	/** HB = HB */
	public static final String BOARD_HB = "HB";
	/** FB = FB */
	public static final String BOARD_FB = "FB";
	/** Set Board.
		@param Board Board	  */
	public void setBoard (String Board)
	{

		set_Value (COLUMNNAME_Board, Board);
	}

	/** Get Board.
		@return Board	  */
	public String getBoard () 
	{
		return (String)get_Value(COLUMNNAME_Board);
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

	/** Set Service.
		@param DU_Service_ID Service	  */
	public void setDU_Service_ID (int DU_Service_ID)
	{
		if (DU_Service_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Service_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Service_ID, Integer.valueOf(DU_Service_ID));
	}

	/** Get Service.
		@return Service	  */
	public int getDU_Service_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Service_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DU_ServiceType getDU_ServiceType() throws RuntimeException
    {
		return (I_DU_ServiceType)MTable.get(getCtx(), I_DU_ServiceType.Table_Name)
			.getPO(getDU_ServiceType_ID(), get_TrxName());	}

	/** Set ServiceType.
		@param DU_ServiceType_ID ServiceType	  */
	public void setDU_ServiceType_ID (int DU_ServiceType_ID)
	{
		if (DU_ServiceType_ID < 1) 
			set_Value (COLUMNNAME_DU_ServiceType_ID, null);
		else 
			set_Value (COLUMNNAME_DU_ServiceType_ID, Integer.valueOf(DU_ServiceType_ID));
	}

	/** Get ServiceType.
		@return ServiceType	  */
	public int getDU_ServiceType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_ServiceType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unit Price.
		@param PriceActual 
		Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual)
	{
		set_Value (COLUMNNAME_PriceActual, PriceActual);
	}

	/** Get Unit Price.
		@return Actual Price 
	  */
	public BigDecimal getPriceActual () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceActual);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set PriceEuro.
		@param PriceEuro PriceEuro	  */
	public void setPriceEuro (BigDecimal PriceEuro)
	{
		set_Value (COLUMNNAME_PriceEuro, PriceEuro);
	}

	/** Get PriceEuro.
		@return PriceEuro	  */
	public BigDecimal getPriceEuro () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceEuro);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set SalesPrice.
		@param SalesPrice SalesPrice	  */
	public void setSalesPrice (BigDecimal SalesPrice)
	{
		set_Value (COLUMNNAME_SalesPrice, SalesPrice);
	}

	/** Get SalesPrice.
		@return SalesPrice	  */
	public BigDecimal getSalesPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SalesPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** SV = SV */
	public static final String VIEW_SV = "SV";
	/** GV = GV */
	public static final String VIEW_GV = "GV";
	/** CV = CV */
	public static final String VIEW_CV = "CV";
	/** Set View.
		@param View View	  */
	public void setView (String View)
	{

		set_Value (COLUMNNAME_View, View);
	}

	/** Get View.
		@return View	  */
	public String getView () 
	{
		return (String)get_Value(COLUMNNAME_View);
	}
}