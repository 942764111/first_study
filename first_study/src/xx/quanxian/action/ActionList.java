/*
 * @(#)xx.quanxian.action.{file_name} 
 * @ 创建时间：2011-4-18     
 * @ 作者：tlq
 * @ Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.action;

import java.util.List;

/**
 * @author tlq
 *主要功能是存储用户的function
 */
public class ActionList {
	
	private String moduleclass;
	private String modulename;
	private List functionname;
	
	
	public String getModuleclass() {
		return moduleclass;
	}
	public void setModuleclass(String moduleclass) {
		this.moduleclass = moduleclass;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public List getFunctionname() {
		return functionname;
	}
	public void setFunctionname(List functionname) {
		this.functionname = functionname;
	}
	
}
