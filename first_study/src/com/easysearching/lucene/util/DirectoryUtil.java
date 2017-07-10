package com.easysearching.lucene.util;

import java.io.IOException;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class DirectoryUtil {

	public static Directory getFSDirectory(String indexPath) {
		Directory directory = null;
		try {
			directory = FSDirectory.getDirectory(indexPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return directory;
	}

	public static Directory getRAMDirectory(String indexPath) {
		Directory directory = null;
		try {
			directory = new RAMDirectory(getFSDirectory(indexPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return directory;

	}
}
