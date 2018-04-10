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

/** Generated Interface for DU_CreatePackage
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DU_CreatePackage 
{

    /** TableName=DU_CreatePackage */
    public static final String Table_Name = "DU_CreatePackage";

    /** AD_Table_ID=1000029 */
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

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By	  */
	public int getCreatedBy();

    /** Column name CreatePackage */
    public static final String COLUMNNAME_CreatePackage = "CreatePackage";

	/** Set Create Package	  */
	public void setCreatePackage (String CreatePackage);

	/** Get Create Package	  */
	public String getCreatePackage();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DU_CreatePackage_ID */
    public static final String COLUMNNAME_DU_CreatePackage_ID = "DU_CreatePackage_ID";

	/** Set CreatePackage	  */
	public void setDU_CreatePackage_ID (int DU_CreatePackage_ID);

	/** Get CreatePackage	  */
	public int getDU_CreatePackage_ID();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active	  */
	public void setIsActive (boolean IsActive);

	/** Get Active	  */
	public boolean isActive();

    /** Column name IsVerified */
    public static final String COLUMNNAME_IsVerified = "IsVerified";

	/** Set Verified.
	  * The BOM configuration has been verified
	  */
	public void setIsVerified (boolean IsVerified);

	/** Get Verified.
	  * The BOM configuration has been verified
	  */
	public boolean isVerified();

    /** Column name M_ProductBI_ID */
    public static final String COLUMNNAME_M_ProductBI_ID = "M_ProductBI_ID";

	/** Set Billet	  */
	public void setM_ProductBI_ID (int M_ProductBI_ID);

	/** Get Billet	  */
	public int getM_ProductBI_ID();

	public org.compiere.model.I_M_Product getM_ProductBI() throws RuntimeException;

    /** Column name M_ProductDO_ID */
    public static final String COLUMNNAME_M_ProductDO_ID = "M_ProductDO_ID";

	/** Set Douane	  */
	public void setM_ProductDO_ID (int M_ProductDO_ID);

	/** Get Douane	  */
	public int getM_ProductDO_ID();

	public org.compiere.model.I_M_Product getM_ProductDO() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_ProductMD_ID */
    public static final String COLUMNNAME_M_ProductMD_ID = "M_ProductMD_ID";

	/** Set Hotel Medina	  */
	public void setM_ProductMD_ID (int M_ProductMD_ID);

	/** Get Hotel Medina	  */
	public int getM_ProductMD_ID();

	public org.compiere.model.I_M_Product getM_ProductMD() throws RuntimeException;

    /** Column name M_ProductV_ID */
    public static final String COLUMNNAME_M_ProductV_ID = "M_ProductV_ID";

	/** Set VISA	  */
	public void setM_ProductV_ID (int M_ProductV_ID);

	/** Get VISA	  */
	public int getM_ProductV_ID();

	public org.compiere.model.I_M_Product getM_ProductV() throws RuntimeException;

    /** Column name M_Sejour_ID */
    public static final String COLUMNNAME_M_Sejour_ID = "M_Sejour_ID";

	/** Set Package	  */
	public void setM_Sejour_ID (int M_Sejour_ID);

	/** Get Package	  */
	public int getM_Sejour_ID();

	public org.compiere.model.I_M_Product getM_Sejour() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NbrDayMedina */
    public static final String COLUMNNAME_NbrDayMedina = "NbrDayMedina";

	/** Set Nbr Jours à Medina	  */
	public void setNbrDayMedina (BigDecimal NbrDayMedina);

	/** Get Nbr Jours à Medina	  */
	public BigDecimal getNbrDayMedina();

    /** Column name NbrDayMekha */
    public static final String COLUMNNAME_NbrDayMekha = "NbrDayMekha";

	/** Set Nbr Jours à Mekha	  */
	public void setNbrDayMekha (BigDecimal NbrDayMekha);

	/** Get Nbr Jours à Mekha	  */
	public BigDecimal getNbrDayMekha();

    /** Column name PriceBi */
    public static final String COLUMNNAME_PriceBi = "PriceBi";

	/** Set Prix Billet	  */
	public void setPriceBi (BigDecimal PriceBi);

	/** Get Prix Billet	  */
	public BigDecimal getPriceBi();

    /** Column name PriceDouane */
    public static final String COLUMNNAME_PriceDouane = "PriceDouane";

	/** Set Prix Douane	  */
	public void setPriceDouane (BigDecimal PriceDouane);

	/** Get Prix Douane	  */
	public BigDecimal getPriceDouane();

    /** Column name PriceLimit */
    public static final String COLUMNNAME_PriceLimit = "PriceLimit";

	/** Set Limit Price.
	  * Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit);

	/** Get Limit Price.
	  * Lowest price for a product
	  */
	public BigDecimal getPriceLimit();

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

    /** Column name PriceMD */
    public static final String COLUMNNAME_PriceMD = "PriceMD";

	/** Set Price Medina	  */
	public void setPriceMD (BigDecimal PriceMD);

	/** Get Price Medina	  */
	public BigDecimal getPriceMD();

    /** Column name PriceMK */
    public static final String COLUMNNAME_PriceMK = "PriceMK";

	/** Set Prix Mekha	  */
	public void setPriceMK (BigDecimal PriceMK);

	/** Get Prix Mekha	  */
	public BigDecimal getPriceMK();

    /** Column name PriceStd */
    public static final String COLUMNNAME_PriceStd = "PriceStd";

	/** Set Standard Price.
	  * Standard Price
	  */
	public void setPriceStd (BigDecimal PriceStd);

	/** Get Standard Price.
	  * Standard Price
	  */
	public BigDecimal getPriceStd();

    /** Column name PriceVisa */
    public static final String COLUMNNAME_PriceVisa = "PriceVisa";

	/** Set Prix Visa	  */
	public void setPriceVisa (BigDecimal PriceVisa);

	/** Get Prix Visa	  */
	public BigDecimal getPriceVisa();

    /** Column name Rate */
    public static final String COLUMNNAME_Rate = "Rate";

	/** Set Rate.
	  * Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate);

	/** Get Rate.
	  * Rate or Tax or Exchange
	  */
	public BigDecimal getRate();

    /** Column name TotalMD */
    public static final String COLUMNNAME_TotalMD = "TotalMD";

	/** Set Total Medina	  */
	public void setTotalMD (BigDecimal TotalMD);

	/** Get Total Medina	  */
	public BigDecimal getTotalMD();

    /** Column name TotalMK */
    public static final String COLUMNNAME_TotalMK = "TotalMK";

	/** Set Total Mekha	  */
	public void setTotalMK (BigDecimal TotalMK);

	/** Get Total Mekha	  */
	public BigDecimal getTotalMK();

    /** Column name TypeRoom */
    public static final String COLUMNNAME_TypeRoom = "TypeRoom";

	/** Set Type de chambre	  */
	public void setTypeRoom (String TypeRoom);

	/** Get Type de chambre	  */
	public String getTypeRoom();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By	  */
	public int getUpdatedBy();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
