package com.easysearching.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.search.BooleanClause;

import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;
import com.easysearching.lucene.beans.QueryField;
import com.easysearching.lucene.beans.QueryResult;
import com.easysearching.lucene.dao.IndexDao;
import com.easysearching.lucene.factory.RamIndexDaoFactory;

public class SeniorSearchAction2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SeniorSearchAction2() {
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
		
		String zsdmc = request.getParameter("queryString");
		zsdmc=new String(zsdmc.getBytes("ISO-8859-1"),"utf-8");
		String fileType = request.getParameter("searchType");
		fileType=new String(fileType.getBytes("ISO-8859-1"),"utf-8");
		request.setAttribute("searchType", fileType);
		String TCName = request.getParameter("TCName");
		TCName=new String(TCName.getBytes("ISO-8859-1"),"utf-8");
		request.setAttribute("TCName", TCName);
//		String ZName = request.getParameter("zhangId");
//		String JName = request.getParameter("jieId");
		String pubWriter = request.getParameter("userid");
		pubWriter=new String(pubWriter.getBytes("ISO-8859-1"),"utf-8");
		request.setAttribute("userid", pubWriter);
//		System.out.println(" fileType:"+fileType+" TCName:"+TCName+" ZName:"+ZName+" JName:"+JName+" pubWriter:"+pubWriter);
		List<Map<String, String>>list=new ArrayList<Map<String,String>>();
		
		if (zsdmc!=null&&!zsdmc.equals("")) {
			
			Map<String, String>map=new HashMap<String, String>();
			map.put("keyword", zsdmc);
			list.add(map);
		}
		if (fileType!=null&&!fileType.equals("")) {
			
			if (fileType.equals("全部")) {
				
			}else{
				System.out.println("fileType:"+fileType);
				Map<String, String>map=new HashMap<String, String>();
				map.put("fileType", fileType);
				list.add(map);
			}
			
		}
		if (TCName!=null&&!TCName.equals("")) {
			Map<String, String>map=new HashMap<String, String>();
			map.put("TCName", TCName);
			list.add(map);
		}
//		if (ZName!=null&&!ZName.equals("")) {
//
//			ZName=ZName.split(",,,,,")[1];
//			Map<String, String>map=new HashMap<String, String>();
//			map.put("ZName", ZName);
//			list.add(map);
//			
//		}
//		if (JName!=null&&!JName.equals("")) {
//			Map<String, String>map=new HashMap<String, String>();
//			map.put("JName", JName);
//			list.add(map);
//		}
		if (pubWriter!=null&&!pubWriter.equals("")) {
			Map<String, String>map=new HashMap<String, String>();
			map.put("pubWriter", pubWriter);
			list.add(map);
		}
		
		
		System.out.println("list:"+list);
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
			pageCount = Integer.parseInt(request.getParameter("pageCount"));
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
		request.setAttribute("queryString", zsdmc);
		
		int size=0;
		if (zsdmc==null||zsdmc.equals("")) {
			size=list.size();
		} else {
            size=list.size()+1;
		}
		String[] keys=new String[size];
		String[] fileds=new String[size];
		BooleanClause.Occur[] flags=new BooleanClause.Occur[size];
			if (zsdmc==null||zsdmc.equals("")) {
				for (int i = 0; i < size; i++) {
					Map<String, String>map=list.get(i);
					
					Set keySet = map.keySet(); // key的set集合  
			        Iterator it = keySet.iterator();  
			        while(it.hasNext()){  
			        	fileds[i] = (String) it.next(); // key  
			        	keys[i] = map.get(fileds[i]);  //value        
			        }
					
					flags[i]=BooleanClause.Occur.MUST;
				}
			}else {
				keys[0]=zsdmc;
				keys[1]=zsdmc;
				fileds[0]="name";
				fileds[1]="content";
				flags[0]=BooleanClause.Occur.MUST;
				flags[1]=BooleanClause.Occur.MUST;
				
					for (int i = 1; i < list.size(); i++) {
						Map<String, String>map=list.get(i);
						System.out.println("map:"+map);
						
						Set keySet = map.keySet(); // key的set集合  
				        Iterator it = keySet.iterator(); 
				       
				        while(it.hasNext()){  
				        	fileds[i+1] = (String) it.next(); // key  
				        
				        	keys[i+1] = map.get(fileds[i+1]);  //value   
				        	
				        }
				
						flags[i+1]=BooleanClause.Occur.MUST;
					}
				}
				
			System.out.println("keys:"+keys+"     "+fileds+"      "+flags);
		
		queryResults = indexDao.search1(keys, fileds,flags,(currentPage - 1) * pageSize, pageSize);
		
		
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
		request.getRequestDispatcher("page/search/main1.jsp").forward(request, response);
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
