/*
 *@(#)xx.xuexi.dzbj.action
 *@Bjshow.java.java  
 *@创建时间:2011-11-26上午10:51:10
 *@作者：guangge
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.dzbj.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

import xx.collection.bean.Dmtzl;
import xx.collection.bean.Dzbj;
import xx.collection.bean.DzbjId;
import xx.collection.bean.Scwj;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.DmtZl;
import xx.xuexi.dzbj.dao.JdbcActorDao;

/**
 * @Bjshow <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class Bjshow extends ActionSupport{
	@Resource(name="baseService")
	private BaseService baseservice;
	
	
	private int page;//当前页
	private int row_s;//每页显示的条数
	private int total;//记录数据条数
	
	
	private String classno;
	private List<Dzbj> dmtzllist = new ArrayList<Dzbj>();
	private List<String> note0 = new ArrayList<String>();
	private List<String> source0 = new ArrayList<String>();
	private List<Integer> xssx = new ArrayList<Integer>();
	private List<Integer> tmbh = new ArrayList<Integer>();
	private List<String> zlid = new ArrayList<String>();
	private List<String> path = new ArrayList<String>();
	private List<String> zlmc = new ArrayList<String>();
	private List<Integer> xs = new ArrayList<Integer>();
	private List<String> weizhi = new ArrayList<String>();
	/**
	 * @return the xs
	 */
	
	@JSON(serialize=false)
	public List<Integer> getXs() {
		return xs;
	}
	/**
	 * @return the weizhi
	 */
	
	/**
	 * @param xs the xs to set
	 */
	public void setXs(List<Integer> xs) {
		this.xs = xs;
	}
	/**
	 * @return the weizhi
	 */
	public List<String> getWeizhi() {
		return weizhi;
	}

	/**
	 * @param weizhi the weizhi to set
	 */
	public void setWeizhi(List<String> weizhi) {
		this.weizhi = weizhi;
	}

	/**
	 * @return the tm
	 */
	@JSON(serialize=false)
	public List<Integer> getTm() {
		return tm;
	}
	/**
	 * @param tm the tm to set
	 */
	public void setTm(List<Integer> tm) {
		this.tm = tm;
	}


	private List<Integer> tm = new ArrayList<Integer>();

	@Resource(name="jdbcDao")
	private JdbcActorDao j;
	
	/**
	 * @return the j
	 */
	@JSON(serialize=false)
	public JdbcActorDao getJ() {
		return j;
	}


	/**
	 * @param j the j to set
	 */
	public void setJ(JdbcActorDao j) {
		this.j = j;
	}
	

	/**
	 * @return the note0
	 */
	public List<String> getNote0() {
		return note0;
	}
	/**
	 * @param note0 the note0 to set
	 */
	public void setNote0(List<String> note0) {
		this.note0 = note0;
	}
	/**
	 * @return the source0
	 */
	public List<String> getSource0() {
		return source0;
	}
	/**
	 * @param source0 the source0 to set
	 */
	public void setSource0(List<String> source0) {
		this.source0 = source0;
	}
	/**
	 * @return the xssx
	 */
	public List<Integer> getXssx() {
		return xssx;
	}
	/**
	 * @param xssx the xssx to set
	 */
	public void setXssx(List<Integer> xssx) {
		this.xssx = xssx;
	}
	/**
	 * @return the tmbh
	 */
	
	public List<Integer> getTmbh() {
		return tmbh;
	}
	/**
	 * @param tmbh the tmbh to set
	 */
	public void setTmbh(List<Integer> tmbh) {
		this.tmbh = tmbh;
	}
	/**
	 * @return the dmtzllist
	 */
	@JSON(serialize=false)
	public List<Dzbj> getDmtzllist() {
		return dmtzllist;
	}
	/**
	 * @param dmtzllist the dmtzllist to set
	 */
	public void setDmtzllist(List<Dzbj> dmtzllist) {
		this.dmtzllist = dmtzllist;
	}

	/**
	 * @return the baseservice
	 */
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	/**
	 * @param baseservice the baseservice to set
	 */
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	/**
	 * @return the page
	 */
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the row_s
	 */
	@JSON(serialize=false)
	public int getRow_s() {
		return row_s;
	}
	/**
	 * @param row_s the row_s to set
	 */
	public void setRow_s(int row_s) {
		this.row_s = row_s;
	}
	/**
	 * @return the total
	 */
	
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	/**
	 * @return the classno
	 */
	@JSON(serialize=false)
	public String getClassno() {
		return classno;
	}
	/**
	 * @param classno the classno to set
	 */
	public void setClassno(String classno) {
		this.classno = classno;
	}

	
	/**
	 * @return the zlid
	 */
	
	/**
	 * @return the path
	 */
	public List<String> getPath() {
		return path;
	}
	/**
	 * @return the zlid
	 */
	public List<String> getZlid() {
		return zlid;
	}
	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(List<String> zlid) {
		this.zlid = zlid;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(List<String> path) {
		this.path = path;
	}
	/**
	 * @return the zlmc
	 */
	public List<String> getZlmc() {
		return zlmc;
	}
	/**
	 * @param zlmc the zlmc to set
	 */
	public void setZlmc(List<String> zlmc) {
		this.zlmc = zlmc;
	}
	@Action(value="/shownote",results={@Result(name="success",type="json")})
	public String courseData() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		if(xs.size()!=0){
		List<Dzbj> ll= new ArrayList<Dzbj>();
		for(int i=0;i<xs.size();i++){
			Dzbj d = new Dzbj();
			DzbjId did = new DzbjId();
			did.setClassno(classno);
			did.setUserId(userid);
			did.setTmbh(tm.get(i));
			d.setId(did);
			d.setXssxh(xs.get(i));
			ll.add(d);
			
		}
		
		this.j.batchUpdate(ll);
		}
		System.out.println(1);
		System.out.println(xs.size());
		
		
		String hql="from Dzbj d where d.id.userId='"+userid+"' and d.id.classno='"+classno+"' order by xssxh asc";
		dmtzllist =this.baseservice.findSql(Dzbj.class, hql, page+1, row_s);
		List<Dzbj> l= this.baseservice.findHql(Dzbj.class,"from Dzbj d where d.id.userId='"+userid+"' and d.id.classno='"+classno+"'");  //记录条数的记录
		total=l.size();
		for(int i=0; i<dmtzllist.size(); i++ ) {
			note0.add(dmtzllist.get(i).getTmnr());
			xssx.add(dmtzllist.get(i).getXssxh());
			tmbh.add(dmtzllist.get(i).getId().getTmbh());
			zlid.add(dmtzllist.get(i).getZlid());
			weizhi.add(dmtzllist.get(i).getWeizhi());
			if(dmtzllist.get(i).getPath()==null){
				
				source0.add("haha.jpg");
			}else{
				
				source0.add(dmtzllist.get(i).getPath());
			}
			
			
		}
		
		if (zlid==null||zlid.size()==0) {
			return SUCCESS;
		}
		for (int i = 0; i < zlid.size(); i++) {
			String[] keys={"dmtzl.zlbh"};
			Object[] values={Integer.parseInt(zlid.get(i))};
			Scwj scwj=this.baseservice.find(Scwj.class, "Scwj", keys, values).get(0);
			zlmc.add(scwj.getFilename());
			path.add(scwj.getFilepath());
		}
		
		return SUCCESS;
		
	}
	
	@Action(value="/shownote1",results={@Result(name="success",type="json")})
	public String courseData1() {
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		System.out.println("笔记测试1："+xs);
    	System.out.println("no:"+classno);
    	System.out.println("tm:"+tm);
		
		if(xs.size()!=0){
		List<Dzbj> ll= new ArrayList<Dzbj>();
		for(int i=0;i<xs.size();i++){
			Dzbj d = new Dzbj();
			DzbjId did = new DzbjId();
			
			did.setUserId(userid);
			did.setTmbh(tm.get(i));
			d.setZlid(classno);
			d.setId(did);
			d.setXssxh(xs.get(i));
			ll.add(d);
			
		}
		
		this.j.batchUpdate(ll);
		}
		System.out.println(1);
		System.out.println(xs.size());
		
		
		String hql="from Dzbj d where d.id.userId='"+userid+"' and d.zlid='"+classno+"' order by xssxh asc";
		dmtzllist =this.baseservice.findSql(Dzbj.class, hql, page+1, row_s);
		List<Dzbj> l= this.baseservice.findHql(Dzbj.class,"from Dzbj d where d.id.userId='"+userid+"' and d.zlid='"+classno+"'");  //记录条数的记录
		total=l.size();
		for(int i=0; i<dmtzllist.size(); i++ ) {
			note0.add(dmtzllist.get(i).getTmnr());
			xssx.add(dmtzllist.get(i).getXssxh());
			tmbh.add(dmtzllist.get(i).getId().getTmbh());
			if(dmtzllist.get(i).getPath()==null){
				
				source0.add("haha.jpg");
			}else{
				
				source0.add(dmtzllist.get(i).getPath());
			}

		}
		return SUCCESS;
	}
}
