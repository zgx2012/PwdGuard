package com.lovethinking.passport.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
	public static boolean isUserName(String strNickName) {
		String strPattern = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{1,10}$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strNickName);
		return m.matches();
	}

	public static boolean isEmail(String strEmail) {
		// String strPattern =
		// "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";
		String strPattern = "^\\w+@\\w+(\\.\\w+)+(\\,\\w+@\\w+(\\.\\w+)+)*$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

	public static boolean isMatchName(String strNickName) {
		String strPattern = "^[\\w]{2,20}$";
		// String strPattern = "^\\w+$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strNickName);
		return m.matches();
	}

	public static boolean isMatchPassWord(String strPassWord) {
		String strPattern = "^[a-zA-z0-9_-]{3,18}$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strPassWord);
		return m.matches();
	}
}
