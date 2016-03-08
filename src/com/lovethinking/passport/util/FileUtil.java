package com.lovethinking.passport.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;


public class FileUtil {
	private static final String TAG = FileUtil.class.getSimpleName();

	public static String getSDPath(Context context) {
		File skRoot = Environment.getExternalStorageDirectory();
		return skRoot.getPath() + "/";
	}

	public static String getFilesPath(Context context) {
		File file = context.getFilesDir();
		return file.getPath() + "/";
	}


	public static List<String> getFileNameList(File dir) {
		if (dir != null && !dir.isDirectory()) {
			return null;
		}
		List<String> list = new ArrayList<String>();

		if (!dir.getAbsolutePath().equals(new File("/").getAbsolutePath()))
			list.add("..");
		for (String fileName : dir.list()) {
			list.add(fileName);
		}
		return list;
	}

	public static List<File> getFileList(File dir) {
		return getFileList(dir,(FileFilter)null);
	}


	public static List<File> getFileList(File dir, FilenameFilter filenameFilter) {
		if (dir != null && !dir.isDirectory()) {
			return null;
		}
		List<File> list = new ArrayList<File>();

		if (!dir.getAbsolutePath().equals(new File("/").getAbsolutePath()))
			list.add(dir.getParentFile());
		for (File file : dir.listFiles(filenameFilter)) {
			list.add(file);
		}
		return list;
	}

	public static List<File> getFileList(File dir, FileFilter fileFilter) {
		if (dir != null && !dir.isDirectory()) {
			return null;
		}
		List<File> list = new ArrayList<File>();

		if (!dir.getAbsolutePath().equals(new File("/").getAbsolutePath()))
			list.add(dir.getParentFile());
		for (File file : dir.listFiles(fileFilter)) {
			list.add(file);
		}
		return list;
	}


	public static boolean hasSDCard() {
		return Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}


	public static boolean hasDir(String dirPathName) {
		File dir = new File(dirPathName);
		return dir.exists();
	}


	public static boolean hasFile(String filePathName) {
		File file = new File(filePathName);
		return file.exists();
	}


	public static File creatFile(String filePathName) throws IOException {
		File file = new File(filePathName);
		file.createNewFile();
		return file;
	}


	public static boolean delFile(String filePathName) {
		File file = new File(filePathName);
		if (file == null || !file.exists() || file.isDirectory())
			return false;
		file.delete();
		MyLog.d(TAG, " delete file ok!----"+filePathName);
		return true;
	}

	public static File creatDir(String dirPathName) {
		File dir = new File(dirPathName);
		dir.mkdir();
		return dir;
	}


	public static boolean delDir(String dirPathName) {
		File dir = new File(dirPathName);
		return delDir(dir);
	}


	public static boolean renameFile(String oldFilePathName,
			String newFilePathName) {
		File oleFile = new File(oldFilePathName);
		File newFile = new File(newFilePathName);
		return oleFile.renameTo(newFile);
	}


	public static boolean copyFileTo(String srcFilePathName,
			String destFilePathName) throws IOException {
		File srcFile = new File(srcFilePathName);
		File destFile = new File(destFilePathName);
		return copyFileTo(srcFile, destFile);
	}


	public static boolean copyFilesTo(String srcDirPathName,
			String destDirPathName) throws IOException {
		File srcDir = new File(srcDirPathName);
		File destDir = new File(destDirPathName);
		return copyFilesTo(srcDir, destDir);
	}


	public static boolean moveFileTo(String srcFilePathName,
			String destFilePathName) throws IOException {
		File srcFile = new File(srcFilePathName);
		File destFile = new File(destFilePathName);
		return moveFileTo(srcFile, destFile);
	}


	public static boolean moveFilesTo(String srcDirPathName,
			String destDirPathName) throws IOException {
		File srcDir = new File(srcDirPathName);
		File destDir = new File(destDirPathName);
		return moveFilesTo(srcDir, destDir);
	}


	public static void writeFile(OutputStream os, String content)
			throws IOException {
		os.write(content.getBytes());
	}


	public static void appendFile(OutputStream os, String content)
			throws IOException {
		os.write(content.getBytes());
	}


	public static byte[] readFile(InputStream is) throws IOException {
		byte[] b = new byte[is.available()];
		is.read(b);
		return b;
	}


	public static boolean delFile(File file) {
		if (file.isDirectory())
			return false;
		return file.delete();
	}


	public static boolean delDir(File dir) {
		if (dir == null || !dir.exists() || dir.isFile()) {
			return false;
		}
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				delDir(file);
			}
		}
		dir.delete();
		return true;
	}


	public static boolean copyFileTo(File srcFile, File destFile)
			throws IOException {
		if (srcFile.isDirectory() || destFile.isDirectory())
			return false;
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(destFile);
		int readLen = 0;
		byte[] buf = new byte[1024];
		while ((readLen = fis.read(buf)) != -1) {
			fos.write(buf, 0, readLen);
		}
		fos.flush();
		fos.close();
		fis.close();
		return true;
	}


	public static boolean copyFilesTo(File srcDir, File destDir)
			throws IOException {
		if (!srcDir.isDirectory() || !destDir.isDirectory())
			return false;
		if (!destDir.exists())
			return false;
		File[] srcFiles = srcDir.listFiles();
		for (int i = 0; i < srcFiles.length; i++) {
			if (srcFiles[i].isFile()) {
				File destFile = new File(destDir.getPath() + "/"
						+ srcFiles[i].getName());
				copyFileTo(srcFiles[i], destFile);
			} else if (srcFiles[i].isDirectory()) {
				File theDestDir = new File(destDir.getPath() + "/"
						+ srcFiles[i].getName());
				copyFilesTo(srcFiles[i], theDestDir);
			}
		}
		return true;
	}

	public static boolean moveFileTo(File srcFile, File destFile)
			throws IOException {
		boolean iscopy = copyFileTo(srcFile, destFile);
		if (!iscopy)
			return false;
		delFile(srcFile);
		return true;
	}

	public static boolean moveFilesTo(File srcDir, File destDir)
			throws IOException {
		if (!srcDir.isDirectory() || !destDir.isDirectory()) {
			return false;
		}
		File[] srcDirFiles = srcDir.listFiles();
		for (int i = 0; i < srcDirFiles.length; i++) {
			if (srcDirFiles[i].isFile()) {
				File oneDestFile = new File(destDir.getPath() + "/"
						+ srcDirFiles[i].getName());
				moveFileTo(srcDirFiles[i], oneDestFile);
				delFile(srcDirFiles[i]);
			} else if (srcDirFiles[i].isDirectory()) {
				File oneDestFile = new File(destDir.getPath() + "/"
						+ srcDirFiles[i].getName());
				moveFilesTo(srcDirFiles[i], oneDestFile);
				delDir(srcDirFiles[i]);
			}
		}
		return true;
	}
}
