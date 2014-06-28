/**
 * @Project     : Utility
 * @Title       : FileXUtil.java
 * @Package     : com.pr.baby.util
 * @Description : TODO
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-8-16 ����03:59:37
 * @Copyright   : 2011 ZGX Inc. All rights reserved.
 * @version     : V1.0
 */
package com.lovethinking.passport.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

/**
 * @ClassName   : FileXUtil
 * @Description : TODO
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-8-16 ����03:59:37
 * 
 */
public class FileXUtil extends FileUtil {

	/************************** SD���ϵĲ��� ***********************/
	/**
	 * ��SD���ϴ����ļ�
	 */
	public static File creatSDFile(Context context,String fileName) throws IOException {
		return creatFile(getSDPath(context) + fileName);
	}

	/**
	 * ɾ��SD���ϵ��ļ�
	 */
	public static boolean delSDFile(Context context,String fileName) {
		return delFile(getSDPath(context) + fileName);
	}

	/**
	 * ��SD���ϴ���Ŀ¼
	 */
	public static File creatSDDir(Context context,String dirName) {
		return creatDir(getSDPath(context) + dirName);
	}

	/**
	 * ɾ��SD���ϵ�Ŀ¼
	 */
	public static boolean delSDDir(Context context,String dirName) {
		return delDir(getSDPath(context) + dirName);
	}

	/**
	 * �޸�SD���ϵ��ļ���Ŀ¼��
	 */
	public static boolean renameSDFile(Context context,String oldfileName, String newFileName) {
		return renameFile(getSDPath(context) + oldfileName, getSDPath(context) + oldfileName);
	}

	/**
	 * ����SD���ϵĵ����ļ�
	 */
	public static boolean copySDFileTo(Context context,String srcFileName, String destFileName)
			throws IOException {
		return copyFileTo(getSDPath(context) + srcFileName, getSDPath(context) + destFileName);
	}

	/**
	 * ����SD����ָ��Ŀ¼�������ļ�
	 */
	public static boolean copySDFilesTo(Context context,String srcDirName, String destDirName)
			throws IOException {
		return copyFilesTo(getSDPath(context) + srcDirName, getSDPath(context) + srcDirName);
	}

	/**
	 * �ƶ�SD���ϵĵ����ļ�
	 */
	public static boolean moveSDFileTo(Context context,String srcFileName, String destFileName)
			throws IOException {
		return moveFilesTo(getSDPath(context) + srcFileName, getSDPath(context) + destFileName);
	}

	/**
	 * �ƶ�SD���ϵ�ָ��Ŀ¼�������ļ�
	 */
	public static boolean moveSDFilesTo(Context context,String srcDirName, String destDirName)
			throws IOException {
		return moveFilesTo(getSDPath(context) + srcDirName, getSDPath(context) + destDirName);
	}

	/*
	 * ���ļ�д��sd������:writeSDFile("test.txt","hello");
	 */
	public static void writeSDFile(Context context,String fileName, String content)
			throws IOException {
		File file = new File(getSDPath(context) + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		writeFile(fos, content);
	}

	/*
	 * ��ԭ���ļ��ϼ���д�ļ�����:appendSDFile("test.txt","hello");
	 */
	public static void appendSDFile(Context context,String fileName, String content)
			throws IOException {
		File file = new File(getSDPath(context) + fileName);
		FileOutputStream fos = new FileOutputStream(file, true);
		writeFile(fos, content);
	}

	/*
	 * ��SD����ȡ�ļ�����:readSDFile("test.txt");
	 */
	public static byte[] readSDFile(Context context,String fileName) throws IOException {
		File file = new File(getSDPath(context) + fileName);
		FileInputStream fis = new FileInputStream(file);
		return readFile(fis);
	}

	/************************ ˽��Ŀ¼�ļ��Ĳ��� *********************/
	/**
	 * ����˽���ļ�
	 */
	public static File creatDataFile(Context context,String fileName) throws IOException {
		return creatFile(getFilesPath(context) + fileName);
	}

	/**
	 * ����˽��Ŀ¼
	 */
	public static File creatDataDir(Context context,String dirName) {
		return creatDir(getFilesPath(context) + dirName);
	}

	/**
	 * ɾ��˽���ļ�
	 */
	public static boolean delDataFile(Context context,String fileName) {
		return delFile(getFilesPath(context) + fileName);
	}

	/**
	 * ɾ��˽��Ŀ¼
	 */
	public static boolean delDataDir(Context context,String dirName) {
		return delFile(getFilesPath(context) + dirName);
	}

	/**
	 * ���˽���ļ���Ŀ¼��
	 */
	public static boolean renameDataFile(Context context,String oldName, String newName) {
		return renameFile(getFilesPath(context) + oldName, getFilesPath(context) + newName);
	}

	/**
	 * ��˽��Ŀ¼�½����ļ�����
	 */
	public static boolean copyDataFileTo(Context context,String srcFileName, String destFileName)
			throws IOException {
		return copyFileTo(getFilesPath(context) + srcFileName, getFilesPath(context) + destFileName);
	}

	/**
	 * ����˽��Ŀ¼��ָ��Ŀ¼�������ļ�
	 */
	public static boolean copyDataFilesTo(Context context,String srcDirName, String destDirName)
			throws IOException {
		return copyFilesTo(getFilesPath(context) + srcDirName, getFilesPath(context) + destDirName);
	}

	/**
	 * �ƶ�˽��Ŀ¼�µĵ����ļ�
	 */
	public static boolean moveDataFileTo(Context context,String srcFileName, String destFileName)
			throws IOException {
		return moveFileTo(getFilesPath(context) + srcFileName, getFilesPath(context) + destFileName);
	}

	/**
	 * �ƶ�˽��Ŀ¼�µ�ָ��Ŀ¼�µ������ļ�
	 */
	public static boolean moveDataFilesTo(Context context,String srcDirName, String destDirName)
			throws IOException {
		return moveFilesTo(getFilesPath(context) + srcDirName, getFilesPath(context) + destDirName);
	}

	/*
	 * ���ļ�д��Ӧ��˽�е�filesĿ¼����:writeFile("test.txt","hello");
	 */
	public static void wirteFile(Context context, String fileName,
			String content) throws IOException {
		OutputStream os = context.openFileOutput(fileName,
				Context.MODE_WORLD_WRITEABLE);
		writeFile(os, content);
	}

	/*
	 * ��ԭ���ļ��ϼ���д�ļ�����:appendFile("test.txt","hello");
	 */
	public static void appendFile(Context context, String fileName,
			String content) throws IOException {
		OutputStream os = context.openFileOutput(fileName, Context.MODE_APPEND);
		writeFile(os, content);
	}

	/*
	 * ��Ӧ�õ�˽��Ŀ¼files��ȡ�ļ�����:readFile("test.txt");
	 */
	public static byte[] readFile(Context context,String fileName) throws IOException {
		InputStream is = context.openFileInput(fileName);
		return readFile(is);
	}
}
