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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DU_Visa_Group
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_Visa_Group extends PO implements I_DU_Visa_Group, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180302L;

    /** Standard Constructor */
    public X_DU_Visa_Group (Properties ctx, int DU_Visa_Group_ID, String trxName)
    {
      super (ctx, DU_Visa_Group_ID, trxName);
      /** if (DU_Visa_Group_ID == 0)
        {
			setDU_Visa_Group_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_Visa_Group (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_Visa_Group[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Changer Statut.
		@param ChangeVisaStatus Changer Statut	  */
	public void setChangeVisaStatus (String ChangeVisaStatus)
	{
		set_Value (COLUMNNAME_ChangeVisaStatus, ChangeVisaStatus);
	}

	/** Get Changer Statut.
		@return Changer Statut	  */
	public String getChangeVisaStatus () 
	{
		return (String)get_Value(COLUMNNAME_ChangeVisaStatus);
	}

	/** Set Visa.
		@param DU_Visa_Group_ID Visa	  */
	public void setDU_Visa_Group_ID (int DU_Visa_Group_ID)
	{
		if (DU_Visa_Group_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Visa_Group_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Visa_Group_ID, Integer.valueOf(DU_Visa_Group_ID));
	}

	/** Get Visa.
		@return Visa	  */
	public int getDU_Visa_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Visa_Group_ID);
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

	/** Brouillon = BR */
	public static final String STATUSVISA_Brouillon = "BR";
	/** Attente = AT */
	public static final String STATUSVISA_Attente = "AT";
	/** Confirmé = CF */
	public static final String STATUSVISA_Confirmé = "CF";
	/** Visé = VS */
	public static final String STATUSVISA_Visé = "VS";
	/** Set Statut.
		@param StatusVisa Statut	  */
	public void setStatusVisa (String StatusVisa)
	{

		set_Value (COLUMNNAME_StatusVisa, StatusVisa);
	}

	/** Get Statut.
		@return Statut	  */
	public String getStatusVisa () 
	{
		return (String)get_Value(COLUMNNAME_StatusVisa);
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