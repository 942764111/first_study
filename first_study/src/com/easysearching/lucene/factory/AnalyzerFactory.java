package com.easysearching.lucene.factory;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class AnalyzerFactory {

	public static Analyzer createMMAnalyzer() {
		return new MMAnalyzer();
	}

	public static Analyzer createStandardAnalyzer() {
		return new StandardAnalyzer();
	}

}
