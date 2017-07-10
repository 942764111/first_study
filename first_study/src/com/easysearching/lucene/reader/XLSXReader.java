package com.easysearching.lucene.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReader extends FileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public XLSXReader(File file) {
		this.file = file;
	}

	public XLSXReader() {

	}

	public String getContent() {
		StringBuffer buffer = null;
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		XSSFSheet childSheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		int sheetSize = 0;
		int rowSize = 0;
		int cellSize = 0;
		try {
			buffer = new StringBuffer();
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheetSize = workbook.getNumberOfSheets();
			for (int i = 0; i < sheetSize; i++) {
				childSheet = workbook.getSheetAt(i);
				if (childSheet == null) {
					continue;
				}
				rowSize = childSheet.getPhysicalNumberOfRows();
				for (int j = 0; j < rowSize; j++) {
					row = childSheet.getRow(j);
					if (row == null) {
						continue;
					}
					cellSize = row.getPhysicalNumberOfCells();
					for (int k = 0; k < cellSize; k++) {
						cell = row.getCell(k);
						if (cell == null) {
							continue;
						}
						switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_NUMERIC: // 数字
							buffer.append(cell.getNumericCellValue()).append("   ");
							break;
						case XSSFCell.CELL_TYPE_STRING: // 字符串
							buffer.append(cell.getStringCellValue()).append("   ");
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
							buffer.append(cell.getBooleanCellValue()).append("   ");
							break;
						case XSSFCell.CELL_TYPE_FORMULA: // 公式
							buffer.append(cell.getCellFormula()).append("   ");
							break;
						case XSSFCell.CELL_TYPE_BLANK: // 空值
							buffer.append(" ");
							System.out.println(" ");
							break;
						case XSSFCell.CELL_TYPE_ERROR: // 故障
							buffer.append(" ");
							break;
						default:
							buffer.append(" ");
							break;
						}
					}
					buffer.append(System.getProperty("line.separator"));
				}
				buffer.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();

	}
}
