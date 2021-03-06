package com.lovethinking.passport.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;


public class ImageUtil {


	public static void createDirIfNotExist(String path) {
		File tmp = new File(path);
		if (!tmp.exists()) {
			tmp.mkdir();
		}
	}


	public static Bitmap getBitmapByPath(String pathName) {
//		MyLog.d("tag",pathName);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 1;
		Bitmap bm = BitmapFactory.decodeFile(pathName, options);
		return bm;
	}

	public static Bitmap getSimpleBitmapByPath(int desWidth,int desHeight,String pathName){
		BitmapFactory.Options opt = new BitmapFactory.Options();

		opt.inJustDecodeBounds = true;
		Bitmap bm = BitmapFactory.decodeFile(pathName, opt);
		int rateW=opt.outWidth/desWidth;
		int rateH=opt.outHeight/desHeight;
		int rate=Math.max(rateW, rateH);
		if(rate<1)rate=1;

		opt.inSampleSize=rate;
		opt.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(pathName, opt);
		return bm;
	}


	public static Bitmap addText2Bitmap(Bitmap bitmap, String text) {
		Bitmap bm = bitmap.copy(Bitmap.Config.ARGB_8888, true);
		Canvas canvas = new Canvas(bm);

		Paint p = new Paint();
		p.setColor(Color.YELLOW);
		p.setTextSize(30);
		p.setAntiAlias(true);

		canvas.drawText(text, 12.0f, 40.0f, p);
		canvas.save();
		return bm;
	}


	public static void saveBitmapAsJPG(Bitmap bitmap, String pathName) {
		if (!pathName.substring(pathName.length() - 4).toLowerCase()
				.equals(".jpg"))
			pathName += ".jpg";

		File f = new File(pathName);
		FileOutputStream fOut = null;
		try {
			f.createNewFile();
			fOut = new FileOutputStream(f);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut)) {
				fOut.flush();
				fOut.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * @Title       : isImage
	 * @Description	: TODO
	 * @param       : @param absolutePath
	 * @param       : @return
	 * @return      : boolean
	 * @throws
	 */
	public static boolean isImage(String fileName) {
		String imgExts[]={".png",".bmp",".jpg",".PNG",".BMP",".JPG"};
		if(fileName.length()<4)return false;
		String extName = fileName.substring(fileName.length()-4, fileName.length());
		for(String ext:imgExts){
			if(ext.equals(extName))
				return true;
		}
		return false;
	}
	

	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}


	public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h) {
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			int newWidth = w;
			int newHeight = h;
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
					height, matrix, true);
			return resizedBitmap;
		} else {
			return null;
		}
	}


}
