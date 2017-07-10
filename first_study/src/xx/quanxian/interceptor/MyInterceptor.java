package xx.quanxian.interceptor;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(MyInterceptor.class);
	public void init() {
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
        /**
         * 检查用户是否有权限访问这些页面
         */
		ActionContext ctx = invocation.getInvocationContext();
		String actname = ctx.getName();
		logger.info("权限拦截器 ：" + actname);

		Map session = ctx.getSession();
		Integer user = (Integer) session.get("role");
		if (user != null) {
			if (user < 210){             //roleid小于210时，才可以调用action
				
					List<String> sl = (List<String>) session.get("useraction");
					// 遍历useraction的list存在此action说明有此权限，继续执行。
					for (String temp : sl) {
						if (temp.equals(actname)) {
							return invocation.invoke();
						}
					}
					logger.info("您没有此权限");
					return Action.NONE; 
				  
				}
		}
		return Action.LOGIN;

	}

}
