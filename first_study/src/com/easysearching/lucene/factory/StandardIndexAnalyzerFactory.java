package com.easysearching.lucene.factory;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;

public class StandardIndexAnalyzerFactory extends IndexAnalyzerFactory {

	@Override
	public Analyzer createAnalyzer() {
		return AnalyzerFactory.createMMAnalyzer();
	}

	@Override
	public IndexWriter createIndexWriter() {
		return IndexWriterFactory.createStandardIndexWriter(this);
	}

}
