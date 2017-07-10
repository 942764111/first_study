/*
 *@(#)xx.tables.action
 *@ZsdActions.java.java  
 *@创建时间:2011-8-4上午10:24:01
 *@作者：ylj
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.tables.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Pd;
import xx.collection.bean.Tk;
import xx.collection.bean.Xz;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.CztdDto;
import xx.collection.bean.Cztda;
import xx.collection.bean.Jie;
import xx.collection.bean.JieId;
import xx.collection.bean.Zsd;
import xx.collection.bean.ZsdId;
import xx.directory.service.directoryService;

import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ZsdActions <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class ZsdActions extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	@Resource(name="directoryService")
	private directoryService directoryService; 
	private List<Zsd> zsd = new ArrayList<Zsd>();
	private List rows= new ArrayList();
	private List<Zsd> rows1 = new ArrayList<Zsd>();
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private int total;//记录数据条数
	private ZsdId id1;
	private Jie jie;
	private String zsdmc;
	private String zsdms;
	private Integer sfzd;
	private Integer sfdn;
	private String zsdkey;
    private String CName;
    //添加的字段
    private String TCName;
	private Integer zsdbh;
	private Integer zbh;
	private int CId;
	private JieId id2;
	private List<Zsd>zsdlist;
	private CourseChapter coursechapter;
    private String zmc;
    private String zsd_lx;
    private String zsdtype;
    private String zsdword;
    private List<String> zsdjy=new ArrayList<String>();
    private int b;
    private int a;
	private static int total1=0;
	
	
	
	//添加
	public String getTCName() {
		return TCName;
	}
	
	public void setTCName(String name) {
		TCName = name;
	}
	
    public int getA() {
		return a;
	}
	
	
	public void setA(int a) {
		this.a = a;
	}
	public static int getTotal1() {
		return total1;
	}
	public static void setTotal1(int total1) {
		ZsdActions.total1 = total1;
	}
	@JSON(serialize=false)
    public List<String> getZsdjy() {
		return zsdjy;
	}
	public void setZsdjy(List<String> zsdjy) {
		this.zsdjy = zsdjy;
	}
	@JSON(serialize=false)
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public String getZsdword() {
		return zsdword;
	}
	public void setZsdword(String zsdword) {
		this.zsdword = zsdword;
	}
	@JSON(serialize=false)
    public String getZsdtype() {
		return zsdtype;
	}
	public void setZsdtype(String zsdtype) {
		this.zsdtype = zsdtype;
	}
	@JSON(serialize=false)
	public String getZsd_lx() {
		return zsd_lx;
	}
	public void setZsd_lx(String zsd_lx) {
		this.zsd_lx = zsd_lx;
	}
	public String getZsdkey() {
		return zsdkey;
	}
	public void setZsdkey(String zsdkey) {
		this.zsdkey = zsdkey;
	}
	
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	public String getCName() {
		return CName;
	}
	public void setCName(String name) {
		CName = name;
	}

	@JSON(serialize=false)
	public ZsdId getId1() {
		return id1;
	}
	public void setId1(ZsdId id1) {
		this.id1 = id1;
	}
	
	@JSON(serialize=false)
	public List<Zsd> getZsd() {
		return zsd;
	}
	public void setZsd(List<Zsd> zsd) {
		this.zsd = zsd;
	}
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	public directoryService getDirectoryService() {
		return directoryService;
	}

	public void setDirectoryService(directoryService directoryService) {
		this.directoryService = directoryService;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	
	@JSON(serialize=false)
	public String getZsdmc() {
		return zsdmc;
	}
	@JSON(serialize=false)
	
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	
	@JSON(serialize=false)
	public String getZsdms() {
		return zsdms;
	}
	public void setZsdms(String zsdms) {
		this.zsdms = zsdms;
	}
	
	@JSON(serialize=false)
	public Integer getSfzd() {
		return sfzd;
	}
	public void setSfzd(Integer sfzd) {
		this.sfzd = sfzd;
	}
	
	@JSON(serialize=false)
	public Integer getSfdn() {
		return sfdn;
	}
	public void setSfdn(Integer sfdn) {
		this.sfdn = sfdn;
	}
	
	@JSON(serialize=false)
	public Integer getZbh() {
		return zbh;
	}
	public void setZbh(Integer zbh) {
		this.zbh = zbh;
	}
	
	@JSON(serialize=false)
	public int getCId() {
		return CId;
	}
	public void setCId(int id) {
		CId = id;
	}
	
	@JSON(serialize=true)
	public Integer getZsdbh() {
		return zsdbh;
	}
	@JSON(deserialize=true)
	public void setZsdbh(Integer zsdbh) {
		this.zsdbh = zsdbh;
	}

	@JSON(serialize=false)
	public List<Zsd> getZsdlist() {
		return zsdlist;
	}
	public void setZsdlist(List<Zsd> zsdlist) {
		this.zsdlist = zsdlist;
	}
	
	
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	public Jie getJie() {
		return jie;
	}
	public void setJie(Jie jie) {
		this.jie = jie;
	}
	public JieId getId2() {
		return id2;
	}
	public void setId2(JieId id2) {
		this.id2 = id2;
	}
	public CourseChapter getCoursechapter() {
		return coursechapter;
	}
	public void setCoursechapter(CourseChapter coursechapter) {
		this.coursechapter = coursechapter;
	}
	public List<Zsd> getRows1() {
		return rows1;
	}
	public void setRows1(List<Zsd> rows1) {
		this.rows1 = rows1;
	}
	//添加功能
	@Action(value="/insertZsd",results={@Result(name="success",type="json")})			
	public String insertZsd(){
		//在Jie表中查节名称
		String[] keys = new String[1];
		keys[0] ="zmc";//zmc表示节名称
		Object[] values = new Object[1];
		values[0]=zmc;
		List<Integer> CId=this.baseservice.find(Integer.class,"Jie","id.CId",keys,values);
		
		//通过CName查找zbh
		String[] keys2=new String[1];
		keys2[0]="CName";
		Object[] values2=new Object[1];
		values2[0]=CName;
		List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys2, values2);
		
		//自动生成知识点编号
		String hql ="select count(*)from Zsd where c_id="+CId.get(0);
		int t=this.baseservice.getTotalSql(hql);
		String a =String.valueOf(t+1);
		String c = String.valueOf(CId.get(0));
		String b =String.valueOf(0);
//		if(t+1<10){
//			zsdbh=Integer.parseInt(c+b+b+a);//字符串拼接
//		}
//		if(t+1<100&&t+1>10){
//			zsdbh=Integer.parseInt(c+b+a);//字符串拼接
//		}
//		if(t+1>100){
			zsdbh=Integer.parseInt(c+a);//字符串拼接
	//	}
		
		ZsdId id1 = new ZsdId();
		id1.setZsdbh(zsdbh);
		id1.setCId(CId.get(0));
		id1.setZbh(zbh.get(0));

		Zsd zsd = new Zsd();
		zsd.setId(id1);
	    zsd.setZsdmc(zsdmc);
	    zsd.setZsdms(zsdms);
	    zsd.setZsdkey(zsdkey);
	    zsd.setSfzd(sfzd);
	    zsd.setSfdn(sfdn);
	   // zsd.setJie(jie);
	   
		this.baseservice.save(zsd);
		return SUCCESS;		
	}
	//查询所有功能
	@Action(value="/listZsd1",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String listZsd1(){	
		zsd = this.baseservice.findAll(Zsd.class,"Zsd",page, rows_s);
		total=this.baseservice.getTotal("Zsd");//记录条数
		for(int i=0;i<zsd.size();i++){
			Zsdxx element = new Zsdxx();
			element.setInt1(zsd.get(i).getJie().getCourseChapter().getZbh());
        	element.setInt2(zsd.get(i).getJie().getId().getCId());
			element.setInt3(zsd.get(i).getId().getZsdbh());
			if(zsd.get(i).getSfzd()!=null && zsd.get(i).getSfdn()!=null){
				element.setInt4(zsd.get(i).getSfzd());
				element.setInt5(zsd.get(i).getSfdn());
			}
			element.setStr1(zsd.get(i).getZsdmc());
			element.setStr2(zsd.get(i).getZsdms());
			element.setStr5(zsd.get(i).getZsdkey());
			element.setStr3(zsd.get(i).getJie().getZmc());
			element.setStr4(zsd.get(i).getJie().getCourseChapter().getCName());
			element.setKcmc(zsd.get(i).getJie().getCourseChapter().getTCName());
			rows.add(element);//把所有记录都放到rows
		}
		return "root";
		}
	//删除功能
	@Action(value="deleteZsd")
	public String deleteZsd(){
		
		List<String> keys=new ArrayList<String>();
		List<String> values=new ArrayList<String>();
		keys.add("zsdbh");
		values.add(zsdbh.toString());
		this.directoryService.updateByHql("update Xz set zsdbh=null,zbh=null,c_id=null where zsdbh="+zsdbh);
		this.directoryService.updateByHql("update Pd set zsdbh=null,zbh=null,c_id=null where zsdbh="+zsdbh);
		this.directoryService.updateByHql("update Tk set zsdbh=null,zbh=null,c_id=null where zsdbh="+zsdbh);
		this.directoryService.updateByHql("update Cztda set zsdbh=null,zbh=null,c_id=null where zsdbh="+zsdbh);
		this.directoryService.delete(Zsd.class, "Zsd", keys, values);
		return null;
	}
	//修改功能
	@Action(value="updateZsd",results={@Result(name="success",type="json")})
	public String updateZsd(){
		String[] keys1 = new String[1];
		keys1[0] ="zmc";//zmc表示节名称
		Object[] values1 = new Object[1];
		values1[0]=zmc;
		List<Integer> CId=this.baseservice.find(Integer.class,"Jie","id.CId",keys1,values1);
		
		//通过CName查找zbh
		String[] keys3=new String[1];
		keys3[0]="CName";
		Object[] values3=new Object[1];
		values3[0]=CName;
		List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys3, values3);

		ZsdId id11 = new ZsdId();
		id11.setZsdbh(zsdbh);
		id11.setZbh(zbh.get(0));
		id11.setCId(CId.get(0));
		
		Zsd zsd1 = new Zsd();
		zsd1.setId(id11);
		zsd1.setZsdmc(zsdmc);
		zsd1.setZsdms(zsdms);
		zsd1.setZsdkey(zsdkey);
		zsd1.setSfzd(sfzd);
		zsd1.setSfdn(sfdn);
		//zsd1.setJie(jie);
		
		this.baseservice.update(zsd1);
		return SUCCESS;
	}
	
	//知识点关键词查询
	@Action(value="/searchZsd",results={@Result(name="success",type="json")})
	public String searchZsd(){
		rows1 = this.baseservice.findByTypage(Zsd.class,"Zsd",zsdtype,zsdword, "order by zsdbh asc", page, rows_s);
		total=total1;//记录条数
		for(int i=0;i<rows1.size();i++){
			Zsdxx element = new Zsdxx();
			element.setInt1(rows1.get(i).getId().getZbh());
			element.setInt2(rows1.get(i).getId().getCId());
			element.setInt3(rows1.get(i).getId().getZsdbh());
			element.setInt4(rows1.get(i).getSfzd());
			element.setInt5(rows1.get(i).getSfdn());
			element.setStr1(rows1.get(i).getZsdmc());
			element.setStr2(rows1.get(i).getZsdms());
			element.setStr5(rows1.get(i).getZsdkey());
			element.setStr3(rows1.get(i).getJie().getZmc());
			element.setStr4(rows1.get(i).getJie().getCourseChapter().getCName());
			element.setKcmc(rows1.get(i).getJie().getCourseChapter().getTCName());
		
			rows.add(element);//把所有记录都放到rows
		}
		return SUCCESS;
	}
	
	
	//检验查询是否为空,并得出数据条数
	@Action(value="/searchZsd1",results={@Result(name="root",type="json")})
	public String searchZsd1(){
		String hql = "select count(*) from Zsd where "+zsdtype+" like '%"+zsdword+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
	
		return "root";
	}
	
	//页面校验
	@Action(value="/Zsdjy",results={@Result(name="success",type="json")})
	public String  Zsdjy(){
		zsdjy = this.baseservice.find(String.class, "Zsd", "zsdmc");
		if(zsdjy.contains(zsdmc)){
		b = 0;
		}else{b=1;}
		return SUCCESS;
	}
	//显示详细信息
	@Action(value="/Show1",results={@Result(name="root",type="json")})
	public String show1(){
		this.zsdmc = this.baseservice.find(Zsd.class, zsdbh).getZsdmc();
		this.zsdms = this.baseservice.find(Zsd.class, zsdbh).getZsdms();
		return "root";
	}
//	/**
//	 * 模块分类（知识点信息）
//	 */	
//	@Action(value="zsd",results={@Result(name="success",location="/kgt/Zsd.jsp")})
//	public String zsd(){
//		this.zsdb=this.baseservice.find(Zsd.class);
//		return SUCCESS;
//	}
	//二级联动通过知识点名称得到题型
	@Action(value="/gettx",results={@Result(name="root",type="json",params={"includeProperties","rows.*"})})
	public String getTX(){
		Zsd z=new Zsd();
		String[] keys = new String[1];
		Object[] values = new Object[1];
		keys[0]="zsdmc";
		values[0]=zsdmc;
		z=this.baseservice.find(Zsd.class, "Zsd", keys, values).get(0);
		keys[0]="zsdbh";
		values[0]=z.getId().getZsdbh();
		if(this.baseservice.getTotalP("Xz", keys, values)>0){
			rows.add("选择题");
		}
		if(this.baseservice.getTotalP("Tk", keys, values)>0){
			rows.add("填空题");
		}
		if(this.baseservice.getTotalP("Pd", keys, values)>0){
			rows.add("判断题");
		}
		if(this.baseservice.getTotalP("Cztda", keys, values)>0){
			rows.add("操作题");
		}
		if(rows.size()==0){
			rows.add("无相关题型");
		}
		return "root";
	}
	@Action(value="/getsource",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String getsource(){
		List<Zsd> tem=new ArrayList<Zsd>();
		String[] keys=new String[1];
		Object[] values=new Object[1];
		List<String> keys1=new ArrayList<String>();
		List<String> values1=new ArrayList<String>();
		keys[0]="zsdmc";
		values[0]=zsdword;
		tem=this.baseservice.find(Zsd.class, "Zsd", keys, values);
		keys1.add("zsdbh");
		values1.add(tem.get(0).getId().getZsdbh().toString());
		
		if(zsdtype.equals("Xz")){
			keys[0]="zsdbh";
			values[0]=tem.get(0).getId().getZsdbh();
			rows=this.baseservice.find(Xz.class, zsdtype, keys, values, "order by zsdbh asc", page, rows_s);
			total=this.directoryService.gettotal(Xz.class,zsdtype, keys1, values1);
		}
		if(zsdtype.equals("Tk")){
			keys[0]="zsdbh";
			values[0]=tem.get(0).getId().getZsdbh();
			rows=this.baseservice.find(Tk.class, zsdtype, keys, values, "order by zsdbh asc", page, rows_s);
			total=this.directoryService.gettotal(Tk.class,zsdtype, keys1, values1);
		}
		if(zsdtype.equals("Pd")){
			keys[0]="zsdbh";
			values[0]=tem.get(0).getId().getZsdbh();
			rows=this.baseservice.find(Pd.class, zsdtype, keys, values, "order by zsdbh asc", page, rows_s);
			total=this.directoryService.gettotal(Pd.class,zsdtype, keys1, values1);
		}
		if(zsdtype.equals("Czt")){
			List<Cztda> rows1 = new ArrayList<Cztda>();
			keys[0]="zsdbh";
			values[0]=tem.get(0).getId().getZsdbh();
			rows1=this.baseservice.find(Cztda.class, "Cztda", keys, values, "order by zsdbh asc", page, rows_s);
			for(Cztda czt:rows1){
				CztdDto dto=new CztdDto();
				dto.setFenzhi(czt.getCzt().getCztd().getDtfz());
				dto.setJytigan(Html2Text(czt.getCzt().getCztd().getTg()).substring(0, 15)+".......");
				dto.setTihao(czt.getCzt().getCztd().getTh());
				rows.add(dto);
			}
			total=this.directoryService.gettotal(Cztda.class,"Cztda", keys1, values1);
		}
		return "root";
	}
	public  String Html2Text(String inputString) { 
        String htmlStr = inputString; 
            String textStr =""; 
      java.util.regex.Pattern p_script; 
      java.util.regex.Matcher m_script; 
      java.util.regex.Pattern p_style; 
      java.util.regex.Matcher m_style; 
      java.util.regex.Pattern p_html; 
      java.util.regex.Matcher m_html; 
   
      try { 
       String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
       String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";  
          String regEx_html = "<[^>]+>"; 
      
          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
          m_script = p_script.matcher(htmlStr); 
          htmlStr = m_script.replaceAll(""); 

          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
          m_style = p_style.matcher(htmlStr); 
          htmlStr = m_style.replaceAll("");
      
          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
          m_html = p_html.matcher(htmlStr); 
          htmlStr = m_html.replaceAll(""); 
      
       textStr = htmlStr; 
      
      }catch(Exception e) { 
               System.err.println("Html2Text: " + e.getMessage()); 
      } 
     textStr=textStr.replace("&nbsp;","");
     System.out.println("Str"+"     "+textStr);
      return textStr;
       }   
}
