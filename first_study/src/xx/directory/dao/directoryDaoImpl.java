/*
 *@(#)xx.directory.dao
 *@directoryDaoImple.java.java  
 *@����ʱ��:2011-10-27����04:47:41
 *@���ߣ�tongkesong
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.directory.dao;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;


import org.springframework.stereotype.Repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
/**
 * @directoryDaoImple <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Repository("directoryDao")
public class directoryDaoImpl extends HibernateDaoSupport implements directoryDao{

	
	
	@Resource(name = "sessionFactory")                  //ע������λ��
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
