/*
 *@(#)xx.quanxian.service
 *@BasicService.java.java  
 *@创建时间:2011-4-3上午11:33:35
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.service;

import java.util.List;

import xx.collection.bean.Rolefunction;
import xx.collection.bean.Roles;
import xx.collection.bean.Scwj;
import xx.collection.bean.Userinfo;



/**
 * @BasicService <code>{类名称}</code>
 * @author  {guoqiang}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
 */

public interface BaseService {
	public boolean regJson(String uid);
	public <T> List<T> getList(Class<T> cls,String hql);
	public int getTotalPages2(String classname,String[] keys,Object[] values);
	public <T> List<T> findByOne(Class<T> cls, String classname, String[] keys,Object[] values,int page,int rows);
	public List<Rolefunction> findRoleAndFunctionsChecked(int id);
	public String changeRoleAndFunction(Roles role,List<String> funIds,List<Rolefunction> rfs);
	public String changeRoleAndFunction(List<String> roleIDs,List<String> funIds);
	public List<Rolefunction> findRoleFunByFunId(Roles role,String funId);
	public <T> List<T> findByNumber(Class<T> cls,String classname,int num);
	public void save (Object  obj); 
    public void save (Object [] objs);
    public void saveOrUpdate(Object obj1,Object obj2);
    public void saveOrUpdate(Object obj);
    public <T> T find(Class<T> cls, String id);
    public <T> T find(Class<T> cls, Integer id);
    public <T> List<List<T>> findByHql(Class<T> cls,String queryString);
    public <T> List<T> findHql(Class<T> cls,String hql);
    public <T> List<T> find (Class<T> cls,T exampleEntity);
    public <T> List<T> find (Class<T> cls,String classname,String [] keys,Object [] values);
    public <T> List<T> find (Class<T> cls,String classname,String [] keys,Object [] values,String orders);
    public <T> List<T> find (Class<T> cls,String classname,String prop,String [] keys,Object [] values);
    public <T> List<T> find (Class<T> cls,String classname,String prop);
    public <T> List<List<T>> find (Class<T> cls,String classname,String [] prop,String [] keys,Object [] values);
    public <T> List<List<T>> find (Class<T> cls,String classname,String [] prop);
    public <T> List<List<T>> find (Class<T> cls,String classname,String [] prop,String orders);
    public <T> List<T> find (Class<T> cls,String [] prop,String [] keys,Object [] values,String orders );
    //==================根据属性查询========================
    public <T> List<T> findByProperty(String queryString,String property);
    public void update (Object obj);
    public void bulkUpdate(String queryString);
    //批量插入
    public <T> int[] batchUpdate(final List<T> obj) ;
    //批量修改
    public <T> int[] batchUpdate12(final List<T> obj) ;
		
    public void delete (Object obj);
    public void delete (Object [] obj);
    public void delete (String classname,String id);
    public void delete (String classname,Integer id);
    public void delete (String classname,String [] keys,Object [] values);
	public <T> List<T> find(Class<T> cls);
	public String login(Userinfo edu);
	
	
	//============分页===========
	public <T> List<T> find(Class<T> cls, String classname, String[] keys,
			Object[] values, String orders,int page,int rows);
	public <T> List<T> findLike(Class<T> cls, String classname, String[] keys,
			Object[] values, String orders,int page,int rows);
	public int getTotalPages(String classname,String[] keys,
			Object[] values);
	public int getTotalP(String classname,String[] keys,
			Object[] values);
	public <T> List<T> findAll(Class<T> cls,String classname,int page,int rows);
	public <T> List<T> findSql(Class<T> cls, String hql, int page,
			int rows);
	public int getTotal(String classname);
	public int getTotalSql(String hql);
	public <T> List<T> findByTypage(Class<T> cls,String classname,String type,
			String word,String orders,int page,int rows);
	//============================
	public <T> List<T> findBPfenye(String queryString,String property,int page,int rows_s);
	public <T> List<T> findBPfenye(String queryString,int property,int page,int rows_s);
	public int sumTotal_int(String classname,String name,int value);
	public int sumTotal_string(String classname,String name,String value);
	public int getSum(String classname,String disname,String name,int value);
	//=======
	public int findMax(String name,String cls);
	//===================求操作题的素材文件list=============================
	public List<Scwj> findScwj(int sjno,int sxh,int dtth);
	public <T> List<T> getEntity(Class<T> cls, String hql,int page,int rows);
}
