package com.easysearching.lucene.reader;

import java.io.File;

import com.easysearching.lucene.exception.FileTypeNotSupportException;
import com.easysearching.lucene.util.FileUtil;

public class FileReaderFactory {

	public static FileReader getInstance(File file) throws FileTypeNotSupportException{
		String fileType = FileUtil.getFileType(file);
		FileReader fileReader = null;
		if (FileReaderType.DOC.equalsIgnoreCase(fileType)) {
			fileReader = new DOCReader(file);
		} else if (FileReaderType.DOCX.equalsIgnoreCase(fileType)) {
			fileReader = new DOCXReader(file);
		} else if (FileReaderType.XLS.equalsIgnoreCase(fileType)) {
			fileReader = new XLSReader(file);
		} else if (FileReaderType.XLSX.equalsIgnoreCase(fileType)) {
			fileReader = new XLSXReader(file);
		} else if (FileReaderType.PPT.equalsIgnoreCase(fileType)) {
			fileReader = new PPTReader(file);
		} else if (FileReaderType.PPTX.equalsIgnoreCase(fileType)) {
			fileReader = new PPTXReader(file);
		} else if (FileReaderType.PDF.equalsIgnoreCase(fileType)) {
			fileReader = new PdfReader(file);
		} else if (FileReaderType.HTM.equalsIgnoreCase(fileType) || FileReaderType.HTML.equalsIgnoreCase(fileType)
				|| FileReaderType.MHT.equalsIgnoreCase(fileType)) {
			fileReader = new HtmlReader(file);
		} else if(FileReaderType.TEXT.equalsIgnoreCase(fileType)){
			fileReader = new TextReader(file);
		}else{
			throw new FileTypeNotSupportException(fileType);
		}
		return fileReader;
	}

}
