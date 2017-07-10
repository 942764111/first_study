
package xx.tables.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ToJslbAction <code>{������}</code>
 * @author  {ylj}
 * @version {�汾,����ʱ�����}
 * @{��ת����ʦ������} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
@SuppressWarnings("serial")
public class ToJslbAction extends ActionSupport {
	
	/**
	 * ģ����ࣨ��ʦ���
	 */
	@Action(value="jslb",results={@Result(name="success",location="/tables/datagrid_Jslb.jsp",type="redirect")})
	public String jslb(){
		return SUCCESS;
	}
	

}
