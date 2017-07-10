/*
 *@(#)xx.kgt.action
 *@PdAction.java.java  
 *@创建时间:2011-8-3上午09:54:43
 *@作者：张哓莉
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.kgt.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jie;
import xx.collection.bean.Pd;
import xx.collection.bean.Rolefunction;
import xx.collection.bean.Xz;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zsd;
import xx.collection.bean.ZsdId;
import xx.collection.bean.Zysc;
import xx.kgt.bean.PanDuan;
import xx.md5.module.MD5Builder;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @PdAction <code>{类名称}</code>
 * @author  {张哓莉}
 * @version {版本,常用时间代替}
 * @{...功能描述} 
 */

@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class PdAction extends ActionSupport {
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<Pd> pdlist = new ArrayList<Pd>();
	private List<PanDuan> rows= new ArrayList<PanDuan>();
	private int page;//当前页
	private int rows_s;//每页显示的条数
	private int total;//记录数据条数
	private List<Pd> panduan;
	private Pd pd;
	private CourseChapter kcxx;
	
	
	private int CId;
	private int zbh;
	private int zsdbh;
	
	private Integer th;
	private String tg;
	private Integer da;
	private Integer csrcs;
	private Integer zqrcs;
	private String queryType;	//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;	//查询功能时的查询参数，所要根据查询的关键字
	
	private String CName;
	private String zmc;
	private String zsdmc;
	private String kcmc;
	private String zhangm;
	private String TCName;
	private int a;
	private int totalo1;
	/**
	 * 调出用户收藏题的变量
	 * 
	 */
	private String userId;
	private String QueryWord;
	private int Radio;
	private String message = "";
	private int queryw;
    private String str1;	
    private Integer int5;
    private String str3;
    private Integer int2;
    
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public Integer getInt5() {
		return int5;
	}
	public void setInt5(Integer int5) {
		this.int5 = int5;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}
	public Integer getInt2() {
		return int2;
	}
	public void setInt2(Integer int2) {
		this.int2 = int2;
	}
	/**
	 * 调出用户收藏题的变量
	 * 
	 */
	
	@JSON(serialize=false)
	public int getQueryw() {
		return queryw;
	}
	public void setQueryw(int queryw) {
		this.queryw = queryw;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@JSON(serialize=false)
	public String getZhangm() {
		return zhangm;
	}
	
	public void setZhangm(String zhangm) {
		this.zhangm = zhangm;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
	@JSON(serialize=false)
	public String getTCName() {
		return TCName;
	}
	public void setTCName(String name) {
		TCName = name;
	}
//	@JSON(serialize=false)
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	@JSON(serialize=false)
	public List<Pd> getPdlist() {
		return pdlist;
	}
	public void setPdlist(List<Pd> pdlist) {
		this.pdlist = pdlist;
	}
	public List<PanDuan> getRows() {
		return rows;
	}
	public void setRows(List<PanDuan> rows) {
		this.rows = rows;
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
	public List<Pd> getPanduan() {
		return panduan;
	}
	public void setPanduan(List<Pd> panduan) {
		this.panduan = panduan;
	}
	@JSON(serialize=false)
	public Pd getPd() {
		return pd;
	}
	public void setPd(Pd pd) {
		this.pd = pd;
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
	public String getTg() {
		return tg;
	}
	public void setTg(String tg) {
		this.tg = tg;
	}
	@JSON(serialize=false)
	public Integer getDa() {
		return da;
	}
	public void setDa(Integer da) {
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
	public String getQueryType() {
		return queryType;
	}
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
//	@JSON(serialize=false)
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
//	@JSON(serialize=false)
	public String getZsdmc() {
		return zsdmc;
	}
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	@JSON(serialize=false)
	public CourseChapter getKcxx() {
		return kcxx;
	}
	public void setKcxx(CourseChapter kcxx) {
		this.kcxx = kcxx;
	}
	/**
	 * @{spd.action}
	 * 该方法是用来调出用户查询的判断题
	*/
	@Action(value="/cxpd",results={@Result(name="root",type="json")})
	public String cxpd(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		QueryWord = (String) session1.getAttribute("querywordzy");
		Radio = Integer.parseInt((String) session1.getAttribute("radiozy"));
		if(Radio==1)
		{
			List<Pd> lxz = new ArrayList<Pd>();
			lxz = this.baseservice.findSql(Pd.class, "from Pd p where p.zsd.zsdmc like '%"+QueryWord+"%'",page, rows_s);
			for(int j=0;j<lxz.size();j++){
				pdlist.add(lxz.get(j));
			}
			List<Pd> list1 = new ArrayList<Pd>();
			list1 = this.baseservice.findHql(Pd.class,"from Pd p where p.zsd.zsdmc like '%"+QueryWord+"%'");
			total = list1.size();
		}
		if(Radio==2){
			List<Pd> list = new ArrayList<Pd>();
			list = this.baseservice.findSql(Pd.class, "from Pd p where p.tg like '%"+QueryWord+"%'",page, rows_s);
			for(int i=0;i<list.size();i++){
				pdlist.add(list.get(i));
			}
			List<Pd> list1 = new ArrayList<Pd>();
			list1 = this.baseservice.findHql(Pd.class, "from Pd p where p.tg like '%"+QueryWord+"%'");
			total = list1.size();
		}
		for(int i=0;i<pdlist.size();i++){
        	PanDuan element = new PanDuan();  //利用xx.kgt.bean中的PanDuan.java来拿到需要在前台显示的数据。
        	element.setInt1(pdlist.get(i).getCsrcs());
        	element.setInt2(pdlist.get(i).getDa());  
        	
        	kcxx = this.baseservice.find(CourseChapter.class, pdlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getTCName());//课程名
        	element.setStr1(kcxx.getCName());//章名
        	
        	element.setStr2(pdlist.get(i).getMd5());
        	element.setInt4(pdlist.get(i).getNyd());
        	element.setStr3(pdlist.get(i).getTg());
        	element.setInt5(pdlist.get(i).getTh());
        	
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=pdlist.get(i).getZsd().getId().getCId();		
			List<Jie> jie = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(jie.get(0).getZmc());//节名
        	
        	element.setInt7(pdlist.get(i).getZqrcs());
        	element.setZsdmc(pdlist.get(i).getZsd().getZsdmc()); //知识点名称
        	rows.add(element);   //把数据放进rows，实现分页查询显示    	
        	
        }
		return "root";
	}
	/**
	 * 该方法是用来检查已收藏的却被删除的判断题
	 */
	@Action(value="/jcpd",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String jcpd(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List<Zysc> list = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=12 and z.id.userId='"+userId+"'");
		if(list.size()>0){
			for(Zysc z:list){
				message += z.getZybh();
				this.baseservice.delete(z);
			}
		}
		return SUCCESS;
	}
	/**
	 * @{spd.action}
	 * 该方法是用来调出用户收藏的判断题
	*/
	@Action(value="/spd",results={@Result(name="root",type="json")})
	public String spd(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		
		List<Zysc> list = this.baseservice.findSql(Zysc.class, "from Zysc z where z.zylx=2 and z.id.userId='"+userId+"'",page, rows_s);//分页查询
		for(int i=0;i<list.size();i++){
			List<Pd> lxz = new ArrayList<Pd>();
			lxz = this.baseservice.findHql(Pd.class, "from Pd p where p.th='"+list.get(i).getZybh()+"'");
			if(lxz.size()!=0){
				pdlist.add(lxz.get(0));
			}
		}
		List<Zysc> list1 = this.baseservice.findHql(Zysc.class,"from Zysc z where z.zylx=2 and z.id.userId='"+userId+"'");
		total = list1.size();
		for(int i=0;i<pdlist.size();i++){
        	PanDuan element = new PanDuan();  //利用xx.kgt.bean中的PanDuan.java来拿到需要在前台显示的数据。
        	element.setInt1(pdlist.get(i).getCsrcs());
        	element.setInt2(pdlist.get(i).getDa());  
        	
        	kcxx = this.baseservice.find(CourseChapter.class, pdlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getTCName());//课程名
        	element.setStr1(kcxx.getCName());//章名
        	
        	element.setStr2(pdlist.get(i).getMd5());
        	element.setInt4(pdlist.get(i).getNyd());
        	element.setStr3(pdlist.get(i).getTg());
        	element.setInt5(pdlist.get(i).getTh());
        	
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=pdlist.get(i).getZsd().getId().getCId();		
			List<Jie> jie = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(jie.get(0).getZmc());//节名
        	
        	element.setInt7(pdlist.get(i).getZqrcs());
        	element.setZsdmc(pdlist.get(i).getZsd().getZsdmc()); //知识点名称
        	rows.add(element);   //把数据放进rows，实现分页查询显示    	
        	
        }
		return "root";
	}
	/**
	 * @{pd.action}
	 * 该方法是用来查询所有判断题的
	*/
	@Action(value="/pd",results={@Result(name="root",type="json")})
	public String pd(){
		pdlist = this.baseservice.findAll(Pd.class, "Pd", page, rows_s);//分页查询
		total = this.baseservice.getTotal("Pd");
		for(int i=0;i<pdlist.size();i++){
        	PanDuan element = new PanDuan();  //利用xx.kgt.bean中的PanDuan.java来拿到需要在前台显示的数据。
        	element.setInt1(pdlist.get(i).getCsrcs());
        	element.setInt2(pdlist.get(i).getDa());  
        	
        	kcxx = this.baseservice.find(CourseChapter.class, pdlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getTCName());//课程名
        	element.setStr1(kcxx.getCName());//章名
        	
        	element.setStr2(pdlist.get(i).getMd5());
        	element.setInt4(pdlist.get(i).getNyd());
        	element.setStr3(pdlist.get(i).getTg());
        	element.setInt5(pdlist.get(i).getTh());
        	
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=pdlist.get(i).getZsd().getId().getCId();		
			List<Jie> jie = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(jie.get(0).getZmc());//节名
        	
        	element.setInt7(pdlist.get(i).getZqrcs());
        	element.setZsdmc(pdlist.get(i).getZsd().getZsdmc()); //知识点名称
        	rows.add(element);   //把数据放进rows，实现分页查询显示    	
        	
        }
		return "root";
	}
	
	/**
	 * @{insertpd.action}
	 * 该方法是用来增加一个判断题的
	*/
	@Action(value="/insertpd2",results={@Result(name="success",type="json")})
	public String insertpd2(){
		String md5="";
		if(tg!=null){
			md5 = this.getTg().toString();
		}
		MD5Builder md5b = new MD5Builder();		
		String pdmd5;
			try {
				pdmd5 = md5b.EncoderByMd5(md5);//用MD5对题干加密，并把加密后的MD5值放到pdmd5中。
				System.out.println(pdmd5);
				
				//通过CName查找zbh
				String[] keys=new String[1];
				keys[0]="CName";
				Object[] values=new Object[1];
				values[0]=CName;
				List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
				
				//通过zmc查找CId
				String[] keys1=new String[1];
				keys1[0]="zmc";
				Object[] values1=new Object[1];
				values1[0]=zmc;
				List<Integer> CId=this.baseservice.find(Integer.class, "Jie", "id.CId", keys1, values1);
				
				
				//通过zsdmc查找zsdbh
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
				Pd pd1 = new Pd();
				
				if(zqrcs!=null&&csrcs!=null){
					int nyd1;  //计算难易度，用四舍五入的方法把它转化为整形。
					nyd1 =Integer.parseInt(new java.text.DecimalFormat("0").format(zqrcs*100/csrcs));
					pd1.setNyd(nyd1);
				}
				pd1.setCsrcs(csrcs);
				pd1.setDa(da);
				pd1.setMd5(pdmd5);
				
				pd1.setTg(tg);
				pd1.setZqrcs(zqrcs);
				pd1.setZsd(zsd1);
				
				this.baseservice.save(pd1);
				
			} catch (NoSuchAlgorithmException e) {
						
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
					
				e.printStackTrace();
			}
			
		return SUCCESS;
	}
	
	/**
	 * @{deletepd.action}
	 * 该方法是用来删除某个判断题的
	*/
	@Action(value="/deletepd",results={@Result(name="success",type="json")})
	public String deietepd(){
		pd=this.baseservice.find(Pd.class, th);
		//删除时要考虑到资源是否收藏的情况，下面做处理。
		List<Zysc> sclist = new ArrayList<Zysc>();
		sclist = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=2 and z.zybh="+pd.getTh());
		if(sclist.size()>0){
			for(int i=0;i<sclist.size();i++){
				Zysc zz = new Zysc();
				zz = sclist.get(i);
				List<Yhzdymc> yhzdymc = new ArrayList<Yhzdymc>();                          //建一个用户自定义分类
				yhzdymc = this.baseservice.findHql(Yhzdymc.class, "from Yhzdymc y where y.zdyflno="+zz.getYhzdymc().getZdyflno());  
				Yhzdymc yhzdymc1 = new Yhzdymc();
				yhzdymc1 = yhzdymc.get(0);                                  //拿到这个对应的自定义分类
				int old = yhzdymc1.getZysl();                               //拿出以前的资源数量
				int xin = old-1;                                           //获取新的数量
				yhzdymc1.setZysl(xin);                                       //给刚定义的用户自定义分类赋值
				this.baseservice.update(yhzdymc1);
				zz.setZylx(12);//更新数据库
				this.baseservice.update(zz);
			}
		}
		this.baseservice.delete(pd);
		return SUCCESS;
	}
	
	/**
	 * @{updatepd.action}
	 * 该方法是用来更新某个判断题的
	*/
	@Action(value="/updatepd",results={@Result(name="success",type="json")})
	public String updatepd(){
		String md5="";
		if(tg!=null){
			md5 = this.getTg().toString();
		}
		MD5Builder md5b = new MD5Builder();		
			String pdmd5;
			try {
				pdmd5 = md5b.EncoderByMd5(md5);//用MD5对题干加密，并把加密后的MD5值放到pdmd5中。
				System.out.println(pdmd5);
				
				//通过CName查找zbh
				String[] keys=new String[1];
				keys[0]="CName";
				Object[] values=new Object[1];
				values[0]=zhangm;
				List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
				
				//通过zmc查找CId
				String[] keys1=new String[1];
				keys1[0]="zmc";
				Object[] values1=new Object[1];
				values1[0]=zmc;
				List<Integer> CId=this.baseservice.find(Integer.class, "Jie", "id.CId", keys1, values1);
				
				
				//通过zsdmc查找zsdbh
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
				Pd pd1 = new Pd();
				if(zqrcs!=null&&csrcs!=null){
					int nyd1;  //计算难易度，用四舍五入的方法把它转化为整形。
					nyd1 =Integer.parseInt(new java.text.DecimalFormat("0").format(zqrcs*100/csrcs));
					pd1.setNyd(nyd1);
				}
				pd1.setCsrcs(csrcs);
				pd1.setDa(da);
				pd1.setMd5(pdmd5);
				
				pd1.setTg(tg);
				pd1.setTh(th);
				pd1.setZqrcs(zqrcs);
				pd1.setZsd(zsd1);
				
				this.baseservice.update(pd1);
				
			} catch (NoSuchAlgorithmException e) {
						
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
					
				e.printStackTrace();
			}
					
		return SUCCESS;
	}
	
	//检验查询是否为空,并得出数据条数
	@Action(value="/querypd1",results={@Result(name="root",type="json")})
	public String querypd1(){
		String hql = "select count(*) from Pd where "+queryType+" like '%"+queryWord+"%'";
		HttpSession hs = ServletActionContext.getRequest().getSession();
		totalo1 = this.baseservice.getTotalSql(hql);
		hs.setAttribute("pdtotal", totalo1);
		if(totalo1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	/**
	 * @{querypd.action}
	 * 该方法是用来根据关键字查询判断题的，查询时会从页面接收两个参数，queryType，queryWord
	 * queryType是要查询时所根据的类型，queryWord是关键字
	 * 查询出的结果页要以分页的形式显示
	*/
	@Action(value="/querypd",results={@Result(name="root",type="json")})
	public String querypd(){
		
		pdlist = this.baseservice.findByTypage(Pd.class, "Pd", queryType, queryWord, "order by th asc", page, rows_s);//按关键字查询
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("pdtotal");
		for(int i=0;i<pdlist.size();i++){
        	PanDuan element = new PanDuan();//利用xx.kgt.bean中的PanDuan.java来拿到需要在前台显示的数据。
        	element.setInt1(pdlist.get(i).getCsrcs());
        	//element.setStr1(pdlist.get(i).getZsd_1());       	
        	element.setInt2(pdlist.get(i).getDa());  
        	kcxx = this.baseservice.find(CourseChapter.class, pdlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getTCName());//课程名称
        	element.setStr1(kcxx.getCName());//章名
        	element.setStr2(pdlist.get(i).getMd5());
        	element.setInt4(pdlist.get(i).getNyd());
        	element.setStr3(pdlist.get(i).getTg());
        	element.setInt5(pdlist.get(i).getTh());
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=pdlist.get(i).getZsd().getId().getCId();		
			List<Jie> jie = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(jie.get(0).getZmc());//节名
        	element.setInt7(pdlist.get(i).getZqrcs());
        	element.setZsdmc(pdlist.get(i).getZsd().getZsdmc()); //知识点名称
        	rows.add(element);   //把数据放进rows，实现分页查询显示    	
        	
        }
		return "root";
	}
	
	
	
	/**
	 * @{panduan.action}
	 * 该方法是用来指定跳转到的页面的
	 */
//	@Action(value="/panduan",results={@Result(name="success",location="/kgt/pd.jsp")})
//	public String panduan(){
//			return SUCCESS;
//	}
	
	
	/**
	 * 
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{这是对于收藏的题目的查询}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	//检验查询是否为空,并得出数据条数
	@Action(value="/querypdsc1",results={@Result(name="root",type="json")})
	public String queryxzsc1(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		int cxsl = 0;
		String hql = "select count(*) from Zysc z where z.zylx=2 and z.id.userId='"+userId+"' and z.zybh="+queryw;
		cxsl = this.baseservice.getTotalSql(hql);
		if(cxsl!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	@Action(value="/querypdsc",results={@Result(name="root",type="json")})
	public String querypdsc(){
		
		pdlist = this.baseservice.findHql(Pd.class, "from Pd p where p.th="+queryw);
		total = 1;
		for(int i=0;i<pdlist.size();i++){
        	PanDuan element = new PanDuan();//利用xx.kgt.bean中的PanDuan.java来拿到需要在前台显示的数据。
        	element.setInt1(pdlist.get(i).getCsrcs());
        	//element.setStr1(pdlist.get(i).getZsd_1());       	
        	element.setInt2(pdlist.get(i).getDa());  
        	kcxx = this.baseservice.find(CourseChapter.class, pdlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getTCName());//课程名称
        	element.setStr1(kcxx.getCName());//章名
        	element.setStr2(pdlist.get(i).getMd5());
        	element.setInt4(pdlist.get(i).getNyd());
        	element.setStr3(pdlist.get(i).getTg());
        	element.setInt5(pdlist.get(i).getTh());
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=pdlist.get(i).getZsd().getId().getCId();		
			List<Jie> jie = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(jie.get(0).getZmc());//节名
        	element.setInt7(pdlist.get(i).getZqrcs());
        	element.setZsdmc(pdlist.get(i).getZsd().getZsdmc()); //知识点名称
        	rows.add(element);   //把数据放进rows，实现分页查询显示    	
        	
        }
		return "root";
	}
	
	/**
	 * 
	 * @{方法名}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{查看收藏的判断题（单个）}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/querypdscdg",results={@Result(name="root",type="json")})
	public String querypdscdg(){
		
		pdlist = this.baseservice.findHql(Pd.class, "from Pd p where p.th="+queryw);
		total = 1;
		for(int i=0;i<pdlist.size();i++){
		    int2 = pdlist.get(i).getDa();  
        	kcxx = this.baseservice.find(CourseChapter.class, pdlist.get(i).getZsd().getId().getZbh());
        	kcmc = kcxx.getTCName();//课程名称
        	str1 = kcxx.getCName();//章名
//        	element.setStr2(pdlist.get(i).getMd5());
//        	element.setInt4(pdlist.get(i).getNyd());
        	str3 = pdlist.get(i).getTg();
        	int5 = pdlist.get(i).getTh();
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=pdlist.get(i).getZsd().getId().getCId();		
			List<Jie> jie = this.baseservice.find(Jie.class, "Jie", keys, values);
			zmc = jie.get(0).getZmc();//节名
        	zsdmc = pdlist.get(i).getZsd().getZsdmc(); //知识点名称
        	
        }
		return "root";
	}
}
