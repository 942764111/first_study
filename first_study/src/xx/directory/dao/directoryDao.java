/*
 *@(#)xx.directory.dao
 *@directoryDao.java.java  
 *@����ʱ��:2011-10-27����04:46:45
 *@���ߣ�tongkesong
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.directory.dao;

import java.util.List;



/**
 * @directoryDao <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

public interface directoryDao {

	public int updateByHql(String hql);
	public <T>void save(List<T> obj);
	public void save(Object obj);
	
	
}