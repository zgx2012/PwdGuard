package com.lovethinking.passport.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;


public class ActivityUtil {
	public static int ScreenOrient(Activity activity) {
	//	int orientation = activity.getRequestedOrientation();
		int landscape = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
		int portrait = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
		int width = activity.getWindowManager().getDefaultDisplay().getWidth();
		int height = activity.getWindowManager().getDefaultDisplay()
				.getHeight();
		return width > height ? landscape : portrait;
	}

	public static void AutoBackground(Activity activity,View view, int res_back_v, int res_back_h) {
		int orient = ScreenOrient(activity);
		if (orient == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
			view.setBackgroundResource(res_back_v);
		} else {
			view.setBackgroundResource(res_back_h);
		}
	}
}
