package com;

import java.util.Date;

import com.utils.excel.annotation.ExcelCellInfo;
import com.utils.excel.cellParser.CellBooleanYesNoArParser;


public class SCData {

    @ExcelCellInfo(index = 0)
    private String bcpApsNo;
    @ExcelCellInfo(index = 1)
    private String LendInAppId;
    @ExcelCellInfo(index =2)
    private String akAppNum;
    @ExcelCellInfo(index = 3)
    private String bcbIdNo1;
    @ExcelCellInfo(index = 4)
    private String bcbIdNo2;
    @ExcelCellInfo(index = 5)
    private String bcbIdType;
    @ExcelCellInfo(index =6)
    private String cothIsic;
    @ExcelCellInfo(index = 7)
    private String consType;
    @ExcelCellInfo(index = 8)
    private Date vintageDat;
    public String getBcpApsNo() {
        return bcpApsNo;
    }
    public void setBcpApsNo(String bcpApsNo) {
        this.bcpApsNo = bcpApsNo;
    }
    public String getLendInAppId() {
        return LendInAppId;
    }
    public void setLendInAppId(String lendInAppId) {
        LendInAppId = lendInAppId;
    }
    public String getAkAppNum() {
        return akAppNum;
    }
    public void setAkAppNum(String akAppNum) {
        this.akAppNum = akAppNum;
    }
    public String getBcbIdNo1() {
        return bcbIdNo1;
    }
    public void setBcbIdNo1(String bcbIdNo1) {
        this.bcbIdNo1 = bcbIdNo1;
    }
    public String getBcbIdNo2() {
        return bcbIdNo2;
    }
    public void setBcbIdNo2(String bcbIdNo2) {
        this.bcbIdNo2 = bcbIdNo2;
    }
    public String getBcbIdType() {
        return bcbIdType;
    }
    public void setBcbIdType(String bcbIdType) {
        this.bcbIdType = bcbIdType;
    }
    public String getCothIsic() {
        return cothIsic;
    }
    public void setCothIsic(String cothIsic) {
        this.cothIsic = cothIsic;
    }
    public String getConsType() {
        return consType;
    }
    public void setConsType(String consType) {
        this.consType = consType;
    }
    public Date getVintageDat() {
        return vintageDat;
    }
    public void setVintageDat(Date vintageDat) {
        this.vintageDat = vintageDat;
    }
    @Override
    public String toString() {
	return "SCData [bcpApsNo=" + bcpApsNo + ", LendInAppId=" + LendInAppId + ", akAppNum=" + akAppNum
		+ ", bcbIdNo1=" + bcbIdNo1 + ", bcbIdNo2=" + bcbIdNo2 + ", bcbIdType=" + bcbIdType + ", cothIsic="
		+ cothIsic + ", consType=" + consType + ", vintageDat=" + vintageDat + "]";
    }
    
    
    
}
