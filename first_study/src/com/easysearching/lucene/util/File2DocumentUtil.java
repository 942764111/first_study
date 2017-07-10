package com.easysearching.lucene.util;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumberTools;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import com.easysearching.lucene.exception.FileTypeNotSupportException;
import com.easysearching.lucene.reader.FileReader;
import com.easysearching.lucene.reader.FileReaderFactory;

public class File2DocumentUtil {

	public static Document file2Document(String filePath) {
		File file = new File(filePath);
		FileReader fileReader = null;
		Document document = null;
		try {
			fileReader = FileReaderFactory.getInstance(file);
			document = new Document();
			document.add(new Field("name", file.getName(), Store.YES, Index.ANALYZED));
			document.add(new Field("content", fileReader.getContent(), Store.YES, Index.ANALYZED));
			document.add(new Field("size", NumberTools.longToString(file.length()), Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("path", file.getPath(), Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("type", FileUtil.getFileType(file), Store.YES, Index.NOT_ANALYZED));
		} catch (FileTypeNotSupportException e) {
			e.printStackTrace();
		}
		return document;
	}

}
