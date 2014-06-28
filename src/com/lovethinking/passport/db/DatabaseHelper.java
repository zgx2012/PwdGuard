/**
 * @Title       : DatabaseAdapter.java
 * @Package     : 
 * @Description : 数据库帮助文件
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-7-11 下午06:15:58
 * @Copyright   : 2011 Baby Inc. All rights reserved.
 * @version     : V1.0
 */
package com.lovethinking.passport.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lovethinking.passport.constant.DbConstant;
import com.lovethinking.passport.util.MyLog;

/**
 * @ClassName : DatabaseAdapter
 * @Description : 数据库帮助类：数据库操作的底层支持
 * @author : ZGX zhangguoxiao_happy@163.com
 * @date : 2011-7-11 下午06:15:58
 * 
 */
public abstract class DatabaseHelper implements DbConstant {
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    protected static SQLiteDatabase mDatabase;

    /**
     * 
     * @Title : openDatabase
     * @Description : 打开数据库,单例返回SQLiteDatabase
     * @param : @param context
     * @param : @return
     * @return : SQLiteDatabase
     * @throws
     */
    public synchronized SQLiteDatabase openDatabase(Context context) {
        if (null == mDatabase || !mDatabase.isOpen()) {
            mDatabase = context.openOrCreateDatabase(DB_NAME,
                    Context.MODE_PRIVATE, null);
        }
        return mDatabase;
    }

    /**
     * 
     * @Title : createTables
     * @Description : 创建表
     * @param : @param context
     * @param : @param createSQL
     * @return : void
     * @throws
     */
    public synchronized void createTables(Context context, String createSQL) {
        mDatabase = openDatabase(context);
        synchronized (mDatabase) {
            try {
                mDatabase.execSQL(createSQL);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    /**
     * 
     * @Title : dropTable
     * @Description : 删除表
     * @param : @param context
     * @param : @param tableName
     * @return : void
     * @throws
     */
    public synchronized void dropTable(Context context, String tableName) {
        mDatabase = openDatabase(context);
        synchronized (mDatabase) {
            mDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
    }

    /**
     * 
     * @Title : deleteDatabase
     * @Description : 删除数据库
     * @param : @param context
     * @return : void
     * @throws
     */
    public synchronized void deleteDatabase(Context context) {
        context.deleteDatabase(DB_NAME);
    }

    /**
     * 
     * @Title : executeQuery
     * @Description : 查询List
     * @param : @param context
     * @param : @param sql
     * @param : @return
     * @return : List<Map<String,Object>>
     * @throws
     */
    public synchronized List<Map<String, Object>> executeQuery(Context context,
            String sql) {
        mDatabase = openDatabase(context);
        synchronized (mDatabase) {
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            Cursor cursor = mDatabase.rawQuery(sql, null);
            Map<String, Object> resultMap = null;
            int columnNum = cursor.getColumnCount();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                    .moveToNext()) {
                resultMap = new HashMap<String, Object>();
                for (int i = 0; i < columnNum; i++) {
                    resultMap.put(cursor.getColumnName(i), cursor.getString(i));
                }
                resultList.add(resultMap);
            }
            cursor.close();
            if (resultList != null && resultList.size() > 0)
                return resultList;
            else
                return null;
        }
    }

    /**
     * 
     * @Title : save
     * @Description : 保存Map
     * @param : @param context
     * @param : @param tableName
     * @param : @param saveMap
     * @return : void
     * @throws
     */
    public synchronized long save(Context context, String tableName,
            Map<String, Object> saveMap) {
        mDatabase = openDatabase(context);
        synchronized (mDatabase) {
            if (saveMap == null)
                return -1;
            ContentValues cv = new ContentValues();
            for (String key : saveMap.keySet()) {
                cv.put(key, saveMap.get(key).toString());
            }
            long ret = mDatabase.insert(tableName, null, cv);
            if (-1 == ret) {
                MyLog.e(TAG, "an error occurred when save a map");
                MyLog.d(TAG, "Look : " + saveMap.toString());
            } else {
                MyLog.d(TAG, "save a map success");
            }
            return ret;
        }
    }

    /**
     * 
     * @Title : update
     * @Description : 修改
     * @param : @param context
     * @param : @param tableName
     * @param : @param saveMap
     * @param : @param whereClause
     * @return : void
     * @throws
     */
    public synchronized int update(Context context, String tableName,
            Map<String, Object> saveMap, String whereClause) {
        mDatabase = openDatabase(context);
        synchronized (mDatabase) {
            ContentValues cv = new ContentValues();
            for (String key : saveMap.keySet()) {
                cv.put(key, saveMap.get(key).toString());
            }
            int ret = mDatabase.update(tableName, cv, whereClause, null);
            MyLog.d(TAG, "update " + tableName + saveMap.toString()
                    + whereClause);
            MyLog.d(TAG, ret + " rows affected when update");
            return ret;
        }
    }

    /**
     * 
     * @Title : save
     * @Description : 保存List
     * @param : @param context
     * @param : @param tableName
     * @param : @param saveList
     * @return : void
     * @throws
     */
    public synchronized void save(Context context, String tableName,
            List<Map<String, Object>> saveList) {
        mDatabase = openDatabase(context);
        synchronized (mDatabase) {
            mDatabase.beginTransaction();
            if (null == saveList) {
                saveList = new ArrayList<Map<String, Object>>();
            }
            ContentValues cv = null;
            long ret = 0;
            for (Map<String, Object> saveMap : saveList) {
                cv = new ContentValues();
                for (String key : saveMap.keySet()) {
                    cv.put(key, saveMap.get(key).toString());
                }
                ret = mDatabase.insert(tableName, null, cv);
                if (-1 == ret) {
                    MyLog.e(TAG, "an error occurred when save a map");
                    MyLog.d(TAG, "Look : " + saveMap.toString());
                } else {
                    MyLog.d(TAG, "save a map success");
                }
            }
            if (ret >= 0) {
            }
            mDatabase.setTransactionSuccessful();
            mDatabase.endTransaction();
        }
    }

    /**
     * 
     * @Title : delete
     * @Description : 删除记录
     * @param : @param context
     * @param : @param tableName
     * @param : @param clause
     * @return : void
     * @throws
     */
    public synchronized void delete(Context context, String tableName,
            String clause) {
        mDatabase = openDatabase(context);
        synchronized (mDatabase) {
            try {
                if (clause == null || clause == "")
                    mDatabase.execSQL("DELETE FROM " + tableName);
                else
                    mDatabase.execSQL("DELETE FROM " + tableName + " WHERE "
                            + clause);
            } catch (Exception e) {
                MyLog.e(TAG, "error occurred when delete from " + tableName
                        + " success");
                MyLog.d(TAG, "Look : DELETE FROM " + tableName + " WHERE "
                        + clause);
                e.printStackTrace();
            } finally {
                MyLog.d(TAG, "delete from " + tableName + " success");
            }
        }
    }

    /**
     * 
     * @Title : executeSQL
     * @Description : 执行sql，可以是insert update delete
     * @param : @param context
     * @param : @param sql
     * @return : void
     * @throws
     */
    public synchronized void executeSQL(Context context, String sql) {
        mDatabase = openDatabase(context);
        synchronized (mDatabase) {
            mDatabase.execSQL(sql);
        }
    }

    /**
     * 
     * @Title : closeDatabase
     * @Description : 关闭，(客户端一般不用)
     * @param :
     * @return : void
     * @throws
     */
    public synchronized void closeDatabase() {
        if (null != mDatabase) {
            mDatabase.close();
            mDatabase = null;
        }
    }
}
