package com.utils.excel.cellParser;

import static org.slf4j.LoggerFactory.getLogger;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;

public abstract class CellParser {

	private Logger parserLogger = getLogger(CellParser.class);
	
	public abstract void parse(Cell cell, Object object, Field field);
	
	protected Logger getParserLogger() {
		return parserLogger;
	}

	protected void setField(Cell cell, Object object, Field field, Object cellValue) {
		try {
			// makeAccessible first
			field.set(object, cellValue);
		} catch (NumberFormatException | IllegalAccessException e) {
			logDefaultFailure(e, cell, field);
		}
	}
	
	protected void logDefaultFailure(Cell cell, Field field) {
		logDefaultFailure(new Exception(), cell, field);
	}
	
	protected void logDefaultFailure(Exception e, Cell cell, Field field) {
		getParserLogger().error("{} {} while parsing cell {} to field {}", this.getClass(), e.getMessage(), cell.getAddress().toString(), field.getName());
	}
}
