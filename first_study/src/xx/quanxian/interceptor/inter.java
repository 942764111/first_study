/*
 *@(#)xx.quanxian.interceptor
 *@inter.java.java  
 *@����ʱ��:2011-9-26����12:29:29
 *@���ߣ�tlq
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @inter <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
		System.out.println("û�д�Ȩ�ޣ�");
		return Action.LOGIN;
	}

}
