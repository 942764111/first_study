/*
 *@(#)xx.spdh.action
 *@ZlzsddyAction.java.java  
 *@创建时间:2011-9-13下午08:26:30
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Dmtzl;
import xx.collection.bean.Zlzsddy;
import xx.collection.bean.ZlzsddyId;
import xx.collection.bean.Zsd;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.ZlzsdDy;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ZlzsddyAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:资料知识点对应的增删改查}
 */
@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
@SuppressWarnings("serial")
public class ZlzsddyAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	private List<Zlzsddy> zlzsddylist = new ArrayList<Zlzsddy>();
	private List<Zlzsddy> zlzsddylist1 = new ArrayList<Zlzsddy>();
	private List<ZlzsdDy> rows= new ArrayList<ZlzsdDy>();
	private List<Zsd> listzsd;
	private Zlzsddy zsddy1;
	
	private List<Dmtzl> dmtzllist;
	private String zlbh;
	
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private int total;//记录数据条数
	private static int total1;
	
	private int CId;
	private int zbh;
	private String zsdbh;
	private String weizhi;
	
	private String zlmc;
	private String CName;
	private String zmc;
	private String zsdmc;
	private String zlzsddytype;
	private String zlzsddyword;
	private int a;
	
	private String zlmczlbh;
	
	public String getZlmczlbh() {
		return zlmczlbh;
	}

	public void setZlmczlbh(String zlmczlbh) {
		this.zlmczlbh = zlmczlbh;
	}

	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	@JSON(serialize=false)
	public Zlzsddy getZsddy1() {
		return zsddy1;
	}

	public void setZsddy1(Zlzsddy zsddy1) {
		this.zsddy1 = zsddy1;
	}
	
	@JSON(serialize=false)
	public static int getTotal1() {
		return total1;
	}

	public static void setTotal1(int total1) {
		ZlzsddyAction.total1 = total1;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public List<Zlzsddy> getZlzsddylist() {
		return zlzsddylist;
	}

	public void setZlzsddylist(List<Zlzsddy> zlzsddylist) {
		this.zlzsddylist = zlzsddylist;
	}

	public List<ZlzsdDy> getRows() {
		return rows;
	}

	public void setRows(List<ZlzsdDy> rows) {
		this.rows = rows;
	}

	public List<Zsd> getListzsd() {
		return listzsd;
	}

	public void setListzsd(List<Zsd> listzsd) {
		this.listzsd = listzsd;
	}
	@JSON(serialize=false)
	public String getZlzsddytype() {
		return zlzsddytype;
	}

	public void setZlzsddytype(String zlzsddytype) {
		this.zlzsddytype = zlzsddytype;
	}
	
	@JSON(serialize=false)
	public String getZlzsddyword() {
		return zlzsddyword;
	}

	public void setZlzsddyword(String zlzsddyword) {
		this.zlzsddyword = zlzsddyword;
	}

	@JSON(serialize=false)
	public List<Dmtzl> getDmtzllist() {
		return dmtzllist;
	}
	
	@JSON(serialize=false)
	public String getZlbh() {
		return zlbh;
	}

	public void setZlbh(String zlbh) {
		this.zlbh = zlbh;
	}

	public void setDmtzllist(List<Dmtzl> dmtzllist) {
		this.dmtzllist = dmtzllist;
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
	

	public int getCId() {
		return CId;
	}

	public void setCId(int id) {
		CId = id;
	}

	public int getZbh() {
		return zbh;
	}

	public void setZbh(int zbh) {
		this.zbh = zbh;
	}

	@JSON(serialize=false)
	public String getZsdbh() {
		return zsdbh;
	}

	public void setZsdbh(String zsdbh) {
		this.zsdbh = zsdbh;
	}

	public String getWeizhi() {
		return weizhi;
	}

	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

	public String getZlmc() {
		return zlmc;
	}

	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String name) {
		CName = name;
	}

	public String getZmc() {
		return zmc;
	}

	public void setZmc(String zmc) {
		this.zmc = zmc;
	}

	public String getZsdmc() {
		return zsdmc;
	}

	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}

	/**
	 * @insertzlzsddy{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @throws JSONException 
	 * @添加资料知识点对应{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/insertzlzsddy",results={@Result(name="success",type="json")})
	public String insertzlzsddy() throws JSONException {
		zlmczlbh=this.getZlmczlbh();
		
		JSONArray jsonArray = new JSONArray(zlmczlbh);
		int iSize = jsonArray.length();
		for (int i = 0; i < iSize; i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			zlbh=(String) jsonObj.get("zlbh");
			zlmc=jsonObj.getString("zlbh");
			
			ZlzsddyId id1 = new ZlzsddyId();
			if(zlbh==null||zlbh==""){
				//通过zlmc查找zlbh
				String[] keys1 = new String[1];
				keys1[0] = "zlmc";
				Object[] values1 = new Object[1];
				values1[0] = zlmc;
				List<Integer> zlbh = this.baseservice.find(Integer.class, "Dmtzl", "id.zlbh", keys1, values1);
				id1.setZlbh(zlbh.get(0));
			}else{
				id1.setZlbh(Integer.valueOf(zlbh));
			}
			//通过CName查找zbh
			String[] keys2 = new String[1];
			keys2[0] = "CName";
			Object[] values2 = new Object[1];
			values2[0] = CName;
			List<Integer> zbh = this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys2, values2);
			
			//在Jie表中查节名称
			String[] keys3 = new String[1];
			keys3[0] = "zmc";//zmc表示节名称
			Object[] values3 = new Object[1];
			values3[0] = zmc;
			List<Integer> CId = this.baseservice.find(Integer.class, "Jie", "id.CId", keys3, values3);
			
			//通过zsdmc查找zsdbh
			String[] keys4=new String[1];
			keys4[0]="zsdmc";
			Object[] values4=new Object[1];
			values4[0]=zsdmc;
			List<Integer> zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys4, values4);
			
			
			id1.setZbh(zbh.get(0));
			id1.setCId(CId.get(0));
			id1.setZsdbh(zsdbh.get(0));
			
			Zlzsddy zlzsddy = new Zlzsddy();
			zlzsddy.setWeizhi(this.getWeizhi());
			zlzsddy.setId(id1);
			
			this.baseservice.save(zlzsddy);
		}
		
		
//		ZlzsddyId id1 = new ZlzsddyId();
//		if(zlbh==null||zlbh==""){
//			//通过zlmc查找zlbh
//			String[] keys1 = new String[1];
//			keys1[0] = "zlmc";
//			Object[] values1 = new Object[1];
//			values1[0] = zlmc;
//			List<Integer> zlbh = this.baseservice.find(Integer.class, "Dmtzl", "id.zlbh", keys1, values1);
//			id1.setZlbh(zlbh.get(0));
//		}else{
//			id1.setZlbh(Integer.valueOf(zlbh));
//		}
//		//通过CName查找zbh
//		String[] keys2 = new String[1];
//		keys2[0] = "CName";
//		Object[] values2 = new Object[1];
//		values2[0] = CName;
//		List<Integer> zbh = this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys2, values2);
//		
//		//在Jie表中查节名称
//		String[] keys3 = new String[1];
//		keys3[0] = "zmc";//zmc表示节名称
//		Object[] values3 = new Object[1];
//		values3[0] = zmc;
//		List<Integer> CId = this.baseservice.find(Integer.class, "Jie", "id.CId", keys3, values3);
//		
//		//通过zsdmc查找zsdbh
//		String[] keys4=new String[1];
//		keys4[0]="zsdmc";
//		Object[] values4=new Object[1];
//		values4[0]=zsdmc;
//		List<Integer> zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys4, values4);
//		
//		
//		
//		
//		id1.setZbh(zbh.get(0));
//		id1.setCId(CId.get(0));
//		id1.setZsdbh(zsdbh.get(0));
//		
//		Zlzsddy zlzsddy = new Zlzsddy();
//		zlzsddy.setWeizhi(this.getWeizhi());
//		zlzsddy.setId(id1);
//		
//		this.baseservice.save(zlzsddy);
		return SUCCESS;
	}
	
	/**
	 * @deletezlzsddy{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @删除资料知识点对应{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/deletezlzsddy",results={@Result(name="success",type="json")})
	public String deletezlzsddy() {
		
		String[] keys1 = new String[1];
		keys1[0] = "zsdmc";
		Object[] values1 = new Object[1];
		values1[0]=this.getZsdmc();
		
		List<Zsd> zsd = this.baseservice.find(Zsd.class,"Zsd",keys1,values1);
		int zsdbh1 = zsd.get(0).getId().getZsdbh();
		
		String[] keys2 = new String[1];
		keys2[0] = "zsdbh";
		Object[] values2 = new Object[1];
		values2[0]=zsdbh1;
		
		List<Zlzsddy> zlzsddylist1 = this.baseservice.find(Zlzsddy.class,"Zlzsddy",keys2,values2);
		
		Zlzsddy zlzsddy1 = new Zlzsddy();
		zlzsddy1 = zlzsddylist1.get(0);
		this.baseservice.delete(zlzsddy1);
		return SUCCESS;
	}
	
	
	/**
	 * @updatezlzsddy{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @修改资料知识点对应{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/updatezlzsddy",results={@Result(name="success",type="json")})
	public String updatezlzsddy() {
		ZlzsddyId id1 = new ZlzsddyId();
		if(zlbh==null||zlbh==""){
			//通过zlmc查找zlbh
			String[] keys1 = new String[1];
			keys1[0] = "zlmc";
			Object[] values1 = new Object[1];
			values1[0] = zlmc;
			List<Integer> zlbh = this.baseservice.find(Integer.class, "Dmtzl", "id.zlbh", keys1, values1);
			id1.setZlbh(zlbh.get(0));
		}else{
			id1.setZlbh(Integer.valueOf(zlbh));
		}
		
		
		//通过CName查找zbh
		String[] keys2 = new String[1];
		keys2[0] = "CName";
		Object[] values2 = new Object[1];
		values2[0] = CName;
		List<Integer> zbh = this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys2, values2);
		
		//在Jie表中查节名称
		String[] keys3 = new String[1];
		keys3[0] = "zmc";//zmc表示节名称
		Object[] values3 = new Object[1];
		values3[0] = zmc;
		List<Integer> CId = this.baseservice.find(Integer.class, "Jie", "id.CId", keys3, values3);
		
		//通过zsdmc查找zsdbh
		String[] keys4=new String[1];
		keys4[0]="zsdmc";
		Object[] values4=new Object[1];
		values4[0]=zsdmc;
		List<Integer> zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys4, values4);
		
		id1.setZbh(zbh.get(0));
		id1.setCId(CId.get(0));
		id1.setZsdbh(zsdbh.get(0));
		
		Zlzsddy zlzsddy = new Zlzsddy();
		zlzsddy.setWeizhi(this.getWeizhi());
		zlzsddy.setId(id1);
		
		this.baseservice.update(zlzsddy);
		return SUCCESS;
	}

	/**
	 * @searchzlzsddy1{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @检验查询是否为空,并得出数据条数{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/searchzlzsddy1",results={@Result(name="root",type="json")})
	public String searchzlzsddy1(){
		if(zlzsddytype.equals("zlmc")){
			String hql1 = "select zlbh from Dmtzl where zlmc like '%"+zlzsddyword+"%'";
			dmtzllist=this.baseservice.findHql(Dmtzl.class, hql1);
			if(dmtzllist.size()!=0){
				for(int i=0;i<dmtzllist.size();i++){
					String hql2 = "from Zlzsddy where zlbh="+dmtzllist.get(i);
					zlzsddylist = this.baseservice.findHql(Zlzsddy.class, hql2);
					for(int m=0;m<zlzsddylist.size();m++){
						zlzsddylist1.add(zlzsddylist.get(m));
					}
				}
			}
			total1=zlzsddylist1.size();
		}
		
		if(zlzsddytype.equals("zsdmc")){
			String hql2 = "select id.zsdbh from Zsd where zsdmc like '%"+zlzsddyword+"%'";
			listzsd=this.baseservice.findHql(Zsd.class, hql2);
			if(listzsd.size()!=0){
				for(int i=0;i<listzsd.size();i++){
					String hql1 = "from Zlzsddy where zsdbh="+listzsd.get(i);
					zlzsddylist = this.baseservice.findHql(Zlzsddy.class, hql1);
					for(int m=0;m<zlzsddylist.size();m++){
						zlzsddylist1.add(zlzsddylist.get(m));
					}
				}
			}
			total1=zlzsddylist1.size();
		}
		if(total1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	/**
	 * @searchzlzsddy{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @资料知识点对应查询{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/searchzlzsddy",results={@Result(name="success",type="json")})
	public String searchzlzsddy(){
		if(zlzsddytype.equals("zlmc")){
			String hql1 = "select zlbh from Dmtzl where zlmc like '%"+zlzsddyword+"%'";
			dmtzllist=this.baseservice.findHql(Dmtzl.class, hql1);
			if(dmtzllist.size()!=0){
				for(int i=0;i<dmtzllist.size();i++){
					System.out.println( dmtzllist.get(i));
					String hql2 = "from Zlzsddy where zlbh="+ dmtzllist.get(i);
					zlzsddylist = this.baseservice.findHql(Zlzsddy.class, hql2);
					for(int m=0;m<zlzsddylist.size();m++){
						zlzsddylist1.add(zlzsddylist.get(m));
					}
				}
			}
		}
		
		if(zlzsddytype.equals("zsdmc")){
			String hql2 = "select id.zsdbh from Zsd where zsdmc like '%"+zlzsddyword+"%'";
			listzsd=this.baseservice.findHql(Zsd.class, hql2);
			if(listzsd.size()!=0){
				for(int i=0;i<listzsd.size();i++){
					String hql1 = "from Zlzsddy where zsdbh="+ listzsd.get(i);
					zlzsddylist = this.baseservice.findHql(Zlzsddy.class, hql1);
					for(int m=0;m<zlzsddylist.size();m++){
						zlzsddylist1.add(zlzsddylist.get(m));
					}
				}
			}
		}
		for(int j=0;j<zlzsddylist1.size();j++) {
			ZlzsdDy element = new ZlzsdDy();
			element.setStr1(zlzsddylist1.get(j).getDmtzl().getZlmc());
			element.setStr2(zlzsddylist1.get(j).getZsd().getJie().getCourseChapter().getTCName());
			element.setStr3(zlzsddylist1.get(j).getZsd().getJie().getCourseChapter().getCName());
			element.setStr4(zlzsddylist1.get(j).getZsd().getJie().getZmc());
			element.setStr5(zlzsddylist1.get(j).getZsd().getZsdmc());
			element.setStr6(zlzsddylist1.get(j).getWeizhi());
			rows.add(element);//把所有记录都放到rows 
		}
		total=total1;
		return SUCCESS;
	}
	
	/**
	 * @zlzsddylist{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @知识点对应列表{方法的功能/动作描述}
	 * @exception {说明在某情况下,将发生什么异常}
	*/
	@Action(value="/zlzsddylist",results={@Result(name="root",type="json")})
	public String zlzsddylist() {
		zlzsddylist = this.baseservice.findAll(Zlzsddy.class,"Zlzsddy",page, rows_s);
		total=this.baseservice.getTotal("Zlzsddy");//记录条数
		for(int i=0;i<zlzsddylist.size();i++) {
			ZlzsdDy element = new ZlzsdDy();
			element.setStr1(zlzsddylist.get(i).getDmtzl().getZlmc());
			element.setStr2(zlzsddylist.get(i).getZsd().getJie().getCourseChapter().getTCName());
			element.setStr3(zlzsddylist.get(i).getZsd().getJie().getCourseChapter().getCName());
			element.setStr4(zlzsddylist.get(i).getZsd().getJie().getZmc());
			element.setStr5(zlzsddylist.get(i).getZsd().getZsdmc());
			element.setStr6(zlzsddylist.get(i).getWeizhi());
			rows.add(element);//把所有记录都放到rows 
		}
		return "root";
	}
	
}
