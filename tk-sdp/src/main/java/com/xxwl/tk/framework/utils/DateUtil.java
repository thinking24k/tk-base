package com.xxwl.tk.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 时间工具类
 * @author deng
 *
 */
public class DateUtil {
	
	public static final String  DATE_PATTERN="yyyy-MM-dd";
	public static final String  TIME_PATTERN="HH:mm:ss";
	public static final String  DATETIME_PATTERN="yyyy-MM-dd HH:mm:ss";
	
	
	private static Logger  logger=Logger.getLogger(DateUtil.class);
	//时间格式化对象
	private static ConcurrentHashMap<String, SimpleDateFormat> rongCloud = new ConcurrentHashMap<String,SimpleDateFormat>();
	
	//初始化常用pattern
	static {
		getSimpleDateFormat(DATE_PATTERN);
		getSimpleDateFormat(TIME_PATTERN);
		getSimpleDateFormat(DATETIME_PATTERN);
	}
	
	/**
	 * 时间转换为String
	 * @param date
	 * @return
	 */
	public static String paseString(String pattern,Date date){
		SimpleDateFormat sdf = getSimpleDateFormat(pattern);
		return sdf.format(date);
	}
	/**
	 * 获取今天的Long型时间
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Long getTodayTime(){
		GregorianCalendar now = new GregorianCalendar();
		int year=now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH);
		int day=now.get(Calendar.DAY_OF_MONTH);
		//			int hour=now.get(Calendar.HOUR);
		//			int min =now.get(Calendar.MINUTE);
		Date date = new Date(year,month,day);
		return date.getTime();
	}
	
	public static Long getTime(String pattern,String source){
		SimpleDateFormat sdf=getSimpleDateFormat(pattern);
		try {
			return sdf.parse(source).getTime();
		} catch (ParseException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 
	 * @Title: getSimpleDateFormat 
	 * @Description: 获取缓存的时间格式化对象
	 * @param pattern
	 * @return SimpleDateFormat
	 */
	public static SimpleDateFormat getSimpleDateFormat(String pattern) {
		if (null == rongCloud.get(pattern)) {
			rongCloud.putIfAbsent(pattern, new SimpleDateFormat(pattern));
		}
		return rongCloud.get(pattern);
	}

}
