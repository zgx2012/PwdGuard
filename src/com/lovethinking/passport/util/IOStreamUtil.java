/**
 * @Project     : TaoJia-V2
 * @Title       : IOStreamUtil.java
 * @Package     : Utility
 * @Description : TODO
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-7-19 ����05:06:09
 * @Copyright   : 2011 ZGX Inc. All rights reserved.
 * @version     : V1.0
 */
package com.lovethinking.passport.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ClassName   : IOStreamUtil
 * @Description : TODO
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-7-19 ����05:06:09
 * 
 */
public class IOStreamUtil {
	public static String convertStreamToString(final InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * finally { try{ is.close(); } catch(IOException e){
		 * e.printStackTrace(); } }
		 */
		return sb.toString();
	}
	
	/**
	 * ��ȡ��ͼƬ�Ķ��������
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		// ����һ��ByteArrayOutputStream
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// ����һ��������
		byte[] buffer = new byte[1024];
		int len = 0;
		// �ж������������Ƿ����-1�����ǿ�
		while ((len = inStream.read(buffer)) != -1) {
			// �ѻ����������д�뵽������У���0��ʼ��ȡ������Ϊlen
			outStream.write(buffer, 0, len);
		}
		// �ر�������
		inStream.close();
		return outStream.toByteArray();
	}
	
	public static void saveByte2File(String filePath, byte[] data) {
		// ����һ���ļ������
		FileOutputStream outStream;
		try {
			File imageFile = new File(filePath);
			outStream = new FileOutputStream(imageFile);
			outStream.write(data);
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // ���ļ����д�뵽�����
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
