package com.lovethinking.passport.db;

import java.util.List;
import java.util.Map;

import com.lovethinking.passport.constant.DbConstant;
import com.lovethinking.passport.util.MyLog;

import android.content.ContentValues;
import android.content.Context;

public class PwdBusinessHelper extends DatabaseHelper implements DbConstant {

    public void createTable(Context context) {
        createTables(context, CREATE_TABLE_LOGIN);
        createTables(context, CREATE_TABLE_PWD);
    }

    public boolean loginConfirm(Context context, String username,
            String password) {
        String sql = "";
        sql += "select * from " + TABLE_LOGIN + " where username = " + username
                + " and password = " + password;
        List<Map<String, Object>> list = executeQuery(context, sql);
        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    public Map<String, Object> getLoginPwd(Context context) {
        String sql = "";
        sql += "select * from " + TABLE_LOGIN;
        List<Map<String, Object>> list = executeQuery(context, sql);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<Map<String, Object>> getSavedPwd(Context context) {
        String sql = "";
        sql += "select * from " + TABLE_PWD;
        List<Map<String, Object>> list = executeQuery(context, sql);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }

    public boolean registerLogin(Context context, String username,
            String password) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_LOGIN_USERNAME, username);
        cv.put(KEY_LOGIN_PASSWORD, password);
        long ret = mDatabase.insert(TABLE_LOGIN, null, cv);
        if (ret == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addSavedPwd(Context context, String username,
            String password, String what) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_PWD_USERNAME, username);
        cv.put(KEY_PWD_PASSWORD, password);
        cv.put(KEY_PWD_WHAT, what);
        long ret = mDatabase.insert(TABLE_PWD, null, cv);
        if (ret == -1) {
            return false;
        } else {
            return true;
        }
    }

}
