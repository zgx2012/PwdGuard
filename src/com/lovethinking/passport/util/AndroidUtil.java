package com.lovethinking.passport.util;

import java.util.ArrayList;
import java.util.UUID;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

/**
 * @ClassName : AndroidUtil
 * @Description : TODO
 * @author : zgx2012@gmail.com
 * @date : 2011-9-5 05:20:09
 * 
 */
public class AndroidUtil {

	public static boolean isInternetConnected(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			return false;
		}
		return true;
	}

	/**
	 * (UUID)
	 */
	public static String getUUID() {
		String[] str = UUID.randomUUID().toString().split("-");
		String uuidStr = "";
		for (String s : str) {
			uuidStr += s;
		}
		return uuidStr;
	}

	/**
	 * Toast
	 */
	public static void toastMsg(Context context, String msg, int duration) {
		if (context != null)
			Toast.makeText(context, msg, duration).show();
	}

	/**
	 * Toast
	 */
	public static void toastMsg(Context context, int resId, int duration) {
		if (context != null)
			Toast.makeText(context, context.getResources().getString(resId),
					duration).show();
	}

	public static void sendMessage(Handler handler, int what) {
		Message msg = new Message();
		msg.what = what;
		handler.sendMessage(msg);
	}

	public static void sendMessage(Handler handler, int what, Object obj) {
		Message msg = new Message();
		msg.what = what;
		msg.obj = obj;
		handler.sendMessage(msg);
	}

	/**
	 * @return
	 */
	public static Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	/**
	 * 
	 * @return
	 */
	public static Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	/**
	 * 
	 * @return
	 */
	public static Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	/**
	 * 
	 * @return
	 */
	public static Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(500);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}

	public static boolean serviceIsWorking(Context context, Class<?> clazz) {
		ActivityManager myManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager
				.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString()
					.equals(clazz.getName())) {// "pr.android.taojia.service.MsgService"
				return true;
			}
		}
		return false;
	}
}
