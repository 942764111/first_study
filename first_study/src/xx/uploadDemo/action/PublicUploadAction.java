/*
 *@(#)xx.uploadDemo.action
 *@PublicUploadAction.java.java  
 *@����ʱ��:2011-5-26����02:34:24
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.uploadDemo.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.stereotype.Controller;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @PublicUploadAction <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:ͨ���ļ��ϴ���} 
 */
@Controller
@ParentPackage("default-package")
@Namespace("")
@SuppressWarnings("serial")
public class PublicUploadAction extends ActionSupport {
	
	private File file;
	private String[] fileName;
    private String path;
    
    public static final String ROOT = "root\\";
    private final int BUFFER_SIZE = 16 * 1024;
    
    @JSON
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public String[] getFileName() {
		return fileName;
	}
	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * ���request
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	/**
	 * ���session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}
	// �Լ���װ��һ����Դ�ļ������Ƴ�Ŀ���ļ�����     
	private void save(File src, File dst) {
       InputStream in = null;  
       OutputStream out = null;  
       try {     
    	   in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);  
           out = new BufferedOutputStream(new FileOutputStream(dst),     
                   BUFFER_SIZE);     
           byte[] buffer = new byte[BUFFER_SIZE];     
           int len = 0;     
           while ((len = in.read(buffer)) > 0) {     
        	   out.write(buffer, 0, len);     
           }     
       } catch (Exception e) { 
    	   e.printStackTrace();
       } finally {     
    	   if (null != in) {    
    		   try {     
    			   in.close();     
    		   } catch (IOException e) {     
    			   e.printStackTrace();     
    		   }     
    	   }     
    	   if (null != out) {     
    		   try {     
    			   out.close();     
    		   } catch (IOException e) {     
    			   e.printStackTrace();     
    		   }     
    	   }     
       }     
	}
	
    private static boolean isNonEmpty(Object[] objArray) {  
    	boolean result = false;  
    	for (int index = 0; index < objArray.length && !result; index++) {  
    		if (objArray[index] != null) {  
    			result = true;
    		} 
    	}  
    	return result;
    } 

    /**
     * @{upload}
     * @param {���������} {�������˵��}
     * @return {SUCCESS} {���ز���˵��}
     * @{�����Ĺ���/��������}
     * @exception {˵����ĳ�����,������ʲô�쳣}
    */
    @Action(value="/upload",results={@Result(name="success",type="json")})
    public String upload() {
		MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();
		// Bind allowed Files
		Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
		
		while (fileParameterNames != null && fileParameterNames.hasMoreElements()) {
			// get the value of this input tag
			String inputName = (String) fileParameterNames.nextElement();
			// get the content type
			String[] contentType = multiWrapper.getContentTypes(inputName);
			if (isNonEmpty(contentType)) {
				// get the name of the file from the input tag  
				String[] fileName = multiWrapper.getFileNames(inputName);
				StringBuffer sb = new StringBuffer();
				HttpSession session = ServletActionContext.getRequest().getSession();
				for(int i=0;i<fileName.length;i++) {
					String filenames = sb.append(fileName[i]).toString();
					System.out.println(filenames);
					session.setAttribute("fileNames", filenames);
				}
				if (isNonEmpty(fileName)) {
					// Get a File object for the uploaded File
					File[] files = multiWrapper.getFiles(inputName);
					if (files != null) {  
						for (int index = 0; index < files.length; index++) {
							String rootPath = getSession().getServletContext().getRealPath("/");
							rootPath += ROOT;
							String uploadPath = rootPath+fileName[index];
							File dstFile = new File(uploadPath);
							this.save(files[index], dstFile);
						}  
					}  
				}   
			}  
		}
		return SUCCESS;
    }
    
}
