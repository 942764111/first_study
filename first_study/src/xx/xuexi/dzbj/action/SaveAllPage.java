/*

 *@(#)xx.xuexi.dzbj
 *@SaveAllPage.java.java  
 *@创建时间:2011-11-4下午07:55:25
 *@作者：guangge
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.xuexi.dzbj.action;



import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.collection.bean.Dzbj;
import xx.collection.bean.DzbjId;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @SaveAllPage <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
public class SaveAllPage extends ActionSupport {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="baseService")
	private BaseService baseService;
	
	
	
	
	
	
	/**
	 * @return the baseService
	 */
	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}


	/**
	 * @param baseService the baseService to set
	 */
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}




	
	/**
	 * @return the zlmc
	 */
	@JSON(serialize=false)
	public String getZlmc() {
		return zlmc;
	}


	/**
	 * @param zlmc the zlmc to set
	 */
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}





	private String zlmc;
	private String currentPage;
	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}


	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}



	private int x1;
	/**
	 * @return the x1
	 */
	public int getX1() {
		return x1;
	}


	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}


	/**
	 * @return the y1
	 */
	public int getY1() {
		return y1;
	}


	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}


	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}


	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}


	/**
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}


	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}



	private int y1;
	private int x2;
	private int y2;
	private String color;
	private String filename; 
	private String contentType; 
	private int tmbh;
	private int num;
	private int xs;
	private String notenote;
	private String classno;
	private String neirong;
	


	/**
	 * @return the neirong
	 */
	public String getNeirong() {
		return neirong;
	}


	/**
	 * @param neirong the neirong to set
	 */
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}


	/**
	 * @return the classno
	 */
	public String getClassno() {
		return classno;
	}


	/**
	 * @param classno the classno to set
	 */
	public void setClassno(String classno) {
		this.classno = classno;
	}


	/**
	 * @return the notenote
	 */
	public String getNotenote() {
		return notenote;
	}


	/**
	 * @param notenote the notenote to set
	 */
	public void setNotenote(String notenote) {
		this.notenote = notenote;
	}


	/**
	 * @return the xs
	 */
	public int getXs() {
		return xs;
	}


	/**
	 * @param xs the xs to set
	 */
	public void setXs(int xs) {
		this.xs = xs;
	}


	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}


	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}


	/**
	 * @return the tmbh
	 */
	public int getTmbh() {
		return tmbh;
	}


	/**
	 * @param tmbh the tmbh to set
	 */
	public void setTmbh(int tmbh) {
		this.tmbh = tmbh;
	}


	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}


	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}


	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	

	/**
	 * @return the filename
	 */
	@JSON(serialize=false)
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
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
	


	@Action(value="/save1aa",results={@Result(name="success",type="json")})
	public String execute(){
		MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();
		// Bind allowed Files
		Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
		String inputName = (String) fileParameterNames.nextElement();
		//String[] contentType1 = multiWrapper.getContentTypes(inputName);
		//String[] fileName = multiWrapper.getFileNames(inputName);
		File[] files = multiWrapper.getFiles(inputName);
		
		String nowTimeStr = "";
		InputStream is = null;
		SimpleDateFormat sDateFormat; 
		String path;
		String root = ServletActionContext.getRequest().getRealPath("/upload/dzbj");
//		
//		String[] keys0 = new String[1];
//		keys0[0] = "zlmc";
//		Object[] values0 = new Object[1];
//		values0[0] = zlmc;
//		List<Integer> zlbh = this.baseService.find(Integer.class, "Dmtzl", "zlbh", keys0, values0);
//		String zlid = zlbh.get(0).toString();
		
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid1 = (String)hs.getAttribute("uid");
		String classno1 = (String)hs.getAttribute("classno");
		try {
			if (files != null) {
				sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
				sDateFormat.setLenient(false);
				
					nowTimeStr = sDateFormat.format(new Date());
					Random r = new Random();
					int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;
					
					filename = nowTimeStr + rannum + ".jpg";
					path = filename;
					is = new FileInputStream(files[0]);
					BufferedImage bufferedImage = ImageIO.read(is);  
					ImageIO.write(bufferedImage, "jpeg", new File(root,filename));
					
					FileInputStream fin = new FileInputStream( root + "/" + filename);
					
					byte[] bt = InputStreamToByte(fin);
					DzbjId dzbjid=new DzbjId();
					dzbjid.setUserId(userid1);
					dzbjid.setClassno(classno1);
					dzbjid.setTmbh(tmbh);
					Dzbj dzbj=new Dzbj();
					
					dzbj.setId(dzbjid);
					dzbj.setBiaojilx("juxing");
					dzbj.setZuobiaoX(x1);
					dzbj.setZuobiaoY(y1);
					dzbj.setZuobiaoX1(x2);
					dzbj.setZuobiaoY1(y2);
					dzbj.setBiaojiys(color);
					dzbj.setWeizhi(currentPage);
					dzbj.setXssxh(tmbh*20);
					dzbj.setTmnr(neirong);
					dzbj.setTmimage(bt);
					dzbj.setPath(path);
					dzbj.setZlid(zlmc);
					this.baseService.save(dzbj);
					
				
			}  
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	
		
		
		
		
		
		return "success";
	}
	@Action(value="/showshow",results={@Result(name="success",type="json")})
	public String show(){
		

		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid1 = (String)hs.getAttribute("uid");
		
		
		MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();
		// Bind allowed Files
		Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
		String inputName = (String) fileParameterNames.nextElement();
		//String[] contentType1 = multiWrapper.getContentTypes(inputName);
		//String[] fileName = multiWrapper.getFileNames(inputName);
		File[] files = multiWrapper.getFiles(inputName);
		
		String nowTimeStr = "";
		InputStream is = null;
		SimpleDateFormat sDateFormat; 
		String path;
		String root = ServletActionContext.getRequest().getRealPath("/upload/dzbj");
		try {
			if (files != null) {
				sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
				sDateFormat.setLenient(false);
				
					nowTimeStr = sDateFormat.format(new Date());
					Random r = new Random();
					int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;
					
					filename = nowTimeStr + rannum + ".jpg";
					path = filename;
					is = new FileInputStream(files[0]);
					BufferedImage bufferedImage = ImageIO.read(is);  
					ImageIO.write(bufferedImage, "jpeg", new File(root,filename));
					
					FileInputStream fin = new FileInputStream( root + "/" + filename);
					
					byte[] bt = InputStreamToByte(fin);
					String hql2 ="select max(dz.id.tmbh) from Dzbj dz";
					List l=this.baseService.findHql(Dzbj.class,hql2);
					//l.get(0);
					//System.out.println("hhhhhhhhhhhhhhhh");
					num=Integer.parseInt(l.get(0)+"");
					DzbjId dzbjid=new DzbjId();
					dzbjid.setUserId(userid1);
					dzbjid.setClassno(classno);
					dzbjid.setTmbh(num+1);
					Dzbj dzbj=new Dzbj();
					dzbj.setId(dzbjid);
					

					dzbj.setXssxh(xs+1);
					dzbj.setTmnr(notenote);
					dzbj.setTmimage(bt);
					dzbj.setPath(path);
					this.baseService.save(dzbj);
					
				
			}  
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	
		
		
		
		
		
		return "success";
	}
	
	@Action(value="/showshow1",results={@Result(name="success",type="json")})
	public String show1(){
		

		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid1 = (String)hs.getAttribute("uid");
		
		
		MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();
		// Bind allowed Files
		Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
		String inputName = (String) fileParameterNames.nextElement();
		//String[] contentType1 = multiWrapper.getContentTypes(inputName);
		//String[] fileName = multiWrapper.getFileNames(inputName);
		File[] files = multiWrapper.getFiles(inputName);
		
		String nowTimeStr = "";
		InputStream is = null;
		SimpleDateFormat sDateFormat; 
		String path;
		String root = ServletActionContext.getRequest().getRealPath("/upload/dzbj");
		try {
			if (files != null) {
				sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
				sDateFormat.setLenient(false);
				
					nowTimeStr = sDateFormat.format(new Date());
					Random r = new Random();
					int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;
					
					filename = nowTimeStr + rannum + ".jpg";
					path = filename;
					is = new FileInputStream(files[0]);
					BufferedImage bufferedImage = ImageIO.read(is);  
					ImageIO.write(bufferedImage, "jpeg", new File(root,filename));
					
					FileInputStream fin = new FileInputStream( root + "/" + filename);
					
					byte[] bt = InputStreamToByte(fin);
					String hql2 ="select max(dz.id.tmbh) from Dzbj dz";
					List l=this.baseService.findHql(Dzbj.class,hql2);
					//l.get(0);
					//System.out.println("hhhhhhhhhhhhhhhh");
					num=Integer.parseInt(l.get(0)+"");
					DzbjId dzbjid=new DzbjId();
					dzbjid.setUserId(userid1);
					String classno1 = (String)hs.getAttribute("classno");
					dzbjid.setClassno(classno1);
					dzbjid.setTmbh(num+1);
					Dzbj dzbj=new Dzbj();
					dzbj.setId(dzbjid);
					

					dzbj.setXssxh(xs+1);
					dzbj.setTmnr(notenote);
					dzbj.setTmimage(bt);
					dzbj.setPath(path);
					this.baseService.save(dzbj);
					
				
			}  
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	
		
		
		
		
		
		return "success";
	}
	
	
}
