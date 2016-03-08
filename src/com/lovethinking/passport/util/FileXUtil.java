package com.lovethinking.passport.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

public class FileXUtil extends FileUtil {


	public static File creatSDFile(Context context,String fileName) throws IOException {
		return creatFile(getSDPath(context) + fileName);
	}


	public static boolean delSDFile(Context context,String fileName) {
		return delFile(getSDPath(context) + fileName);
	}


	public static File creatSDDir(Context context,String dirName) {
		return creatDir(getSDPath(context) + dirName);
	}


	public static boolean delSDDir(Context context,String dirName) {
		return delDir(getSDPath(context) + dirName);
	}


	public static boolean renameSDFile(Context context,String oldfileName, String newFileName) {
		return renameFile(getSDPath(context) + oldfileName, getSDPath(context) + oldfileName);
	}


	public static boolean copySDFileTo(Context context,String srcFileName, String destFileName)
			throws IOException {
		return copyFileTo(getSDPath(context) + srcFileName, getSDPath(context) + destFileName);
	}


	public static boolean copySDFilesTo(Context context,String srcDirName, String destDirName)
			throws IOException {
		return copyFilesTo(getSDPath(context) + srcDirName, getSDPath(context) + srcDirName);
	}


	public static boolean moveSDFileTo(Context context,String srcFileName, String destFileName)
			throws IOException {
		return moveFilesTo(getSDPath(context) + srcFileName, getSDPath(context) + destFileName);
	}


	public static boolean moveSDFilesTo(Context context,String srcDirName, String destDirName)
			throws IOException {
		return moveFilesTo(getSDPath(context) + srcDirName, getSDPath(context) + destDirName);
	}


	public static void writeSDFile(Context context,String fileName, String content)
			throws IOException {
		File file = new File(getSDPath(context) + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		writeFile(fos, content);
	}


	public static void appendSDFile(Context context,String fileName, String content)
			throws IOException {
		File file = new File(getSDPath(context) + fileName);
		FileOutputStream fos = new FileOutputStream(file, true);
		writeFile(fos, content);
	}


	public static byte[] readSDFile(Context context,String fileName) throws IOException {
		File file = new File(getSDPath(context) + fileName);
		FileInputStream fis = new FileInputStream(file);
		return readFile(fis);
	}


	public static File creatDataFile(Context context,String fileName) throws IOException {
		return creatFile(getFilesPath(context) + fileName);
	}


	public static File creatDataDir(Context context,String dirName) {
		return creatDir(getFilesPath(context) + dirName);
	}


	public static boolean delDataFile(Context context,String fileName) {
		return delFile(getFilesPath(context) + fileName);
	}

	public static boolean delDataDir(Context context,String dirName) {
		return delFile(getFilesPath(context) + dirName);
	}


	public static boolean renameDataFile(Context context,String oldName, String newName) {
		return renameFile(getFilesPath(context) + oldName, getFilesPath(context) + newName);
	}


	public static boolean copyDataFileTo(Context context,String srcFileName, String destFileName)
			throws IOException {
		return copyFileTo(getFilesPath(context) + srcFileName, getFilesPath(context) + destFileName);
	}


	public static boolean copyDataFilesTo(Context context,String srcDirName, String destDirName)
			throws IOException {
		return copyFilesTo(getFilesPath(context) + srcDirName, getFilesPath(context) + destDirName);
	}


	public static boolean moveDataFileTo(Context context,String srcFileName, String destFileName)
			throws IOException {
		return moveFileTo(getFilesPath(context) + srcFileName, getFilesPath(context) + destFileName);
	}


	public static boolean moveDataFilesTo(Context context,String srcDirName, String destDirName)
			throws IOException {
		return moveFilesTo(getFilesPath(context) + srcDirName, getFilesPath(context) + destDirName);
	}


	public static void wirteFile(Context context, String fileName,
			String content) throws IOException {
		OutputStream os = context.openFileOutput(fileName,
				Context.MODE_WORLD_WRITEABLE);
		writeFile(os, content);
	}


	public static void appendFile(Context context, String fileName,
			String content) throws IOException {
		OutputStream os = context.openFileOutput(fileName, Context.MODE_APPEND);
		writeFile(os, content);
	}


	public static byte[] readFile(Context context,String fileName) throws IOException {
		InputStream is = context.openFileInput(fileName);
		return readFile(is);
	}
}
