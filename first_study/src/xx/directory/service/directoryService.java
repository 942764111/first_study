/*
 *@(#)xx.directory.service
 *@directoryService.java.java  
 *@创建时间:2011-10-26下午08:37:48
 *@作者：tongkesong
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.directory.service;

import java.util.List;

import xx.collection.bean.Friends;
import xx.collection.bean.Msginfo;
import xx.collection.bean.US;

/**
 * @directoryService <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public interface directoryService {
	
	public List<US> queryTS(String queryType,String queryWord,int page,int rows);
	public int queryTStotal(String queryType,String queryWord);
	public List<Friends> queryFriend(String userid,String queryType,String queryWord,int page,int rows_s);
	public int updateByHql(String hql);
	public <T>int delete(Class<T> cls,String classname,List<String> keys,List<String> values);
	public <T>List<T> find(Class<T> cls,String classname,List<String> keys,List<String> values,int page,int rows);
	public <T>int gettotal(Class<T> cls,String classname,List<String> keys,List<String> values);
	public void saveByAnalysis(String userid,Msginfo msg);
	public <T>List<T> findByHql(Class<T> cls,String hql);
	public void save(Object obj);
	public int getTotalPages2(String classname,String[] keys,Object[] values);

}
