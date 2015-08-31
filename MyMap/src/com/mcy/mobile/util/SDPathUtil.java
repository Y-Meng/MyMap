package com.mcy.mobile.util;

import java.io.File;
import android.os.Environment;

public class SDPathUtil {
	
	private static String appName = "userdata";
	
	public static String sdLocationFilePath(){
		String path = Environment.getExternalStorageDirectory()+"/"+appName+"/Location";
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
		return  path + "/";
	}
	
	
	public static String sdTileFileTDLY(){
		String path = Environment.getExternalStorageDirectory()+"/"+appName+"/Map/Layers.tpk";
		return  path ;
	}
	
	public static String sdTileFileCSLW(){
		String path = Environment.getExternalStorageDirectory()+"/"+appName+"/Map/Road1_4000.tpk";
		return  path ;
	}
	
	public static String sdTileFileYGYX(){
		String path = Environment.getExternalStorageDirectory()+"/"+appName+"/Map/XA102014410800YGYX1_8000.tpk";
		return  path ;
	}
	
	public static String sdTileFileXZQH(){
		String path = Environment.getExternalStorageDirectory()+"/"+appName+"/Map/XA102011410800XZQH.tpk";
		return  path ;
	}
	
	
	public static String sdMediaPath() {
		File sd = Environment.getExternalStorageDirectory();
		String path = sd.getPath() + "/"+appName+"/Media";
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
		return path + "/";
	}
	
	
	public static String sdDBPath() {
		File sd = Environment.getExternalStorageDirectory();
		String path = sd.getPath() + "/"+appName+"/DataBase";
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
		return path + "/";
	}
	
	
	public static String sdParamPath(){
		String path = Environment.getExternalStorageDirectory()+"/"+appName+"/Params";
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
		return  path + "/";
	}


	public static String getPatrolPath(String mDZJGH, String mRWLX,String node) {
		String path = "";
		if(node.isEmpty())
		    path = Environment.getExternalStorageDirectory()+"/"+appName+"/Media/"+mDZJGH+"/"+mRWLX;
		else
			path = Environment.getExternalStorageDirectory()+"/"+appName+"/Media/"+mDZJGH+"/"+mRWLX+"/"+node;
		File file = new File(path);
		if(!file.exists())
			file.mkdirs();
		return path+"/";
	}
	

//	public static String sdPicPath() {
//		File sd = Environment.getExternalStorageDirectory();
//		String path = sd.getPath() + "/DIST_File/Picture";
//		File dir = new File(path);
//		if (!dir.exists())
//			dir.mkdirs();
//		return  path + "/";
//	}
//

//   public static String sdVedioPath() {
//		String str = "";
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {
//			File path = Environment.getExternalStorageDirectory();
//			str = path.getPath() + "/DIST_File/Video";
//		} else {
//			File path = Environment.getRootDirectory();
//			str = path.getPath() + "/DIST_File/Video";
//		}
//		
//		File dir = new File(str);
//		if(!dir.exists())
//			dir.mkdirs();
//		
//		return str+"/";
//	}
}
