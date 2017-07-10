
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
 * ToXuyanAction <code>{������}</code>
 * @author  {ylj}
 * @version {�汾,����ʱ�����}
 * @{��ת��ѧԺ����} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class ToXuyanAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseService;
	//�ܼ�¼����  
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
	 * ģ����ࣨѧԺ����
	 */	
	@Action(value="xuyan",results={@Result(name="success",location="/tables/datagrid_Xuyan.jsp",type="redirect")})
	public String xuyan(){
		total=this.baseService.getTotal("Xuyan"); 
		return SUCCESS;
	}
	

}
