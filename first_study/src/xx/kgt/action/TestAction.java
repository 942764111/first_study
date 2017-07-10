/*
 *@(#)xx.kgt.action
 *@XzTestAction.java.java  
 *@创建时间:2011-8-9上午09:20:58
 *@作者：张晓莉
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.kgt.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import xx.collection.bean.Pd;
import xx.collection.bean.Xz;
import xx.kgt.bean.Da;
import xx.kgt.bean.PanDuan;
import xx.kgt.bean.XuanZ;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @XzTestAction <code>{类名称}</code>
 * @author  {张哓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class TestAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Xz> xzlist = new ArrayList<Xz>();
	private List<XuanZ> xzlist1=new ArrayList<XuanZ>();
	private List<XuanZ> xzlist2=new ArrayList<XuanZ>();
	private Integer ddx;//判断单、多选时用
	private List<String> da1;//用于接收选择题题目正确的答案。
	private List<String> tg1=new ArrayList<String>();
	
	
	private List<Pd> pdlist=new ArrayList<Pd>();
	private List<PanDuan> pdlist1=new ArrayList<PanDuan>();
	private List<PanDuan> panduan=new ArrayList<PanDuan>();
	private List<Integer> da2;//用于接收判断题目正确的答案。
	private List<String> tg;
	
	private List<Da> xzd=new ArrayList<Da>();//用来保存提交的答案以及对应的题目、题目的答案等
	private List<Da> templist1=new ArrayList<Da>();
	private List<Da> templist2=new ArrayList<Da>();
	private List<List> answer=new ArrayList<List>();//用于接收前台传来的测试者的答案。
	private int score = 0;//初始化得分
	private int zql;//所做题目的正确率
	private List<Integer> zsdz=new ArrayList<Integer>();//知识点正确率
	private List<Integer> zsdbh1;//用于接收知识点编号 
	private List<String> zsdmc1=new ArrayList<String>();
	private List<String> zsdmc2=new ArrayList<String>();//把知识点名称传给前台
	private List<Integer> zsdz2=new ArrayList<Integer>();//把知识点正确率传给前台
	private int page=1;//初始页
	private String zmc;//节名
	private String CName;//章名
	private String TCName;//课程名
	private String zsdmc;
	private int total;//总的题数
	private int a;//传给前台，以a的值判断选择范围内是否有题
	private int zy;//总页
	private int pages;
	private int pagex;
	private int paget;
	private int p;
	private int b;
	private int x;//用来判断进行的操作是重新选题、下一页、上一页还是跳转
	private int tip;//用来标志使用哪一种查询测试题目的方式
	
	private int zbh=0;
	private int jbh=0;
	Cookie cookie = null;
	Cookie cookiep = null;
	


	public int getZbh() {
		return zbh;
	}
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}
	public int getJbh() {
		return jbh;
	}
	public void setJbh(int jbh) {
		this.jbh = jbh;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPagex() {
		return pagex;
	}
	public void setPagex(int pagex) {
		this.pagex = pagex;
	}
	public int getPaget() {
		return paget;
	}
	public void setPaget(int paget) {
		this.paget = paget;
	}
	public int getZy() {
		return zy;
	}
	public void setZy(int zy) {
		this.zy = zy;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(serialize=false)
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	@JSON(serialize=false)
	public String getCName() {
		return CName;
	}
	public void setCName(String name) {
		CName = name;
	}
	@JSON(serialize=false)
	public String getTCName() {
		return TCName;
	}
	public void setTCName(String name) {
		TCName = name;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public List<Integer> getZsdbh1() {
		return zsdbh1;
	}
	public void setZsdbh1(List<Integer> zsdbh1) {
		this.zsdbh1 = zsdbh1;
	}
	
	public List<String> getZsdmc1() {
		return zsdmc1;
	}
	public void setZsdmc1(List<String> zsdmc1) {
		this.zsdmc1 = zsdmc1;
	}
	@JSON(serialize=false)
	public List<String> getZsdmc2() {
		return zsdmc2;
	}
	public void setZsdmc2(List<String> zsdmc2) {
		this.zsdmc2 = zsdmc2;
	}
	@JSON(serialize=false)
	public List<Integer> getZsdz2() {
		return zsdz2;
	}
	public void setZsdz2(List<Integer> zsdz2) {
		this.zsdz2 = zsdz2;
	}
	@JSON(serialize=false)
	public List<String> getDa1() {
		return da1;
	}
	public void setDa1(List<String> da1) {
		this.da1 = da1;
	}
	@JSON(serialize=false)
	public List<Integer> getDa2() {
		return da2;
	}
	public void setDa2(List<Integer> da2) {
		this.da2 = da2;
	}
	@JSON(serialize=false)
	public List<String> getTg() {
		return tg;
	}
	public void setTg(List<String> tg) {
		this.tg = tg;
	}
	@JSON(serialize=false)
	public List<Pd> getPdlist() {
		return pdlist;
	}
	public void setPdlist(List<Pd> pdlist) {
		this.pdlist = pdlist;
	}
	@JSON
	public List<PanDuan> getPdlist1() {
		return pdlist1;
	}
	public void setPdlist1(List<PanDuan> pdlist1) {
		this.pdlist1 = pdlist1;
	}
	@JSON(serialize=false)
	public int getZql() {
		return zql;
	}
	public void setZql(int zql) {
		this.zql = zql;
	}
	@JSON(serialize=false)
	public List<Integer> getZsdz() {
		return zsdz;
	}
	public void setZsdz(List<Integer> zsdz) {
		this.zsdz = zsdz;
	}
	@JSON(serialize=false)
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@JSON(serialize=false)
	public List<List> getAnswer() {
		return answer;
	}
	public void setAnswer(List<List> answer) {
		this.answer = answer;
	}
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	@JSON(serialize=false)
	public List<Xz> getXzlist() {
		return xzlist;
	}
	public void setXzlist(List<Xz> xzlist) {
		this.xzlist = xzlist;
	}
	
	@JSON
	public List<XuanZ> getXzlist1() {
		return xzlist1;
	}
	public void setXzlist1(List<XuanZ> xzlist1) {
		this.xzlist1 = xzlist1;
	}
	
	
	@JSON(serialize=false)
	public Integer getDdx() {
		return ddx;
	}
	public void setDdx(Integer ddx) {
		this.ddx = ddx;
	}
	public String getZsdmc() {
		return zsdmc;
	}
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	
	
	
//**********************选择题测试的功能*****************************
	
	/**
	 * @{xzcs2.action}
	 * 该方法是用来指定跳转到的页面是选择测试页面
	 */
	@Action(value="/xzcs2",results={@Result(name="root",type="json")})
	public String xzcs2(){
		Cookie[] cookies;
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		//获取请求相关的cookie
		cookies = request.getCookies();
		if (cookie == null){
			  cookie = new Cookie("VisitTimes","1");
			  response.addCookie(cookie);
			  }
		if(x==1){//如果重新选题, xzd清空.
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("VisitTimes")){
					cookies[i].setValue(Integer.toString(0));
				}
			}
			xzd=(List<Da>) session.getAttribute("templ2");
			if(xzd!=null){
				session.setAttribute("templ2", null);
			}
		}
		
		  //判断Cookie VisitTimes是否存在
		  //如果存在，其值加1
		  if (cookies != null){
			  for (int i = 0; i < cookies.length; i++){
				  if (cookies[i].getName( ).equals("VisitTimes")){
				  String v=cookies[i].getValue();
				  int value=Integer.parseInt(v)+1;
				  cookies[i].setValue(Integer.toString(value));
				  response.addCookie(cookies[i]);
				  cookie=cookies[i];
				  }  
			  }
		  }
		int tempp=Integer.parseInt(cookie.getValue());
		session.setAttribute("testzbh", 0);
		session.setAttribute("testjbh", 0);
		session.setAttribute("Zsdmc", "");
		session.setAttribute("testkcmc", null);
		session.setAttribute("testxz",null);
		session.setAttribute("zytemppage", tempp);
		session.setAttribute("tempx", x);
		//选择题
		if(zsdmc!=null&&!zsdmc.equals("")){
			session.setAttribute("Zsdmc", zsdmc);
		}else{
			session.setAttribute("Zsdmc", null);
			if(!TCName.equals("--请选择--")&&!zmc.equals("--请选择--")&&!CName.equals("--请选择--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", zbh);
				session.setAttribute("testjbh", jbh);
			}else if(!TCName.equals("--请选择--")&&zmc.equals("--请选择--")&&!CName.equals("--请选择--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", zbh);
				session.setAttribute("testjbh", 0);
			}else if(!TCName.equals("--请选择--")&&zmc.equals("--请选择--")&&CName.equals("--请选择--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", 0);
				session.setAttribute("testjbh", 0);
			}
		}
		String zsdmc1=(String)session.getAttribute("Zsdmc");
		int zbh1=(Integer)session.getAttribute("testzbh");
		int jbh1=(Integer)session.getAttribute("testjbh");
		String kcmc1=(String)session.getAttribute("testkcmc");
		List<Integer> zsdbh=new  ArrayList<Integer>();
		if(zsdmc1!=null&&!zsdmc1.equals("")){
			String[] keys1=new String[1];
			keys1[0]="zsdmc";
			Object[] values1=new Object[1];
			values1[0]=zsdmc1;
			zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys1, values1);
		}
		
		
		String hql1="";
		if(zsdmc1!=null&&!zsdmc1.equals("")){
			hql1="select count(*) from Xz where zsdbh="+zsdbh.get(0).toString();
			total=this.baseservice.getTotalSql(hql1);
		}else{
			if(jbh1!=0){
				hql1="select count(*) from Xz where zbh="+zbh1+ " and c_id="+jbh1;
				total=this.baseservice.getTotalSql(hql1);
			}else if(zbh1!=0&&jbh1==0){
				hql1="select count(*) from Xz where zbh="+zbh1;
				total=this.baseservice.getTotalSql(hql1);
			}else if(kcmc1!=null&&zbh1==0&&jbh1==0){
				String[] keys=new String[1];
				keys[0]="TCName";
				Object[] values=new Object[1];
				values[0]=kcmc1;
				List<Integer> zbh2=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
				hql1="select count(*) from Xz where zbh>='"+zbh2.get(0).toString()+ "' and zbh<='"+zbh2.get(zbh2.size()-1).toString()+"'";
				total=this.baseservice.getTotalSql(hql1);
			}
		}
		session.setAttribute("testtt", (int)Math.ceil((float)total/3));//凑数取整,总的页数
		zy=(int)Math.ceil((float)total/3);// 总页 
		
		 
		pagex=tempp+1;//用来判定是否已是最后一页
//		if(page==-1&&pagE>=2){
//			pagE=pagE-1;//上一页
//			pages=pagE;
//		}
//		else if(page==0&&pagE<zy&&pagE>0){
//			pagE=pagE+1;//下一页
//			pagex=pagE;
//		}else if(pagE<=zy&&pagE>=0&&page>0&&page<=zy){
//			pagE=page;//跳转到固定页
//			paget=page;
//		}
//		paget=page;//用来判定跳转时是否已超过最大页
		if(tempp<=zy&&tempp>=0){
			String hql="";
			if(zsdmc1!=null&&!zsdmc1.equals("")){
				hql="from Xz where zsdbh="+zsdbh.get(0).toString();
			}else{
				if(jbh1!=0){
					hql = "from Xz where zbh="+zbh1+ " and c_id="+jbh1;
				}else if(zbh1!=0&&jbh1==0){
					hql = "from Xz where zbh="+zbh1;
				}else if(kcmc1!=null&&zbh1==0&&jbh1==0){
					String[] keys=new String[1];
					keys[0]="TCName";
					Object[] values=new Object[1];
					values[0]=kcmc1;
					List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
					hql = "from Xz where  zbh>="+zbh.get(0).toString()+ " and zbh<="+zbh.get(zbh.size()-1).toString();
				}
			}
			
			if(total>0){//如果查出数据继续执行，否则返回a=0
				a=1;
				if(total>3){
					xzlist = this.baseservice.findSql(Xz.class, hql, tempp, 3);//分页查询,此时会带出多余的数据
				}else{
					xzlist=this.baseservice.findHql(Xz.class, hql);
				}
				for(int i=0;i<xzlist.size();i++){
		        	XuanZ element = new XuanZ();//利用xx.kgt.bean中的XuanZ.java来拿到需要在前台显示的数据。
		        	element.setTg(xzlist.get(i).getTg());      	
		        	element.setXx1(xzlist.get(i).getXx1());
		        	element.setXx2(xzlist.get(i).getXx2());
		        	element.setXx3(xzlist.get(i).getXx3());
		        	element.setXx4(xzlist.get(i).getXx4());
		        	element.setDa(xzlist.get(i).getDa());
		        	element.setZsdbh(xzlist.get(i).getZsd().getId().getZsdbh()); 
		        	element.setDdx(xzlist.get(i).getDdx());
		        	element.setZhangname(xzlist.get(i).getZsd().getJie().getCourseChapter().getCName());
		        	element.setTh(xzlist.get(i).getTh());
		        		xzlist2.add(element);   //此时的数据都是需要的数据
		        }
				session.setAttribute("testxz", xzlist2);
			}else{
				a=0;
			}
		}
		return "root";
	}
	
	
	//显示选择题的页面
	@Action(value="/xzcs3",results={@Result(name="root",location="/kgt/xztest.jsp")})
	public String xzcs3(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		zy=(Integer)session.getAttribute("testtt");
		p=(Integer)session.getAttribute("zytemppage");
		xzlist1=(List)session.getAttribute("testxz");
		session.removeAttribute("testxz");
		return "root";
	}
	
	//flex端的发送请求,显示选择题的页面
	@Action(value="/selectTest",results={@Result(name="root",type="json")})
	public String selectTest(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		zy=(Integer)session.getAttribute("testtt");
		p=(Integer)session.getAttribute("zytemppage");
		xzlist1=(List)session.getAttribute("testxz");
		session.removeAttribute("testxz");
		return "root";
	}
	
	//保存选择题答案(分页保存)
	@Action(value="/saveanswer",results={@Result(name="root",type="json")})
	public String saveanswer(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String an="";
		for(int b=0;b<answer.size();b++){//answer.size()是所选题的数目z。
			if(answer.get(b)!=null){
				String strResult="";
				int i = answer.get(b).toString().length();//其中i为3*[数组的长度，即元素的个数（所答每道题选中的个数）]		
				for(int a=0;a<i/3;a++){
					strResult+=answer.get(b).get(a);//把answer中的第b个元素里的a个数据连接起来。	
				}				
				an = strResult;//此时（在for循环外）的strResult才是b中所有元素的连接。
				Da da2 = new Da();
				da2.setXzanswer(da1.get(b));
				da2.setXzda(an);
				da2.setTg(tg.get(b));
				da2.setZsdbh(zsdbh1.get(b));
				String[] keys1=new String[1];
				keys1[0]="zsdbh";
				Object[] values1=new Object[1];
				values1[0]=zsdbh1.get(b);
				List<String> zsdmc=this.baseservice.find(String.class, "Zsd", "zsdmc", keys1, values1);
				da2.setZsdmc(zsdmc.get(0));
				if(templist1.size()==0){
					templist1.add(da2);
					tg1.add(tg.get(b));
				}else{
					if(!tg1.contains(tg.get(b))){
						templist1.add(da2);
						tg1.add(tg.get(b));
					}
				}
			}else{
				an = "";
			}
		}
		templist2.addAll(templist1);
		List<Da> templist3=new ArrayList<Da>();
		templist3=(List<Da>) hs.getAttribute("templ2");
		if(templist3!=null){
			templist2.addAll(templist3);
		}
		hs.setAttribute("templ2", templist2);
		return "root";
	}
	//选择题测试结果分析
	@Action(value="/xzfx3",results={@Result(name="root",location="/kgt/fx.jsp")})
	public String xzfx3(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String x1=(Integer)session.getAttribute("tempx")+"";
		xzd=(List<Da>) session.getAttribute("templ2");
		if(x1.equals("null")){
			xzd.clear();
			x=2;
		}
		if(xzd.size()!=0){
			for(int n=0;n<xzd.size();n++){
				if(xzd.get(n).getXzda().equals(xzd.get(n).getXzanswer())){
					score=score+1; //如果答案正确，分数加一.
				}
			}
			//所做题的正确率
			zql=Integer.parseInt(new java.text.DecimalFormat("0").format(score*100/xzd.size()));//得出正确率(其中的score即为答对的题数)
			//知识点正确率
			for(int c=0;c<xzd.size();c++){
				zsdmc1.add(xzd.get(c).getZsdmc());
				int t=0;
				int z=0;
				for(int e=0;e<xzd.size();e++){
					if(xzd.get(c).getZsdmc().equals(xzd.get(e).getZsdmc())){
						t=t+1;	//知识点相同的题数
						if(xzd.get(e).getXzda().equals(xzd.get(e).getXzanswer())){
							z=z+1;	//同一知识点正确的题数
						}
					}
				}
				int zz=0;
				zz =Integer.parseInt(new java.text.DecimalFormat("0").format(z*100/t));// 知识点正确率
						zsdz.add(zz);
			}
			//取消重复的数据 
			for(int x=0;x<xzd.size();x++){
				if(zsdmc2.size()==0){
					zsdmc2.add(xzd.get(x).getZsdmc());
					zsdz2.add(zsdz.get(x));
				}else if(!zsdmc2.contains(xzd.get(x).getZsdmc())){
					zsdmc2.add(xzd.get(x).getZsdmc());
					zsdz2.add(zsdz.get(x));
				}
			}
		}
		session.setAttribute("tempx", null);
		return "root";
	}	
	

//**********************判断题测试的功能***********************************
	/**
	 * @{pdcs.action}
	 * 该方法是用来指定跳转到的页面是判断测试页面
	 */
	//根据条件选题页面
	@Action(value="/pdcs",results={@Result(name="root",type="json")})
	public String pdcs(){
		//判断题
		Cookie[] cookiesp;
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		//获取请求相关的cookie
		cookiesp = request.getCookies();
		if (cookiep == null){
			cookiep = new Cookie("VisitTimesp","1");
			  response.addCookie(cookiep);
			  }
		if(x==1){//如果重新选题, xzd清空.
			for(int i=0;i<cookiesp.length;i++){
				if(cookiesp[i].getName().equals("VisitTimesp")){
					cookiesp[i].setValue(Integer.toString(0));
				}
			}
			xzd=(List<Da>) session.getAttribute("temppdlist");
			if(xzd!=null){
				session.setAttribute("templ2", null);
			}
		}
		
		  //判断Cookie VisitTimes是否存在
		  //如果存在，其值加1
		  if (cookiesp != null){
			  for (int i = 0; i < cookiesp.length; i++){
				  if (cookiesp[i].getName( ).equals("VisitTimesp")){
				  String v=cookiesp[i].getValue();
				  int value=Integer.parseInt(v)+1;
				  cookiesp[i].setValue(Integer.toString(value));
				  response.addCookie(cookiesp[i]);
				  cookiep=cookiesp[i];
				  }  
			  }
		  }
		 int tempp=Integer.parseInt(cookiep.getValue());
		 
		
		session.setAttribute("testzbh", 0);
		session.setAttribute("testjbh", 0);
		session.setAttribute("Zsdmc", "");
		session.setAttribute("testkcmc", null);
		session.setAttribute("testxz",null);
		session.setAttribute("zytemppagep", tempp);
		session.setAttribute("temppd", x);
		
		if(zsdmc!=null&&!zsdmc.equals("")){
			session.setAttribute("Zsdmc", zsdmc);
		}else{
			session.setAttribute("Zsdmc", null);
			if(!TCName.equals("--请选择--")&&!zmc.equals("--请选择--")&&!CName.equals("--请选择--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", zbh);
				session.setAttribute("testjbh", jbh);
			}else if(!TCName.equals("--请选择--")&&zmc.equals("--请选择--")&&!CName.equals("--请选择--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", zbh);
				session.setAttribute("testjbh", 0);
			}else if(!TCName.equals("--请选择--")&&zmc.equals("--请选择--")&&CName.equals("--请选择--")){
				session.setAttribute("testkcmc", TCName);
				session.setAttribute("testzbh", 0);
				session.setAttribute("testjbh", 0);
			}
		}
		String zsdmc1=(String)session.getAttribute("Zsdmc");
		int zbh1=(Integer)session.getAttribute("testzbh");
		int jbh1=(Integer)session.getAttribute("testjbh");
		String kcmc1=(String)session.getAttribute("testkcmc");
		List<Integer> zbh=new  ArrayList<Integer>();
		List<Integer> zsdbh=new  ArrayList<Integer>();
		if(zsdmc1!=null&&!zsdmc1.equals("")){
			String[] keys1=new String[1];
			keys1[0]="zsdmc";
			Object[] values1=new Object[1];
			values1[0]=zsdmc1;
			zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys1, values1);
		}
		
		String hql1="";
		if(zsdmc1!=null&&!zsdmc1.equals("")){
			hql1="select count(*) from Pd where zsdbh="+zsdbh.get(0).toString();
			total=this.baseservice.getTotalSql(hql1);
		}else{
			if(jbh1!=0){
				hql1="select count(*) from Pd where zbh="+zbh1+ " and c_id="+jbh1;
				total=this.baseservice.getTotalSql(hql1);
			}else if(zbh1!=0&&jbh1==0){
				hql1="select count(*) from Pd where zbh="+zbh1;
				total=this.baseservice.getTotalSql(hql1);
			}else if(kcmc1!=null&&zbh1==0&&jbh1==0){
				String[] keys=new String[1];
				keys[0]="TCName";
				Object[] values=new Object[1];
				values[0]=kcmc1;
				List<Integer> zbh2=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
				hql1="select count(*) from Pd where zbh>='"+zbh2.get(0).toString()+ "' and zbh<='"+zbh2.get(zbh2.size()-1).toString()+"'";
				total=this.baseservice.getTotalSql(hql1);
			}
		}
		session.setAttribute("testtt", (int)Math.ceil((float)total/3));//凑数取整,总的页数
		zy=(int)Math.ceil((float)total/3);// 总页 
		
		pagex=tempp+1;//用来判定是否已是最后一页
//		if(page==0&&pagE<zy&&pagE>0){
//			pagE=pagE+1;//下一页
//		}else if(pagE<=zy&&pagE>=0&&page>0&&page<=zy){
//			pagE=page;
//		}
//		pagex=pagE+1;//用来判定已是最后一页
//		if(page==-1&&pagE>=2){
//			pagE=pagE-1;//上一页
//			pages=pagE;
//		}else if(page==0&&pagE<zy&&pagE>0){
//			pagE=pagE+1;//下一页
//			pagex=pagE;
//		}else if(pagE<=zy&&pagE>=0&&page>0&&page<=zy){
//			pagE=page;//跳转到固定页
//			paget=page;
//		}
//		paget=page;//用来判定跳转时已超过最大页
		if(tempp<=zy&&tempp>=0){
			String hql="";
			if(zsdmc1!=null&&!zsdmc1.equals("")){
				hql="from Pd where zsdbh="+zsdbh.get(0).toString();
			}else{
				if(jbh1!=0){
					hql = "from Pd where zbh="+zbh1+ " and c_id="+jbh1;
				}else if(zbh1!=0&&jbh1==0){
					hql = "from Pd where zbh="+zbh1;
				}else if(kcmc1!=null&&zbh1==0&&jbh1==0){
					String[] keys=new String[1];
					keys[0]="TCName";
					Object[] values=new Object[1];
					values[0]=kcmc1;
					zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
					hql = "from Pd where  zbh>="+zbh.get(0).toString()+ " and zbh<="+zbh.get(zbh.size()-1).toString();
				}
			}
			
			if(total>0){//如果查出数据继续执行，否则返回a=0
				a=1;
				if(total>3){
					pdlist = this.baseservice.findSql(Pd.class, hql, tempp, 3);//分页查询,此时会带出多余的数据
				}else{
					pdlist=this.baseservice.findHql(Pd.class, hql);
				}
				for(int i=0;i<pdlist.size();i++){
		        	PanDuan element = new PanDuan();//利用xx.kgt.bean中的PanDuan.java来拿到需要在前台显示的数据。
		        	element.setStr1(pdlist.get(i).getTg());      	
		        	element.setInt1(pdlist.get(i).getDa());
		        	element.setInt2(pdlist.get(i).getZsd().getId().getZsdbh()); 
		        	element.setZmc(pdlist.get(i).getZsd().getJie().getCourseChapter().getCName());
		        	element.setInt4(pdlist.get(i).getTh());
		        	panduan.add(element);   //此时的数据都是需要的数据
		        }
				session.setAttribute("testpd", panduan);
			}else{
				a=0;
			}
		}
		return "root";
	}
	
	
	//查询所有知识点名称(自动完成时用)
	@Action(value="/queryzsdmc",results={@Result(name="root",type="json")})
	public String queryzsdmc(){
		String hql="select zsdmc from Zsd";
		zsdmc1=this.baseservice.findHql(String.class, hql);
		return "root";
	}
	
	
	
//显示判断题的页面 
	@Action(value="/pdcs1",results={@Result(name="root",location="/kgt/pdtest.jsp")})
	public String pdcs1(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		zy=(Integer)session.getAttribute("testtt");
		pdlist1=(List)session.getAttribute("testpd");
		p=(Integer)session.getAttribute("zytemppagep");
		return "root";
	}
	
	//显示判断题的页面 
	@Action(value="/pdTest",results={@Result(name="root",type="json")})
	public String pdTest(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		zy=(Integer)session.getAttribute("testtt");
		pdlist1=(List)session.getAttribute("testpd");
		p=(Integer)session.getAttribute("zytemppagep");
		return "root";
	}
	
//保存判断题答案(分页保存)
	@Action(value="/saveanswerp",results={@Result(name="root",type="json")})
	public String saveanswerp(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		for(int b=0;b<answer.size();b++){//answer.size()是所选题的数目。
			if(answer.get(b)!=null){
				Da da3 = new Da();
				da3.setPdanswer(da2.get(b));//正真的答案
				da3.setPdda(Integer.parseInt(answer.get(b).get(0).toString()));//提交的答案
				da3.setTg(tg.get(b));
				da3.setZsdbh(zsdbh1.get(b));
				//由zsdbh得出zsdmc
				String[] keys1=new String[1];
				keys1[0]="zsdbh";
				Object[] values1=new Object[1];
				values1[0]=zsdbh1.get(b);
				List<String> zsdmc=this.baseservice.find(String.class, "Zsd", "zsdmc", keys1, values1);
				
				da3.setZsdmc(zsdmc.get(0));
				System.out.println(tg.get(b));
				System.out.println(Integer.parseInt(answer.get(b).get(0).toString()));
				System.out.println(da2.get(b));
				System.out.println(zsdbh1.get(b));
				
				if(templist1.size()==0){
					templist1.add(da3);
					tg1.add(tg.get(b));
				}else{
					if(!tg1.contains(tg.get(b))){
						templist1.add(da3);
						tg1.add(tg.get(b));
					}
				}
//				if(xzd.size()==0){
//					xzd.add(da3);
//					tg1.add(tg.get(b));
//				}else{
//					if(!tg1.contains(tg.get(b))){
//						xzd.add(da3);
//						tg1.add(tg.get(b));
//					}
//				}
			}
		}
		templist2.addAll(templist1);
		List<Da> templist3=new ArrayList<Da>();
		templist3=(List<Da>) hs.getAttribute("temppdlist");
		if(templist3!=null){
			templist2.addAll(templist3);
		}
		hs.setAttribute("temppdlist", templist2);
		return "root";
	}	
	
	//判断题测试结果分析
	@Action(value="/pdfx3",results={@Result(name="root",location="/kgt/fx.jsp")})
	public String pdfx3(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String x1=(Integer)session.getAttribute("temppd")+"";
		xzd=(List<Da>) session.getAttribute("temppdlist");
		if(x1.equals("null")){
			xzd.clear();
			x=2;
		}
		if(xzd.size()!=0){
			for(int n=0;n<xzd.size();n++){
				if(xzd.get(n).getPdda()==(xzd.get(n).getPdanswer())){
					score=score+1; //如果答案正确，分数加一.
				}
			}
			//所做题的正确率
			zql=Integer.parseInt(new java.text.DecimalFormat("0").format(score*100/xzd.size()));//得出正确率(其中的score即为答对的题数)
			
			//知识点正确率
			for(int c=0;c<xzd.size();c++){
				zsdmc1.add(xzd.get(c).getZsdmc());
				int t=0;
				int z=0;
				for(int e=0;e<xzd.size();e++){
					if(xzd.get(c).getZsdmc().equals(xzd.get(e).getZsdmc())){
						t=t+1;	//知识点相同的题数
						if(xzd.get(e).getPdda()==(xzd.get(e).getPdanswer())){
							z=z+1;	//同一知识点正确的题数
						}
					}
				}
				int zz=0;
				zz =Integer.parseInt(new java.text.DecimalFormat("0").format(z*100/t));// 知识点正确率
						zsdz.add(zz);
			}
			//取消重复的数据 
			for(int x=0;x<xzd.size();x++){
				if(zsdmc2.size()==0){
					zsdmc2.add(xzd.get(x).getZsdmc());
					zsdz2.add(zsdz.get(x));
				}else if(!zsdmc2.contains(xzd.get(x).getZsdmc())){
					zsdmc2.add(xzd.get(x).getZsdmc());
					zsdz2.add(zsdz.get(x));
				}
			}
		}
		session.setAttribute("temppd", null);
		return "root";
	}		
}
