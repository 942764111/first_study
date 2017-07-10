/*
 *@(#)xx.spdh.action
 *@VideoPdfAction.java.java  
 *@创建时间:2011-11-10上午09:33:46
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

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

import xx.collection.bean.Dmtzl;
import xx.collection.bean.Jxjh;
import xx.collection.bean.Scwj;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Zljl;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.ZlJl;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @VideoPdfAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class ZljlAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Zljl> zljllist = new ArrayList<Zljl>();
	private List<ZlJl> rows = new ArrayList<ZlJl>();
	private Zljl zljl = new Zljl();
	
	private int page;//当前页
	private int rows_s;//每一页显示的条数
	private int total;//记录数量
	
	private int zlbh;
	private String weizhi;
	private Date open_time;
	private int jxjh_id;
	private String path;
	
	private int zljl_no;
	
	
	
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
	 * @return the zljllist
	 */
	@JSON(serialize=false)
	public List<Zljl> getZljllist() {
		return zljllist;
	}

	/**
	 * @param zljllist the zljllist to set
	 */
	public void setZljllist(List<Zljl> zljllist) {
		this.zljllist = zljllist;
	}

	/**
	 * @return the page
	 */
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
	 * @return the rows_s
	 */
	public int getRows_s() {
		return rows_s;
	}

	/**
	 * @param rows_s the rows_s to set
	 */
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}

	/**
	 * @return the zljl
	 */
	@JSON(serialize=false)
	public Zljl getZljl() {
		return zljl;
	}

	/**
	 * @param zljl the zljl to set
	 */
	public void setZljl(Zljl zljl) {
		this.zljl = zljl;
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
	 * @return the zlbh
	 */
	public int getZlbh() {
		return zlbh;
	}

	/**
	 * @param zlbh the zlbh to set
	 */
	public void setZlbh(int zlbh) {
		this.zlbh = zlbh;
	}

	/**
	 * @return the weizhi
	 */
	

	/**
	 * @return the jxjh_id
	 */
	@JSON(serialize=false)
	public int getJxjh_id() {
		return jxjh_id;
	}

	/**
	 * @return the weizhi
	 */
	@JSON(serialize=false)
	
	public String getWeizhi() {
		return weizhi;
	}

	/**
	 * @param weizhi the weizhi to set
	 */
	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

	/**
	 * @param jxjh_id the jxjh_id to set
	 */
	public void setJxjh_id(int jxjh_id) {
		this.jxjh_id = jxjh_id;
	}

	/**
	 * @return the path
	 */
	@JSON(serialize=false)
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the zljl_no
	 */
	@JSON(serialize=false)
	public int getZljl_no() {
		return zljl_no;
	}

	/**
	 * @param zljl_no the zljl_no to set
	 */
	public void setZljl_no(int zljl_no) {
		this.zljl_no = zljl_no;
	}

	/**
	 * @return the rows
	 */
	public List<ZlJl> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<ZlJl> rows) {
		this.rows = rows;
	}
	
	@Action(value="/saveJilu",results={@Result(name="success",type="json")})
	public String saveJilu(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
	//	String[] keys2={"dmtzl.zlbh,userinfo.userId"};
	//	Object[] values={zlbh,userid};
	//	List<Zljl> list=this.baseservice.find(Zljl.class, "Zljl", keys2, values);
		
		try {
			//生成当前日期
			SimpleDateFormat sDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			sDateFormat1.setLenient(false);
			String nowTimeStr1 = sDateFormat1.format(new Date());
			open_time = sDateFormat1.parse(nowTimeStr1);
			
			
	
			Dmtzl dmtzl = new Dmtzl();
			dmtzl.setZlbh(zlbh);
			
			Userinfo userinfo = new Userinfo();
			userinfo.setUserId(userid);
			
			Jxjh jxjh = new Jxjh();
			jxjh.setId(jxjh_id);
			
			Zljl zljl = new Zljl();
			zljl.setUserinfo(userinfo);
			zljl.setDmtzl(dmtzl);
			zljl.setJxjh(jxjh);
			zljl.setWeizhi(Integer.parseInt(weizhi));
			zljl.setOpenTime(open_time);
			
			String[] keys1={"dmtzl.zlbh"};
			Object[] values2 = {zlbh};
		   List<Scwj>sList=this.baseservice.find(Scwj.class, "Scwj", keys1, values2);
			zljl.setPath(sList.get(0).getFilepath());
			//if (list.size()==0) {
				this.baseservice.save(zljl);
//			} else {
//                Zljl zljl2=list.get(0);
//                zljl2.setWeizhi(weizhi);
//                zljl2.setOpenTime(open_time);
//                this.baseservice.update(zljl2);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
		
	}

	/**
	 * 此action用来处理退出登录，当关闭播放视频页面时会触动该action
	 * 当用户再次登录观看该视频时，能够显示上次打开的时间
	*/
	@Action(value="/zljllist1",results={@Result(name="success",type="json")})
	public String zljllist1(){
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		
		String hql = "from Zljl where userId='" + userid + "'";
		zljllist = this.baseservice.findSql(Zljl.class, hql, page, rows_s);
		
		//zljllist = this.baseservice.findAll(Zljl.class, "Zljl", page, rows_s);
		total = zljllist.size();
		for(int i=0;i<zljllist.size();i++) {
			ZlJl zljl = new ZlJl();
			zljl.setUserId(zljllist.get(i).getUserinfo().getUserId());
			zljl.setOpen_time(zljllist.get(i).getOpenTime());
			zljl.setZlmc(zljllist.get(i).getDmtzl().getZlmc());
			zljl.setZljl_no(zljllist.get(i).getZljlNo());
			zljl.setWeizhi(zljllist.get(i).getWeizhi());
			//zljl.setJxjh_id(zljllist.get(i).getJxjh().getId());
			zljl.setPath(zljllist.get(i).getPath());
			zljl.setZlbh(zljllist.get(i).getDmtzl().getZlbh());
			zljl.setImg(zljllist.get(i).getImg());
			rows.add(zljl);
		}
		return SUCCESS;
		
	}
	
	@Action(value = "/delzljl", results = {@Result(name="success",type="json")})
	public String delzljl() {
		zljl=this.baseservice.find(Zljl.class,zljl_no);//通过lxm删除一条数据，lxm要进行反序列化。
		this.baseservice.delete(zljl);// 删除数据
		return SUCCESS;
	}
}
