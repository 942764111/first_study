/*
 *@(#)xx.roleAndfunction.action
 *@Mycomparator.java.java  
 *@����ʱ��:2011-9-22����08:03:46
 *@���ߣ�tlq
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.roleAndfunction.action;

import java.util.Comparator;

/**
 * @Mycomparator <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @������������ 
 */

public class Mycomparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Function f1 = (Function)o1;
		Function f2 = (Function)o2;
		if(f1.getModuleclass().compareTo(f2.getModuleclass())>0){
			return 1;
		}else{
			return 0;
		}
	}

}
