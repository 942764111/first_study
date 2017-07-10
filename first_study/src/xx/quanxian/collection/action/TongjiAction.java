/*
 *@(#)xx.quanxian.collection.action
 *@TongjiAction.java.java  
 *@创建时间:2011-8-29上午10:52:20
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.collection.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Tjb;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @TongjiAction <code>{类名称}</code>
 * @author  {徐鹏飞}
 * @version {2011-8-29上午10:52:20}
 * @{实现资源的统计功能} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class TongjiAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService service;
	private List<Zytj> rows = new ArrayList<Zytj>();
	private int total;
	private int page;
	private int rows_s;
	private List<Zytj> list1=new ArrayList<Zytj>();
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public List<Zytj> getList1() {
		return list1;
	}
	public void setList1(List<Zytj> list1) {
		this.list1 = list1;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	
	public List<Zytj> getRows() {
		return rows;
	}
	public void setRows(List<Zytj> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}

	/**
	 * Tjlist <code>{Tjlist}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-8-29上午10:52:20}
	 * @{实现资源的分页统计功能} 
	 */
	@Action(value="/Tjlist",results={@Result(name="success",type="json",params={"includeProperties","rows.*,total"})})
	public String Tjlist(){
		
		List<Tjb> list= this.service.findHql(Tjb.class, "from Tjb tjb order by tjb.id.CName desc");
		
		List<Tjb> list1 = new ArrayList<Tjb>();
		
		List<Tjb> list2 = new ArrayList<Tjb>();   //编写算法过滤重复的课程名称
		
		for(int m=0;m<list.size();m++){
			if(m==0){
				list2.add(list.get(m));
			}
			else {
				if(!(list.get(m).getId().getCName().equals(list.get(m-1).getId().getCName()))){
					list2.add(list.get(m));
				}
			}
		}
		for(int i=0;i<list2.size();i++)
		{
			Tjb tjb = new Tjb();
			tjb = list2.get(i);
			Zytj z = new Zytj();
			z.setCname(tjb.getId().getCName().replaceAll(" ", ""));
			List<Zytjchild> childs=new ArrayList<Zytjchild>();
			list1= this.service.findHql(Tjb.class, "from Tjb tjb where tjb.id.CName='"+tjb.getId().getCName()+"'");
			//定义六个统计父类的变量
			int forczt = 0;
			int fordmt = 0;
			int forpd = 0;
			int forxz = 0;
			int fortk = 0;
			int forzysc = 0;
			for(int j=0;j<list1.size();j++){
				Tjb tjb1 = new Tjb();
				tjb1=list1.get(j);
				Zytjchild zz=new Zytjchild();
				zz.setCname(tjb1.getId().getZmc());
				zz.setCzt_sys(tjb1.getCztSl()+"");
				zz.setDmt_sys(tjb1.getDmtSl()+"");
				zz.setPd_sys(tjb1.getPdSl()+"");
				zz.setTk_sys(tjb1.getTkSl()+"");
				zz.setXz_sys(tjb1.getXzSl()+"");
				zz.setZysc_sys(tjb1.getZyscSl()+"");
				forczt += tjb1.getCztSl();
				fordmt += tjb1.getDmtSl();
				forpd += tjb1.getPdSl();
				forxz += tjb1.getXzSl();
				fortk += tjb1.getTkSl();
				forzysc += tjb1.getZyscSl();
				childs.add(zz);
			}
			z.setCzt_sys(forczt+"");
			z.setDmt_sys(fordmt+"");
			z.setPd_sys(forpd+"");
			z.setTk_sys(fortk+"");
			z.setXz_sys(forxz+"");
			z.setZysc_sys(forzysc+"");
			z.setChildren(childs);
			z.setState("closed");
			rows.add(z);
		}
		total = this.service.getTotal("Tjb");
		return SUCCESS;
	}

}
