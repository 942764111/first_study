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
	 *  ����û��Ƿ��¼
	 */
	private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext act = invocation.getInvocationContext();
	    logger.info("��¼��������"+act.getName());
		Map session = act.getSession();
		try{
			String userid = (String)session.get("uid");
			Integer roleid = (Integer)session.get("role");
			if(userid != null && roleid != null){
				if(roleid<210){//����roleidС��210�Ĳſ��Խ����ϵͳ
					/*��¼ʱ����ԭ��
					 * login.jspҳ����������action�ֱ���login.action(���ڱ����صİ���)��adminfunctions.action���ڱ����صİ����
					 * ����ύ��ťʱ�����첽������login.action��uid��role��Ϊ��session�е����ԣ�
					 * json����SUCCESS��ʱ׼������adminfunctions.action����������סadminfunctions.action�����ĵ��ã��ж�session���Ƿ���ֵ
					 * ��ֵ�Ļ����������ĵ���adminfunctions.action
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
			e.printStackTrace(new PrintWriter("session ����Ч��"));
			return Action.LOGIN;
		}
		
		
	}

}
