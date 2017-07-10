package com.easysearching.lucene.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HtmlReader extends FileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public HtmlReader() {

	}

	public HtmlReader(File file) {
		this.file = file;
	}

	@Override
	public String getContent() {
		String regexHtmlTag = "<([^>]*)>";
		FileInputStream fis = null;
		byte[] byteArray = null;
		String content = null;
		try {
			byteArray = new byte[(int) file.length()];
			fis = new FileInputStream(file);
			fis.read(byteArray);
			content = new String(byteArray);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content.replaceAll(regexHtmlTag, "");
	}

}
