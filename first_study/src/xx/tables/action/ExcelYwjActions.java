/*
 *@(#)xx.tables.action
 *@ExcelYwjActions.java.java  
 *@����ʱ��:2011-8-21����02:30:40
 *@���ߣ�Administrator
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.tables.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Cztdto;
import xx.collection.bean.Cztywj;
import xx.collection.bean.Cztywjdto;
import xx.quanxian.service.BaseService;

import com.opensymphony.xwork2.ActionSupport;



@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class ExcelYwjActions extends ActionSupport {
	private List<Cztywjdto> rows=new ArrayList<Cztywjdto>();
	 @Resource(name="baseService")
	private BaseService baseservice;
	private String message;
    private int tihao;
    private int bh;
    private List<Integer>  bianhao=new ArrayList<Integer>();
	private File file;
    private String fileFileName; 
	private String fileContentType; 
	private Cztywj cztywj=new Cztywj();
    private int total;
    
	

	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}

	public List<Integer> getBianhao() {
		return bianhao;
	}
	
	public void setBianhao(List<Integer> bianhao) {
		this.bianhao = bianhao;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public Cztywj getCztywj() {
		return cztywj;
	}
	public void setCztywj(Cztywj cztywj) {
		this.cztywj = cztywj;
	}
	

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getBh() {
		return bh;
	}
	public void setBh(int bh) {
		this.bh = bh;
	}
	public List<Cztywjdto> getRows() {
		return rows;
	}
	public void setRows(List<Cztywjdto> rows) {
		this.rows = rows;
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
	

	@Action(value="/getallwj",results={@Result(name="success",type="json",params={"includeProperties","rows.*,total"})})
	 public  String getAllYwj()
	 {
		/*HttpServletResponse response = ServletActionContext.getResponse();   
		Cookie cookie=new Cookie("tihao",tihao+"");
		cookie.setMaxAge(60*60);
		response.addCookie(cookie);*/
		List<Cztywj> list=this.baseservice.findHql(Cztywj.class, "from Cztywj where cztd.th="+this.tihao);
		if(list.size()>0)
		{
			Cztywj cztywj=list.get(0);
			Cztywjdto wjdto=new Cztywjdto();
			wjdto.setBh(cztywj.getBh());
			wjdto.setDtth(cztywj.getCztd().getTh());
			wjdto.setMs(cztywj.getMs());
			wjdto.setWjmc(cztywj.getWjmc());
			this.rows.add(wjdto);
		    this.total=1;
		}
		else
		{
			total=0;
		}
		return  SUCCESS; 
	 }
	
	
	@Action(value="/pldelwj",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	 public String delwj()
	{
		for(int i=0;i<this.bianhao.size();i++)
		{
		Cztywj cztywj=new Cztywj();
		cztywj.setBh(this.bianhao.get(i));
		this.baseservice.delete(cztywj);
		}
		message="文件删除成功";
		return SUCCESS;
	}
	

	@Action(value="/getwj",results={@Result(name="success",type="json",params={"includeProperties","cztywj.*"})})
	public String getwj()
	{
		List<Cztywj> list=this.baseservice.findHql(Cztywj.class, "from Cztywj where bh="+bh);
		cztywj=list.get(0);
		return SUCCESS;
	}
	
	@Action(value="/updatewj",results={@Result(name="success",location="/cztjsp/New5.jsp")})
	public String updatewj()
	{
		HttpServletRequest request= ServletActionContext.getRequest(); 
		    	String name=null;
		    	InputStream in=null;	

	    		try {
	    			in = new FileInputStream(this.file);	
	                cztywj.setFile(this.InputStreamToByte(in));
	                name=this.fileFileName;
	                cztywj.setWjmc(name);
	    		    this.baseservice.update(cztywj);     
	    			in.close();		
	    			request.setAttribute("sfwj", 1);
	    			request.setAttribute("wjms", this.cztywj.getMs());
	    		}  catch (Exception e) {
	    			request.setAttribute("sfwj", 0);
	    			e.printStackTrace();
	    		}
	    	
	/*	    	else
		    	{
		    		Cztywj ywj=this.baseservice.find(Cztywj.class, cztywj.getBh());
		    		String wjm=ywj.getWjmc();
		    		name=this.fileFileName;
		    		this.tihao=cztywj.getCztd().getTh();
		    		String path=this.getClass().getResource("/").toString();
		    		path=path.substring(0,path.length()-17);
		    		path=path.substring(6,path.length());
		    		File file = new File(path+"//cztimages//"+tihao+"//"+wjm);
		    		   if(file.exists()){
		    		    boolean d = file.delete();
		    		   }
	              BufferedImage image;
				try {
					image = ImageIO.read(this.file);
					String hz=name.substring(name.length()-3, name.length());
				    ImageIO.write(image,hz , new File(path+"//cztimages//"+tihao+"//"+name));
				    cztywj.setFile(null);
				    cztywj.setWjmc(this.fileFileName);
				    this.baseservice.update(cztywj);
				    request.setAttribute("sfwj", 1);
				} catch (IOException e) {
					request.setAttribute("sfwj", 0);
					e.printStackTrace();
				}
		
		    	}*/
		   
			return SUCCESS;	
	
	}
	private byte[] InputStreamToByte(InputStream is) throws IOException {   
		   ByteArrayOutputStream bytestream = new ByteArrayOutputStream();   
		   int ch;   
		   while ((ch = is.read()) != -1) {   
		    bytestream.write(ch);   
		   }   
		   byte imgdata[] = bytestream.toByteArray();   
		   bytestream.close();   
		   return imgdata;   
		  } 

}
