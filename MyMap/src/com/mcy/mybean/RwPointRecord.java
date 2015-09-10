package com.mcy.mybean;

import com.j256.ormlite.field.DatabaseField;
import com.mcy.mobile.core.JsonBean;

public class RwPointRecord extends JsonBean {

	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
    String XSRW_XSRYID;
    @DatabaseField
	String XSRW_XSRY;
    @DatabaseField
	String XSRW_BM;
    @DatabaseField
	String XSRW_ID;
    @DatabaseField
	String UPINFO_ID;
    @DatabaseField
	String XSRW_ZXMC;
    @DatabaseField
	String XSRW_SUBMC;
    @DatabaseField
	String SXRW_GTID;
    @DatabaseField
	String SXRW_SCSJ;
    @DatabaseField
	String SXRW_MS;
    @DatabaseField
	String SXRW_CJX;
    @DatabaseField
	String SXRW_CJY;
    @DatabaseField
	String COORX;
    @DatabaseField
	String COORY;
	public String getXSRW_XSRYID() {
		return XSRW_XSRYID;
	}
	public void setXSRW_XSRYID(String xSRW_XSRYID) {
		XSRW_XSRYID = xSRW_XSRYID;
	}
	public String getXSRW_XSRY() {
		return XSRW_XSRY;
	}
	public void setXSRW_XSRY(String xSRW_XSRY) {
		XSRW_XSRY = xSRW_XSRY;
	}
	public String getXSRW_BM() {
		return XSRW_BM;
	}
	public void setXSRW_BM(String xSRW_BM) {
		XSRW_BM = xSRW_BM;
	}
	public String getXSRW_ID() {
		return XSRW_ID;
	}
	public void setXSRW_ID(String xSRW_ID) {
		XSRW_ID = xSRW_ID;
	}
	public String getUPINFO_ID() {
		return UPINFO_ID;
	}
	public void setUPINFO_ID(String uPINFO_ID) {
		UPINFO_ID = uPINFO_ID;
	}
	public String getXSRW_ZXMC() {
		return XSRW_ZXMC;
	}
	public void setXSRW_ZXMC(String xSRW_ZXMC) {
		XSRW_ZXMC = xSRW_ZXMC;
	}
	public String getXSRW_SUBMC() {
		return XSRW_SUBMC;
	}
	public void setXSRW_SUBMC(String xSRW_SUBMC) {
		XSRW_SUBMC = xSRW_SUBMC;
	}
	public String getSXRW_GTID() {
		return SXRW_GTID;
	}
	public void setSXRW_GTID(String sXRW_GTID) {
		SXRW_GTID = sXRW_GTID;
	}
	public String getSXRW_SCSJ() {
		return SXRW_SCSJ;
	}
	public void setSXRW_SCSJ(String sXRW_SCSJ) {
		SXRW_SCSJ = sXRW_SCSJ;
	}
	public String getSXRW_MS() {
		return SXRW_MS;
	}
	public void setSXRW_MS(String sXRW_MS) {
		SXRW_MS = sXRW_MS;
	}
	public String getSXRW_CJX() {
		return SXRW_CJX;
	}
	public void setSXRW_CJX(String sXRW_CJX) {
		SXRW_CJX = sXRW_CJX;
	}
	public String getSXRW_CJY() {
		return SXRW_CJY;
	}
	public void setSXRW_CJY(String sXRW_CJY) {
		SXRW_CJY = sXRW_CJY;
	}
	public String getCOORX() {
		return COORX;
	}
	public void setCOORX(String cOORX) {
		COORX = cOORX;
	}
	public String getCOORY() {
		return COORY;
	}
	public void setCOORY(String cOORY) {
		COORY = cOORY;
	}
}
