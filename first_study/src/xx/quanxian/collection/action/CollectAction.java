/*
 *@(#)xx.quanxian.collection.action
 *@Statistics.java.java  
 *@����ʱ��:2011-8-22����03:12:47
 *@���ߣ�xupengfei
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
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

import xx.collection.bean.Userinfo;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zsd;
import xx.collection.bean.ZsdId;
import xx.collection.bean.Zysc;
import xx.collection.bean.ZyscId;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class CollectAction extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService service;
	private Zsd zsd;
	private ZsdId zsdId;
	private Zysc zysc;
	private ZyscId zyscId;
	private Userinfo userinfo;
	private List<Zysc> rows= new ArrayList<Zysc>();
	private List<Yhzdymc> yhzdymclist;
	private List<Zsd> zsdlist;
	private int total;//��������total
	//datagrid����
	private int page;//��ǰҳ��
	private int rows_s;//ÿҳ��ʾrow_s������
	//===============================
	private static String userId;
	private int zdyflno;
	private int sxh;
	private int zylx;
	private int zybh;
	private String sskcmc;
	private String zyms;
	private String zdyflmc;
	//============����Ԫ��===================
	private Zytj zytj;//��Դͳ�ƶ������
	public Zytj getZytj() {
		return zytj;
	}
	public void setZytj(Zytj zytj) {
		this.zytj = zytj;
	}
	@JSON(serialize=false)	
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	@JSON(serialize=false)
	public Zsd getZsd() {
		return zsd;
	}
	public void setZsd(Zsd zsd) {
		this.zsd = zsd;
	}
	@JSON(serialize=false)
	public ZsdId getZsdId() {
		return zsdId;
	}
	public void setZsdId(ZsdId zsdId) {
		this.zsdId = zsdId;
	}
	@JSON(serialize=false)
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	@JSON(serialize=false)
	public List<Yhzdymc> getYhzdymclist() {
		return yhzdymclist;
	}
	public void setYhzdymclist(List<Yhzdymc> yhzdymclist) {
		this.yhzdymclist = yhzdymclist;
	}
	@JSON(serialize=false)
	public List<Zsd> getZsdlist() {
		return zsdlist;
	}
	public void setZsdlist(List<Zsd> zsdlist) {
		this.zsdlist = zsdlist;
	}
	//==========һ�����==================
	@JSON(serialize=false)
	public int getZylx() {
		return zylx;
	}
	public void setZylx(int zylx) {
		this.zylx = zylx;
	}
	@JSON(serialize=false)
	public int getZybh() {
		return zybh;
	}
	public void setZybh(int zybh) {
		this.zybh = zybh;
	}
	@JSON(serialize=false)
	public String getSskcmc() {
		return sskcmc;
	}
	public void setSskcmc(String sskcmc) {
		this.sskcmc = sskcmc;
	}
	@JSON(serialize=false)
	public String getZyms() {
		return zyms;
	}
	public void setZyms(String zyms) {
		this.zyms = zyms;
	}
	@JSON(serialize=false)
	public int getSxh() {
		return sxh;
	}
	public static String getUserId() {
		return userId;
	}
	@JSON(serialize=false)
	public static void setUserId(String userId) {
		CollectAction.userId = userId;
	}
	public void setSxh(int sxh) {
		this.sxh = sxh;
	}
	@JSON(serialize=false)
	public int getZdyflno() {
		return zdyflno;
	}
	public void setZdyflno(int zdyflno) {
		this.zdyflno = zdyflno;
	}
	@JSON(serialize=false)
	public String getZdyflmc() {
		return zdyflmc;
	}
	public void setZdyflmc(String zdyflmc) {
		this.zdyflmc = zdyflmc;
	}
	//=============
	@JSON(serialize=false)
	public Zysc getZysc() {
		return zysc;
	}
	@JSON(deserialize=true)
	public void setZysc(Zysc zysc) {
		this.zysc = zysc;
	}
	@JSON(serialize=false)
	public ZyscId getZyscId() {
		return zyscId;
	}
	public void setZyscId(ZyscId zyscId) {
		this.zyscId = zyscId;
	}
	
	public List<Zysc> getRows() {
		return rows;
	}
	public void setRows(List<Zysc> rows) {
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
	//=============
	/**
	 * listResource <code>listResource</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ���г��û��ղصĹ��� 
	 */
	@Action(value="/listResource",results={@Result(name="success",type="json")})
	public String list(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		System.out.println("userid:"+userId);
		List<Zysc> list = new ArrayList <Zysc>();
		list = this.service.findByProperty( "from Zysc where userId =?", userId);
		rows = this.service.findBPfenye("from Zysc where userId=?", userId, 1, 10);
		total = list.size();
		return SUCCESS;
	}
	/**
	 * deleteResource <code>deleteResource</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ���û��ղص�ɾ������ 
	 */
	@Action(value="/deleteResource",results={@Result(name="success",type="json")})
	public String delete(){
		List <Zysc> ll = new ArrayList<Zysc>();
		ll = this.service.findHql(Zysc.class, "from Zysc z where z.id.sxh="+sxh);
		zysc = ll.get(0);
		this.service.delete(zysc);
		List<Yhzdymc> yhzdymc = new ArrayList<Yhzdymc>();                          //��һ���û��Զ������
		yhzdymc = this.service.findHql(Yhzdymc.class, "from Yhzdymc y where y.zdyflno='"+zysc.getYhzdymc().getZdyflno()+"'");  
		Yhzdymc yhzdymc1 = new Yhzdymc();
		yhzdymc1 = yhzdymc.get(0);                                  //�õ������Ӧ���Զ������
		
		int old = yhzdymc1.getZysl();                               //�ó���ǰ����Դ����
		int xin = old-1;                                           //��ȡ�µ�����
		yhzdymc1.setZysl(xin);                                       //���ն�����û��Զ�����ำֵ
		this.service.update(yhzdymc1);								//�������ݿ�
		return SUCCESS;
	}
	
	/**
	 * deleteResource <code>deleteResource</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ���û��ղص�ɾ������ 
	 */
	@Action(value="/deleteResource1",results={@Result(name="success",type="json")})
	public String delete1(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List <Zysc> ll = new ArrayList<Zysc>();
		ll = this.service.findHql(Zysc.class, "from Zysc z where z.id.userId='"+userId+"' and z.zybh="+zybh+" and z.zylx="+zylx);
		zysc = ll.get(0);
		this.service.delete(zysc);
		List<Yhzdymc> yhzdymc = new ArrayList<Yhzdymc>();                          //��һ���û��Զ������
		yhzdymc = this.service.findHql(Yhzdymc.class, "from Yhzdymc y where y.zdyflno="+zysc.getYhzdymc().getZdyflno());  
		Yhzdymc yhzdymc1 = new Yhzdymc();
		yhzdymc1 = yhzdymc.get(0);                                  //�õ������Ӧ���Զ������
		int old = yhzdymc1.getZysl();                               //�ó���ǰ����Դ����
		int xin = old-1;                                           //��ȡ�µ�����
		yhzdymc1.setZysl(xin);                                       //���ն�����û��Զ�����ำֵ
		this.service.update(yhzdymc1);								//�������ݿ�
		return SUCCESS;
	}
	
	/**
	 * deleteResource <code>deleteResource</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ���û��ղص�ɾ������ 
	 */
	@Action(value="/deleteResource2",results={@Result(name="success",type="json")})
	public String delete2(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List <Zysc> ll = new ArrayList<Zysc>();
		ll = this.service.findHql(Zysc.class, "from Zysc z where z.id.userId='"+userId+"' and z.zybh="+zybh+" and z.zylx="+zylx);
		zysc = ll.get(0);
		this.service.delete(zysc);
		return SUCCESS;
	}
	
}
