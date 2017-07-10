/*
 *@(#)xx.servlet.web
 *@DisableUrlSessionFilter.java.java  
 *@创建时间:2012-3-7下午08:51:24
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.servlet.web;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
/**
 * @DisableUrlSessionFilter <code>{类名称}</code>
 * @author  {作者gq}
 * @version {版本,常用时间代替}
 * @{功能描述:通过加入 Filter 的方式过滤掉 URL 中包含的 jsessionid，再重新包装 Response 返回给浏览器} 
 */

public class DisableUrlSessionFilter implements Filter {

	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		// skip non-http requests 
	 	if (!(request instanceof HttpServletRequest)) {
		 		 chain.doFilter(request, response); 
		 		 return; 
	 		 }
	 		 
	 HttpServletRequest httpRequest = (HttpServletRequest) request;
	 HttpServletResponse httpResponse = (HttpServletResponse) response; 
	    // clear session if session id in URL 
	    if (httpRequest.isRequestedSessionIdFromURL()) { 
 		   	HttpSession session = httpRequest.getSession(); 
 		   	if (session != null) session.invalidate(); 
	   	} 
	   	// wrap response to remove URL encoding 
	   	HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(httpResponse) { 
	 		   		@Override 
	 		   		public String encodeRedirectUrl(String url) { 
	 		   			return url; 
	 		   		} 
	 		   		@Override 
	 		   		public String encodeRedirectURL(String url) { 
	 		   			return url; 
	 		   		}
	 		   		@Override 
	 		   		public String encodeUrl(String url) { 
	 		   			return url; 
	 		   		}
	 		   		@Override 
	 		   		public String encodeURL(String url) {
	 		   			return url; 
	 		   		} 
	   	  };
	 	 	 // process next request in chain 
	 	 	 chain.doFilter(request, wrappedResponse); 
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
