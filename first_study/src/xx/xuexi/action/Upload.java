/*
 *@(#)xx.xuexi.action
 *@Xgfx.java.java  
 *@创建时间:2011-8-5上午11:15:59
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Scwj;
import xx.collection.bean.Dmtzl;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Wjlx;
import xx.collection.bean.Zlzsddy;
import xx.collection.bean.ZlzsddyId;
import xx.collection.bean.Zsd;

import xx.page.module.Pdf2SwfService;
import xx.quanxian.service.BaseService;
import xx.md5.module.MD5Builder;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Xgfx <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{学习效果分析部分的上传功能，教学管理部分的上传功能是调用小猪的} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Upload extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseService;
	@Resource(name="pdf2swf")
	private Pdf2SwfService pdf2swf;
	
	private int zsdbh;

	private String tip;
    private List<File> fileupload;
	private List<String> fileuploadFileName;
	private  String fileName;
	private  String filepath;
	private  Date scrq;
	private  int changdu;
	private  String zlmc1;
	private int no;
	private Integer zlbh;
	private String zlms;

	private String userId;
	private String lxm;
    private String zlly;
	private String zlscm;
	private String zmfilename;
	
	private String zsdmc;
	
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}


	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	/**
	 * @return the scrq
	 */
	public Date getScrq() {
		return scrq;
	}


	/**
	 * @param scrq the scrq to set
	 */
	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}


	/**
	 * @return the changdu
	 */
	public int getChangdu() {
		return changdu;
	}


	/**
	 * @param changdu the changdu to set
	 */
	public void setChangdu(int changdu) {
		this.changdu = changdu;
	}


	/**
	 * @return the zlmc1
	 */
	public String getZlmc1() {
		return zlmc1;
	}


	/**
	 * @param zlmc1 the zlmc1 to set
	 */
	public void setZlmc1(String zlmc1) {
		this.zlmc1 = zlmc1;
	}


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
	 * @return the zsdbh
	 */
	public int getZsdbh() {
		return zsdbh;
	}


	/**
	 * @param zsdbh the zsdbh to set
	 */
	public void setZsdbh(int zsdbh) {
		this.zsdbh = zsdbh;
	}


	/**
	 * @return the zsdmc
	 */
	public String getZsdmc() {
		return zsdmc;
	}


	/**
	 * @param zsdmc the zsdmc to set
	 */
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}



	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}


	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}


	/**
	 * @return the zlbh
	 */
	public Integer getZlbh() {
		return zlbh;
	}


	/**
	 * @param zlbh the zlbh to set
	 */
	public void setZlbh(Integer zlbh) {
		this.zlbh = zlbh;
	}


	/**
	 * @return the zlms
	 */
	public String getZlms() {
		return zlms;
	}


	/**
	 * @param zlms the zlms to set
	 */
	public void setZlms(String zlms) {
		this.zlms = zlms;
	}

	/**
	 * @return the lxm
	 */
	public String getLxm() {
		return lxm;
	}


	/**
	 * @param lxm the lxm to set
	 */
	public void setLxm(String lxm) {
		this.lxm = lxm;
	}


	/**
	 * @return the zlly
	 */
	public String getZlly() {
		return zlly;
	}


	/**
	 * @param zlly the zlly to set
	 */
	public void setZlly(String zlly) {
		this.zlly = zlly;
	}


	/**
	 * @return the zlscm
	 */
	public String getZlscm() {
		return zlscm;
	}


	/**
	 * @param zlscm the zlscm to set
	 */
	public void setZlscm(String zlscm) {
		this.zlscm = zlscm;
	}


	/**
	 * @return the zmfilename
	 */
	public String getZmfilename() {
		return zmfilename;
	}


	/**
	 * @param zmfilename the zmfilename to set
	 */
	public void setZmfilename(String zmfilename) {
		this.zmfilename = zmfilename;
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
	 * @return the tip
	 */
	public String getTip() {
		return tip;
	}


	/**
	 * @param tip the tip to set
	 */
	public void setTip(String tip) {
		this.tip = tip;
	}
   /**
	 * 根据路径创建一系列的目录
	 * 
	 * @param path
	 */
	public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				return file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
		return false;
	}
	/**
	 * @{方法名:t_upload_file}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述:将文件上传到数据库中}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="tt_uupload_file",results={@Result(name="success",type="json")})
	public String t_uploadFile() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		zsdbh=(Integer) hs.getAttribute("zsdbH");
		return SUCCESS;
		
	}
	/**
	 * @{方法名:t_upload_file}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述:将文件上传到数据库中}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="t_upload_file",results={@Result(name="success",type="json")})
	public String uploadFile() { 
		
		String jsessionid=userId;
		Scwj scwj = new Scwj();
		for(int i=0;i<fileupload.size();++i){
			
			String extName = ""; //保存文件拓展名 
			String newFileName = ""; //保存新的文件名 
			String oldFileName = ""; //pdf2swf时，保存原始文件名
			String nowTimeStr = ""; //保存当前时间 
			String root = "";
			
			try {
								
				//获取拓展名 
		        if (fileuploadFileName.get(i).lastIndexOf(".") >= 0){   
		            extName = fileuploadFileName.get(i).substring(fileuploadFileName.get(i).lastIndexOf(".")); 
		            System.err.println(extName);
		        }
		        
				SimpleDateFormat sDateFormat; 
                Random r = new Random(); 
				
				HttpServletResponse response = ServletActionContext.getResponse(); 
		        response.setCharacterEncoding("utf-8"); //务必，防止返回文件名是乱码 
		        
		        //生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）   
		        int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; //获取随机数 
				
		        sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //时间格式化的格式 
				sDateFormat.setLenient(false);
			    nowTimeStr = sDateFormat.format(new Date()); //当前时间
			    
			    newFileName = nowTimeStr + rannum + extName; //文件重命名后的名字
			    int j = fileuploadFileName.get(i).indexOf(".");
				zlmc1 = fileuploadFileName.get(i).substring(0, j);
			    scrq = sDateFormat.parse(nowTimeStr);
			    //fileName = fileuploadFileName.get(i);
			    fileName=zlmc1+"_"+userId+extName;
			    changdu = (int)fileupload.get(i).length();
                
			    
		        if(extName.equals(".jpg")||extName.equals(".jpeg")||extName.equals(".bmp")
		        		||extName.equals(".gif")||extName.equals(".png")) {
				    
				    root = ServletActionContext.getRequest().getRealPath("/upload/images/" + jsessionid);
				    filepath = "images\\" + jsessionid + "\\" + newFileName;
				    lxm="动画";
					
		        } else if (extName.equals(".pdf")||extName.equals(".ppt")
		        		||extName.equals(".xls")||extName.equals(".doc")||extName.equals(".docx")){
		        	
		        	root = ServletActionContext.getRequest().getRealPath("/upload/texts/" + jsessionid);
		        	if(!extName.equals(".swf")){//pdf2swf判断
		        		oldFileName = newFileName;
		        		newFileName = newFileName.replace(extName,".swf");
		        	}
		        	filepath = "texts\\" + jsessionid + "\\" + newFileName;
					lxm="文本";
				} else if (extName.equals(".flv")||extName.equals(".swf")) {
					
				    root = ServletActionContext.getRequest().getRealPath("/upload/videos/" + jsessionid);
				    filepath = "videos\\" + jsessionid + "\\" + newFileName;
					lxm="视频";
				} else if (extName.equals(".mp3")||extName.equals(".mp4")) {
					
					root = ServletActionContext.getRequest().getRealPath("/upload/voices/" + jsessionid);
					filepath = "voices\\" + jsessionid + "\\" + newFileName;
					lxm="音频";
				} else {
					
				}
			    InputStream is = new FileInputStream(fileupload.get(i));
			    mkDirectory(root);
						
				scwj.setFilepath(filepath);
				scwj.setFilename(fileName);
				this.baseService.save(scwj);
				File destFile = null;
				if(extName.equals(".swf")||extName.equals(".flv")){//pdf2swf判断选择
					destFile = new File(root,newFileName);
				}else{
					destFile = new File(root,oldFileName);
				}
				
	            OutputStream os = new FileOutputStream(destFile);
				byte[] buffer = new byte[400];
				int length = 0;
				while ((length = is.read(buffer)) > 0)
				{
					os.write(buffer, 0, length);
				}
				is.close();
				os.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//在web端实现pdf向swf的转换
				 if(!extName.equals(".swf")){
					root = root+"/";
					String sourcePath=root+oldFileName;
					 if(extName.equals(".pdf")){
						 int temp = pdf2swf.convertPDF2SWF(sourcePath, root, newFileName);
						 System.out.println("temp:"+temp);
						}else{
//							String pdffile=sourcePath.replace(extName, ".pdf");
//							new ConvertToPdf().convert(sourcePath, pdffile);//将文件转化为pdf的格式
//							pdf2swf.convertPDF2SWF(pdffile, root, newFileName);//再将pdf的格式转化为swf格式
//							this.deleteFile(pdffile);
						}
					 this.deleteFile(sourcePath);
					
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @{方法名:t_updatescwj}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{方法的功能/动作描述:将文件上传到数据库中}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="t_updatescwj",results={@Result(name="success",type="json")})
	public String updatescwj() {
		String md5 = this.getZlmc1();
		MD5Builder md5b = new MD5Builder();
		try {
			String zlmd5 = md5b.EncoderByMd5(md5);
            Userinfo userinfo = new Userinfo();
			
			userinfo.setUserId(userId);
			
			Wjlx wjlx = new Wjlx();
			wjlx.setLxm(lxm);
			
			Dmtzl dmtzl = new Dmtzl();
				
				dmtzl.setUserinfo(userinfo);
				dmtzl.setWjlx(wjlx);
				dmtzl.setZlmc(zlmc1);
				System.out.println("zlmcl:"+zlmc1+"fileName:"+fileName+"scrq:"+scrq+"changdu:"+changdu);
				dmtzl.setFilename(fileName);
				dmtzl.setZlms(zlms);
				dmtzl.setZlly(zlly);
				dmtzl.setZlscm(zlscm);
				dmtzl.setZmfilename(zlmc1);
				dmtzl.setScrq(scrq);
				dmtzl.setChangdu(changdu);
				dmtzl.setZlmd5(zlmd5);
				
				this.baseService.save(dmtzl);
			
				String hql = "select max(zlbh) from Dmtzl where filename='"+fileName+"'";
				zlbh = this.baseService.findHql(Integer.class, hql).get(0);

			String[] key_s=new String[1];Object[] value_s=new Object[1];
			key_s[0]="filepath";value_s[0]=filepath;
			Scwj scwj =this.baseService.find(Scwj.class, "Scwj", key_s, value_s).get(0);
			Dmtzl dmt= new Dmtzl();
			dmt.setZlbh(zlbh);
			scwj.setDmtzl(dmt);
			this.baseService.update(scwj);
			
			Zlzsddy zlzsddy=new Zlzsddy();
			key_s[0]="zsdbh";int ls;
			value_s[0]=zsdbh;ls=zsdbh;
			
			Zsd zsd=this.baseService.find(Zsd.class, "Zsd", key_s, value_s).get(0);
			zlzsddy.setZsd(zsd);
			
			dmtzl.setZlbh(zlbh);
			zlzsddy.setDmtzl(dmtzl);
			
			ZlzsddyId zlzsdid=new ZlzsddyId();
			zlzsdid.setZlbh(zlbh); zlzsdid.setZsdbh(ls); zlzsdid.setZbh(zsd.getId().getZbh()); zlzsdid.setCId(zsd.getId().getCId());
			zlzsddy.setId(zlzsdid);
			this.baseService.save(zlzsddy);

			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	/** 
	 * 删除单个文件 
	 * @param   fPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(String fPath) {  
		boolean flag = false;  
	    File file = new File(fPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}

	/**
	 * @{deletescwj.action}
	 * @param {scwjlist.action} {显示多媒体资料信息}
	 * @return {scwjlist.action} {显示所有多媒体资料信息} 
	 * @{删除多媒体资料信息}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/t_deletescwj",results={@Result(name="success",type="json")})
	public String deletedmtzl(){
		
		Scwj scwj=this.baseService.find(Scwj.class, no);
		String[] keys=new String[2];keys[0]="zsdmc";
		Object[] values=new Object[2];values[0]=zsdmc;
		Zsd zsd=this.baseService.find(Zsd.class, "Zsd", keys, values).get(0);
		
		keys[0]="zlbh";keys[1]="zsdbh";
		values[0]=scwj.getDmtzl().getZlbh();values[1]=zsd.getId().getZsdbh();
		Zlzsddy zlzsddy=this.baseService.find(Zlzsddy.class, "Zlzsddy", keys, values).get(0);
		this.baseService.delete(zlzsddy);
//		String root = ServletActionContext.getRequest().getRealPath("/upload");
//		String fPath = root + "\\" + scwj.getFilepath();
//		System.out.println(fPath);
//		deleteFile(fPath);
//		this.baseService.delete(scwj);
//		
//		Dmtzl dm=this.baseService.find(Dmtzl.class, scwj.getDmtzl().getZlbh());
//		this.baseService.delete(dm);
		return SUCCESS;
	}
	
	/**
	 * @{zxck.action(教师与学生)在线查看多媒体资料信息}
	 * @param {} {}
	 * @return {json数据} {tscwj.jsp} 
	 * @{}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/zxck",results={@Result(name="success",type="json")})
	public String zxck(){
		Dmtzl dmtzl=this.baseService.find(Dmtzl.class, zlbh);
		if(dmtzl.getWjlx().getLxm().equals("视频")){
			tip="视频";
		}else{
			tip="文本";
		}
		return SUCCESS;
	}
	
}
