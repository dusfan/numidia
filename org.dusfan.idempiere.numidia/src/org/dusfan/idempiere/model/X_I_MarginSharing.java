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

/** Generated Model for I_MarginSharing
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_I_MarginSharing extends PO implements I_I_MarginSharing, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180813L;

    /** Standard Constructor */
    public X_I_MarginSharing (Properties ctx, int I_MarginSharing_ID, String trxName)
    {
      super (ctx, I_MarginSharing_ID, trxName);
      /** if (I_MarginSharing_ID == 0)
        {
			setI_MarginSharing_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_MarginSharing (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_MarginSharing[")
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

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cost Price.
		@param CostPrice 
		Cost Price
	  */
	public void setCostPrice (BigDecimal CostPrice)
	{
		set_Value (COLUMNNAME_CostPrice, CostPrice);
	}

	/** Get Cost Price.
		@return Cost Price
	  */
	public BigDecimal getCostPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Currency.
		@param Currency Currency	  */
	public void setCurrency (String Currency)
	{
		set_Value (COLUMNNAME_Currency, Currency);
	}

	/** Get Currency.
		@return Currency	  */
	public String getCurrency () 
	{
		return (String)get_Value(COLUMNNAME_Currency);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set General Profit.
		@param GeneralProfit 
		General Profit
	  */
	public void setGeneralProfit (BigDecimal GeneralProfit)
	{
		set_Value (COLUMNNAME_GeneralProfit, GeneralProfit);
	}

	/** Get General Profit.
		@return General Profit
	  */
	public BigDecimal getGeneralProfit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GeneralProfit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set MarginSharing.
		@param I_MarginSharing_ID MarginSharing	  */
	public void setI_MarginSharing_ID (int I_MarginSharing_ID)
	{
		if (I_MarginSharing_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_MarginSharing_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_MarginSharing_ID, Integer.valueOf(I_MarginSharing_ID));
	}

	/** Get MarginSharing.
		@return MarginSharing	  */
	public int getI_MarginSharing_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_MarginSharing_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Net Sales Price.
		@param NetSalesPriceUsd 
		Actual Price 
	  */
	public void setNetSalesPriceUsd (BigDecimal NetSalesPriceUsd)
	{
		set_Value (COLUMNNAME_NetSalesPriceUsd, NetSalesPriceUsd);
	}

	/** Get Net Sales Price.
		@return Actual Price 
	  */
	public BigDecimal getNetSalesPriceUsd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NetSalesPriceUsd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Unit Price.
		@param PriceActualNet 
		Actual Price 
	  */
	public void setPriceActualNet (BigDecimal PriceActualNet)
	{
		set_ValueNoCheck (COLUMNNAME_PriceActualNet, PriceActualNet);
	}

	/** Get Unit Price.
		@return Actual Price 
	  */
	public BigDecimal getPriceActualNet () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceActualNet);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Receipt Amount.
		@param ReceiptAmount 
		Receipt Amount
	  */
	public void setReceiptAmount (BigDecimal ReceiptAmount)
	{
		set_Value (COLUMNNAME_ReceiptAmount, ReceiptAmount);
	}

	/** Get Receipt Amount.
		@return Receipt Amount
	  */
	public BigDecimal getReceiptAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ReceiptAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}