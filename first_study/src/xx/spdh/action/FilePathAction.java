/*
 *@(#)xx.spdh.action
 *@FilePathAction.java.java  
 *@创建时间:2011-11-5下午08:54:00
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @FilePathAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class FilePathAction extends ActionSupport {

	@Resource(name="baseService")
	private BaseService baseservice;
	
	private String filename;
	private String zlmc;
	private String fp4;
	
	
	/**
	 * @return the baseservice
	 */
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	/**
	 * @param baseservice the baseservice to set
	 */
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

	/**
	 * @return the filename
	 */
	@JSON(serialize=false)
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the zlmc
	 */
	@JSON(serialize=false)
	public String getZlmc() {
		return zlmc;
	}

	/**
	 * @param zlmc the zlmc to set
	 */
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}

	/**
	 * @return the fp4
	 */
	@JSON
	public String getFp4() {
		return fp4;
	}

	/**
	 * @param fp4 the fp4 to set
	 */
	public void setFp4(String fp4) {
		this.fp4 = fp4;
	}
	

	@Action(value="/filePath",results={@Result(name="root",type="json")})
	public String filePath() {
		String[] keys1 = new String[1];
		keys1[0] = "filename";
		Object[] values1 = new Object[1];
		values1[0] =filename;
		List<String> fp1 = this.baseservice.find(String.class, "Scwj", "id.filepath", keys1, values1);
		
		String[] keys2 = new String[1];
		keys2[0] = "zlmc";
		Object[] values2 = new Object[1];
		values2[0] =zlmc;
		List<Integer> bb = this.baseservice.find(Integer.class, "Dmtzl", "zlbh", keys2, values2);
		
		int m = fp1.get(0).indexOf("\\") + 1;
		int n = fp1.get(0).lastIndexOf("\\");
		int j = fp1.get(0).lastIndexOf("\\") + 1;
		String fp2 = fp1.get(0).substring(m, n);
		String fp3 = fp1.get(0).substring(j, fp1.get(0).length());
		fp4 = fp2 + "/" + fp3;
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("_zlbh",bb.get(0));
		return "root";
	}
	
}
