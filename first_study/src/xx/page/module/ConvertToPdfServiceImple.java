package xx.page.module;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/*
 * doc docx格式转换
 * @author Administrator
 */
public class ConvertToPdfServiceImple {
	private static final int environment=1;//环境1：windows 2:linux(涉及pdf2swf路径问题)
	private String inputfile;
	private String outputfile;
	private String outputPath="";//输入路径，如果不设置就输出在默认位置
	private String fileName;
	private File pdfFile;
	private File swfFile;
	private File docFile;
	private String exam;
	private String thumbnail;
	private static OfficeManager officeManager;
	private static String OFFICE_HOME = "D:\\Anzhuangchengxu\\OpenOffice4";
	private static int port[] = { 8100,8110,8120, };
	public static void startService() {
		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		try {
			//System.out.println("准备启动服务....");
			configuration.setOfficeHome(OFFICE_HOME);// 设置OpenOffice.org安装目录
			configuration.setPortNumbers(port); // 设置转换端口，默认为8100
			configuration.setMaxTasksPerProcess(3);//设置�?��进程�?
			configuration.setTaskExecutionTimeout(1000 * 60 * 3L);// 设置任务执行超时�?分钟
			configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);// 设置任务队列超时�?4小时
			officeManager = configuration.buildOfficeManager();
			officeManager.start(); // 启动服务
			System.out.println("office转换服务启动成功!");
		} catch (Exception ce) {
			System.out.println("office转换服务启动失败!详细信息:" + ce);
		}
	}
	public ConvertToPdfServiceImple(Map<String, String> map)
	{
		ini(map);
	}

	/*
	 * 重新设置 file
	 * @param fileString
	 */
	public void setFile(Map<String, String> map)
	{
		ini(map);
	}

	/*
	 * 初始化
	 * @param fileString
	 */
	private void ini(Map<String, String> map)
	{
		
		this.inputfile=map.get("inputfile");
		outputfile=map.get("outputfile");
		System.out.println("inputfile:"+inputfile);
		fileName=outputfile.substring(0,outputfile.lastIndexOf("."));
		System.out.println("fileName:"+fileName);
		exam=inputfile.substring(inputfile.lastIndexOf("."));//文件后缀
		System.out.println("exam:"+exam);
		docFile=new File(inputfile);
		pdfFile=new File(fileName+".pdf");
		swfFile=new File(fileName+".swf");
		thumbnail=map.get("thumbnail");
	}

	/*
	 * 转为PDF
	 * @param file
	 */
	private void doc2pdf() throws Exception
	{
		System.out.println(docFile);
		if(docFile.exists())
		{
			if(!pdfFile.exists())
			{
				try
				{

					OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
					converter.convert(docFile,pdfFile);
					System.out.println("****pdf转换成功，PDF输出："+pdfFile.getPath()+"****");
				}
				catch(Exception e)
				{
					e.printStackTrace();
					throw e;
				}
			}
			else
			{
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
		
			
		}
		else
		{
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
	}

	
	/*
	 * 转换成swf
	 */
	private void pdf2swf() throws Exception
	{
		Runtime r=Runtime.getRuntime();
		if(!swfFile.exists())
		{
			if(pdfFile.exists())
			{
				try {
					Process p=r.exec("H:/input/pdf2swf.exe "+pdfFile.getPath()+" -o "+swfFile.getPath()+" -T 9");
					System.out.print(loadStream(p.getInputStream()));
					System.err.print(loadStream(p.getErrorStream()));
					System.out.print(loadStream(p.getInputStream()));
					System.err.println("****swf转换成功，文件输出："+swfFile.getPath()+"****");
					if(pdfFile.exists())
					{
						pdfFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
			else {
				System.out.println("****pdf不存在，无法转换****");
			}
		}
		else {
			System.out.println("****swf已存在不需要转换****");
		}
	}

	static String loadStream(InputStream in) throws IOException
	{
		int ptr=0;
		in=new BufferedInputStream(in);
		StringBuffer buffer=new StringBuffer();

		while((ptr=in.read())!=-1)
		{
			buffer.append((char)ptr);
		}
		return buffer.toString();
	}

	/*
	 * 转换主方法
	 */
	public boolean conver()
	{
		if(swfFile.exists())
		{
			System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
			return true;
		}

		System.out.println("****swf转换器开始工作，当前设置运行环境windows****");

		try {
			System.out.println("执行了:"+exam);
			if (exam.equals(".pdf")) {
				System.out.println("执行这里了》》》》》");
				pdfFile=new File(inputfile);
				PdfToJpgTest.start(pdfFile.getPath(), thumbnail);
				pdf2swf();
			} else {
				doc2pdf();
				PdfToJpgTest.start(pdfFile.getPath(), thumbnail);
				pdf2swf();
			}
			
			
		} catch (Exception e) {
			// TODO: Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		if(swfFile.exists())
		{
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * 返回文件路径
	 * @param s
	 */
	public String getswfPath()
	{
		if(swfFile.exists())
		{
			String tempString =swfFile.getPath();
			tempString=tempString.replaceAll("\\\\", "/");
			return tempString;
		}
		else{
			return "";
		}
	}

	/*
	 * 设置输出路径
	 */
	public void setOutputPath(String outputPath)
	{
		this.outputPath=outputPath;
		if(!outputPath.equals(""))
		{
			String realName=fileName.substring(fileName.lastIndexOf("/"),fileName.lastIndexOf("."));
			if(outputPath.charAt(outputPath.length())=='/')
			{
				swfFile=new File(outputPath+"output/"+realName+".swf");
			}
			else
			{
				swfFile=new File(outputPath+realName+".swf");
			}
		}
	}
	public static void stopService() {
		System.out.println("关闭office转换服务....");
		if (officeManager != null) {
			officeManager.stop();
		}
		System.out.println("关闭office转换成功!");
	}
	public static void cover(List<Map<String, String>> list)
	{
		startService();
		
		for (int i = 0; i < list.size(); i++) {
			ConvertToPdfServiceImple d=new ConvertToPdfServiceImple(list.get(i));	
			d.conver();
		}
		stopService();
	}
	public static void main(String[] a){
		startService();
//		ConvertToPdfServiceImple d=new ConvertToPdfServiceImple("h:/input/java开班简介.avi");
//		ConvertToPdfServiceImple d2=new ConvertToPdfServiceImple("h:/input/聚子伴软件系统平台PPT.ppt");
//		ConvertToPdfServiceImple d3=new ConvertToPdfServiceImple("h:/input/百度语音.pdf");
//		ConvertToPdfServiceImple d4=new ConvertToPdfServiceImple("h:/input/中软杯题意分析.doc");
//		ConvertToPdfServiceImple d5=new ConvertToPdfServiceImple("h:/input/招新报名表(1).xls");
//		ConvertToPdfServiceImple d6=new ConvertToPdfServiceImple("h:/input/测试.html");
//		ConvertToPdfServiceImple d7=new ConvertToPdfServiceImple("h:/input/55bb3da82054b.docx");
//		ConvertToPdfServiceImple d8=new ConvertToPdfServiceImple("h:/input/工作簿1_20160404034446.xlsx");
//
//				d.conver();
//				d2.conver();
//				d3.conver();
//				d4.conver();
//				d5.conver();
//				d6.conver();
//				d7.conver();
//		d8.conver();
	}
}
