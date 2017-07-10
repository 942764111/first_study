/*
 *@(#)xx.quanxian.collection.action
 *@AddShouCang.java.java  
 *@创建时间:2011-8-21上午11:25:21
 *@作者：xupengfei
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
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
 * @AddShouCang <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
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
	private String zlid;//flex端传过来的资料id
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
	 * zylx：1代表选择题，2代表判断题，3代表操作题,4代表多媒体资料，11代表已经从题库中删除的选择
	 * ，12代表已经从题库中删除的判断题，13代表已经从题库中删除的操作题，14代表已经从题库中删除的
	 * 多媒体资料，15代表教学计划中收藏的多媒体资源。
	 */
	
	/**
	 * @return the path
	 */
	
	/**
	 * 列出用户自定义分类名称
	 */
	@Action(value="/listyhzdy",results={@Result(name="success",type="json")})
	public String listyhzdy(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");              //获取用户登录id
		listy = this.service.findHql(Yhzdymc.class,"from Yhzdymc y where y.userinfo.userId='"+userId+"'"); //查出该用户自定义的分类
		List<ListY> listy1 = new ArrayList<ListY>();
		for(int i=0;i<listy.size();i++){
			
			ListY listy2 = new ListY();
			listy2.setId(i+1);
			listy2.setValue(listy.get(i).getZdyflmc());
			rows.add(listy2);                                            //将用户自定义分类添加到rows以便发送到前台
		}
		return SUCCESS;
	}
	/**
	 * @AddShouCang <code>AddShouCang</code>
	 * @author  徐鹏飞
	 * @version 2011-8-21上午11:25:21
	 * @实现选择题的收藏功能 
	 */
	@Action(value="/addXzcollection",results={@Result(name="success",type="json"),
											  @Result(name="error",type="json")})
	public String addXzcollection(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");

		zylx = 1;                                       //资源类型为选择题
		//zybh = 11;
		Zysc z = new Zysc();
		ZyscId i = new ZyscId();
		i.setUserId(userId);
		sxh = this.service.findMax("sxh","Zysc")+1;      //顺序号算法
		i.setSxh(sxh);                      
		z.setId(i);                 
		//========zdyflno=======================
		Yhzdymc y = new Yhzdymc();                       //定义一个Yhzdymc类
		String[] keys = new String[1];
		keys[0] = "zdyflmc";
		Object[] values = new Object[1];
		values[0]=zdyflmc;
		List<Yhzdymc> ll_1 = new ArrayList<Yhzdymc>(); 
		ll_1 = this.service.find(Yhzdymc.class,"Yhzdymc", keys, values); //按自定义名称查找
		Yhzdymc ym = new Yhzdymc();
		if(ll_1.size()>0){                                      //如果查找到了记录
			ym = ll_1.get(0);									//将查到的值赋给ym
			zdyflno = ym.getZdyflno();                          //获得查到的自定义分类编号
			y.setZdyflno(zdyflno);
			z.setYhzdymc(y);                                    //设置将要收藏的记录的自定义分类为y
		}
		else {    												//如果没有查到结果
			List<Userinfo> userlist = new ArrayList<Userinfo>();//准备查用户
			userlist = this.service.findHql(Userinfo.class, "from Userinfo where userId='"+userId+"'");//按用户id进行查找
			Userinfo userinfo = new Userinfo();//定义一个新的用户
			userinfo = userlist.get(0);//将查找到的用户赋给新定义的用户对象
			ym.setUserinfo(userinfo);//设置自定义分类的用户为刚查到的用户
			ym.setZdyflmc(zdyflmc);//设置自定义分类的名称为从后台获取到的zdyflmc
			ym.setZysl(1);         //设置这个用户自定义分类对象的zysl为1
			this.service.save(ym); //保存这个自定义分类
			z.setYhzdymc(ym);      //设置将要收藏的题目的自定分类为刚才新保存的用户自定义分类
		}
		//=========sskcmc=======================
		z.setSskcmc(sskcmc);        //设置要收藏的题目所属课程
		z.setZybh(zybh);             //设置要收藏的题目资源编号
		z.setZylx(zylx);              //设置要收藏的题目资源类型
		z.setZyms(zyms);               //设置要收藏的题目资源描述
		List<Zysc> l = new ArrayList<Zysc>();
		l = this.service.findHql(Zysc.class, "from Zysc z where z.zylx='"+zylx+"' and z.zybh='"+zybh+"' and z.id.userId='"+userId+"'");   //从数据库中查找一下看该题目是否已被该用户收藏过
		if(l.size()==0){             //如果没被收藏过
			this.service.save(z);    //保存该收藏
			message = 1;              //message 为返回前台的信息，1表示没有被收藏过
			if(ll_1.size()>0){
				int old = ll_1.get(0).getZysl();          //拿出资源数量
				ym.setZysl(old+1);               		  //设置新的资源数量
				this.service.update(ym);                    //修改资源数量
			}                                
			return SUCCESS;
		}
		else {                        //如果被收藏过
			if(ll_1.size()==0){       
				this.service.delete(ym);        //将刚才可能新保存的用户自定义分类删除
			}
			message = 2;              //返回给前台信息，说明用户已经收藏过此题
			return ERROR;
		}
	}
	/**
	 * @AddShouCang <code>AddShouCang</code>
	 * @author  徐鹏飞
	 * @version 2011-8-21上午11:25:21
	 * @实现判断题的收藏功能 
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
				int old = ll_1.get(0).getZysl();          //拿出资源数量
				ym.setZysl(old+1);               		  //设置新的资源数量
				this.service.update(ym);
			}//修改资源数量 
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
	 * @author  徐鹏飞
	 * @version 2011-8-21上午11:25:21
	 * @实现多媒体题的收藏功能 
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
			sskcmc = "无章名称";
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
				int old = ll_1.get(0).getZysl();          //拿出资源数量
				ym.setZysl(old+1);               		  //设置新的资源数量
				this.service.update(ym);
			}//修改资源数量 
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
	 * @author  徐鹏飞
	 * @version 2011-8-21上午11:25:21
	 * @实现教学计划中的资源收藏功能 
	 */
	@Action(value="/addDmtcollection1", results={@Result(name="success", type="json")})
	public String addDmtcollection1(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		//实验需要 先写假数据 需要的数据有： keci sskc ssz zylx zybh zyms
		Date nowtime = new Date();
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		String strtime = dateFm.format(nowtime);
		//keci = 1;
//		sskc = "实验课程";
//		ssz = "实验章";
//		zybh = 222;
//		zymc = "实验";
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
		int ii = Integer.parseInt(zlid);//将资料id转换成int存库
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
	 * @author  徐鹏飞
	 * @version 2011-8-21上午11:25:21
	 * @实现操作题的收藏功能 
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
			sskcmc = "无章名称";
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
				int old = ll_1.get(0).getZysl();          //拿出资源数量
				ym.setZysl(old+1);               		  //设置新的资源数量
				this.service.update(ym);                    //修改资源数量 
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
