package com.easysearching.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;
import com.easysearching.lucene.factory.RamIndexDaoFactory;
import com.easysearching.lucene.util.IndexUtil;

public class ContextLoaderListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		String realPath = event.getServletContext().getRealPath("/");
		System.out.println("初始化配置缓存");
		ConfigurationCache.initConfiguration(realPath);

		if ("debug".equals(ConfigurationCache.getConfiguration(ConfigurationConstant.EASYSEARCHING_INDEXSOURCE_GENERATE_MODE))) {
			long begin = System.currentTimeMillis();
			System.out.println("初始化索引数据库");
			IndexUtil.createIndexDatabase();
			long end = System.currentTimeMillis();
			System.out.println("初始化索引数据库共计:" + (end - begin) / 1000 + "秒");
		}
		System.out.println("初始化索引内存数据库");
		RamIndexDaoFactory.init();

	}

	public void contextDestroyed(ServletContextEvent event) {
		//将内存数据库刷入到索引数据库中
		//System.out.println("内存数据库加载到索引数据库");
		//IndexUtil.addDocumentsFromRamToFS();
		
		if ("debug".equals(ConfigurationCache.getConfiguration(ConfigurationConstant.EASYSEARCHING_INDEXSOURCE_GENERATE_MODE))) {
			System.out.println("销毁索引数据库");
			//IndexUtil.destoryIndexDatabase();
		}
		System.out.println("销毁配置缓存");
		ConfigurationCache.clearCache();
		System.out.println("销毁内存数据库");
		RamIndexDaoFactory.destory();
	}

}
