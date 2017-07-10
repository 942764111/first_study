/*
 *@(#)xx.spdh.action
 *@SpdhAction.java.java  
 *@创建时间:2011-10-26上午09:43:34
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

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

import xx.collection.bean.Dmtzl;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Wjlx;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zsd;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @SpdhAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class SpdhAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Dmtzl> dmtzllist;
	private List<Wjlx> wjlxlist;
	private List<Userinfo> userinfolist;
	private List<Zsd> listzsd;
	
	private List<Yhzdymc> listy = new ArrayList<Yhzdymc>();	
	private String userId;
	
	@JSON(serialize=false)
	public List<Yhzdymc> getListy() {
		return listy;
	}

	public void setListy(List<Yhzdymc> listy) {
		this.listy = listy;
	}

	@JSON(serialize=false)
	public List<Dmtzl> getDmtzllist() {
		return dmtzllist;
	}
	public void setDmtzllist(List<Dmtzl> dmtzllist) {
		this.dmtzllist = dmtzllist;
	}

	@JSON(serialize=false)
	public List<Wjlx> getWjlxlist() {
		return wjlxlist;
	}
	public void setWjlxlist(List<Wjlx> wjlxlist) {
		this.wjlxlist = wjlxlist;
	}

	@JSON(serialize=false)
	public List<Userinfo> getUserinfolist() {
		return userinfolist;
	}
	public void setUserinfolist(List<Userinfo> userinfolist) {
		this.userinfolist = userinfolist;
	}
	
	@JSON(serialize=false)
	public List<Zsd> getListzsd() {
		return listzsd;
	}
	public void setListzsd(List<Zsd> listzsd) {
		this.listzsd = listzsd;
	}
	/**
	 * @{scwj2}
	 * @return {/spdh/datagrid_scwj.jsp} {显示素材信息}
	 * @{教学  素材文件管理}
	 */	
	@Action(value="/scwj1",results={@Result(name="success",location="/spdh/scwj.jsp")})
	public String scwj1(){
		this.dmtzllist=this.baseservice.find(Dmtzl.class);
		this.wjlxlist=this.baseservice.find(Wjlx.class);//通过Dmtzl.class查询所有数据并放在listdmtzl中。
		this.userinfolist=this.baseservice.find(Userinfo.class);
		return SUCCESS;
	}
	
	/**
	 * @{dmtzl}
	 * @return {/spdh/datagrid_dmtzl.jsp} {显示多媒体资料信息}
	 * @{教学  多媒体资料}
	 * 在main.jsp中点击多媒体资料时跳转到datagrid_wjlx.jsp
	 */	
	@Action(value="/dmtzl1",results={@Result(name="success",location="/spdh/dmtzl.jsp")})
	public String dmtzl1() {
		this.wjlxlist=this.baseservice.find(Wjlx.class);//通过Dmtzl.class查询所有数据并放在listdmtzl中。
		this.userinfolist=this.baseservice.find(Userinfo.class);
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		listy = this.baseservice.findHql(Yhzdymc.class,"from Yhzdymc y where y.userinfo.userId='"+userId+"'");
		return SUCCESS;
	}
	
	/**
	 * @{wjlx}
	 * @return {/spdh/datagrid_wjlx.jsp} {显示文件类型信息}
	 * @{教学  文件类型}
	 * 在main.jsp中点击文件类型时跳转到datagrid_wjlx.jsp
	 */	
	@Action(value="/wjlx1",results={@Result(name="success",location="/spdh/wjlx.jsp")})
	public String wjlx1() {
		//this.wjlxlist=this.baseservice.find(Wjlx.class);//通过Wjlx.class查到所有文件类型信息
		return SUCCESS;
	}
	
	/**
	 * @{zxsplb1}
	 * @return {/spdh/zxsplb.jsp} {显示素材信息}
	 * @{教学  素材文件管理}
	 */	
	@Action(value="/zxsplb1",results={@Result(name="success",location="/spdh/zxsplb.jsp")})
	public String zxsplb1(){
		return SUCCESS;
	}
	
	/**
	 * @zlzsddy{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return spdh/zlzsddy.jsp{返回参数名} {返回参数说明}
	 * @返回jsp{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/zlzsddy1",results={@Result(name="success",location="/spdh/zlzsddy.jsp")})
	public String zlzsddy11(){
		this.dmtzllist=this.baseservice.find(Dmtzl.class);
		this.listzsd = this.baseservice.find(Zsd.class);
		return SUCCESS;
	}
	
	@Action(value="/yhmlgl",results={@Result(name="success",location="/spdh/contentManager.jsp")})
	public String yhmlgl(){
		return SUCCESS;
	}
	
	/**
	 * 此action用来处理退出登录，当关闭播放视频页面时会触动该action
	 * 当用户再次登录观看该视频时，能够显示上次打开的时间
	*/
	@Action(value="/jxksp",results={@Result(name="success",location="/spdh/zljl.jsp")})
	public String jxksp(){
		return SUCCESS;
	}
	
}
