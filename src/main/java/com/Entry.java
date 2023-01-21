package com;

import java.io.IOException;
import java.util.List;

import com.utils.excel.ExcelSheetDescriptor;
import com.utils.excel.ExcelUtils;
import com.utils.excel.RowClassSample;

public class Entry {

	public static void main(String[] args) {
		try {
			ExcelSheetDescriptor<RowClassSample> sheetDescriptor = new ExcelSheetDescriptor<>(RowClassSample.class).setHasHeader();
			// TODO change path
			List<RowClassSample> rows = ExcelUtils.readFirstSheet("<pathToFile>/testSheet.xlsx", sheetDescriptor);
			for (RowClassSample f : rows) {
				System.out.println(f.toString());
			}
		} catch (IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
