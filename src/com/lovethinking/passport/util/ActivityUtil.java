/**
 * @Project     : Baby-V1
 * @Title       : ActivityUtil.java
 * @Package     : com.pr.baby.util
 * @Description : TODO
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-9-2 ����11:35:57
 * @Copyright   : 2011 http://www.pengruikeji.com/ Inc. All rights reserved.
 * @version     : V1.0
 */
package com.lovethinking.passport.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;

/**
 * @ClassName   : ActivityUtil
 * @Description : TODO
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-9-2 ����11:35:57
 * 
 */
public class ActivityUtil {
	// ��õ�ǰ����Ļ����
	public static int ScreenOrient(Activity activity) {
		// ȡ�õ�ǰ��Ļ�ķ�������ֵΪ-1��ʾandroidManifest.xmlû������Android:screanOrentation�������������޷��ж���Ļ����
		// ����ʹ����һ��˼·�������ȴ��ڸ߶ȵ�Ϊ����������Ϊ������
	//	int orientation = activity.getRequestedOrientation();// �õ���Ļ����
		int landscape = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;// ������̬����
		int portrait = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;// ��������
		int width = activity.getWindowManager().getDefaultDisplay().getWidth();// �õ�ϵͳ��ʾ���Ժ�õ���Ļ���
		int height = activity.getWindowManager().getDefaultDisplay()
				.getHeight();// �õ���Ļ�߶�
		return width > height ? landscape : portrait;// �ж�
	}

	public static void AutoBackground(Activity activity,View view, int res_back_v, int res_back_h) {
		int orient = ScreenOrient(activity);
		if (orient == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) { // ����
			view.setBackgroundResource(res_back_v);
		} else { // ����
			view.setBackgroundResource(res_back_h);
		}
	}
}
