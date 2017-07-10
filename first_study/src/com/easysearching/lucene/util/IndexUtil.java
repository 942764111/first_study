package com.easysearching.lucene.util;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;

import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;
import com.easysearching.lucene.beans.FileBean;
import com.easysearching.lucene.dao.IndexDao;
import com.easysearching.lucene.dao.impl.IndexDaoImpl;
import com.easysearching.lucene.factory.AnalyzerFactory;
import com.easysearching.lucene.factory.IndexAnalyzerFactory;
import com.easysearching.lucene.factory.NoOptimizeIndexAnalyzerFactory;
import com.easysearching.lucene.factory.RamDirectoryFactory;
import com.easysearching.lucene.factory.RamIndexAnalyzerFactory;
import com.easysearching.lucene.factory.StandardIndexAnalyzerFactory;

public class IndexUtil {

	public static void createIndexDatabase() {
		IndexDao indexDao = new IndexDaoImpl();
		indexDao.setIndexAnalyzerFactory(IndexAnalyzerFactory.getInstance(StandardIndexAnalyzerFactory.class));
		File fileSource = new File(ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_PATH));
		Document document = null;
		FileBean fileBean = null;
		if (fileSource.isDirectory() && fileSource.listFiles().length > 0) {
			for (File file : fileSource.listFiles()) {
				if (file.isDirectory()) {
					continue;
				}
				fileBean = new FileBean();
				fileBean.setPubFile(file);
				document = FileBean2DocumentUtil.fileBean2Document(fileBean);
				indexDao.save(document);
				System.out.println(file.getName() + ">>>创建到索引库");
			}

		}
	}

	public static void addDocumentsFromRamToFS() {
		IndexAnalyzerFactory indexAnalyzerFactory = IndexAnalyzerFactory.getInstance(NoOptimizeIndexAnalyzerFactory.class);
		IndexWriter indexWriter = indexAnalyzerFactory.createIndexWriter();
		try {
			indexWriter.addIndexesNoOptimize(new Directory[] { RamDirectoryFactory.getInstance() });
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeIndexWriter(indexWriter);
		}
	}

	public static void destoryIndexDatabase() {
		File indexSource = new File(ConfigurationCache.getConfiguration(ConfigurationConstant.INDEX_PATH));
		if (indexSource.isDirectory() && indexSource.listFiles().length > 0) {
			for (File file : indexSource.listFiles()) {
				if (!file.delete()) {
					System.out.println(file.getName() + " 删除失败!");
				} else {
					System.out.println(file.getName() + " 删除成功!");
				}
			}
		}
	}

	public static void closeIndexWriter(IndexWriter indexWriter) {
		if (indexWriter != null) {
			try {
				indexWriter.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
