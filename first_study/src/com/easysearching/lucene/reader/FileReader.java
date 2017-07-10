package com.easysearching.lucene.reader;

public abstract class FileReader {

	public abstract String getContent();

	public static void main(String[] args) {
		// FileReader fileReader = FileReaderFactory.getInstance(FileType.MHT);
		// System.out.println(fileReader.getContent(new File("E:\\学习\\java\\ebook\\Freemarker教程_中文版.pdf")));
		// System.out.println(fileReader.getContent(new File("E:\\学习\\java\\docs\\java解决文档\\java concurrent 探秘 - aurawing - 博客园.mht")));
		// System.out.println(fileReader.getContent(new File("E:\\学习\\java\\docs\\第9章附一_SOAP简介.ppt")));
		// System.out.println(fileReader.getContent(new File("E:\\学习\\java\\docs\\Apache集群布置方法.doc")));
		// System.err.println(fileReader.getContent(new File("E:\\学习\\zikao\\自学考试成绩预览.xls")));
		// System.err.println(fileReader.getContent(new File("E:\\学习\\java\\docs\\java解决文档\\最完整的java调用oracle存储过程、函数 - JavaEye - JavaEye技术网站.htm")));
	}

}
