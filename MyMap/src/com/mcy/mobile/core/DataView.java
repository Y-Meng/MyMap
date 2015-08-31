package com.mcy.mobile.core;


/**
 * Interface of dataview
 * @author Administrator
 */
public interface DataView {
	/**
	 * get data bean
	 * @return
	 */
	public JsonBean getData();
	/**
	 * get data format to json
	 * @return
	 */
	public String getDataJson();
	/**
	 * set data json
	 * @param json
	 */
	public void setData(String json);
	/**
	 * set data bean
	 * @param databean
	 */
	public void setData(Object data);
	/**
	 * update data view
	 */
    public void updateData();
}
