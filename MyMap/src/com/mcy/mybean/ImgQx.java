package com.mcy.mybean;

import com.j256.ormlite.field.DatabaseField;

public class ImgQx {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String qxid;//ȱ��ID
	@DatabaseField
	private String imgName;//�ļ�����
	@DatabaseField
	private String imgPath;//�ļ�·��
	@DatabaseField
	private String qxzt;//ȱ��״̬
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
