/*
 *@(#)xx.spdh.action
 *@BflbAction.java.java  
 *@创建时间:2011-8-28上午10:00:37
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import xx.collection.bean.Dmtzl;
import xx.collection.bean.Scwj;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.Bflb;
import xx.spdh.bean.ZxspLb;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @BflbAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
@SuppressWarnings("serial")
public class BflbAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<String> filenameList = new ArrayList<String>();
	private List<Dmtzl> dmtzllist = new ArrayList<Dmtzl>();
	private List<Scwj> scwjlist = new ArrayList<Scwj>();
	private List<ZxspLb> rows = new ArrayList<ZxspLb>();//对象集合  
	private List<ZxspLb> zxsplb = new ArrayList<ZxspLb>();
	
    private String filename;
	
	private List<Scwj> scwj;
	
	private Bflb bflblist;

	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

	@JSON(serialize=false)
	public List<String> getFilenameList() {
		return filenameList;
	}

	public void setFilenameList(List<String> filenameList) {
		this.filenameList = filenameList;
	}

	@JSON(serialize=false)
	public List<Scwj> getScwj() {
		return scwj;
	}

	public void setScwj(List<Scwj> scwj) {
		this.scwj = scwj;
	}

	@JSON(serialize=false)
	public Bflb getBflblist() {
		return bflblist;
	}

	public void setBflblist(Bflb bflblist) {
		this.bflblist = bflblist;
	}
	
	@JSON(serialize=false)
	public List<Dmtzl> getDmtzllist() {
		return dmtzllist;
	}

	public void setDmtzllist(List<Dmtzl> dmtzllist) {
		this.dmtzllist = dmtzllist;
	}
	
	@JSON(serialize=false)
	public List<Scwj> getScwjlist() {
		return scwjlist;
	}

	public void setScwjlist(List<Scwj> scwjlist) {
		this.scwjlist = scwjlist;
	}

	@JSON(serialize=false)
	public List<ZxspLb> getRows() {
		return rows;
	}

	public void setRows(List<ZxspLb> rows) {
		this.rows = rows;
	}
	
	@JSON
	public List<ZxspLb> getZxsplb() {
		return zxsplb;
	}

	public void setZxsplb(List<ZxspLb> zxsplb) {
		this.zxsplb = zxsplb;
	}

	@JSON(serialize=false)
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Action(value="/bflb",results={@Result(name="success",type="json")})
	public String bflb() {
		String hql = "from Scwj where filename like '%"+".flv"+"%'";
		scwj = this.baseservice.findHql(Scwj.class, hql);
		for(int i=0;i<scwj.size();i++){
			filenameList.add(scwj.get(i).getFilename());
		}
		return SUCCESS;
	}
	
	@Action(value="/bflb1",results={@Result(name="root",type="json")})
	public String bflb1() {
		//String hql1 = "from Dmtzl where filename like '%.flv'";
		String hql2 = "select count(*)from Dmtzl where filename like '%.flv'";
		//dmtzllist = this.baseservice.findSql(Dmtzl.class, hql1, page, rows_s);
		//total=this.baseservice.getTotalSql(hql2);  //记录条数的记录
		String hql = "from Scwj where filename like '%"+".flv"+"%'";
		scwjlist = this.baseservice.findHql(Scwj.class, hql);
	
		for(int i=0; i<scwjlist.size(); i++) {
			int j = scwjlist.get(i).getFilename().indexOf(".");
			ZxspLb element = new ZxspLb();
			element.setInt1(scwjlist.get(i).getNo());
			element.setInt2(scwjlist.get(i).getDmtzl().getZlbh());
			element.setStr1(scwjlist.get(i).getFilename().substring(0, j));
			element.setStr2(scwjlist.get(i).getDmtzl().getZlms());
			zxsplb.add(element);
		}
		return "root";
	}
}
