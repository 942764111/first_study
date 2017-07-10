package com.easysearching.lucene.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;
import com.easysearching.lucene.beans.FileBean;
import com.easysearching.lucene.beans.QueryField;
import com.easysearching.lucene.beans.QueryResult;
import com.easysearching.lucene.dao.IndexDao;
import com.easysearching.lucene.factory.IndexAnalyzerFactory;
import com.easysearching.lucene.util.File2DocumentUtil;
import com.easysearching.lucene.util.FileBean2DocumentUtil;
import com.easysearching.lucene.util.IndexUtil;
import com.easysearching.util.StringUtil;

public class IndexDaoImpl implements IndexDao {

	IndexAnalyzerFactory indexAnalyzerFactory;
	
	QuerySplit qSplit;

	public IndexAnalyzerFactory getIndexAnalyzerFactory() {
		return this.indexAnalyzerFactory;
	}

	public void setIndexAnalyzerFactory(IndexAnalyzerFactory indexAnalyzerFactory) {
		this.indexAnalyzerFactory = indexAnalyzerFactory;

	}

	private static final int NAME_DISPLAY_LENGTH = 100;

	private static final int CONTENT_DISPLAY_LENGTH = 300;

	public void delete(Term term) {
		IndexWriter indexWriter = null;
		try {
			indexWriter = indexAnalyzerFactory.createIndexWriter();
			indexWriter.deleteDocuments(term);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IndexUtil.closeIndexWriter(indexWriter);
		}
	}

	public void delete(Query query) {
		IndexWriter indexWriter = null;
		try {
			indexWriter = indexAnalyzerFactory.createIndexWriter();
			indexWriter.deleteDocuments(query);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IndexUtil.closeIndexWriter(indexWriter);
		}
	}

	public void save(Document doc) {
		IndexWriter indexWriter = null;
		try {
			indexWriter = indexAnalyzerFactory.createIndexWriter();
			indexWriter.addDocument(doc);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IndexUtil.closeIndexWriter(indexWriter);
		}
	}

	public void update(Term term, Document doc) {
		IndexWriter indexWriter = null;
		try {
			indexWriter = indexAnalyzerFactory.createIndexWriter();
			indexWriter.updateDocument(term, doc);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IndexUtil.closeIndexWriter(indexWriter);
		}
	}

	public QueryResult search(String queryString, QueryField queryField, int firstResult, int maxResults) {
		Query query = null;
		try {
			Set<String> setFields = queryField.getQueryFields().keySet();
			String[] fields = setFields.toArray(new String[setFields.size()]);
			for (String field : fields) {
				System.out.println("field:" + field);
			}
			Map<String, Float> boosts = queryField.getQueryFields();
			QueryParser queryParser = new MultiFieldQueryParser(fields, indexAnalyzerFactory.createAnalyzer(), boosts);
			query = queryParser.parse(queryString);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return search(query, firstResult, maxResults);

	}

	public QueryResult search(String queryString, int firstResult, int maxResults) {
		Query query = null;
		try {
			String[] fields = { "name", "content" };
			Map<String, Float> boosts = new HashMap<String, Float>();
			boosts.put("name", 3.0f);
			boosts.put("content", 1.0f); // 默认为1.0f
			ChineseAnalyzer analyzer=new ChineseAnalyzer();
			QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer, boosts);
			
			String queryString2=qSplit.getQuerySplit(queryString);
			query = queryParser.parse(queryString2);
            System.out.println("测试query:"+query);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return search(query, firstResult, maxResults);

	}

	public void save(String filePath) {
		Document document = File2DocumentUtil.file2Document(filePath);
		this.save(document);
	}

	public void save(FileBean fileBean) {
		Document document = FileBean2DocumentUtil.fileBean2Document(fileBean);
		this.save(document);
	}

	public QueryResult search(Query query, int firstResult, int maxResults) {
		IndexSearcher indexSearcher = null;

		try {
			// 2，进行查询
			indexSearcher = new IndexSearcher(ConfigurationCache.getConfiguration(ConfigurationConstant.INDEX_PATH));
			// Filter filter = new RangeFilter("size", NumberTools.MIN_STRING_VALUE, NumberTools.MAX_STRING_VALUE, true, true);

			// ========== 排序
			Sort sort = new Sort();
			// sort.setSort(new SortField("name",SortField.STRING)); // 默认为升序
			sort.setSort(new SortField("size"));
			// ==========

			TopDocs topDocs = indexSearcher.search(query, null, 10000, sort);

			int recordCount = topDocs.totalHits;
			List<Document> recordList = new ArrayList<Document>();

			// ============== 准备高亮器
			Formatter formatter = new SimpleHTMLFormatter("^..", "$..");
			Scorer scorer = new QueryScorer(query);
			Highlighter highlighterName = new Highlighter(formatter, scorer);
			Highlighter highlighterContent = new Highlighter(formatter, scorer);
			Fragmenter fragmenterName = new SimpleFragmenter(NAME_DISPLAY_LENGTH);
			Fragmenter fragmenterContent = new SimpleFragmenter(CONTENT_DISPLAY_LENGTH);
			highlighterName.setTextFragmenter(fragmenterName);
			highlighterContent.setTextFragmenter(fragmenterContent);
			// ==============

			// 3，取出当前页的数据
			int end = Math.min(firstResult + maxResults, topDocs.totalHits);
			for (int i = firstResult; i < end; i++) {
				ScoreDoc scoreDoc = topDocs.scoreDocs[i];

				int docSn = scoreDoc.doc; // 文档内部编号
				Document doc = indexSearcher.doc(docSn); // 根据编号取出相应的文档

				// =========== 高亮
				// 返回高亮后的结果，如果当前属性值中没有出现关键字，会返回 null
				String hcName = highlighterName.getBestFragment(indexAnalyzerFactory.createAnalyzer(), "name", doc.get("name"));
				String hcContent = highlighterContent.getBestFragment(indexAnalyzerFactory.createAnalyzer(), "content", doc.get("content"));
				if (hcName == null) {
					String name = doc.get("name");
					hcName = name.length() > NAME_DISPLAY_LENGTH ? (name.substring(0, NAME_DISPLAY_LENGTH) + "...") : name;
				} else {
					hcName = hcName.length() > NAME_DISPLAY_LENGTH ? (hcName.substring(0, NAME_DISPLAY_LENGTH) + "...") : hcName;
				}

				doc.getField("name").setValue(StringUtil.handlerHighlighterForHTML(hcName));

				if (hcContent == null) {
					String content = doc.get("content");
					hcContent = content.length() > CONTENT_DISPLAY_LENGTH ? (content.substring(0, CONTENT_DISPLAY_LENGTH) + "...") : content;
				} else {
					hcContent = hcContent.length() > CONTENT_DISPLAY_LENGTH ? (hcContent.substring(0, CONTENT_DISPLAY_LENGTH) + "...") : hcContent;
				}

				doc.getField("content").setValue(StringUtil.handlerHighlighterForHTML(hcContent));
				// ===========

				recordList.add(doc);
			}

			// 返回结果
			return new QueryResult(recordCount, recordList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				indexSearcher.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.easysearching.lucene.dao.IndexDao#search1(java.lang.String[], java.lang.String[], org.apache.lucene.search.BooleanClause.Occur[], int, int)
	 */
	@Override
	public QueryResult search1(String[] keys, String[] fileds, Occur[] flags,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		  ChineseAnalyzer analyzer = new ChineseAnalyzer();  
	      //用MultiFieldQueryParser得到query对象  
		  Query query =null;
	      try {
			 query = MultiFieldQueryParser.parse(keys, fileds, flags, analyzer);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return  search(query, firstResult, maxResults);
	}

}
