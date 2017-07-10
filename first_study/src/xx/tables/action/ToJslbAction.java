
package xx.tables.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ToJslbAction <code>{类名称}</code>
 * @author  {ylj}
 * @version {版本,常用时间代替}
 * @{跳转到教师类别管理} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class ToJslbAction extends ActionSupport {
	
	/**
	 * 模块分类（教师类别）
	 */
	@Action(value="jslb",results={@Result(name="success",location="/tables/datagrid_Jslb.jsp",type="redirect")})
	public String jslb(){
		return SUCCESS;
	}
	

}
