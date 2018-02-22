package com.qa.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class DataProviderUtil {

	static Sheet oSheet;
	Row oRow;
	Cell oCell;

	public static Object[][] getSheetData(String xlFilePath, String sSheetName) {
		int iRow, iRowCount;
		int iCell, iCellCount;
		int ci, cj;
		ExcelDriver oExcelDriver = new ExcelDriver();
		oExcelDriver.openExcelWorkbook(xlFilePath);
		iRowCount = oExcelDriver.getRowCountOfSheet(sSheetName);
		iCellCount = oExcelDriver.getCellCount(sSheetName, 0);
		Object[][] data = new Object[iRowCount][iCellCount];
		ci = 0;
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (iRow = 1; iRow <= iRowCount; iRow++, ci++) {
			cj = 0;
			for (iCell = 0; iCell < iCellCount; iCell++, cj++) {
				data[ci][cj] = oExcelDriver.getCellCData(sSheetName, iRow, iCell);
				// System.out.println(data[iRow-1][iCell]);
			}
		}
		oExcelDriver.close();
		return data;

	}
	
	
}