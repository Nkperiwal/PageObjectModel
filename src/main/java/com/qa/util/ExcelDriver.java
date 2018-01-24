package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	private InputStream oFileReader;
	private OutputStream oFileWriter;
	private Workbook oExcelWorkbook;
	private String sExcelFileName;
	Sheet oSheet;
	Row oRow;
	Cell oCell;

	public ExcelDriver() {
		this.setNull();
	}

	public void createNewExcelWorkbook(String sFileName) {
		try {

			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if ((new File(sFileName)).exists()) {
				throw new Exception("File already Exists");
			}

			if (sFileName.toLowerCase().endsWith("xlsx")) {
				oExcelWorkbook = new XSSFWorkbook();

			} else if (sFileName.toLowerCase().endsWith("xls")) {
				oExcelWorkbook = new HSSFWorkbook();

			} else {
				throw new Exception("Invalid File Extension...");
			}

			oFileWriter = new FileOutputStream(sFileName);
			oExcelWorkbook.write(oFileWriter);
			oFileWriter.close();
			((FileInputStream) oExcelWorkbook).close();
			this.setNull();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setNull() {
		oFileReader = null;
		oFileWriter = null;
		oExcelWorkbook = null;
		sExcelFileName = "";
	}

	// ---------------------------------------------------------

	public void openExcelWorkbook(String sFileName) {
		try {

			sFileName = sFileName.trim();
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if (!(new File(sFileName)).exists()) {
				throw new Exception("File does not Exists");
			}

			oFileReader = new FileInputStream(sFileName);
			sExcelFileName = sFileName;
			oExcelWorkbook = WorkbookFactory.create(oFileReader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------

	public void save() {
		try {
			oFileWriter = new FileOutputStream(sExcelFileName);
			oExcelWorkbook.write(oFileWriter);
			oFileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------------

	public void saveAs(String sFileName) {
		try {
			if (sFileName.isEmpty()) {
				throw new Exception("No file name specified...");
			}

			if ((new File(sFileName)).exists()) {
				throw new Exception("File already Exists");
			}

			oFileWriter = new FileOutputStream(sFileName);
			oExcelWorkbook.write(oFileWriter);
			oFileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------

	public void close() {
		try {
			((FileInputStream) oExcelWorkbook).close();
			oFileReader.close();
			setNull();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// --------------------------------------------------------------------

	public void createSheet(String sSheetName, String sWorkbook) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}

			Sheet oSheet;
			openExcelWorkbook(sWorkbook);
			oSheet = oExcelWorkbook.getSheet(sSheetName);
			if (oSheet != null) {
				throw new Exception("Sheet already Exist");
			}
			oExcelWorkbook.createSheet();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// --------------------------------------------------------------------

	public int getRowCountOfSheet(String sSheetName) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}
			Sheet oSheet;
			oSheet = oExcelWorkbook.getSheet(sSheetName);
			System.out.println("Sheet Name: " + sSheetName);
			if (oSheet == null) {
				throw new Exception("Sheet does not Exist");
			}

			return oSheet.getLastRowNum();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	// ------------------------------------------------------------------------

	public int getCellCount(String sSheetName, int iRow) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}

			Sheet oSheet;

			oSheet = oExcelWorkbook.getSheet(sSheetName);
			if (oSheet == null) {
				throw new Exception("Sheet doesnot Exist");
			}

			/*if (iRow < 1) {
				throw new Exception("ROw Index start from 1");
			}*/

			Row oRow;

			oRow = oSheet.getRow(iRow);

			if (oRow == null) {
				return 0;
			} else {
				return oRow.getLastCellNum();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	// ---------------------------------------------------------------------

	public String getCellCData(String sSheetName, int iRow, int iCell) { // irow=1,icell = 0																	
		try {
			String value = "";
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");
			}
			oSheet = oExcelWorkbook.getSheet(sSheetName);

			if (oSheet == null) {
				throw new Exception("Sheet does not Exist");
			}
			/*if (iRow < 1 || iCell < 1) {
				throw new Exception("Row & Cell Index start from 1");
			}*/
			oRow = oSheet.getRow(iRow);

			if (oRow == null) {
				return "";
			}
			oCell = oRow.getCell(iCell);
			if (oCell == null) {
				// fill the return string as per your requirement
				return "";
			} else {
				
				switch (oCell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                	value = String.valueOf(oCell.getNumericCellValue());
                   // System.out.print(oCell.getNumericCellValue() + "(Integer)\t");
                    break;
                case Cell.CELL_TYPE_STRING:
                	value = oCell.getStringCellValue();
                   // System.out.print(oCell.getStringCellValue() + "(String)\t");
                    break;
            }
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	

	}
	

	// ----------------------------------------------------------------

	public void setCellCData(String sSheetName, int iRow, int iCell,
			String sValue) {
		try {
			sSheetName = sSheetName.trim();
			if (sSheetName.isEmpty()) {
				throw new Exception("Sheet name not specified");

			}

			Sheet oSheet;

			oSheet = oExcelWorkbook.getSheet(sSheetName);
			if (oSheet == null) {
				throw new Exception("Sheet doesnot  Exist");
			}

			if (iRow < 1 || iCell < 1) {
				throw new Exception("Row & Cell Index start from 1");
			}

			Row oRow;

			oRow = oSheet.getRow(iRow - 1);

			if (oRow == null) {
				oSheet.createRow(iRow - 1);
				oRow = oSheet.getRow(iRow - 1);
			}

			Cell oCell;
			oCell = oRow.getCell(iCell - 1);
			if (oCell == null) {
				oRow.createCell(iCell - 1);
				oCell = oRow.getCell(iCell - 1);
			}

			oCell.setCellValue(sValue);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
