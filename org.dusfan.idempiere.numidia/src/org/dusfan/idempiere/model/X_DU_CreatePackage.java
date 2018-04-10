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

/** Generated Model for DU_CreatePackage
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_CreatePackage extends PO implements I_DU_CreatePackage, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180330L;

    /** Standard Constructor */
    public X_DU_CreatePackage (Properties ctx, int DU_CreatePackage_ID, String trxName)
    {
      super (ctx, DU_CreatePackage_ID, trxName);
      /** if (DU_CreatePackage_ID == 0)
        {
			setDU_CreatePackage_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_CreatePackage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_CreatePackage[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Create Package.
		@param CreatePackage Create Package	  */
	public void setCreatePackage (String CreatePackage)
	{
		set_Value (COLUMNNAME_CreatePackage, CreatePackage);
	}

	/** Get Create Package.
		@return Create Package	  */
	public String getCreatePackage () 
	{
		return (String)get_Value(COLUMNNAME_CreatePackage);
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

	/** Set CreatePackage.
		@param DU_CreatePackage_ID CreatePackage	  */
	public void setDU_CreatePackage_ID (int DU_CreatePackage_ID)
	{
		if (DU_CreatePackage_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_CreatePackage_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_CreatePackage_ID, Integer.valueOf(DU_CreatePackage_ID));
	}

	/** Get CreatePackage.
		@return CreatePackage	  */
	public int getDU_CreatePackage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_CreatePackage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Verified.
		@param IsVerified 
		The BOM configuration has been verified
	  */
	public void setIsVerified (boolean IsVerified)
	{
		set_ValueNoCheck (COLUMNNAME_IsVerified, Boolean.valueOf(IsVerified));
	}

	/** Get Verified.
		@return The BOM configuration has been verified
	  */
	public boolean isVerified () 
	{
		Object oo = get_Value(COLUMNNAME_IsVerified);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getM_ProductBI() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_ProductBI_ID(), get_TrxName());	}

	/** Set Billet.
		@param M_ProductBI_ID Billet	  */
	public void setM_ProductBI_ID (int M_ProductBI_ID)
	{
		if (M_ProductBI_ID < 1) 
			set_Value (COLUMNNAME_M_ProductBI_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductBI_ID, Integer.valueOf(M_ProductBI_ID));
	}

	/** Get Billet.
		@return Billet	  */
	public int getM_ProductBI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductBI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_ProductDO() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_ProductDO_ID(), get_TrxName());	}

	/** Set Douane.
		@param M_ProductDO_ID Douane	  */
	public void setM_ProductDO_ID (int M_ProductDO_ID)
	{
		if (M_ProductDO_ID < 1) 
			set_Value (COLUMNNAME_M_ProductDO_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductDO_ID, Integer.valueOf(M_ProductDO_ID));
	}

	/** Get Douane.
		@return Douane	  */
	public int getM_ProductDO_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductDO_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_ProductMD() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_ProductMD_ID(), get_TrxName());	}

	/** Set Hotel Medina.
		@param M_ProductMD_ID Hotel Medina	  */
	public void setM_ProductMD_ID (int M_ProductMD_ID)
	{
		if (M_ProductMD_ID < 1) 
			set_Value (COLUMNNAME_M_ProductMD_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductMD_ID, Integer.valueOf(M_ProductMD_ID));
	}

	/** Get Hotel Medina.
		@return Hotel Medina	  */
	public int getM_ProductMD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductMD_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_ProductV() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_ProductV_ID(), get_TrxName());	}

	/** Set VISA.
		@param M_ProductV_ID VISA	  */
	public void setM_ProductV_ID (int M_ProductV_ID)
	{
		if (M_ProductV_ID < 1) 
			set_Value (COLUMNNAME_M_ProductV_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductV_ID, Integer.valueOf(M_ProductV_ID));
	}

	/** Get VISA.
		@return VISA	  */
	public int getM_ProductV_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductV_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Sejour() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Sejour_ID(), get_TrxName());	}

	/** Set Package.
		@param M_Sejour_ID Package	  */
	public void setM_Sejour_ID (int M_Sejour_ID)
	{
		if (M_Sejour_ID < 1) 
			set_Value (COLUMNNAME_M_Sejour_ID, null);
		else 
			set_Value (COLUMNNAME_M_Sejour_ID, Integer.valueOf(M_Sejour_ID));
	}

	/** Get Package.
		@return Package	  */
	public int getM_Sejour_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Sejour_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Nbr Jours à Medina.
		@param NbrDayMedina Nbr Jours à Medina	  */
	public void setNbrDayMedina (BigDecimal NbrDayMedina)
	{
		set_Value (COLUMNNAME_NbrDayMedina, NbrDayMedina);
	}

	/** Get Nbr Jours à Medina.
		@return Nbr Jours à Medina	  */
	public BigDecimal getNbrDayMedina () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NbrDayMedina);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Nbr Jours à Mekha.
		@param NbrDayMekha Nbr Jours à Mekha	  */
	public void setNbrDayMekha (BigDecimal NbrDayMekha)
	{
		set_Value (COLUMNNAME_NbrDayMekha, NbrDayMekha);
	}

	/** Get Nbr Jours à Mekha.
		@return Nbr Jours à Mekha	  */
	public BigDecimal getNbrDayMekha () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NbrDayMekha);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Prix Billet.
		@param PriceBi Prix Billet	  */
	public void setPriceBi (BigDecimal PriceBi)
	{
		set_Value (COLUMNNAME_PriceBi, PriceBi);
	}

	/** Get Prix Billet.
		@return Prix Billet	  */
	public BigDecimal getPriceBi () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceBi);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Prix Douane.
		@param PriceDouane Prix Douane	  */
	public void setPriceDouane (BigDecimal PriceDouane)
	{
		set_Value (COLUMNNAME_PriceDouane, PriceDouane);
	}

	/** Get Prix Douane.
		@return Prix Douane	  */
	public BigDecimal getPriceDouane () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceDouane);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Limit Price.
		@param PriceLimit 
		Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit)
	{
		set_Value (COLUMNNAME_PriceLimit, PriceLimit);
	}

	/** Get Limit Price.
		@return Lowest price for a product
	  */
	public BigDecimal getPriceLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Price Medina.
		@param PriceMD Price Medina	  */
	public void setPriceMD (BigDecimal PriceMD)
	{
		set_Value (COLUMNNAME_PriceMD, PriceMD);
	}

	/** Get Price Medina.
		@return Price Medina	  */
	public BigDecimal getPriceMD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceMD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Prix Mekha.
		@param PriceMK Prix Mekha	  */
	public void setPriceMK (BigDecimal PriceMK)
	{
		set_Value (COLUMNNAME_PriceMK, PriceMK);
	}

	/** Get Prix Mekha.
		@return Prix Mekha	  */
	public BigDecimal getPriceMK () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceMK);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Standard Price.
		@param PriceStd 
		Standard Price
	  */
	public void setPriceStd (BigDecimal PriceStd)
	{
		set_Value (COLUMNNAME_PriceStd, PriceStd);
	}

	/** Get Standard Price.
		@return Standard Price
	  */
	public BigDecimal getPriceStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceStd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Prix Visa.
		@param PriceVisa Prix Visa	  */
	public void setPriceVisa (BigDecimal PriceVisa)
	{
		set_Value (COLUMNNAME_PriceVisa, PriceVisa);
	}

	/** Get Prix Visa.
		@return Prix Visa	  */
	public BigDecimal getPriceVisa () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceVisa);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_ValueNoCheck (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Medina.
		@param TotalMD Total Medina	  */
	public void setTotalMD (BigDecimal TotalMD)
	{
		set_Value (COLUMNNAME_TotalMD, TotalMD);
	}

	/** Get Total Medina.
		@return Total Medina	  */
	public BigDecimal getTotalMD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalMD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Mekha.
		@param TotalMK Total Mekha	  */
	public void setTotalMK (BigDecimal TotalMK)
	{
		set_Value (COLUMNNAME_TotalMK, TotalMK);
	}

	/** Get Total Mekha.
		@return Total Mekha	  */
	public BigDecimal getTotalMK () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalMK);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Chambre double = 20 */
	public static final String TYPEROOM_ChambreDouble = "20";
	/** Chambre cinq = 50 */
	public static final String TYPEROOM_ChambreCinq = "50";
	/** Chambre quadruple = 40 */
	public static final String TYPEROOM_ChambreQuadruple = "40";
	/** Chambre six = 60 */
	public static final String TYPEROOM_ChambreSix = "60";
	/** Chambre single = 10 */
	public static final String TYPEROOM_ChambreSingle = "10";
	/** Chambre triple = 30 */
	public static final String TYPEROOM_ChambreTriple = "30";
	/** Chambre Seven = 70 */
	public static final String TYPEROOM_ChambreSeven = "70";
	/** Chambre Huit = 80 */
	public static final String TYPEROOM_ChambreHuit = "80";
	/** INF = 90 */
	public static final String TYPEROOM_INF = "90";
	/** CHD sans LIT = 91 */
	public static final String TYPEROOM_CHDSansLIT = "91";
	/** Guide Numidia = 92 */
	public static final String TYPEROOM_GuideNumidia = "92";
	/** Gratuite Guide Sous Traitons = 93 */
	public static final String TYPEROOM_GratuiteGuideSousTraitons = "93";
	/** Gratuite Agence = 94 */
	public static final String TYPEROOM_GratuiteAgence = "94";
	/** Set Type de chambre.
		@param TypeRoom Type de chambre	  */
	public void setTypeRoom (String TypeRoom)
	{

		set_Value (COLUMNNAME_TypeRoom, TypeRoom);
	}

	/** Get Type de chambre.
		@return Type de chambre	  */
	public String getTypeRoom () 
	{
		return (String)get_Value(COLUMNNAME_TypeRoom);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}