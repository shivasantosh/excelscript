package com.utils.excel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.utils.excel.annotation.ExcelCellInfo;
import com.utils.reflection.ReflectionUtils;

public class ExcelSheetDescriptor<R> {

	private int sheetIndex = 0;
	private boolean hasHeader = false;
	private int firstDataRowIndex = 0;
	private Class<R> rowClass;
	Map<Integer, Field> fieldsMapper;
	
	public ExcelSheetDescriptor(Class<R> rowClass) {
		this.rowClass = rowClass;
		fieldsMapper = new HashMap<>();
		ReflectionUtils.executeOnClassFieldsByAnnotation(rowClass, ExcelCellInfo.class, fieldDef -> {
			ExcelCellInfo cellInfo = fieldDef.getAnnotation(ExcelCellInfo.class);
			fieldsMapper.put(cellInfo.index(), fieldDef);
		});
	}
	
	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public boolean getHasHeader() {
		return hasHeader;
	}
	
	public ExcelSheetDescriptor<R> setHasHeader() {
		return setHasHeader(true);
	}

	public ExcelSheetDescriptor<R> setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
		if (hasHeader && firstDataRowIndex == 0)
			firstDataRowIndex = 1;

		return this;
	}

	public int getFirstDataRowIndex() {
		return firstDataRowIndex;
	}

	public ExcelSheetDescriptor<R> setFirstDataRowIndex(int firstDataRowIndex) {
		this.firstDataRowIndex = firstDataRowIndex;
		return this;
	}
	
	public Class<R> getRowClass() {
		return rowClass;
	}

	public Map<Integer, Field> getFieldsMapper() {
		return fieldsMapper;
	}

}