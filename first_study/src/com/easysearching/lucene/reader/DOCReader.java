package com.easysearching.lucene.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DOCReader extends FileReader {
	private File file;

	public DOCReader() {

	}

	public DOCReader(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String getContent() {
		FileInputStream fis = null;
		String content = null;
		try {
			fis = new FileInputStream(file);
			WordExtractor wordExtractor = new WordExtractor(fis);
			content = wordExtractor.getText();
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
		return content;
	}

}
