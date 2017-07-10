package xx.dao;

import java.util.List;
import java.util.Map;

import xx.collection.bean.Scwj;

public interface PublicDao {
	/** 
	* 保存（持久化）一个对象 
	* @param obj 要保存的对象 
	*/ 
	public void save (Object  obj); 
	/** 
	* 保存（持久化）一组对象 
	* @param objs 要保存的对象数组 
	*/ 
	public <T> List<T> getList(Class<T> cls,String hql);
    public void save (Object [] objs);
    /** 
	* 根据对象的类型和id查找（持久化）一个对象 
	* @param cls 要查找的对象的类型,id 要查找的对象的id（String类型）
	*/ 
//    public void Save(List<Object> objs);
    /*  
     * 批量插入
     */
    public List<Scwj> getList(String Hql);
    public <T> T find(Class<T> cls, String id);
    /** 
	* 根据对象的类型和id查找（持久化）一个对象 
	* @param cls 要查找的对象的类型,id 要查找的对象的id（Integer类型）
	*/ 
    public <T> T find(Class<T> cls, Integer id);
    public <T> List<List<T>> findByHql(Class<T> cls,String queryString);
    public <T> List<T> find (Class<T> cls,T exampleEntity);
    public <T> List<T> find(Class<T> cls);
    public <T> List<T> find (Class<T> cls,String classname,String [] keys,Object [] values);
    public <T> List<T> find (Class<T> cls,String classname,String [] keys,Object [] values,String orders);
    public <T> List<T> find (Class<T> cls,String classname,String prop,String [] keys,Object [] values);
    public <T> List<T> find (Class<T> cls,String classname,String prop);
    public <T> List<List<T>> find (Class<T> cls,String classname,String [] prop,String [] keys,Object [] values);
    public <T> List<List<T>> find (Class<T> cls,String classname,String [] prop);
    public <T> List<List<T>> find (Class<T> cls,String classname,String [] prop,String orders);
    public <T> List<T> find (Class<T> cls,String [] prop,String [] keys,Object [] values,String orders );
    
    public void update (Object obj);
    public void bulkUpdate(String queryString);
    public void bulkUpdate(String queryString,Object[] values);
    
    public void saveOrUpdate(Object obj);
    public void delete (Object obj);
    public void delete (Object [] obj);
    public void delete (String classname,String id);
    public void delete (String classname,Integer id);
    public void delete (String classname,String [] keys,Object [] values);
    public <T> int[]  batchUpdate12(final List <T> obj) ;;
    public  <T> int[] batchUpdate(final List<T> obj);
    public <T> List<T> findHql(Class<T> cls,String hql);
    //==================根据属性查询==================
    public <T> List<T> findByProperty(String queryString,String property);
    
   
	//================分页==============
	public <T> List<T> getEntity(Class<T> cls, String hql,int page,int rows);
	public int getTotalPages(String hql);
	//=============分页查询字段=====================
	public <T> List<T> findBPfenye(String queryString,String property,int page,int rows);
	public <T> List<T> findBPfenye(String queryString,int property,int page,int rows);
	//==============
	public int findMax(String queryString);
	

	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	
	List<Map<String, String>> list(String sql);
}
