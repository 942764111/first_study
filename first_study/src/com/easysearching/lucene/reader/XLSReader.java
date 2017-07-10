package com.easysearching.lucene.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XLSReader extends FileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public XLSReader(File file) {
		this.file = file;
	}

	public XLSReader() {

	}

	@Override
	public String getContent() {
		StringBuffer buffer = null;
		FileInputStream fis = null;
		HSSFWorkbook workbook = null;
		HSSFSheet childSheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		int sheetSize = 0;
		int rowSize = 0;
		int cellSize = 0;
		try {
			buffer = new StringBuffer();
			fis = new FileInputStream(file);
			workbook = new HSSFWorkbook(fis);
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
						cell = row.getCell((short) k);
						if (cell == null) {
							continue;
						}
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC: // 数字
							buffer.append(cell.getNumericCellValue()).append("   ");
							break;
						case HSSFCell.CELL_TYPE_STRING: // 字符串
							buffer.append(cell.getStringCellValue()).append("   ");
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
							buffer.append(cell.getBooleanCellValue()).append("   ");
							break;
						case HSSFCell.CELL_TYPE_FORMULA: // 公式
							buffer.append(cell.getCellFormula()).append("   ");
							break;
						case HSSFCell.CELL_TYPE_BLANK: // 空值
							buffer.append(" ");
							System.out.println(" ");
							break;
						case HSSFCell.CELL_TYPE_ERROR: // 故障
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
