package com;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinalJsonObject {
	@JsonProperty(value = "data")
	private FirstJsonData data;

	
	
	
	public FirstJsonData getData() {
		return data;
	}

	public void setData(FirstJsonData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FinalJsonObject [data=" + data + "]";
	}
	
	
}
