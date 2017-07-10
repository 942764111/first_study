/*
 *@(#)xx.tables.action
 *@ExcelActions1.java.java  
 *@ï¿½ï¿½ï¿½ï¿½Ê±ï¿½ï¿½:2011-8-16ï¿½ï¿½ï¿½ï¿½08:35:05
 *@ï¿½ï¿½ï¿½ß£ï¿½Administrator
 *@Copyright 2009-2010 ï¿½Ó±ï¿½ï¿½ï¿½ï¿½ï¿½Ñ§Ôºï¿½ï¿½Ï¢ï¿½ï¿½Ñ§ï¿½ë¹¤ï¿½ï¿½Ñ§Ôºï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ 
 */

package xx.tables.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import xx.collection.bean.Children;
import xx.collection.bean.Czt;
import xx.collection.bean.Cztbg;
import xx.collection.bean.Cztd;
import xx.collection.bean.CztdDto;
import xx.collection.bean.Cztda;
import xx.collection.bean.CztdaId;
import xx.collection.bean.Sjnr;
import xx.collection.bean.XsdyjlCztZsd;
import xx.collection.bean.Zsd;
import xx.quanxian.service.BaseService;
import xx.tables.dao.ExcelDao;

/**
 * @ExcelActions1 <code>{ï¿½ï¿½ï¿½ï¿½ï¿½}</code>
 * @author  {ï¿½ï¿½ï¿½ï¿½}
 * @version {ï¿½æ±¾,ï¿½ï¿½ï¿½ï¿½Ê±ï¿½ï¿½ï¿½ï¿½ï¿½}
 * @{ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class ExcelBgActions extends ActionSupport {
	
	private ArrayList<Cztbg>  rows=new ArrayList<Cztbg>();
	private int num;
	@Resource(name="baseService")
	private BaseService baseservice;
	private int tihao;
	private String pf;
	private List<Integer> defen=new ArrayList<Integer>();
	private int sjbh;
	private int noopen;
	@Resource(name="exceldao")
	private ExcelDao exceldao;

	


	public int getNoopen() {
		return noopen;
	}
	public void setNoopen(int noopen) {
		this.noopen = noopen;
	}
	public int getSjbh() {
		return sjbh;
	}
	public void setSjbh(int sjbh) {
		this.sjbh = sjbh;
	}
	public List<Integer> getDefen() {
		return defen;
	}
	public void setDefen(List<Integer> defen) {
		this.defen = defen;
	}
	
	public String getPf() {
		return pf;
	}
	public void setPf(String pf) {
		this.pf = pf;
	}
	public int getTihao() {
		return tihao;
	}
	public void setTihao(int tihao) {
		this.tihao = tihao;
	}
	public BaseService getBaseservice() {
		return baseservice;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<Cztbg> getRows() {
		return rows;
	}

	public void setRows(ArrayList<Cztbg> rows) {
		this.rows = rows;
	}
	
	public ExcelDao getExceldao() {
		return exceldao;
	}
	public void setExceldao(ExcelDao exceldao) {
		this.exceldao = exceldao;
	}
	@Action(value="/gettf",results={@Result(name="success",type="json",params={"includeProperties","pf"})})
	public String gettf()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		int sjbh=(Integer)request.getSession().getAttribute("testpaperno");
		//»ñµÃÓÃ»§µÄid£¬ÎªÅÐ¶ÎÆätypeÊÇÀÏÊ¦µÄT£¬»¹ÊÇÑ§ÉúµÄS
		String userid=(String)request.getSession().getAttribute("uid");
		String hql="select distinct type from Userinfo where UserId='"+userid+"'";
         String type=this.baseservice.findHql(String.class, hql).get(0);
         
		Sjnr sjnr=this.baseservice.find(Sjnr.class, sjbh);
		XsdyjlCztZsd xsd=new XsdyjlCztZsd();
		List <Cztda>  list=this.baseservice.findHql(Cztda.class,"select  new Cztda(id,zsd.zsdmc,zsd.id.zsdbh,zsd.id.zbh,zsd.id.CId,bufz,sheetname,wz,da,cda,carea,yzsx) from Cztda where id.dtTh="+tihao+"  order by id.dtTh ,id.sxh");
		String aa=sjnr.getCztsz();
		String aaa[]=aa.split(",");
		String aaaa[]=null;
		for(int i=0;i<aaa.length;i++)
		{
			   String nr=aaa[i];
			   aaaa=nr.split("\\|");	
			   if(aaaa[0].equals(this.tihao+""))
			   {
				   break;
			   }
		}
		int size=aaaa.length-1;
		if(noopen==1)
		{
			this.pf=tihao+"|";
			for(int i=0;i<size-1;i++)
			{		  
			  pf+=i+1+",";
			}
			pf+=size+"|";
			for(int i=0;i<size-1;i++)
			{
				pf+="F,";
			}
			pf+="F";
			Object obj[][]=new Object[list.size()][6];
			String sql="insert into xsdyjl_czt_zsd(sjno,UserId,sxh,dt_th,zsdbh,zqf) value(?,?,?,?,?,?)";
			for(int i=0;i<list.size();i++)
			{
				obj[i][0]=sjbh;//gq ago:this.sjbh
				obj[i][1]=(String)request.getSession().getAttribute("uid");
				obj[i][2]=list.get(i).getId().getSxh();
				obj[i][3]=list.get(i).getId().getDtTh();
				obj[i][4]=list.get(i).getZsd().getId().getZsdbh();
				obj[i][5]="F";
				
			}
			if(type.equals("S")){//½öÔÚÑ§ÉúÊ±£¬²ÅÍùxsdyjl_czt_zsd±íÖÐ²åÈëÊý¾Ý
				this.exceldao.batchUpdate(sql, obj);
			}
			
			return SUCCESS;
		}
		this.pf=tihao+"|";
		for(int i=0;i<size-1;i++)
		{		  
		  pf+=i+1+",";
		}
		pf+=size+"|";
		int num=0;
		Object obj[][]=new Object[list.size()][6];
		String sql="insert into xsdyjl_czt_zsd(sjno,UserId,sxh,dt_th,zsdbh,zqf) value(?,?,?,?,?,?)";
		for(int i=0;i<size;i++)
		{
			     int sxh=i+1;
				  int pd=1;
				  int cztnum=0;
				  for(int k=0;k<list.size();k++)
				  {
					  if(list.get(k).getId().getSxh()-sxh==0)
					  {
						  cztnum++;
					  }
				  }
				  for(int j=0;j<cztnum;j++)
				  {
					    obj[num][0]=sjbh;//gq ago:this.sjbh
						obj[num][1]=(String)request.getSession().getAttribute("uid");
						obj[num][2]=list.get(num).getId().getSxh();
						obj[num][3]=list.get(num).getId().getDtTh();
						obj[num][4]=list.get(num).getZsd().getId().getZsdbh();
						obj[num][5]="T";
				   if(list.get(num).getBufz()-defen.get(num)!=0)
				   {
					   obj[num][5]="F";
					   pd=0;
				   }
				   num++;
				  }
			   if(pd==1)   
			   {
				   pf+="T,";
			   }
			   else
			   {
				   pf+="F,";
			   }

		}
		if(type.equals("S")){//½öÔÚÑ§ÉúÊ±£¬²ÅÍùxsdyjl_czt_zsd±íÖÐ²åÈëÊý¾Ý
			this.exceldao.batchUpdate(sql, obj);
		}
		
		pf=pf.substring(0, pf.length()-1);
		System.out.println(pf);
		return SUCCESS;
	}
	
	
	@Action(value="/getbg",results={@Result(name="success",type="json",params={"includeProperties","rows.*"})})
	public String getBaogao()
	{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		List<Integer> defen=new ArrayList<Integer>();
		List<String> sxhs=new ArrayList<String>();
		List<String> bzhs=new ArrayList<String>();
		Cookie cookie[]=request.getCookies();
		int length=cookie.length;
		int num=0;
		for(int i=0;i<cookie.length;i++)
		{
			if(cookie[i].getName().equals("xiazaitihao"))
			{
				tihao=Integer.parseInt(cookie[i].getValue());
			}
			if(cookie[i].getName().equals("num"))
			{
				num=Integer.parseInt(cookie[i].getValue());
			}
		}
		int m=0;
		int n=0;
		while(n<length)
		{
		    	        if(cookie[n].getName().equals("defen"+m))
		    			{
		    		           defen.add(m, Integer.parseInt(cookie[n].getValue()));
		    		           m++;
		    		           if(m==num)
		    		           {
		    		        	   break;
		    		           }
		    			}
		    	        n++;
		}

		List<Czt> list=this.baseservice.findHql(Czt.class, "from Czt  where cztd.th="+tihao);
		int count=list.size();
		int zf=0;
		int countnum=0;
		List <Cztda>  lists=this.baseservice.findHql(Cztda.class,"select  new Cztda(id,zsd.zsdmc,zsd.id.zsdbh,zsd.id.zbh,zsd.id.CId,bufz,sheetname,wz,da,cda,carea,yzsx)  from Cztda where id.dtTh="+tihao+"  order by id.dtTh ,id.sxh");
		for(int j=1;j<=count;j++)
		{
    	    Cztbg cztbg=new Cztbg();
		    List<Children> childrenlist=new ArrayList<Children>();
		    int fen1=0;
		    int fen2=0;
		    int cztdasize=0;
		    for(int l=0;l<lists.size();l++)
		    {
		    	if(lists.get(l).getId().getSxh()-j==0)
		    	{
		    		cztdasize++;
		    	}
		    }
            for(int k=0;k<cztdasize;k++)
            {
		    	Children children=new Children();
		    	Cztda cztda=lists.get(countnum);
		    	children.setJytg(lists.get(countnum).getYzsx());
		    	children.setId(k+1);
		    	children.setSjdf(defen.get(countnum)+""); 	
		    	children.setYdf(cztda.getBufz()+"");
		    	children.setZsd(lists.get(countnum).getZsd().getZsdmc());
		    	childrenlist.add(k, children);
		    	fen1+=defen.get(countnum);
		    	fen2+=cztda.getBufz();
		    	countnum++;
            }
		    cztbg.setJytg(Html2Text(list.get(j-1).getTg())+"......");
		    cztbg.setChildren(childrenlist);
		    cztbg.setId(j);
		    cztbg.setState("closed");
		    cztbg.setSjdf(fen1+"");
		    cztbg.setYdf(fen2+"");
		    cztbg.setZsd("");
		    this.rows.add(j-1,cztbg);
		    zf=zf+fen1;
		}
		System.out.println("zf:"+zf);
		Cztd cztd=this.baseservice.find(Cztd.class, tihao);
		Cztbg cztbg=new Cztbg();
		cztbg.setSjdf(zf+"");
		cztbg.setId(count+1);
		cztbg.setZsd("æ€»åˆ†");
		cztbg.setYdf(cztd.getDtfz()+"");
		this.rows .add(count,cztbg);
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
	

}
