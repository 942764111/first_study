package com.easysearching.lucene.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TextReader extends FileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public TextReader(File file) {
		this.file = file;
	}

	public TextReader() {

	}

	public String getContent() {
		FileInputStream fis = null;
		byte[] byteArray = null;
		String content = null;
		try {
			byteArray = new byte[(int) file.length()];
			fis = new FileInputStream(file);
			fis.read(byteArray);
			String encoding = this.getFileEncoding(file);
			content = new String(byteArray, encoding);
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

	private String getFileEncoding(File file) {
		InputStream inputStream = null;
		String encoding = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] head = new byte[3];
			inputStream.read(head);
			if (head[0] == -1 && head[1] == -2) {
				encoding = "UTF-16";
			} else if (head[0] == -2 && head[1] == -1) {
				encoding = "Unicode";
			} else if (head[0] == -17 && head[1] == -69 && head[2] == -65) {
				encoding = "UTF-8";
			} else {
				encoding = "GBK";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return encoding;

	}

}
