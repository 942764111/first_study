
package xx.tables.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Zsd;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ToMzAction <code>{类名称}</code>
 * @author  {ylj}
 * @version {版本,常用时间代替}
 * @{跳转到知识点} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class ToZsdAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	private List<Zsd>zsdb;
    
	
	/**
	 * @return the zsdb
	 */
	public List<Zsd> getZsdb() {
		return zsdb;
	}


	/**
	 * @param zsdb the zsdb to set
	 */
	public void setZsdb(List<Zsd> zsdb) {
		this.zsdb = zsdb;
	}


	/**
	 * 模块分类（知识点信息）
	 */	
	@Action(value="zsd",results={@Result(name="success",location="/kgt/Zsd.jsp")})
	public String zsd(){
		this.zsdb=this.baseservice.find(Zsd.class);
		return SUCCESS;
	}
	

}
