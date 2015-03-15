import java.io.File;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class ExcelFactory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public void expExcel(String fileName, ArrayList<String> fields, ArrayList<ArrayList<String>> values) {
	

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet firstSheet = workbook.createSheet("Sheet1");
		
		//STYLE
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.LIME.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillBackgroundColor(HSSFColor.LIME.index);
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);
		
		 
		FileOutputStream fos = null;
		 
		try {
		fos = new FileOutputStream(new File(fileName));
	
		HSSFRow firstrow = firstSheet.createRow(0);
		//row.setRowStyle(style);
		for(int i = 0;i<fields.size();i++){
			firstrow.createCell(i).setCellValue(fields.get(i));
			firstrow.getCell(i).setCellStyle(style);
			
		}
		for(int i = 1;i<values.size();i++){
			HSSFRow row = firstSheet.createRow(i);
			ArrayList<String> line = values.get(i);
			
			for(int j = 0; j<line.size();j++){
				row.createCell(j).setCellValue(line.get(j));
			}
		}
	 
		 
		workbook.write(fos);
	
		
		 
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("The file cannot be exported");
		} finally {
		try {
		fos.flush();
		fos.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		}
	
	


}
