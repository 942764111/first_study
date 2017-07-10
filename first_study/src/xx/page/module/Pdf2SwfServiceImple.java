/*
 *@(#)xx.page.module
 *@Pdf2Swf.java.java  
 *@����ʱ��:2012-5-28����03:09:15
 *@���ߣ�gq
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @Pdf2Swf <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{��������:ʵ��pdf��swf�е�ת����Ĭ�ϵ��õĵط������ϴ����ļ�֮��
 * ��web���������Զ����õģ����û���������pdf�ϴ��Զ�ת��Ϊ��swf} 
 */
@Service("pdf2swf")
@Transactional
public class Pdf2SwfServiceImple{
	
	    
	    /**
	     * Pro.exitValue() ���÷������ķ�ʽ���أ����û�������õ�����ֵ�����׳��쳣
	     * Pro.waitFor() ��ǰ�̵߳ȴ������б�Ҫ��һֱҪ�ȵ��ɸ� Pro �����ʾ�Ľ����Ѿ���ֹ��
	     * ������������ڵ��ô˷���ʱ�������ע��Ļ��������׳������߳�������ProҲ����������
	     * �ڵ���waitFor() ��ʱ��Pro��Ҫ�����̻߳㱨����״����
	     * ����Ҫע����ջ���������InputStream��ErrorStream�������ϣ��ܶ�ֻ�ᵽ����InputStream��������ErrorStream��
	     * ����һ�δ��룬ע�⵽����һ�㡣
	     * @throws IOException 
	     * 
	     */
	    public int convertPDF2SWF(String sourcePath, String destPath) throws IOException{  
	    	
	    	//SWFTools�Ļ�����װ·��  
		    String SWFTOOLS_PATH = "H:/input/";
	  
	        File dest = new File(destPath);       
	        FileUtils.forceMkdir(dest);    
	      
	        // Դ�ļ��������򷵻�       
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
				
			}).start();// �����������߳������pro.getInputStream()�Ļ�����
			
			 InputStream is2 = pro.getErrorStream();
			 BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));            
			 StringBuilder buf = new StringBuilder(); // ������������           
			 String line = null;           
			 try {
				while((line = br2.readLine()) != null) buf.append(line);// ѭ���ȴ�ffmpeg���̽���           
				 System.out.println("������Ϊ��" + buf);        
				 while (br2.readLine() != null);//���pro.getErrorStream()�Ļ�����
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
		  // pImple.convertPDF2SWF(sourcePath, destPath, "������ƿ.swf");
	   }

}
