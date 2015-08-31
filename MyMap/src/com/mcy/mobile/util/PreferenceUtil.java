package com.mcy.mobile.util;

import java.util.Set;
import android.content.SharedPreferences;

public class PreferenceUtil {

	/**
	 * д������
	 * 
	 * @param sp
	 * @param key
	 * @param value
	 */
	public static void write(SharedPreferences sp, String key, String value) {
		sp.edit().putString(key, value).commit();
	}

	public static void write(SharedPreferences sp, String key, Boolean value) {
		sp.edit().putBoolean(key, value).commit();
	}

	public static void write(SharedPreferences sp, String key, Float value) {
		sp.edit().putFloat(key, value).commit();
	}

	public static void write(SharedPreferences sp, String key, Integer value) {
		sp.edit().putInt(key, value).commit();
	}

	public static void write(SharedPreferences sp, String key, Long value) {
		sp.edit().putLong(key, value).commit();
	}

	public static void write(SharedPreferences sp, String key,
			Set<String> values) {
		sp.edit().putStringSet(key, values).commit();
	}

	/**
	 * ��ȡ����
	 * 
	 * @param key
	 * @return ����value
	 */
	public static String read(SharedPreferences sp, String key, String defValue) {
		return sp.getString(key, defValue);
	}

	public static int read(SharedPreferences sp, String key, int defValue) {
		return sp.getInt(key, defValue);
	}

	public static float read(SharedPreferences sp, String key, float defValue) {
		return sp.getFloat(key, defValue);
	}

	public static long read(SharedPreferences sp, String key, long defValue) {
		return sp.getLong(key, defValue);
	}

	public static Boolean read(SharedPreferences sp, String key,
			Boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public static Set<String> read(SharedPreferences sp, String key,
			Set<String> defValue) {
		return sp.getStringSet(key, defValue);
	}
}
