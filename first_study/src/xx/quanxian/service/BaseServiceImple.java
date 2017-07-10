/*
 *@(#)xx.quanxian.service
 *@BasicServiceImple.java.java  
 *@创建时间:2011-4-3上午11:36:11
 *@作者：guoqiang
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xx.collection.bean.Functions;
import xx.collection.bean.Rolefunction;
import xx.collection.bean.RolefunctionId;
import xx.collection.bean.Roles;
import xx.collection.bean.Scwj;
import xx.collection.bean.Studentifno;
import xx.collection.bean.XsdyjlCztZsd;
import xx.collection.bean.Zlzsddy;

import xx.collection.bean.Userinfo;
import xx.dao.PublicDao;

import xx.quanxian.action.OnlineSessionList;

/**
 * @BasicServiceImple <code>{类名称}</code>
 * @author  {guoqiang}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
 */
@Service("baseService")
@Transactional
public class BaseServiceImple implements BaseService {
	@Resource(name="dao")
	private PublicDao publicDao;

	public PublicDao getPublicDao() {
		return publicDao;
	}

	public void setPublicDao(PublicDao publicDao) {
		this.publicDao = publicDao;
	}
	
	public void delete(Object obj) {
		this.publicDao.delete(obj);
	}

	public void delete(Object[] obj) {
		this.publicDao.delete(obj);
	}

	public void delete(String classname, String id) {
		this.publicDao.delete(classname, id);
	}

	public void delete(String classname, Integer id) {
		this.publicDao.delete(classname, id);
	}

	public void delete(String classname, String[] keys, Object[] values) {
		this.publicDao.delete(classname, keys, values);
	}
    
	public <T> List<T> find(Class<T> cls) {
		return this.publicDao.find(cls);
	}
	
	public <T> T find(Class<T> cls, String id) {
		return this.publicDao.find(cls, id);
	}

	public <T> T find(Class<T> cls, Integer id) {
		return this.publicDao.find(cls, id);
	}

	public <T> List<T> find(Class<T> cls, T exampleEntity) {
		return this.publicDao.find(cls, exampleEntity);
	}

	public <T> List<T> find(Class<T> cls, String classname, String[] keys,
			Object[] values) {
		return this.publicDao.find(cls, classname, keys, values);
	}

	public <T> List<T> find(Class<T> cls, String classname, String[] keys,
			Object[] values, String orders) {
		return this.publicDao.find(cls, classname, keys, values, orders);
	}
	
	public <T> List<T> find(Class<T> cls, String classname, String prop,
			String[] keys, Object[] values) {
		return this.publicDao.find(cls, classname, prop, keys, values);
	}

	public <T> List<List<T>> find(Class<T> cls, String classname,
			String[] prop, String[] keys, Object[] values) {
		return this.publicDao.find(cls, classname, prop, keys, values);
	}

	public <T> List<T> find (Class<T> cls,String classname,String prop){
		return this.publicDao.find(cls, classname, prop);
	}
	public <T> List<List<T>> find(Class<T> cls, String classname, String[] prop) {
		return this.publicDao.find(cls, classname, prop);
	}

	public <T> List<List<T>> find(Class<T> cls, String classname,
			String[] prop, String orders) {
		return this.publicDao.find(cls, classname, prop, orders);
	}

	public <T> List<T> find(Class<T> cls, String[] prop, String[] keys,
			Object[] values, String orders) {
		return this.publicDao.find(cls, prop, keys, values, orders);
	}

	public <T> List<List<T>> findByHql(Class<T> cls, String queryString) {
		return this.publicDao.findByHql(cls, queryString);
	}
	
	public <T> List<T> findByProperty(String queryString,String property){
		return this.publicDao.findByProperty(queryString, property);
	}

	public void save(Object obj) {
		this.publicDao.save(obj);
	}

	public void save(Object[] objs) {
		this.publicDao.save(objs);
	}

	public void update(Object obj) {
		this.publicDao.update(obj);
	}
	public void bulkUpdate(String queryString){
		this.publicDao.bulkUpdate(queryString);
	}
	
	public void saveOrUpdate(Object obj1,Object obj2){
		
		
		this.publicDao.saveOrUpdate(obj1);
		this.publicDao.saveOrUpdate(obj2);
	}
	public void saveOrUpdate(Object obj){
		
		this.publicDao.saveOrUpdate(obj);
	}
	//批量修改
	public <T> int[] batchUpdate12(final List<T> obj) {
		return this.publicDao.batchUpdate12(obj);
	}
	//批量插入
	 public <T> int[] batchUpdate(final List<T> obj) {
		return this.publicDao.batchUpdate(obj);
	 }
	
	

	/* (non-Javadoc)  作者：guoqiang
	 * @see xx.quanxian.service.BasicService#regJson(java.lang.String)
	 */
	public boolean regJson(String uid) {
		String[] keys={"userId"};
		String[] values={uid};
		List<Userinfo> listui=this.publicDao.find(Userinfo.class, "Userinfo", keys, values);
		if(!listui.isEmpty()&&listui.size() == 1){
			return true;//表示此uid，已经存在
		}
		return false;//表示此uid，不存在
	}


   /*作者：tlq*/
	public String changeRoleAndFunction(Roles role, List<String> funIds,List<Rolefunction> rfs) {
		if(funIds==null||funIds.isEmpty()){
			for(Rolefunction rf:rfs){
				this.publicDao.delete(rf);
			} 
		}
		else {
			for(Rolefunction rf:rfs){
				if(!funIds.contains(rf.getId().getActionname())){
					this.publicDao.delete(rf);
				}
			}
			for(String f:funIds){
				List<Rolefunction> rrr=findRoleFunByFunId(role,f);
				if(rrr.size()!=0||!rrr.isEmpty()){
					continue;
				}
				else {
					Rolefunction roleF=new Rolefunction();
					RolefunctionId rfi = new RolefunctionId();
					rfi.setActionname(this.publicDao.find(Functions.class, f).getActionname());
					rfi.setRoleid(role.getRoleid());
					
					roleF.setId(rfi);
					this.publicDao.save(roleF);
				}
			}
		}
		return "success";
	}
   //作者：tlq
	public String changeRoleAndFunction(List<String> roleIDs,
			List<String> funIds) {
		for(String roleid:roleIDs){
			Roles role=this.publicDao.find(Roles.class, Integer.parseInt(roleid));
			List<Rolefunction> rfs = new ArrayList<Rolefunction>();
			rfs=findRoleAndFunctionsChecked(role.getRoleid());
			for(String f:funIds){
				List<Rolefunction> rrr=findRoleFunByFunId(role,f);
				if(rrr.size()!=0||!rrr.isEmpty()){
					continue;
				}
				else {
					Rolefunction roleF=new Rolefunction();
					Functions func=this.publicDao.find(Functions.class, Integer.parseInt(f));
					RolefunctionId rfi = new RolefunctionId();
					rfi.setActionname(func.getActionname());
					rfi.setRoleid(role.getRoleid());
					roleF.setId(rfi);
					this.publicDao.save(roleF);
				}
			}
		}
		return "success";
	}
/*
 * 从一个表中查出num条记录然后返回
 * tlq
 */
	public <T> List<T> findByNumber(Class<T> cls,String classname,int num){
		String hql = "from ";
		hql += classname;
		hql += " where roleid > 0";
		int first = 0;
		return this.publicDao.getEntity(cls, hql, first, num);
	}
	
//作者：tlq
	public List<Rolefunction> findRoleAndFunctionsChecked(int id) {
		String[] keys={"roles.roleid"};
		Object[] values={id};
		List<Rolefunction> rfs=this.publicDao.find(Rolefunction.class, "Rolefunction", keys, values);
		return rfs;
	}
//作者：tlq
	public List<Rolefunction> findRoleFunByFunId(Roles role, String funId) {
		String[] keys={"functions.actionname","roles.roleid"};
		Object[] values={funId,role.getRoleid()};
		List<Rolefunction> roleFuns=this.publicDao.find(Rolefunction.class, "Rolefunction", keys, values);
		return roleFuns;
	}

	/* (non-Javadoc)
	 * @see xx.quanxian.service.BaseService#login(xx.bean.UserInfo)
	 * 作者：tlq
	 * 功能：判断用户是否在线
	 */
	public String login(Userinfo edu) {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		OnlineSessionList olsl=new OnlineSessionList();
		/**
		 * 检测针对ie6以后的版本的浏览器，在同一款浏览器中，因先后登录了两个不同的用户共同使用了同一个session的问题。
		 */
		String uid=(String)hs.getAttribute("uid");
		if(uid==null){
			System.out.println("此session无人使用过。原uid是------->"+uid);
			if(olsl.hm.containsKey(edu.getUserId())){
				return "islogin";
			}else{
				olsl.hm.put(edu.getUserId(), hs.getId());
				return "notLog";
			}
			
		}else{
			System.out.println("此session已经有人使用过了！原uid是--------->"+uid);
			//判断新登录的edu是否已经登录了
			if(olsl.hm.containsKey(edu.getUserId())){
				return "islogin";
			}else{
				//表示session虽然被用过，但当前准备登陆的用户未用过
				OnlineSessionList.hm.remove(uid);
				hs.invalidate();
				
				olsl.hm.put(edu.getUserId(), hs.getId());
				return "notLog";
			}
			
		}
		
	}


	/* (non-Javadoc)
	 * @see xx.quanxian.service.BaseService#findHql(java.lang.Class, java.lang.String)
	 */
	public <T> List<T> findHql(Class<T> cls, String hql) {
		return this.publicDao.findHql(cls, hql);
	}
 
	//===============分页================
	public <T> List<T> find(Class<T> cls, String classname, String[] keys,
			Object[] values, String orders,int page,int rows) {
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		String hql="from "+classname+" where ";
		for(int i=0;i<keys.length-1;i++){
			hql +=keys[i]+" like '"+values[i]+"' and ";
		}
		hql +=keys[keys.length-1]+" like '"+values[keys.length-1]+"' ";
		if (orders!= null)
			hql +=orders;
		return this.publicDao.getEntity(cls, hql, page, rows);
	}
	public <T> List<T> findLike(Class<T> cls, String classname, String[] keys,
			Object[] values, String orders,int page,int rows) {
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		String hql="from "+classname+" where ";
		for(int i=0;i<keys.length-1;i++){
			hql +=keys[i]+" like '%"+values[i]+"%' and ";
		}
		hql +=keys[keys.length-1]+" like '%"+values[keys.length-1]+"%' ";
		if (orders!= null)
			hql +=orders;
		return this.publicDao.getEntity(cls, hql, page, rows);
	}
	public int getTotalP(String classname,String[] keys,
			Object[] values){
		if (keys == null)
			return 0;
		if (keys.length == 0)
			return 0;
		String hql="select count(*) from "+classname+" where ";
		for(int i=0;i<keys.length-1;i++){
			hql +=keys[i]+" like "+values[i]+" and ";
		}
		hql +=keys[keys.length-1]+" like "+values[keys.length-1]+" ";
		return this.publicDao.getTotalPages(hql);
	}
	
	public int getTotalPages(String classname,String[] keys,
			Object[] values){
		if (keys == null)
			return 0;
		if (keys.length == 0)
			return 0;
		String hql="select count(*) from "+classname+" where ";
		for(int i=0;i<keys.length-1;i++){
			hql +=keys[i]+" like '%"+values[i]+"%' and ";
		}
		hql +=keys[keys.length-1]+" like '%"+values[keys.length-1]+"%' ";
		return this.publicDao.getTotalPages(hql);
	}
	
	public <T> List<T> findByOne(Class<T> cls, String classname, String[] keys,
			Object[] values,int page,int rows) {
		if (keys == null)
			return null;
		if (keys.length == 0)
			return null;
		String hql="from "+classname+" where ";
		for(int i=0;i<keys.length-1;i++){
			hql +=keys[i]+"="+values[i]+" and ";
		}
		hql +=keys[keys.length-1]+"='"+values[keys.length-1]+"'";
		System.out.println(hql);
		return this.publicDao.getEntity(cls, hql, page, rows);
	}
	
	public int getTotalPages2(String classname,String[] keys,
			Object[] values){
		if (keys == null)
			return 0;
		if (keys.length == 0)
			return 0;
		String hql="select count(*) from "+classname+" where ";
		for(int i=0;i<keys.length-1;i++){
			hql +=keys[i]+"="+values[i]+" and ";
		}
		hql +=keys[keys.length-1]+"="+values[keys.length-1];
		return this.publicDao.getTotalPages(hql);
	}
    //=======================================

	
		/* (non-Javadoc)
		 * @see xx.quanxian.service.BaseService#findAll(java.lang.Class, java.lang.String, int, int)
		 */
		
	public <T> List<T> findAll(Class<T> cls, String classname, int page,
			int rows) {
		String hql = "from "+classname;
		return this.publicDao.getEntity(cls, hql, page, rows);
		
	}

	
	public <T> List<T> findSql(Class<T> cls, String hql, int page,
			int rows) {
		return this.publicDao.getEntity(cls, hql, page, rows);
	}

	
		/* (non-Javadoc)
		 * @see xx.quanxian.service.BaseService#getTotal(java.lang.String)
		 */
		
	public int getTotal(String classname) {
		String hql = "select count(*) from "+classname;
		return this.publicDao.getTotalPages(hql);
	}
	
	public int getTotalSql(String hql) {
		
		return this.publicDao.getTotalPages(hql);
	}

	
		/* (non-Javadoc)
		 * @see xx.quanxian.service.BaseService#findByType(java.lang.Class, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
		 */
		
	public <T> List<T> findByTypage(Class<T> cls, String classname, String type,
			String word, String orders, int page, int rows) {
		if (type == null)
			return null;
		
		String hql="from "+classname+" where "+type;
			hql +=" like '%"+word+"%'";
		if (orders!= null)
			hql +=orders;
		return this.publicDao.getEntity(cls, hql, page, rows);
	}
	//=======================================
	public <T> List<T> findBPfenye(String queryString,String property,int page,int rows_s){
		return this.publicDao.findBPfenye(queryString, property, page, rows_s);
	}
	public <T> List<T> findBPfenye(String queryString,int property,int page,int rows_s){
		return this.publicDao.findBPfenye(queryString, property, page, rows_s);
	}
	public int sumTotal_int(String classname,String name,int value) {
		String hql = "select count(*) from "+classname;
		hql += " where "+name+" = "+value;
		return this.publicDao.getTotalPages(hql);
	}
	public int sumTotal_string(String classname,String name,String value) {
		String hql = "select count(*) from "+classname;
		hql += " where "+name+" = "+value;
		return this.publicDao.getTotalPages(hql);
	}
	public int getSum(String classname,String disname,String name,int value){
		String hql = "select count(DISTINCT "+disname+" )"+" from "+classname;
		hql += " where "+name+" = "+value;
		return this.publicDao.getTotalPages(hql);
	}

	public int findMax(String name,String cls) {
		String queryString = "select max("+name+") from "+cls;
		return this.publicDao.findMax(queryString);
	}
	public List<Scwj> findScwj(int sjno,int sxh,int dtth){
		HttpSession hs=ServletActionContext.getRequest().getSession();
		List<String> arr=new ArrayList<String>();
		List<Scwj> rows=new ArrayList<Scwj>();
		
		String[] keys=new String[3];keys[0]="sjno";keys[1]="sxh";keys[2]="dt_th";
		Object[] values=new Object[3];values[0]=sjno;values[1]=sxh;values[2]=dtth;
		List<Integer> listzsdbh=this.find(Integer.class, "XsdyjlCztZsd", "zsdbh", keys, values);
		
		for(int i=0;i<listzsdbh.size();i++){
			String[] key_s=new String[1];key_s[0]="zsdbh";
			Object[] value_s=new Object[1];value_s[0]=listzsdbh.get(i);
			List<Zlzsddy> listzlzsddy=this.publicDao.find(Zlzsddy.class, "Zlzsddy", key_s, value_s);
			for(int j=0;j<listzlzsddy.size();j++){
				key_s[0]="zlbh";
				value_s[0]=listzlzsddy.get(j).getId().getZlbh();
				List<Scwj> scwj=this.publicDao.find(Scwj.class, "Scwj", key_s, value_s);
				//scwj.get(0).setFjxx(listzlzsddy.get(j).getZsd().getZsdmc());//此处暂时用了“scwj类”的fjxx属性存放了zsdmc
				arr.add(listzlzsddy.get(j).getZsd().getZsdmc());
				rows.add(scwj.get(0));
			}
		}
		hs.setAttribute("arr", arr);
		return rows;
	}

	/* (non-Javadoc)
	 * @see xx.quanxian.service.BaseService#getList(java.lang.Class, java.lang.String)
	 */
	@Override
	public <T> List<T> getList(Class<T> cls, String hql) {
		// TODO Auto-generated method stub
		return this.publicDao.getList(cls, hql);
	}

	/* (non-Javadoc)
	 * @see xx.quanxian.service.BaseService#getEntity(java.lang.Class, java.lang.String, int, int)
	 */
	@Override
	public <T> List<T> getEntity(Class<T> cls, String hql, int page, int rows) {
		// TODO Auto-generated method stub
		return this.publicDao.getEntity(cls, hql, page, rows);
	}

	

}
