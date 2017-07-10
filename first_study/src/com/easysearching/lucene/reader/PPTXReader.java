package com.easysearching.lucene.reader;

import java.io.File;
import java.io.IOException;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.xmlbeans.XmlException;

public class PPTXReader extends FileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public PPTXReader(File file) {
		this.file = file;
	}

	public PPTXReader() {

	}

	@Override
	public String getContent() {
		String content = null;
		XSLFPowerPointExtractor powerpointExtractor = null;
		try {
			powerpointExtractor = new XSLFPowerPointExtractor(POIXMLDocument.openPackage(file.getAbsolutePath()));
			content = powerpointExtractor.getText();
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
