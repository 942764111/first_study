/*
 *@(#)xx.quanxian.interceptor
 *@inter.java.java  
 *@创建时间:2011-9-26上午12:29:29
 *@作者：tlq
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.interceptor;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @inter <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class inter extends AbstractInterceptor {

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		String actname = ctx.getName();
		HttpSession session = ServletActionContext.getRequest().getSession();
		List<String> useraction = (List<String>) session.getAttribute("useraction");
		for(String a:useraction){
			if(actname.equals(a)){
				return invocation.invoke();
			}
		}
		System.out.println("没有此权限！");
		return Action.LOGIN;
	}

}
