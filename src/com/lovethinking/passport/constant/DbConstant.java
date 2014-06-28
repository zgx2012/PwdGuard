package com.lovethinking.passport.constant;

public interface DbConstant extends Constant {
	/**
	 * 数据库名
	 */
	public String DB_NAME = "pwd.db";
	/**
	 * 数据库版本
	 */
	public int DB_VERSION = 1;
	/*
	 * 登录信息表
	 */
	public String TABLE_LOGIN = "login";
	
	public String KEY_LOGIN_ID = "_id";
	public String KEY_LOGIN_USERNAME = "username";
	public String KEY_LOGIN_PASSWORD = "password";

	public String FIELD_LOGIN = "("
		+ KEY_LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ KEY_LOGIN_USERNAME + " TEXT,"
		+ KEY_LOGIN_PASSWORD + " TEXT)";
	public String CREATE_TABLE_LOGIN = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_LOGIN + FIELD_LOGIN;
	/*
	 * 密码信息表
	 */
	public String TABLE_PWD = "pwd";
	
	public String KEY_PWD_ID = "_id";
	public String KEY_PWD_USERNAME = "saved_username";
	public String KEY_PWD_PASSWORD = "saved_password";
	public String KEY_PWD_WHAT = "what";

	public String FIELD_PWD = "("
		+ KEY_PWD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ KEY_PWD_USERNAME + " TEXT,"
		+ KEY_PWD_PASSWORD + " TEXT,"
		+ KEY_PWD_WHAT + " TEXT)";
	public String CREATE_TABLE_PWD = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_PWD + FIELD_PWD;
}
