package com.easysearching.lucene.dao;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;

import com.easysearching.lucene.beans.FileBean;
import com.easysearching.lucene.beans.QueryField;
import com.easysearching.lucene.beans.QueryResult;
import com.easysearching.lucene.factory.IndexAnalyzerFactory;

public interface IndexDao {

	public void setIndexAnalyzerFactory(IndexAnalyzerFactory indexAnalyzerFactory);

	public IndexAnalyzerFactory getIndexAnalyzerFactory();

	public void save(Document doc);

	public void save(String filePath);

	public void save(FileBean fileBean);

	public void delete(Term term);

	public void update(Term term, Document doc);

	public void delete(Query query);

	public QueryResult search(String queryString, int firstResult, int maxResults);

	public QueryResult search(String queryString, QueryField queryField, int firstResult, int maxResults);
	public QueryResult search(Query query, int firstResult, int maxResults);
	
	public QueryResult search1(String[] keys, String[] fileds, BooleanClause.Occur[] flags,int firstResult, int maxResults);
	

}
