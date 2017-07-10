/*
 *@(#)xx.quanxian.collection.action
 *@queryAction.java.java  
 *@创建时间:2011-9-20下午02:43:27
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.quanxian.collection.action;

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

import xx.adminservice.AdminService;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @queryAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class queryAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService service;
	@Resource(name="adminService")
	private AdminService adminservice;
	private List<Zycxdto> rows= new ArrayList<Zycxdto>();
	private int total;//数据总数total
	private int page;//当前页数
	private int rows_s;//每页显示row_s条数据
	private static String mc;
	private static String lx;
	private String zsd_mc;
	//资源查询
	private String zyqueryword;
	private String radio;
	private String QueryWord;
	private int Radio;
	private String q;
	@JSON(serialize=false)
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	@JSON(serialize=false)
	public AdminService getAdminservice() {
		return adminservice;
	}
	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}
	
	//资源查询
	@JSON(serialize=false)
	public String getZyqueryword() {
		return zyqueryword;
	}
	public void setZyqueryword(String zyqueryword) {
		this.zyqueryword = zyqueryword;
	}
	@JSON(serialize=false)
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public List<Zycxdto> getRows() {
		return rows;
	}
	public void setRows(List<Zycxdto> rows) {
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
	@JSON(serialize=false)
	public static String getMc() {
		return mc;
	}
	public static void setMc(String mc) {
		queryAction.mc = mc;
	}
	@JSON(serialize=false)
	public static String getLx() {
		return lx;
	}
	public static void setLx(String lx) {
		queryAction.lx = lx;
	}
	@JSON(serialize=false)
	public String getZsd_mc() {
		return zsd_mc;
	}
	public void setZsd_mc(String zsd_mc) {
		this.zsd_mc = zsd_mc;
	}
	/**
	 * @{xuanze.action}
	 * 该方法是用来接收查询条件的
	 */
	@Action(value="/zy",results={@Result(name="success",type="json")})
	public String zy(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		session1.setAttribute("querywordzy", zyqueryword);
		session1.setAttribute("radiozy", radio);
		return SUCCESS;
	}
	/**
	 * @{xuanze.action}
	 * 该方法是用来跳转页面
	 */
	@Action(value="/zy_tz",results={@Result(name="success",location="/page/resource/findresult.jsp")})
	public String zytz(){
		return SUCCESS;
	}
	/**
	 * @{xuanze.action}
	 * 该方法是用来显示查询结果页面的
	 */
	@Action(value="/cxresult",results={@Result(name="success",type="json")})
	public String cxresult(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		QueryWord = "%"+(String) session1.getAttribute("querywordzy")+"%";
		Radio = Integer.parseInt((String) session1.getAttribute("radiozy"));
		int xzsl = 0;
		int pdsl = 0;
		int cztsl = 0;
		//调用存储过程
		List<Integer> ll = this.adminservice.zycxto(Radio, QueryWord);
		Zycxdto xzt = new Zycxdto();
		xzt.setLxmc("选择题");
		xzt.setLxsl(ll.get(0));
		Zycxdto pdt = new Zycxdto();
		pdt.setLxmc("判断题");
		pdt.setLxsl(ll.get(1));
		Zycxdto cztt = new Zycxdto();
		cztt.setLxmc("操作题");
		cztt.setLxsl(ll.get(2));
		
		Zycxdto dmt = new Zycxdto();
		
		dmt.setLxmc("多媒体资料");
		dmt.setLxsl(ll.get(3));
		
		rows.add(xzt);
		rows.add(pdt);
		rows.add(cztt);
		rows.add(dmt);
		return SUCCESS;
	}

	/**
	 * @Statistics <code>{zycx}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-9-20下午02:43:27}
	 * @{实现资源查询功能} 
	 */
	/*@Action(value="/ceshi",results={@Result(name="success",type="json")})
	public String zycx(){
		zsd_mc = mc;
		//System.out.println("asdfs");
		//System.out.println(mc);
		List<String> lx = new ArrayList<String>();
		List<Integer> total_list = new ArrayList<Integer>();
		int a=1;
		Cxjg cxjg = new Cxjg();
		lx.add("tk");
		lx.add("xz");
		lx.add("pd");
		lx.add("dmt");
		lx.add("czt");
		for(String s:lx){
			Connection connection = AdminDaoImpl.getConnection();
			  try{
			   ResultSet rs = null;
			   // 设置调用的存储过程名
			   CallableStatement proc = connection.prepareCall("{ call query_findSource(?,?)}");
			   // 调入存储过程，传递参数
			   proc.setString(1,zsd_mc);
			   proc.setString(2,s);
			   proc.execute();
			   // 取出存储过程的结果集
			   rs = proc.getResultSet();
			   boolean hasResult = true;
			   if(rs==null){
				   hasResult=false;
			   }else hasResult=true;
			   while (hasResult) {
			         
			         while(rs!=null&&rs.next()){
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
	//=======计算total=================================================
			  int t = 0;
			  Connection cnt = AdminDaoImpl.getConnection();
			  try{
			   ResultSet rs = null;
			   // 设置调用的存储过程名
			   CallableStatement proc = cnt.prepareCall("{ call query_total(?,?)}");
			   // 调入存储过程，传递参数
			   proc.setString(1,zsd_mc);
			   proc.setString(2,s);
			   proc.execute();
			   // 取出存储过程的结果集
			   rs = proc.getResultSet();
			   boolean hr = true;
			   if(rs==null){
				   hr=false;
			   }else hr=true;
			   while (hr) {
			        
			         while(rs.next()){
			        	t = rs.getInt(1);
			         }
			         hr = proc.getMoreResults();
			   }
			   connection.close();
			  }catch (SQLException e){
			   e.printStackTrace();

			  }
			  total_list.add(t);
		}
		Cxjg cxjg_tk = new Cxjg();
		Cxjg cxjg_xz = new Cxjg();
		Cxjg cxjg_pd = new Cxjg();
		Cxjg cxjg_dmt = new Cxjg();
		Cxjg cxjg_czt = new Cxjg();
		cxjg_tk.setId(1);cxjg_tk.setLx("填空题");cxjg_tk.setState("closed");
		cxjg_xz.setId(2);cxjg_xz.setLx("选择题");cxjg_xz.setState("closed");
		cxjg_pd.setId(3);cxjg_pd.setLx("判断题");cxjg_pd.setState("closed");
		cxjg_dmt.setId(4);cxjg_dmt.setLx("多媒体资料");cxjg_dmt.setState("closed");
		cxjg_czt.setId(5);cxjg_czt.setLx("操作题");cxjg_czt.setState("closed");
		rows.add(cxjg_tk);
		rows.add(cxjg_xz);
		rows.add(cxjg_pd);
		rows.add(cxjg_dmt);
		rows.add(cxjg_czt);
		total = total_list.get(0)+total_list.get(1)+total_list.get(2)+total_list.get(3)+total_list.get(4);
		return SUCCESS;
	}*/
	/**
	 * @Statistics <code>{zycxtz}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-9-20下午02:43:27}
	 * @{实现跳转功能} 
	 */
	/*@Action(value="/zycx_tz",results={@Result(name="success",type="json")})
	public String zycxtz(){
		List<Jie> list = this.service.findHql(Jie.class, "from Jie j where j.zmc like '%"+zsd_mc+"%'");
		System.out.println(list.size());
		System.out.println(list.size());
		this.mc = list.get(0).getZmc();
		//System.out.println(mc);
		//System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		return SUCCESS;
	}*/
	/**
	 * @Statistics <code>{list_zycx}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-9-20下午02:43:27}
	 * @{实现跳转功能} 
	 */
	/*@Action(value="/list_zycx",results={@Result(name="success",location="/page/resource/treefind.jsp")})
	public String listzycx(){
		return SUCCESS;
	}*/
	/**
	 * @Statistics <code>{list_zycx}</code>
	 * @author  {徐鹏飞}
	 * @version {2011-9-20下午02:43:27}
	 * @{实现跳转功能} 
	 */
	@Action(value="/zysearch",results={@Result(name="success",location="/page/resource/zysearch.jsp")})
	public String zysearch(){
		return SUCCESS;
	}
}
