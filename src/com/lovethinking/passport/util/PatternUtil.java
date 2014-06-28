/**
 * @Project     : Baby-V1
 * @Title       : PatternUtil.java
 * @Package     : com.pr.baby.util
 * @Description : TODO
 * @author      : ZGX  zhangguoxiao_happy@163.com
 * @date        : 2011-11-4 ����04:41:30
 * @Copyright   : 2011 http://www.pengruikeji.com/ Inc. All rights reserved.
 * @version     : V1.0
 */
package com.lovethinking.passport.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName : PatternUtil
 * @Description : TODO
 * @author : ZGX zhangguoxiao_happy@163.com
 * @date : 2011-11-4 ����04:41:30
 * 
 */
public class PatternUtil {
	//������ĸ�����»���
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
