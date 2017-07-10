/*
 *@(#)xx.kgt.action
 *@XzAction.java.java  
 *@����ʱ��:2011-8-2����08:09:37
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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
import xx.collection.bean.Xz;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zsd;
import xx.collection.bean.ZsdId;
import xx.collection.bean.Zysc;
import xx.kgt.bean.XuanZe;
import xx.md5.module.MD5Builder;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @XzAction <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{...��������} 
 */


@Controller
@Scope("prototype")
@ParentPackage("default-package")
@Namespace("")
public class XzAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService baseservice;
	
	private List<XuanZe> rows = new ArrayList<XuanZe>();
	private List<Xz> xzlist = new ArrayList<Xz>();
	private int page;//��ǰҳ
	private int rows_s;//ÿҳ��ʾ������
	private int total;//��¼��������
	private Xz xz;
	private CourseChapter kcxx;
	private Jie zxx;
	private int b;
	private int a;
	private int totalo1;
	private List<String> xzjy = new ArrayList<String>();
	
	private ZsdId id1;
	private int CId;
	private int zbh;
	private int zsdbh;
	
	private Integer th;
	private Zsd zsd;
	private String tg;
	private String xx1;
	private String xx2;
	private String xx3;
	private String xx4;
	private Integer ddx;
	private String da;
	private Integer csrcs;
	private Integer zqrcs;
	private String queryType;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ������
	private String queryWord;	//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	
	private String CName;
	private String zmc;
	private String zsdmc;
	private String kcmc;
	
	//===============
	private List<Yhzdymc> listy = new ArrayList<Yhzdymc>();
	
	/**
	 * �����û��ղ�ѡ����ı���
	 * 
	 */
	private String userId;
	private String message = "";
	private String QueryWord;
	private int Radio;
	private int queryw;
	private int int1;
    private String tcname;
    private int int5;
    private String str3;
 
    private String str4;
    private String str5;
    private String str6;
    private String str7;
    private int int2;
    private int int7;
    private String str1;
    
	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public int getInt1() {
		return int1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}
	public String getTcname() {
		return tcname;
	}

	public void setTcname(String tcname) {
		this.tcname = tcname;
	}

	public int getInt5() {
		return int5;
	}

	public void setInt5(int int5) {
		this.int5 = int5;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

	public String getStr5() {
		return str5;
	}

	public void setStr5(String str5) {
		this.str5 = str5;
	}

	public String getStr6() {
		return str6;
	}

	public void setStr6(String str6) {
		this.str6 = str6;
	}

	public String getStr7() {
		return str7;
	}

	public void setStr7(String str7) {
		this.str7 = str7;
	}

	public int getInt2() {
		return int2;
	}

	public void setInt2(int int2) {
		this.int2 = int2;
	}

	public int getInt7() {
		return int7;
	}

	public void setInt7(int int7) {
		this.int7 = int7;
	}

	/**
	 * �����û��ղ�ѡ����ı���
	 * 
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@JSON(serialize=false)
	public int getQueryw() {
		return queryw;
	}

	public void setQueryw(int queryw) {
		this.queryw = queryw;
	}

	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}

	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	
	@JSON(serialize=false)
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@JSON(serialize=false)
	public List<String> getXzjy() {
		return xzjy;
	}
	public void setXzjy(List<String> xzjy) {
		this.xzjy = xzjy;
	}
	//@JSON(serialize=false)
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	@JSON(serialize=false)
	public List<Xz> getXzlist() {
		return xzlist;
	}
	public void setXzlist(List<Xz> xzlist) {
		this.xzlist = xzlist;
	}
		
	public List<XuanZe> getRows() {
		return rows;
	}
	public void setRows(List<XuanZe> rows) {
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
	public Xz getXz() {
		return xz;
	}
	public void setXz(Xz xz) {
		this.xz = xz;
	}
	@JSON(serialize=false)
	public CourseChapter getKcxx() {
		return kcxx;
	}
	public void setKcxx(CourseChapter kcxx) {
		this.kcxx = kcxx;
	}
	@JSON(serialize=false)
	public Jie getZxx() {
		return zxx;
	}
	public void setZxx(Jie zxx) {
		this.zxx = zxx;
	}
	@JSON(serialize=false)
	public ZsdId getId1() {
		return id1;
	}
	public void setId1(ZsdId id1) {
		this.id1 = id1;
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
	public String getXx1() {
		return xx1;
	}
	public void setXx1(String xx1) {
		this.xx1 = xx1;
	}
	@JSON(serialize=false)
	public String getXx2() {
		return xx2;
	}
	public void setXx2(String xx2) {
		this.xx2 = xx2;
	}
	@JSON(serialize=false)
	public String getXx3() {
		return xx3;
	}
	public void setXx3(String xx3) {
		this.xx3 = xx3;
	}
	@JSON(serialize=false)
	public String getXx4() {
		return xx4;
	}
	public void setXx4(String xx4) {
		this.xx4 = xx4;
	}
	@JSON(serialize=false)
	public Integer getDdx() {
		return ddx;
	}
	public void setDdx(Integer ddx) {
		this.ddx = ddx;
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
	//@JSON(serialize=false)
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	//@JSON(serialize=false)
	public String getZsdmc() {
		return zsdmc;
	}
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	//=================
	@JSON(serialize=false)
	public List<Yhzdymc> getListy() {
		return listy;
	}
	public void setListy(List<Yhzdymc> listy) {
		this.listy = listy;
	}
	/**
	 * �÷�������������ѯ����ѡ����
	 */
	@Action(value="/cxxz",results={@Result(name="success",type="json")})
	
	public String cxxz(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		QueryWord = (String) session1.getAttribute("querywordzy");
		Radio =   Integer.parseInt((String) session1.getAttribute("radiozy"));
		if(Radio==1)
		{
			List<Xz> lxz = new ArrayList<Xz>();
			lxz = this.baseservice.findSql(Xz.class, "from Xz x where x.zsd.zsdmc like '%"+QueryWord+"%'",page, rows_s);
			for(int j=0;j<lxz.size();j++){
				xzlist.add(lxz.get(j));
			}
			String hql = "select count(*) from Xz x where x.zsd.zsdmc like '%"+QueryWord+"%'";
			total = this.baseservice.getTotalSql(hql);
		}
		if(Radio==2){
			List<Xz> list = new ArrayList<Xz>();
			list = this.baseservice.findSql(Xz.class, "from Xz x where x.tg like '%"+QueryWord+"%'",page, rows_s);
			for(int i=0;i<list.size();i++){
				xzlist.add(list.get(i));
			}
			String hql = "select count(*) from Xz x where x.tg like '%"+QueryWord+"%'";
			total = this.baseservice.getTotalSql(hql);
		}
		for(int i=0;i<xzlist.size();i++){
        	XuanZe element = new XuanZe();//����xx.kgt.bean�е�XuanZe.java���õ���Ҫ��ǰ̨��ʾ�����ݡ�
        	element.setInt1(xzlist.get(i).getCsrcs());
        	element.setStr1(xzlist.get(i).getDa());       	
        	element.setInt2(xzlist.get(i).getDdx()); 
        	
        	kcxx = this.baseservice.find(CourseChapter.class, xzlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getCName());//����
        	element.setTcname(kcxx.getTCName());//�γ���
        	
        	element.setStr2(xzlist.get(i).getMd5());
        	element.setInt4(xzlist.get(i).getNyd());
        	element.setStr3(xzlist.get(i).getTg());
        	element.setInt5(xzlist.get(i).getTh());
        	element.setStr4(xzlist.get(i).getXx1());
        	element.setStr5(xzlist.get(i).getXx2());
        	element.setStr6(xzlist.get(i).getXx3());
        	element.setStr7(xzlist.get(i).getXx4());
        	
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=xzlist.get(i).getZsd().getId().getCId();		
			List<Jie> zinf = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(zinf.get(0).getZmc());//����
        	
        	element.setInt7(xzlist.get(i).getZqrcs());
        	element.setZsdmc(xzlist.get(i).getZsd().getZsdmc()); //֪ʶ������
        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    
//        	System.out.println("===================================");
//        	System.out.println(rows);
        	
        }
		return SUCCESS;
	}
	
	/**
	 * �÷���������������ղص�ȴ��ɾ����ѡ����
	 */
	@Action(value="/jcxz",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String jcxz(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List<Zysc> list = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=11 and z.id.userId='"+userId+"'");
		if(list.size()>0){
			for(Zysc z:list){
				message += z.getZybh();
				this.baseservice.delete(z);
			}
		}
		return SUCCESS;
	}
	
//	/**
//	 * �÷�������������ĳ�����ղص�ѡ�����
//	 */
//	@Action(value="/mgxzsc",results={@Result(name="success",type="json")})
//	public String mgxzsc(){
//		xzlist = this.baseservice.findHql(Xz.class, "from Xz x where x.th="zybh);//��ҳ��ѯ
//		return SUCCESS;
//	}
	/**
	 * �÷������������ղص�ѡ����
	 */
	@Action(value="/sxz",results={@Result(name="success",type="json")})
	public String sxz(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List<Zysc> list = this.baseservice.findSql(Zysc.class, "from Zysc z where z.zylx=1 and z.id.userId='"+userId+"'",page, rows_s);//��ҳ��ѯ
		for(int i=0;i<list.size();i++){
			List<Xz> lxz = new ArrayList<Xz>();
			lxz = this.baseservice.findHql(Xz.class, "from Xz x where x.th='"+list.get(i).getZybh()+"'");
			if(lxz.size()!=0){
				xzlist.add(lxz.get(0));
			}
		}
		String hql = "select count(*) from Zysc z where z.zylx=1 and z.id.userId='"+userId+"'";
		total = this.baseservice.getTotalSql(hql);
		for(int i=0;i<xzlist.size();i++){
        	XuanZe element = new XuanZe();//����xx.kgt.bean�е�XuanZe.java���õ���Ҫ��ǰ̨��ʾ�����ݡ�
        	element.setInt1(xzlist.get(i).getCsrcs());
        	element.setStr1(xzlist.get(i).getDa());       	
        	element.setInt2(xzlist.get(i).getDdx()); 
        	
        	kcxx = this.baseservice.find(CourseChapter.class, xzlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getCName());//����
        	element.setTcname(kcxx.getTCName());//�γ���
        	
        	element.setStr2(xzlist.get(i).getMd5());
        	element.setInt4(xzlist.get(i).getNyd());
        	element.setStr3(xzlist.get(i).getTg());
        	element.setInt5(xzlist.get(i).getTh());
        	element.setStr4(xzlist.get(i).getXx1());
        	element.setStr5(xzlist.get(i).getXx2());
        	element.setStr6(xzlist.get(i).getXx3());
        	element.setStr7(xzlist.get(i).getXx4());
        	
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=xzlist.get(i).getZsd().getId().getCId();		
			List<Jie> zinf = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(zinf.get(0).getZmc());//����
        	
        	element.setInt7(xzlist.get(i).getZqrcs());
        	element.setZsdmc(xzlist.get(i).getZsd().getZsdmc()); //֪ʶ������
        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    
        	
        }
		return SUCCESS;
	}
	
	/**
	 * @{xz.action}
	 * �÷�����������ѯ����ѡ�����
	*/
	@Action(value="/xz",results={@Result(name="root",type="json")})
	public String xz(){
		xzlist = this.baseservice.findAll(Xz.class, "Xz", page, rows_s);//��ҳ��ѯ
		total = this.baseservice.getTotal("Xz");
		for(int i=0;i<xzlist.size();i++){
        	XuanZe element = new XuanZe();//����xx.kgt.bean�е�XuanZe.java���õ���Ҫ��ǰ̨��ʾ�����ݡ�
        	element.setInt1(xzlist.get(i).getCsrcs());
        	element.setStr1(xzlist.get(i).getDa());       	
        	element.setInt2(xzlist.get(i).getDdx()); 
        	
        	kcxx = this.baseservice.find(CourseChapter.class, xzlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getCName());//����
        	element.setTcname(kcxx.getTCName());//�γ���
        	
        	element.setStr2(xzlist.get(i).getMd5());
        	element.setInt4(xzlist.get(i).getNyd());
        	element.setStr3(xzlist.get(i).getTg());
        	element.setInt5(xzlist.get(i).getTh());
        	element.setStr4(xzlist.get(i).getXx1());
        	element.setStr5(xzlist.get(i).getXx2());
        	element.setStr6(xzlist.get(i).getXx3());
        	element.setStr7(xzlist.get(i).getXx4());
        	
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=xzlist.get(i).getZsd().getId().getCId();		
			List<Jie> zinf = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(zinf.get(0).getZmc());//����
        	
        	element.setInt7(xzlist.get(i).getZqrcs());
        	element.setZsdmc(xzlist.get(i).getZsd().getZsdmc()); //֪ʶ������
        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    	
        	
        }
		return "root";
	}

	/**
	 * @{insertxz.action}
	 * �÷�������������һ��ѡ�����
	*/
	@Action(value="/insertxz",results={@Result(name="success",type="json")})
	public String insertxz(){		
		String md5="";
		if(tg!=null){
			md5 = this.getTg().toString();
		}
		MD5Builder md5b = new MD5Builder();		
			String xzmd5;
			try {
				xzmd5 = md5b.EncoderByMd5(md5);//��MD5����ɼ��ܣ����Ѽ��ܺ��MD5ֵ�ŵ�pdmd5�С�
				System.out.println(xzmd5);
				
				//ͨ��CName���zbh
				String[] keys=new String[1];
				keys[0]="CName";
				Object[] values=new Object[1];
				values[0]=CName;
				List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
				
				//ͨ����ô�����CId
				String[] keys1=new String[1];
				keys1[0]="zmc";
				Object[] values1=new Object[1];
				values1[0]=zmc;
				List<Integer> CId=this.baseservice.find(Integer.class, "Jie", "id.CId", keys1, values1);
				
				
				//ͨ��zsdmc����zsdbh
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
				
				Xz xz1 = new Xz();
				if(zqrcs!=null&&csrcs!=null){
					int nyd1;  //�������׶ȣ�����������ķ�������ת��Ϊ���Ρ�
					nyd1 =Integer.parseInt(new java.text.DecimalFormat("0").format(zqrcs*100/csrcs));
					xz1.setNyd(nyd1);
				}
				xz1.setMd5(xzmd5);			
				xz1.setCsrcs(csrcs);
				xz1.setDa(da);
				xz1.setDdx(ddx);
				xz1.setTg(tg);
				xz1.setXx1(xx1);
				xz1.setXx2(xx2);
				xz1.setXx3(xx3);
				xz1.setXx4(xx4);
				xz1.setZqrcs(zqrcs);
				xz1.setZsd(zsd1);
				this.baseservice.save(xz1);
				
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			}
					
		return SUCCESS;
	}
	
	/**
	 * @{deletexz.action}
	 * �÷���������ɾ��ĳ��ѡ�����
	*/
	@Action(value="/deletexz",results={@Result(name="success",type="json")})
	public String deietexz(){
		xz=this.baseservice.find(Xz.class, th);
		
		//ɾ��ʱҪ���ǵ���Դ�Ƿ��ղص����������������
		List<Zysc> sclist = new ArrayList<Zysc>();
		sclist = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=1 and z.zybh="+xz.getTh());
		if(sclist.size()>0){
			for(int i=0;i<sclist.size();i++){
				Zysc zz = new Zysc();
				zz = sclist.get(i);
				List<Yhzdymc> yhzdymc = new ArrayList<Yhzdymc>();                          //��һ���û��Զ������
				yhzdymc = this.baseservice.findHql(Yhzdymc.class, "from Yhzdymc y where y.zdyflno="+zz.getYhzdymc().getZdyflno());  
				Yhzdymc yhzdymc1 = new Yhzdymc();
				yhzdymc1 = yhzdymc.get(0);                                  //�õ������Ӧ���Զ������
				int old = yhzdymc1.getZysl();                               //�ó���ǰ����Դ����
				int xin = old-1;                                           //��ȡ�µ�����
				yhzdymc1.setZysl(xin);                                       //���ն�����û��Զ�����ำֵ
				this.baseservice.update(yhzdymc1);
				zz.setZylx(11);//�������ݿ�
				this.baseservice.update(zz);
			}
		}
		this.baseservice.delete(xz);
		
		return SUCCESS;
	}
	
	/**
	 * @{updatexz.action}
	 * �÷�������������ĳ��ѡ�����
	*/
	@Action(value="/updatexz",results={@Result(name="success",type="json")})
	public String updatexz(){
		String md5="";
		if(tg!=null){
			md5 = this.getTg().toString();
		}
		MD5Builder md5b = new MD5Builder();		
			String xzmd5;
			try {
				xzmd5 = md5b.EncoderByMd5(md5);//��MD5����ɼ��ܣ����Ѽ��ܺ��MD5ֵ�ŵ�pdmd5�С�
				System.out.println(xzmd5);
				
				//ͨ��CName���zbh
				String[] keys=new String[1];
				keys[0]="CName";
				Object[] values=new Object[1];
				values[0]=kcmc;
				List<Integer> zbh=this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys, values);
				
				//ͨ��zmc����CId
				String[] keys1=new String[1];
				keys1[0]="zmc";
				Object[] values1=new Object[1];
				values1[0]=zmc;
				List<Integer> CId=this.baseservice.find(Integer.class, "Jie", "id.CId", keys1, values1);
				
				
				//ͨ��zsdmc����zsdbh
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
				Xz xz1 = new Xz();
				if(zqrcs!=null&&csrcs!=null){
					int nyd1;  //�������׶ȣ�����������ķ�������ת��Ϊ���Ρ�
					nyd1 =Integer.parseInt(new java.text.DecimalFormat("0").format(zqrcs*100/csrcs));
					xz1.setNyd(nyd1);
				}
				xz1.setMd5(xzmd5);			
				xz1.setCsrcs(csrcs);
				xz1.setDa(da);
				xz1.setDdx(ddx);
				xz1.setTh(th);
				xz1.setTg(tg);
				xz1.setXx1(xx1);
				xz1.setXx2(xx2);
				xz1.setXx3(xx3);
				xz1.setXx4(xx4);
				xz1.setZqrcs(zqrcs);
				xz1.setZsd(zsd1);
				this.baseservice.update(xz1);
				
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			}
		
		return SUCCESS;
	}
	
	
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/queryxz1",results={@Result(name="root",type="json")})
	public String queryxz1(){
		String hql = "select count(*) from Xz where "+queryType+" like '%"+queryWord+"%'";
		totalo1 = this.baseservice.getTotalSql(hql);
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("xztotal", totalo1);
		if(totalo1!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	/**
	 * @{queryxz.action}
	 * �÷�����������ѯѡ����ģ���ѯʱ���ҳ���������������queryType��queryWord
	 * queryType��Ҫ��ѯʱ�����ݵ����ͣ�queryWord�ǹؼ���
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryxz",results={@Result(name="root",type="json")})
	public String queryxz(){
		xzlist = this.baseservice.findByTypage(Xz.class, "Xz", queryType, queryWord, "order by th asc", page, rows_s);//���ؼ��ֲ�ѯ
		HttpSession hs = ServletActionContext.getRequest().getSession();
		total=(Integer)hs.getAttribute("xztotal");
		for(int i=0;i<xzlist.size();i++){
			
        	XuanZe element = new XuanZe();//����xx.kgt.bean�е�XuanZe.java���õ���Ҫ��ǰ̨��ʾ�����ݡ�
        	element.setInt1(xzlist.get(i).getCsrcs());
        	element.setStr1(xzlist.get(i).getDa());       	
        	element.setInt2(xzlist.get(i).getDdx());  
        	kcxx = this.baseservice.find(CourseChapter.class, xzlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getCName());
        	element.setTcname(kcxx.getTCName());
        	element.setStr2(xzlist.get(i).getMd5());
        	element.setInt4(xzlist.get(i).getNyd());
        	element.setStr3(xzlist.get(i).getTg());
        	element.setInt5(xzlist.get(i).getTh());
        	element.setStr4(xzlist.get(i).getXx1());
        	element.setStr5(xzlist.get(i).getXx2());
        	element.setStr6(xzlist.get(i).getXx3());
        	element.setStr7(xzlist.get(i).getXx4());
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=xzlist.get(i).getZsd().getId().getCId();		
			List<Jie> zinf = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(zinf.get(0).getZmc());
        	element.setInt7(xzlist.get(i).getZqrcs());
        	element.setZsdmc(xzlist.get(i).getZsd().getZsdmc());
        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    	
        	
        }
		return "root";
	}
	/**
	 * 
	 * @{������}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{���Ƕ����ղص���Ŀ�Ĳ�ѯ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/queryxzsc1",results={@Result(name="root",type="json")})
	public String queryxzsc1(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		int cxsl = 0;
		String hql = "select count(*) from Zysc z where z.zylx=1 and z.id.userId='"+userId+"' and z.zybh="+queryw;
		cxsl = this.baseservice.getTotalSql(hql);
		if(cxsl!=0){
			a=1;
		}else{
			a=0;
		}
		return "root";
	}
	
	/**
	 * @{queryxz.action}
	 * �÷�����������ѯѡ�����
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryxzscdg",results={@Result(name="root",type="json")})
	public String queryxzscdg(){
		xzlist = this.baseservice.findHql(Xz.class, "from Xz x where x.th="+queryw);//���ؼ��ֲ�ѯ
		
//		System.out.println(xzlist.size());
		total = 1;
		for(int i=0;i<xzlist.size();i++){
			int1 = xzlist.get(i).getCsrcs();
        	str1 = xzlist.get(i).getDa();       	
        	int2 = xzlist.get(i).getDdx();  
        	kcxx = this.baseservice.find(CourseChapter.class, xzlist.get(i).getZsd().getId().getZbh());
        	kcmc = kcxx.getCName();
        	tcname = kcxx.getTCName();
        	str3 = xzlist.get(i).getTg();
        	int5 = xzlist.get(i).getTh();
        	str4 = xzlist.get(i).getXx1();
        	str5 = xzlist.get(i).getXx2();
        	str6 = xzlist.get(i).getXx3();
        	str7 = xzlist.get(i).getXx4();
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=xzlist.get(i).getZsd().getId().getCId();		
			List<Jie> zinf = this.baseservice.find(Jie.class, "Jie", keys, values);
			zmc = zinf.get(0).getZmc();
			int7 = xzlist.get(i).getZqrcs();
        	zsdmc = xzlist.get(i).getZsd().getZsdmc();
        	
        }
		return "root";
	}
	/**
	 * @{queryxz.action}
	 * �÷�����������ѯѡ�����
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryxzsc",results={@Result(name="root",type="json")})
	public String queryxzsc(){
		xzlist = this.baseservice.findHql(Xz.class, "from Xz x where x.th="+queryw);//���ؼ��ֲ�ѯ
		
//		System.out.println(xzlist.size());
		total = 1;
		for(int i=0;i<xzlist.size();i++){
        	XuanZe element = new XuanZe();//����xx.kgt.bean�е�XuanZe.java���õ���Ҫ��ǰ̨��ʾ�����ݡ�
        	element.setInt1(xzlist.get(i).getCsrcs());
        	element.setStr1(xzlist.get(i).getDa());       	
        	element.setInt2(xzlist.get(i).getDdx());  
        	kcxx = this.baseservice.find(CourseChapter.class, xzlist.get(i).getZsd().getId().getZbh());
        	element.setKcmc(kcxx.getCName());
        	element.setTcname(kcxx.getTCName());
        	element.setStr2(xzlist.get(i).getMd5());
        	element.setInt4(xzlist.get(i).getNyd());
        	element.setStr3(xzlist.get(i).getTg());
        	element.setInt5(xzlist.get(i).getTh());
        	element.setStr4(xzlist.get(i).getXx1());
        	element.setStr5(xzlist.get(i).getXx2());
        	element.setStr6(xzlist.get(i).getXx3());
        	element.setStr7(xzlist.get(i).getXx4());
        	String[] keys=new String[1];
			keys[0]="c_id";		
			Object[] values=new Object[1];
			values[0]=xzlist.get(i).getZsd().getId().getCId();		
			List<Jie> zinf = this.baseservice.find(Jie.class, "Jie", keys, values);
        	element.setZmc(zinf.get(0).getZmc());
        	element.setInt7(xzlist.get(i).getZqrcs());
        	element.setZsdmc(xzlist.get(i).getZsd().getZsdmc());
        	rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ    	
        	
        }
		return "root";
	}
	
	//ҳ��У��
//	@Action(value="/xzjy",results={@Result(name="success",type="json")})
//	public String  xzjy(){
//		xzjy = this.baseservice.find(String.class, "Xz", "tg");
//		if(xzjy.contains(tg)){
//		b = 0;
//		}else{b=1;}
//		return SUCCESS;
//	}
	
	
	
	/**
	 * @{xuanze.action}
	 * �÷���������ָ����ת����ҳ���
	 */
//	@Action(value="/xuanze",results={@Result(name="success",location="/kgt/xz.jsp")})
//	public String xuanze(){
//		listy = this.baseservice.find(Yhzdymc.class);
//		return SUCCESS;
//	}
	
}
