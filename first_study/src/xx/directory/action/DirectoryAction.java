
package xx.directory.action;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Userinfo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * DirectoryAction <code>{类名称}</code>
 * @author  {tks}
 * @version {版本,常用时间代替}
 * @{跳转到通讯录管理} 
 */

@Controller
@Scope("prototype")
@Namespace("directory")
@ParentPackage("default")
@SuppressWarnings("serial")
public class DirectoryAction extends ActionSupport {
	
	private String userid;
	
	
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}


	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}


	@Action(value="/friend_m",results={@Result(name="success",location="/page/txl/txlm.jsp")})
	public String loginjsp()throws Exception{
		HttpSession hs=ServletActionContext.getRequest().getSession();
		HttpSession session=ServletActionContext.getRequest().getSession();
		Userinfo user = (Userinfo)hs.getAttribute("userifno");
		userid = user.getUserId();
		session.setAttribute("userid", userid);
		return "success";
	}
	@Action(value="/search",results={@Result(name="success",location="/page/search/index.html")})
	public String search()throws Exception{
		
		return "success";
	}
	

}
