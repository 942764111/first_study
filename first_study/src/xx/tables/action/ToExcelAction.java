
package xx.tables.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ToExcelAction <code>{������}</code>
 * @author  {wab}
 * @version {�汾,����ʱ�����}
 * @{��ת��֪ʶ��} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class ToExcelAction extends ActionSupport {
	
	@Action(value="/getCztd",results={@Result(name="success",location="/tables/datagrid_Czt.jsp",type="redirect")})
	public String getallCztd(){
		return SUCCESS;
	}

}
