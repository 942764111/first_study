/*
 *@(#)xx.xuexi.action
 *@Xgfx.java.java  
 *@����ʱ��:2011-8-5����11:15:59
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.action;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @Xgfx <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{�ϴ���ʦͷ��} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class UploadTeaImageAction extends ActionSupport {

	private List<File> fileupload;
	private List<String> fileuploadFileName;
	
	private  String userId;
	
	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the fileupload
	 */
	public List<File> getFileupload() {
		return fileupload;
	}


	/**
	 * @param fileupload the fileupload to set
	 */
	public void setFileupload(List<File> fileupload) {
		this.fileupload = fileupload;
	}


	/**
	 * @return the fileuploadFileName
	 */
	public List<String> getFileuploadFileName() {
		return fileuploadFileName;
	}


	/**
	 * @param fileuploadFileName the fileuploadFileName to set
	 */
	public void setFileuploadFileName(List<String> fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	
   
	/**
	 * @{������:tt_uupload_image}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�����Ĺ���/��������:���ļ��ϴ������ݿ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="tt_uupload_image",results={@Result(name="success",type="json")})
	public String t_uploadFile() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		//����LoginAction.java�ļ��еģ�RegUser1.action������hs.setAttribute("newuid", userid);
		String newuid=(String)hs.getAttribute("newuid");
		if(newuid==null||newuid.equals("")){
			userId = (String)hs.getAttribute("uid");
		}else{
			userId=newuid;
			hs.removeAttribute("newuid");
		}
	    
		return SUCCESS;
		
	}
//	public void saveFilePath(){
//		HttpSession hs = ServletActionContext.getRequest().getSession();
//		hs.setAttribute("filepath",filepath);                                  //Ϊ�����û��ύ��ϸ��Ϣʱ�����ͷ��·��
//	}
	/**
	 * @{������:t_upload_image}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�����Ĺ���:���Ȱ���ʦ��ͷ�񱣴���ָ����Ŀ¼�£�������ύ��ʦ��ϸ��Ϣʱ�Ž�ͷ��·������ݿ�teacher���jszp�ֶ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="t_upload_image",results={@Result(name="success",type="json")})
	public String uploadFile() { 
		
		String jsessionid=userId;
		System.out.println("jsessionid------------->"+jsessionid);
		for(int i=0;i<fileupload.size();++i){
			try {
				String extName = ""; //�����ļ���չ�� 
				String newFileName = ""; //�����µ��ļ��� 
				
				
				//��ȡ��չ�� 
		        if (fileuploadFileName.get(i).lastIndexOf(".") >= 0){   
		            extName = fileuploadFileName.get(i).substring(fileuploadFileName.get(i).lastIndexOf(".")); 
		            System.err.println(extName);
		        }
		       				
				HttpServletResponse response = ServletActionContext.getResponse(); 
		        response.setCharacterEncoding("utf-8"); //��أ���ֹ�����ļ��������� 
		      
		        newFileName = jsessionid + ".jpg"; //�ļ��������������
			    String root = "";
			    
		        if(extName.equals(".jpg")||extName.equals(".jpeg")||extName.equals(".bmp")
		        		||extName.equals(".gif")||extName.equals(".png")) {
				    
				    root = ServletActionContext.getRequest().getRealPath("/upload/teaImages/");
//				    filepath = "upload/teaImages/"+ newFileName;                                   //���ݿ�Ҫ�����·��
//				    saveFilePath();
		        } 

				File destFile = new File(root,newFileName);                                //���Ŀ���ļ��д���ͬ����ͼƬ���Զ����ǵ��ã���ʵ�����޸�ͷ��

				//=================��������ͼ=========================

				Image src = javax.imageio.ImageIO.read(fileupload.get(i)); //����Image����

				float tagsize=110;
				int old_w=src.getWidth(null); //�õ�Դͼ��
				int old_h=src.getHeight(null); 
				int new_w=0;
				int new_h=0; //�õ�Դͼ��
				int tempsize;
				float tempdouble; 
				if(old_w>old_h){
				tempdouble=old_w/tagsize;
				}else{
				tempdouble=old_h/tagsize;
				}
				new_w=Math.round(old_w/tempdouble);
				new_h=Math.round(old_h/tempdouble);//������ͼ����
				BufferedImage image = new BufferedImage(new_w,new_h,BufferedImage.TYPE_INT_RGB);//����һ��BufferedImage����Ϊͼ��*������ 
				Graphics g = image.getGraphics(); //����һ����ͼ���������л���ͼ�� 
				g.drawImage(src,0,0,new_w,new_h,null); //��ԭͼ���������������BufferedImage 
				g.setFont(new Font("Times New Roman",Font.PLAIN,24)); //�趨�ı����� 
				String rand = ""; 
				g.setColor(Color.red); //�趨�ı���ɫ 
				g.drawString(rand,new_w-200,new_h-10); //��BufferedImageд���ı��ַ� 
				g.dispose(); //ʹ������Ч

				FileOutputStream newimage=new FileOutputStream(destFile); //������ļ���
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage); 
				encoder.encode(image); //��JPEG����
				newimage.close(); 
				
				//===================================================

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	
}
