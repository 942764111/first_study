/*
 * @(#)xx.quanxian.action.{file_name} 
 * @ ����ʱ�䣺2011-4-18     
 * @ ���ߣ�tlq
 * @ Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.action;

import java.util.List;

/**
 * @author tlq
 *��Ҫ�����Ǵ洢�û���function
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
