package com.qa.util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class TestUtil {
	//public static String TESTDATA_SHEET_PATH = ".\\src\\main\\java\\com\\qa\\testdata\\FreeCrmTestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	
	
	public static Properties getProperties(String sPropertiesFile){
		
		try {
			InputStream oFileReader;
			Properties oProperty;
			
			oFileReader = new FileInputStream(sPropertiesFile);
			oProperty = new Properties();
			
			oProperty.load(oFileReader);
			
			return oProperty;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
		
		
	}
	
	//---------------------------------------------------------------
	
	
	
	/*public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}*/
	
	public static Object[][] getTestData(String oExcelDriverFile, String sSheetName) {
		int iRow, iRowCount;
		int iCell, iCellCount;
		ExcelDriver oExcelDriver = new ExcelDriver();
		oExcelDriver.openExcelWorkbook(oExcelDriverFile);
		iRowCount = oExcelDriver.getRowCountOfSheet(sSheetName);
		iCellCount = oExcelDriver.getCellCount(sSheetName, 0);
		Object[][] data = new Object[iRowCount][iCellCount];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (iRow = 1; iRow <= iRowCount; iRow++) {
			for (iCell = 0; iCell < iCellCount; iCell++) {
				data[iRow-1][iCell] = oExcelDriver.getCellCData(sSheetName, iRow, iCell);
				 //System.out.println(data[iRow-1][iCell]);
			}
		}
		return data;
	}
	
	//---------------------------------------------------------------------------------------
	
	public static String getDateTimeStamp(){

		Date oDate;
		String[] sDatePart;
		String sDateStamp;
		
		
		oDate = new Date();
		System.out.println(oDate.toString());
		//Mon Sep 07 17:28:42 IST 2015

		sDatePart = oDate.toString().split(" ");
		
		sDateStamp = sDatePart[5] + "_" +
				sDatePart[1] + "_" +
				sDatePart[2] + "_" +
				sDatePart[3] ;
		
		sDateStamp = sDateStamp.replace(":",  "_");
		System.out.println(sDateStamp);
		return sDateStamp;
	}
	
	public static void uploadFile(String fileLocation) {
		try {
			// StringSelection is a class that can be used for copy and paste operations.
			StringSelection stringSelection = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Thread.sleep(3000);
			// native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
}
