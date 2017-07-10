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
	  * 用于过滤除jsp以外的所有请求
	  * struts2的拦截器与fck 上传文件冲突   
	  * 重struts2的拦截器，增加了判断，如果是fck的编辑器上传文件，不进行拦截。  
	  * 判断路径是否有fckeditor 实现在fckeditor下的操作不进行拦截  
	  */  
	 private static Logger logger = Logger.getLogger(MyStrutsFilterDispatcher.class);
	 public void init(FilterConfig filterConfig) throws ServletException {
		 
		 super.init(filterConfig);
		 logger.warn("struts 过滤器 初始化成功");
	 }
	 @Override  
	 public void doFilter(ServletRequest req, ServletResponse res,   
	   FilterChain chain) throws IOException, ServletException {   
	 
	      String url = ((HttpServletRequest)req).getRequestURI();
	      
	      //HttpSession hs=((HttpServletRequest)req).getSession();
	      //System.out.println("我想看到:"+hs.getId());
	      //List<String> useraction  =(List<String>)hs.getAttribute("useraction");
	      //if(useraction.contains(url)){//判断“将通过过滤器的”
	    	  if(url.indexOf(".jpg")>0||url.indexOf(".png")>0||url.indexOf(".gif")>0||url.indexOf(".js")>0||url.indexOf(".css")>0){
		       }else{
		    	  logger.info("struts 过滤器:"+url);
		      }
		      
		      if (url.indexOf("fckeditor") < 0 && url.indexOf("webs") < 0) {
		    	      
		    		  super.doFilter(req, res, chain);    
		    	   
		       } else {  
		    	   
		             chain.doFilter(req, res);   
		       }
	     // }else{
	    	//  System.out.println("此url地址不存在！");
	     // }
	           
	    }   
	  
	}  

