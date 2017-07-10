/*
 *@(#)xx.page.module
 *@Pdf2Swf.java.java  
 *@创建时间:2012-5-28下午03:09:15
 *@作者：gq
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.page.module;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Pdf2Swf <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{功能描述:实现pdf向swf中的转换，默认调用的地方是在上传完文件之后。
 * 在web服务器端自动调用的，给用户的体验是pdf上传自动转换为了swf} 
 */
@Service("pdf2swf")
@Transactional
public class Pdf2SwfServiceImple{
	
	    
	    /**
	     * Pro.exitValue() 采用非阻塞的方式返回，如果没有立即拿到返回值，则抛出异常
	     * Pro.waitFor() 当前线程等待，如有必要，一直要等到由该 Pro 对象表示的进程已经终止。
	     * 但是如果我们在调用此方法时，如果不注意的话，很容易出现主线程阻塞，Pro也挂起的情况。
	     * 在调用waitFor() 的时候，Pro需要向主线程汇报运行状况，
	     * 所以要注意清空缓存区，即InputStream和ErrorStream，在网上，很多只提到处理InputStream，忽略了ErrorStream。
	     * 以下一段代码，注意到了这一点。
	     * @throws IOException 
	     * 
	     */
	    public int convertPDF2SWF(String sourcePath, String destPath) throws IOException{  
	    	
	    	//SWFTools的环境安装路径  
		    String SWFTOOLS_PATH = "H:/input/";
	  
	        File dest = new File(destPath);       
	        FileUtils.forceMkdir(dest);    
	      
	        // 源文件不存在则返回       
	        File source = new File(sourcePath);       
	        if (!source.exists()) {       
	            return -1;       
	        }       
	          
	        String[] envp = new String[1];       
	        envp[0] = "PATH="+SWFTOOLS_PATH;       
	        String command = "cmd /c \""+SWFTOOLS_PATH+"pdf2swf\" -z -s flashversion=9 " + sourcePath + " -o " + destPath ;       
	        Process pro=null;
	        
	        try {
				pro = Runtime.getRuntime().exec(command, envp);
			} catch (IOException e1) {
				e1.printStackTrace();
			}       
			final InputStream is1 = pro.getInputStream();  
			new Thread(new Runnable(){

				@Override
				public void run() {
					BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(is1));       
			        try {
						while (bufferedReader.readLine() != null) {       
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} 
				}
				
			}).start();// 启动单独的线程来清空pro.getInputStream()的缓冲区
			
			 InputStream is2 = pro.getErrorStream();
			 BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));            
			 StringBuilder buf = new StringBuilder(); // 保存输出结果流           
			 String line = null;           
			 try {
				while((line = br2.readLine()) != null) buf.append(line);// 循环等待ffmpeg进程结束           
				 System.out.println("输出结果为：" + buf);        
				 while (br2.readLine() != null);//清空pro.getErrorStream()的缓冲区
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			  
	              
	        try {      
	            pro.waitFor();       
	        } catch (Exception e) {       
	            e.printStackTrace();       
	        }     
	              
	        return pro.exitValue();  
	    }
	    
	   public static void main(String[] aStrings){
		   Pdf2SwfServiceImple pImple=new Pdf2SwfServiceImple();
		   String sourcePath="E:\\123.pdf";
		   String destPath="F:\\";
		  // pImple.convertPDF2SWF(sourcePath, destPath, "智能奶瓶.swf");
	   }

}
