package xx.quanxian.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import org.apache.log4j.Logger;

import xx.quanxian.action.OnlineSessionList;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 *  检查用户是否登录
	 */
	private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext act = invocation.getInvocationContext();
	    logger.info("登录拦截器："+act.getName());
		Map session = act.getSession();
		try{
			String userid = (String)session.get("uid");
			Integer roleid = (Integer)session.get("role");
			if(userid != null && roleid != null){
				if(roleid<210){//设置roleid小于210的才可以进入该系统
					/*登录时运作原理
					 * login.jsp页面中有两个action分别是login.action(不在被拦截的包里)和adminfunctions.action（在被拦截的包里）；
					 * 点击提交按钮时，先异步调用了login.action把uid和role设为了session中的属性；
					 * json返回SUCCESS这时准备调用adminfunctions.action，此类拦截住adminfunctions.action对它的调用，判断session中是否有值
					 * 有值的话，才真正的调用adminfunctions.action
					 * */
					return invocation.invoke();
				}else{
					if(OnlineSessionList.hm.containsKey(userid)){
						OnlineSessionList.hm.remove(userid);
					}
					session.remove("uid");
					session.remove("pass");
					session.remove("role");
					session.remove("userid");
					
					return Action.ERROR;
				}
				
			}else {
				
				return Action.LOGIN;
			}
		}catch(IllegalStateException e){
			e.printStackTrace(new PrintWriter("session 已无效！"));
			return Action.LOGIN;
		}
		
		
	}

}
