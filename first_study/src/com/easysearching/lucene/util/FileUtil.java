package com.easysearching.lucene.util;

import java.io.File;

public class FileUtil {

	public static String getFileType(File file) {
		String fileName = file.getName();
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		return fileType;
	}
	

}
