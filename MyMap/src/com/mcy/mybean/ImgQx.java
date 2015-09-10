package com.mcy.mybean;

import com.j256.ormlite.field.DatabaseField;

public class ImgQx {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String qxid;//缺陷ID
	@DatabaseField
	private String imgName;//文件名称
	@DatabaseField
	private String imgPath;//文件路径
	@DatabaseField
	private String qxzt;//缺陷状态
	public String getQxid() {
		return qxid;
	}
	public void setQxid(String qxid) {
		this.qxid = qxid;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getQxzt() {
		return qxzt;
	}
	public void setQxzt(String qxzt) {
		this.qxzt = qxzt;
	}
	
}
