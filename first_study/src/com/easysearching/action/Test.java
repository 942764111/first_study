/*
 *@(#)com.easysearching.action
 *@Test.java.java  
 *@创建时间:2016-4-16上午8:52:00
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package com.easysearching.action;

import java.util.HashMap;
import java.util.Map;

import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;
import com.easysearching.lucene.beans.QueryField;
import com.easysearching.lucene.beans.QueryResult;
import com.easysearching.lucene.dao.IndexDao;
import com.easysearching.lucene.factory.RamIndexDaoFactory;

/**
 * @Test <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class Test {

	/**
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String queryString ="你好";
		System.out.println("queryString:"+queryString);
		int currentPage = 1;
		int pageSize = 10;
		int totalPages = 0;
		int totalResults = 0;
		int pageCount = 0;
	

		QueryResult queryResults = null;
		IndexDao indexDao = RamIndexDaoFactory.getInstance();
		QueryField queryField = new QueryField();
		Map<String, Float> queryFields = new HashMap<String, Float>();
		queryFields.put("name", 3f);
//		queryFields.put("content", 1f);
		queryFields.put("fileType", 1f);
		queryField.setQueryFields(queryFields);

		long begin = System.currentTimeMillis();
		if (queryString != null && !"".equals(queryString)) {
		
			//queryResults = indexDao.search(queryString, (currentPage - 1) * pageSize, pageSize);
			queryResults=indexDao.search(queryString, queryField, (currentPage - 1) * pageSize, pageSize);
		}
		
		long end = System.currentTimeMillis();
		double searchingTime = (end - begin) * 1.0 / 1000;

		totalResults = queryResults.getResultCount();
		totalPages = totalResults % pageSize == 0 ? totalResults / pageSize : totalResults / pageSize + 1;

		System.out.println("共查询到" + totalResults + "条结果,用时" + searchingTime + "秒");
		
	}

}
