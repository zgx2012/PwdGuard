/**
 * @Project     : Taojia-V3
 * @Title       : TjSharePreferences.java
 * @Package     : pr.android.taojia.app
 * @Description : TODO
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-9-7 下午04:14:44
 * @Copyright   : 2011 http://www.pengruikeji.com/ Inc. All rights reserved.
 * @version     : V1.0
 */
package com.lovethinking.passport.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.lovethinking.passport.constant.Constant;

/**
 * @ClassName : TjSharePreferences
 * @Description : TODO
 * @author : ZGX zhangguoxiao_happy@163.com
 * @date : 2011-9-7 下午04:14:44
 * 
 */
public class BabySharePreferences implements Constant {

    private static SharedPreferences mSysProfile;

    public static int getNotifyId(Context context) {
        if (mSysProfile == null)
            mSysProfile = context.getSharedPreferences(SYSPROFILE, 0);
        int nId = mSysProfile.getInt("notify_id", 0);
        return nId;
    }

    public static void saveNotifyId(Context context, int nId) {
        if (mSysProfile == null)
            mSysProfile = context.getSharedPreferences(SYSPROFILE, 0);
        mSysProfile.edit().putInt("notify_id", nId).commit();
        MyLog.i("saveNotifyId", nId);
    }

    public static int getVersionId(Context context) {
        if (mSysProfile == null)
            mSysProfile = context.getSharedPreferences(SYSPROFILE, 0);
        int vid = mSysProfile.getInt("client_version_id", 0);
        return vid;
    }

    public static void saveVersionId(Context context, int vid) {
        if (mSysProfile == null)
            mSysProfile = context.getSharedPreferences(SYSPROFILE, 0);
        mSysProfile.edit().putInt("client_version_id", vid).commit();
    }

    public static void saveIsSysFirstLoad(Context context) {
        if (mSysProfile == null)
            mSysProfile = context.getSharedPreferences(SYSPROFILE, 0);
        mSysProfile.edit().putInt("is_firstload", 1).commit();
    }

    public static boolean isSysFirstLoad(Context context) {
        if (mSysProfile == null)
            mSysProfile = context.getSharedPreferences(SYSPROFILE, 0);
        int is_firstload = mSysProfile.getInt("is_firstload", 0);
        if (is_firstload == 0)
            return true;
        return false;
    }
}
