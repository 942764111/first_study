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
         * ����û��Ƿ���Ȩ�޷�����Щҳ��
         */
		ActionContext ctx = invocation.getInvocationContext();
		String actname = ctx.getName();
		logger.info("Ȩ�������� ��" + actname);

		Map session = ctx.getSession();
		Integer user = (Integer) session.get("role");
		if (user != null) {
			if (user < 210){             //roleidС��210ʱ���ſ��Ե���action
				
					List<String> sl = (List<String>) session.get("useraction");
					// ����useraction��list���ڴ�action˵���д�Ȩ�ޣ�����ִ�С�
					for (String temp : sl) {
						if (temp.equals(actname)) {
							return invocation.invoke();
						}
					}
					logger.info("��û�д�Ȩ��");
					return Action.NONE; 
				  
				}
		}
		return Action.LOGIN;

	}

}
