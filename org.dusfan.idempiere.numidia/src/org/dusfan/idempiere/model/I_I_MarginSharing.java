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

/** Generated Interface for I_MarginSharing
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_I_MarginSharing 
{

    /** TableName=I_MarginSharing */
    public static final String Table_Name = "I_MarginSharing";

    /** AD_Table_ID=1000048 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization	  */
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

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By	  */
	public int getCreatedBy();

    /** Column name Currency */
    public static final String COLUMNNAME_Currency = "Currency";

	/** Set Currency	  */
	public void setCurrency (String Currency);

	/** Get Currency	  */
	public String getCurrency();

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

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name I_MarginSharing_ID */
    public static final String COLUMNNAME_I_MarginSharing_ID = "I_MarginSharing_ID";

	/** Set MarginSharing	  */
	public void setI_MarginSharing_ID (int I_MarginSharing_ID);

	/** Get MarginSharing	  */
	public int getI_MarginSharing_ID();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active	  */
	public void setIsActive (boolean IsActive);

	/** Get Active	  */
	public boolean isActive();

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

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

	/** Get Updated	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By	  */
	public int getUpdatedBy();
}
