package com.lovethinking.passport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	/**
	 * @Title : isDateString
	 * @param : dirtyDate
	 * @return : true - if dirtyDate is formatted as "yyyy-MM-dd"; false else
	 */
	public static boolean isDateString(String dirtyDate) {
		int len = dirtyDate.length();
		if (len == 10) {
			for (int i = 0; i < len; i++) {
				if (i == 4 || i == 7) {
					if (dirtyDate.charAt(i) != '-')
						return false;
				} else {
					if (!Character.isDigit(dirtyDate.charAt(i)))
						return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * @Title : isDateTimeString
	 * @param : dirtyDateTime
	 * @return : true - if dirtyDate is formatted as "yyyy-MM-dd" or
	 *         "yyyy-MM-ss HH:mm:ss"; false else
	 */
	public static boolean isDateTimeString(String dirtyDateTime) {
		int len = dirtyDateTime.length();
		if (len != 10 && len != 19)
			return false;
		if (!isDateString(dirtyDateTime.substring(0, 10)))
			return false;
		if (len == 10)
			return true;
		if (dirtyDateTime.charAt(10) != ' ')
			return false;
		String timeString = dirtyDateTime.substring(11);
		len = timeString.length();
		if (len == 8) {
			for (int i = 0; i < len; i++) {
				if (i == 2 || i == 5) {
					if (timeString.charAt(i) != ':')
						return false;
				} else {
					if (!Character.isDigit(timeString.charAt(i)))
						return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * @Title : parse2Date
	 * @Description :
	 * @param : @param s
	 * @param : @param d
	 * @return : Date
	 */
	public static Date parse2Date(SimpleDateFormat s, Object d) {
		if (d instanceof String) {
			try {
				return s.parse(d.toString());
			} catch (ParseException e) {
				return new Date();
			}
		} else if (d instanceof Date) {
			return (Date) d;
		} else {
			return new Date();
		}
	}

	/**
	 * @Title : formatDate
	 * @param : @param d
	 * @return : String
	 */
	public static String formatDate(Date d) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return s.format(d);
	}

	/**
	 * @Title : getNowDateTime
	 * @return : String
	 */
	public static String getNowDateTime() {
		Date date = new Date();
		return formatDate(date);
	}

	/**
	 * @Title : formatDate
	 * @param : @param s
	 * @param : @param d
	 * @return : String
	 */
	public static String formatDate(SimpleDateFormat s, Date d) {
		return s.format(d);
	}

	public static long daysBetweenDate(Date date1, Date date2) {
		long d = date1.getTime() - date2.getTime();
		return d / 86400000;
	}


	public static long hoursBetweenDate(Date date1, Date date2) {
		long d = date1.getTime() - date2.getTime();
		return d / 3600000;
	}


	public static long minuteBetweenDate(Date date1, Date date2) {
		long d = date1.getTime() - date2.getTime();
		return d / 60000;
	}


	public static long secondBetweenDate(Date date1, Date date2) {
		long d = date1.getTime() - date2.getTime();
		return d / 1000;
	}


	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}

	/**
	 *
	 */
	public static int compareDate(Date date1, Date date2) {
		return date1.compareTo(date2);
	}

	/**
	 *
	 */
	public static Date dateAfterDate(Date date, long days) {
		long d = date.getTime();
		d += days * 86400000;
		return new java.util.Date(d);
	}

	/**
	 *
	 */
	public static Date dateBeforeDate(Date date, long days) {
		long d = date.getTime();
		d += days * 86400000;
		return new java.util.Date(d);
	}
}