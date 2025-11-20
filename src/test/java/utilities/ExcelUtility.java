package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream file;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public int getRows() throws IOException {
		file = new FileInputStream(".//test-data/Input_Details.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(0); 
		
		return sheet.getLastRowNum(); //1-based indexing
	}
	
	public int getCols() throws IOException {
		file = new FileInputStream(".//test-data/Input_Details.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(0); 
		
		row = sheet.getRow(1);
		int cols = row.getLastCellNum(); //0-based indexing
		
		return cols;
	}

	public String[][] getInputValues() throws IOException{
		int rows = getRows();
		int cols = getCols();
	
		String[][] data = new String[rows][cols];
		
		for(int r=0;r<rows;r++) { 
			XSSFRow row1 = sheet.getRow(r+1);
			     XSSFCell cell1 = row1.getCell(1);
			     
			     switch(cell1.getCellType()) {
			     case STRING : data[r][0] = cell1.getStringCellValue(); break;
			     case NUMERIC : 
			    	       long numericVal = (long) cell1.getNumericCellValue();
			    	       data[r][0] = String.valueOf(numericVal); break;
			     case BOOLEAN : data[r][0] = String.valueOf(cell1.getBooleanCellValue()); break;
			     }
		}
		
		return data;
	}
}
