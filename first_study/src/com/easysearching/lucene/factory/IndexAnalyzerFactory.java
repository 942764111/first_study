package com.easysearching.lucene.factory;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;

public abstract class IndexAnalyzerFactory {

	public abstract Analyzer createAnalyzer();

	public abstract IndexWriter createIndexWriter();

	public static IndexAnalyzerFactory getInstance(Class<?> clazz) {
		IndexAnalyzerFactory indexAnalyzerFactory = null;
		try {
			Object object = clazz.newInstance();
			if(object instanceof IndexAnalyzerFactory)
			{
				indexAnalyzerFactory = (IndexAnalyzerFactory)object;
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return indexAnalyzerFactory;
	}
	


}
