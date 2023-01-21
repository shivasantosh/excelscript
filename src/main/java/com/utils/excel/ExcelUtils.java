package com.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.utils.excel.annotation.ExcelCellInfo;

public class ExcelUtils {

	private ExcelUtils() {
	}

	public static <R> List<R> readFirstSheet(String path, ExcelSheetDescriptor<R> sheetDescriptor) throws IOException, InstantiationException, IllegalAccessException {
		return readSheet(path, sheetDescriptor);
	}

	public static <R> List<R> readSheet(String path, ExcelSheetDescriptor<R> sheetDescriptor) throws IOException, InstantiationException, IllegalAccessException {
		File excelFile = new File(path);
		List<R> result = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(excelFile); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			// open specified sheet
			XSSFSheet sheet = workbook.getSheetAt(sheetDescriptor.getSheetIndex());
			// Iterate over rows
			Iterator<Row> rowIt = sheet.iterator();
			int currentRowIndex = 0;
			while (rowIt.hasNext()) {
				Row row = rowIt.next();
				if (sheetDescriptor.getFirstDataRowIndex() > currentRowIndex) {
					currentRowIndex++;
					continue;
				}
				R rowInstance = sheetDescriptor.getRowClass().newInstance();

				// Iterate over row cells
				Field fieldDef;
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// check if there is mapping field for current cell
					fieldDef = sheetDescriptor.getFieldsMapper().get(cell.getAddress().getColumn());
					if (fieldDef != null)
						fieldDef.getAnnotation(ExcelCellInfo.class).cellParser().newInstance().parse(cell, rowInstance, fieldDef);
				}

				result.add(rowInstance);
				currentRowIndex++;
			}
		}
		return result;
	}

}
