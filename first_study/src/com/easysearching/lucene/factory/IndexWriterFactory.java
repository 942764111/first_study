package com.easysearching.lucene.factory;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;

import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;

public class IndexWriterFactory {

	public static IndexWriter createStandardIndexWriter(Object object) {
		IndexWriter indexWriter = null;
		String indexPath = null;
		Analyzer analyzer = null;
		try {
			if (object instanceof IndexAnalyzerFactory) {
				analyzer = ((IndexAnalyzerFactory) object).createAnalyzer();
				indexPath = ConfigurationCache.getConfiguration(ConfigurationConstant.INDEX_PATH);
				indexWriter = new IndexWriter(indexPath, analyzer, MaxFieldLength.UNLIMITED);
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexWriter;
	}

	public static IndexWriter createNoOptimizeIndexWriter(Object object) {
		IndexWriter indexWriter = null;
		String indexPath = null;
		Analyzer analyzer = null;
		try {
			if (object instanceof IndexAnalyzerFactory) {
				analyzer = ((IndexAnalyzerFactory) object).createAnalyzer();
				indexPath = ConfigurationCache.getConfiguration(ConfigurationConstant.INDEX_PATH);
				indexWriter = new IndexWriter(indexPath, analyzer, true, MaxFieldLength.UNLIMITED);
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexWriter;

	}

	public static IndexWriter createRamIndexWriter(Object object) {
		IndexWriter indexWriter = null;
		Analyzer analyzer = null;
		Directory ramDir = null;
		try {
			if (object instanceof IndexAnalyzerFactory) {
				analyzer = ((IndexAnalyzerFactory) object).createAnalyzer();
				ramDir = RamDirectoryFactory.getInstance();
				indexWriter = new IndexWriter(ramDir, analyzer, MaxFieldLength.UNLIMITED);
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexWriter;
	}

	public static IndexWriter createFileSystemIndexWriter(Object object) {
		IndexWriter indexWriter = null;
		String indexPath = null;
		Analyzer analyzer = null;
		Directory fsDir = null;
		Directory ramDir = null;
		try {
			if (object instanceof IndexAnalyzerFactory) {
				analyzer = ((IndexAnalyzerFactory) object).createAnalyzer();
				indexPath = ConfigurationCache.getConfiguration(ConfigurationConstant.INDEX_PATH);
				fsDir = FSDirectory.getDirectory(indexPath);
				ramDir = new RAMDirectory(fsDir);
				indexWriter = new IndexWriter(fsDir, analyzer, true, MaxFieldLength.UNLIMITED);
				indexWriter.addIndexesNoOptimize(new Directory[] { ramDir });
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexWriter;
	}

}
