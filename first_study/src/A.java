import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@(#)
 *@A.java.java  
 *@����ʱ��:2016-8-24����9:14:06
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

/**
 * @A <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

public class A {
	public static void main(String[] args) {
		  String str = "/";
		  String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";
		  Pattern p = Pattern.compile(regEx);
		  Matcher m = p.matcher(str);
		  System.out.println(m.find());
		 }

}
