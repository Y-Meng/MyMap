package com.mcy.mybean;

import com.j256.ormlite.field.DatabaseField;
import com.mcy.mobile.core.JsonBean;

public class RwQxRecord extends JsonBean {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField
	String DEFECT_BS;//N	VARCHAR2(20)	Y			线路，变电站  缺陷标识
    @DatabaseField
	String XSRWID;	 //N	VARCHAR2(50)	Y			计划标识
    @DatabaseField
	String SBID;     //N	VARCHAR2(50)	Y			设备id
    @DatabaseField
	String SBBM;     //N	VARCHAR2(50)	Y			设备编码
    @DatabaseField
	String DEFECT_OBJECT;//	N	VARCHAR2(50)	Y			缺陷主体
    @DatabaseField
	String DEFECT_PART;//	N	VARCHAR2(500)	Y			缺陷部件
    @DatabaseField
	String DEFECT_CONTENT;//	N	VARCHAR2(500)	Y			缺陷内容
	@DatabaseField
	String FIND_TIME;//	N	VARCHAR2(20)	Y			发现时间  2014-11-01 12:12:12
	@DatabaseField
	String FIND_RYID;//	N	VARCHAR2(50)	Y			发现人ID
	@DatabaseField
	String FIND_RYMC;//	N	VARCHAR2(50)	Y			发现人名称
	@DatabaseField
	String FIND_RYBM;//	N	VARCHAR2(100)	Y			发现人员部门
	@DatabaseField
	String REMARK;	//N	VARCHAR2(200)	Y			备注
	@DatabaseField
	String STATE; //	N	VARCHAR2(10)	Y	('未消缺')		未消缺，已消缺
	@DatabaseField
	String DEFECTID;//N	VARCHAR2(36)	N			缺陷ID
	@DatabaseField
	String DEFECT_LEVEL;//	N	VARCHAR2(100)	Y			一般缺陷，重大缺陷，紧急缺陷
	@DatabaseField
	String SBMC; //	N	VARCHAR2(100)	Y			设备名称
	@DatabaseField
	String VERIFY_NR;//	N	VARCHAR2(500)	Y			核实内容  下达消缺的任务
	@DatabaseField
	String VERIFY_RYID;//	N	VARCHAR2(50)	Y			人员ID
	@DatabaseField
	String VERIFY_RY;//	N	VARCHAR2(50)	Y			核实人员
	@DatabaseField
	String VERIFY_BM;//	N	VARCHAR2(100)	Y			核实部门
	@DatabaseField
	String VERIFY_SJ;//	N	VARCHAR2(20)	Y			核实时间2014-11-01 12:12:12
	@DatabaseField
	String VERIFY_JG;//	N	VARCHAR2(100)	Y			核实结果   消缺完成的结果
	@DatabaseField
	String VERIFY_SCSJ;//	N	VARCHAR2(20)	Y			上传时间2014-11-01 12:12:12
	@DatabaseField
	String DEFECT_X;//N	VARCHAR2(20)	Y			上报缺陷位置
	@DatabaseField
	String DEFECT_Y;//	N	VARCHAR2(20)	Y			
	@DatabaseField
	String VERIFY_X;//	N	VARCHAR2(20)	Y			核实缺陷位置
	@DatabaseField
	String VERIFY_Y;//	N	VARCHAR2(20)	Y			
	public String getDEFECT_BS() {
		return DEFECT_BS;
	}
	public void setDEFECT_BS(String dEFECT_BS) {
		DEFECT_BS = dEFECT_BS;
	}
	public String getXSRWID() {
		return XSRWID;
	}
	public void setXSRWID(String xSRWID) {
		XSRWID = xSRWID;
	}
	public String getSBID() {
		return SBID;
	}
	public void setSBID(String sBID) {
		SBID = sBID;
	}
	public String getSBBM() {
		return SBBM;
	}
	public void setSBBM(String sBBM) {
		SBBM = sBBM;
	}
	public String getDEFECT_OBJECT() {
		return DEFECT_OBJECT;
	}
	public void setDEFECT_OBJECT(String dEFECT_OBJECT) {
		DEFECT_OBJECT = dEFECT_OBJECT;
	}
	public String getDEFECT_PART() {
		return DEFECT_PART;
	}
	public void setDEFECT_PART(String dEFECT_PART) {
		DEFECT_PART = dEFECT_PART;
	}
	public String getDEFECT_CONTENT() {
		return DEFECT_CONTENT;
	}
	public void setDEFECT_CONTENT(String dEFECT_CONTENT) {
		DEFECT_CONTENT = dEFECT_CONTENT;
	}
	public String getFIND_TIME() {
		return FIND_TIME;
	}
	public void setFIND_TIME(String fIND_TIME) {
		FIND_TIME = fIND_TIME;
	}
	public String getFIND_RYID() {
		return FIND_RYID;
	}
	public void setFIND_RYID(String fIND_RYID) {
		FIND_RYID = fIND_RYID;
	}
	public String getFIND_RYMC() {
		return FIND_RYMC;
	}
	public void setFIND_RYMC(String fIND_RYMC) {
		FIND_RYMC = fIND_RYMC;
	}
	public String getFIND_RYBM() {
		return FIND_RYBM;
	}
	public void setFIND_RYBM(String fIND_RYBM) {
		FIND_RYBM = fIND_RYBM;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getDEFECTID() {
		return DEFECTID;
	}
	public void setDEFECTID(String dEFECTID) {
		DEFECTID = dEFECTID;
	}
	public String getDEFECT_LEVEL() {
		return DEFECT_LEVEL;
	}
	public void setDEFECT_LEVEL(String dEFECT_LEVEL) {
		DEFECT_LEVEL = dEFECT_LEVEL;
	}
	public String getSBMC() {
		return SBMC;
	}
	public void setSBMC(String sBMC) {
		SBMC = sBMC;
	}
	public String getVERIFY_NR() {
		return VERIFY_NR;
	}
	public void setVERIFY_NR(String vERIFY_NR) {
		VERIFY_NR = vERIFY_NR;
	}
	public String getVERIFY_RYID() {
		return VERIFY_RYID;
	}
	public void setVERIFY_RYID(String vERIFY_RYID) {
		VERIFY_RYID = vERIFY_RYID;
	}
	public String getVERIFY_RY() {
		return VERIFY_RY;
	}
	public void setVERIFY_RY(String vERIFY_RY) {
		VERIFY_RY = vERIFY_RY;
	}
	public String getVERIFY_BM() {
		return VERIFY_BM;
	}
	public void setVERIFY_BM(String vERIFY_BM) {
		VERIFY_BM = vERIFY_BM;
	}
	public String getVERIFY_SJ() {
		return VERIFY_SJ;
	}
	public void setVERIFY_SJ(String vERIFY_SJ) {
		VERIFY_SJ = vERIFY_SJ;
	}
	public String getVERIFY_JG() {
		return VERIFY_JG;
	}
	public void setVERIFY_JG(String vERIFY_JG) {
		VERIFY_JG = vERIFY_JG;
	}
	public String getVERIFY_SCSJ() {
		return VERIFY_SCSJ;
	}
	public void setVERIFY_SCSJ(String vERIFY_SCSJ) {
		VERIFY_SCSJ = vERIFY_SCSJ;
	}
	public String getDEFECT_X() {
		return DEFECT_X;
	}
	public void setDEFECT_X(String dEFECT_X) {
		DEFECT_X = dEFECT_X;
	}
	public String getDEFECT_Y() {
		return DEFECT_Y;
	}
	public void setDEFECT_Y(String dEFECT_Y) {
		DEFECT_Y = dEFECT_Y;
	}
	public String getVERIFY_X() {
		return VERIFY_X;
	}
	public void setVERIFY_X(String vERIFY_X) {
		VERIFY_X = vERIFY_X;
	}
	public String getVERIFY_Y() {
		return VERIFY_Y;
	}
	public void setVERIFY_Y(String vERIFY_Y) {
		VERIFY_Y = vERIFY_Y;
	}
}
