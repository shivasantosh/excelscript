package com;

import java.util.List;

public class SecondJsonData {
	private List<CBMSData> data;

	public List<CBMSData> getData() {
		return data;
	}

	public void setData(List<CBMSData> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SecondJsonData [data=" + data + "]";
	}
	
	
}
