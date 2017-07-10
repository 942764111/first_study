/*
 *@(#)xx.directory.service
 *@directoryServiceImple.java.java  
 *@创建时间:2011-10-27上午11:00:39
 *@作者：tongkesong
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.directory.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xx.collection.bean.Friends;
import xx.collection.bean.Msginfo;
import xx.collection.bean.Studentifno;
import xx.collection.bean.Teacher;
import xx.collection.bean.US;
import xx.collection.bean.Userinfo;
import xx.dao.PublicDao;
import xx.directory.dao.directoryDao;

/**
 * @directoryServiceImple <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Service("directoryService")
@Transactional
public class directoryServiceImple implements directoryService{

	@Resource(name="dao")
	private PublicDao publicDao;
	@Resource(name="directoryDao")
	private directoryDao directorydao;
	public PublicDao getPublicDao() {
		return publicDao;
	}

	public void setPublicDao(PublicDao publicDao) {
		this.publicDao = publicDao;
	}
	
	public directoryDao getDirectorydao() {
		return directorydao;
	}

	public void setDirectorydao(directoryDao directorydao) {
		this.directorydao = directorydao;
	}

	public int updateByHql(String hql){
		return this.directorydao.updateByHql(hql);
	}
	public <T>List<T> findByHql(Class<T> cls,String hql){
		List<List<T>> t=this.publicDao.findByHql(cls, hql);
		List<T> tt=new ArrayList<T>();
		for(int i=0;i<t.size();i++){
			tt.add( (T)t.get(i));
		}
		return tt;
	}
	public <T>int delete(Class<T> cls,String classname,List<String> keys,List<String> values){
		String hql = "from "+classname+" where "+keys.get(0)+"='"+values.get(0)+"' ";
		for(int i=1;i<keys.size();i++){
			hql+="and "+keys.get(i)+"='"+values.get(i)+"'";
		}
		List<List<T>> t=this.publicDao.findByHql(cls, hql);
		for(int i=0;i<t.size();i++){
			this.publicDao.delete((T)t.get(i));
		}
		return t.size();
	}

	public <T>List<T> find(Class<T> cls,String classname,List<String> keys,List<String> values,int page,int rows){
		String hql = "from "+classname+" where "+keys.get(0)+"='"+values.get(0)+"' ";
		for(int i=1;i<keys.size();i++){
			hql+="and "+keys.get(i)+"='"+values.get(i)+"'";
		}
		return this.publicDao.getEntity(cls, hql, page, rows);
	}
	public <T>int gettotal(Class<T> cls,String classname,List<String> keys,List<String> values){
		String hql = "from "+classname+" where "+keys.get(0)+"='"+values.get(0)+"' ";
		for(int i=1;i<keys.size();i++){
			hql+="and "+keys.get(i)+"='"+values.get(i)+"'";
		}
		return this.publicDao.findByHql(cls, hql).size();
	}
	public List<US> queryTS(String queryType,String queryWord,int page,int rows_s){
		int rowsnow;
		String queryTypes;
		String queryTypet;
		String username;
		List<Studentifno> s = new ArrayList<Studentifno>();
		List<Teacher> t = new ArrayList<Teacher>();
		List<US> tem = new ArrayList<US>();
		List<US> tem1 = new ArrayList<US>();
		List<List<Teacher>> testt = new ArrayList<List<Teacher>>();
		List<List<Studentifno>> tests = new ArrayList<List<Studentifno>>();
		queryTypes=queryType.equals("userId")==true?"userId":"s_name";
		String hqls="from Studentifno where "+queryTypes+" like '%"+queryWord+"%'";
		tests = this.publicDao.findByHql(Studentifno.class, hqls);
		for(int i=0;i<tests.size();i++){
			s.add((Studentifno)tests.get(i));
		}
	    queryTypet=queryType.equals("userId")==true?"userId":"jsxm";
	    String hqlt="from Teacher where "+queryTypet+" like '%"+queryWord+"%'";
		testt = this.publicDao.findByHql(Teacher.class, hqlt);
		for(int i=0;i<testt.size();i++){
			t.add((Teacher)testt.get(i));
		}
		for(int i=0;i<t.size();i++){
	    	US us = new US();
	    	String userId;
	    	  userId=t.get(i).getUserinfo().getUserId();
	    	  us.setUserid(userId);
	    	  username=t.get(i).getJsxm();
	    	  us.setUsername(username);
	    	  us.setShenfen("教师");
	    	  tem.add(i,us);
	    	}
	    for(int i=0;i<s.size();i++){
	    	US us = new US();
	    	String userId;
	    	userId=((Studentifno)s.get(i)).getUserinfo().getUserId();
	    	us.setUserid(userId);
	    	username=((Studentifno)s.get(i)).getSName();
	    	us.setUsername(username);
	    	us.setShenfen("学生");
	    	tem.add(i+t.size(),us);
	    }
	    rowsnow=page<tem.size()/rows_s+1?rows_s:tem.size()%rows_s;
	    for(int i=rows_s*(page-1)+1;i<=rowsnow+(page-1)*rows_s;i++){
	    	tem1.add(tem.get(i-1));
	    }
	    return tem1;
	}
	public int queryTStotal(String queryType,String queryWord){
		String queryTypes;
		String queryTypet;
		int sub=0;
		List<List<Teacher>> testt = new ArrayList<List<Teacher>>();
		List<List<Studentifno>> tests = new ArrayList<List<Studentifno>>();
		queryTypes=queryType.equals("userId")==true?"userId":"s_name";
		String hqls="from Studentifno where "+queryTypes+" like '%"+queryWord+"%'";
		tests = this.publicDao.findByHql(Studentifno.class, hqls);
		for(int i=0;i<tests.size();i++){
			if(((Studentifno)tests.get(i)).getUserinfo().getRoles().getRoleid()==10)
				sub+=1;
		}
		queryTypet=queryType.equals("userId")==true?"userId":"jsxm";
		String hqlt="from Teacher where "+queryTypet+" like '%"+queryWord+"%'";
	    testt = this.publicDao.findByHql(Teacher.class, hqlt);
	    return tests.size()+testt.size()-sub;
	}
	public List<Friends> queryFriend(String userid,String queryType,String queryWord,int page,int rows_s){
		int rowsnow=0;
		List<List<Friends>> f = new ArrayList<List<Friends>>();
		String hql = "from Friends where userid='"+userid+"' and "+queryType+"='"+queryWord+"'";
		f = this.publicDao.findByHql(Friends.class, hql);
		List<Friends> tem = new ArrayList<Friends>();
		rowsnow=page<f.size()/rows_s+1?rows_s:f.size()%rows_s;
	    for(int i=rows_s*(page-1)+1;i<=rowsnow+(page-1)*rows_s;i++){
	    	tem.add((Friends) f.get(i-1));
	    }
	    return tem;
	}
	public void saveByAnalysis(String userid,Msginfo msg){
		int sb=0;
		int se=0;
		boolean flag=false;
		String userlist = userid;
		for(int i=0;i<userlist.length();i++){
			char c = userlist.charAt(i);
			if(c=='<'){
				sb=i;
				flag=true;
			}
			if(flag&&c=='>'){
				se=i;
				userid=userlist.substring(sb+1,se);
				System.out.println(userid);
				flag=false;
		Userinfo userinfo=new Userinfo();
		userinfo.setUserId(userid);
		msg.setUserinfo(userinfo);
		this.directorydao.save(msg);
			}
		}
	}
	public void save(Object obj){
		this.directorydao.save(obj);
	}
	public int getTotalPages2(String classname,String[] keys,
			Object[] values){
		if (keys == null)
			return 0;
		if (keys.length == 0)
			return 0;
		String hql="select count(*) from '"+classname+"' where ";
		for(int i=0;i<keys.length-1;i++){
			hql +=keys[i]+"='"+values[i]+"' and ";
		}
		hql +=keys[keys.length-1]+"="+values[keys.length-1];
		return this.publicDao.getTotalPages(hql);
	}
}
