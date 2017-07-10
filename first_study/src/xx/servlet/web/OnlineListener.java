package xx.servlet.web;


import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import xx.quanxian.action.OnlineSessionList;



public class OnlineListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	/**
	 *  ֻ�Ƕ�session��������û�н�����־��¼
	 *  ����ӡ�
	 */
	
	
	// ����Ҫ�������ݿ⣬���Զ��������������������Ӧ�ý���springע��
//	private LoginLog log = new LoginLog();
//
//	public LoginLog getLog() {
//		return log;
//	}
//
//	public void setLog(LoginLog log) {
//		this.log = log;
//	}

	private static Logger logger = Logger.getLogger(OnlineListener.class);
	public void sessionCreated(HttpSessionEvent hse) {
		logger.info("����session"+"  sessionid:"+hse.getSession().getId());

	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		String uid=(String) hse.getSession().getAttribute("uid");
		//�������session���б���LoginAction.java�е�quitLogin.actionԭ��һ��
		//�����Զ�����������ֶ����
		if(OnlineSessionList.hm.containsKey(uid)){
			OnlineSessionList.hm.remove(uid);
		}
		logger.info("����session");
//		WebApplicationContext webApplicationContext = WebApplicationContextUtils
//				.getWebApplicationContext(hse.getSession().getServletContext());
//		ILoginLogDAO logdao = (ILoginLogDAO) webApplicationContext
//				.getBean("logDao");
//		if (hse.getSession().getAttribute("uid") != null) {
//			String temp = (String) hse.getSession().getAttribute("uid");
//			log.setUerid(temp);
//			logdao.delete(log);
//
//			System.out.println("������ɾ������");
//		} else
//			System.out.println("������session��û�н���ɾ������");

	}

	public void attributeAdded(HttpSessionBindingEvent hse) {
		logger.info("�����������ص���,�˴ε�nameΪ" + hse.getName() + "�˴ε�valueΪ"
				+ hse.getValue());
//
//		if (hse.getName().equals("uid")) {
//			System.out.println("��¼��¼��־");
//			WebApplicationContext webApplicationContext = WebApplicationContextUtils
//					.getWebApplicationContext(hse.getSession()
//							.getServletContext());
//			ILoginLogDAO logdao = (ILoginLogDAO) webApplicationContext
//					.getBean("logDao");
//
//			// ���userid
//
//			log.setUerid((String) hse.getValue());
//
//			// LoginTime��ֵ
//			Timestamp time = new Timestamp(new Date(System.currentTimeMillis())
//					.getTime());
//			System.out.println(time);
//			log.setLoginTime(time);
//
//			log.setSessionId(hse.getSession().getId());
//
//			logdao.save(log);
//
//		}

	}

	public void attributeRemoved(HttpSessionBindingEvent hse) {
		/**
		 * û��ɾ�����˵�¼��־ 
		 */
		logger.info("�Ƴ����ԣ�"+hse.getName()+","+hse.getValue());
		
		// if(hse.getName().equals("uid")){
		// WebApplicationContext webApplicationContext =
		// WebApplicationContextUtils.getWebApplicationContext(hse.getSession().getServletContext());
		// ILoginLogDAO logdao =
		// (ILoginLogDAO)webApplicationContext.getBean("logDao");
		// log.setUerid((String)hse.getValue());
		// logdao.delete(log);
		//			
		// }

	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		logger.info("�������ã�"+arg0.getName()+","+arg0.getValue());
	}
}
