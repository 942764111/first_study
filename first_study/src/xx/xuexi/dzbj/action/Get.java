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
public class Get extends ActionSupport {
	
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
		private ArrayList<Integer> x=new ArrayList<Integer>();
		/**
		 * @return the color
		 */
		public ArrayList<String> getColor() {
			return color;
		}


		/**
		 * @param color the color to set
		 */
		public void setColor(ArrayList<String> color) {
			this.color = color;
		}


		/**
		 * @return the source
		 */
		public ArrayList<String> getSource() {
			return source;
		}


		/**
		 * @param source the source to set
		 */
		public void setSource(ArrayList<String> source) {
			this.source = source;
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


		/**
		 * @return the x
		 */
		public ArrayList<Integer> getX() {
			return x;
		}
		private ArrayList<Integer> y=new ArrayList<Integer>();
		private ArrayList<Integer> x1=new ArrayList<Integer>();
		private ArrayList<Integer> y1=new ArrayList<Integer>();
		private ArrayList<String> color=new ArrayList<String>();
		private ArrayList<String> source=new ArrayList<String>();
		private ArrayList<String> note=new ArrayList<String>();
		private ArrayList<Integer> tmbh=new ArrayList<Integer>();
		private int num;
		private String classnoclass;
		
		
		


	



		/**
		 * @return the classnoclass
		 */
		public String getClassnoclass() {
			return classnoclass;
		}


		/**
		 * @param classnoclass the classnoclass to set
		 */
		public void setClassnoclass(String classnoclass) {
			this.classnoclass = classnoclass;
		}


		/**
		 * @return the num
		 */
		public int getNum() {
			return num;
		}


		/**
		 * @param num the num to set
		 */
		public void setNum(int num) {
			this.num = num;
		}


		/**
		 * @return the tmbh
		 */
		public ArrayList<Integer> getTmbh() {
			return tmbh;
		}


		/**
		 * @param tmbh the tmbh to set
		 */
		public void setTmbh(ArrayList<Integer> tmbh) {
			this.tmbh = tmbh;
		}


		/**
		 * @param x the x to set
		 */
		public void setX(ArrayList<Integer> x) {
			this.x = x;
		}


		/**
		 * @return the y
		 */
		public ArrayList<Integer> getY() {
			return y;
		}


		/**
		 * @param y the y to set
		 */
		public void setY(ArrayList<Integer> y) {
			this.y = y;
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


		@Action(value="/save2aa",results={@Result(name="success",type="json")})
		public String execute(){
			Dzbj d = new Dzbj();


			
			
			HttpSession hs = ServletActionContext.getRequest().getSession();
			String userid = (String)hs.getAttribute("uid");
//			
//			String[] keys0 = new String[1];
//			keys0[0] = "zlmc";
//			Object[] values0 = new Object[1];
//			values0[0] = zlmc;
//			List<Integer> zlbh = this.baseService.find(Integer.class, "Dmtzl", "zlbh", keys0, values0);
//			String zlid = zlbh.get(0).toString();
			
			String hql = "from Dzbj d where d.id.userId='"+userid+"' and d.biaojilx='juxing' and d.zlid='"+zlmc+"' and d.weizhi='"+currentpage+"'";
			list = this.baseService.findHql(Dzbj.class, hql);
			if(list.size()!=0){
			System.out.println(list.size());
			for(int i=0;i<list.size();i++){
				
				d = (Dzbj) list.get(i);
				x.add(d.getZuobiaoX());
				y.add(d.getZuobiaoY());
				x1.add(d.getZuobiaoX1());
				y1.add(d.getZuobiaoY1());
				color.add(d.getBiaojiys());
				source.add(d.getPath());
				note.add(d.getTmnr());
				tmbh.add(d.getId().getTmbh());
				classnoclass=d.getId().getClassno();
				
			}
			}
		
			System.out.println(tmbh);
			//String h="select max(d.tmbh) from Dzbj d";
			String hql2 ="select max(dz.id.tmbh) from Dzbj dz";
			List l=this.baseService.findHql(Dzbj.class,hql2);
			//l.get(0);
			//System.out.println("hhhhhhhhhhhhhhhh");
			if(l==null){
				
				num=0;
				
			}else{
			num=Integer.parseInt(l.get(0)+"");
			}
			//System.out.println(num+"");
			return "success";
			
		}
}
