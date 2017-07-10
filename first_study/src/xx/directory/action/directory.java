package xx.directory.action;


import java.net.URLDecoder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

import xx.collection.bean.Node;
import xx.collection.bean.Nodec;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Studentifno;
import xx.collection.bean.Teacher;
import xx.collection.bean.US;

import xx.collection.bean.GroupInfo;

import xx.collection.bean.Friends;
import xx.collection.bean.Roles;
import xx.collection.bean.Userinfo;
import xx.quanxian.service.BaseService;
import xx.dao.PublicDao;
import xx.directory.service.directoryService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Scope("prototype")
@Namespace("directory")
@ParentPackage("default-package")
public class directory extends ActionSupport {
	
	private Friends friends;
	

	private Integer no;
	private String friendid;
	private String friendname;
	private String mark;
    private String userid;
	private int total;	
	private int page;		
	int rows_s;//分页时，当前的页数，从前台接收    
	
	private GroupInfo groupinfo;
	private Integer id;
	private String groupname;
	private String memberg;
	private String memberid;
	
	private Roles role;
	private Integer roleid;
	private String queryType;						//查询功能时的查询参数，所要根据查询的类型
	private String queryWord;			//查询功能时的查询参数，所要根据查询的关键字
	
	private Studentifno studentifno;
	private String SNo;
	private String SName;
	private Userinfo userinfo;
	private String userId;
	
	private Teacher teacher;
	private String jsxm;
	
	private US us;
	private String tip;
	private String newgroupname;
	

	private List rows = new ArrayList();
	//List<GroupInfo> rows = new ArrayList<GroupInfo>();
	//List<US> rows = new ArrayList<US>();

	
	@Resource(name="baseService")
    private BaseService baseService; 
	
	
	@Resource(name="directoryService")
	private directoryService directoryService; 

	



	public directoryService getDirectoryService() {
		return directoryService;
	}

	public void setDirectoryService(directoryService directoryService) {
		this.directoryService = directoryService;
	}

	public List getRows() {
		return rows;
	}

	@JSON(deserialize=true)
	public void setRows(List rows) {
		this.rows = rows;
	}

	public Studentifno getStudentifno() {
		return studentifno;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getJsxm() {
		return jsxm;
	}

	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

	public void setStudentifno(Studentifno studentifno) {
		this.studentifno = studentifno;
	}

	public String getSNo() {
		return SNo;
	}
	public void setSNo(String sNo) {
		SNo = sNo;
	}

	public String getSName() {
		return SName;
	}

	public void setSName(String sName) {
		SName = sName;
	}

	
	public String getNewgroupname() {
		return newgroupname;
	}

	public void setNewgroupname(String newgroupname) {
		this.newgroupname = newgroupname;
	}


	


	public String getTip() {
		return tip;
	}


	@JSON(deserialize=true)
	public void setTip(String tip) {
		this.tip = tip;
	}


	
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
	public US getUs() {
		return us;
	}

	@JSON(deserialize=true)
	public void setUs(US us) {
		this.us = us;
	}

	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}

	@JSON(deserialize=true)
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JSON(serialize=false)
	public Roles getRole() {
		return role;
	}

	@JSON(deserialize=true)
	public void setRole(Roles role) {
		this.role = role;
	}

	@JSON(serialize=false)
	public Integer getRoleid() {
		return roleid;
	}

	@JSON(deserialize=true)
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	

	

    
	@JSON(serialize=false)
	public Friends getFriends() {
		return friends;
	}

	public void setFriends(Friends friends) {
		this.friends = friends;
	}
	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	@JSON(serialize=false)
	public Integer getNo() {
		return no;
	}

    @JSON(deserialize=true)
    public void setNo(Integer no) {
		this.no = no;
	}

	@JSON(serialize=false)
	public String getFriendid() {
		return friendid;
	}

	@JSON(deserialize=true)
	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	@JSON(serialize=false)
	public String getFriendname() {
		return friendname;
	}

	@JSON(deserialize=true)
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}

	@JSON(serialize=false)
	public String getMark() {
		return mark;
	}

	@JSON(deserialize=true)
	public void setMark(String mark) {
		this.mark = mark;
	}



	@JSON(serialize=true)
	public String getUserid() {
		return userid;
	}
	@JSON(deserialize=true)
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@JSON(serialize=false)
	public GroupInfo getGroupinfo() {
		return groupinfo;
	}
	@JSON(deserialize=true)
	public void setGroupinfo(GroupInfo groupinfo) {
		this.groupinfo = groupinfo;
	}

	@JSON(serialize=false)
	public Integer getId() {
		return id;
	}

	@JSON(deserialize=true)
	public void setId(Integer id) {
		this.id = id;
	}

	@JSON(serialize=false)
	public Userinfo getUserinfo() {
		return userinfo;
	}

	@JSON(deserialize=true)
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@JSON(serialize=false)
	public String getGroupname() {
		return groupname;
	}

	@JSON(deserialize=true)
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@JSON(serialize=false)
	public String getMemberg() {
		return memberg;
	}

	@JSON(deserialize=true)
	public void setMemberg(String memberg) {
		this.memberg = memberg;
	}

	

	

	@JSON(serialize=true)
	public String getMemberid() {
		return memberid;
	}

	@JSON(deserialize=true)
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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
	
	@Action(value="/deleteFriend",results={@Result(name="success",type="json")})
	public String deletefriend() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add(0, "userid");
		keys.add(1,"friendid");
		values.add(0,userid);
		String[]s=friendid.split(",");
		for(int i=0;i<s.length;i++){
			values.add(1,s[i]);
			this.directoryService.delete(Friends.class,"Friends", keys, values);
		}
		return "success";
	}
  
	@Action(value="/savefriend")
	public void saveFriend()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=session.getAttribute("userid").toString();
		Friends friends=new Friends();
		friends.setUserid(userid);
		friends.setfriendid(friendid);
		friends.setfriendname(friendname);
		friends.setmark(mark);
		this.baseService.save(friends);
		
	}
	
    	
//	@Action(value="/friend_m",results={@Result(name="success",location="/page/txl/txlm.jsp")})
//	public String loginjsp()throws Exception{
//		HttpSession hs=ServletActionContext.getRequest().getSession();
//		HttpSession session=ServletActionContext.getRequest().getSession();
//		Userinfo user = (Userinfo)hs.getAttribute("userifno");
//		userid = user.getUserId();
//		session.setAttribute("userid", userid);
//		return "success";
//	}
	@Action(value="/json1",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String listFriends() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add("userid");values.add(userid);
		rows = this.directoryService.find(Friends.class, "Friends", keys, values, page, rows_s);
		total = this.directoryService.gettotal(Friends.class, "Friends", keys, values);
		return "root";
	} 
	@Action(value="/json2",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String listgroup()throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add("userid");keys.add("memberg");values.add(userid);values.add(userid);
		total = this.directoryService.gettotal(GroupInfo.class, "GroupInfo", keys, values);
		rows = this.directoryService.find(GroupInfo.class, "GroupInfo", keys, values, 1, total);
		return "root";
	}
	@Action(value="/savegroup")
	public String savegroup()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		GroupInfo groupinfo = new GroupInfo();
		Userinfo userinfo = new Userinfo();
		userinfo.setUserId(userid);
		groupinfo.setUserinfo(userinfo);
		groupinfo.setGroupname(groupname);
		groupinfo.setMemberg(userid);
		groupinfo.setMemberid(userid);
		this.baseService.save(groupinfo);
		return null;
	}
	@Action(value="/deletegroup")
	public String detelegroup()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add(0, "userid");
		keys.add(1,"groupname");
		values.add(0,userid);
		values.add(1,groupname);
		this.directoryService.delete(GroupInfo.class, "GroupInfo", keys, values);
		return null;
	}
	@Action(value="/listmember",results={@Result(name="success",type="json",params={"includeProperties","rows.*,total"})})
	public String listmember()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		groupname=URLDecoder.decode(URLDecoder.decode(groupname, "utf-8"),"utf-8"); 
		List<GroupInfo> tem=new ArrayList<GroupInfo>();
		List<GroupInfo> tem1=new ArrayList<GroupInfo>();
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add(0, "userid");keys.add(1,"groupname");
		values.add(0,userid);values.add(1,groupname);
		total = this.directoryService.gettotal(GroupInfo.class, "GroupInfo", keys, values)-1;
		tem = this.directoryService.find(GroupInfo.class, "GroupInfo", keys, values,1, total+1);
		for(GroupInfo g:tem){
			if(g.getMemberg().equals(userid)==false){
				tem1.add(g);
			}
		}
		rows=tem1;
		return "success";
	}
	@Action(value="/groupinvite")
	public String groupinvite()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		GroupInfo groupinfo = new GroupInfo();
		Userinfo userinfo = new Userinfo();
		userinfo.setUserId(userid);
		groupinfo.setUserinfo(userinfo);
		groupinfo.setGroupname(groupname);
		groupinfo.setMemberid(memberid);
		groupinfo.setMemberg(memberg);
		this.baseService.save(groupinfo);
		return null;
	}
	
	@Action(value="/memberdelete",results={@Result(name="success",type="json",params={"includeProperties","tip"})})
	public String deletem()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		if(memberid.equals(userid)){
			tip=memberid;
		}else{
		    List<String> keys=new ArrayList<String>();
		    List<String> values=new ArrayList<String>();
		    keys.add(0,"userid");keys.add(1,"groupname");keys.add(2,"memberid");
		    values.add(0,userid);values.add(1,groupname);values.add(2,memberid);
		    this.directoryService.delete(GroupInfo.class, "GroupInfo", keys, values);
		}
		return "success";
	}
	

	@Action(value="/queryusers",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String queryUsers(){
	
	    rows=this.directoryService.queryTS(queryType, queryWord, page, rows_s);
	    total = this.directoryService.queryTStotal(queryType, queryWord);
		return "root";
	}
	@Action(value="/checkfriend",results={@Result(name="root",type="json",params={"includeProperties","tip"})})
	public String checkfriend(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		List<Friends> rs = new ArrayList<Friends>();
		rs = this.baseService.find(Friends.class);
			for(Friends r:rs){
				if(friendid.equals(r.getfriendid())&&userid.equals(r.getUserid())){
					tip = r.getfriendid();
					break;
				}
			}
		return "root";
	}
	
	@Action(value="/checkgroup",results={@Result(name="root",type="json",params={"includeProperties","tip"})})
	public String checkgroup(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		List<GroupInfo> rs = new ArrayList<GroupInfo>();
		rs = this.baseService.find(GroupInfo.class);
			for(GroupInfo r:rs){
				if(groupname.equals(r.getGroupname())&&userid.equals(r.getUserinfo().getUserId())){
					tip = r.getGroupname();
					break;
				}
			}
		return "root";
	}
	@Action(value="/checkmember",results={@Result(name="root",type="json",params={"includeProperties","tip"})})
	public String checkmember(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		String hql = "from GroupInfo g where g.userinfo.userId='"+userid+"' and g.groupname='"+groupname+"' and g.memberid='"+memberid+"'";
		List<GroupInfo> temp =  new ArrayList<GroupInfo>();
		GroupInfo rs; 
		temp= this.baseService.findHql(GroupInfo.class, hql);
		if(temp.size() > 0) {
			rs=temp.get(0);
			if(memberid.equals(rs.getMemberid())){
				tip = rs.getMemberid();
			}
		}
		
		return "root";
	}
	@Action(value="/updatefriend")
	public String updatefriend(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		String hql = "update Friends set mark='"+mark+"' where userid='"+userid+"' and friendid='"+friendid+"'";
		this.directoryService.updateByHql(hql);
		return null;
	}
	@Action(value="/updategroupname")
	public String updategroupname(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		String hql = "update GroupInfo set groupname='"+newgroupname+"' where userid='"+userid+"' and groupname='"+groupname+"'";
	    this.directoryService.updateByHql(hql);
		return null;
	}
	@Action(value="/queryfriend",results={@Result(name="root",type="json",params={"includeProperties","rows.*,total"})})
	public String queryfriend(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
	    rows=this.directoryService.queryFriend(userid, queryType, queryWord, page, rows_s);
		total = rows.size();
		return "root";
	}
	@Action(value="/listallmember",results={@Result(name="ok",type="json",params={"includeProperties","rows.*,userid,total"})})
	public String listallmember(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		String hql = "from GroupInfo where userid='"+userid+"'";
		rows=this.directoryService.findByHql(GroupInfo.class, hql);
		total=rows.size();
		return "ok";
	}
	/*
	 * 发送消息界面以树形结构列出所有好友
	 */
	@Action(value="/listallbody",results={@Result(name="success",type="json",params={"includeProperties","rows.*"})})
	public String listallbody(){
		List<Friends> f = new ArrayList<Friends>();
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		String hql = "from Friends where userid='"+userid+"'";
		f = this.directoryService.findByHql(Friends.class, hql);
		List<Node> itemsp = new ArrayList<Node>();
		List<Nodec> itemsc = new ArrayList<Nodec>();
		Node node = new Node();
		node.setText( "我的好友");
		for(Friends ff:f){
			Nodec nodec = new Nodec();
			nodec.setId(ff.getfriendid());
			nodec.setText(ff.getfriendname());
			nodec.setIconCls("icon-friend");
			itemsc.add(nodec);
			
		}
		node.setState( "open");
		node.setChildren( itemsc);
		node.setIconCls("icon-group");
		itemsp.add(node);
		rows=itemsp;
		return "success";
	}
	/*
	 * 在发送消息界面以树形结构列出所有群组及其成员
	 */
	@Action(value="/grouptree",results={@Result(name="success",type="json",params={"includeProperties","rows.*,userid"})})
	public String grouptree(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		userid=(String)session.getAttribute("userid");
		List<GroupInfo> g=new ArrayList<GroupInfo>();
		String hql="from GroupInfo where userid='"+userid+"'";
		g=this.directoryService.findByHql(GroupInfo.class, hql);
		List<Node> itemsp = new ArrayList<Node>();
		for(GroupInfo gg:g){
			if(gg.getMemberg().equals(gg.getMemberid())&&gg.getUserinfo().getUserId().equals(gg.getMemberid())){
				List<Nodec> itemsc = new ArrayList<Nodec>();
				Node node=new Node();
				node.setId(gg.getMemberid());
				node.setText(gg.getGroupname());
				node.setIconCls("icon-group");
				node.setState("closed");
				for(GroupInfo ggg:g){
					if(ggg.getMemberg().equals(ggg.getMemberid())==false&&ggg.getGroupname().equals(gg.getGroupname())){
						Nodec nodec=new Nodec();
						nodec.setId(ggg.getMemberid());
						nodec.setText(ggg.getMemberg());
						nodec.setIconCls("icon-friend");
						itemsc.add(nodec);
					}
				}
				if(itemsc.size()==0){
					Nodec nodec=new Nodec();
					nodec.setText("暂无成员");
					nodec.setIconCls("icon-cancel");
					itemsc.add(nodec);
				}
				node.setChildren(itemsc);
				itemsp.add(node);
			}
		}
		rows=itemsp;
		return "success";
	}

}
