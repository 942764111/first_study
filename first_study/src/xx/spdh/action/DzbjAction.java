/*
 *@(#)xx.spdh.action
 *@DzbjAction.java.java  
 *@创建时间:2011-11-3下午04:25:06
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import xx.collection.bean.Jxjh;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Zljl;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.DmtZl;

/**
 * @DzbjAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class DzbjAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Dmtzl> dmtzllist = new ArrayList<Dmtzl>();
	private List<DmtZl> rows = new ArrayList<DmtZl>();
	
	private List<Dzbj> dzbjlist = new ArrayList<Dzbj>();
	
	private int page;//当前页
	private int row_s;//每页显示的条数
	private int total;//记录数据条数
	
	private String userId;
	private int weizhi;
	private String zlmc;
	private Boolean sfjx = true;
	
	private File request;
	private File flex;



	/**
	 * @return the flex
	 */
	@JSON(serialize=false)
	public File getFlex() {
		return flex;
	}

	/**
	 * @param flex the flex to set
	 */
	public void setFlex(File flex) {
		this.flex = flex;
	}

	/**
	 * @return the request
	 */
	@JSON(serialize=false)
	public File getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(File request) {
		this.request = request;
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
	 * @return the dmtzllist
	 */
	@JSON(serialize=false)
	public List<Dmtzl> getDmtzllist() {
		return dmtzllist;
	}

	/**
	 * @param dmtzllist the dmtzllist to set
	 */
	public void setDmtzllist(List<Dmtzl> dmtzllist) {
		this.dmtzllist = dmtzllist;
	}

	/**
	 * @return the rows
	 */
	public List<DmtZl> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<DmtZl> rows) {
		this.rows = rows;
	}

	/**
	 * @return the dzbjlist
	 */
	@JSON(serialize=false)
	public List<Dzbj> getDzbjlist() {
		return dzbjlist;
	}

	/**
	 * @param dzbjlist the dzbjlist to set
	 */
	public void setDzbjlist(List<Dzbj> dzbjlist) {
		this.dzbjlist = dzbjlist;
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
	@JSON(serialize=false)
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
	 * @return the userId
	 */
	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the weizhi
	 */
	@JSON(serialize=false)
	public int getWeizhi() {
		return weizhi;
	}

	/**
	 * @param weizhi the weizhi to set
	 */
	public void setWeizhi(int weizhi) {
		this.weizhi = weizhi;
	}

	/**
	 * @return the zlmc
	 */
	@JSON(serialize=false)
	public String getZlmc() {
		return zlmc;
	}

	/**
	 * @param zlmc the zlmc to set
	 */
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}

	@Action(value="/courseData",results={@Result(name="root",type="json")})
	public String courseData() {
		//dmtzllist = this.baseservice.find(Dmtzl.class);
		dmtzllist = this.baseservice.findAll(Dmtzl.class, "Dmtzl", page+1, row_s);
		total=this.baseservice.getTotal("Dmtzl");  //记录条数的记录
		
		for(int i=0; i<dmtzllist.size(); i++ ) {
			
			DmtZl element = new DmtZl();
			element.setStr1(dmtzllist.get(i).getUserinfo().getUserId());
			element.setStr2(dmtzllist.get(i).getWjlx().getLxm());
			element.setStr3(dmtzllist.get(i).getZlmc());
			element.setStr4(dmtzllist.get(i).getFilename());
			element.setStr5(dmtzllist.get(i).getZlms());
			element.setStr6(dmtzllist.get(i).getZlly());
			element.setStr7(dmtzllist.get(i).getZlscm());
			element.setStr8(dmtzllist.get(i).getZmfilename());
			element.setStr9(dmtzllist.get(i).getZlmd5());
			element.setDt(dmtzllist.get(i).getScrq());
			element.setInt1(dmtzllist.get(i).getZlbh());
			element.setInt2(dmtzllist.get(i).getLlcs());
			element.setInt3(dmtzllist.get(i).getCssl());
			element.setInt4(dmtzllist.get(i).getChangdu());
			element.setInt5(total);
			rows.add(element);   //把数据放进rows，实现分页查询显示
		}
		return "root";
	}
	
	/**
	 * 此action用来处理退出登录，当关闭播放视频页面时会触动该action
	 * 在该方法中，将记录视频播放的时间、用户名以及播放的位置
	*/
	@Action(value="/zljl",results={@Result(name="success",type="json")})
	public String zljl(){
		String nowTimeStr = "";
		try {
			SimpleDateFormat sDateFormat;
			sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			sDateFormat.setLenient(false);
		    nowTimeStr = sDateFormat.format(new Date());
			
			HttpSession hs = ServletActionContext.getRequest().getSession();
			String userid = (String)hs.getAttribute("uid");
			
			//通过zlmc查找zlbh
			String[] keys1 = new String[1];
			keys1[0] = "zlmc";
			Object[] values1 = new Object[1];
			values1[0] = zlmc;
			List<Integer> zlbh = this.baseservice.find(Integer.class, "Dmtzl", "id.zlbh", keys1, values1);
			
			Userinfo userinfo = new Userinfo();
			userinfo.setUserId(userid);
			
			Dmtzl dmtzl = new Dmtzl();
			dmtzl.setZlbh(zlbh.get(0));
			
			Jxjh jxjh = new Jxjh();
			jxjh.setId(1);
			
			Zljl zljl = new Zljl();
			zljl.setUserinfo(userinfo);
			zljl.setDmtzl(dmtzl);
			zljl.setWeizhi(weizhi);
			zljl.setSfjx(sfjx);
			zljl.setJxjh(jxjh);
			Date rq = sDateFormat.parse(nowTimeStr);
			
			zljl.setOpenTime(rq);
			this.baseservice.save(zljl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
		
	}
	
	/**
	 * 此action用来处理退出登录，当关闭播放视频页面时会触动该action
	 * 当用户再次登录观看该视频时，能够显示上次打开的时间
	*/
	@Action(value="/jxxx1",results={@Result(name="success",type="json")})
	public String jxxx1(){
		return SUCCESS;
	}
	
	@Action(value="/dzbj",results={@Result(name="success",location="/spdh/dzbj.jsp")})
	public String dzbj(){
		return SUCCESS;
	}
	
	
}
