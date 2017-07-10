package xx.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import xx.collection.bean.Scwj;

@Repository("dao")
public class PublicDaoImpl extends HibernateDaoSupport implements PublicDao {
	
	
	private SimpleJdbcTemplate simpleJdbcTemplate;
		
	@Resource(name = "sessionFactory")                 
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	
	public void delete(Object obj) {
		this.getHibernateTemplate().delete(obj);
		

	}
//   public void save(List<Object>list){
//	   
//	   Session session=this.getSession();
//	   session.setCacheMode(CacheMode.IGNORE);
//	   for(int i=0;i<list.size();i++){
//			Device device = list.get(i);
//			session.save(device);
//			if(i%20==0){
//				session.flush();
//				session.clear();
//			}
//		}
//   }
	public void delete(Object[] obj) {
		if (obj == null)
			return;
		for (int i = 0; i < obj.length; i++) {
			this.getHibernateTemplate().delete(obj[i]);
		}

	}

	public void delete(String classname, String id) {
		//this.getHibernateTemplate().findByExample(exampleEntity)
//		Object obj = this.find(classname, id);
//		if (obj == null)
//			return;
//		this.getHibernateTemplate().delete(obj);

	}

	public void delete(String classname, Integer id) {
//		Object obj = this.find(classname, id);
//		if (obj == null)
//			return;
//		this.getHibernateTemplate().delete(obj);

	}

	public void delete(String classname, String[] keys, Object[] values) {
//		List list = this.find(classname, keys, values);
//		if (list == null)
//			return;
//		for (int i = 0; i < list.size(); i++) {
//			this.getHibernateTemplate().delete(list.get(i));
//		}

	}

	
	public  <T> T find(Class<T> cls, String id) {
		  HibernateTemplate ht = getHibernateTemplate();
		  Object obj = ht.get(cls, id);
		  
		  if(obj != null)
		   return (T)obj;
		  else
		   return null;
	
	}

	@SuppressWarnings("unchecked")
	public  <T> T find(Class<T> cls, Integer id) {
		 HibernateTemplate ht = getHibernateTemplate();
		  Object obj = ht.get(cls, id);
		  
		  if(obj != null)
		   return (T)obj;
		  else
		   return null;
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> find(Class<T> cls) {		
		String queryString = "from " + cls.getName() ;
		List list = this.getHibernateTemplate().find(queryString);
		return list;
	}
	public <T> List<T> find(Class<T> cls,String classname, String[] keys, Object[] values) {		
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		String queryString = "from " + classname + " where " + keys[0] + "=?";
		if (keys.length > 1) {
			for (int i = 1; i < keys.length; i++) {
				queryString += " and " + keys[i] + " =?";
			}

		}
		List list = this.getHibernateTemplate().find(queryString, values);
		return list;

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(Class<T> cls,String classname, String[] keys, Object[] values,
			String orders) {
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		String queryString = "from " + classname + " where " + keys[0] + "=?";
		if (keys.length > 1) {
			for (int i = 1; i < keys.length; i++) {
				queryString += " and " + keys[i] + "=?";
			}

		}
		if (orders != null)
			queryString += orders;
		List list = this.getHibernateTemplate().find(queryString,values);
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findLike(Class<T> cls,String classname, String[] keys, Object[] values,
			String orders) {
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		String queryString = "from " + classname + " where " + keys[0] + "=?";
		if (keys.length > 1) {
			for (int i = 1; i < keys.length; i++) {
				queryString += " and " + keys[i] + "=?";
			}

		}
		if (orders != null)
			queryString += orders;
		List list = this.getHibernateTemplate().find(queryString,values);
		
		return list;
	}

	public void save(Object obj) {
		this.getHibernateTemplate().save(obj);
	}

	public void save(Object[] objs) {
		if (objs == null)
			return;
		for (int i = 0; i < objs.length; i++) {
			this.getHibernateTemplate().save(objs[i]);
			
		}

	}

	public void update(Object obj) {
		
        this.getHibernateTemplate().update(obj);
        
	}
    public void bulkUpdate(String queryString) {
    	this.getHibernateTemplate().bulkUpdate(queryString);
        
	}
    public void bulkUpdate(String queryString,Object[] values) {
		
        this.getHibernateTemplate().bulkUpdate(queryString, values);
       
	}
	
	public void saveOrUpdate(Object obj){
		
		this.getHibernateTemplate().saveOrUpdate(obj);
	}

	 @SuppressWarnings("unchecked")
	public <T> List<T> find (Class<T> cls,String classname,String prop,String [] keys,Object [] values) {
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		if(prop ==null)
			return null;		
		String queryString ="select distinct "+prop;
		queryString +=" from " + classname + " where " + keys[0] + "=?";
		if (keys.length > 1) {
			for (int i = 1; i < keys.length; i++) {
				queryString += " and " + keys[i] + " =?";
			}

		}		
		System.out.println(queryString);
		List<T> list = this.getHibernateTemplate().find(queryString, values);
		return list;
	}
	 
	 public <T> List<T> find (Class<T> cls,String classname,String prop) {
			String queryString ="select distinct "+prop;
			queryString +=" from " + classname ;
			List<T> list = this.getHibernateTemplate().find(queryString);
			return list;
		}
	 
	public <T> List<T> find(Class<T> cls, String[] prop, String[] keys,
			Object[] values, String orders) {
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		if(prop ==null)
			return null;
		if(prop.length==0)
			return null;
		String queryString ="select ";
		for(int m=0;m<prop.length;m++){
			queryString +=prop[m];
			
		}
		queryString +=" from " + cls + "where " + keys[0] + "=?";
		if (keys.length > 1) {
			for (int i = 1; i < keys.length; i++) {
				queryString += " and " + keys[i] + " =?";
			}

		}
		if (orders != null)
			queryString += orders;
		List list = this.getHibernateTemplate().find(queryString, values);
		return list;
		
	}

	@SuppressWarnings("unchecked")
	public <T> List<List<T>> find(Class<T> cls, String classname,
			String[] prop, String[] keys, Object[] values) {
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		if(prop ==null)
			return null;
		if(prop.length==0)
			return null;
		String queryString ="select new List(";
		
			queryString +=prop[0];
			if (queryString.length()>1) {
			for (int m = 1; m < prop.length; m++) {
				queryString +=","+ prop[m];

			}
		}
			if(keys[0].contains("<>"))
				queryString +=") from " + classname + " where " + keys[0] + "?";
			else
				queryString +=") from " + classname + " where " + keys[0] + "=?";
		if (keys.length > 1) {
			for (int i = 1; i < keys.length; i++) {
				if(keys[i].contains("<>"))
					queryString += " and " + keys[i] + "?";
				else
				queryString += " and " + keys[i] + " =?";
			}

		}		
		List<List<T>> list = this.getHibernateTemplate().find(queryString, values);
		return list;
	}

	@SuppressWarnings("unchecked")
	public <T> List<List<T>> find(Class<T> cls, String classname, String[] prop) {
		if (prop==null)
			return null;
		if(prop.length==0)
			return null;
		if(classname.equals(null)||classname.equals(""))
			return null;
	
		String queryString ="select new List(";
		
		queryString += prop[0];
		if (queryString.length() > 1) {
			for (int m = 1; m < prop.length; m++) {
				queryString += "," + prop[m];

			}
		}
		queryString +=") from " + classname;
		if(prop.length==1) 
			queryString ="select "+prop[0]+" from "+classname;;
		List<List<T>> list = this.getHibernateTemplate().find(queryString);
		return list;
	}

	public <T> List<List<T>> find(Class<T> cls, String classname,
			String[] prop, String orders) {
		if (prop==null)
			return null;
		if(prop.length==0)
			return null;
		if(classname.equals(null)||classname.equals(""))
			return null;
	
		String queryString ="select new List(";
		
		queryString += prop[0];
		if (queryString.length() > 1) {
			for (int m = 1; m < prop.length; m++) {
				queryString += "," + prop[m];

			}
		}
		queryString +=") from " + classname;
		if(prop.length==1) 
			queryString ="select "+prop[0]+" form "+classname;
		if(!(orders==null||orders.equals(null)||orders.equals("")))
			queryString +=" "+orders;
		List<List<T>> list = this.getHibernateTemplate().find(queryString);
		return list;
	}

	public <T> List<T> find(Class<T> cls, T exampleEntity) {
		List<T> list = this.getHibernateTemplate().findByExample(exampleEntity);
		return list;
	}

	public <T> List<List<T>> findByHql(Class<T> cls, String queryString) {
	return this.getHibernateTemplate().find(queryString);
	}

	public <T> List<T> findHql(Class<T> cls, String hql) {
		return this.getHibernateTemplate().find(hql);
	}
	public <T> List<T> findByProperty(String queryString,String property){
		
		return this.getHibernateTemplate().find(queryString, property);
		
	}

	
//	public <T> void save(Class<T> cls, String className)
//	{ 
//	//��ö��������   
//      Class classType=cls.getClass();   
//      
//      String sql = "insert into " + className + "(";
//      
//    //��ö������������   
//      final Field[] fields=classType.getDeclaredFields();
//      
//    //��ȡ�����ж�Ӧ������ 
//      for(int i=0;i<fields.length;i++){
//        
//      Field field=fields[i];
//         
//      String fieldName=field.getName();   
//      
//      if(i!=0){
//    	  sql += ",";
//      }
//      sql += field.getName();      
//      }
//      sql += ") values(";
//      
//      for(int i=0;i<fields.length;i++){
//    	  if(i!=0){
//    		  sql += ",";
//    	  }
//    	  sql += "?";
//      }
//      sql += ")";
//      
//      jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter(){
//    	  
//    	  public void setValues(PreparedStatement ps,int i)throws SQLException{
//    		  
//    		  for(i=1;i<=fields.length;i++){
//    			  Field field=fields[i];
//    			  String type = field.toString();
//    		  }
//    	  }
//    	  public int getBatchSize() 
//		   { 
//		    return getBatchSize();
//		   } 
//      });
//	} 
	
	public void setDataSource(DataSource dataSource) {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
	
	public  <T> int[] batchUpdate12(final List<T> obj) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(obj.toArray());
        
        
           Class classType=obj.get(0).getClass();
           String s11=classType.toString();
	       String s1=s11.substring(14);
	       java.lang.reflect.Field[] fields=classType.getDeclaredFields(); 
	       String id=fields[0].getName();
	       String T_id=id.substring(0,id.length()-1)+id.substring(id.length()-1).toUpperCase();
	       String  sql="update "+s1+" set ";
	       for(int i=1;i<fields.length-1;i++){
	    	   String fieldName=fields[i].getName();
	           String stringLetter=fieldName.substring(0, 1).toUpperCase();
	           String fieldNam=stringLetter+fieldName.substring(1);
	           sql +=fieldNam+" = :"+fieldName+", ";                                 
	        }
	        String fieldName=fields[fields.length-1].getName();
	           String stringLetter=fieldName.substring(0, 1).toUpperCase();
	           String fieldNam=stringLetter+fieldName.substring(1);
	           sql +=fieldNam+" = :"+fieldName;
	       sql +=" where "+T_id+" = :"+id;
        
        int[] updateCounts = simpleJdbcTemplate.batchUpdate(sql,
       		 batch);
        return updateCounts;
        
    }
   
	public  <T> int[] batchUpdate(final List<T> obj) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(obj.toArray());
        
           Class classType=obj.get(0).getClass();
           
           String s1=classType.toString();
	       String s2=s1.substring(14);
	       
	       java.lang.reflect.Field[] fields=classType.getDeclaredFields(); 
	       
	       String  sql="insert into t_"+s2+" values (null";
	       
	       for(int i=1;i<fields.length;i++){
	    	   
	    	   String fieldName=fields[i].getName();
	          // String stringLetter=fieldName.substring(0, 1).toUpperCase();
	          // String fieldName2=stringLetter+fieldName.substring(1);
	           
	           sql += "," + fieldName;
	        }
	       sql += ")";
        
        int[] insertCounts = simpleJdbcTemplate.batchUpdate(sql,batch);
        return insertCounts;
    }
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getEntity(Class<T> cls,String hql,int page,int rows){
		List<T> list=null;
		try{
			list=getSession().createQuery(hql)
					.setFirstResult((page-1)*rows)
			        .setMaxResults(rows)
			        .list();
		}catch(Exception e){
			 e.printStackTrace();
        }
		return list;
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(Class<T> cls,String hql){
		List<T> list=null;
		try{
			list=getSession().createQuery(hql)
			        .list();
		}catch(Exception e){
			 e.printStackTrace();
        }
		return list;
	}
	
	public int getTotalPages(String hql){
		int total=0;
		try{
			int count=((Long)getSession().createQuery(hql).uniqueResult()).intValue();
			
			total=count;
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}
	
	public <T> List<T> findBPfenye(String queryString,String property,int page,int rows){
		List<T> list=null;
		try{
			list=getSession().createQuery(queryString)
					.setString(0,property)
					.setFirstResult((page-1)*rows)
			        .setMaxResults(rows)
			        .list();

		}catch(Exception e){
			 e.printStackTrace();
        }
		return list;
	}
	
	public <T> List<T> findBPfenye(String queryString,int property,int page,int rows){
		List<T> list=null;
		try{
			list=getSession().createQuery(queryString)
					.setInteger(0,property)
					.setFirstResult((page-1)*rows)
			        .setMaxResults(rows)
			        .list();

		}catch(Exception e){
			 e.printStackTrace();
        }
		return list;
	}
	
	public int findMax(String queryString){
		Query q = getSession().createSQLQuery(queryString);
		int max=0;
		if (q.uniqueResult()==null||q.uniqueResult().equals("null")||q.uniqueResult().equals("")) {
			max=0;
		} else {
			max = (Integer)q.uniqueResult();
		}
	
		return max;
	}

	/* (non-Javadoc)
	 * @see xx.dao.PublicDao#list(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Map<String, String>> list(String sql) {
		// TODO Auto-generated method stub
		Query q = getSession().createSQLQuery(sql);
		
		List<Map<String, String>>list=q.list();
		System.out.println("测试123:"+list.get(0));
		
		return null;
	}

	/* (non-Javadoc)
	 * @see xx.dao.PublicDao#getList(java.lang.String)
	 */
	@Override
	public List<Scwj> getList(String Hql) {
		// TODO Auto-generated method stub
		return null;
	}
}
