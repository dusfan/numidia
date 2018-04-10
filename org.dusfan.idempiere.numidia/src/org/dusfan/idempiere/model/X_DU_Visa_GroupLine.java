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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DU_Visa_GroupLine
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DU_Visa_GroupLine extends PO implements I_DU_Visa_GroupLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180302L;

    /** Standard Constructor */
    public X_DU_Visa_GroupLine (Properties ctx, int DU_Visa_GroupLine_ID, String trxName)
    {
      super (ctx, DU_Visa_GroupLine_ID, trxName);
      /** if (DU_Visa_GroupLine_ID == 0)
        {
			setDU_Visa_GroupLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DU_Visa_GroupLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DU_Visa_GroupLine[")
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
			set_ValueNoCheck (COLUMNNAME_C_BPartnerRelation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartnerRelation_ID, Integer.valueOf(C_BPartnerRelation_ID));
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

	/** Set Code_VISA_E.
		@param Code_VISA_E Code_VISA_E	  */
	public void setCode_VISA_E (String Code_VISA_E)
	{
		set_Value (COLUMNNAME_Code_VISA_E, Code_VISA_E);
	}

	/** Get Code_VISA_E.
		@return Code_VISA_E	  */
	public String getCode_VISA_E () 
	{
		return (String)get_Value(COLUMNNAME_Code_VISA_E);
	}

	/** Set Code_VISA_M.
		@param Code_VISA_M Code_VISA_M	  */
	public void setCode_VISA_M (String Code_VISA_M)
	{
		set_Value (COLUMNNAME_Code_VISA_M, Code_VISA_M);
	}

	/** Get Code_VISA_M.
		@return Code_VISA_M	  */
	public String getCode_VISA_M () 
	{
		return (String)get_Value(COLUMNNAME_Code_VISA_M);
	}

	public I_DU_Visa_Group getDU_Visa_Group() throws RuntimeException
    {
		return (I_DU_Visa_Group)MTable.get(getCtx(), I_DU_Visa_Group.Table_Name)
			.getPO(getDU_Visa_Group_ID(), get_TrxName());	}

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

	/** Set Visa.
		@param DU_Visa_GroupLine_ID Visa	  */
	public void setDU_Visa_GroupLine_ID (int DU_Visa_GroupLine_ID)
	{
		if (DU_Visa_GroupLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DU_Visa_GroupLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DU_Visa_GroupLine_ID, Integer.valueOf(DU_Visa_GroupLine_ID));
	}

	/** Get Visa.
		@return Visa	  */
	public int getDU_Visa_GroupLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DU_Visa_GroupLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set PilgrimID.
		@param PilgrimID PilgrimID	  */
	public void setPilgrimID (String PilgrimID)
	{
		set_Value (COLUMNNAME_PilgrimID, PilgrimID);
	}

	/** Get PilgrimID.
		@return PilgrimID	  */
	public String getPilgrimID () 
	{
		return (String)get_Value(COLUMNNAME_PilgrimID);
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

	/** Set SponserID.
		@param SponserID SponserID	  */
	public void setSponserID (String SponserID)
	{
		set_Value (COLUMNNAME_SponserID, SponserID);
	}

	/** Get SponserID.
		@return SponserID	  */
	public String getSponserID () 
	{
		return (String)get_Value(COLUMNNAME_SponserID);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}
}