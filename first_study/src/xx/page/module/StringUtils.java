/*
 *@(#)xx.page.module
 *@StringUtils.java.java  
 *@����ʱ��:2012-9-7����12:42:27
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.page.module;

/**
 * @StringUtils <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{��������:String������} 
 */

public class StringUtils {
	/**
	 * �Ƿ�Ϊ�� ���򷵻�һ�����ַ�
	 * 
	 * @param str
	 * @return String
	 */
	public static String notNull(String str) {
		return str == null ? "" : str;
	}
	
	/**
	 * �Ƿ�Ϊ�� ���򷵻ز���2
	 * 
	 * @param str
	 *            str1
	 * @return String
	 */
	public static String notNull(String str, String str1) {
		return (str == null || "".equals(str)) ? str1 : str;
	}
	
	/**
	 * �Ƿ�Ϊ��ָ��
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNull(String str) {
		return str == null ? true : false;
	}
	
	/**
	 * �Ƿ�Ϊ��
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
