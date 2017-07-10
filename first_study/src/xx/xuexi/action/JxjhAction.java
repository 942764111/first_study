



	/**
	 * �ļ�����JxjhAction.java
	 *
	 * �汾��Ϣ��
	 * ���ڣ�2011-8-5
	 * ���ߣ�tlq
	 * Copyright �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ Corporation 2011 
	 * ��Ȩ����
	 *
	 */
	
package xx.xuexi.action;

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

import xx.adminservice.JxjhService;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Jxjh;
import xx.collection.bean.JxjhYck;
import xx.collection.bean.Teacher;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
	/**
 * �����������ǣ�
 * @author: tlq
 * @version: 2011-8-5 ����04:56:26 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class JxjhAction extends ActionSupport {
    
	@Resource(name="baseService")
	private BaseService baseService;
	@Resource(name="jxjhservice")
	private JxjhService jxjhService;
	
	private Jxjh jxjh;
	private int page;								//��ҳʱ����ǰ��ҳ������ǰ̨����
	private int rows_s;								//��ҳʱ����ǰ��ҳ�����������ǰ̨����
	private int total;								//��ѯ����¼��������
	List<Object> rows = new ArrayList<Object>();
	
	List<Teacher> teachers = new ArrayList<Teacher>();
	List<CourseChapter> kcxxs = new ArrayList<CourseChapter>();
	
	private String kname;
	private String jhmc;
	private String xq;
	private int jhid;

	private int xsh=0;
	private String tip;
	private int kch;
	private String jsbh;
	private String queryword;
	
	
	private String courseName;
	private String tip1;
	private String[] arr;
	
	private String s1;//ѧ����ݵ�¼ʱflex�˴������Ĳ���
	
	
	/**
	 * @return the s1
	 */
	@JSON(serialize=false)
	public String getS1() {
		return s1;
	}
	/**
	 * @param s1 the s1 to set
	 */
	public void setS1(String s1) {
		this.s1 = s1;
	}
	/**
	 * @return the arr
	 */
	@JSON(serialize=false)
	public String[] getArr() {
		return arr;
	}
	/**
	 * @param arr the arr to set
	 */
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	/**
	 * @return the tip1
	 */
	public String getTip1() {
		return tip1;
	}
	/**
	 * @param tip1 the tip1 to set
	 */
	public void setTip1(String tip1) {
		this.tip1 = tip1;
	}

	
	/**
	 * @return the queryword
	 */
	@JSON(serialize=false)
	public String getQueryword() {
		return queryword;
	}
	/**
	 * @param queryword the queryword to set
	 */
	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}
	/**
	 * @return the kch
	 */
	@JSON(serialize=false)
	public int getKch() {
		return kch;
	}
	/**
	 * @param kch the kch to set
	 */
	public void setKch(int kch) {
		this.kch = kch;
	}
	/**
	 * @return the jsbh
	 */
	@JSON(serialize=false)
	public String getJsbh() {
		return jsbh;
	}
	/**
	 * @param jsbh the jsbh to set
	 */
	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	@JSON(serialize=false)
	public int getXsh() {
		return xsh;
	}

	public void setXsh(int xsh) {
		this.xsh = xsh;
	}

	
	@JSON(serialize=false)
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}

	public int getJhid() {
		return jhid;
	}
	
	public void setJhid(int jhid) {
		this.jhid = jhid;
	}
	
	@JSON(serialize=false)
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	@JSON(serialize=false)
	public String getJhmc() {
		return jhmc;
	}
	public void setJhmc(String jhmc) {
		this.jhmc = jhmc;
	}
	@JSON(serialize=false)
	public List<CourseChapter> getKcxxs() {
		return kcxxs;
	}
	public void setKcxxs(List<CourseChapter> kcxxs) {
		this.kcxxs = kcxxs;
	}
	@JSON(serialize=false)
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	@JSON(serialize=false)
	public Jxjh getJxjh() {
		return jxjh;
	}
	public void setJxjh(Jxjh jxjh) {
		this.jxjh = jxjh;
	}
	
	public int getPage() {
		return page;
	}
	@JSON(deserialize=true)
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows_s() {
		return rows_s;
	}
	@JSON(deserialize=true)
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}
	public int getTotal() {
		return total;
	}
	@JSON(deserialize=true)
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Object> getRows() {
		return rows;
	}
	@JSON(deserialize=true)
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	
	/**
	 * @return the courseName
	 */
	@JSON(serialize=false)
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * @{jxjh_tj_json.action}
	 * @param {} {Ϊ��ѧ�ƻ�ͳ��ҳ���ṩjson����}
	 * 
	*/
	@Action(value="/jxjh_tj_json",results={@Result(name="success",type="json")})
	public String jxjh_tj_json(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int kch=(Integer)hs.getAttribute("kkch");
		String word=(String) hs.getAttribute("wword");
		if(kch==0||word==null||word.equals("")){
			rows=this.jxjhService.proc_jxjh_tj(page,rows_s);
			total=this.jxjhService.proc_jxjh_tjTotal();
			  
		}else{
			rows=this.jxjhService.proc_jxjh_tj_search(kch, word, page, rows_s);
			total=this.jxjhService.proc_jxjh_tj_search(kch, word);
			hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
		}
		
		return SUCCESS;
	}
	
	/**
	 * @{jxjh_tj_json1.action}
	 * @param {} {Ϊ��ѧ�ƻ�ͳ��ҳ���ṩjson����}
	 * ���flex��
	*/
	@Action(value="/jxjh_tj_json1",results={@Result(name="success",type="json")})
	public String jxjh_tj_json1(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int kch=(Integer)hs.getAttribute("kkch");
		String word=(String) hs.getAttribute("wword");
		if(kch==0||word==null||word.equals("")){
			rows=this.jxjhService.proc_jxjh_tj(page,rows_s);
			total=this.jxjhService.proc_jxjh_tjTotal();
			  
		}else{
			rows=this.jxjhService.proc_jxjh_tj_search(kch, word, page, rows_s);
			total=this.jxjhService.proc_jxjh_tj_search(kch, word);
			hs.setAttribute("kkch", 0);hs.setAttribute("wword", "");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * @{jxjh.action������}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{��Ҫ���ܾ�����ת����ѧ�ƻ������ҳ�棬��ת��ʱ��Ὣ���е���ʦ�������Ϳγ����Ƽ��ص�ҳ��}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@Action(value="/jxjh",results={@Result(name="success",location="/page/xuexi/jxjh.jsp")})
	public String jxjh() throws Exception{
		HttpSession hs = ServletActionContext.getRequest().getSession();
		if(tip==null||tip.equals("")){//��ʾ����
		}else{
			//i_d=jhid;
			//kkch=kch;
			//���γ̱�źͿγ����ƴ���session
            hs.setAttribute("coursebh", kch);
            hs.setAttribute("courseName", courseName);
			hs.setAttribute("i_d", jhid);//i_d��ʾ�ǽ�ѧ�ƻ��ı��
			hs.setAttribute("kkch", kch);//��������
			hs.setAttribute("tip", tip);//��������Ƿ�����jxjh_tj.jsp��jxjh_tj_stu.jsp
		}
		kch=(Integer)hs.getAttribute("kkch");
		String hql1="from CourseChapter where TCName in (select TCName from CourseChapter where zbh='"+kch+"')";
		kcxxs=this.baseService.findHql(CourseChapter.class, hql1);

		return SUCCESS;
	}
	/**
	 * @{blukaddyck.action}
	 * @param {listFunctions} {��ʾ����}
	 * ��ת����
	 * ��Ҫ���ܾ�����ת����ѧ�ƻ������ҳ�棬��ת��ʱ��Ὣ���е���ʦ�������Ϳγ����Ƽ��ص�ҳ��
	*/
	@Action(value="/blukaddyck",results={@Result(name="success",type="json")})
	public String blukaddyck() throws Exception{
		HttpSession hs = ServletActionContext.getRequest().getSession();
		int i_d=(Integer)hs.getAttribute("i_d");
		List<JxjhYck> list=new ArrayList<JxjhYck>();
		
		for(int i=0;i<arr.length;){
			JxjhYck yck=new JxjhYck();
			yck.setYckms(arr[i]);
			yck.setXsh(Integer.valueOf(arr[i+1]));
			CourseChapter k = new CourseChapter();k.setZbh(Integer.valueOf(arr[i+2]));
			yck.setCourseChapter(k);
			yck.setJxjhSz(i_d+"");
			list.add(yck);
			i=i+3;
		}
		this.jxjhService.batchInsert(list);
		return SUCCESS;
	}
	/**
	 * @{AddJxjh.action}
	 * @param {} {��ʾ����}
	 * ��ӹ���
	 * ��Ҫ���ܾ�����ӽ�ѧ�ƻ�
	*/
	@Action(value="/AddJxjh",results={@Result(name="success",type="json")})
	public String AddJxjh(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		
		CourseChapter k = new CourseChapter();
		
		if(tip!=null&&tip.equals("jxjh_tj")){//�ӽ�ѧ�ƻ�����ͳ��ҳ����ӡ���ѧ�ƻ�����
			jsbh=(String)hs.getAttribute("jsbh");
			String hql="from CourseChapter where TCName ='"+kname+"'";
			kch=this.baseService.findSql(CourseChapter.class, hql, 1, 1).get(0).getZbh();//�˴���kchӦ�ö���ÿ�ſεĵ�һ�²Ŷ�
			int tt=this.jxjhService.proc_addjxjh(jsbh, kch, xq);System.out.println("tt:"+tt);
			if(tt==0){
				tip1="cuowu";
			}else{
				tip1="zq";
				hs.setAttribute("i_d", tt);//��������yckʱ�õġ�
			}
			
		}else{                                 //�ӽ�ѧ�ƻ�����ͳ�ƻ����ݸſ�ҳ����ӡ�һ�οΡ���
			
			JxjhYck jxjhyck=new JxjhYck();
			k.setZbh(kch);
			jxjhyck.setCourseChapter(k);
			jxjhyck.setYckms(jhmc);
			jxjhyck.setXsh(xsh);
			if(jhid==0){
				jhid=(Integer)hs.getAttribute("i_d");
				jxjhyck.setJxjhSz(jhid+"");//�����ݸſ�ҳ������ӡ�һ�οΡ�(��ѧ�ƻ��ı�Ŵ�jxjh.action�л��)
			}else{
				jxjhyck.setJxjhSz(jhid+"");//�ӽ�ѧ�ƻ�����ͳ������ӡ�һ�οΡ�����ѧ�ƻ��ı��Ҳ��ҳ��ȡ�ã�
			}
			
			this.baseService.save(jxjhyck);

		}
		
		return SUCCESS;
	}
	/**
	 * @{JxjhJson.action}
	 * @param {} {��ʾ����}
	 * ��ʵ������
	 * �ڼ��ؽ�ѧ�ƻ���ʱ��datagrid���Զ��������action
	*/
	@Action(value="/JxjhJson",results={@Result(name="root",type="json")})
	public String listJxjh() throws Exception {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		
		//�ж��Ƿ���ѧ����¼
		if("student".equals(s1)) {
			if(tip==null||tip.equals("")){//��ʾ����
			}else{
				//i_d=jhid;
				//kkch=kch;
				//���γ̱�źͿγ����ƴ���session
	            hs.setAttribute("coursebh", kch);
	            hs.setAttribute("courseName", courseName);
				hs.setAttribute("i_d", jhid);//i_d��ʾ�ǽ�ѧ�ƻ��ı��
				hs.setAttribute("kkch", kch);//��������
			}
//			kch=(Integer)hs.getAttribute("kkch");
//			String hql1="from CourseChapter where TCName in (select TCName from CourseChapter where zbh='"+kch+"')";
//			kcxxs=this.baseService.findHql(CourseChapter.class, hql1);
		}
		
        int i_d=(Integer)hs.getAttribute("i_d");
		String hql;
		String id1=i_d+"";
		String id2=i_d+",%";
		String id3="%,"+i_d;
		String id4="%,"+i_d+",%";
		hql="select count(*) from JxjhYck where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"')";
		if(queryword==null||queryword.equals("")){
			
			total=this.baseService.getTotalSql(hql);
			hql="from JxjhYck where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"') order by zbh asc,no asc";

		}else{
			hql=hql+" and (yckms like '%"+queryword+"%')";
			total=this.baseService.getTotalSql(hql);
			hql="from JxjhYck where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"') and (yckms like '%"+queryword+"%') order by zbh asc,no asc";
			queryword=null;

		}
		 List<JxjhYck> jxjhlist=this.baseService.findSql(JxjhYck.class, hql, page, rows_s);

		 for(int i=0;i<jxjhlist.size();i++) {
			 Jxjh_z jh = new Jxjh_z();
				jh.setId(jxjhlist.get(i).getNo());
				jh.setJhmc(jxjhlist.get(i).getYckms());
				jh.setKname(jxjhlist.get(i).getCourseChapter().getCName());
				jh.setXsh(jxjhlist.get(i).getXsh());
				jh.setZbh(jxjhlist.get(i).getCourseChapter().getZbh());
				jh.setLabel("��" + (i + 1) + "�ο�");
				rows.add(jh);
			 
		 }
		
		return "root";
	}
	/**
	 * @{GetType.action}
	 * �ж��û���ɫ�����
	 * ϵͳҪ�����û��Ľ�ɫ���û����費ͬ��Ȩ�ޣ���action������ҳ��ռ��ص�ʱ���ж��û��Ľ�ɫ����
	*/
	@Action(value="/GetType",results={@Result(name="root",type="json")})
	public String gettype(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		String hql="select distinct type from Userinfo where UserId='"+userid+"'";

		tip=this.baseService.findHql(String.class, hql).get(0);
		return "root";
	}
	/**
	 * @{deleteJxjh.action}
	 * ɾ������
	 * ��Ҫ���ܾ��Ǹ���ǰ̨�������ļƻ�idֵ����Ӧ�Ľ�ѧ�ƻ�ɾ��
	*/
	@Action(value="/deleteJxjh",results={@Result(name="root",type="json")})
	public String DelJxjh(){
		JxjhYck j = this.baseService.find(JxjhYck.class, jhid);
		
		String hql="select count(*) from Jxnr where jxjhYck.no='"+jhid+"'";
		int shu=this.baseService.getTotalSql(hql);
		if(shu==0){
			this.baseService.delete(j);
			tip="true";
		}else{
			tip="false";
		}
		
		return "root";
	}
	/**
	 * @{updateJxjh.action}
	 * ���¹���
	 * ��Ҫ������½�ѧ�ƻ�
	*/
	@Action(value="/updateJxjh",results={@Result(name="root",type="json")})
	public String UpdateJxjh(){

		String hql="update JxjhYck set yckms='"+jhmc+"',xsh='"+xsh+"',courseChapter.zbh='"+kch+"' where no='"+jhid+"'";
		this.baseService.bulkUpdate(hql);
		return "root";
	}
	
//	/**
//	 * @{ToJxnr.action}
//	 * ��ת����
//	 * ����Ҫ�鿴��ѧ�ƻ��Ľ�ѧ����ʱ���ô�action������ת����Ӧ�Ľ�ѧ����ҳ��
//	 * ����������������Ҫ��Ϊ����ӽ�ѧ����ʱ�����
//	*/
//	@Action(value="/ToUploadFile",results={@Result(name="success",location="/page/xuexi/uploadFile.jsp")})
//	public String ToUploadFile(){
//		this.scwj = this.baseService.find(Scwj.class);
//		this.wjlxlist=this.baseService.find(Wjlx.class);
//		return SUCCESS;
//	}
	/**
	 * @{queryJxjh.action}
	 * ��ѯ����
	 * �����ѯ��ѧ�ƻ������ݿγ����ƺ�ѧ�ڽ���Ӧ�Ľ�ѧ�ƻ������
	 * ����if��else�����Ҫ�Ǹ����û��������Ϣ��ͬ����ѯ��������ͬ
	*/
	@Action(value="/queryJxjh",results={@Result(name="root",type="json")})
	public String queryFunction(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
        int i_d=(Integer)hs.getAttribute("i_d");
        
		//queryWord=queryword;
		String id1=i_d+""; String id2=i_d+",%"; String id3="%,"+i_d; String id4="%,"+i_d+",%";
		String hql="select count(*) from JxjhYck where (jxjhSz like '"+id1+"' or jxjhSz like '"+id2+"' or jxjhSz like '"+id3+"' or jxjhSz like '"+id4+"')";
		hql=hql+" and (yckms like '%"+queryword+"%')";
		int size=this.baseService.getTotalSql(hql);
		
		if(size!=0){
			tip="true";
		}else{
			tip="kong";
		}
		
		return "root";
	}
}
