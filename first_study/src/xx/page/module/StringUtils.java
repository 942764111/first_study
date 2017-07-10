/*
 *@(#)xx.page.module
 *@StringUtils.java.java  
 *@创建时间:2012-9-7上午12:42:27
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.page.module;

/**
 * @StringUtils <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{功能描述:String工具类} 
 */

public class StringUtils {
	/**
	 * 是否为空 是则返回一个空字符
	 * 
	 * @param str
	 * @return String
	 */
	public static String notNull(String str) {
		return str == null ? "" : str;
	}
	
	/**
	 * 是否为空 是则返回参数2
	 * 
	 * @param str
	 *            str1
	 * @return String
	 */
	public static String notNull(String str, String str1) {
		return (str == null || "".equals(str)) ? str1 : str;
	}
	
	/**
	 * 是否为空指针
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNull(String str) {
		return str == null ? true : false;
	}
	
	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String... str) {
		for (String s : str) {
			if (isNull(s) || notNull(s).trim().length() < 1) return true;
		}
		return false;
	}
}
