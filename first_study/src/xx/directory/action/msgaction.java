/*
 *@(#)xx.directory.action
 *@msgaction.java.java  
 *@创建时间:2011-8-13上午09:57:57
 *@作者：tongkesong
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.directory.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xx.collection.bean.GroupInfo;
import xx.collection.bean.MTTS;
import xx.collection.bean.Msginfo;
import xx.collection.bean.Userinfo;
import xx.directory.service.directoryService;
import xx.quanxian.service.BaseService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @msgaction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
/**
 * @msgaction <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
/**
 * @msgaction <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("msgaction")
@ParentPackage("default-package")
public class msgaction extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseService; 
	@Resource(name="directoryService")
	private directoryService directorservice; 
	
	
	private Msginfo msginfo;
	
	private Integer msgid;
	private Userinfo userinfo;
	private String userid;
	private String senderid;
	private String msgTitle;
	private String msgcomments;
	private Date sendtime;
	private Date receivedtime;
	private Integer msgstate;
	
	private String tip;
	private int page;
	private int rows_s;
	private List rows = new ArrayList();
	private int total;
	List<String> users = new ArrayList<String>();
	
	private int sf = 0;//判断用户是否有未读消息
	
	
	/**
	 * @return the sf
	 */
	public int getSf() {
		return sf;
	}
	/**
	 * @param sf the sf to set
	 */
	public void setSf(int sf) {
		this.sf = sf;
	}
	@JSON(serialize=true)
	public String getTip() {
		return tip;
	}
	@JSON(deserialize=true)
	public void setTip(String tip) {
		this.tip = tip;
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

	public void setRows_s(int rowsS) {
		rows_s = rowsS;
	}
	@JSON(serialize=true)
	public List getRows() {
		return rows;
	}
	@JSON(deserialize=true)
	public void setRows(List rows) {
		this.rows = rows;
	}
	@JSON(serialize=true)
	public int getTotal() {
		return total;
	}
	@JSON(deserialize=true)
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(serialize=true)
	public List<String> getUsers() {
		return users;
	}
	@JSON(deserialize=true)
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	
	public directoryService getDirectorservice() {
		return directorservice;
	}
	public void setDirectorservice(directoryService directorservice) {
		this.directorservice = directorservice;
	}
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	@JSON(serialize=false)
	public Msginfo getMsginfo() {
		return msginfo;
	}
	public void setMsginfo(Msginfo msginfo) {
		this.msginfo = msginfo;
	}
	public Integer getMsgid() {
		return msgid;
	}
	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	
	public String getUserid() {
		return userid;
	}
	@JSON(deserialize=true)
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@JSON(serialize=true)
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	@JSON(serialize=false)
	public String getMsgTitle() {
		return msgTitle;
	}
	@JSON(deserialize=true)
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	@JSON(serialize=false)
	public String getMsgcomments() {
		return msgcomments;
	}
	@JSON(deserialize=true)
	public void setMsgcomments(String msgcomments) {
		this.msgcomments = msgcomments;
	}
	@JSON(serialize=false)
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public Date getReceivedtime() {
		return receivedtime;
	}
	public void setReceivedtime(Date receivedtime) {
		this.receivedtime = receivedtime;
	}
	public Integer getMsgstate() {
		return msgstate;
	}
	public void setMsgstate(Integer msgstate) {
		this.msgstate = msgstate;
	}
	
//	@Action(value="/msg",results={@Result(name="success",location="/page/msg/msg.jsp")})
//	public String loginmsgjsp()throws Exception{
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Userinfo user = (Userinfo)session.getAttribute("userifno");
//		userid = user.getUserId();
//		session.setAttribute("userid", userid);
//		return "success";
//	}
//	@Action(value="/msg_manage",results={@Result(name="success",location="/page/msg/msgmanage.jsp")})
//	public String msgmanage()throws Exception{
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Userinfo user = (Userinfo)session.getAttribute("userifno");
//		userid = user.getUserId();
//		session.setAttribute("userid", userid);
//		return "success";
//	}
	
	@Action(value="/savemsg")
	public void savemsg() throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		senderid=(String)session.getAttribute("userid");
		int sb=0;
		int se=0;
		boolean flag=false;
		String userlist = userid;
		for(int i=0;i<userlist.length();i++){
			char c = userlist.charAt(i);
			if(c=='<'){
				sb=i;
				flag=true;
			}
			if(flag&&c=='>'){
				se=i;
				flag=false;
				userid=userlist.substring(sb+1,se);
		Userinfo userinfo = new Userinfo();
		userinfo.setUserId(userid);
		Msginfo msginfo = new Msginfo();
		msginfo.setUserinfo(userinfo);
		msginfo.setSenderid(senderid);
		msginfo.setMsgcomments(msgcomments);
		Date d = new Date();
		msginfo.setSendtime(d);
		msginfo.setMsgTitle(msgTitle);
		this.directorservice.save(msginfo);
			}
		}
	}
	@Action(value="/listmsg",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String listmsg(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Msginfo> msglist = new ArrayList<Msginfo> ();
		List<MTTS> tem = new ArrayList<MTTS> ();
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add(0, "userid");
		values.add(0,userid);
		msglist = this.directorservice.find(Msginfo.class, "Msginfo", keys, values, page, rows_s);
		for(Msginfo msg:msglist){
			MTTS mtts = new MTTS();
			mtts.setMsgcomments(msg.getMsgcomments());
			mtts.setMsgid(msg.getMsgid());
			mtts.setMsgTitle(msg.getMsgTitle());
			if(msg.getMsgstate()!=null)
			mtts.setMsgstate(msg.getMsgstate());
			mtts.setSenderid(msg.getSenderid());
			mtts.setUserinfo(msg.getUserinfo());
			mtts.setSendtime(df.format(msg.getSendtime()));
			if(msg.getReceivedtime()!=null)
			mtts.setReceivedtime(df.format(msg.getReceivedtime()));
			tem.add(mtts);
		}
		rows=tem;
		total = this.directorservice.gettotal(Msginfo.class, "Msginfo", keys, values);
		return "root";
	}
	
	//判断用户是否有未读消息
	@Action(value="/sfmsg",results={@Result(name="root",type="json",params={"includeProperties","sf"})})
	public String sfmsg(){
		String hql = "select msgstate from Msginfo where userinfo.userId='"+userid+"'";
		List<Integer> msgstates = this.baseService.findHql(Integer.class, hql);
		if(msgstates.contains(0)||msgstates.contains(null)){
			sf = 1;
		}
		return "root";
	}
	
	@Action(value="/toread",results={@Result(name="root",type="json",params={"includeProperties","tip"})})
	public String toread(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tip=format.format(new Date());
		String hql="update Msginfo set receivedtime='"+tip+"', msgstate=1 where msgid="+msgid;
		System.out.println(hql);
		this.directorservice.updateByHql(hql);
		return "root";
	}
	@Action(value="/revertmsg")
	public void revertmsg(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		senderid=(String)session.getAttribute("userid");
		Msginfo msg = new Msginfo();
		msg.setMsgcomments(msgcomments);
		msg.setMsgTitle(msgTitle);
		msg.setSenderid(senderid);
		Date d = new Date();
		msg.setSendtime(d);
		Userinfo userinfo = new Userinfo();
		userinfo.setUserId(userid);
		msg.setUserinfo(userinfo);
		this.directorservice.save(msg);
	}
	@Action(value="/delmsg")
	public void delmsg(){
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add(0, "msgid");
		values.add(0,msgid.toString());
		this.directorservice.delete(Msginfo.class, "Msginfo", keys, values);
	}
}
