/*
 *@(#)xx.directory.dao
 *@directoryDaoImple.java.java  
 *@创建时间:2011-10-27下午04:47:41
 *@作者：tongkesong
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.directory.dao;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;


import org.springframework.stereotype.Repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
/**
 * @directoryDaoImple <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Repository("directoryDao")
public class directoryDaoImpl extends HibernateDaoSupport implements directoryDao{

	
	
	@Resource(name = "sessionFactory")                  //注意它的位置
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	public <T>void save(List<T> list){
		for(int i=0;i<list.size();i++){
			this.getHibernateTemplate().persist(list.get(i));
		}
	}
	public void save(Object obj){
		this.getHibernateTemplate().save(obj);
	}
	public int updateByHql(String hql){
		return this.getHibernateTemplate().bulkUpdate(hql);
	}
	
}
