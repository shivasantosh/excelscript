package com.utils.excel.cellParser;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;

import com.utils.reflection.ReflectionUtils;

public class CellBooleanYesNoArParser extends CellParser {
	
	private static String yesAr = "نعم";
	private static String noAr = "لا";

	public void parse(Cell cell, Object object, Field field) {
		String stringValue = cell.getStringCellValue().trim();
		Boolean cellValue = null;
		if (yesAr.equals(stringValue))
			cellValue = true;
		else if (noAr.equals(stringValue))
			cellValue = false;
		else {
			logDefaultFailure(cell, field);
			return;
		}
		try {
			ReflectionUtils.setField(object, field, cellValue);
		} catch (NumberFormatException | IllegalAccessException e) {
			logDefaultFailure(e, cell, field);
		}
	}

}
