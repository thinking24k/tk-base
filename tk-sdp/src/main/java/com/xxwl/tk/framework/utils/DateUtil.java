package com.xxwl.tk.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

/**
 * 时间工具类
 * @author deng
 *
 */
public class DateUtil {
		static Logger  logger=Logger.getLogger(DateUtil.class);
		/**
		 * 时间转换为String
		 * @param date
		 * @return
		 */
		public static String paseString(Date date){
			
			return null;
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
			SimpleDateFormat sdf=new SimpleDateFormat(pattern);
			try {
				return sdf.parse(source).getTime();
			} catch (ParseException e) {
				logger.error(e);
				e.printStackTrace();
			}
			return 0L;
		}
		
}
