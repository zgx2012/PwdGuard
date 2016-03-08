package com.lovethinking.passport.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


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

	public static byte[] readInputStream(InputStream inStream) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
	public static void saveByte2File(String filePath, byte[] data) {
		FileOutputStream outStream;
		try {
			File imageFile = new File(filePath);
			outStream = new FileOutputStream(imageFile);
			outStream.write(data);
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
