package org.uestc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	
	
	public static String LongTimeToDate(long datetime){
		Date date=new Date(datetime);
		return DateToStr(date);
	}
	
	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期毫秒数
	 * 
	 * @param str
	 * @return String
	 * @throws ParseException 
	 */
	public static String StrToDate(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;

		date = format.parse(str);

		return "" + date.getTime();
	}

}
