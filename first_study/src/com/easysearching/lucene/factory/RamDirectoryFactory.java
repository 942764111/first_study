package com.easysearching.lucene.factory;

import java.io.IOException;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;

public class RamDirectoryFactory {

	private static Directory ramDirectory;

	private RamDirectoryFactory() {

	}

	static {
		init();
	}

	private static void init() {
		Directory fsDir = null;
		String indexPath = null;
		try {
			indexPath = ConfigurationCache.getConfiguration(ConfigurationConstant.INDEX_PATH);
			fsDir = FSDirectory.getDirectory(indexPath);
			ramDirectory = new RAMDirectory(fsDir);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Directory getInstance() {
		if (ramDirectory == null) {
			init();
		}
		return ramDirectory;
	}

	public static void reloadRamDirectory() {
		ramDirectory = null;
	}
}
