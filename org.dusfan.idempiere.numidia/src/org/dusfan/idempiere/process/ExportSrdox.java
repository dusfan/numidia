package org.dusfan.idempiere.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.dusfan.idempiere.model.MVol;

public class ExportSrdox extends SvrProcess {

	// vol
	private int m_DU_Vol_ID = 0;

	@Override
	protected void prepare() {
		m_DU_Vol_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		int count = DB.getSQLValue(get_TrxName(), "Select count(1) from DU_VolLine_V_SRDOCS_02 where du_vol_id=?", m_DU_Vol_ID);
		if (count <= 0 )
			return "Il n y a pas de lignes pour ce VOL";
		if (processUI != null)
			processUI.download(getExportSrdocs(getTableData()));
		
		return "Export Srdocs " + m_DU_Vol_ID;
	}
	
	private ArrayList<Object[]> getTableData() {
		ArrayList<Object[]> tableDataList = null;
		String sql="Select * from DU_VolLine_V_SRDOCS_02 where du_vol_id = " + m_DU_Vol_ID + " Order By bpname";
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql, get_TrxName());
				rs = pstmt.executeQuery();
				tableDataList = new ArrayList<Object[]>();
				int count = 0;
				while (rs.next()) {
					Object[] objArray = new Object[4];
					count++;
					objArray[0] = count; // N°
					objArray[1] = rs.getString(2); // Nom
					objArray[2] = rs.getString(3)+""+count; // apis
					objArray[3] = rs.getString(4); // nature
					tableDataList.add(objArray);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return tableDataList;
	}
	
	 public File getExportSrdocs (ArrayList<Object[]> dataList)  {
	        if(dataList != null && !dataList.isEmpty()) {
	            HSSFWorkbook workBook = new HSSFWorkbook();
	            HSSFSheet sheet = workBook.createSheet();
	            HSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell(0).setCellValue("N°");
	            headingRow.createCell(1).setCellValue("Nom");
	            headingRow.createCell(2).setCellValue("APIS");
	            headingRow.createCell(3).setCellValue("Nature");
	            short rowNo = 1;
	            for (Object[] objects : dataList) {
	                HSSFRow row = sheet.createRow(rowNo);
	                row.createCell(0).setCellValue(objects[0].toString());
	                row.createCell(1).setCellValue(objects[1].toString());
	                row.createCell(2).setCellValue(objects[2].toString());
	                row.createCell(3).setCellValue(objects[3].toString());
	                rowNo++;
	            }
	            try {
	            	String srdocs = System.getProperty("java.io.tmpdir");
					if (!srdocs.endsWith("/") && !srdocs.endsWith("\\"))
						srdocs+= File.separator;
					MVol vol = new MVol(getCtx(), m_DU_Vol_ID, get_TrxName());
					srdocs = srdocs + "SRDOCS_" +vol.getValue().toUpperCase()+".xls";
					File file = new File(srdocs);
	                FileOutputStream fos = new FileOutputStream(file);
	                workBook.write(fos);
	                fos.flush();
	                return file;
	            } catch(FileNotFoundException e){
	                e.printStackTrace();
	                System.out.println("Invalid directory or file not found");
	                return null;
	            } catch(IOException e){
	                e.printStackTrace();
	                System.out.println("Error occurred while writting excel file to directory");
	                return null;
	            }
	        }
			return null;
	    }
}
