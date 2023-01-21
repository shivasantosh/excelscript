package com.utils.excel.cellParser;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;

import com.utils.reflection.ReflectionUtils;

public class CellLongParser extends CellParser {

	public void parse(Cell cell, Object object, Field field) {
		String stringValue = cell.getStringCellValue().trim();
		try {
			ReflectionUtils.setField(object, field, Long.parseLong(stringValue));
		} catch (NumberFormatException | IllegalAccessException e) {
			logDefaultFailure(e, cell, field);
		}
	}

}
