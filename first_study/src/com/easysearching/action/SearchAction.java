package com.easysearching.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;
import com.easysearching.lucene.beans.QueryField;
import com.easysearching.lucene.beans.QueryResult;
import com.easysearching.lucene.dao.IndexDao;
import com.easysearching.lucene.factory.RamIndexDaoFactory;

public class SearchAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the object.
	 */
	public SearchAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String queryString = request.getParameter("queryString");
		
		
		
//		  String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//		  Pattern p = Pattern.compile(regEx);
//		  Matcher m = p.matcher(queryString);
//		  if (m.find()) {
//			  queryString="";
//		  }
//		
//		
		
		
		
		
		
		
		System.out.println("queryString:"+queryString);
		int currentPage = 1;
		int pageSize = 10;
		int totalPages = 0;
		int totalResults = 0;
		int pageCount = 0;
		if (null == request.getParameter("currentPage") || "".equals(request.getParameter("currentPage"))) {
			currentPage = 1;
		}
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		try {
			pageSize = Integer.parseInt(ConfigurationCache.getConfiguration(ConfigurationConstant.PAGING_SPLIT_PAGESIZE));
		} catch (NumberFormatException e) {
			pageSize = 10;
		}

		try {
			//pageCount = Integer.parseInt(request.getParameter("pageCount"));
			pageCount=0;
		} catch (NumberFormatException e) {
			pageCount = 0;
		}

		QueryResult queryResults = null;
		IndexDao indexDao = RamIndexDaoFactory.getInstance();
		QueryField queryField = new QueryField();
		Map<String, Float> queryFields = new HashMap<String, Float>();
		queryFields.put("name", 3f);
		queryFields.put("content", 1f);
		queryField.setQueryFields(queryFields);

		long begin = System.currentTimeMillis();
		if (queryString != null && !"".equals(queryString)) {
			request.setAttribute("queryString", queryString);
			queryResults = indexDao.search(queryString, (currentPage - 1) * pageSize, pageSize);
		}
		
		long end = System.currentTimeMillis();
		double searchingTime = (end - begin) * 1.0 / 1000;

		totalResults = queryResults.getResultCount();
		totalPages = totalResults % pageSize == 0 ? totalResults / pageSize : totalResults / pageSize + 1;

		System.out.println("共查询到" + totalResults + "条结果,用时" + searchingTime + "秒");
	
		//查询结果
		request.setAttribute("queryResults", queryResults);
		//总页数
		request.setAttribute("totalPages", totalPages);
		//总查询时间
		request.setAttribute("searchingTime", searchingTime);
		//页数显示
		request.setAttribute("pageCount", pageCount);
		
        request.setAttribute("currentPage", currentPage-1);
		
		request.getRequestDispatcher("page/search/main.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
	}

}
