package com.easysearching.lucene.beans;
import java.util.Map;

public class QueryField {

	private Map<String,Float> queryFields = null;

	public Map<String, Float> getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(Map<String, Float> queryFields) {
		this.queryFields = queryFields;
	}
	
	

}
