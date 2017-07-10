package com.easysearching.lucene.beans;

import java.util.List;

import org.apache.lucene.document.Document;

public class QueryResult {

	public QueryResult() {

	}

	public QueryResult(int resultCount, List<Document> resultList) {
		this.resultCount = resultCount;
		this.resultList = resultList;
	}

	private int resultCount;

	private List<Document> resultList;

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<Document> getResultList() {
		return resultList;
	}

	public void setResultList(List<Document> resultList) {
		this.resultList = resultList;
	}

}
