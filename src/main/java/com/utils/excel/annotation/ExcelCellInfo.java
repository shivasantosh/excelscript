package com.utils.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.utils.excel.cellParser.CellParser;
import com.utils.excel.cellParser.DefaultCellParser;

@Target(FIELD)
@Retention(RUNTIME)
public @interface ExcelCellInfo {
	int index();
	Class<? extends CellParser> cellParser() default DefaultCellParser.class;
}
