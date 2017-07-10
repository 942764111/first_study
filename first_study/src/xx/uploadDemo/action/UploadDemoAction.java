/*
 *@(#)xx.uploadDemo.action
 *@UploadDemoAction.java.java  
 *@创建时间:2011-5-18上午09:49:44
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.uploadDemo.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @UploadDemoAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述：通用文件上传测试action,1.向session.upload变量中传递上传文件参数。（文件扩展名、大小、服务存储路径。）
 * 2.返回muploadDemo.jsp} 
 */

@Controller
@ParentPackage("default-package")
@Namespace("")
@SuppressWarnings("serial")
public class UploadDemoAction extends ActionSupport {
	
	private BaseService baseservice;
	
	private String username;
	private String password;
	/**
	 * 1:文件类型为全部(*.*)
	 * 2:文件类型为：图像(*.jpg,*.jpeg,*.bmp,*.gif,*.png)
	 * 3:文件类型为:文档(*.pdf,*.flv,*.swf)
	 * 4:文件类型为:音频(*.mp3,*.wav,*.midi)
	 * 5:文件类型为:视频(*.flv)
	 * 6:文件类型为:动画(*.swf)
	 * 7:文件类型为:多媒体(*.ppt;*.swf;*.rar)
	 * 8:文件类型为:excel表格(*.xls)
	 */
	private String fileType="2";
	private int maxSize=3 * 1024 * 1024;
	private String filesPath = "root\\";
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	public String getUsername() {
		return username;
	}
	@JSON(deserialize=true)
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	@JSON(deserialize=true)
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JSON(serialize=false)
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Action(value="/getParam",results={@Result(name="success",location="/upload/muploadDemo.jsp")})
	public String getParam() {
		HttpSession upload = ServletActionContext.getRequest().getSession();
		upload.setAttribute("fileType", fileType);
		upload.setAttribute("maxSize", maxSize);
		upload.setAttribute("filesPath", filesPath);
		return SUCCESS;
	}
	
	/**
	 * @{tjbd.action}
	 * @{添加功能}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/tjbd",results={@Result(name="success",type="json")})
	public String tjbd(){
//		ActionContext context = ActionContext.getContext(); 
//		Map params = context.getParameters(); 
//		List<String> fileName =(List<String>)params.get("upfiles");
//		System.out.println(fileName);
		HttpSession session = ServletActionContext.getRequest().getSession();
		String filename = (String)session.getAttribute("fileNames");
//		Upload upload = new Upload();
//		upload.setUsername(username);
//		upload.setPassword(password);
//		upload.setFilename(filename);
//		this.baseservice.save(upload);		
		return SUCCESS;
	}
	
}