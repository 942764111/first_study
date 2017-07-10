package com.easysearching.lucene.reader;

import java.io.File;
import java.io.IOException;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

public class DOCXReader extends FileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public DOCXReader() {

	}

	public DOCXReader(File file) {
		this.file = file;
	}

	public String getContent() {
		String content = null;
		XWPFWordExtractor wordExtractor = null;
		try {
			wordExtractor = new XWPFWordExtractor(POIXMLDocument.openPackage(file.getAbsolutePath()));
			content = wordExtractor.getText();
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (OpenXML4JException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
