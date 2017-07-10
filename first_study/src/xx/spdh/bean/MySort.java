/*
 *@(#)xx.spdh.bean
 *@Sort.java.java  
 *@创建时间:2011-9-25上午09:41:59
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.bean;
import java.util.*;


/**
 * @Sort <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class MySort implements Comparator {
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Spxx spxx1 = (Spxx)o1;
		Spxx spxx2 = (Spxx)o2;
		if(spxx1.getStr2().compareTo(spxx2.getStr2())>0) {
			return 1;
		} else {
			return 0;
		}
	}
}
