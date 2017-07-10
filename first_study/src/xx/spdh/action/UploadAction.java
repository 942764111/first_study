/*
 *@(#)xx.spdh.action
 *@UploadAction.java.java  
 *@创建时间:2011-11-12上午08:43:29
 *@作者：ZYK
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.spdh.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Decoder;
import xx.collection.bean.Dzbj;
import xx.collection.bean.DzbjId;
import xx.collection.bean.MapNode;
import xx.collection.bean.MindMap;
import xx.collection.bean.MindMapDetail;
import xx.collection.bean.Scwj;
import xx.collection.bean.Zidingyibijimulu;
import xx.collection.bean.ZidingyibijimuluId;
import xx.collection.bean.Zsd2;
import xx.quanxian.interceptor.inter;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @UploadAction <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */
@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class UploadAction extends ActionSupport{

	@Resource(name="baseService")
	private BaseService baseservice;

	private String filename;
	private Map<String, Object> map;
	private String zlmc;
	private int tmbh;
	private String tmnr;
	private String weizhi;
	private String zlid;
	private String classno;
	private String neirong;
	private String state;
	private String zsdid;
	private String file;
	private String zsdmc;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zsdmc
	 */
	public String getZsdmc() {
		return zsdmc;
	}

	/**
	 * @param zsdmc the zsdmc to set
	 */
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}

	/**
	 * @return the zsdid
	 */
	public String getZsdid() {
		return zsdid;
	}

	/**
	 * @param zsdid the zsdid to set
	 */
	public void setZsdid(String zsdid) {
		this.zsdid = zsdid;
	}

	/**
	 * @return the baseservice
	 */
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}

	/**
	 * @param baseservice the baseservice to set
	 */
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
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

	/**
	 * @return the tmbh
	 */
	@JSON(serialize=false)
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
	 * @return the tmnr
	 */
	@JSON(serialize=false)
	public String getTmnr() {
		return tmnr;
	}

	/**
	 * @param tmnr the tmnr to set
	 */
	public void setTmnr(String tmnr) {
		this.tmnr = tmnr;
	}

	/**
	 * @return the weizhi
	 */
	@JSON(serialize=false)
	public String getWeizhi() {
		return weizhi;
	}


	/**
	 * @param weizhi the weizhi to set
	 */
	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

	/**
	 * @return the zlid
	 */
	@JSON(serialize=false)
	public String getZlid() {
		return zlid;
	}

	/**
	 * @param zlid the zlid to set
	 */
	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	/**
	 * @return the neirong
	 */
	@JSON(serialize=false)
	public String getNeirong() {
		return neirong;
	}
    private String kcmc;
	/**
	 * @param neirong the neirong to set
	 */
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}

	//将输入流转换成字节流1s
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

	//保存笔记（sun）
	@Action(value="/savebiji",results={@Result(name="success",type="json")})
	public String savabiji() throws IOException{


		String nowTimeStr = "";
		SimpleDateFormat sDateFormat; 
		String path;
		HttpSession hs = ServletActionContext.getRequest().getSession();	
		String userId = (String)hs.getAttribute("uid");
		//String classno = (String)hs.getAttribute("classno");
		sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
		sDateFormat.setLenient(false);

		nowTimeStr = sDateFormat.format(new Date());
		Random r = new Random();
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;
		filename = nowTimeStr + rannum + ".jpg";
		path =userId+"/"+filename;

		String root = ServletActionContext.getRequest().getRealPath("/upload/dzbj/"+userId);
		mkDirectory(root);
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(file);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath =root+"/"+filename;// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
		}catch (Exception e) {
			state="-1";
			return SUCCESS;
		}

		String userid = (String)hs.getAttribute("uid");
		String[] keys={"id.userId","zlid"};
		Object[] values={userid,zlid};
		List<Dzbj>classno1=this.baseservice.find(Dzbj.class, "Dzbj", keys, values);
    
		//判断是否有该笔记的目录
		if (classno1==null||classno1.size()==0) {
			//没有笔记目录，创建，判断是一级还是二级。
			
			System.out.println("classno:"+classno);
			if (classno.contains(",")) {
				//二级目录
				String[] keys2={"zlbh"};

				Object[] values2={zlid};
				String name=this.baseservice.find(Scwj.class, "Scwj", keys2, values2).get(0).getFilename();
                kcmc=this.baseservice.find(Scwj.class, "Scwj", keys2, values2).get(0).getKcmc();
				String[] keys3={"userinfo.userId","classname"};

				Object[] values3={userid,name};
				//判断电子笔记目录是否存在
				List<Zidingyibijimulu> zList=this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys3, values3);
				System.out.println("zlist:"+zList+"  "+zList.size());
				if (zList==null||zList.size()==0) {
					String hql = "select id.classno from Zidingyibijimulu where userId='" + userid + "' and substr(classno,1,7)='" + classno + "' and classno like '___________'";
					List<String> classno2 = this.baseservice.findHql(String.class, hql);
					int total = 0;
					for(int i=0;i<classno2.size();i++) {
						total++;
					}
					int total1 = total + 1;
					String three_classno="";
					if(total<10) {
						three_classno = classno + "," +"00" + total1;
					} else if(total>10&&total<100) {
						three_classno = classno + "," +"0" + total1;
					} else if(total>=100&&total<1000) {
						three_classno = classno + "," + String.valueOf(total);
					}


					ZidingyibijimuluId zdybjmlId = new ZidingyibijimuluId();
					zdybjmlId.setUserId(userid);
					zdybjmlId.setClassno(three_classno);
					Zidingyibijimulu zdybjml = new Zidingyibijimulu();
					zdybjml.setId(zdybjmlId);
					zdybjml.setZlid(zlid);
					zdybjml.setClassname(name);
					zdybjml.setBeizhu("");
					classno=three_classno;
					this.baseservice.save(zdybjml);
				}else {
					classno=zList.get(0).getId().getClassno();
					System.out.println("目录已存在:"+classno);
				}
			}else {
				//一级目录底下添加资料

				String[] keys7={"zlbh"};

				Object[] values7={zlid};
				String name=this.baseservice.find(Scwj.class, "Scwj", keys7, values7).get(0).getFilename();

				String[] keys3={"userinfo.userId","classname"};

				Object[] values3={userid,name};
				//判断电子笔记目录是否存在
				List<Zidingyibijimulu> zList=this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys3, values3);
				if (zList==null||zList.size()==0) {
					String s1 = classno;
					String hql = "select id.classno from Zidingyibijimulu where userId='" + userid + "'and classno like '_______'";
					List<String> classno2 = this.baseservice.findHql(String.class, hql);
					int total = 0;
					for(int i=0;i<classno2.size();i++) {
						String s2 = new String();
						s2 = classno2.get(i).substring(0, 3);
						if(s2.equals(s1)) {
							total++;
						}
					}
					int total1 = total + 1;
					String two_classno="";
					if(total<10) {
						two_classno = classno2.get(0) + "," +"00" + total1;
					} else if(total>10&&total<100) {
						two_classno = classno2.get(0) + "," +"0" + total1;
					} else if(total>=100&&total<1000) {
						two_classno = classno2.get(0) + "," + String.valueOf(total);
					}

					ZidingyibijimuluId zdybjmlId = new ZidingyibijimuluId();
					zdybjmlId.setUserId(userid);
					zdybjmlId.setClassno(two_classno);
					Zidingyibijimulu zdybjml = new Zidingyibijimulu();
					zdybjml.setId(zdybjmlId);
					zdybjml.setZlid(zlid);
					zdybjml.setClassname(name);
					zdybjml.setBeizhu("");
					this.baseservice.save(zdybjml);
					classno=two_classno;
					return SUCCESS;
				} else {
					classno=zList.get(0).getId().getClassno();
					System.out.println("目录已存在:"+classno);
				}

			}
		} 

		DzbjId dzbjId = new DzbjId();
		dzbjId.setUserId(userId);
		dzbjId.setClassno(classno);
		//String nowTime=String.valueOf(System.currentTimeMillis()).substring(3).trim();	

		int tmbh=this.baseservice.findMax("tmbh","Dzbj");
		System.out.println("tmbh:"+tmbh);
		dzbjId.setTmbh(tmbh+1);
		Dzbj dzbj = new Dzbj();
		dzbj.setId(dzbjId);
		dzbj.setTmnr(neirong);//笔记内容
		dzbj.setZlid(zlid);
		dzbj.setWeizhi(weizhi);
		dzbj.setPath(path);
		dzbj.setZsdid(zsdid);
		
		
		this.baseservice.save(dzbj);
		

		
		String[] keys8={"zlbh"};

		Object[] values8={zlid};
		
        kcmc=this.baseservice.find(Scwj.class, "Scwj", keys8, values8).get(0).getKcmc();
		//知识点和笔记相关联
		List<MapNode> mapNodes=this.baseservice.find(MapNode.class);
		
		int size=(zsdid.length()-1)/4;
	
		if (mapNodes==null||mapNodes.size()<=0) {

			for (int i = 0; i < size; i++) {
				System.out.println("i："+i);
				String str4=getMap().get(String.valueOf(i+1));
				String nodeid=str4+zsdid.substring(1,4*(i+1)+1);
				MapNode mapNode=new MapNode();
				mapNode.setNodeid(nodeid);
				
				System.out.println("name:"+nodeid);
				String[] keys10={"zsdid"};
				Object[] values10={nodeid};
				String nodename=this.baseservice.find(Zsd2.class, "Zsd2", keys10, values10).get(0).getZsdmc();
				mapNode.setNodename(nodename);
				mapNode.setType(kcmc);
				mapNode.setUserid(userid);

				if (nodeid.length()==5) {
					mapNode.setParentid("");
				}else {
					String str=getMap().get(String.valueOf(i));
					String parentid=str+zsdid.substring(1,4*i+1);
					mapNode.setParentid(parentid);
				}
				this.baseservice.save(mapNode);
			}

		}else {
			for (int i = size; i > 0; i--) {
				String str4=getMap().get(String.valueOf(i));
				String nodeid=str4+zsdid.substring(1, 4*i+1);
				
				String[] keys13={"nodeid","userid"};
				Object[] values13={nodeid,userid};
				List<MapNode> mapNode=this.baseservice.find(MapNode.class, "MapNode", keys13, values13);

				if (mapNode==null||mapNode.size()<=0) {
                MapNode mapNode2=new MapNode();
                mapNode2.setNodeid(nodeid);
  
                System.out.println("nodeid:"+nodeid);
                String[] keys10={"zsdid"};
				Object[] values10={nodeid};
				String nodename=this.baseservice.find(Zsd2.class, "Zsd2", keys10, values10).get(0).getZsdmc();
                mapNode2.setNodename(nodename);
                if (nodeid.length()==5) {
					mapNode2.setParentid("");
				}else {
					
					String str=getMap().get(String.valueOf(i-1));
					String parentid=str+zsdid.substring(1,4*(i-1)+1);
					mapNode2.setParentid(parentid);
				}
                mapNode2.setType(kcmc);
                mapNode2.setUserid(userid);
                this.baseservice.save(mapNode2);
				} else {
					break;
				}
			}

		}
	
		
//		String[] keys1={"nodeid","userid","mapid"};
//		Object[] values1={zsdid,userid,"91001"};
//		List<MindMapDetail> list=this.baseservice.find(MindMapDetail.class, "MindMapDetail", keys1, values1);
//		if (list==null||list.size()<=0) {
//			MindMapDetail mapDetail=new MindMapDetail();
//			mapDetail.setUserid(userid);
//			mapDetail.setImgurl("/upload/dzbj/"+path);
//			mapDetail.setGaishu(neirong);
//	        mapDetail.setType("4");
//			mapDetail.setNodeid(zsdid);
//			mapDetail.setMapid("91001");//课程id
//	        this.baseservice.save(mapDetail);
//		}  else {
//			MindMapDetail mapDetail=list.get(0);
//			String imgurl=mapDetail.getImgurl()+",/upload/dzbj/"+path;
//			String gaishu=mapDetail.getGaishu()+"@_@"+neirong;
//			mapDetail.setImgurl(imgurl);
//			mapDetail.setGaishu(gaishu);
//			
//			this.baseservice.update(mapDetail);
//		}     
//		

		state="0";
		String[] keys9={"id.userId","id.classno"};
		Object[] values9={userId,classno};

		Zidingyibijimulu z=this.baseservice.find(Zidingyibijimulu.class, "Zidingyibijimulu", keys9, values9).get(0);
		z.setZlid(zlid);
		this.baseservice.update(z);
		return SUCCESS;
	}

	public static Map<String, String> getMap(){
		
		 
		Map<String, String> map=new HashMap<String, String>();
		map.put("1", "9");
		map.put("2", "b");
		map.put("3", "c");
		map.put("4", "d");
		map.put("5", "e");
		map.put("6", "f");
		map.put("7", "g");
		map.put("8", "h");
		map.put("9", "i");
		map.put("10", "j");
		map.put("11", "k");
		map.put("12", "l");
		map.put("13", "m");
		map.put("14", "n");
		map.put("15", "o");
		return map;
		
	}

	//		MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();
	//		// Bind allowed Files
	//		Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
	//		String inputName = (String) fileParameterNames.nextElement();
	//		//String[] contentType1 = multiWrapper.getContentTypes(inputName);
	//		//String[] fileName = multiWrapper.getFileNames(inputName);
	//		File[] files = multiWrapper.getFiles(inputName);
	//		
	//		String nowTimeStr = "";
	//		InputStream is = null;
	//		SimpleDateFormat sDateFormat; 
	//		String path;
	//		

	//		
	//		String root = ServletActionContext.getRequest().getRealPath("/upload/dzbj");
	//		try {
	//			if (files != null) {
	//				sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
	//				sDateFormat.setLenient(false);
	//				for(int i=0;i<files.length;i++) {
	//					nowTimeStr = sDateFormat.format(new Date());
	//					Random r = new Random();
	//					int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;
	//					
	//					filename = nowTimeStr + rannum + ".jpg";
	//					path = filename;
	//					
	//					is = new FileInputStream(files[i]);
	//					BufferedImage bufferedImage = ImageIO.read(is);  
	//					
	//					mkDirectory(root);
	//					ImageIO.write(bufferedImage, "jpeg", new File(root,filename));
	//					
	//					FileInputStream fin = new FileInputStream( root + "/" + filename);
	//					
	//					byte[] bt = InputStreamToByte(fin);
	//					
	//					DzbjId dzbjId = new DzbjId();
	//					dzbjId.setUserId(userId);
	//					dzbjId.setClassno(classno);
	//					String nowTime=String.valueOf(System.currentTimeMillis()).substring(3);	
	//					dzbjId.setTmbh(Integer.parseInt(nowTime));
	//					Dzbj dzbj = new Dzbj();
	//					dzbj.setId(dzbjId);
	//					dzbj.setTmnr(neirong);//笔记内容
	//					dzbj.setTmimage(bt);
	//					dzbj.setZlid(zlid);
	//					dzbj.setWeizhi(weizhi);
	//					dzbj.setPath(path);
	//					this.baseservice.save(dzbj);
	//					state="0";
	//				}
	//			}  
	//			
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//			e.printStackTrace();
	//			state="-1";
	//		}

	//		return SUCCESS;



	@Action(value="/uploadJieTu1",results={@Result(name="success",type="json")})
	public String uploadJieTu1(){

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

		HttpSession hs = ServletActionContext.getRequest().getSession();	
		String userId = (String)hs.getAttribute("uid");
		//String classno = (String)hs.getAttribute("classno");

		String root = ServletActionContext.getRequest().getRealPath("/upload/dzbj");
		try {
			if (files != null) {
				sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
				sDateFormat.setLenient(false);
				for(int i=0;i<files.length;i++) {
					nowTimeStr = sDateFormat.format(new Date());
					Random r = new Random();
					int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;

					filename = nowTimeStr + rannum + ".jpg";
					path = filename;

					is = new FileInputStream(files[i]);
					BufferedImage bufferedImage = ImageIO.read(is);  

					mkDirectory(root);
					ImageIO.write(bufferedImage, "jpeg", new File(root,filename));

					FileInputStream fin = new FileInputStream( root + "/" + filename);

					byte[] bt = InputStreamToByte(fin);

					DzbjId dzbjId = new DzbjId();
					dzbjId.setUserId(userId);
					dzbjId.setClassno(classno);

					dzbjId.setTmbh(tmbh);
					Dzbj dzbj = new Dzbj();
					dzbj.setId(dzbjId);

					dzbj.setXssxh(tmbh*20);

					dzbj.setTmnr(tmnr);
					dzbj.setTmimage(bt);
					dzbj.setZlid(zlid);
					dzbj.setWeizhi(weizhi);
					dzbj.setPath(path);
					this.baseservice.save(dzbj);
				}
			}  

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return SUCCESS;
	}

	@Action(value="/updateNote",results={@Result(name="success",type="json")})
	public String updateNote() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		String[] keys1=new String[3];
		keys1[0]="userId";	
		keys1[1] = "zlid";
		keys1[2] = "tmbh";
		Object[] values1=new Object[3];
		values1[0]= userid;
		values1[1] = zlid;
		values1[2] = tmbh;

		List<Dzbj> dzbjlist = this.baseservice.find(Dzbj.class, "Dzbj", keys1, values1);
		Dzbj dzbj1 = dzbjlist.get(0);
		dzbj1.setTmnr(neirong);
		try {
			this.baseservice.update(dzbj1);
			state="0";
		} catch (Exception e) {
			// TODO: handle exception
			state="-1";
		}

		return SUCCESS;
	}

	/** 
	 * 删除单个文件 
	 * @param   fPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(String fPath) {  
		boolean flag = false;  
		File file = new File(fPath);  
		System.out.println(file);
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {  
			file.delete();  
			flag = true;  
		}  
		return flag;  
	}

	@Action(value="/deleteNote",results={@Result(name="success",type="json")})
	public String deleteNote() {
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
       
		System.out.println(zlid + tmbh);
		String[] keys1=new String[3];
		keys1[0]="userId";	
		keys1[1] = "zlid";
		keys1[2] = "tmbh";
		Object[] values1=new Object[3];
		values1[0]= userid;
		values1[1] = zlid;
		values1[2] = tmbh;

		List<Dzbj> dzbjlist = this.baseservice.find(Dzbj.class, "Dzbj", keys1, values1);
		Dzbj dzbj1 = dzbjlist.get(0);

		List<String> path = this.baseservice.find(String.class, "Dzbj", "path", keys1, values1);
		String fp = path.get(0);
		String root = ServletActionContext.getRequest().getRealPath("/upload/dzbj");
		String fPath = root + "\\" + fp;
		deleteFile(fPath);

		try {
			
			this.baseservice.delete(dzbj1);
			state="0";
		} catch (Exception e) {
			// TODO: handle exception
			state="1";
		}
	
		return SUCCESS;
	}

	
	public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				return file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
		return false;
	}
}
