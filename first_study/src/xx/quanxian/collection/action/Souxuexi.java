/*
 *@(#)xx.quanxian.collection.action
 *@Souxuexi.java.java  
 *@创建时间:2011-10-17下午03:04:09
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.collection.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.admindao.AdminDaoImpl;
import xx.collection.bean.Cxjg;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Souxuexi <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class Souxuexi extends ActionSupport {
	@Resource(name="baseService")
	private BaseService service;
	private List<Cxjg> rows= new ArrayList<Cxjg>();
	private static String mc;
	private static String lx;
	private String zsd_mc;
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	public List<Cxjg> getRows() {
		return rows;
	}
	public void setRows(List<Cxjg> rows) {
		this.rows = rows;
	}
	@JSON(serialize=false)
	public static String getMc() {
		return mc;
	}
	public static void setMc(String mc) {
		Souxuexi.mc = mc;
	}
	@JSON(serialize=false)
	public static String getLx() {
		return lx;
	}
	public static void setLx(String lx) {
		Souxuexi.lx = lx;
	}
	@JSON(serialize=false)
	public String getZsd_mc() {
		return zsd_mc;
	}
	public void setZsd_mc(String zsd_mc) {
		this.zsd_mc = zsd_mc;
	}
	@Action(value="/souxuexi",results={@Result(name="success",type="json")})
	public String souxuex(){
		zsd_mc = mc;
		List<String> lx = new ArrayList<String>();
		List<Integer> total_list = new ArrayList<Integer>();
		int a=1;
//		Cxjg cxjg = new Cxjg();
//		lx.add("tk");
//		lx.add("xz");
//		lx.add("pd");
//		lx.add("dmt");
//		lx.add("czt");
//		for(String s:lx){
			Connection connection = AdminDaoImpl.getConnection();
			  try{
			   ResultSet rs = null;
			   // 设置调用的存储过程名
			   CallableStatement proc = connection.prepareCall("{ call query_findSource(?,?)}");
			   // 调入存储过程，传递参数
//			   proc.setString(1,zsd_mc);
			   String s = "xz"; 
			   proc.setString(2,s);
			   proc.setString(1,"信息的");
			   proc.execute();
			   // 取出存储过程的结果集
			   rs = proc.getResultSet();
			   boolean hasResult = true;
			   if(rs==null){
				   hasResult=false;
			   }else hasResult=true;
			   while (hasResult) {
			         while(rs.next()){
			        		if(s.equals("tk")){
			        			Cxjg cxjg1 = new Cxjg();
			        			cxjg1.setId(6+a);
//			        			cxjg1.setLx("填空题");
			        			cxjg1.setNo(rs.getInt(1));
			        			cxjg1.setTg(rs.getString(5));
			        			cxjg1.set_parentId(1);
			        			rows.add(cxjg1);
			        			a++;
			        		}else if(s.equals("czt")){
			        			Cxjg cxjg2 = new Cxjg();
			        			cxjg2.setId(100+a);
//			        			cxjg2.setLx("操作题");
			        			cxjg2.setNo(rs.getInt(1));
			        			cxjg2.setTg(rs.getString(3));
			        			cxjg2.set_parentId(5);
			        			rows.add(cxjg2);
			        			a++;
						    }else if(s.equals("xz")){
						    	Cxjg cxjg3 = new Cxjg();
						    	cxjg3.setId(200+a);
//						    	cxjg3.setLx("选择题");
						    	cxjg3.setNo(rs.getInt(1));
						    	cxjg3.setTg(rs.getString(5));
						    	cxjg3.set_parentId(2);
						    	rows.add(cxjg3);
						    	a++;
						    }else if(s.equals("pd")){
						    	Cxjg cxjg4 = new Cxjg();
						    	cxjg4.setId(300+a);
//						    	cxjg4.setLx("判断题");
						    	cxjg4.setNo(rs.getInt(1));
						    	cxjg4.setTg(rs.getString(5));
						    	cxjg4.set_parentId(3);
						    	rows.add(cxjg4);
						    	a++;
						    }else if(s.equals("dmt")){
						    	Cxjg cxjg5 = new Cxjg();
						    	cxjg5.setId(400+a);
//						    	cxjg5.setLx("多媒体资料");
						    	cxjg5.setNo(rs.getInt(1));
						    	cxjg5.setTg(rs.getString(5));
						    	cxjg5.set_parentId(4);
						    	rows.add(cxjg5);
						    	a++;
						    }
			         }
			         hasResult = proc.getMoreResults();
			   }
			   connection.close();
			  }catch (SQLException e){
			   e.printStackTrace();

			  }
//		}
//		Cxjg cxjg_tk = new Cxjg();
//		Cxjg cxjg_xz = new Cxjg();
//		Cxjg cxjg_pd = new Cxjg();
//		Cxjg cxjg_dmt = new Cxjg();
//		Cxjg cxjg_czt = new Cxjg();
//		cxjg_tk.setId(1);cxjg_tk.setLx("填空题");cxjg_tk.setState("closed");
//		cxjg_xz.setId(2);cxjg_xz.setLx("选择题");cxjg_xz.setState("closed");
//		cxjg_pd.setId(3);cxjg_pd.setLx("判断题");cxjg_pd.setState("closed");
//		cxjg_dmt.setId(4);cxjg_dmt.setLx("多媒体资料");cxjg_dmt.setState("closed");
//		cxjg_czt.setId(5);cxjg_czt.setLx("操作题");cxjg_czt.setState("closed");
//		rows.add(cxjg_tk);
//		rows.add(cxjg_xz);
//		rows.add(cxjg_pd);
//		rows.add(cxjg_dmt);
//		rows.add(cxjg_czt);
		return SUCCESS;
	}
	@Action(value="/souxuexi_tz",results={@Result(name="success",type="json")})
	public String souxuexi_tz(){
		this.mc = zsd_mc;
		System.out.println("tttttttttttttttttttttttttttttttt");
		return SUCCESS;
	}
	/**
	 * @Statistics <code>{list_zycx}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-9-20下午02:43:27}
	 * @{实现跳转功能} 
	 */
	@Action(value="/list_souxuexi",results={@Result(name="success",location="/page/resource/treexuexi.jsp")})
	public String list_souxuexi(){
		return SUCCESS;
	}
}
