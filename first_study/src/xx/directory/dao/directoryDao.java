/*
 *@(#)xx.directory.dao
 *@directoryDao.java.java  
 *@创建时间:2011-10-27下午04:46:45
 *@作者：tongkesong
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.directory.dao;

import java.util.List;



/**
 * @directoryDao <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public interface directoryDao {

	public int updateByHql(String hql);
	public <T>void save(List<T> obj);
	public void save(Object obj);
	
	
}