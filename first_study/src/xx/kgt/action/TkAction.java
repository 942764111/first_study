/*
 *@(#)xx.kgt.action
 *@TkAction.java.java  
 *@创建时间:2011-8-2上午08:12:10
 *@作者：张哓莉
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.kgt.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Tk;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zsd;
import xx.collection.bean.ZsdId;
import xx.kgt.bean.Tko;
import xx.md5.module.MD5Builder;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @TkAction <code>{类名称}</code>
 * @author  {张哓莉}
 * @version {版本,常用时间代替}
 * @{填空题} 
 */

@Controller
@ParentPackage("default-package")
@Namespace("")
public class TkAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private int total;//记录数据条数
	private List<Tk> tklist =new ArrayList<Tk>();
	private List<Tko> rows = new ArrayList<Tko>();
	private Tk tk;
	
	private int CId;
	private int zbh;
	private int zsdbh;
	
	private Integer th;
	private Zsd zsd;
	private String tg;
	private String da;
	private Integer csrcs;
	private Integer zqrcs;
	private Integer nyd;
	private String md5;
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	
	private String CName;
	private String zmc;
	private String zsdmc;
	
	private List<Yhzdymc> listy = new ArrayList<Yhzdymc>();
	
	
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
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
	public List<Tk> getTklist() {
		return tklist;
	}
	public void setTklist(List<Tk> tklist) {
		this.tklist = tklist;
	}
	
	public List<Tko> getRows() {
		return rows;
	}
	public void setRows(List<Tko> rows) {
		this.rows = rows;
	}
	
	
	@JSON(serialize=false)
	public Tk getTk() {
		return tk;
	}
	public void setTk(Tk tk) {
		this.tk = tk;
	}
	@JSON(serialize=false)
	public int getCId() {
		return CId;
	}
	public void setCId(int id) {
		CId = id;
	}
	@JSON(serialize=false)
	public int getZbh() {
		return zbh;
	}
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}
	@JSON(serialize=false)
	public int getZsdbh() {
		return zsdbh;
	}
	public void setZsdbh(int zsdbh) {
		this.zsdbh = zsdbh;
	}
	@JSON(serialize=false)
	public Integer getTh() {
		return th;
	}
	@JSON(deserialize=true)
	public void setTh(Integer th) {
		this.th = th;
	}
	@JSON(serialize=false)
	public Zsd getZsd() {
		return zsd;
	}
	public void setZsd(Zsd zsd) {
		this.zsd = zsd;
	}
	@JSON(serialize=false)
	public String getTg() {
		return tg;
	}
	public void setTg(String tg) {
		this.tg = tg;
	}
	@JSON(serialize=false)
	public String getDa() {
		return da;
	}
	public void setDa(String da) {
		this.da = da;
	}
	@JSON(serialize=false)
	public Integer getCsrcs() {
		return csrcs;
	}
	public void setCsrcs(Integer csrcs) {
		this.csrcs = csrcs;
	}
	@JSON(serialize=false)
	public Integer getZqrcs() {
		return zqrcs;
	}
	public void setZqrcs(Integer zqrcs) {
		this.zqrcs = zqrcs;
	}
	@JSON(serialize=false)
	public Integer getNyd() {
		return nyd;
	}
	public void setNyd(Integer nyd) {
		this.nyd = nyd;
	}
	@JSON(serialize=false)
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	
	@JSON(serialize=false)
	public String getQueryType() {
		return queryType;
	}
	@JSON(deserialize=true)
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	@JSON(serialize=false)
	public String getQueryWord() {
		return queryWord;
	}
	@JSON(deserialize=true)
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	
	@JSON(serialize=false)
	public String getCName() {
		return CName;
	}
	public void setCName(String name) {
		CName = name;
	}
	@JSON(serialize=false)
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	@JSON(serialize=false)
	public String getZsdmc() {
		return zsdmc;
	}
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	//==================
	@JSON(serialize=false)
	public List<Yhzdymc> getListy() {
		return listy;
	}
	public void setListy(List<Yhzdymc> listy) {
		this.listy = listy;
	}
	
	/**
	 * @{tk.action}
	 * 该方法是用来查询所有填空题的
	*/
	@Action(value="/tk",results={@Result(name="root",type="json")})
	public String tk(){
		tklist = this.baseservice.findAll(Tk.class, "Tk", page, rows_s);//分页查询
		total = this.baseservice.getTotal("Tk");
		for(int i=0;i<tklist.size();i++){
			Tko tk1 =new Tko();
			tk1.setInt1(tklist.get(i).getTh());
			tk1.setInt2(tklist.get(i).getCsrcs());
			tk1.setInt3(tklist.get(i).getNyd());
			tk1.setInt4(tklist.get(i).getZqrcs());
			tk1.setInt5(tklist.get(i).getZsd().getId().getCId());
			tk1.setInt6(tklist.get(i).getZsd().getId().getZbh());
			tk1.setInt7(tklist.get(i).getZsd().getId().getZsdbh());
			tk1.setStr1(tklist.get(i).getDa());
			tk1.setStr2(tklist.get(i).getTg());
			tk1.setStr3(tklist.get(i).getMd5()); 
			
			rows.add(tk1);
		}
		return "root";
	}
	

	/**
	 * @{inserttk.action}
	 * 该方法是用来增加一个填空题的
	*/
	@Action(value="/inserttk",results={@Result(name="success",type="json")})
	public String inserttk(){
		String md5 = this.getTg();
		MD5Builder md5b = new MD5Builder();		
			String xzmd5;
			try {
				xzmd5 = md5b.EncoderByMd5(md5);//用MD5对题干加密，并把加密后的MD5值放到pdmd5中。
				System.out.println(xzmd5);
				
				
				//通过CName获得CId
				String[] keys=new String[1];
				keys[0]="CName";
				Object[] values=new Object[1];
				values[0]=CName;
				List<Integer> CId=this.baseservice.find(Integer.class, "Kcxx", "CId", keys, values);
				
				//通过zmc获得的zbh
				String[] keys1=new String[1];
				keys1[0]="zmc";
				Object[] values1=new Object[1];
				values1[0]=zmc;
				List<Integer> zbh=this.baseservice.find(Integer.class, "Zinfo", "id.zbh", keys1, values1);
				
				//通过zsdmc获得zsdbh
				String[] keys2=new String[1];
				keys2[0]="zsdmc";
				Object[] values2=new Object[1];
				values2[0]=zsdmc;
				List<Integer> zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys2, values2);
		
				ZsdId id1 = new ZsdId();
				id1.setCId(CId.get(0));
				id1.setZbh(zbh.get(0));
				id1.setZsdbh(zsdbh.get(0));
				
				Zsd zsd1 = new Zsd();
				zsd1.setId(id1);				
				
				Tk tk2 = new Tk();
				if(zqrcs!=null&&csrcs!=null){
					int nyd1;  //计算难易度，用四舍五入的方法把它转化为整形。
					nyd1 =Integer.parseInt(new java.text.DecimalFormat("0").format(zqrcs*100/csrcs));
					tk2.setNyd(nyd1);
				}
				tk2.setMd5(xzmd5);			
				tk2.setCsrcs(csrcs);
				tk2.setDa(da);
				tk2.setTg(tg);
				//tk2.setTh(th);
				tk2.setZqrcs(zqrcs);
				tk2.setZsd(zsd1);
				this.baseservice.save(tk2);
				
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			}
		return SUCCESS;
	}
	
	
	
	/**
	 * @{deletetk.action}
	 * 该方法是用来删除某个填空题的
	*/
	@Action(value="/deletetk",results={@Result(name="success",type="json")})
	public String deletetk(){
		tk=this.baseservice.find(Tk.class, th);
		this.baseservice.delete(tk);
		return SUCCESS;
	}
	
	
	/**
	 * @{updatetk.action}
	 * 该方法是用来更新某个填空题的
	*/
	@Action(value="/updatetk",results={@Result(name="success",type="json")})
	public String updatetk(){
		String md5 = this.getTg();
		MD5Builder md5b = new MD5Builder();		
			String xzmd5;
			try {
				xzmd5 = md5b.EncoderByMd5(md5);//用MD5对题干加密，并把加密后的MD5值放到pdmd5中。
				System.out.println(xzmd5);
				
				ZsdId id1 = new ZsdId();
				id1.setCId(CId);
				id1.setZbh(zbh);
				id1.setZsdbh(zsdbh);
				
				Zsd zsd1 = new Zsd();
				zsd1.setId(id1);				
				
				Tk tk2 = new Tk();
				if(zqrcs!=null&&csrcs!=null){
					int nyd1;  //计算难易度，用四舍五入的方法把它转化为整形。
					nyd1 =Integer.parseInt(new java.text.DecimalFormat("0").format(zqrcs*100/csrcs));
					tk2.setNyd(nyd1);
				}
				tk2.setMd5(xzmd5);			
				tk2.setCsrcs(csrcs);
				tk2.setDa(da);
				tk2.setTg(tg);
				tk2.setTh(th);
				tk2.setZqrcs(zqrcs);
				tk2.setZsd(zsd1);
				this.baseservice.update(tk2);
				
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			}
		return SUCCESS;
	}
	
	
	/**
	 * @{queryxz.action}
	 * 该方法是用来查询选择题的，查询时会从页面接收两个参数，queryType，queryWord
	 * queryType是要查询时所根据的类型，queryWord是关键字
	 * 查询出的结果页要以分页的形式显示
	*/
	@Action(value="/querytk",results={@Result(name="root",type="json")})
	public String querytk(){
		tklist = this.baseservice.findByTypage(Tk.class, "Tk", queryType, queryWord, "order by th asc", page, rows_s);//按关键字查询
		total = this.baseservice.getTotal("Tk");
		for(int i=0;i<tklist.size();i++){
			Tko tk1 =new Tko();
			tk1.setInt1(tklist.get(i).getTh());
			tk1.setInt2(tklist.get(i).getCsrcs());
			tk1.setInt3(tklist.get(i).getNyd());
			tk1.setInt4(tklist.get(i).getZqrcs());
			tk1.setInt5(tklist.get(i).getZsd().getId().getCId());
			tk1.setInt6(tklist.get(i).getZsd().getId().getZbh());
			tk1.setInt7(tklist.get(i).getZsd().getId().getZsdbh());
			tk1.setStr1(tklist.get(i).getDa());
			tk1.setStr2(tklist.get(i).getTg());
			tk1.setStr3(tklist.get(i).getMd5()); 
			
			rows.add(tk1);
		}
		return "root";
	}
	
	
	/**
	 * @{xuanze.action}
	 * 该方法是用来指定跳转到的页面的
	 */
	@Action(value="/tko",results={@Result(name="success",location="/kgt/tk.jsp")})
	public String tko(){
		listy = this.baseservice.find(Yhzdymc.class);
		return SUCCESS;
	}
}
