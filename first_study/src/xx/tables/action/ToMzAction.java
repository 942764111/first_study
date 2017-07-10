
package xx.tables.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ToMzAction <code>{类名称}</code>
 * @author  {ylj}
 * @version {版本,常用时间代替}
 * @{跳转到民族管理} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class ToMzAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	//总记录条数  
    private int total=0;
    
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
	 * 模块分类（民族管理）
	 */	
	@Action(value="mz",results={@Result(name="success",location="/tables/datagrid_Mz.jsp",type="redirect")})
	public String mz(){
		total=this.baseService.getTotal("Mz"); 
		return SUCCESS;
	}
	

}
