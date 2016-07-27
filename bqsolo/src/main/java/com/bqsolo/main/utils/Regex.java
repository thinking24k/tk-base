package com.bqsolo.main.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证工具类
 * @author deng
 *
 */
public class Regex {
	/**用户名2-14位汉字,数字,字母组合*/
	public static final String REGEXUSERNAME="^(([\u4e00-\u9fa5]|[A-Za-z0-9 ]){2,14})$";
	/**用户密码4-16位数字,字母组合*/
	public static final String REGEXUSERPWD="^[A-Za-z0-9 ]{4,16}$";
	/**用户邮箱合*/
	public static final String REGEXUSEREMAIL="^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
	/**用户性别0或1*/
	public static final String REGEXUSEREGENDER="^[0-1]{1}$";
	/**
	 * 指定为字符串的正则表达式必须首先被编译为此类的实例。
	 * 然后，可将得到的模式用于创建 Matcher 对象，依照正则表达式，该对象可以与任意字符序列匹配。
	 * 执行匹配所涉及的所有状态都驻留在匹配器中，所以多个匹配器可以共享同一模式。 
	 * @param regex 正则表达式
	 * @param input 输入
	 * @return 
	 */
	public static boolean regex(String regex,String input){
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(input);
		return m.matches();
	}
	/**
	 * 等效于上面的三个语句，尽管对于重复的匹配而言它效率不高，因为它不允许重用已编译的模式。 
	 * 此类的实例是不可变的，可供多个并发线程安全使用。Matcher 类的实例用于此目的则不安全。
	 * @param regex 正则表达式
	 * @param input 输入
	 * @return 匹配成功返回true
	 */
	public static boolean regexBySinger(String regex,String input){
		return Pattern.matches(regex, input);
	}

}
