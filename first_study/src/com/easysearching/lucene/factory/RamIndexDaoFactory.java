package com.easysearching.lucene.factory;

import com.easysearching.lucene.dao.IndexDao;
import com.easysearching.lucene.dao.impl.IndexDaoImpl;

public class RamIndexDaoFactory {

	private static IndexDao indexDao;

	private RamIndexDaoFactory() {

	}

	public static void init() {
		if (null == indexDao) {
			indexDao = new IndexDaoImpl();
			indexDao.setIndexAnalyzerFactory(IndexAnalyzerFactory.getInstance(RamIndexAnalyzerFactory.class));
		}
	}

	public static IndexDao getInstance() {
		if (null == indexDao) {
			init();
		}
		return indexDao;
	}

	public static void destory() {
		indexDao = null;
	}
}
