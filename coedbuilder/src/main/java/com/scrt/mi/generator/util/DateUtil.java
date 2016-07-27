package com.scrt.mi.generator.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @ClassName: DateUtil 
 * @Description: 日期工具类
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月3日 
 *
 */
public class DateUtil {
	/**时间格式 yyyy年MM月dd天*/
	public static final String DATA_FORMAT="yyyy年MM月dd日";
	/**
	 * 
	 * @Title: getNowDate 
	 * @Description: 获取当前指定格式的时间字符串
	 * @param dataFormat 时间的格式
	 * @return String
	 */
	public static String  getNowDate(String dataFormat){
		return new SimpleDateFormat(dataFormat).format(new Date());
	}
}
