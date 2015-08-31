package com.mcy.mobile.util;

import java.lang.ref.WeakReference;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;

public class BitmapUtil {

	/**
	 * 缩放图片
	 * 
	 * @param filepath
	 * @param wSpec
	 * @param hSpec
	 * @return  
	 */
	public static Bitmap scaleFromFile(String filepath, int wSpec, int hSpec) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		// 只获取尺�?
		opts.inJustDecodeBounds = true;
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		// 解码文件
		BitmapFactory.decodeFile(filepath, opts);
		if (opts.outWidth != -1) {
			int width = opts.outWidth;
			int height = opts.outHeight;
			float scaleWidth = 0.f, scaleHeight = 0.f;
			if (width > wSpec || height > hSpec) {
				//重新计算尺寸
				scaleWidth = ((float) width) / wSpec;
				scaleHeight = ((float) height) / hSpec;
			}
			opts.inJustDecodeBounds = false;
			opts.inSampleSize = (int) Math.max(scaleWidth, scaleHeight);
			WeakReference<Bitmap> weak = new WeakReference<Bitmap>(
					BitmapFactory.decodeFile(filepath, opts));
			// return Bitmap.createScaledBitmap(weak.get(), w, h, true);
			return Bitmap.createBitmap(weak.get());
		}
		return null;
	}

	/**
	 * 获取视频缩略�?
	 */
	public static Bitmap getVideoThumbnail(ContentResolver cr, Uri uri) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inDither = false;
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Cursor cursor = cr.query(uri,
				new String[] { MediaStore.Video.Media._ID }, null, null, null);

		if (cursor == null || cursor.getCount() == 0) {
			return null;
		}
		cursor.moveToFirst();
		String videoId = cursor.getString(cursor
				.getColumnIndex(MediaStore.Video.Media._ID));
		// image id in image table.s
		if (videoId == null) {
			return null;
		}
		cursor.close();
		long videoIdLong = Long.parseLong(videoId);
		bitmap = MediaStore.Video.Thumbnails.getThumbnail(cr, videoIdLong,
				Images.Thumbnails.MICRO_KIND, options);
		return bitmap;
	}
}
