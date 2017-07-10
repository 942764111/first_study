/*
 *@(#)xx.quanxian.collection.action
 *@AddShouCang.java.java  
 *@����ʱ��:2011-8-21����11:25:21
 *@���ߣ�xupengfei
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.quanxian.collection.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import xx.collection.bean.Cztda;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zlzsddy;
import xx.collection.bean.Zysc;
import xx.collection.bean.ZyscId;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @AddShouCang <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class AddShouCang extends ActionSupport {
	
	@Resource(name="baseService")
	private BaseService service;
	private int zbh;
	private int zlbh;
	private int sxh;
	private int dt_th;
	private String userId;
	private int zdyflno;
	private int zylx;
	private int zybh;
	private String sskcmc;
	private String zyms;
	private String zdyflmc;
	private List<ListY> rows = new ArrayList<ListY>();
	private List<Yhzdymc> listy = new ArrayList<Yhzdymc>();	
	private Integer message;
	private int keci;
	private String sskc;
	private String ssz;
	private String zymc;
	private String zlid;//flex�˴�����������id
	private String path;
	
	@JSON(serialize=false)
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	
	/**
	 * @return the zlid
	 */
	@JSON(serialize=false)
	public String getZlid() {
		return zlid;
	}
	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(String zlid) {
		this.zlid = zlid;
	}
	@JSON(serialize=false)
	public int getKeci() {
		return keci;
	}
	public void setKeci(int keci) {
		this.keci = keci;
	}
	@JSON(serialize=false)
	public String getSskc() {
		return sskc;
	}
	public void setSskc(String sskc) {
		this.sskc = sskc;
	}
	@JSON(serialize=false)
	public String getSsz() {
		return ssz;
	}
	public void setSsz(String ssz) {
		this.ssz = ssz;
	}
	public Integer getMessage() {
		return message;
	}
	public void setMessage(Integer message) {
		this.message = message;
	}
	@JSON(serialize=false)
	public List<Yhzdymc> getListy() {
		return listy;
	}
	public void setListy(List<Yhzdymc> listy) {
		this.listy = listy;
	}

	public List<ListY> getRows() {
		return rows;
	}
	public void setRows(List<ListY> rows) {
		this.rows = rows;
	}
	@JSON(serialize=false)
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}
	@JSON(serialize=false)
	public int getZbh() {
		return zbh;
	}
	public void setZbh(int zbh) {
		this.zbh = zbh;
	}
	@JSON(serialize=false)
	public int getZlbh() {
		return zlbh;
	}
	public void setZlbh(int zlbh) {
		this.zlbh = zlbh;
	}
	@JSON(serialize=false)
	public int getSxh() {
		return sxh;
	}
	public void setSxh(int sxh) {
		this.sxh = sxh;
	}
	@JSON(serialize=false)
	public int getDt_th() {
		return dt_th;
	}
	public void setDt_th(int dt_th) {
		this.dt_th = dt_th;
	}
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
	public String getZdyflmc() {
		return zdyflmc;
	}
	public void setZdyflmc(String zdyflmc) {
		this.zdyflmc = zdyflmc;
	}
	@JSON(serialize=false)
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * zylx��1����ѡ���⣬2�����ж��⣬3���������,4�����ý�����ϣ�11�����Ѿ��������ɾ����ѡ��
	 * ��12�����Ѿ��������ɾ�����ж��⣬13�����Ѿ��������ɾ���Ĳ����⣬14�����Ѿ��������ɾ����
	 * ��ý�����ϣ�15�����ѧ�ƻ����ղصĶ�ý����Դ��
	 */
	
	/**
	 * @return the path
	 */
	
	/**
	 * �г��û��Զ����������
	 */
	@Action(value="/listyhzdy",results={@Result(name="success",type="json")})
	public String listyhzdy(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");              //��ȡ�û���¼id
		listy = this.service.findHql(Yhzdymc.class,"from Yhzdymc y where y.userinfo.userId='"+userId+"'"); //������û��Զ���ķ���
		List<ListY> listy1 = new ArrayList<ListY>();
		for(int i=0;i<listy.size();i++){
			
			ListY listy2 = new ListY();
			listy2.setId(i+1);
			listy2.setValue(listy.get(i).getZdyflmc());
			rows.add(listy2);                                            //���û��Զ��������ӵ�rows�Ա㷢�͵�ǰ̨
		}
		return SUCCESS;
	}
	/**
	 * @AddShouCang <code>AddShouCang</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ��ѡ������ղع��� 
	 */
	@Action(value="/addXzcollection",results={@Result(name="success",type="json"),
											  @Result(name="error",type="json")})
	public String addXzcollection(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");

		zylx = 1;                                       //��Դ����Ϊѡ����
		//zybh = 11;
		Zysc z = new Zysc();
		ZyscId i = new ZyscId();
		i.setUserId(userId);
		sxh = this.service.findMax("sxh","Zysc")+1;      //˳����㷨
		i.setSxh(sxh);                      
		z.setId(i);                 
		//========zdyflno=======================
		Yhzdymc y = new Yhzdymc();                       //����һ��Yhzdymc��
		String[] keys = new String[1];
		keys[0] = "zdyflmc";
		Object[] values = new Object[1];
		values[0]=zdyflmc;
		List<Yhzdymc> ll_1 = new ArrayList<Yhzdymc>(); 
		ll_1 = this.service.find(Yhzdymc.class,"Yhzdymc", keys, values); //���Զ������Ʋ���
		Yhzdymc ym = new Yhzdymc();
		if(ll_1.size()>0){                                      //������ҵ��˼�¼
			ym = ll_1.get(0);									//���鵽��ֵ����ym
			zdyflno = ym.getZdyflno();                          //��ò鵽���Զ��������
			y.setZdyflno(zdyflno);
			z.setYhzdymc(y);                                    //���ý�Ҫ�ղصļ�¼���Զ������Ϊy
		}
		else {    												//���û�в鵽���
			List<Userinfo> userlist = new ArrayList<Userinfo>();//׼�����û�
			userlist = this.service.findHql(Userinfo.class, "from Userinfo where userId='"+userId+"'");//���û�id���в���
			Userinfo userinfo = new Userinfo();//����һ���µ��û�
			userinfo = userlist.get(0);//�����ҵ����û������¶�����û�����
			ym.setUserinfo(userinfo);//�����Զ��������û�Ϊ�ղ鵽���û�
			ym.setZdyflmc(zdyflmc);//�����Զ�����������Ϊ�Ӻ�̨��ȡ����zdyflmc
			ym.setZysl(1);         //��������û��Զ����������zyslΪ1
			this.service.save(ym); //��������Զ������
			z.setYhzdymc(ym);      //���ý�Ҫ�ղص���Ŀ���Զ�����Ϊ�ղ��±�����û��Զ������
		}
		//=========sskcmc=======================
		z.setSskcmc(sskcmc);        //����Ҫ�ղص���Ŀ�����γ�
		z.setZybh(zybh);             //����Ҫ�ղص���Ŀ��Դ���
		z.setZylx(zylx);              //����Ҫ�ղص���Ŀ��Դ����
		z.setZyms(zyms);               //����Ҫ�ղص���Ŀ��Դ����
		List<Zysc> l = new ArrayList<Zysc>();
		l = this.service.findHql(Zysc.class, "from Zysc z where z.zylx='"+zylx+"' and z.zybh='"+zybh+"' and z.id.userId='"+userId+"'");   //�����ݿ��в���һ�¿�����Ŀ�Ƿ��ѱ����û��ղع�
		if(l.size()==0){             //���û���ղع�
			this.service.save(z);    //������ղ�
			message = 1;              //message Ϊ����ǰ̨����Ϣ��1��ʾû�б��ղع�
			if(ll_1.size()>0){
				int old = ll_1.get(0).getZysl();          //�ó���Դ����
				ym.setZysl(old+1);               		  //�����µ���Դ����
				this.service.update(ym);                    //�޸���Դ����
			}                                
			return SUCCESS;
		}
		else {                        //������ղع�
			if(ll_1.size()==0){       
				this.service.delete(ym);        //���ղſ����±�����û��Զ������ɾ��
			}
			message = 2;              //���ظ�ǰ̨��Ϣ��˵���û��Ѿ��ղع�����
			return ERROR;
		}
	}
	/**
	 * @AddShouCang <code>AddShouCang</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ���ж�����ղع��� 
	 */
	@Action(value="/addPdcollection",results={@Result(name="success",type="json"),
											  @Result(name="error",type="json")})
	public String addPdcollection(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		zylx = 2;
		Zysc z = new Zysc();
		ZyscId i = new ZyscId();
		i.setUserId(userId);
		sxh = this.service.findMax("sxh","Zysc")+1;
		i.setSxh(sxh);
		z.setId(i);
		//========zdyflno=======================
		Yhzdymc y = new Yhzdymc();
		String[] keys = new String[1];
		keys[0] = "zdyflmc";
		Object[] values = new Object[1];
		values[0]=zdyflmc;
		List<Yhzdymc> ll_1 = new ArrayList<Yhzdymc>();
		ll_1 = this.service.find(Yhzdymc.class,"Yhzdymc", keys, values);
		Yhzdymc ym = new Yhzdymc();
		if(ll_1.size()>0){
			ym = ll_1.get(0);
			zdyflno = ym.getZdyflno();
			y.setZdyflno(zdyflno);
			z.setYhzdymc(y);
		}
		else {
			List<Userinfo> userlist = new ArrayList<Userinfo>();
			userlist = this.service.findHql(Userinfo.class, "from Userinfo where userId='"+userId+"'");
			Userinfo userinfo = new Userinfo();
			userinfo = userlist.get(0);
			ym.setUserinfo(userinfo);
			ym.setZdyflmc(zdyflmc);
			ym.setZysl(1);
			this.service.save(ym);
			z.setYhzdymc(ym);
		}
		//=========sskcmc=======================
		z.setSskcmc(sskcmc);
		z.setZybh(zybh);
		z.setZylx(zylx);
		z.setZyms(zyms);
		List<Zysc> l = new ArrayList<Zysc>();
		l = this.service.findHql(Zysc.class, "from Zysc z where z.zylx='"+zylx+"' and z.zybh='"+zybh+"' and z.id.userId='"+userId+"'");
		if(l.size()==0){
			this.service.save(z);
			message = 1;
			if(ll_1.size()>0){
				int old = ll_1.get(0).getZysl();          //�ó���Դ����
				ym.setZysl(old+1);               		  //�����µ���Դ����
				this.service.update(ym);
			}//�޸���Դ���� 
			return SUCCESS;
		}
		else {
			if(ll_1.size()==0){
				this.service.delete(ym);
			}
			message = 2;
			return ERROR;
		}
	}
	/**
	 * @AddShouCang <code>AddShouCang</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ�ֶ�ý������ղع��� 
	 */
	@Action(value="/addDmtcollection",results={@Result(name="success",type="json"),
											   @Result(name="error",type="json")})
	public String addDmtcollection(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		Zysc z = new Zysc();
		ZyscId i = new ZyscId();
		i.setUserId(userId);
		sxh = this.service.findMax("sxh","Zysc")+1;
		i.setSxh(sxh);
		z.setId(i);
		//========zdyflno=======================
//		
		Yhzdymc y = new Yhzdymc();
		String[] keys = new String[1];
		keys[0] = "zdyflmc";
		Object[] values = new Object[1];
		values[0]=zdyflmc;
		List<Yhzdymc> ll_1 = new ArrayList<Yhzdymc>();
		ll_1 = this.service.find(Yhzdymc.class,"Yhzdymc", keys, values);
		Yhzdymc ym = new Yhzdymc();
		if(ll_1.size()>0){
			ym = ll_1.get(0);
			zdyflno = ym.getZdyflno();
			y.setZdyflno(zdyflno);
			z.setYhzdymc(y);
		}
		else {
			List<Userinfo> userlist = new ArrayList<Userinfo>();
			userlist = this.service.findHql(Userinfo.class, "from Userinfo where userId='"+userId+"'");
			Userinfo userinfo = new Userinfo();
			userinfo = userlist.get(0);
			ym.setUserinfo(userinfo);
			ym.setZdyflmc(zdyflmc);
			ym.setZysl(1);
			this.service.save(ym);
			z.setYhzdymc(ym);
		}
		
		//==============sskcmc==================
		List<Zlzsddy> list = this.service.findHql(Zlzsddy.class, "from Zlzsddy z where z.id.zlbh='"+zybh+"'");
		
		String sskcmc = null;
		if(list.size()==0){
			sskcmc = "��������";
		}
		else {
			int n = list.get(0).getId().getZbh();
			List<CourseChapter> listjie = this.service.findHql(CourseChapter.class, "from CourseChapter c where c.zbh="+n);
			sskcmc = listjie.get(0).getCName();
		}
		z.setSskcmc(sskcmc);
		z.setZybh(zybh);
		z.setZylx(zylx);
		z.setZyms(zyms);
		List<Zysc> ll = new ArrayList<Zysc>();
		ll = this.service.findHql(Zysc.class, "from Zysc z where z.zylx='"+zylx+"' and z.zybh='"+zybh+"' and z.id.userId='"+userId+"'");
		if(ll.size()==0){
			this.service.save(z);
			message = 1;
			if(ll_1.size()>0){
				int old = ll_1.get(0).getZysl();          //�ó���Դ����
				ym.setZysl(old+1);               		  //�����µ���Դ����
				this.service.update(ym);
			}//�޸���Դ���� 
			return SUCCESS;
		}
		else {
			if(ll_1.size()==0){
				this.service.delete(ym);
			}
			message = 2;
			return ERROR;
		}
	
	}
	/**
	 * @AddShouCang <code>AddShouCang</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ�ֽ�ѧ�ƻ��е���Դ�ղع��� 
	 */
	@Action(value="/addDmtcollection1", results={@Result(name="success", type="json")})
	public String addDmtcollection1(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		//ʵ����Ҫ ��д������ ��Ҫ�������У� keci sskc ssz zylx zybh zyms
		Date nowtime = new Date();
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		String strtime = dateFm.format(nowtime);
		//keci = 1;
//		sskc = "ʵ��γ�";
//		ssz = "ʵ����";
//		zybh = 222;
//		zymc = "ʵ��";
		zylx = 15;
		String zyma = "";
		zyma = ssz+"`"+keci+"`"+zymc+"`"+strtime+"`"+path;
		
		String pinchuan = sskc;
		//======
		Zysc z = new Zysc();
		ZyscId i = new ZyscId();
		i.setUserId(userId);
		
		try {
			sxh = this.service.findMax("sxh","Zysc")+1;
		} catch (Exception e) {
			sxh = 1;
		}
		i.setSxh(sxh);
		z.setId(i);
		
		//========zdyflno=======================
		
		//==============sskcmc==================
		z.setSskcmc(pinchuan);
		int ii = Integer.parseInt(zlid);//������idת����int���
		zybh = ii;
		z.setZybh(ii);
		z.setZylx(zylx);
		z.setZyms(zyma);
		List<Zysc> ll = new ArrayList<Zysc>();
		ll = this.service.findHql(Zysc.class, "from Zysc z where z.zylx='"+zylx+"' and z.zybh='"+zybh+"' and z.id.userId='"+userId+"'");
		if(ll.size()==0){
			this.service.save(z);
			message = 1;
		}
		else {
			message = 2;
		}
		
		return SUCCESS;
	
	}
	
	/**
	 * @AddShouCang <code>AddShouCang</code>
	 * @author  ������
	 * @version 2011-8-21����11:25:21
	 * @ʵ�ֲ�������ղع��� 
	 */
	@Action(value="/addCztcollection",results={@Result(name="success",type="json"),
											   @Result(name="error",type="json")})
	public String addCztcollection(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		zylx = 3;
		Zysc z = new Zysc();
		ZyscId i = new ZyscId();
		i.setUserId(userId);
		sxh = this.service.findMax("sxh","Zysc")+1;
		i.setSxh(sxh);
		z.setId(i);
		//========zdyflno=======================
//		
		Yhzdymc y = new Yhzdymc();
		String[] keys = new String[1];
		keys[0] = "zdyflmc";
		Object[] values = new Object[1];
		values[0]=zdyflmc;
		List<Yhzdymc> ll_1 = new ArrayList<Yhzdymc>();
		ll_1 = this.service.find(Yhzdymc.class,"Yhzdymc", keys, values);
		Yhzdymc ym = new Yhzdymc();
		if(ll_1.size()>0){
			ym = ll_1.get(0);
			zdyflno = ym.getZdyflno();
			y.setZdyflno(zdyflno);
			z.setYhzdymc(y);
		}
		else {
			List<Userinfo> userlist = new ArrayList<Userinfo>();
			userlist = this.service.findHql(Userinfo.class, "from Userinfo where userId='"+userId+"'");
			Userinfo userinfo = new Userinfo();
			userinfo = userlist.get(0);
			ym.setUserinfo(userinfo);
			ym.setZdyflmc(zdyflmc);
			ym.setZysl(1);
			this.service.save(ym);
			z.setYhzdymc(ym);
		}
		//==============sskcmc==================
		List<Cztda> list = this.service.findHql(Cztda.class, "from Cztda c where c.id.dtTh='"+zybh+"'");
		String sskcmc = null;
		if(list.size()==0){
			sskcmc = "��������";
		}
		else {
			int n = list.get(0).getZsd().getId().getZbh();
			List<CourseChapter> listjie = this.service.findHql(CourseChapter.class, "from CourseChapter c where c.zbh="+n);
			sskcmc = listjie.get(0).getCName();
		}
		z.setSskcmc(sskcmc);
		z.setZybh(zybh);
		z.setZylx(zylx);
		z.setZyms(zyms);
		List<Zysc> ll = new ArrayList<Zysc>();
		ll = this.service.findHql(Zysc.class, "from Zysc z where z.zylx='"+zylx+"' and z.zybh='"+zybh+"' and z.id.userId='"+userId+"'");
		if(ll.size()==0){
			this.service.save(z);
			message = 1;
			if(ll_1.size()>0){
				int old = ll_1.get(0).getZysl();          //�ó���Դ����
				ym.setZysl(old+1);               		  //�����µ���Դ����
				this.service.update(ym);                    //�޸���Դ���� 
			}                                             
			return SUCCESS;
		}
		else {
			if(ll_1.size()==0){
				this.service.delete(ym);
			}
			message = 2;
			return ERROR;
		}
	}
}
