/*
 *@(#)xx.roleAndfunction.action
 *@Mycomparator.java.java  
 *@创建时间:2011-9-22下午08:03:46
 *@作者：tlq
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.roleAndfunction.action;

import java.util.Comparator;

/**
 * @Mycomparator <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @该类用来排序 
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
