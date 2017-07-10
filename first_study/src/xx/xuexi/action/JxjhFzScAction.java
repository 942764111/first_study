/*
 *@(#)xx.xuexi.action
 *@JxjhFzAction.java.java  
 *@创建时间:2012-2-15上午10:13:51
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.action;

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

import xx.adminservice.JxjhService;


/**
 * @JxjhFzAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class JxjhFzScAction {
	
	@Resource(name="jxjhservice")
	private JxjhService jxjhService;
	
	private String xq;
	private int jhid;
	private int kch;
	private String jsbh;
	private String xq2;
	private String tip;
	
	
	
	/**
	 * @return the tip
	 */
	public String getTip() {
		return tip;
	}
	/**
	 * @param tip the tip to set
	 */
	public void setTip(String tip) {
		this.tip = tip;
	}
	/**
	 * @return the xq2
	 */
	@JSON(serialize=false)
	public String getXq2() {
		return xq2;
	}
	/**
	 * @param xq2 the xq2 to set
	 */
	public void setXq2(String xq2) {
		this.xq2 = xq2;
	}
	/**
	 * @return the jsbh
	 */
	@JSON(serialize=false)
	public String getJsbh() {
		return jsbh;
	}
	/**
	 * @param jsbh the jsbh to set
	 */
	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}
	/**
	 * @return the xq
	 */
	@JSON(serialize=false)
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the jhid
	 */
	@JSON(serialize=false)
	public int getJhid() {
		return jhid;
	}
	/**
	 * @param jhid the jhid to set
	 */
	public void setJhid(int jhid) {
		this.jhid = jhid;
	}
	/**
	 * @return the kch
	 */
	@JSON(serialize=false)
	public int getKch() {
		return kch;
	}
	/**
	 * @param kch the kch to set
	 */
	public void setKch(int kch) {
		this.kch = kch;
	}
	
	/**
	 * @{fzjxjhAction.action}
	 * @param {kch,xq,jhid} {}
	 * 主要功能就是复制教学计划即另一个教师使用该教学计划
	*/
	@Action(value="/fzjxjhAction",results={@Result(name="success",type="json")})
	public String fzjxjh() throws Exception{
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String jsbh2=(String)hs.getAttribute("jsbh");
		tip="ok!";
		if(jsbh.equals(jsbh2)){
			if(xq.equals(xq2)){
				tip="no!";
			}else{
				this.jxjhService.proc_jxglfz(jhid, jsbh2, kch, xq);
			}
		}else{
			this.jxjhService.proc_jxglfz(jhid, jsbh2, kch, xq);
		}
        
		return "success";
	}
	
	/**
	 * @{scjxjhAction.action}
	 * @param {jhid} {}
	 * 主要功能就是删除教学计划
	*/
	@Action(value="/scjxjhAction",results={@Result(name="success",type="json")})
	public String scjxjh() throws Exception{
		boolean t=this.jxjhService.proc_deljxjh(jhid);
        if(t){
        	tip="ok!";
        }else{
        	tip="no!";
        }
		return "success";
	}
	
}
