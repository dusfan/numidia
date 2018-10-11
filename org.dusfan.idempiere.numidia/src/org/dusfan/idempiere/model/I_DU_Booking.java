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

/** Generated Interface for DU_Booking
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DU_Booking 
{

    /** TableName=DU_Booking */
    public static final String Table_Name = "DU_Booking";

    /** AD_Table_ID=1000045 */
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

    /** Column name AgencyProfit */
    public static final String COLUMNNAME_AgencyProfit = "AgencyProfit";

	/** Set Agency Profit.
	  * Agency Profit
	  */
	public void setAgencyProfit (BigDecimal AgencyProfit);

	/** Get Agency Profit.
	  * Agency Profit
	  */
	public BigDecimal getAgencyProfit();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name CommissionAmt */
    public static final String COLUMNNAME_CommissionAmt = "CommissionAmt";

	/** Set Commission Amount.
	  * Commission Amount
	  */
	public void setCommissionAmt (BigDecimal CommissionAmt);

	/** Get Commission Amount.
	  * Commission Amount
	  */
	public BigDecimal getCommissionAmt();

    /** Column name CostPrice */
    public static final String COLUMNNAME_CostPrice = "CostPrice";

	/** Set Cost Price.
	  * Cost Price
	  */
	public void setCostPrice (BigDecimal CostPrice);

	/** Get Cost Price.
	  * Cost Price
	  */
	public BigDecimal getCostPrice();

    /** Column name C_PurchaseCurrency_ID */
    public static final String COLUMNNAME_C_PurchaseCurrency_ID = "C_PurchaseCurrency_ID";

	/** Set Purchase Currency.
	  * The Currency for this record
	  */
	public void setC_PurchaseCurrency_ID (int C_PurchaseCurrency_ID);

	/** Get Purchase Currency.
	  * The Currency for this record
	  */
	public int getC_PurchaseCurrency_ID();

	public org.compiere.model.I_C_Currency getC_PurchaseCurrency() throws RuntimeException;

    /** Column name C_PurchaseInvoice_ID */
    public static final String COLUMNNAME_C_PurchaseInvoice_ID = "C_PurchaseInvoice_ID";

	/** Set Purchase Invoice.
	  * Purchase Invoice Identifier
	  */
	public void setC_PurchaseInvoice_ID (int C_PurchaseInvoice_ID);

	/** Get Purchase Invoice.
	  * Purchase Invoice Identifier
	  */
	public int getC_PurchaseInvoice_ID();

	public org.compiere.model.I_C_Invoice getC_PurchaseInvoice() throws RuntimeException;

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

    /** Column name C_SalesInvoice_ID */
    public static final String COLUMNNAME_C_SalesInvoice_ID = "C_SalesInvoice_ID";

	/** Set Sales Invoice.
	  * Sales Invoice Identifier
	  */
	public void setC_SalesInvoice_ID (int C_SalesInvoice_ID);

	/** Get Sales Invoice.
	  * Sales Invoice Identifier
	  */
	public int getC_SalesInvoice_ID();

	public org.compiere.model.I_C_Invoice getC_SalesInvoice() throws RuntimeException;

    /** Column name DateInvoiced */
    public static final String COLUMNNAME_DateInvoiced = "DateInvoiced";

	/** Set Date Invoiced.
	  * Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced);

	/** Get Date Invoiced.
	  * Date printed on Invoice
	  */
	public Timestamp getDateInvoiced();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name DU_Booking_ID */
    public static final String COLUMNNAME_DU_Booking_ID = "DU_Booking_ID";

	/** Set Booking	  */
	public void setDU_Booking_ID (int DU_Booking_ID);

	/** Get Booking	  */
	public int getDU_Booking_ID();

    /** Column name DU_Booking_UU */
    public static final String COLUMNNAME_DU_Booking_UU = "DU_Booking_UU";

	/** Set DU_Booking_UU	  */
	public void setDU_Booking_UU (String DU_Booking_UU);

	/** Get DU_Booking_UU	  */
	public String getDU_Booking_UU();

    /** Column name GeneralProfit */
    public static final String COLUMNNAME_GeneralProfit = "GeneralProfit";

	/** Set General Profit.
	  * General Profit
	  */
	public void setGeneralProfit (BigDecimal GeneralProfit);

	/** Get General Profit.
	  * General Profit
	  */
	public BigDecimal getGeneralProfit();

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

    /** Column name isPaidByCard */
    public static final String COLUMNNAME_isPaidByCard = "isPaidByCard";

	/** Set isPaidByCard	  */
	public void setisPaidByCard (boolean isPaidByCard);

	/** Get isPaidByCard	  */
	public boolean isPaidByCard();

    /** Column name LeadPassenger */
    public static final String COLUMNNAME_LeadPassenger = "LeadPassenger";

	/** Set LeadPassenger	  */
	public void setLeadPassenger (String LeadPassenger);

	/** Get LeadPassenger	  */
	public String getLeadPassenger();

    /** Column name NetSalesPriceUsd */
    public static final String COLUMNNAME_NetSalesPriceUsd = "NetSalesPriceUsd";

	/** Set Net Sales Price.
	  * Actual Price 
	  */
	public void setNetSalesPriceUsd (BigDecimal NetSalesPriceUsd);

	/** Get Net Sales Price.
	  * Actual Price 
	  */
	public BigDecimal getNetSalesPriceUsd();

    /** Column name PaidAmount */
    public static final String COLUMNNAME_PaidAmount = "PaidAmount";

	/** Set Paid Amount.
	  * Paid Amount
	  */
	public void setPaidAmount (BigDecimal PaidAmount);

	/** Get Paid Amount.
	  * Paid Amount
	  */
	public BigDecimal getPaidAmount();

    /** Column name PendingPayment */
    public static final String COLUMNNAME_PendingPayment = "PendingPayment";

	/** Set Pending Payment.
	  * Pending Payment
	  */
	public void setPendingPayment (BigDecimal PendingPayment);

	/** Get Pending Payment.
	  * Pending Payment
	  */
	public BigDecimal getPendingPayment();

    /** Column name PriceActual */
    public static final String COLUMNNAME_PriceActual = "PriceActual";

	/** Set Unit Price.
	  * Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual);

	/** Get Unit Price.
	  * Actual Price 
	  */
	public BigDecimal getPriceActual();

    /** Column name PriceActualNet */
    public static final String COLUMNNAME_PriceActualNet = "PriceActualNet";

	/** Set Unit Price.
	  * Actual Price 
	  */
	public void setPriceActualNet (BigDecimal PriceActualNet);

	/** Get Unit Price.
	  * Actual Price 
	  */
	public BigDecimal getPriceActualNet();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ReceiptAmount */
    public static final String COLUMNNAME_ReceiptAmount = "ReceiptAmount";

	/** Set Receipt Amount.
	  * Receipt Amount
	  */
	public void setReceiptAmount (BigDecimal ReceiptAmount);

	/** Get Receipt Amount.
	  * Receipt Amount
	  */
	public BigDecimal getReceiptAmount();

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
}
