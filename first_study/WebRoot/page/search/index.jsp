<%@ page language="java"
	import="java.util.*,java.io.*,com.easysearching.configuration.*;"
	pageEncoding="UTF-8"%>
<%
    ServletContext context = getServletContext();
    System.out.println("contextPath:"+context.getContextPath());
    System.out.println("realpath:"+this.getServletContext().getRealPath("/"));
	File file = new File(this.getServletContext().getRealPath("/")+ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_PATH) + "\\test.txt");
	FileInputStream fis = new FileInputStream(file);
	byte[] byteArray = new byte[(int) file.length()];
	fis.read(byteArray);
	String content = new String(byteArray, "UTF-8");
    out.println(content);
	//System.out.println(fileSource.isFile());
	//File indexSource = new File(ConfigurationCache.getConfiguration(ConfigurationConstant.INDEX_PATH));
    //System.out.println(indexSource.getAbsolutePath());
    
%>

