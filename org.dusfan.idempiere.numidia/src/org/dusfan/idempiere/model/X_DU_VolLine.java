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
import org.compiere.util.KeyNamePair;

/** Generated Model for DU_VolLine
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_VolLine extends PO implements I_DU_VolLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180313L;

    /** Standard Constructor */
    public X_DU_VolLine (Properties ctx, int DU_VolLine_ID, String trxName)
    {
      super (ctx, DU_VolLine_ID, trxName);
      /** if (DU_VolLine_ID == 0)
        {
			setDU_VolLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_VolLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_VolLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_C_BPartner getC_BPartnerRelation() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartnerRelation_ID(), get_TrxName());	}

	/** Set Related Partner.
		@param C_BPartnerRelation_ID 
		Related Business Partner
	  */
	public void setC_BPartnerRelation_ID (int C_BPartnerRelation_ID)
	{
		if (C_BPartnerRelation_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerRelation_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerRelation_ID, Integer.valueOf(C_BPartnerRelation_ID));
	}

	/** Get Related Partner.
		@return Related Business Partner
	  */
	public int getC_BPartnerRelation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerRelation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_BPartnerRelation_ID()));
    }

	public org.compiere.model.I_C_BPartner getC_BP_Guide() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BP_Guide_ID(), get_TrxName());	}

	/** Set Guide.
		@param C_BP_Guide_ID Guide	  */
	public void setC_BP_Guide_ID (int C_BP_Guide_ID)
	{
		throw new IllegalArgumentException ("C_BP_Guide_ID is virtual column");	}

	/** Get Guide.
		@return Guide	  */
	public int getC_BP_Guide_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Guide_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BP_Mohrem() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BP_Mohrem_ID(), get_TrxName());	}

	/** Set Mohrem.
		@param C_BP_Mohrem_ID Mohrem	  */
	public void setC_BP_Mohrem_ID (int C_BP_Mohrem_ID)
	{
		if (C_BP_Mohrem_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Mohrem_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Mohrem_ID, Integer.valueOf(C_BP_Mohrem_ID));
	}

	/** Get Mohrem.
		@return Mohrem	  */
	public int getC_BP_Mohrem_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Mohrem_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_DU_Vol getDU_Vol() throws RuntimeException
    {
		return (I_DU_Vol)MTable.get(getCtx(), I_DU_Vol.Table_Name)
			.getPO(getDU_Vol_ID(), get_TrxName());	}

	/** Set Vol.
		@param DU_Vol_ID Vol	  */
	public void setDU_Vol_ID (int DU_Vol_ID)
	{
		if (DU_Vol_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Vol_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Vol_ID, Integer.valueOf(DU_Vol_ID));
	}

	/** Get Vol.
		@return Vol	  */
	public int getDU_Vol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Vol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set VolLine.
		@param DU_VolLine_ID VolLine	  */
	public void setDU_VolLine_ID (int DU_VolLine_ID)
	{
		if (DU_VolLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_VolLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_VolLine_ID, Integer.valueOf(DU_VolLine_ID));
	}

	/** Get VolLine.
		@return VolLine	  */
	public int getDU_VolLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_VolLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DU_VolLine_UU.
		@param DU_VolLine_UU DU_VolLine_UU	  */
	public void setDU_VolLine_UU (String DU_VolLine_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DU_VolLine_UU, DU_VolLine_UU);
	}

	/** Get DU_VolLine_UU.
		@return DU_VolLine_UU	  */
	public String getDU_VolLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_DU_VolLine_UU);
	}

	/** Set Gratuitee.
		@param isFree Gratuitee	  */
	public void setisFree (boolean isFree)
	{
		set_Value (COLUMNNAME_isFree, Boolean.valueOf(isFree));
	}

	/** Get Gratuitee.
		@return Gratuitee	  */
	public boolean isFree () 
	{
		Object oo = get_Value(COLUMNNAME_isFree);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Included.
		@param IsInclude 
		Defines whether this content / template is included into another one
	  */
	public void setIsInclude (boolean IsInclude)
	{
		set_Value (COLUMNNAME_IsInclude, Boolean.valueOf(IsInclude));
	}

	/** Get Included.
		@return Defines whether this content / template is included into another one
	  */
	public boolean isInclude () 
	{
		Object oo = get_Value(COLUMNNAME_IsInclude);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_ValueNoCheck (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** الابن = 1 */
	public static final String LINKMOHREM_الابن = "1";
	/** اﻷب = 3 */
	public static final String LINKMOHREM_اﻷب = "3";
	/** اﻷخ = 5 */
	public static final String LINKMOHREM_اﻷخ = "5";
	/** الزوج = 7 */
	public static final String LINKMOHREM_الزوج = "7";
	/** الجد = 9 */
	public static final String LINKMOHREM_الجد = "9";
	/** ابن اﻷخ = 11 */
	public static final String LINKMOHREM_ابناﻷخ = "11";
	/** ابن اﻷخت = 12 */
	public static final String LINKMOHREM_ابناﻷخت = "12";
	/** صهر الزوج = 13 */
	public static final String LINKMOHREM_صهرالزوج = "13";
	/** صهر الزوجة = 14 */
	public static final String LINKMOHREM_صهرالزوجة = "14";
	/** عصبة نساء = 15 */
	public static final String LINKMOHREM_عصبةنساء = "15";
	/** الحفيد = 16 */
	public static final String LINKMOHREM_الحفيد = "16";
	/** الخال = 18 */
	public static final String LINKMOHREM_الخال = "18";
	/** العم = 20 */
	public static final String LINKMOHREM_العم = "20";
	/** الحمو = 22 */
	public static final String LINKMOHREM_الحمو = "22";
	/** زوج البنت = 23 */
	public static final String LINKMOHREM_زوجالبنت = "23";
	/** زوج اﻷب = 24 */
	public static final String LINKMOHREM_زوجاﻷب = "24";
	/** أخرى = 99 */
	public static final String LINKMOHREM_أخرى = "99";
	/** Set Type de parenté.
		@param LinkMohrem Type de parenté	  */
	public void setLinkMohrem (String LinkMohrem)
	{

		set_Value (COLUMNNAME_LinkMohrem, LinkMohrem);
	}

	/** Get Type de parenté.
		@return Type de parenté	  */
	public String getLinkMohrem () 
	{
		return (String)get_Value(COLUMNNAME_LinkMohrem);
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

	/** Set Afféctation Pélrin.
		@param SetAffectation Afféctation Pélrin	  */
	public void setSetAffectation (String SetAffectation)
	{
		set_Value (COLUMNNAME_SetAffectation, SetAffectation);
	}

	/** Get Afféctation Pélrin.
		@return Afféctation Pélrin	  */
	public String getSetAffectation () 
	{
		return (String)get_Value(COLUMNNAME_SetAffectation);
	}

	/** Set Choir les gratuitées.
		@param SetGratuiteSV Choir les gratuitées	  */
	public void setSetGratuiteSV (String SetGratuiteSV)
	{
		set_Value (COLUMNNAME_SetGratuiteSV, SetGratuiteSV);
	}

	/** Get Choir les gratuitées.
		@return Choir les gratuitées	  */
	public String getSetGratuiteSV () 
	{
		return (String)get_Value(COLUMNNAME_SetGratuiteSV);
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
	/** Guide Sous Traitons = 93 */
	public static final String TYPEROOM_GuideSousTraitons = "93";
	/** Gratuite = 94 */
	public static final String TYPEROOM_Gratuite = "94";
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
}