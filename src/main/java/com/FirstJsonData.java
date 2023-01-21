package com;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirstJsonData {
	
	@JsonProperty(value = "actionCount")
	private String actionCount;
	private SecondJsonData data;
	@Override
	public String toString() {
		return "FirstJsonData [actionCount=" + actionCount + ", data=" + data + "]";
	}
	public String getActionCount() {
		return actionCount;
	}
	public void setActionCount(String actionCount) {
		this.actionCount = actionCount;
	}
	public SecondJsonData getData() {
		return data;
	}
	public void setData(SecondJsonData data) {
		this.data = data;
	}
	
 
}
