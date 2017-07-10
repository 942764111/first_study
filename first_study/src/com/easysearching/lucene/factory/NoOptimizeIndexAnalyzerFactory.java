package com.easysearching.lucene.factory;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;

public class NoOptimizeIndexAnalyzerFactory extends IndexAnalyzerFactory{

	@Override
	public Analyzer createAnalyzer() {
		return AnalyzerFactory.createStandardAnalyzer();
	}

	@Override
	public IndexWriter createIndexWriter() {
		return IndexWriterFactory.createNoOptimizeIndexWriter(this);
	}

}
