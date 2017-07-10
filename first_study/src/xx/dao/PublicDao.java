package xx.dao;

import java.util.List;
import java.util.Map;

import xx.collection.bean.Scwj;

public interface PublicDao {
	/** 
	* ���棨�־û���һ������ 
	* @param obj Ҫ����Ķ��� 
	*/ 
	public void save (Object  obj); 
	/** 
	* ���棨�־û���һ����� 
	* @param objs Ҫ����Ķ������� 
	*/ 
	public <T> List<T> getList(Class<T> cls,String hql);
    public void save (Object [] objs);
    /** 
	* ���ݶ�������ͺ�id���ң��־û���һ������ 
	* @param cls Ҫ���ҵĶ��������,id Ҫ���ҵĶ����id��String���ͣ�
	*/ 
//    public void Save(List<Object> objs);
    /*  
     * ��������
     */
    public List<Scwj> getList(String Hql);
    public <T> T find(Class<T> cls, String id);
    /** 
	* ���ݶ�������ͺ�id���ң��־û���һ������ 
	* @param cls Ҫ���ҵĶ��������,id Ҫ���ҵĶ����id��Integer���ͣ�
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
    //==================�������Բ�ѯ==================
    public <T> List<T> findByProperty(String queryString,String property);
    
   
	//================��ҳ==============
	public <T> List<T> getEntity(Class<T> cls, String hql,int page,int rows);
	public int getTotalPages(String hql);
	//=============��ҳ��ѯ�ֶ�=====================
	public <T> List<T> findBPfenye(String queryString,String property,int page,int rows);
	public <T> List<T> findBPfenye(String queryString,int property,int page,int rows);
	//==============
	public int findMax(String queryString);
	

	/**
	 * @{������}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�����Ĺ���/��������}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	
	List<Map<String, String>> list(String sql);
}
