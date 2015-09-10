package com.mcy.mybean;

import com.j256.ormlite.field.DatabaseField;

public class ImgXs {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String rwid;//巡视子任务ID（upinfo_id）
	@DatabaseField
	private String imgName;
	@DatabaseField
	private String imgPath;
	
	public String getRwid() {
		return rwid;
	}
	public void setRwid(String rwid) {
		this.rwid = rwid;
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
}
