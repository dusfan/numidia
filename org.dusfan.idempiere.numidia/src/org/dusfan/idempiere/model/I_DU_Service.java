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

/** Generated Interface for DU_Service
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DU_Service 
{

    /** TableName=DU_Service */
    public static final String Table_Name = "DU_Service";

    /** AD_Table_ID=1000056 */
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

    /** Column name Board */
    public static final String COLUMNNAME_Board = "Board";

	/** Set Board	  */
	public void setBoard (String Board);

	/** Get Board	  */
	public String getBoard();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By	  */
	public int getCreatedBy();

    /** Column name DU_Hotel_ID */
    public static final String COLUMNNAME_DU_Hotel_ID = "DU_Hotel_ID";

	/** Set Hotel	  */
	public void setDU_Hotel_ID (int DU_Hotel_ID);

	/** Get Hotel	  */
	public int getDU_Hotel_ID();

	public I_DU_Hotel getDU_Hotel() throws RuntimeException;

    /** Column name DU_Service_ID */
    public static final String COLUMNNAME_DU_Service_ID = "DU_Service_ID";

	/** Set Service	  */
	public void setDU_Service_ID (int DU_Service_ID);

	/** Get Service	  */
	public int getDU_Service_ID();

    /** Column name DU_ServiceType_ID */
    public static final String COLUMNNAME_DU_ServiceType_ID = "DU_ServiceType_ID";

	/** Set ServiceType	  */
	public void setDU_ServiceType_ID (int DU_ServiceType_ID);

	/** Get ServiceType	  */
	public int getDU_ServiceType_ID();

	public I_DU_ServiceType getDU_ServiceType() throws RuntimeException;

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active	  */
	public void setIsActive (boolean IsActive);

	/** Get Active	  */
	public boolean isActive();

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

    /** Column name PriceEuro */
    public static final String COLUMNNAME_PriceEuro = "PriceEuro";

	/** Set PriceEuro	  */
	public void setPriceEuro (BigDecimal PriceEuro);

	/** Get PriceEuro	  */
	public BigDecimal getPriceEuro();

    /** Column name SalesPrice */
    public static final String COLUMNNAME_SalesPrice = "SalesPrice";

	/** Set SalesPrice	  */
	public void setSalesPrice (BigDecimal SalesPrice);

	/** Get SalesPrice	  */
	public BigDecimal getSalesPrice();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By	  */
	public int getUpdatedBy();

    /** Column name View */
    public static final String COLUMNNAME_View = "View";

	/** Set View	  */
	public void setView (String View);

	/** Get View	  */
	public String getView();
}
