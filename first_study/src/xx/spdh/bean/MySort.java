/*
 *@(#)xx.spdh.bean
 *@Sort.java.java  
 *@����ʱ��:2011-9-25����09:41:59
 *@���ߣ�ZYK
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.spdh.bean;
import java.util.*;


/**
 * @Sort <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
