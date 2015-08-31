package com.mcy.mobile.util;

import com.google.gson.Gson;
/**
 * Json数据序列化工�?
 * @author Administrator
 *
 */
public class GsonUtil {

	private static Gson mGson;
	public static Gson getGson(){
		if(mGson==null)
			mGson = new Gson();
		return mGson;
	}
	public static <T>T fromJson(String json,Class<T> classOfT){
		return getGson().fromJson(json, classOfT);
	}
}
