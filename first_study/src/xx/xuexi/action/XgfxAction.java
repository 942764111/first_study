/*
 *@(#)xx.xuexi.action
 *@Xgfx.java.java  
 *@����ʱ��:2011-8-5����11:15:59
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.xuexi.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @Xgfx <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{ѧϰЧ������} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default")
public class XgfxAction extends ActionSupport {

	/**
	 * @{��ת����ʦ��������ҳ��/page/xgfx/xgfx.jsp}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��xgfx.jsp}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	//@Action(value="xgfx",results={@Result(name="success",location="/page/xgfx/xgfx.jsp")})
	@Action(value="/xxggffxx",results={@Result(name="root",location="/page/xgfx/xgfx.jsp")})
	public String xgfx(){
		
		return "root";
	}
	/**
	 * @{��ת��ѧ����������ҳ��/page/xgfx/stuSj.jsp}
	 * @param {} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @{��ת��stuSj.jsp}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	**/
	@Action(value="/stuSj",results={@Result(name="success",location="/page/xgfx/stuSj.jsp")})
	public String stuSj(){
		
		return SUCCESS;
	}
	
}
