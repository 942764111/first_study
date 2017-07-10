package xx.mindMap.action;  

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.CourseChapter;
import xx.collection.bean.JxjhYck;
import xx.collection.bean.Jxnr;
import xx.collection.bean.Scwj;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class GetFileAction extends ActionSupport  
{  

	private List<AllFile> rows=new ArrayList<AllFile>(); 
	@Resource(name="baseService")
	private BaseService baseservice;
	private String state;
	public List<AllFile> getRows() {
		return rows;
	}

	public void setRows(List<AllFile> rows) {
		this.rows = rows;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Action(value="getWenjian",results={@Result(name="success",type="json")})
	public String getWenjian() throws Exception  
	{  
		
		AllFile aFile=new AllFile();
		aFile.setKey("C语言程序设计"+"/"+"基础知识");
		aFile.setName("C语言讲解.avi");
		aFile.setZlid("7");
		aFile.setViewPath("upload\\previewFile\\videos\\guoqiang\\2016060610552468179.flv");
		rows.add(aFile);
		AllFile aFile1=new AllFile();
		aFile1.setKey("C语言程序设计"+"/"+"基础知识");
		aFile1.setName("标识符.doc");
		aFile1.setZlid("6");
		aFile1.setViewPath("upload\\previewFile\\videos\\guoqiang\\2016060610552468179.flv");
		
		rows.add(aFile1);
		AllFile aFile2=new AllFile();
		aFile2.setKey("C语言程序设计"+"/"+"基础知识");
		aFile2.setName("常量与变量.doc");
		aFile2.setZlid("5");
		aFile2.setViewPath("upload\\previewFile\\videos\\guoqiang\\2016060610552468179.flv");
		
		rows.add(aFile2);
		AllFile aFile3=new AllFile();
		aFile3.setKey("C语言程序设计"+"/"+"基础知识");
		aFile3.setName("关键字.doc");
		aFile3.setZlid("4");
		aFile3.setViewPath("upload\\previewFile\\videos\\guoqiang\\2016060610552468179.flv");
		
		rows.add(aFile3);
		AllFile aFile4=new AllFile();
		aFile4.setKey("C语言程序设计"+"/"+"基础知识");
		aFile4.setName("C语言发展.txt");
		aFile4.setZlid("3");
		aFile4.setViewPath("upload\\previewFile\\videos\\guoqiang\\2016060610552468179.flv");
		
		rows.add(aFile4);
		AllFile aFile5=new AllFile();
		aFile5.setKey("C语言程序设计"+"/"+"基础知识");
		aFile5.setName("变量定义.docx");
		aFile5.setZlid("2");
		aFile5.setViewPath("upload\\previewFile\\videos\\guoqiang\\2016060610552468179.flv");
		
		rows.add(aFile5);
		AllFile aFile6=new AllFile();
		aFile6.setKey("C语言程序设计"+"/"+"基础知识");
		aFile6.setName("基础练习题.doc");
		aFile6.setZlid("1");
		aFile6.setViewPath("upload\\previewFile\\videos\\guoqiang\\2016060610552468179.flv");
		
		rows.add(aFile6);
//		state="1";
//		String hql="select distinct(a.TCName) from CourseChapter as a";
//		//获取课程名称
//		List<CourseChapter> zList=this.baseservice.findHql(CourseChapter.class, hql);
//		if (zList.size()==0) {
//			return SUCCESS;
//		}
//		String aStrings=zList.toString().substring(1,zList.toString().length()-1);
//		String[] czString=aStrings.split(",");
//		System.out.println("czString:"+czString);
//		for (int i = 0; i < czString.length; i++) {
//
//			String KName=czString[i];
//			//根据课程名称选择章节名称
//			String[] keys={"TCName"};
//			String[] values={KName};
//			System.out.println("课程名称:"+KName);
//			List<CourseChapter> cList=this.baseservice.find (CourseChapter.class,"CourseChapter",keys,values);
//			System.out.println("章节长度:"+cList.size());
//			for (int k = 0; k < cList.size(); k++) {
//				CourseChapter chapter=cList.get(k);
//				String ZName=chapter.getCName();
//				int zbh=chapter.getZbh();
//				System.out.println("章节名称:"+ZName+" 章节编号:"+zbh);
//				//根据编号查找对应资料id
//				String hql1="select a from JxjhYck as a where a.courseChapter.zbh='"+zbh+"'";
//				List<JxjhYck> zList2=this.baseservice.findHql(JxjhYck.class, hql1);
//				System.out.println("zList:"+zList2+" 长度:"+zList2.size());
//				if (zList2.size()==0) {
//					System.out.println("该教学计划底下没有资料");
//				} else {
//					String jxjhid=zList2.get(i).getJxjhSz();
//					System.out.println("教学计划id:"+jxjhid);
//					//根据资料编号查找资料名称
//					String hql4="select a from Jxnr as a where a.jxjhSz='"+jxjhid+"'";
//					List<Jxnr> zList4=this.baseservice.findHql(Jxnr.class, hql4);
//					for (int j = 0; j < zList4.size(); j++) {
//						int zlbh=zList4.get(j).getZlid();
//						System.out.println("资料Id:"+zlbh);
//						String hql2="select a from Scwj as a where a.dmtzl.zlbh='"+zlbh+"'";
//						List<Scwj> zList3=this.baseservice.findHql(Scwj.class, hql2);
//
//						Scwj scwj=zList3.get(0);
//						System.out.println("scwj:"+scwj.getFilename());
//						AllFile aFile=new AllFile();
//						aFile.setKey(KName+"/"+ZName);
//						aFile.setName(scwj.getFilename());
//						aFile.setZlid(String.valueOf(scwj.getNo()));
//						rows.add(aFile);
//					}
//				}
//			}	
//		}
		HttpSession session1 = ServletActionContext.getRequest().getSession(); System.out.println("session1:"+session1.getId());
		session1.setAttribute("rows",rows);

		return SUCCESS;

	}

}