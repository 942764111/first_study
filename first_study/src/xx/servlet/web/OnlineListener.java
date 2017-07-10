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
	 *  只是对session监听，并没有进行日志记录
	 *  需添加。
	 */
	
	
	// 由于要操作数据库，所以定义两个变量，按框架走应该进行spring注入
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
		logger.info("创建session"+"  sessionid:"+hse.getSession().getId());

	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		String uid=(String) hse.getSession().getAttribute("uid");
		//清除在线session的列表与LoginAction.java中的quitLogin.action原理一样
		//这是自动清除，那是手动清除
		if(OnlineSessionList.hm.containsKey(uid)){
			OnlineSessionList.hm.remove(uid);
		}
		logger.info("销毁session");
//		WebApplicationContext webApplicationContext = WebApplicationContextUtils
//				.getWebApplicationContext(hse.getSession().getServletContext());
//		ILoginLogDAO logdao = (ILoginLogDAO) webApplicationContext
//				.getBean("logDao");
//		if (hse.getSession().getAttribute("uid") != null) {
//			String temp = (String) hse.getSession().getAttribute("uid");
//			log.setUerid(temp);
//			logdao.delete(log);
//
//			System.out.println("进行了删除操作");
//		} else
//			System.out.println("销毁了session但没有进行删除操作");

	}

	public void attributeAdded(HttpSessionBindingEvent hse) {
		logger.info("增加属性拦截到了,此次的name为" + hse.getName() + "此次的value为"
				+ hse.getValue());
//
//		if (hse.getName().equals("uid")) {
//			System.out.println("记录登录日志");
//			WebApplicationContext webApplicationContext = WebApplicationContextUtils
//					.getWebApplicationContext(hse.getSession()
//							.getServletContext());
//			ILoginLogDAO logdao = (ILoginLogDAO) webApplicationContext
//					.getBean("logDao");
//
//			// 获得userid
//
//			log.setUerid((String) hse.getValue());
//
//			// LoginTime赋值
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
		 * 没有删除个人登录日志 
		 */
		logger.info("移除属性："+hse.getName()+","+hse.getValue());
		
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
		logger.info("属性重置："+arg0.getName()+","+arg0.getValue());
	}
}
