/*
 *@(#)xx.xuexi.action
 *@Pdf2SwfDownLoadAction.java.java  
 *@����ʱ��:2012-2-22����10:51:23
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
 * @Pdf2SwfDownLoadAction <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Pdf2SwfDownLoadAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @{������pdf2swf.action}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{��action��������������pdf2swfת���������ع����ģ�����ϵͳ�ĵ���Ϊ���ϴ��ı�����ʱ��java�����Զ�ʵ����swf��ת�����Ժ�
	 * ���Բ������ظ�ת���� ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
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
			System.out.println("���سɹ�!");
		}else if(temp==1){
			System.out.println("�ļ��Ѿ����ڣ�����");
		}else{
			System.out.println("���ع����г�������");
		}
		
		return "success";
	}

}
