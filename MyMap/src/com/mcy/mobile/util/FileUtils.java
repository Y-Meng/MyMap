package com.mcy.mobile.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
/**
 * 文件工具
 * @author Administrator
 *
 */
public class FileUtils {
	private String SDPATH;
	private int downLoadFileSize;
	public int getDownLoadFileSize() {
		return downLoadFileSize;
	}
	public String getSDPATH(){
		return SDPATH;
	}

	public FileUtils(){
		SDPATH = Environment.getExternalStorageDirectory() + "/";
	}
	/**
	 * 在SD卡上创建文件
	 */
	public File creatSDFile(String fileName) throws IOException{
		File file = new File(SDPATH + fileName);
		file.createNewFile();
		return file;
	}
	/**
	 * 创建目录
	 */
	public File creatSDDir(String dirName){
		File dir = new File(SDPATH + dirName);
		dir.mkdir();
		return dir;
	}
	/**
	 * 判断是否存在
	 * @param fileName
	 * @return
	 */
	public boolean isFileExist(String fileName){
		File file = new File(SDPATH + fileName);
		return file.exists();
	}
	/**
	 * 将输入流写入sd�?
	 * @param path 路径
	 * @param fileName 文件�?
	 * @param inpout 输入�?
	 * @return
	 */
	public File write2SDFromInput(String path,String fileName,InputStream input){
		File file = null;
		OutputStream output = null;
		try {
			creatSDDir(path);
			file = creatSDFile(path + fileName);
			output = new FileOutputStream(file);
			byte[] buffer = new byte[4 * 1024];
			while (input.read(buffer) != -1) {
				output.write(buffer);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public int downLoadFile(String url, String path, String filename) throws IOException {
		// ���غ���
//		filename = url.substring(url.lastIndexOf("/") + 1);
		// ��ȡ�ļ���
		URL myURL = new URL(url);
		URLConnection conn = myURL.openConnection();
		conn.connect();
		InputStream is = conn.getInputStream();
//		this.fileSize = conn.getContentLength();// ������Ӧ��ȡ�ļ���С
//		if (this.fileSize <= 0)
//			throw new RuntimeException("�޷���֪�ļ���С ");
		if (is == null)
		{
//			throw new RuntimeException("stream is null");
			return -1;
		}else {
//			FileUtils util = new FileUtils();
			creatSDDir(path);
			File file = creatSDFile(path + filename);
			FileOutputStream fos = new FileOutputStream(file);
			try {
				// �����ݴ���·��+�ļ���
				byte buf[] = new byte[1024];
				downLoadFileSize = 0;
//				handler.sendEmptyMessage(0);
				do {
					// ѭ����ȡ
					int numread = is.read(buf);
					if (numread == -1) {
						break;
					}
					fos.write(buf, 0, numread);
					downLoadFileSize += numread;
//				handler.sendEmptyMessage(1); // ���½�����
				} while (true);
//			handler.sendEmptyMessage(2); // ֪ͨ�������?
				is.close();
				fos.close();
				return 2;
			} catch (Exception e) {
				// TODO: handle exception
				is.close();
				fos.close();
				file.delete();
				return -1;
			}
		}
	}
	/**
	 * 使用系统软件打开文件
	 * @param path
	 */
	public static void openFileByMIME(Context context,File file){
		Intent intent = new Intent(); 
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
	    //设置intent的Action属�?? 
	    intent.setAction(Intent.ACTION_VIEW); 
	    //获取文件file的MIME类型 
	    String type = getMIMEType(file); 
	    //设置intent的data和Type属�?��?? 
	    intent.setDataAndType(Uri.fromFile(file), type); 
	    //跳转 
	    context.startActivity(intent); 
	}
	
	/**
	 * 获取MIME标签
	 */
	private static String getMIMEType(File file){
		String type = "*/*";
		String fName = file.getName();
		
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		
		String end = fName.substring(dotIndex, fName.length()).toLowerCase(Locale.CHINA);
		if (end == "")
			return type;
		
		
		for (int i = 0; i < MIME_MapTable.length; i++) {
			
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}
	
	private static final String[][] MIME_MapTable = {
			
			{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" },
			{ ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" },
			{ ".c", "text/plain" },
			{ ".class", "application/octet-stream" },
			{ ".conf", "text/plain" },
			{ ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".exe", "application/octet-stream" },
			{ ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" },
			{ ".h", "text/plain" },
			{ ".htm", "text/html" },
			{ ".html", "text/html" },
			{ ".jar", "application/java-archive" },
			{ ".java", "text/plain" },
			{ ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" },
			{ ".js", "application/x-javascript" },
			{ ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" },
			{ ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" },
			{ ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" },
			{ ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" },
			{ ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" },
			{ ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" },
			{ ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".prop", "text/plain" }, { ".rc", "text/plain" },
			{ ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
			{ ".z", "application/x-compress" },
			{ ".zip", "application/x-zip-compressed" }, { "", "*/*" } };
}
