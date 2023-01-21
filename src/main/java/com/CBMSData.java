package com;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CBMSData {

	private String code;
	private String lable;
	@JsonProperty(value = "CBMS_CODE")
	private String cbmsCode;
	private String incomeFactor;
	private String isProhibited;
	private String IncomeFactorInPercentage;
	
	

	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getLable() {
		return lable;
	}



	public void setLable(String lable) {
		this.lable = lable;
	}



	public String getCbmsCode() {
		return cbmsCode;
	}



	public void setCbmsCode(String cbmsCode) {
		this.cbmsCode = cbmsCode;
	}



	public String getIncomeFactor() {
		return incomeFactor;
	}



	public void setIncomeFactor(String incomeFactor) {
		this.incomeFactor = incomeFactor;
	}



	public String getIsProhibited() {
		return isProhibited;
	}



	public void setIsProhibited(String isProhibited) {
		this.isProhibited = isProhibited;
	}



	public String getIncomeFactorInPercentage() {
		return IncomeFactorInPercentage;
	}



	public void setIncomeFactorInPercentage(String incomeFactorInPercentage) {
		IncomeFactorInPercentage = incomeFactorInPercentage;
	}



	@Override
	public String toString() {
		return "ThirdJsonData [code=" + code + ", lable=" + lable + ", incomeFactor=" + incomeFactor + ", isProhibited="
				+ isProhibited + "]";
	}
	
	
}
