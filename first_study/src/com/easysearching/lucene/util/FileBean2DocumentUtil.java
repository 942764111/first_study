package com.easysearching.lucene.util;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumberTools;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import com.easysearching.lucene.beans.FileBean;
import com.easysearching.lucene.exception.FileTypeNotSupportException;
import com.easysearching.lucene.reader.FileReader;
import com.easysearching.lucene.reader.FileReaderFactory;
import com.easysearching.util.TimeUtil;

public class FileBean2DocumentUtil {
	public static Document fileBean2Document(FileBean fileBean) {
		File file = fileBean.getPubFile();
		File file2=fileBean.getDownLoadFile();
		String pubDate = fileBean.getPubDate();
		String pubWriter = fileBean.getPubWriter();
		String viewPath=fileBean.getViewPath();
		String keci=fileBean.getKeci();
		String i_id=fileBean.getI_id();
		String zlid=fileBean.getZlid();
		String zsdmc=fileBean.getZsdmc();
		String fileType=fileBean.getFileType();
	    String TCName=fileBean.getTCName();//课程名称
		String ZName =fileBean.getZName();//章名称
		String JName =fileBean.getJName();//节名称

		if (null == pubDate || "".equals(pubDate)) {
			pubDate = TimeUtil.getCurrentDate();
		}
		if (null == pubWriter || "".equals(pubWriter)) {
			pubWriter = "anonymous";
		}
		if (null == viewPath || "".equals(viewPath)) {
			pubWriter = "error//404.swf";
		}
		FileReader fileReader = null;
		Document document = null;
		try {
			fileReader = FileReaderFactory.getInstance(file);
			document = new Document();
			document.add(new Field("name", file2.getName(), Store.YES, Index.ANALYZED));
			document.add(new Field("content", fileReader.getContent(), Store.YES, Index.ANALYZED));
			document.add(new Field("size", NumberTools.longToString(file.length()), Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("path", file2.getPath(), Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("type", FileUtil.getFileType(file), Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("pubWriter", pubWriter, Store.YES, Index.ANALYZED));
			document.add(new Field("pubDate", pubDate, Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("viewPath", viewPath, Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("keci", keci, Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("i_id", i_id, Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("zlid", zlid, Store.YES, Index.NOT_ANALYZED));
			document.add(new Field("fileType", fileType, Store.YES, Index.ANALYZED));
			document.add(new Field("TCName", TCName, Store.YES, Index.ANALYZED));
			document.add(new Field("ZName", ZName, Store.YES, Index.ANALYZED));
			document.add(new Field("JName", JName, Store.YES, Index.ANALYZED));
			document.add(new Field("JName", JName, Store.YES, Index.ANALYZED));
	
		} catch (FileTypeNotSupportException e) {
			e.printStackTrace();
		}
		return document;
	}

}
