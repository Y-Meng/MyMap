package com.mcy.mobile.core;

import com.mcy.mobile.util.GsonUtil;

/**
 * data bean can be serialized to json format
 * @author mengchaoyue
 *
 */
public class JsonBean implements Jsonable {
	
	public String toJson() {
		return GsonUtil.getGson().toJson(this);
	}
}
