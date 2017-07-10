package xx.servlet.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.FilterDispatcher;

import com.opensymphony.xwork2.Action;


@SuppressWarnings("deprecation")
public class MyStrutsFilterDispatcher extends FilterDispatcher {   
	 /**  
	  * ���ڹ��˳�jsp�������������
	  * struts2����������fck �ϴ��ļ���ͻ   
	  * ��struts2�����������������жϣ������fck�ı༭���ϴ��ļ������������ء�  
	  * �ж�·���Ƿ���fckeditor ʵ����fckeditor�µĲ�������������  
	  */  
	 private static Logger logger = Logger.getLogger(MyStrutsFilterDispatcher.class);
	 public void init(FilterConfig filterConfig) throws ServletException {
		 
		 super.init(filterConfig);
		 logger.warn("struts ������ ��ʼ���ɹ�");
	 }
	 @Override  
	 public void doFilter(ServletRequest req, ServletResponse res,   
	   FilterChain chain) throws IOException, ServletException {   
	 
	      String url = ((HttpServletRequest)req).getRequestURI();
	      
	      //HttpSession hs=((HttpServletRequest)req).getSession();
	      //System.out.println("���뿴��:"+hs.getId());
	      //List<String> useraction  =(List<String>)hs.getAttribute("useraction");
	      //if(useraction.contains(url)){//�жϡ���ͨ���������ġ�
	    	  if(url.indexOf(".jpg")>0||url.indexOf(".png")>0||url.indexOf(".gif")>0||url.indexOf(".js")>0||url.indexOf(".css")>0){
		       }else{
		    	  logger.info("struts ������:"+url);
		      }
		      
		      if (url.indexOf("fckeditor") < 0 && url.indexOf("webs") < 0) {
		    	      
		    		  super.doFilter(req, res, chain);    
		    	   
		       } else {  
		    	   
		             chain.doFilter(req, res);   
		       }
	     // }else{
	    	//  System.out.println("��url��ַ�����ڣ�");
	     // }
	           
	    }   
	  
	}  

