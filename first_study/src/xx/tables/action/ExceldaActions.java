/*
 *@(#)xx.tables.action
 *@ExceldaActions.java.java  
 *@����ʱ��:2011-8-21����05:14:46
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.bean.Cztywj;
import xx.collection.bean.Czt;
import xx.collection.bean.Cztd;
import xx.collection.bean.CztdDto;
import xx.collection.bean.Cztda;
import xx.collection.bean.CztdaId;
import xx.collection.bean.Cztdadto;
import xx.collection.bean.Cztdto;
import xx.collection.bean.Cztxxxx;
import xx.collection.bean.Cztywjdto;
import xx.collection.bean.Zsd;
import xx.quanxian.service.BaseService;
import xx.tables.dao.ExcelDao;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;



/**
 * @ExceldaActions <code>{�����}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class ExceldaActions extends ActionSupport{
	private List  rows=new ArrayList();
	@Resource(name="baseService")
	private BaseService baseservice;
	@Resource(name="adminService")
	private AdminService adminservice;
	@Resource(name="exceldao")
	private ExcelDao exceldao;
	private String message;
    private int tihao;
    private int bh;
    private int bzh;
	private Cztdadto cztdadto=new Cztdadto();
	private List<Integer> bzhs=new ArrayList<Integer>();
    private int total;
    private String zsdallmc;
    private List<CztdDto>  cxcztd=new ArrayList<CztdDto>();
    private String tggjz;
    private String zsdkeymc;
    private String kcmc;
    private String zmc;
    private String jmc;
    private int rows_s;
	private int page;


	
	

    public ExcelDao getExceldao() {
		return exceldao;
	}


	public void setExceldao(ExcelDao exceldao) {
		this.exceldao = exceldao;
	}


	public AdminService getAdminservice() {
		return adminservice;
	}


	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}


	public int getRows_s() {
		return rows_s;
	}


	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public List getRows() {
		return rows;
	}


	public void setRows(List rows) {
		this.rows = rows;
	}

	public String getKcmc() {
		return kcmc;
	}


	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}


	public String getZmc() {
		return zmc;
	}


	public void setZmc(String zmc) {
		this.zmc = zmc;
	}


	public String getJmc() {
		return jmc;
	}


	public void setJmc(String jmc) {
		this.jmc = jmc;
	}


	public String getZsdkeymc() {
		return zsdkeymc;
	}


	public void setZsdkeymc(String zsdkeymc) {
		this.zsdkeymc = zsdkeymc;
	}


	public void setTggjz(String tggjz) {
		this.tggjz = tggjz;
	}




	public String getZsdallmc() {
		return zsdallmc;
	}


	public void setZsdallmc(String zsdallmc) {
		this.zsdallmc = zsdallmc;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}

	public List<Integer> getBzhs() {
		return bzhs;
	}
	
	public void setBzhs(List<Integer> bzhs) {
		this.bzhs = bzhs;
	}
	public Cztdadto getCztdadto() {
		return cztdadto;
	}
	public void setCztdadto(Cztdadto cztdadto) {
		this.cztdadto = cztdadto;
	}

	

	public List<CztdDto> getCxcztd() {
		return cxcztd;
	}


	public void setCxcztd(List<CztdDto> cxcztd) {
		this.cxcztd = cxcztd;
	}
	  public int getBzh() {
			return bzh;
		}
		public void setBzh(int bzh) {
			this.bzh = bzh;
		}

	public BaseService getBaseservice() {
		return baseservice;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTihao() {
		return tihao;
	}
	public void setTihao(int tihao) {
		this.tihao = tihao;
	}
	public int getBh() {
		return bh;
	}
	public void setBh(int bh) {
		this.bh = bh;
	}
	
	
	@Action(value="/getanswers",results={@Result(name="success",type="json",params={"includeProperties","rows.*,total"})})
	public String getanswers()
	{
		String answers[]=new String[4];
		String positions[]=new String[4];
		List<Cztda> list=new ArrayList<Cztda>();
		list=this.baseservice.findHql(Cztda.class, "select  new Cztda(id,zsd.zsdmc,zsd.id.zsdbh,zsd.id.zbh,zsd.id.CId,bufz,sheetname,wz,da,cda,carea,yzsx) from Cztda   where id.dtTh="+tihao+" and id.sxh="+this.bh);
		for(int i=0;i<list.size();i++)
		{
			Cztdadto dto=new Cztdadto();
			Cztda da=list.get(i);
			answers=list.get(i).getCda().split(",");
			positions=list.get(i).getCarea().split(","); 
			if(answers[0].equals("0"))
			{
				answers[0]="";
			}
			if(da.getYzsx()==44)
			{
				answers[0]=this.getSjhz(Integer.parseInt(answers[0].substring(1,5).trim()));
			}
		    dto.setAnswer1(answers[0]);
		    if(positions[0].equals("0"))
		    {
		    	positions[0]="";
		    }
		    dto.setPosition1(positions[0]);
		    if(answers[1].equals("0"))
			{
				answers[1]="";
			}
		    if(da.getYzsx()==44)
			{
				answers[1]=this.getSjhz(Integer.parseInt(answers[1].substring(1,5).trim()));
			}
		    dto.setAnswer2(answers[1]);
		    if(positions[1].equals("0"))
		    {
		    	positions[1]="";
		    }
		    dto.setPosition2(positions[1]);
		    if(positions[2].equals("0"))
		    {
		    	positions[2]="";
		    }
		    dto.setPosition3(positions[2]);
		    if(answers[2].equals("0"))
			{
				answers[2]="";
			}
		    dto.setAnswer3(answers[2]);
		    if(positions[3].equals("0"))
		    {
		    	positions[3]="";
		    }
		    dto.setPosition4(positions[3]);
		    if(answers[3].equals("0"))
			{
				answers[3]="";
			}
		    dto.setAnswer4(answers[3]); 
		    String daan=da.getDa();
		    if(daan==null)
		    {
		    	daan="";
		    }
		    if(da.getYzsx()==26)
		    {
		    	int type=Integer.parseInt(daan);
		    	daan=this.getChartint(type);
		    }
		    else if(da.getYzsx()==23)
		    {
		    	daan=this.getgs(daan);
		    }
			dto.setAnswers(daan);
			dto.setBufz(da.getBufz());
			dto.setBzh(da.getId().getBzxh());
			String wz=da.getWz();
			if(wz==null)
			{
				wz="";
			}
			dto.setPosition(wz);
			String sheetname=da.getSheetname();
			if(sheetname==null)
			{
				sheetname="";
			}
			dto.setSheetname(sheetname);
			dto.setSxh(da.getId().getSxh());
			dto.setTh(da.getId().getDtTh());
			dto.setYzsx(da.getYzsx());
			dto.setZsdmc(da.getZsd().getZsdmc());
			this.rows.add(i,dto);
		}
		this.total=list.size();
		return SUCCESS;
	}
	@Action(value="/updateda",results={@Result(name="success",type="json",params={"includeProperties","message"}),@Result(name="error",type="json",params={"includeProperties","message"})})
	public  String updateda()
	{
		String canswers="";
		String careas="";
		this.cztTrim(this.cztdadto);
		 if(this.cztdadto.getAnswer1().length()==0)
		    {
		    	canswers=canswers+"0";
		    }
		    else
		    {
		    	   if(this.cztdadto.getYzsx()==44)
				    {
			        	canswers=canswers+this.getSjhz(this.cztdadto.getAnswer1().trim());   	
				    }
			        else
			        {
			    	canswers=canswers+this.cztdadto.getAnswer1();
			        }
		    }
		    if(this.cztdadto.getAnswer2().length()==0)
		    {
		    	canswers=canswers+",0";
		    }
		    else
		    {
		    	    if(this.cztdadto.getYzsx()==44)
				    {
			        	canswers=canswers+","+this.getSjhz(this.cztdadto.getAnswer2().trim());   	
				    }
			        else
			        {
			    	canswers=canswers+","+this.cztdadto.getAnswer2();
			        }
		    }
		    if(this.cztdadto.getAnswer3().length()==0)
		    {
		    	canswers=canswers+",0";
		    }
		    else
		    {
		    	canswers=canswers+","+this.cztdadto.getAnswer3();
		    }
		    if(this.cztdadto.getAnswer4().length()==0)
		    {
		    	canswers=canswers+",0";
		    }
		    else
		    {
		    	canswers=canswers+","+this.cztdadto.getAnswer4();
		    }	    
		    if(this.cztdadto.getPosition1().length()==0)
		    {
		    	careas=careas+"0";
		    }
		    else 
		    {
		    	careas=careas+this.cztdadto.getPosition1();
		    }
		    if(this.cztdadto.getPosition2().length()==0)
		    {
		    	careas=careas+",0";
		    }
		    else 
		    {
		    	careas=careas+","+this.cztdadto.getPosition2();
		    }
		    if(this.cztdadto.getPosition3().length()==0)
		    {
		    	careas=careas+",0";
		    }
		    else 
		    {
		    	careas=careas+","+this.cztdadto.getPosition3();
		    }
		    if(this.cztdadto.getPosition4().length()==0)
		    {
		    	careas=careas+",0";
		    }
		    else 
		    {
		    	careas=careas+","+this.cztdadto.getPosition4();
		    }
		Cztda cztda=new Cztda();
		CztdaId id=new CztdaId();
	    id.setBzxh(cztdadto.getBzh());
	    id.setDtTh(cztdadto.getTh());
	    id.setSxh(cztdadto.getSxh());
	    int sx=this.cztdadto.getYzsx();
	    String daan=this.cztdadto.getAnswers();
	    if(sx==26)
	    {
	    	int da=this.getPicturetype(daan.trim());
	    	if(da==0)
	    	{
	    		message="目前不支持此类图";
	    		return ERROR;
	    	}
	    	daan=da+"";
	    }
	    else if(sx==23)
	    {
	    	String  da=this.getSzlx(daan.trim());
	    	if(da.equals("没有这种格式"))
	    	{
	    		message="目前不支持此数字类型";
	    		return ERROR;
	    	}
	    	daan=da+"";   	
	    }
	    cztda.setDa(daan);
	    cztda.setBufz(cztdadto.getBufz());
	    cztda.setCda(canswers);
	    cztda.setCarea(careas);
	    cztda.setCzt(null);
	    cztda.setId(id);
	    cztda.setWz(cztdadto.getPosition());
	    cztda.setSheetname(cztdadto.getSheetname());
	    cztda.setYzsx(cztdadto.getYzsx());
	    int num=Integer.parseInt(cztdadto.getZsdmc());
	    List<Zsd> list=this.baseservice.findAll(Zsd.class, "Zsd",num ,1);
	    Zsd zsd=list.get(0);
	    cztda.setZsd(zsd);
	    this.baseservice.update(cztda);
	    message="答案修改成功";
	    return SUCCESS;
	}
	@Action(value="/luruda",results={@Result(name="success",type="json",params={"includeProperties","message"}),@Result(name="success",type="json",params={"includeProperties","message"})})
	public String luRuda(){
		
	    Cztda cztda=new Cztda();
	    String canswers="";
	    String careas="";
	    this.cztTrim(this.cztdadto);
	    if(this.cztdadto.getAnswer1().length()==0)
	    {
	    	canswers=canswers+"0";
	    }
	    else
	    {
	    	   if(this.cztdadto.getYzsx()==44)
			    {
		        	canswers=canswers+this.getSjhz(this.cztdadto.getAnswer1().trim());   	
			    }
		        else
		        {
		    	canswers=canswers+this.cztdadto.getAnswer1();
		        }
	    }
	    if(this.cztdadto.getAnswer2().length()==0)
	    {
	    	canswers=canswers+",0";
	    }
	    else
	    {
	    	   if(this.cztdadto.getYzsx()==44)
			    {
		        	canswers=canswers+","+this.getSjhz(this.cztdadto.getAnswer2().trim());   	
			    }
		        else
		        {
		    	canswers=canswers+","+this.cztdadto.getAnswer2();
		        }
	    }
	    System.out.println(canswers);
	    if(this.cztdadto.getAnswer3().length()==0)
	    {
	    	canswers=canswers+",0";
	    }
	    else
	    {
	    	canswers=canswers+","+this.cztdadto.getAnswer3();
	    }
	    if(this.cztdadto.getAnswer4().length()==0)
	    {
	    	canswers=canswers+",0";
	    }
	    else
	    {
	    	canswers=canswers+","+this.cztdadto.getAnswer4();
	    }	    
	    if(this.cztdadto.getPosition1().length()==0)
	    {
	    	careas=careas+"0";
	    }
	    else 
	    {
	    	careas=careas+this.cztdadto.getPosition1();
	    }
	    if(this.cztdadto.getPosition2().length()==0)
	    {
	    	careas=careas+",0";
	    }
	    else 
	    {
	    	careas=careas+","+this.cztdadto.getPosition2();
	    }
	    if(this.cztdadto.getPosition3().length()==0)
	    {
	    	careas=careas+",0";
	    }
	    else 
	    {
	    	careas=careas+","+this.cztdadto.getPosition3();
	    }
	    if(this.cztdadto.getPosition4().length()==0)
	    {
	    	careas=careas+",0";
	    }
	    else 
	    {
	    	careas=careas+","+this.cztdadto.getPosition4();
	    }
	    cztda.setBufz(this.cztdadto.getBufz());
	    cztda.setCda(canswers);
	    cztda.setCarea(careas);
	    cztda.setCzt(null);
	    CztdaId id=new CztdaId();
	    id.setBzxh(this.cztdadto.getBzh());
	    id.setDtTh(this.cztdadto.getTh());
	    id.setSxh(this.cztdadto.getSxh());
	    cztda.setId(id);
	    cztda.setWz(this.cztdadto.getPosition());
	    cztda.setSheetname(this.cztdadto.getSheetname());
	    cztda.setYzsx(this.cztdadto.getYzsx());
	    List<Zsd> list=this.baseservice.findHql(Zsd.class, "from Zsd  where zsdmc='"+this.cztdadto.getZsdmc()+"'");
	    cztda.setZsd(list.get(0));
	    int sx=this.cztdadto.getYzsx();
	    String daan=this.cztdadto.getAnswers();
	    if(sx==26)
	    {
	    	int da=this.getPicturetype(daan.trim());
	    	if(da==0)
	    	{
	    		message="目前不支持此类图";
	    		return ERROR;
	    	}
	    	daan=da+"";
	    }
	    else if(sx==23)
	    {
	    	String  da=this.getSzlx(daan.trim());
	    	if(da.equals("没有这种格式"))
	    	{
	    		message="目前不支持此数字类型";
	    		return ERROR;
	    	}
	    	daan=da+"";   	
	    }
	    cztda.setDa(daan);
	    this.baseservice.save(cztda);
	    message="答案录入成功";
		return SUCCESS;
	}
	
	@Action(value="/addxxda",results={@Result(name="success",type="json",params={"includeProperties","message"}),@Result(name="error" ,type="json",params={"includeProperties","message"})})
	public String addxxda()
	{
		   int sum=this.baseservice.getTotalSql("select count(*) from Czt  where id.dtTh="+this.cztdadto.getTh()+" and id.sxh="+this.cztdadto.getSxh());
		   if(sum==0)
		   {
			   message="没有这个大题或者没有这个小题";
			   return ERROR;
		   }
		    Cztda cztda=new Cztda();
		    String canswers="";
		    String careas="";
		    this.cztTrim(this.cztdadto);
		    if(this.cztdadto.getAnswer1().length()==0)
		    {
		    	canswers=canswers+"0";
		    }
		    else
		    {
		        if(this.cztdadto.getYzsx()==44)
			    {
		        	canswers=canswers+this.getSjhz(this.cztdadto.getAnswer1().trim());   	
			    }
		        else
		        {
		    	canswers=canswers+this.cztdadto.getAnswer1();
		        }
		    }
		    if(this.cztdadto.getAnswer2().length()==0)
		    {
		    	canswers=canswers+",0";
		    }
		    else
		    {
		    	     if(this.cztdadto.getYzsx()==44)
				    {
			        	canswers=canswers+","+this.getSjhz(this.cztdadto.getAnswer2().trim());   	
				    }
			        else
			        {
			    	canswers=canswers+","+this.cztdadto.getAnswer2();
			        }
		    }
		    if(this.cztdadto.getAnswer3().length()==0)
		    {
		    	canswers=canswers+",0";
		    }
		    else
		    {
		    	canswers=canswers+","+this.cztdadto.getAnswer3();
		    }
		    if(this.cztdadto.getAnswer4().length()==0)
		    {
		    	canswers=canswers+",0";
		    }
		    else
		    {
		    	canswers=canswers+","+this.cztdadto.getAnswer4();
		    }	    
		    if(this.cztdadto.getPosition1().length()==0)
		    {
		    	careas=careas+"0";
		    }
		    else 
		    {
		    	careas=careas+this.cztdadto.getPosition1();
		    }
		    if(this.cztdadto.getPosition2().length()==0)
		    {
		    	careas=careas+",0";
		    }
		    else 
		    {
		    	careas=careas+","+this.cztdadto.getPosition2();
		    }
		    if(this.cztdadto.getPosition3().length()==0)
		    {
		    	careas=careas+",0";
		    }
		    else 
		    {
		    	careas=careas+","+this.cztdadto.getPosition3();
		    }
		    if(this.cztdadto.getPosition4().length()==0)
		    {
		    	careas=careas+",0";
		    }
		    else 
		    {
		    	careas=careas+","+this.cztdadto.getPosition4();
		    }
		    cztda.setBufz(this.cztdadto.getBufz());
		    cztda.setCda(canswers);
		    cztda.setCarea(careas);
		    cztda.setCzt(null);
		    CztdaId id=new CztdaId();
		    id.setSxh(this.cztdadto.getSxh());
		    id.setDtTh(this.cztdadto.getTh());
		    int num=this.baseservice.getTotalSql("select count(*) from Cztda where id.dtTh="+this.cztdadto.getTh()+"and id.sxh="+this.cztdadto.getSxh());
		    int bzh=num+1;
		    id.setBzxh(bzh);
		    cztda.setId(id);
		    cztda.setWz(this.cztdadto.getPosition());
		    cztda.setSheetname(this.cztdadto.getSheetname());
		    int sx=this.cztdadto.getYzsx();
		    String daan=this.cztdadto.getAnswers();
		    if(sx==26)
		    {
		    	int da=this.getPicturetype(daan.trim());
		    	if(da==0)
		    	{
		    		message="目前不支持此类图";
		    		return ERROR;
		    	}
		    	daan=da+"";
		    }
		    else if(sx==23)
		    {
		    	String  da=this.getSzlx(daan.trim());
		    	if(da.equals("没有这种格式"))
		    	{
		    		message="目前不支持此数字类型";
		    		return ERROR;
		    	}
		    	daan=da+"";   	
		    }
		    cztda.setDa(daan);
		    cztda.setYzsx(this.cztdadto.getYzsx());
		    List<Zsd> list=this.baseservice.findHql(Zsd.class, "from Zsd  where zsdmc='"+this.cztdadto.getZsdmc()+"'");
		    cztda.setZsd(list.get(0));
		    this.baseservice.save(cztda);
		    message="答案录入成功";
		    return SUCCESS;
	}
	
	@Action(value="/pldelda",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public  String delda()
	{

		Object obj[][]=new Object[bzhs.size()][3];
		String cztdasql="delete  from cztda where sxh=? and dt_th=?  and bzxh=?";
		for(int i=0;i<this.bzhs.size();i++)
		{
			obj[i][0]=this.bh;
			obj[i][1]=this.tihao;
			obj[i][2]=bzhs.get(i);
		}
	    this.exceldao.batchUpdate(cztdasql, obj);
		List<Cztda> list1=this.baseservice.findHql(Cztda.class, "select  new Cztda(id,zsd.zsdmc,zsd.id.zsdbh,zsd.id.zbh,zsd.id.CId,bufz,sheetname,wz,da,cda,carea,yzsx) from Cztda where id.dtTh="+this.tihao+"  and id.sxh="+this.bh+"  and id.bzxh>"+this.bzhs.get(0));
		 String cztdasql1="delete  from cztda where sxh=? and dt_th=?  and bzxh=?";
		    Object obj1[][]=new Object[list1.size()][3];
		    for(int i=0;i<list1.size();i++)
		    {
		    	obj1[i][0]=this.bh;
				obj1[i][1]=this.tihao;
				obj1[i][2]=list1.get(i).getId().getBzxh();
		    }
		    this.exceldao.batchUpdate(cztdasql1, obj1);
	   String saveda="insert into cztda(sxh,dt_th,bzxh,zsdbh,zbh,c_id,bufz,sheetname,wz,da,cda,carea,yzsx)    values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	   Object obj4[][]=new Object[list1.size()][13];
	   int num=this.bzhs.get(0);
		for(int j=0;j<list1.size();j++)
		{
		obj4[j][0]=list1.get(j).getId().getSxh();
       	obj4[j][1]=list1.get(j).getId().getDtTh();
       	obj4[j][2]=num;
       	obj4[j][3]=list1.get(j).getZsd().getId().getZsdbh();
       	obj4[j][4]=list1.get(j).getZsd().getId().getZbh();
       	obj4[j][5]=list1.get(j).getZsd().getId().getCId();
       	obj4[j][6]=list1.get(j).getBufz();
       	obj4[j][7]=list1.get(j).getSheetname();
       	obj4[j][8]=list1.get(j).getWz();
       	obj4[j][9]=list1.get(j).getDa();
       	obj4[j][10]=list1.get(j).getCda();
       	obj4[j][11]=list1.get(j).getCarea();
       	obj4[j][12]=list1.get(j).getYzsx();
       	num++;
		}
		this.exceldao.batchUpdate(saveda, obj4);
		message="答案批量删除成功";
		return SUCCESS;
	}
	@Action(value="/delallda",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String delallda()
	{ 
	    String sql="delete from cztda where dt_th=? and sxh=?";
	    Object obj[][]=new Object[1][2];
	    obj[0][0]=this.tihao;
	    obj[0][1]=this.bh;
	    this.exceldao.batchUpdate(sql, obj);
	    message="该小题所有答案已被删除";
		return SUCCESS;
	}
	@Action(value="/cxzsd",results={@Result(name="success",type="json",params={"includeProperties","total,rows.*"})})
	public String getZsdczt()
	{
		List<Cztd>  list=this.adminservice.cxzsd(this.zsdallmc, (this.page-1)*this.rows_s,this.rows_s);
		total=this.adminservice.cxzsdtotal(this.zsdallmc);
		for(int i=0;i<list.size();i++)
		{
				CztdDto dto=new CztdDto();
			    dto.setFenzhi(list.get(i).getDtfz());
			    dto.setTihao(list.get(i).getTh());
			    String tgs=this.Html2Text(list.get(i).getTg()).substring(0, 15)+"......";
			    dto.setJytigan(tgs);
			    this.rows.add(dto);
				dto=null;
		}
		return SUCCESS;
	}
	
	
	@Action(value="/cxzsdkey",results={@Result(name="success",type="json",params={"includeProperties","total,rows.*"})})
	public String getZsdkey()
	{
	
		List<Cztd>  list=this.adminservice.cxzsdkey(this.zsdkeymc,(this.page-1)*this.rows_s,this.rows_s);
		for(int i=0;i<list.size();i++)
		{
				CztdDto dto=new CztdDto();
			    dto.setFenzhi(list.get(i).getDtfz());
			    dto.setTihao(list.get(i).getTh());
			    String tgs=this.Html2Text(list.get(i).getTg()).substring(0, 15)+"......";
			    dto.setJytigan(tgs);
			    this.rows.add(dto);
				dto=null;
		}
		total=this.adminservice.cxzsdkeytotal(this.zsdkeymc);
		return SUCCESS;
	}
	@Action(value="/cxtggjz",results={@Result(name="success",type="json",params={"includeProperties","total,rows.*"})})
	public String getTgGjz()
	{
		
		this.tggjz=this.tggjz.trim();
		while(this.tggjz.contains("  "))
		{
		this.tggjz=this.tggjz.replaceAll("  "," ");
		}
		String gjzs1[]=this.tggjz.split(" ");
		String gjzs[]={"","",""};
		System.out.println(gjzs.length);
		for(int i=0;i<gjzs1.length;i++)
		{
			if(i<3)
			{
				gjzs[i]="%"+gjzs1[i]+"%";
			}
		}
		List<Cztd> list=this.adminservice.cxtggjz((this.page-1)*this.rows_s, this.rows_s, gjzs[0], gjzs[1], gjzs[2]);
		for(int i=0;i<list.size();i++)
		{
				CztdDto dto=new CztdDto();
			    dto.setFenzhi(list.get(i).getDtfz());
			    dto.setTihao(list.get(i).getTh());
			    String tgs=this.Html2Text(list.get(i).getTg()).substring(0, 15)+"......";
			    dto.setJytigan(tgs);
			    this.rows.add(dto);
				dto=null;
		}
		total=this.adminservice.cxtggjztotal(gjzs[0], gjzs[1], gjzs[2]);
		return SUCCESS;
	}

	@Action(value="/cxkc",results={@Result(name="success",type="json",params={"includeProperties","total,rows.*"})})
	public String cxKc()
	{
		List<Cztd> list=this.adminservice.cxkc(this.jmc ,this.kcmc,this.zmc,(this.page-1)*this.rows_s , this.rows_s);
		for(int i=0;i<list.size();i++)
		{
		    Cztd cztd=list.get(i);
		    CztdDto dto=new CztdDto();
		    dto.setFenzhi(cztd.getDtfz());
		    dto.setTihao(cztd.getTh());
		    String tg=this.Html2Text(cztd.getTg()).substring(0, 15)+"......";
		    dto.setJytigan(tg);
		    this.rows.add(dto);
		    dto=null;
		}
		total=this.adminservice.cxkctotal(this.jmc ,this.kcmc,this.zmc);
		return SUCCESS;
	}
	
	@Action(value="/getcztxxxx",results={@Result(name="success",type="json",params={"includeProperties","total,rows.*"})})
	public String getCztxxxx()
	{
		total=this.baseservice.getTotal("Cztd");
	    this.rows=this.adminservice.ckcztqk((this.page-1)*this.rows_s+this.rows_s,(this.page-1)*this.rows_s);
	    for(int i=0;i<this.rows.size();i++)
	    {
	    	Cztxxxx c=new Cztxxxx();
	    	c=(Cztxxxx) this.rows.get(i);
	    	if(c.getNoda()=="")
	    	{
	    		if(c.getXtcount()==0)
	    		{
	    		c.setNoda("该题没有录入小题");
	    		}
	    		else
	    		{
	    			c.setNoda("该题都含有答案");
	    		}
	    	}
	    	c.setTgxx(this.Html2Text(c.getTgxx()).substring(0,15)+"......");
	    	this.rows.set(i,c);
	    }
		return SUCCESS;
	}



	public  String Html2Text(String inputString) { 
        String htmlStr = inputString; 
            String textStr =""; 
      java.util.regex.Pattern p_script; 
      java.util.regex.Matcher m_script; 
      java.util.regex.Pattern p_style; 
      java.util.regex.Matcher m_style; 
      java.util.regex.Pattern p_html; 
      java.util.regex.Matcher m_html; 
   
      try { 
       String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
       String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
          String regEx_html = "<[^>]+>"; 
      
          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
          m_script = p_script.matcher(htmlStr); 
          htmlStr = m_script.replaceAll("");

          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
          m_style = p_style.matcher(htmlStr); 
          htmlStr = m_style.replaceAll(""); 
      
          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
          m_html = p_html.matcher(htmlStr); 
          htmlStr = m_html.replaceAll(""); 
      
       textStr = htmlStr; 
      
      }catch(Exception e) { 
               System.err.println("Html2Text: " + e.getMessage()); 
      } 
     textStr=textStr.replace("&nbsp;"," ");
      return textStr;
       }   
	
	public int getPicturetype(String pictype)
	{
		int x=0;
		if(pictype.equals("饼图"))
		{
			x=5;
		}
		else if(pictype.equals("百分比堆积面积图"))
		{
			 x=79;
		}
		else if(pictype.equals("面积图 "))
		{
			x=1;
		}
		else if(pictype.equals("堆积面积图"))
		{
			x=76;
		}
		else if(pictype.equals("百分比堆积面积图 "))
		{
			x=77;
		}
		else if(pictype.equals("簇状条形图 "))
		{
		        	 x=57;
		}
		else if(pictype.equals("复合条饼图 "))
		{
		        	 x=71;
		}      
		else if(pictype.equals("堆积条形图 "))
		{
		        	 x=58;
		}
		else if(pictype.equals("百分比堆积条形图 "))  
		{
			 x=59;
		}
		else if(pictype.equals("气泡图 "))  
		{
			 x=15;
		}
		else if(pictype.equals("簇状柱形图 "))  
		{
			 x=51;
		}
		else if(pictype.equals("堆积柱形图 "))  
		{
			x=52;
		}
		else if(pictype.equals( "百分比堆积柱形图"))  
		{
			 x=53;
		} 
		else if(pictype.equals( "簇状条形圆锥图"))  
		{
			 x=102;
		}  
		else if(pictype.equals("堆积条形圆锥图 "))  
		{
			x=103;
		} 
		else if(pictype.equals("百分比堆积条形圆锥图 "))  
		{
			x=104;
		}     
		else if(pictype.equals("簇状柱形圆锥图 "))  
		{
			 x=99;
		} 
		else if(pictype.equals("堆积柱形圆锥图 "))  
		{
			 x=100;
		} 
		else if(pictype.equals("百分比堆积柱形圆锥图 "))  
		{
			x=101;
		} 
		else if(pictype.equals("簇状条形圆柱图 "))  
		{
			x=95;
		} 
		else if(pictype.equals("堆积条形圆柱图 "))  
		{
			x=96;
		} 
		else if(pictype.equals("百分比堆积条形圆柱图 "))  
		{
			 x=97;
		} 
		else if(pictype.equals("三维柱形圆柱图 "))  
		{
			x=98;
		} 
		else if(pictype.equals("簇状柱形圆锥图 "))  
		{
			x=92;
		}
		else if(pictype.equals("堆积柱形圆锥图"))  
		{
			 x=93;
		}
		else if(pictype.equals("百分比堆积柱形圆柱图 "))  
		{
			x=94;
		}
		else if(pictype.equals("圆环图 "))  
		{
			 x=-4120;
		}
		else if(pictype.equals("分离型圆环图 "))  
		{
			x=80;
		}
		else if(pictype.equals("折线图 "))  
		{
			 x=4;
		}
		else if(pictype.equals("数据点折线图 "))  
		{
			x=65;
		}
		else if(pictype.equals("堆积数据点折线图 "))  
		{
			 x=66;
		}
		else if(pictype.equals("百分比堆积数据点折线图 "))  
		{
			 x=67;
		}
		else if(pictype.equals("堆积折线图 "))  
		{
			 x=63;
		}
		else if(pictype.equals("分百分比堆积折线图 "))  
		{
			 x=64;
		}
		else if(pictype.equals("分离型饼图"))  
		{
			 x=69;
		}
		else if(pictype.equals("复合饼图"))  
		{
			 x=68;
		}
		else if(pictype.equals("簇状条形棱锥图 "))  
		{
			x=109;
		}
		else if(pictype.equals("堆积条形棱锥图 "))  
		{
			 x=110;
		}
		else if(pictype.equals("百分比堆积条形棱锥图 "))  
		{
			x=111;
		}	
		else if(pictype.equals("簇状柱形棱锥图 "))  
		{
			x=106;
		}
		else if(pictype.equals("堆积柱形棱锥图 "))  
		{
			 x=107;
		}
		else if(pictype.equals("百分比堆积柱形棱锥图 "))  
		{
			 x=108;
		}
		else if(pictype.equals("雷达图 "))  
		{
			x=-4151;
		}
		else if(pictype.equals("填充雷达图 "))  
		{
			 x=82;
		}
		else if(pictype.equals("数据点雷达图 "))  
		{
			x=81;
		}
		else if(pictype.equals("曲面图（俯视图） "))  
		{
			 x=85;
		}
		else if(pictype.equals("曲面图（俯视线框图） "))  
		{
			x=86;
		}
		else if(pictype.equals("散点图 "))  
		{
			x=-4169;
		}
		else if(pictype.equals("折线散点图 "))  
		{
			 x=74;
		}
		else if(pictype.equals("无数据点折线散点图 "))  
		{
			x=75;
		}
		else if(pictype.equals("平滑线散点图 "))  
		{
			 x=72;
		}
		else if(pictype.equals("无数据点平滑线散点图 "))  
		{ x=73;
		}
		     return x;

	}
	
	public String getChartint(int x)
	{
	    String pictype="你输入的图不存在";
		if(x==5)
		{
			pictype="饼图";
		}
		else if(x==79)
		{
			 pictype="百分比堆积面积图";
		}
		else if(x==1)
		{
			
			pictype="面积图 ";
		}
		else if(x==76)
		{
			pictype="堆积面积图";
		}
		else if(x==77)
		{
			pictype="百分比堆积面积图 ";
		}
		else if(x==57)
		{
		        	 pictype="簇状条形图 ";
		}
		else if(x==71)
		{
		        	 pictype="复合条饼图 ";
		}      
		else if(x==58)
		{
		        	 pictype="堆积条形图 ";
		}
		else if(x==59)  
		{
			 
			 pictype="百分比堆积条形图 ";
		}
		else if (x==15)  
		{
			
			 pictype="气泡图 ";
		}
		else if(x==51)  
		{
			 
			 pictype="簇状柱形图 ";
		}
		else if(x==52)  
		{
			
			pictype="堆积柱形图 ";
		}
		else if(x==53)  
		{
			 pictype="堆积柱形图 ";
		} 
		else if(x==102)  
		{
			 
			 pictype="簇状条形圆锥图";
		}  
		else if(x==103)  
		{
			
			pictype="堆积条形圆锥图 ";
		} 
		else if(x==104)  
		{
			
			pictype="百分比堆积条形圆锥图 ";
		}     
		else if( x==99)  
		{
			
			 pictype="簇状柱形圆锥图 ";
		} 
		else if(x==100)  
		{
			 
			 pictype="堆积柱形圆锥图 ";
		} 
		else if(x==101)  
		{
		
			pictype="百分比堆积柱形圆锥图 ";
		} 
		else if(x==95)  
		{
			
			pictype="簇状条形圆柱图 ";
		} 
		else if(x==96)  
		{
			
			pictype="堆积条形圆柱图 ";
		} 
		else if(x==97)  
		{
			 
			 pictype="百分比堆积条形圆柱图 ";
		} 
		else if(x==98)  
		{
			
			pictype="三维柱形圆柱图 ";
		} 
		else if(x==92)  
		{
			
			pictype="簇状柱形圆锥图 ";
		}
		else if( x==93)  
		{
			
			 pictype="堆积柱形圆锥图";
		}
		else if(x==94)  
		{
			
			pictype="百分比堆积柱形圆柱图 ";
		}
		else if(x==-4120)  
		{
			 
			 pictype="圆环图 ";
		}
		else if(x==80)  
		{
			
			 pictype="分离型圆环图 ";
		}
		else if( x==4)  
		{
			
			 pictype="折线图 ";
		}
		else if(x==65)  
		{
			
			pictype="数据点折线图 ";
		}
		else if(x==66)  
		{
			 pictype="堆积数据点折线图 ";
		}
		else if(x==67)  
		{
			 
			 pictype="百分比堆积数据点折线图 ";
		}
		else if( x==63)  
		{
			
			 pictype="堆积折线图 ";
		}
		else if(x==64)  
		{
			 
			 pictype="分百分比堆积折线图 ";
		}
		else if( x==69)  
		{
			
			 pictype="分离型饼图";
		}
		else if(x==68)  
		{
			 
			 pictype="复合饼图";
		}
		else if(x==109)  
		{
			
			 pictype="簇状条形棱锥图 ";
		}
		else if (x==110)  
		{
			
			 pictype="堆积条形棱锥图 ";
		}
		else if(x==111)  
		{
			
			pictype="百分比堆积条形棱锥图 ";
		}	
		else if(x==106)  
		{
			
			pictype="簇状柱形棱锥图 ";
		}
		else if(x==107)  
		{
			 
			 pictype="堆积柱形棱锥图 ";
		}
		else if( x==108)  
		{
			
			 pictype="百分比堆积柱形棱锥图 ";
		}
		else if(x==-4151)  
		{
			
			pictype="雷达图 ";
		}
		else if( x==82)  
		{
			
			 pictype="填充雷达图 ";
		}
		else if(x==81)  
		{
		
			 pictype="数据点雷达图 ";
		}
		else if( x==85)  
		{
			
			 pictype="曲面图（俯视图） ";
		}
		else if(x==86)  
		{
			
			 pictype="曲面图（俯视线框图） ";
		}
		else if(x==-4169)  
		{
			
			 pictype="散点图 ";
		}
		else if(x==74)  
		{
			 
			 pictype="折线散点图 ";
		}
		else if(x==75)  
		{
			
			 pictype="无数据点折线散点图 ";
		}
		else if( x==72)  
		{	
			 pictype="平滑线散点图 ";
		}
		else if(x==73)  
		{ 
		pictype="无数据点平滑线散点图 ";
		}
		 return pictype;
		
	
	}
	
	
    public String getSjhz(int bh)
    {      
        String  sjhz="没有这个汇总方式";
        switch(bh)
        {
        case 4106:  sjhz="平均值"; break; 
        case 4112:  sjhz="计数 "; break; 
        case  4113:  sjhz="数值计数"; break; 
        case 4136:  sjhz="最大值"; break; 
        case 4139:  sjhz="最小值"; break; 
        case 4149:  sjhz="乘积"; break; 
        case  4155:  sjhz="标准偏差"; break; 
        case 4156:  sjhz="总体标准偏差"; break; 
        case 4157:  sjhz="求和"; break; 
        case 4164:  sjhz="方差"; break; 
        case 4165:  sjhz="总体方差"; break; 
        case  1000:  sjhz="无";
        }
        return sjhz;
    }
    
    
    public int  getSjhz(String  sjhz)
    {      
        int bh=0;
        if(sjhz.equals("平均值"))
        {
        	bh=-4106;
        }
        else if(sjhz.equals("计数"))
        {
        	bh=-4112;
        }
        else if(sjhz.equals("数值计数"))
        {
        	bh=-4113;
        }
        else if(sjhz.equals("最大值"))
        {
        	bh=-4136;
        }
        else if(sjhz.equals("最小值"))
        {
        	bh=-4139;
        }
        else if(sjhz.equals("乘积"))
        {
        	bh=-4149;
        }
        else if(sjhz.equals("标准偏差"))
        {
        	bh=-4155;
        }
        else if(sjhz.equals("总体标准偏差"))
        {
        	bh=-4156;
        }
        else if(sjhz.equals("方差"))
        {
        	bh=-4164;
        }
        else if(sjhz.equals("总体方差"))
        {
        	bh=-4165;
        }
        else if(sjhz.equals("无"))
        {
        	bh=1000;
        }
        else if(sjhz.equals("求和"))
        {
        	bh=-4157;
        }
        return bh;
    }

    public String getgs(String lx)
    {
    	String gs=null;
    	if(lx.equals("G/通用格式"))
    	{
    		gs="常规";
    	}
    	else if(lx.equals("0.00"))
    	{
    		gs="数值";
    	}
    	else if(lx.equals("￥#,##0.00;￥-#,##0.00"))
    	{
    		gs="货币";
    	}
    	else if(lx.equals("yyyy-m-d"))
    	{
    		gs="日期";
    	}
    	else if(lx.equals("[$-F400]h:mm:ss AM/PM"))
    	{
    		gs="时间";
    	}
    	else if(lx.equals("0.00%"))
    	{
    		gs="百分比";
    	}
    	else if(lx.equals("0.00E+00"))
    	{
    		gs="科学技术";
    	}
    	return gs;
    }
    public String getSzlx(String lx)
    {
    	String gs="没有这种格式";
    	if(lx.equals("常规"))
    	{
    		gs="G/通用格式";
    	}
    	else if(lx.equals("数值"))
    	{
    		gs="0.00";
    	}
    	else if(lx.equals("货币"))
    	{
    		gs="￥#,##0.00;￥-#,##0.00";
    	}
    	else if(lx.equals("日期"))
    	{
    		gs="yyyy-m-d";
    	}
    	else if(lx.equals("时间"))
    	{
    		gs="[$-F400]h:mm:ss AM/PM";
    	}
    	else if(lx.equals("百分比"))
    	{
    		gs="0.00%";
    	}
    	else if(lx.equals("科学技术"))
    	{
    		gs="0.00E+00";
    	}
    	return gs;
    }

    
    public void cztTrim(Cztdadto dto)
    {
    	dto.setAnswer1(dto.getAnswer1().trim());
    	dto.setAnswer2(dto.getAnswer2().trim());
    	dto.setAnswer3(dto.getAnswer3().trim());
    	dto.setAnswer4(dto.getAnswer4().trim());
    	dto.setPosition(dto.getPosition().trim());
    	dto.setPosition1(dto.getPosition1().trim());
    	dto.setPosition2(dto.getPosition2().trim());
    	dto.setPosition3(dto.getPosition3().trim());
    	dto.setPosition4(dto.getPosition4().trim());
    	dto.setSheetname(dto.getSheetname().trim());
    	dto.setAnswers(dto.getAnswers().trim());
    }

}
