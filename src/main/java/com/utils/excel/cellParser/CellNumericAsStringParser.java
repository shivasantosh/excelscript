package com.utils.excel.cellParser;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;

import com.utils.reflection.ReflectionUtils;

public class CellNumericAsStringParser extends CellParser {

	public void parse(Cell cell, Object object, Field field) {
		try {
			ReflectionUtils.setField(object, field, "" + cell.getNumericCellValue());
		} catch (NumberFormatException | IllegalAccessException e) {
			logDefaultFailure(e, cell, field);
		}
	}

}
