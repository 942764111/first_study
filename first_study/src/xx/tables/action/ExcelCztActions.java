/*
 *@(#)xx.tables.action
 *@ExcelCztActions.java.java  
 *@����ʱ��:2011-8-21����11:30:23
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import xx.collection.bean.Czt;
import xx.collection.bean.CztId;
import xx.collection.bean.CztdDto;
import xx.collection.bean.Cztda;
import xx.collection.bean.CztdaId;
import xx.collection.bean.Cztdto;
import xx.quanxian.service.BaseService;
import xx.tables.dao.ExcelDao;

/**
 * @ExcelCztActions <code>{�����}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */



@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class ExcelCztActions  extends ActionSupport{
	@Resource(name="baseService")
	private BaseService baseservice;
	@Resource(name="exceldao")
	private ExcelDao exceldao;
	private List<Cztdto>  rows=new ArrayList<Cztdto>();
	private String message;
    private int tihao;
    private int sxh;
    private Cztdto cztdto=new Cztdto();
    private int total;
    private List<Integer>  delsxh=new ArrayList<Integer>();
 


	


	public ExcelDao getExceldao() {
		return exceldao;
	}

	public void setExceldao(ExcelDao exceldao) {
		this.exceldao = exceldao;
	}

	public List<Integer> getDelsxh() {
		return delsxh;
	}

	public void setDelsxh(List<Integer> delsxh) {
		this.delsxh = delsxh;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	public Cztdto getCztdto() {
		return cztdto;
	}
	public void setCztdto(Cztdto cztdto) {
		this.cztdto = cztdto;
	}
	public int getSxh() {
		return sxh;
	}
	public void setSxh(int sxh) {
		this.sxh = sxh;
	}
	public int getTihao() {
		return tihao;
	}
	public void setTihao(int tihao) {
		this.tihao = tihao;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BaseService getBaseservice() {
		return baseservice;
	}
	 
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}


	
	public List<Cztdto> getRows() {
		return rows;
	}
	public void setRows(List<Cztdto> rows) {
		this.rows = rows;
	}
	
	
	@Action(value="/getallczt",results={@Result(name="success",type="json",params={"includeProperties","rows.*,total"})})
	public String getAllCzt()
	{
	
		List<Czt>  list=this.baseservice.findHql(Czt.class, "select new Czt(id,tg,xtfz,nyd,csrcs,zqrcs)  from Czt where cztd.th="+tihao);
		for(int i=0;i<list.size();i++)
		{
			Czt czt=list.get(i);
			Cztdto cztdto=new Cztdto();
			cztdto.setCsrcs(czt.getCsrcs());
			cztdto.setNyd(czt.getNyd());
			cztdto.setSxh(czt.getId().getSxh());
			cztdto.setTg(czt.getTg());
			cztdto.setQtg(czt.getTg());
			cztdto.setTh(czt.getId().getDtTh());
			cztdto.setXtfz(czt.getXtfz());
			cztdto.setZqrcs(czt.getZqrcs());
			this.rows.add(i,cztdto);
		}
		this.total=list.size();
		return SUCCESS;
	}
	
	@Action(value="/delczt",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String delCzt()
	{
	   // List <Cztda>  list=this.baseservice.findHql(Cztda.class,"select  new Cztda(id,bufz,sheetname,wz,da,cda,carea,yzsx) from Cztda where czt.id.dtTh="+this.tihao +"and  czt.id.sxh="+delsxh.get(j));
	    Object obj[][]=new Object[delsxh.size()][2];
	    String cztdasql="delete  from cztda where sxh=? and dt_th=?";
	    for(int i=0;i<delsxh.size();i++)
	    {
	    	obj[i][0]=delsxh.get(i);
	    	obj[i][1]=this.tihao;
	    }
	    this.exceldao.batchUpdate(cztdasql, obj);
	    String cztsql="delete  from czt where sxh=?  and dt_th=?";
	    Object obj1[][]=new Object[delsxh.size()][2];
	    for(int i=0;i<delsxh.size();i++)
	    {
	    	obj1[i][0]=delsxh.get(i);
	    	obj1[i][1]=this.tihao;
	    }
	    this.exceldao.batchUpdate(cztsql, obj1);
	    int sxh=delsxh.get(0);
	    int num=sxh;
	    List<Czt>  list1=this.baseservice.findHql(Czt.class, "select new Czt(id,tg,xtfz,nyd,csrcs,zqrcs)  from Czt   where id.sxh>"+sxh+"  and id.dtTh="+this.tihao);
    	List<Cztda> list2=this.baseservice.findHql(Cztda.class, "select  new Cztda(id,zsd.zsdmc,zsd.id.zsdbh,zsd.id.zbh,zsd.id.CId,bufz,sheetname,wz,da,cda,carea,yzsx)  from Cztda  where id.sxh>"+sxh+"  and id.dtTh="+this.tihao+"   order by id.dtTh ,id.sxh ");   	
    	Object obj2[][]=new Object[1][2];
    	String dasql="delete   from cztda where sxh>?  and dt_th=?";
    	obj2[0][0]=sxh;
    	obj2[0][1]=this.tihao;
    	this.exceldao.batchUpdate(dasql, obj2);
    	String updatesql="update  czt set sxh=?  where dt_th=?  and sxh=?";
    	Object obj3[][]=new Object[list1.size()][3];
    	for(int i=0;i<list1.size();i++)
    	{
    		obj3[i][0]=sxh;
    		obj3[i][1]=this.tihao;
    		obj3[i][2]=list1.get(i).getId().getSxh();
    		sxh++;
    	}
    	this.exceldao.batchUpdate(updatesql, obj3);
    	Object obj4[][]=new Object[list2.size()][13];
    	String saveda="insert into cztda(sxh,dt_th,bzxh,zsdbh,zbh,c_id,bufz,sheetname,wz,da,cda,carea,yzsx)    values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	int hao=0;
    	for(int i=0;i<list1.size();i++)
    	{
    		int bh=list1.get(i).getId().getSxh();
    		for(int j=hao;j<list2.size();j++)
    		{
    		       	if(bh!=list2.get(j).getId().getSxh())
    		       	{
    		       		num++;
    		       		System.out.println(num);
    		       		break;
    		       	}
    		       	obj4[hao][0]=num;
    		       	obj4[hao][1]=list2.get(j).getId().getDtTh();
    		       	obj4[hao][2]=list2.get(j).getId().getBzxh();
    		       	obj4[hao][3]=list2.get(j).getZsd().getId().getZsdbh();
    		       	obj4[hao][4]=list2.get(j).getZsd().getId().getZbh();
    		       	obj4[hao][5]=list2.get(j).getZsd().getId().getCId();
    		       	obj4[hao][6]=list2.get(j).getBufz();
    		       	obj4[hao][7]=list2.get(j).getSheetname();
    		       	obj4[hao][8]=list2.get(j).getWz();
    		       	obj4[hao][9]=list2.get(j).getDa();
    		       	obj4[hao][10]=list2.get(j).getCda();
    		       	obj4[hao][11]=list2.get(j).getCarea();
    		       	obj4[hao][12]=list2.get(j).getYzsx();
    		       	hao++;
    		}
    	}
    	this.exceldao.batchUpdate(saveda, obj4);
    	
    	
    	
	  /*  for(int i=0;i<list1.size();i++)
	    {
	    	Czt czt1=new Czt();
	    	czt1=list1.get(i);
	    	int bh=czt1.getId().getSxh();
	    	CztId id1=new CztId();
	    	id1=czt1.getId();
	    	this.baseservice.delete(czt1);
	    	id1.setSxh(sxh);
	    	sxh++;
	    	czt1.setId(id1);
	    	this.baseservice.save(czt1); 	
	    	for(int j=0;j<list2.size();j++)
	    	{
	    		this.baseservice.save(list2.get(j));
	    	}
	    }*/
	    message="操作题删除成功";
		return SUCCESS;
	}
	
	
	@Action(value="/updatext",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String updatext()
	{
		Czt czt=new Czt();
		CztId id=new CztId();
		id.setDtTh(cztdto.getTh());
		id.setSxh(cztdto.getSxh());
		czt.setId(id);
		czt.setNyd(cztdto.getNyd());
		czt.setCsrcs(cztdto.getCsrcs());
		czt.setTg(cztdto.getTg());
		czt.setXtfz(cztdto.getXtfz());
		czt.setZqrcs(cztdto.getZqrcs());
		this.baseservice.update(czt);
		message="小题修改成功";
		return SUCCESS;
	}
	
	@Action(value="/plluruxt",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	 public String plluruxt()
     {
		Object obj[][]=new Object[rows.size()][7];
		String sql="insert into czt(sxh,dt_th,tg,xtfz,nyd,csrcs,zqrcs) values(?,?,?,?,?,?,?)";
        for(int i=0;i<this.rows.size();i++)
        {
          Cztdto cztdto=rows.get(i);
          obj[i][5]=cztdto.getCsrcs();
          obj[i][4]=cztdto.getNyd();
          String tg=cztdto.getTg().replace("$^", "&");
          obj[i][2]=tg;
          obj[i][3]=cztdto.getXtfz();
          obj[i][6]=cztdto.getZqrcs();
          obj[i][1]=cztdto.getTh();
          obj[i][0]=cztdto.getSxh();
        }
        this.exceldao.batchUpdate(sql, obj);
        message="小题批量录入成功";
        return SUCCESS;
     }


}
