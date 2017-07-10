/*
 *@(#)xx.uploadDemo.action
 *@UploadDemoAction.java.java  
 *@����ʱ��:2011-5-18����09:49:44
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @UploadDemoAction <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{����������ͨ���ļ��ϴ�����action,1.��session.upload�����д����ϴ��ļ����������ļ���չ������С������洢·������
 * 2.����muploadDemo.jsp} 
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
	 * 1:�ļ�����Ϊȫ��(*.*)
	 * 2:�ļ�����Ϊ��ͼ��(*.jpg,*.jpeg,*.bmp,*.gif,*.png)
	 * 3:�ļ�����Ϊ:�ĵ�(*.pdf,*.flv,*.swf)
	 * 4:�ļ�����Ϊ:��Ƶ(*.mp3,*.wav,*.midi)
	 * 5:�ļ�����Ϊ:��Ƶ(*.flv)
	 * 6:�ļ�����Ϊ:����(*.swf)
	 * 7:�ļ�����Ϊ:��ý��(*.ppt;*.swf;*.rar)
	 * 8:�ļ�����Ϊ:excel���(*.xls)
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
	 * @{��ӹ���}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
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