package xx.tables.action;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.collection.bean.CourseChapter;
import xx.collection.bean.Czt;
import xx.collection.bean.CztId;
import xx.collection.bean.Cztd;
import xx.collection.bean.CztdDto;
import xx.collection.bean.Cztda;
import xx.collection.bean.Cztdadto;
import xx.collection.bean.Cztywj;
import xx.collection.bean.Jdto;
import xx.collection.bean.Jie;
import xx.collection.bean.Kcdto;
import xx.collection.bean.Sjbhdto;
import xx.collection.bean.Sjnr;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Xsdyjl;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zdto;
import xx.collection.bean.Zsd;
import xx.collection.bean.ZsdKeyDto;
import xx.collection.bean.Zsddto;
import xx.collection.bean.Zysc;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class ExcelActions extends ActionSupport {


	@Resource(name="baseService")
	private BaseService baseservice;
	@Resource(name="adminService")
	private AdminService adminservice;
	private String message;
	private List<Integer> bianhao=new ArrayList<Integer>();
	private List<Cztd> rows1=new ArrayList<Cztd>();
	private ArrayList<CztdDto>  rows=new ArrayList<CztdDto>();
	private Cztd cztd=new Cztd();
	private int tihao;
	private String queryWord;
	private int rows_s;
	private int page;
	private int total;
	private String tiganxxin;
	private Czt czt=new Czt();
	private Cztda cztdaan;
    private Zsd zsd;
	private Cztywj cztywj;
    private List<Czt> cztlist=new ArrayList<Czt>();
    private List<Cztywj> cztywjlist=new ArrayList<Cztywj>();
    private List<Cztda>  cztdalist=new ArrayList<Cztda>();
	private List<File> file=new ArrayList<File>(); 
    private List<String> fileFileName; 
	private List<String> fileContentType; 
	private int id;
	private int fenzhi;
	private String xtigan;
	private String   filenames;
    private String[] filenameArray = null;
    private String   filename;
    private  List<Zsddto> zsddto=new ArrayList<Zsddto>();
    private List<Integer> defen=new ArrayList<Integer>();
    private List<Cztdadto> dadto=new ArrayList<Cztdadto>();
    private String filenum;
    private List<String> pictureurl=new ArrayList<String>();
    private List<String> picturename=new ArrayList<String>();
    private List<ZsdKeyDto> zsdkeydto=new ArrayList<ZsdKeyDto>();
    private String username;
    private List<Kcdto> kcdto=new ArrayList<Kcdto>();
    private String kcmc;
    private String zmc;
    private String jmc;
    private String tggjz;
	private List<Zdto>  zdto=new ArrayList<Zdto>();
	private List<Jdto>  jdto=new ArrayList<Jdto>();
	private List<Sjbhdto> sjbhdto=new ArrayList<Sjbhdto>();
	private List<CztdDto>  cxcztd=new ArrayList<CztdDto>();
    private String userId;
	private List<Yhzdymc> listy = new ArrayList<Yhzdymc>();	
	
	
	/**
	 * 用户收藏代码begin
	 */
	private String QueryWord;
	private int Radio;
	/**
	 * 用户收藏代码end
	 */
	
	@JSON(serialize=false)
	public List<Yhzdymc> getListy() {
		return listy;
	}

	public void setListy(List<Yhzdymc> listy) {
		this.listy = listy;
	}


    
    
    
	public List<CztdDto> getCxcztd() {
		return cxcztd;
	}

	public void setCxcztd(List<CztdDto> cxcztd) {
		this.cxcztd = cxcztd;
	}

	public String getQueryWord() {
		return queryWord;
	}

	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}

	public List<Sjbhdto> getSjbhdto() {
		return sjbhdto;
	}

	public void setSjbhdto(List<Sjbhdto> sjbhdto) {
		this.sjbhdto = sjbhdto;
	}


    public String getTggjz() {
		return tggjz;
	}

	public void setTggjz(String tggjz) {
		this.tggjz = tggjz;
	}
	public AdminService getAdminservice() {
		return adminservice;
	}

	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}

	public String getJmc() {
		return jmc;
	}

	public void setJmc(String jmc) {
		this.jmc = jmc;
	}

	public List<Jdto> getJdto() {
		return jdto;
	}

	public void setJdto(List<Jdto> jdto) {
		this.jdto = jdto;
	}

	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	
	public String getZmc()
	{
		return zmc;
	}



	public List<Zdto> getZdto() {
		return zdto;
	}



	public void setZdto(List<Zdto> zdto) {
		this.zdto = zdto;
	}



	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}



	public List<Kcdto> getKcdto() {
		return kcdto;
	}



	public void setKcdto(List<Kcdto> kcdto) {
		this.kcdto = kcdto;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public List<ZsdKeyDto> getZsdkeydto() {
		return zsdkeydto;
	}



	public void setZsdkeydto(List<ZsdKeyDto> zsdkeydto) {
		this.zsdkeydto = zsdkeydto;
	}



	public List<String> getPictureurl() {
		return pictureurl;
	}



	public void setPictureurl(List<String> pictureurl) {
		this.pictureurl = pictureurl;
	}



	public List<String> getPicturename() {
		return picturename;
	}



	public void setPicturename(List<String> picturename) {
		this.picturename = picturename;
	}



	public String getFilenum() {
		return filenum;
	}



	public void setFilenum(String filenum) {
		this.filenum = filenum;
	}


	public List<Integer> getBianhao() {
		return bianhao;
	}


	public void setBianhao(List<Integer> bianhao) {
		this.bianhao = bianhao;
	}

	public List<Cztdadto> getDadto() {
		return dadto;
	}

	public void setDadto(List<Cztdadto> dadto) {
		this.dadto = dadto;
	}

	public List<Integer> getDefen() {
		return defen;
	}

	public void setDefen(List<Integer> defen) {
		this.defen = defen;
	}

	public List<Zsddto> getZsddto() {
		return zsddto;
	}

	public void setZsddto(List<Zsddto> zsddto) {
		this.zsddto = zsddto;
	}

	public String getFilenames() {
        return filenames;
    }
  
    public void setFilenames(String filenames) {
        this.filenames = filenames;
        if (this.filenames.contains("|")) {
            parseFilenamesToArray();
        }
    }
    
    public void parseFilenamesToArray() {
        filenameArray = filenames.split("\\|");
    }
    
    public String getFilename()
    {
      
	  return this.filename;
	 
    }
 
    


   
    public void setFilename(int  tihao) {
    
        if(isBaleZip()){
            this.filename = "Excel������"+tihao+".zip";
        }else{
        	this.filename=this.getFilenames();
        }
 
     
    }
    
    public  boolean isBaleZip(){

        boolean isZip = false;
        if(this.filenameArray!= null  && this.filenameArray.length>0){
             isZip =  true;
        }
        return isZip;
    }
    
 

    public void baleZip(String zipFilePath,InputStream[] input,String names[]) throws IOException{
        File f = new File(zipFilePath);
        f.createNewFile();
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
       out.putNextEntry(new ZipEntry("/"));
        for(int i=0;i<names.length;i++){
            out.putNextEntry(new ZipEntry(names[i])); 
            int b;
            while ((b = input[i].read()) != -1) {
                out.write(b);
            }
            input[i].close();
        }
         out.flush();
         out.close();
    }
      
      
    
    
 



    
    @Action(value="/download",results={@Result(type ="stream", params = { "contentType", "application/octet-stream;charset=ISO8859-1", "inputName", "inputStream", "contentDisposition", " attachment;filename=${filename}", "bufferSize", "4096" }, name = "success")})
    public String download()
    {	
    	
    	/*HttpServletRequest request = ServletActionContext.getRequest();
    	request.getSession().getServletContext().getRealPath("/");
    	Cookie cookie[]=request.getCookies();
    	for(int i=0;i<cookie.length;i++)
    	{
    		if(cookie[i].getName().equals("tihao"))
    		{
    			this.tihao=Integer.parseInt(cookie[i].getValue());
    		}
    	}*/
    	return "success";
    } 
	@Action(value="/getwjmc",results={@Result(name="success",type="json",params={"includeProperties","filename"})})	 
    public String getWjmc() throws UnsupportedEncodingException
    {
		List<Cztywj> list=this.baseservice.findHql(Cztywj.class, "from Cztywj where cztd.th="+tihao);
		if(list.size()==0)
		{
			filename="null";
			return SUCCESS;
		}
    	byte buff[]=list.get(0).getFile();
    	this.filename=list.get(0).getWjmc();
        return SUCCESS;
    }
  
    
	

    
    
    public InputStream getInputStream(){
    	
    	List<Cztywj> list=this.baseservice.findHql(Cztywj.class, "from Cztywj where cztd.th="+tihao);
    	this.filenames="";
    	int excelid=0;
        this.filenames=this.filenames+list.get(0).getWjmc();
        excelid=list.size()-1;
    	this.setFilenames(filenames);
    	Cztd cztd=this.baseservice.find(Cztd.class, tihao);
    	setFilename(tihao);
        InputStream inputStream=null;
        File tempfile = null;
        inputStream=new ByteArrayInputStream(list.get(excelid).getFile());
        return inputStream;
    }
    
    
   
 

      
    
    
    

    
    
    
    



    
    
   


	public int getFenzhi() {
		return fenzhi;
	}
	public void setFenzhi(int fenzhi) {
		this.fenzhi = fenzhi;
	}
	public String getXtigan() {
		return xtigan;
	}
	public void setXtigan(String xtigan) {
		this.xtigan = xtigan;
	}
	public int getId() {
		return id;
	}
	@JSON(deserialize=true)
	public void setId(int id) {
		this.id = id;
	}
	public List<Czt> getCztlist() {
		return cztlist;
	}
	@JSON(deserialize=true)
	public void setCztlist(List<Czt> cztlist) {
		this.cztlist = cztlist;
	}

	public List<File> getFile() {
		return file;
	}
	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
	public Zsd getZsd() {
		return zsd;
	}
	public void setZsd(Zsd zsd) {
		this.zsd = zsd;
	}
	
	public Cztda getCztdaan() {
		return cztdaan;
	}
	
	@JSON(deserialize=true)
	public void setCztdaan(Cztda cztdaan) {
		this.cztdaan = cztdaan;
	}
	public Cztywj getCztywj() {
		return cztywj;
	}
	public void setCztywj(Cztywj cztywj) {
		this.cztywj = cztywj;
	}
	public List<Cztywj> getCztywjlist() {
		return cztywjlist;
	}
	public void setCztywjlist(List<Cztywj> cztywjlist) {
		this.cztywjlist = cztywjlist;
	}
	

    public List<Cztda> getCztdalist() {
		return cztdalist;
	}
	public void setCztdalist(List<Cztda> cztdalist) {
		this.cztdalist = cztdalist;
	}

	
	
	
	
	
	public Czt getCzt() {
		return czt;
	}
	@JSON(deserialize=true)
	public void setCzt(Czt czt) {
		this.czt = czt;
	}

	public String getTiganxxin() {
		return tiganxxin;
	}

	public void setTiganxxin(String tiganxxin) {
		this.tiganxxin = tiganxxin;
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

	
	


	public int getTihao() {
		return tihao;
	}
	public void setTihao(int tihao) {
		this.tihao = tihao;
	}

	public Cztd getCztd() {
		return cztd;
	}
	@JSON(deserialize=true)
	public void setCztd(Cztd cztd) {
		this.cztd = cztd;
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
	

	

	public List<Cztd> getRows1() {
		return rows1;
	}

	public void setRows1(List<Cztd> rows1) {
		this.rows1 = rows1;
	}

	public ArrayList<CztdDto> getRows() {
		return rows;
	}

	public void setRows(ArrayList<CztdDto> rows) {
		this.rows = rows;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public int getRows_s() {
		return rows_s;
	}

	public void setRows_s(int rowsS) {
		rows_s = rowsS;
	}


	
	
	
	@Action(value="/lu",results={@Result(name="success",type="json",params={"includeProperties","message,tihao"})})	
	public String luRu() throws InterruptedException 
	{
		    int num=this.baseservice.getTotalSql("select count(*)  from Cztd where tg='"+cztd.getTg()+"'");
		    if(num!=0)
		    {
		    	message="1";
		    	return SUCCESS;
		    }
			this.baseservice.save(cztd);
			 message="操作题录入成功";
			 List<Cztd>  list=this.baseservice.findHql(Cztd.class, "from Cztd where tg='"+cztd.getTg()+"'");
			 tihao=list.get(0).getTh();  
	        return "success";
	}
	@Action(value="/gets",results={@Result(name="success",type="json",params={"includeProperties","rows.*,total"})})
	public String gCzt()
	{ 
	    total=this.baseservice.getTotal("Cztd");
	    rows1= this.baseservice.findAll(Cztd.class,"Cztd", page, rows_s);
	    for(Cztd cztd:rows1)
	    {
	    	String tg=cztd.getTg();
	    	tg=this.Html2Text(tg);
	    	CztdDto dto=new CztdDto();
	    	dto.setFenzhi(cztd.getDtfz());
	    	dto.setJytigan(tg.substring(0, 15)+".......");
	    	dto.setTihao(cztd.getTh());
	    	rows.add(dto);
	    }
		return "success";
	}
	


	
	@Action(value="/Cztupdate",results={@Result(name="success",type="json",params={"includeProperties","message"})})	
	public String update()
	{
	    this.baseservice.update(cztd);
		message="修改成功";
		return "success";
	}
	
	

	@Action(value="fileUpload",results={@Result(name="success",location="/cztjsp/New4.jsp")})
	public String fileUpload()
	{

		HttpServletRequest request= ServletActionContext.getRequest(); 
		request.setAttribute("tihao", tihao);
		request.setAttribute("sf", 1);
		   int num=this.baseservice.getTotalSql("select count(*) from Cztywj where cztd.th="+this.tihao);
		    if(num==1)
		    {
		    	request.setAttribute("sf", 0);
		    	return SUCCESS;
		    }
	    	String name=null;
	    	Cztywj cztywj=new Cztywj();
	    	File f=this.file.get(0);
	    	name=this.fileFileName.get(0);
	    	cztywj=this.cztywjlist.get(0);
	    	cztywj.setWjmc(name);
	    	Cztd cztd=new Cztd();
	    	cztd.setTh(tihao);
	    	cztywj.setCztd(cztd);
	    	InputStream in=null;  	
    		try {
    			cztywj.setFile(null);
    			in = new FileInputStream(f);
                cztywj.setFile(this.InputStreamToByte(in));
    		   this.baseservice.save(cztywj);     
    			in.close();
    		}  catch (Exception e) {
    			e.printStackTrace();
    		}   	
		return SUCCESS;
	}
    
	
	public  String getFileMD5(File file) {
	    if (!file.isFile()){
	      return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in=null;
	    byte buffer[] = new byte[1024];
	    int len;
	    try {
	      digest = MessageDigest.getInstance("MD5");
	      in = new FileInputStream(file);
	      while ((len = in.read(buffer, 0, 1024)) != -1) {
	        digest.update(buffer, 0, len);
	      }
	      in.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	    BigInteger bigInt = new BigInteger(1, digest.digest());
	    return bigInt.toString(16);
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
	

	@Action(value="/getdt",results={@Result(name="success",type="json",params={"includeProperties","cztd.*"})})
	public String getXinxi()
	{
		this.cztd=this.baseservice.find(Cztd.class, tihao);
	    return SUCCESS;
	}


	@Action(value="/getall",results={@Result(name="success",type="json",params={"includeProperties","message,cztywj.*,cztd.*,czt.*"}),@Result(name="error",type="json",params={"includeProperties","message"})})
	public String getsAll()
	{
		List list=null;
		list=this.baseservice.findHql(Cztd.class, "from Cztd where th="+tihao);
		if(list.size()==0)
		{
		  message="没有这个题";
		  return ERROR;
		}
 	    cztd=(Cztd)list.get(0);
 	   list=this.baseservice.findHql(Czt.class, "from Czt where id.dtTh="+tihao+"  and id.sxh=1");
 	   czt=(Czt)list.get(0);
 	    list=this.baseservice.findHql(Cztywj.class, "from Cztywj where cztd.th="+tihao);
		if(list.size()>0)
		{
		Cztywj cztywj=(Cztywj)list.get(0);
		}
		message="success";
           return SUCCESS;		
	}
	
	
	@Action(value="/getexercise",results={@Result(name="success",type="json",params={"includeProperties","message,id,picturename.*,pictureurl.*"}),@Result(name="error",type="json",params={"includeProperties","message,id,picturename.*,pictureurl.*"})})
	public String getExecrise()
	{
		HttpServletResponse response = ServletActionContext.getResponse();   
		Cookie cookie=new Cookie("tihao",tihao+"");
		cookie.setMaxAge(60*60);
		response.addCookie(cookie);
		int num=this.baseservice.getTotalSql("select count(*) from Cztywj  where cztd.th="+tihao);
		if(num==0)
		{
			message="no";
			return ERROR;
		}
		int num1=this.baseservice.getTotalSql("select count(*)  from Czt where cztd.th="+tihao);
		if(num1==0)
		{
			message="no";
			return ERROR;
		}
		for(int i=0;i<num1;i++)
		{
		int f=i+1;
		int num2=this.baseservice.getTotalSql("select count(*) from Cztda where id.dtTh="+tihao+"and id.sxh="+f);
		if(num2==0)
		{
			message="no";
			return ERROR;
		}
		}
		List list=null;
		list=this.baseservice.findHql(Cztd.class, "from Cztd where th="+tihao);
 	    cztd=(Cztd)list.get(0);
 	    message=cztd.getTg()+"<BR/>";
 	    List<Czt>  lists=this.baseservice.findHql(Czt.class, "select new Czt(id,tg,xtfz,nyd,csrcs,zqrcs)  from Czt where  id.dtTh="+tihao);
 	    for(int i=0;i<lists.size();i++)
 	    {
 	    	message+=lists.get(i).getTg()+"<BR/>";
 	    }
		return SUCCESS;
	}

	@Action(value="/getzsdzj",results={@Result(name="success",type="json",params={"includeProperties","zsddto.*"})})
	public String getZsdzj()
	{
	   List<Zsd> list=this.baseservice.findHql(Zsd.class, "select new Zsd(id,zsdmc,zsdms,zsdkey,sfzd, sfdn)  from Zsd");
	   Zsddto zsd=new Zsddto();
	   zsd.setId(1);
	   zsd.setSelected(true);
	   zsd.setValue(list.get(0).getZsdmc());
	   zsddto.add(0, zsd);
	   for(int i=1;i<list.size();i++)
	   {
		   Zsddto zsdzj=new Zsddto();
		   zsdzj.setId(i+1);
		   zsdzj.setValue(list.get(i).getZsdmc());
		   zsdzj.setSelected(false);
		   zsddto.add(i, zsdzj);
	   }
	   return SUCCESS;
	}
	
	



	
	
	@Action(value="/addxxxt",results={@Result(name="success",type="json",params={"includeProperties","message,id"}),@Result(name="error",type="json",params={"includeProperties","message"})})
	 public String addXx()
    {
		int num=this.baseservice.getTotalSql("select count(*) from Cztd where th="+this.czt.getId().getDtTh());
		if(num==0)
	  {
		  message="没有这个大题";
		  return ERROR;
	  }
	  int bh=this.baseservice.getTotalSql("select count(*) from Czt where id.dtTh="+this.czt.getId().getDtTh());
	  CztId id=this.czt.getId();
	  id.setSxh(bh+1);
	  czt.setId(id);
	  this.baseservice.save(czt);
	  this.id=bh+1;
	  message="小题录入成功";
      return SUCCESS;
    }
    
	@Action(value="/getpfda",results={@Result(name="success",type="json",params={"includeProperties","cztdalist.*,id"})})
	public String getPfDa()
	{
		List<Cztda>  list1=null;
		this.id=0;
		this.cztdalist=this.baseservice.findHql(Cztda.class, "select  new Cztda(id,bufz,sheetname,wz,da,cda,carea,yzsx) from Cztda where id.dtTh="+tihao+"  order by id.dtTh ,id.sxh");
		id=this.cztdalist.size();
		/*List<Czt> list=this.baseservice.findHql(Czt.class, "from Czt where id.dtTh="+tihao);
		for(int i=0;i<list.size();i++)
		{
			list1=this.baseservice.findHql(Cztda.class,"from Cztda where id.dtTh="+tihao+"and id.sxh="+list.get(i).getId().getSxh());
		    for(int j=0;j<list1.size();j++)
		    {
		    	this.cztdalist.add(this.id,list1.get(j));
		    	this.id++;
		    }
		}*/
		return SUCCESS;
	}
	@Action(value="/searchtihao",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String searchTihao()
	{
		int num=this.baseservice.getTotalSql("select count(*) from Cztd where th="+this.tihao);
		if(num!=0)
		{
			message="success";
		}
		else
		{
			message="error";
		}
  		return SUCCESS;
	
	}
	
	
	@Action(value="/putpf",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String pushDf()
	{
		HttpServletResponse response = ServletActionContext.getResponse();   
		Cookie cookie=new Cookie("xiazaitihao",tihao+"");
		cookie.setMaxAge(60*60);
		response.addCookie(cookie);
		int count=this.defen.size();
		Cookie cookie1=new Cookie("num",count+"");
		response.addCookie(cookie1);
		for(int i=0;i<count;i++)
		{
			Cookie cookies=new Cookie("defen"+i,defen.get(i)+"");
			cookies.setMaxAge(60*60);
			response.addCookie(cookies);
		}
		message="success";
		return "success";
	}
	@Action(value="/pldel",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String plDel()
	{
		int tihaos[]=new int[bianhao.size()];
		for(int i=0;i<this.bianhao.size();i++)
		{
		   tihaos[i]=bianhao.get(i);
		}
		this.adminservice.delczt(tihaos);
		message="批量删除大题成功";
		return SUCCESS;
	}
	
//	@Action(value="/getCztd",results={@Result(name="success",location="/tables/datagrid_Czt.jsp",type="redirect")})
//	public String getallCztd(){
//		return SUCCESS;
//	}
	
	@Action(value="/getzsdkey",results={@Result(name="success",type="json",params={"includeProperties","zsdkeydto.*"})})
	public  String getZsdkey()
	{
	  List<Zsd> list=this.baseservice.findHql(Zsd.class,"from Zsd where zsdkey!=null and zsdkey!=''");
	  String key="";
	  String keys[];
	  int id=0;
	  String value="";
	  key=list.get(0).getZsdkey();
	  keys=key.split("；");
	  if(keys.length>1)
	  {
		  for(int j=0;j<keys.length;j++)
		  {
			  ZsdKeyDto zsdkey=new ZsdKeyDto();
			 if(id==0)
			 {
				 zsdkey.setSelected(true);
			 }
			 else
			 {
				 zsdkey.setSelected(false);
			 }
			 value=keys[j];
			 zsdkey.setId(id+1);
			 zsdkey.setValue(value);
			 this.zsdkeydto.add(id,zsdkey);
			 id++;
			 zsdkey=null;
		  }
	  }
	  else
	  {
		  ZsdKeyDto zsdkey=new ZsdKeyDto();
			 if(id==0)
			 {
				 zsdkey.setSelected(true);
			 }
			 else
			 {
				 zsdkey.setSelected(false);
			 }
			 value=keys[0];
			 zsdkey.setId(id+1);
			 zsdkey.setValue(value);
			 this.zsdkeydto.add(id,zsdkey);
			 id++;
			 zsdkey=null;
	  }
	
	  for(int i=1;i<list.size();i++)
	  {
		 
		  key=list.get(i).getZsdkey();
		  keys=key.split("；");
		  if(keys.length>1)
		  {
			  for(int j=0;j<keys.length;j++)
			  {
				 ZsdKeyDto zsd=new ZsdKeyDto();
				 value=keys[j];
				 zsd.setId(id+1);
				 zsd.setValue(value);
				 this.zsdkeydto.add(id,zsd);
				 id++;
				 zsd=null;
			  }
		  }
		  else
		  {
			     ZsdKeyDto zsd=new ZsdKeyDto();
				 value=keys[0];
				 zsd.setId(id+1);
				 zsd.setValue(value);
				 this.zsdkeydto.add(id,zsd);
				 id++;
				 zsd=null;
		  }
	  }
	  
	  for(int i=0;i<this.zsdkeydto.size();i++)
	  {
		  int j=0;
		  for(;j<i;j++)
		  {
			  if(this.zsdkeydto.get(j).getValue().equals(this.zsdkeydto.get(i).getValue()))
			  {
				  break;
			  }
		  }
		  if(j<i)
		  {
			   this.zsdkeydto.remove(i);
		  }  
	  }
	  return SUCCESS;
	}
	
	@Action(value="/getkcmc",results={@Result(name="success",type="json",params={"includeProperties","kcdto.*"})})
		public String getKcmcs()
	{
		List <CourseChapter> list=this.baseservice.findHql(CourseChapter.class, "from CourseChapter");
		int count=0;
		for(int i=0;i<list.size();i++)
		{
			Kcdto kc=new Kcdto();
		   int j=0;
		   for(;j<count;j++){
			   if(this.kcdto.get(j).getValue().equals(list.get(i).getTCName()))
			   {
				   break;
			   }
		   }
		   if(j>=count)
		   {
			   kc.setId(count+1);
			   kc.setValue(list.get(i).getTCName());
			   kcdto.add(count,kc);
			   count++;  
		   }
		}
		return SUCCESS;
	}
	public String getKcmc() {
		return kcmc;
	}

	@Action(value="/getzmc",results={@Result(name="success",type="json",params={"includeProperties","zdto.*"})})
	public String getZmcs() throws UnsupportedEncodingException
	{
		kcmc=new String(kcmc.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("课程名称:"+kcmc);
		
		List <CourseChapter> list=this.baseservice.findHql(CourseChapter.class, "from CourseChapter  where TCName='"+this.kcmc+"'");
		for(int i=0;i<list.size();i++)
		{
			Zdto z=new Zdto();
			z.setId(i+1);
			z.setValue(list.get(i).getCName());
			this.zdto.add(i,z);
		}
		return SUCCESS;
	
	}
	@Action(value="/getjmc",results={@Result(name="success",type="json",params={"includeProperties","jdto.*"})})
	public String getJmcs()
	{
		List<Jie> list=this.baseservice.findHql(Jie.class, "from Jie where  courseChapter.CName='"+this.zmc+"' and courseChapter.TCName='"+this.kcmc+"'");
		for(int i=0;i<list.size();i++)
		{
			Jdto j=new Jdto();
			j.setId(i+1);
			j.setValue(list.get(i).getZmc());
			this.jdto.add(i,j);
			
		}
		return SUCCESS;
	}
	
	
	@Action(value="/getrole",results={@Result(name="success",type="json",params={"includeProperties","id"})})
	public  String getRole()
	{
		Userinfo userinfo=this.baseservice.find(Userinfo.class, this.username);
		if(userinfo.getType().equals("S"))
		{
			id=0;
		}
		else
		{
			id=1;
		}
		return SUCCESS;
	}
	
	@Action(value="/getsjbh",results={@Result(name="success",type="json",params={"includeProperties","sjbhdto.*"})})
	public String getSjbh()
	{
		List<CourseChapter>  list=this.baseservice.findHql(CourseChapter.class, "from  CourseChapter  where CName='"+zmc+"' and TCName='"+kcmc+"'");
	    List<Sjnr>  list1=this.baseservice.findHql(Sjnr.class, "from Sjnr where courseChapter.zbh="+list.get(0).getZbh());
		for(int i=0;i<list1.size();i++)
		{
			Sjbhdto dto=new Sjbhdto();
			dto.setId(i+1);
			dto.setValue(list1.get(i).getZxx()+"");
			dto.setSjbh(list1.get(i).getSjno());
			this.sjbhdto.add(i, dto);
		} 
		return SUCCESS;
	}
	
	
	@Action(value="/getsjbhcs",results={@Result(name="success",type="json",params={"includeProperties","sjbhdto.*"})})
	public String getSjbhcs() throws UnsupportedEncodingException
	{
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid=(String) hs.getAttribute("uid");
		zmc=new String(zmc.getBytes("ISO-8859-1"),"utf-8");
		kcmc=new String(kcmc.getBytes("ISO-8859-1"),"utf-8");
		List<CourseChapter>  list=this.baseservice.findHql(CourseChapter.class, "from  CourseChapter  where CName='"+zmc+"' and TCName='"+kcmc+"'");
	    List<Sjnr>  list1=this.baseservice.findHql(Sjnr.class, "from Sjnr where courseChapter.zbh="+list.get(0).getZbh());
	    List<Xsdyjl>  xsdyjl=this.baseservice.findHql(Xsdyjl.class, "from Xsdyjl where UserId='"+userid+"'");
	    List<Integer> sjbh=new ArrayList<Integer>();
	    if(xsdyjl.size()>0){
	    	 for(int j=0;j<xsdyjl.size();j++){
	 	    	sjbh.add(xsdyjl.get(j).getId().getSjno());
	 	    }
	    }
	   
	    for(int i=0;i<list1.size();i++)
			{if(sjbh.size()>0){
				if(!sjbh.contains(list1.get(i).getSjno())){
		    		Sjbhdto dto=new Sjbhdto();
		    		dto.setId(i+1);
					dto.setValue(list1.get(i).getZxx()+"");
					dto.setSjbh(list1.get(i).getSjno());
					this.sjbhdto.add(dto);
		    	}
			}else{
				Sjbhdto dto=new Sjbhdto();
				dto.setId(i+1);
				dto.setValue(list1.get(i).getZxx()+"");
				dto.setSjbh(list1.get(i).getSjno());
				this.sjbhdto.add(dto);
			}
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
     textStr=textStr.replace("&nbsp;","");
      return textStr;
       }   
	
	
	
	
	
	/**
	 * 张涛
	 */
	
	/**
	 * @{cxczt}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{调出用户查询的操作题}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/cxczt",results={@Result(name="success",type="json",params={"includeProperties","rows.*,total"})})
	public String cxczt()
	{ 
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		QueryWord = (String) session1.getAttribute("querywordzy");
		Radio = Integer.parseInt((String) session1.getAttribute("radiozy"));
		String qw = "%"+QueryWord+"%";
		List<String> ll = new ArrayList<String>();
		int a = (page-1)*rows_s;
		ll = this.adminservice.cztcx(Radio, qw, a, rows_s);
		if(ll.size()>1){
			for(int i=0;i<ll.size()-1;i++){
				String s = ll.get(i);
				String ss[] = new String[4];
				ss = s.split(",");
				
				String tg = ss[1];
		    	tg=this.Html2Text(tg);
		    	CztdDto dto=new CztdDto();
		    	dto.setFenzhi(Integer.parseInt(ss[2]));
		    	dto.setJytigan(tg.substring(0, 15)+".......");
		    	dto.setTihao(Integer.parseInt(ss[0]));
		    	rows.add(dto);
			}
			total = Integer.parseInt(ll.get(ll.size()-1));
		}
		return "success";
	    
	}
	
	/**
	 * 该方法是用来检查已收藏的却被删除的操作题
	 */
	@Action(value="/jcczt",results={@Result(name="success",type="json",params={"includeProperties","message"})})
	public String jcczt(){
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		message = "";
		List<Zysc> list = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=13 and z.id.userId='"+userId+"'");
		if(list.size()>0){
			for(Zysc z:list){
				message += z.getZybh();
				this.baseservice.delete(z);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @{getsc}
	 * @param {引入参数名} {引入参数说明}
	 * @return {返回参数名} {返回参数说明}
	 * @{调出用户收藏的操作题}
	 * @exception {说明在某情况下,将发生什么异常}
	 */
	@Action(value="/getsc",results={@Result(name="success",type="json",params={"includeProperties","rows.*,total"})})
	public String getsc()
	{ 
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		userId = (String) session1.getAttribute("userid");
		List<Zysc> list = this.baseservice.findSql(Zysc.class, "from Zysc z where z.zylx=3 and z.id.userId='"+userId+"'",page, rows_s);//分页查询
		for(int i=0;i<list.size();i++){
			List<Cztd> lxz = new ArrayList<Cztd>();
			lxz = this.baseservice.findHql(Cztd.class, "from Cztd c where c.th='"+list.get(i).getZybh()+"'");
			if(lxz.size()!=0){
				rows1.add(lxz.get(0));
			}
		}
		List<Zysc> list1 = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=1 and z.id.userId='"+userId+"'");
		total = list1.size();
//	    total=this.baseservice.getTotal("Cztd");
//	    rows1= this.baseservice.findAll(Cztd.class,"Cztd", page, rows_s);
	    for(Cztd cztd:rows1)
	    {
	    	String tg=cztd.getTg();
	    	tg=this.Html2Text(tg);
	    	CztdDto dto=new CztdDto();
	    	dto.setFenzhi(cztd.getDtfz());
	    	dto.setJytigan(tg.substring(0, 15)+".......");
	    	dto.setTihao(cztd.getTh());
	    	rows.add(dto);
	    }
		return "success";
	}
}
