package com.utils.excel.cellParser;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

public class DefaultCellParser extends CellParser {

	@Override
	public void parse(Cell cell, Object object, Field field) {
		Object cellValue;
		if (field.getType().equals(String.class))
			cellValue = cell.getStringCellValue();
		else if (field.getType().equals(int.class) || field.getType().equals(Integer.class))
			cellValue = Double.valueOf(cell.getNumericCellValue()).intValue();
		else if (field.getType().equals(long.class) || field.getType().equals(Long.class))
			cellValue = Double.valueOf(cell.getNumericCellValue()).longValue();
		else if (field.getType().equals(double.class) || field.getType().equals(Double.class))
			cellValue = cell.getNumericCellValue();
		else if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class))
			cellValue = cell.getBooleanCellValue();
		else if (field.getType().equals(Date.class))
			cellValue = cell.getDateCellValue();
		else {
			logDefaultFailure(cell, field);
			return;
		}
		
		setField(cell, object, field, cellValue);
	}

}
