package com.utils.excel;

import com.utils.excel.annotation.ExcelCellInfo;
import com.utils.excel.cellParser.CellBooleanYesNoArParser;
import com.utils.excel.cellParser.CellNumericAsStringParser;
import com.utils.excel.cellParser.CellPercentageParser;

public class RowClassSample {

	@ExcelCellInfo(index = 0)
	private long serial;

	@ExcelCellInfo(index = 1)
	private String name;

	@ExcelCellInfo(index = 2, cellParser = CellNumericAsStringParser.class)
	private String registrationNumber;

	@ExcelCellInfo(index = 3, cellParser = CellPercentageParser.class)
	private Double percentage;

	@ExcelCellInfo(index = 4)
	private String notes;

	@ExcelCellInfo(index = 5, cellParser = CellBooleanYesNoArParser.class)
	private boolean approval;

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	
	@Override
	public String toString() {
		return serial + " , " + name + " , " + registrationNumber + " , " + percentage + " , " + notes + " , " + approval;
	}
	
}
