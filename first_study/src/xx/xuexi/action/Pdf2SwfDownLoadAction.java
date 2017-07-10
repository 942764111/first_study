/*
 *@(#)xx.xuexi.action
 *@Pdf2SwfDownLoadAction.java.java  
 *@创建时间:2012-2-22上午10:51:23
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import xx.download.utils.DownLoad;

/**
 * @Pdf2SwfDownLoadAction <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Pdf2SwfDownLoadAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @{方法名pdf2swf.action}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{该action本打算是用来，pdf2swf转化器的下载工作的，可是系统的调整为了上传文本材料时用java代码自动实现向swf的转换即以后
	 * 可以不用下载该转化器 了}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@SuppressWarnings("deprecation")
	@Action(value="/pdf2swfAction",results={@Result(name="success",type="json")})
	public String pdf2swf(){
		
		String urlpath=ServletActionContext.getRequest().getRealPath("/pdf2swf")+"/pdf2swf.exe";
		String path="C:/360Downloads";
		String filename="pdf2swf_Converter.exe";
		DownLoad dl=new DownLoad(urlpath,path,filename);
		int temp=dl.downLoadFiles();
		if(temp==0){
			System.out.println("下载成功!");
		}else if(temp==1){
			System.out.println("文件已经存在！！！");
		}else{
			System.out.println("下载过程中出现问题");
		}
		
		return "success";
	}

}
