import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@(#)
 *@A.java.java  
 *@创建时间:2016-8-24上午9:14:06
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

/**
 * @A <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class A {
	public static void main(String[] args) {
		  String str = "/";
		  String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		  Pattern p = Pattern.compile(regEx);
		  Matcher m = p.matcher(str);
		  System.out.println(m.find());
		 }

}
