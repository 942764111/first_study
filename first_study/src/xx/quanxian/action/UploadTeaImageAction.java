/*
 *@(#)xx.xuexi.action
 *@Xgfx.java.java  
 *@创建时间:2011-8-5上午11:15:59
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
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
 * @Xgfx <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{上传老师头像} 
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
	 * @{方法名:tt_uupload_image}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述:将文件上传到数据库中}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="tt_uupload_image",results={@Result(name="success",type="json")})
	public String t_uploadFile() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		//来自LoginAction.java文件中的，RegUser1.action设置了hs.setAttribute("newuid", userid);
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
//		hs.setAttribute("filepath",filepath);                                  //为了在用户提交详细信息时获得其头像路径
//	}
	/**
	 * @{方法名:t_upload_image}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能:仅先把老师的头像保存在指定的目录下，当点击提交教师详细信息时才将头像路径填到数据库teacher表的jszp字段中}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="t_upload_image",results={@Result(name="success",type="json")})
	public String uploadFile() { 
		
		String jsessionid=userId;
		System.out.println("jsessionid------------->"+jsessionid);
		for(int i=0;i<fileupload.size();++i){
			try {
				String extName = ""; //保存文件拓展名 
				String newFileName = ""; //保存新的文件名 
				
				
				//获取拓展名 
		        if (fileuploadFileName.get(i).lastIndexOf(".") >= 0){   
		            extName = fileuploadFileName.get(i).substring(fileuploadFileName.get(i).lastIndexOf(".")); 
		            System.err.println(extName);
		        }
		       				
				HttpServletResponse response = ServletActionContext.getResponse(); 
		        response.setCharacterEncoding("utf-8"); //务必，防止返回文件名是乱码 
		      
		        newFileName = jsessionid + ".jpg"; //文件重命名后的名字
			    String root = "";
			    
		        if(extName.equals(".jpg")||extName.equals(".jpeg")||extName.equals(".bmp")
		        		||extName.equals(".gif")||extName.equals(".png")) {
				    
				    root = ServletActionContext.getRequest().getRealPath("/upload/teaImages/");
//				    filepath = "upload/teaImages/"+ newFileName;                                   //数据库要保存的路径
//				    saveFilePath();
		        } 

				File destFile = new File(root,newFileName);                                //如果目的文件中存在同名的图片会自动覆盖掉得，即实现了修改头像

				//=================生成缩略图=========================

				Image src = javax.imageio.ImageIO.read(fileupload.get(i)); //构造Image对象

				float tagsize=110;
				int old_w=src.getWidth(null); //得到源图宽
				int old_h=src.getHeight(null); 
				int new_w=0;
				int new_h=0; //得到源图长
				int tempsize;
				float tempdouble; 
				if(old_w>old_h){
				tempdouble=old_w/tagsize;
				}else{
				tempdouble=old_h/tagsize;
				}
				new_w=Math.round(old_w/tempdouble);
				new_h=Math.round(old_h/tempdouble);//计算新图长宽
				BufferedImage image = new BufferedImage(new_w,new_h,BufferedImage.TYPE_INT_RGB);//创建一个BufferedImage来作为图像*作容器 
				Graphics g = image.getGraphics(); //创建一个绘图环境来进行绘制图象 
				g.drawImage(src,0,0,new_w,new_h,null); //将原图像数据流载入这个BufferedImage 
				g.setFont(new Font("Times New Roman",Font.PLAIN,24)); //设定文本字体 
				String rand = ""; 
				g.setColor(Color.red); //设定文本颜色 
				g.drawString(rand,new_w-200,new_h-10); //向BufferedImage写入文本字符 
				g.dispose(); //使更改生效

				FileOutputStream newimage=new FileOutputStream(destFile); //输出到文件流
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage); 
				encoder.encode(image); //近JPEG编码
				newimage.close(); 
				
				//===================================================

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	
}
