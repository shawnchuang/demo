package com.book.management.demo.general.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final	Date dateFormat(String source, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date =	null;
		try	{
			date = sdf.parse(source);
		} catch	(Exception	e) {
		}
		return date;
	}
	
	public static String dateFormat(Date date, String format) {

		String result = null;

		try {
			if (date != null) {
				SimpleDateFormat simple = null;
				simple = new SimpleDateFormat(format);
				result = simple.format(date);
			}
		} catch (Exception e) {
		}

		return result;
	}
}
