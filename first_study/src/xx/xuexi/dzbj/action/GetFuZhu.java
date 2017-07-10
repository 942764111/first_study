/*
 *@(#)xx.xuexi.dzbj.action
 *@Get.java.java  
 *@创建时间:2011-11-13下午09:45:10
 *@作者：guangge
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.dzbj.action;

import java.util.ArrayList;
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

import xx.collection.bean.Dzbj;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * @Get <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class GetFuZhu extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="baseService")
	private BaseService baseService;
	
	
	private List list = new ArrayList();
	
	
	
	/**
	 * @return the list
	 */
	@JSON(serialize=false)
	public List getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}


	/**
	 * @return the baseService
	 */
	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}


	/**
	 * @param baseService the baseService to set
	 */
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
		


		/**
		 * @return the currentpage
		 */
		@JSON(serialize=false)
		public String getCurrentpage() {
			return currentpage;
		}


		/**
		 * @param currentpage the currentpage to set
		 */
		public void setCurrentpage(String currentpage) {
			this.currentpage = currentpage;
		}
		private String currentpage;
		
		private ArrayList<Integer> x1=new ArrayList<Integer>();
		private ArrayList<Integer> tmbh1=new ArrayList<Integer>();
		private int num1;
		/**
		 * @return the num1
		 */
		public int getNum1() {
			return num1;
		}


		/**
		 * @param num1 the num1 to set
		 */
		public void setNum1(int num1) {
			this.num1 = num1;
		}


		/**
		 * @return the tmbh1
		 */
		public ArrayList<Integer> getTmbh1() {
			return tmbh1;
		}


		/**
		 * @param tmbh1 the tmbh1 to set
		 */
		public void setTmbh1(ArrayList<Integer> tmbh1) {
			this.tmbh1 = tmbh1;
		}


		/**
		 * @return the x1
		 */
		public ArrayList<Integer> getX1() {
			return x1;
		}


		/**
		 * @param x1 the x1 to set
		 */
		public void setX1(ArrayList<Integer> x1) {
			this.x1 = x1;
		}


		/**
		 * @return the y1
		 */
		public ArrayList<Integer> getY1() {
			return y1;
		}


		/**
		 * @param y1 the y1 to set
		 */
		public void setY1(ArrayList<Integer> y1) {
			this.y1 = y1;
		}


		/**
		 * @return the note
		 */
		public ArrayList<String> getNote() {
			return note;
		}


		/**
		 * @param note the note to set
		 */
		public void setNote(ArrayList<String> note) {
			this.note = note;
		}
		private ArrayList<Integer> y1=new ArrayList<Integer>();
		private ArrayList<String> note=new ArrayList<String>();
		private String zlmc;
		




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


		@Action(value="/getfuzhu",results={@Result(name="success",type="json")})
		public String execute(){
			HttpSession hs = ServletActionContext.getRequest().getSession();
			String userid = (String)hs.getAttribute("uid");


			/*String[] keys0 = new String[1];
			keys0[0] = "zlmc";
			Object[] values0 = new Object[1];
			values0[0] = zlmc;
			List<Integer> zlbh = this.baseService.find(Integer.class, "Dmtzl", "zlbh", keys0, values0);
			String zlid = zlbh.get(0).toString();*/
			
			
			Dzbj d = new Dzbj();
			System.out.println(userid+"'"+currentpage);
			String hql = "from Dzbj d where d.id.userId='"+userid+"' and d.zlid='"+zlmc+"' and d.biaojilx='fuzhu' and d.weizhi='"+currentpage+"'";
			list = this.baseService.findHql(Dzbj.class, hql);
			System.out.println(list.size());
			if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				d = (Dzbj) list.get(i);
				note.add(d.getTmnr());
				x1.add(d.getZuobiaoX2());
				y1.add(d.getZuobiaoY2());
				tmbh1.add(d.getId().getTmbh());
			}
			
			}
			
			String hql2 ="select max(dz.id.tmbh) from Dzbj dz";
			List l=this.baseService.findHql(Dzbj.class,hql2);
			//l.get(0);
			//System.out.println("hhhhhhhhhhhhhhhh");
			if(l==null){
				
				num1=0;
				
			}else{
			num1=Integer.parseInt(l.get(0)+"");
			}
			//System.out.println(d.getZuobiaoX()+"'"+d.getZuobiaoX1()+"'");
			return "success";
			
		}
}
