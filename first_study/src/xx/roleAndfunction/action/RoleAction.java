
	/**
	 * �ļ�����RoleAction.java
	 *
	 * �汾��Ϣ��
	 * ���ڣ�2011-4-4
	 * ���ߣ�tlq
	 * Copyright �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ Corporation 2011 
	 * ��Ȩ����
	 * �����Ǵ������й��ڽ�ɫ��һЩ������
	 */
	
package xx.roleAndfunction.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Functions;
import xx.collection.bean.Modules;
import xx.collection.bean.Rolefunction;
import xx.collection.bean.Roles;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * �����������ǣ�����Խ�ɫ��������ɾ���Խ�ɫȨ�޵�����ɾ����
 * @author: tlq
 * @version: 2011-4-4 ����10:21:37 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class RoleAction extends ActionSupport{
	
	@Resource(name="baseService")
	private BaseService baseService;   //��serviceע��
	private Roles role;						//��������ǰ̨������ֵ
	private Rolefunction rf;				//���նԽ�ɫ��Ȩʱ����ɫ��Ȩ��
	List<Roles> rows = new ArrayList<Roles>();			//�����е�role����rows��������У�����ҳ������ʾ��
	private List<String> funIds=new ArrayList<String>();			//����ҳ�洫���ĸ�ѡ���ֵ
	private List<String> roleIDs=new ArrayList<String>();
	List<Functions> functions = new ArrayList<Functions>();
	List<Modules> mds = new ArrayList<Modules>();					//�����������е�module
	public int roleid;
	private static final long serialVersionUID = 1L; 
	private static int ID;
	private int page;								//��ҳʱ����ǰ��ҳ������ǰ̨����
	private int rows_s;								//��ҳʱ����ǰ��ҳ�����������ǰ̨����
	private int total;								//��ѯ����¼��������
	private String queryType;						//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ������
	private String queryWord;						//��ѯ����ʱ�Ĳ�ѯ��������Ҫ���ݲ�ѯ�Ĺؼ���
	private String rolename;	
	private String miaoshu;
	private String tip;								//ʹ��ajax�첽�ύʱ�����ڱ�Ƿ��ص�ֵ
	
	
	public String getTip() {
		return tip;
	}
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	@JSON(serialize=false)
	public String getRolename() {
		return rolename;
	}
	@JSON(deserialize=true)
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getMiaoshu() {
		return miaoshu;
	}
	@JSON(deserialize=true)
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
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
	public String getQueryType() {
		return queryType;
	}
	@JSON(deserialize=true)
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public int getTotal() {
		return total;
	}
	@JSON(deserialize=true)
	public void setTotal(int total) {
		this.total = total;
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

	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}

	public int getRoleid() {
		return roleid;
	}
	@JSON(deserialize=true)
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	  
	@JSON(serialize=false)
	public List<Modules> getMds() {
		return mds;
	}

	public void setMds(List<Modules> mds) {
		this.mds = mds;
	}
	@JSON(serialize=false)
	public List<Functions> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Functions> functions) {
		this.functions = functions;
	}

	@JSON(serialize=false)
	public List<String> getFunIds() {
		return funIds;
	}

	public void setFunIds(List<String> funIds) {
		this.funIds = funIds;
	}
	@JSON(serialize=false)
	public List<String> getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(List<String> roleIDs) {
		this.roleIDs = roleIDs;
	}
	@JSON(serialize=false)
	public List<Functions> getFunction() {
		return functions;
	}

	public void setFunction(List<Functions> function) {
		this.functions = function;
	}

	public List<Roles> getRows() {
		return rows;
	}

	public void setRows(List<Roles> rows) {
		this.rows = rows;
	}

	@JSON(serialize=false)
	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	@JSON(serialize=false)
	public Rolefunction getRf() {
		return rf;
	}

	public void setRf(Rolefunction rf) {
		this.rf = rf;
	}

	
	/**
	 * @{creatRole.action}
	 * @param {/role} 
	 * @{������ɫ}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 * �÷�������һ��roleid���˱���ʱ����ҳ��ģ�ʹҳ����������ɫ��ˢ�£�ֱ���ڱ������һ������
	 * ��ɫ��roleid��������
	*/
	@Action(value="/creatRole",results={@Result(name="root",type="json"),
											@Result(name="input",location="/page/role/listrole.jsp")})
	public String addRoles() throws Exception {
		
		Roles role = new Roles();
		role.setMiaoshu(miaoshu);
		role.setRolename(rolename);
		this.baseService.save(role);
		List<Roles> list = this.baseService.find(Roles.class);		
		int last = list.size();
		this.roleid = list.get(last-1).getRoleid();
		return "root";
	}
	
	/**
	 * @{beforeCreat.action}
	 * �÷������ύ������ɫ���Խ�ɫ������֤����֤��Ҫ�ύ�Ľ�ɫ�����Ƿ��Ѵ���
	 * ǰ̨��ajax��ʽ���գ����ý�ɫ�����Ѿ������ݿ��д��ڣ��򽫸�������json��ʽ����
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	*/
	@Action(value="/beforeCreat",results={@Result(name="root",type="json")})
	public String beforeInsert(){
		List<Roles> rs = new ArrayList<Roles>();
		rs = this.baseService.find(Roles.class);
			for(Roles r:rs){
				if(rolename.equals(r.getRolename())){
					tip = r.getRolename();
					break;
				}
			}
		return "root";
	}	
	
	/**
	 * @{json.action}
	 * ��json��ʽ��ѯ�����еĽ�ɫ����ѯʱҪ��ҳ
	 * page��rows_s�Ƿ�ҳ����Ϣ
	 * page�ǵ�ǰҳ��
	 * rows_s��ÿҳ������
	 * total���ܵļ�¼��
	*/
	@Action(value="/json",results={@Result(name="root",type="json")})
	public String listRoles() throws Exception {
		
		rows = this.baseService.findAll(Roles.class, "Roles", page, rows_s);
		total = this.baseService.getTotal("Roles");
		return "root";
	}
	/**
	 * @{deleteRole.action}
	 * �÷���������ɾ����ɫ�ģ�ɾ��ʱ���ҳ�����һ��������roleid
	 * ɾ����ɫʱ�����Ƚ���ɫ��ӵ�е�Ȩ��ɾ��
	*/
	@Action(value="/deleteRole",results={@Result(name="success",type="json")})
	public String deleteRole() throws Exception {
		tip="";
		role = this.baseService.find(Roles.class, roleid);
		List<Rolefunction> rfs = this.baseService.findRoleAndFunctionsChecked(roleid);
		if(rfs.size()!=0){
			tip="ɾ�� '"+role.getRolename()+"' ������ȡ���ý�ɫ������Ȩ��";
		}else{
			tip="true";
			this.baseService.delete(role);
		}
			
		return SUCCESS;
	}
	/**
	 * @{queryroles.action}
	 * �÷�����������ѯ��ɫ�ģ���ѯʱ���ҳ���������������queryType��queryWord
	 * queryType��Ҫ��ѯʱ�����ݵ����ͣ�queryWord�ǹؼ���
	 * ��ѯ���Ľ��ҳҪ�Է�ҳ����ʽ��ʾ
	*/
	@Action(value="/queryroles",results={@Result(name="root",type="json")})
	public String queryRole(){
		String[] keys = {queryType};
		if(queryType.equals("roleid")){
			int ss = Integer.parseInt(queryWord);
			Object[] values = {ss};
			rows = this.baseService.find(Roles.class, "Roles", keys, values);
			total = rows.size();
		}else{
			Object[] values = {queryWord};
			rows = this.baseService.find(Roles.class, "Roles", keys, values);
			total = rows.size();
		}
		return "root";
	}
	/**
	 * @{updateRole.action}
	 * �÷������������½�ɫ��
	 * ҳ����ajax��ʽ�������ύ�������÷������պ���Ž�role�У�Ȼ��update
	 * ��ɫroleid�������ģ����Բ��ܸ�
	*/
	@Action(value="/updateRole",results={@Result(name="root",type="json"),
											@Result(name="input",location="/page/role/updatePRole.jsp")})
	public String updateRole(){
		
		this.role = this.baseService.find(Roles.class, roleid);
		role.setRolename(rolename);
		role.setMiaoshu(miaoshu);
		this.baseService.update(role);
		return "root";
	}
	
	/*
	 * �г�����function����fuquanRole��updateRoleToFunction�����
	 */
	
	@Action(value="/listFunctions",results={@Result(name="success",location="/main.jsp")})
	public String listFunctions(){
		mds = this.baseService.find(Modules.class);
		functions = this.baseService.find(Functions.class);
		return SUCCESS;
	}
	/**
	 * @{SHOW.action}
	 * �÷����������鿴�û���ϸ��Ϣ��
	 * ҳ����ajax��ʽ����ɫid�ύ������Ȼ���ѯ����
	 * �˷���ֻ�ǽ���ɫ�������أ���ɫid�ͽ�ɫ���ƴ�ҳ����ֱ��ȡ
	*/
	@Action(value="/Show",results={@Result(name="root",type="json")})
	public String show(){
		this.miaoshu = this.baseService.find(Roles.class, roleid).getMiaoshu();
		return "root";
	}
} 
