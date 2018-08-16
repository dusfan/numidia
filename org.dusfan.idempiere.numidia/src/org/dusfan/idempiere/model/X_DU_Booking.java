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

/** Generated Model for DU_Booking
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_Booking extends PO implements I_DU_Booking, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180813L;

    /** Standard Constructor */
    public X_DU_Booking (Properties ctx, int DU_Booking_ID, String trxName)
    {
      super (ctx, DU_Booking_ID, trxName);
      /** if (DU_Booking_ID == 0)
        {
			setDU_Booking_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_Booking (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_Booking[")
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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_C_Currency getC_PurchaseCurrency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_PurchaseCurrency_ID(), get_TrxName());	}

	/** Set Purchase Currency.
		@param C_PurchaseCurrency_ID 
		The Currency for this record
	  */
	public void setC_PurchaseCurrency_ID (int C_PurchaseCurrency_ID)
	{
		if (C_PurchaseCurrency_ID < 1) 
			set_Value (COLUMNNAME_C_PurchaseCurrency_ID, null);
		else 
			set_Value (COLUMNNAME_C_PurchaseCurrency_ID, Integer.valueOf(C_PurchaseCurrency_ID));
	}

	/** Get Purchase Currency.
		@return The Currency for this record
	  */
	public int getC_PurchaseCurrency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PurchaseCurrency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Invoice getC_PurchaseInvoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_PurchaseInvoice_ID(), get_TrxName());	}

	/** Set Purchase Invoice.
		@param C_PurchaseInvoice_ID 
		Purchase Invoice Identifier
	  */
	public void setC_PurchaseInvoice_ID (int C_PurchaseInvoice_ID)
	{
		if (C_PurchaseInvoice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PurchaseInvoice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PurchaseInvoice_ID, Integer.valueOf(C_PurchaseInvoice_ID));
	}

	/** Get Purchase Invoice.
		@return Purchase Invoice Identifier
	  */
	public int getC_PurchaseInvoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PurchaseInvoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Invoice getC_SalesInvoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_SalesInvoice_ID(), get_TrxName());	}

	/** Set Sales Invoice.
		@param C_SalesInvoice_ID 
		Sales Invoice Identifier
	  */
	public void setC_SalesInvoice_ID (int C_SalesInvoice_ID)
	{
		if (C_SalesInvoice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_SalesInvoice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_SalesInvoice_ID, Integer.valueOf(C_SalesInvoice_ID));
	}

	/** Get Sales Invoice.
		@return Sales Invoice Identifier
	  */
	public int getC_SalesInvoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesInvoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Invoiced.
		@param DateInvoiced 
		Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced)
	{
		set_Value (COLUMNNAME_DateInvoiced, DateInvoiced);
	}

	/** Get Date Invoiced.
		@return Date printed on Invoice
	  */
	public Timestamp getDateInvoiced () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateInvoiced);
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

	/** Set Booking.
		@param DU_Booking_ID Booking	  */
	public void setDU_Booking_ID (int DU_Booking_ID)
	{
		if (DU_Booking_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Booking_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Booking_ID, Integer.valueOf(DU_Booking_ID));
	}

	/** Get Booking.
		@return Booking	  */
	public int getDU_Booking_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Booking_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set LeadPassenger.
		@param LeadPassenger LeadPassenger	  */
	public void setLeadPassenger (String LeadPassenger)
	{
		set_Value (COLUMNNAME_LeadPassenger, LeadPassenger);
	}

	/** Get LeadPassenger.
		@return LeadPassenger	  */
	public String getLeadPassenger () 
	{
		return (String)get_Value(COLUMNNAME_LeadPassenger);
	}

	/** Set Net Sales Price USD.
		@param NetSalesPriceUsd 
		Actual Price 
	  */
	public void setNetSalesPriceUsd (BigDecimal NetSalesPriceUsd)
	{
		set_Value (COLUMNNAME_NetSalesPriceUsd, NetSalesPriceUsd);
	}

	/** Get Net Sales Price USD.
		@return Actual Price 
	  */
	public BigDecimal getNetSalesPriceUsd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NetSalesPriceUsd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Paid Amount.
		@param PaidAmount 
		Paid Amount
	  */
	public void setPaidAmount (BigDecimal PaidAmount)
	{
		throw new IllegalArgumentException ("PaidAmount is virtual column");	}

	/** Get Paid Amount.
		@return Paid Amount
	  */
	public BigDecimal getPaidAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PaidAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pending Payment.
		@param PendingPayment 
		Pending Payment
	  */
	public void setPendingPayment (BigDecimal PendingPayment)
	{
		set_Value (COLUMNNAME_PendingPayment, PendingPayment);
	}

	/** Get Pending Payment.
		@return Pending Payment
	  */
	public BigDecimal getPendingPayment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PendingPayment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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