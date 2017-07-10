package xx.spdh.action;  

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.CourseChapter;
import xx.collection.bean.Dmtzl;
import xx.collection.bean.Jie;
import xx.collection.bean.JxjhYck;
import xx.collection.bean.Jxnr;
import xx.collection.bean.MapNode;
import xx.collection.bean.Scwj;
import xx.collection.bean.Userinfo;
import xx.collection.bean.Wjlx;
import xx.collection.bean.Zlzsddy;
import xx.collection.bean.ZlzsddyId;
import xx.collection.bean.Zsd;
import xx.collection.bean.Zsd2;
import xx.md5.module.MD5Builder;
import xx.page.module.String2File;
import xx.quanxian.service.BaseService;

import com.easysearching.lucene.beans.FileBean;
import com.easysearching.lucene.dao.IndexDao;
import com.easysearching.lucene.factory.RamDirectoryFactory;
import com.easysearching.lucene.factory.RamIndexDaoFactory;
import com.easysearching.lucene.util.IndexUtil;
import com.easysearching.util.TimeUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;

/** 
 * @author fish 
 */  
public class UpdatescwjAction extends ActionSupport 
{  

	private String rows;
	private String state;
    
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Resource(name="baseService")
	private BaseService baseservice;
	
	@Resource(name="string2File")
	private String2File string2File;
	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

	@SuppressWarnings("static-access")
	@Action(value="updatescwj",results={@Result(name="success",type="json")})
	public String updatescwj() throws Exception {
		state="1";
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");//用户id
		System.out.println("userid:"+userid);
		String fileName="";
		String lxm="";
		String zlmc="";
		String zlms="";
		String zsdmc="";
		String filePath="";
		String tip="";
		String mc="";
		String zmc="";
		String CName="";
		String KechengName="";
		String ZhangName="";
		String JieName="";
		String zsdid="";
		Date scrq;
		int zlbh=0;
		rows=this.getRows();
     
		rows=new String(rows.getBytes("ISO-8859-1"),"utf-8");

		net.sf.json.JSONObject jObject=net.sf.json.JSONObject.fromObject(rows);
		System.out.println("jObject："+jObject);
		net.sf.json.JSONArray jsonArray = jObject.getJSONArray("rows");

		System.out.println("jsonArry:"+jsonArray);
		int iSize = jsonArray.size();

		for (int i = 0; i < iSize; i++) {
			JSONObject jsonObj = (JSONObject) jsonArray.get(i);

			String md5;
			zlmc=jsonObj.getString("fileName");//资料名称
			System.out.println("fileName:" + fileName);
			md5=zlmc;

			MD5Builder md5b = new MD5Builder();
			try {
				String zlmd5 = md5b.EncoderByMd5(md5);
				Userinfo userinfo = new Userinfo();
				userinfo.setUserId(userid);

				lxm=jsonObj.getString("lxm");//文件类型
				System.out.println("lxm:"+lxm);
				String hql2 = "from Wjlx where kzm like '%"+lxm+"%'";
				List<Wjlx> wjlxlist = this.baseservice.findHql(Wjlx.class, hql2);
				String fileType = wjlxlist.get(0).getLxm();

				Wjlx wjlx = new Wjlx();
				wjlx.setLxm(fileType);

				Dmtzl dmtzl = new Dmtzl();
				dmtzl.setUserinfo(userinfo);
				dmtzl.setWjlx(wjlx);
				dmtzl.setZlmc(zlmc);

				fileName=jsonObj.getString("fileName");
				dmtzl.setFilename(fileName);
				System.out.println("fileName:"+fileName);
				zlms=jsonObj.getString("zlms");
				zlms=fileName.split("\\.")[0];
				dmtzl.setZlms(zlms);
				dmtzl.setZlly("");
				dmtzl.setZlscm("");

				String riqi=jsonObj.getString("scrq");//上传时间
				System.out.println("日期："+riqi);
				String riqi1=riqi.replace("T", "");
				System.out.println("日期1："+riqi1);
				SimpleDateFormat sDateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss"); 
				sDateFormat1.setLenient(false);

				scrq=sDateFormat1.parse(riqi1);
				System.out.println("scrq:" + scrq);
				dmtzl.setScrq(scrq);

				dmtzl.setZlmd5(zlmd5);
				int no1 = Integer.parseInt(jsonObj.getString("no"));//资料编号
				System.out.println("no1:" + no1);
				Scwj scwj = this.baseservice.find(Scwj.class, no1);


				dmtzl.setTotalNum(0);

				
				///CESHIHISHIS
				 
//				zsdmc="C语言基础";
//				zlms="C语言基础";
				this.baseservice.save(dmtzl);

				//更新scwj表
				String hql = "select max(zlbh) from Dmtzl where filename='" + fileName + "'";
				int j = this.baseservice.findHql(Integer.class, hql).get(0);//获取最大的资料编号
				System.out.println("j:"+j);
				Dmtzl dmt= new Dmtzl();
				dmt.setZlbh(j);
				scwj.setDmtzl(dmt);
				//zsdmc=jsonObj.getString("zsdmc");
				zsdmc="程序设计语言";
				scwj.setZsdmc(zsdmc);
				scwj.setZlms(zlms);
				zsdid=jsonObj.getString("zsdid");
				scwj.setFileusertype("1");//教师上传的
				scwj.setZsdid(zsdid);

				//对知识点进行操作

				

				filePath=jsonObj.getString("filePath");
				tip="jxnr";
				System.out.println("tip:"+tip);
				if(tip=="jxnr"||tip.equals("jxnr")){
					Jxnr jr = new Jxnr();
					jr.setWjms(zlms);
					jr.setWjmc(fileName);
					jr.setFilepath(filePath);
					JxjhYck jh = new JxjhYck();
					jh.setNo((Integer)hs.getAttribute("keci"));

					jr.setJxjhSz((Integer)hs.getAttribute("i_d")+"");
					jr.setZlid(j);
					jr.setJxjhYck(jh);
					this.baseservice.save(jr);
					zlbh=j;
					mc = zlmc;
					int kkch= (Integer)hs.getAttribute("kkch");
					CourseChapter cc=this.baseservice.find(CourseChapter.class, kkch);
					CName=cc.getCName();zmc=cc.getTCName();
					System.out.println("CName:"+CName+" zmc:"+zmc);
                    KechengName=zmc;
                    
                    scwj.setKcmc(KechengName);
                    this.baseservice.update(scwj);
                    ZhangName=CName;
					//进行下一步

					String zlbh1=String.valueOf(zlbh);
					ZlzsddyId id1 = new ZlzsddyId();
					if(zlbh1==null||zlbh1==""){
						//通过zlmc查找zlbh
						String[] keys1 = new String[1];
						keys1[0] = "zlmc";
						Object[] values1 = new Object[1];
						values1[0] = zlmc;
						List<Integer> zlbh2 = this.baseservice.find(Integer.class, "Dmtzl", "id.zlbh", keys1, values1);
						id1.setZlbh(zlbh2.get(0));
						System.out.println("资料编号："+zlbh2.get(0));
					}else{
						id1.setZlbh(Integer.valueOf(zlbh));
						System.out.println("资料编号2："+zlbh);
					}
//					
//					//通过CName查找zbh
					String[] keys2 = new String[1];
					keys2[0] = "CName";
					Object[] values2 = new Object[1];
					values2[0] = CName;
					List<Integer> zbh = this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys2, values2);
                    
					System.out.println("zbh:"+zbh);
//					//根据知识点名称获取zmc
//					String[] keys = {"zsdmc"};
//					Object[] values = {zsdmc};
//					List<Zsd> z = this.baseservice.find(Zsd.class, "Zsd", keys, values);
//					int zbh2 = z.get(0).getId().getZbh();
//					int kbh2 = z.get(0).getId().getCId();
//
//					String[] keys1 = {"zbh","c_id"};
//					Object[] values1 = {zbh2,kbh2};
//					Jie j2 = this.baseservice.find(Jie.class, "Jie", keys1, values1).get(0);
//					zmc = j2.getZmc();
//
//					JieName=zmc;
//					
//					//在Jie表中查节名称
//					String[] keys3 = new String[1];
//					keys3[0] = "zmc";//zmc表示节名称
//					Object[] values3 = new Object[1];
//					values3[0] = zmc;
//					List<Integer> CId = this.baseservice.find(Integer.class, "Jie", "id.CId", keys3, values3);
//
//					//通过zsdmc查找zsdbh
//					String[] keys4=new String[1];
//					keys4[0]="zsdmc";
//					Object[] values4=new Object[1];
//					values4[0]=zsdmc;
//					List<Integer> zsdbh=this.baseservice.find(Integer.class, "Zsd", "id.zsdbh", keys4, values4);
//
//
//					id1.setZbh(zbh.get(0));
//					id1.setCId(CId.get(0));
//					id1.setZsdbh(zsdbh.get(0));
//
//					Zlzsddy zlzsddy = new Zlzsddy();
//					//zlzsddy.setWeizhi(this.getWeizhi());
//					zlzsddy.setId(id1);
//                    System.out.println("测试："+id1);
//					this.baseservice.save(zlzsddy);


					//下面是创建文件索引

					Scwj scwj2=this.baseservice.find(Scwj.class, no1);
					String root=ServletActionContext.getServletContext().getRealPath("/upload/");
					String downLoadFilePath=root+"/"+scwj2.getOldfilepath();
					String viewFailPath=scwj2.getFilepath();
					String searchFailPath=root+"/"+scwj2.getSearchfilepath();
					if (fileType.equals("视频")||fileType.equals("动画")||fileType.equals("音频")) {
						File file=new File(searchFailPath);
						string2File.writeTxtFile(zlms, file);
					}
					if(lxm.equals(".zip")||lxm.equals(".rar")){
						File file=new File(searchFailPath);
						
						string2File.writeTxtFile(zlms, file);
						viewFailPath="0";
					}
					
								
					// 将文件创建到索引数据库
					IndexDao indexDao = RamIndexDaoFactory.getInstance();
					FileBean fileBean = new FileBean();
					String pubDate = TimeUtil.getCurrentDate();
					fileBean.setPubDate(pubDate);
					fileBean.setPubWriter(userid);
					fileBean.setViewPath(viewFailPath);

					fileBean.setI_id("0");
					fileBean.setKeci("0");
					fileBean.setZlid(String.valueOf(j));
					fileBean.setTCName("C语言程序设计");
					fileBean.setZName("0");
					fileBean.setJName("0");
					System.err.println("文件类型:"+fileType);
                    fileBean.setFileType(fileType);
					fileBean.setZsdmc(zsdmc);				
					File pubFile = new File(searchFailPath);
					fileBean.setPubFile(pubFile);
					
					File downLoadFile=new File(downLoadFilePath);
					fileBean.setDownLoadFile(downLoadFile);
					indexDao.save(fileBean);
					// indexDao.save(newFilepath);
					//将内存库中新加数据同步更新到索引库
					IndexUtil.addDocumentsFromRamToFS();
					//重新加载内存库
					RamDirectoryFactory.reloadRamDirectory();
				}
			} catch (NoSuchAlgorithmException m) {
				// TODO Auto-generated catch block
				state="1";
				m.printStackTrace();
			} catch (UnsupportedEncodingException m) {
				// TODO Auto-generated catch block
				state="1";
				m.printStackTrace();
			}
		}
		state="0";//0代表成功
		return SUCCESS;
	}


	@SuppressWarnings("static-access")
	@Action(value="updatescwjStudent",results={@Result(name="success",type="json")})
	public String updatescwjStudent() throws Exception {
		state="1";
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");//用户id
		System.out.println("userid:"+userid);
		String fileName="";
		String lxm="";
		String zlms="";
		String zsdmc="";
		String filePath="";
		String tip="";
		Date scrq;
		String zsdid="";
		rows=this.getRows();

//		System.out.println("rows:"+rows);
//		net.sf.json.JSONObject jObject=net.sf.json.JSONObject.fromObject(rows);
//		System.out.println("jObject："+jObject);
		net.sf.json.JSONArray jsonArray = JSONArray.fromObject(rows);

		System.out.println("jsonArry:"+rows);
		int iSize = jsonArray.size();
		SimpleDateFormat sDateFormat333 = new SimpleDateFormat("yyyyMMddHHmmss"); 
		
		String guanlian=sDateFormat333.format(new Date());
		for (int i = 0; i < iSize; i++) {
			
			JSONObject jsonObj = (JSONObject) jsonArray.get(i);

			lxm=jsonObj.getString("lxm");//文件类型
			System.out.println("lxm:"+lxm);
			fileName=jsonObj.getString("fileName");
			System.out.println("fileName:"+fileName);
			zlms=jsonObj.getString("zlms");
			System.out.println("zlms:"+zlms);
			String riqi=jsonObj.getString("scrq");//上传时间
			System.out.println("日期："+riqi);
			String riqi1=riqi.replace("T", "");
			System.out.println("日期1："+riqi1);
			SimpleDateFormat sDateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss"); 
			sDateFormat1.setLenient(false);

			scrq=sDateFormat1.parse(riqi1);
			System.out.println("scrq:" + scrq);

			int no1 = Integer.parseInt(jsonObj.getString("no"));//资料编号
			System.out.println("no1:" + no1);

			zsdmc=jsonObj.getString("zsdmc");
			System.out.println("zsdmc:"+zsdmc);
			filePath=jsonObj.getString("filePath");
			System.out.println("filePath:"+filePath);
			tip=jsonObj.getString("jxnr");
			System.out.println("tip:"+tip);
		    zlms=jsonObj.getString("zlms");
		    
		    
		    Userinfo userinfo = new Userinfo();
			userinfo.setUserId(userid);
		    //保存至资料标号表：
		    Dmtzl dmtzl = new Dmtzl();
			dmtzl.setUserinfo(userinfo);
			//dmtzl.setWjlx(wjlx);
			dmtzl.setZlmc(fileName);

			dmtzl.setFilename(fileName);
			System.out.println("fileName:"+fileName);
			zlms=jsonObj.getString("zlms");
			dmtzl.setZlms(zlms);
			
			
			//////这里是测试接口:
			
			dmtzl.setZlly("");
			dmtzl.setZlscm("");

			dmtzl.setScrq(scrq);

			dmtzl.setZlmd5("");
			dmtzl.setTotalNum(0);

			this.baseservice.save(dmtzl);
		    
			String hql = "select max(zlbh) from Dmtzl where filename='" + fileName + "'";
			int j = this.baseservice.findHql(Integer.class, hql).get(0);//获取最大的资料编号
			System.out.println("j:"+j);
			Dmtzl dmt= new Dmtzl();
			dmt.setZlbh(j);
		    
			String hql2 = "from Wjlx where kzm like '%"+lxm+"%'";
			List<Wjlx> wjlxlist = this.baseservice.findHql(Wjlx.class, hql2);
			String fileType = wjlxlist.get(0).getLxm();
			
			Scwj scwj2=this.baseservice.find(Scwj.class, no1);
			String root=ServletActionContext.getServletContext().getRealPath("/upload/");
			String downLoadFilePath=root+"/"+scwj2.getOldfilepath();
			String viewFailPath=scwj2.getFilepath();
			String searchFailPath=root+"/"+scwj2.getSearchfilepath();
			if (fileType.equals("视频")||fileType.equals("动画")||fileType.equals("图纸")) {
				File file=new File(searchFailPath);
				string2File.writeTxtFile(zlms, file);
			}
			if(lxm.equals(".zip")||lxm.equals(".rar")){
				File file=new File(searchFailPath);
				
				string2File.writeTxtFile(zlms, file);
				viewFailPath="0";
			}
			if (fileType.equals("音频")) {
				//执行相应的操纵
				File file=new File(searchFailPath);
				string2File.writeTxtFile(zlms, file);
			} 
			
			scwj2.setZlms(zlms);
			scwj2.setFileusertype("2");//学生上传的
			scwj2.setDmtzl(dmt);
			 scwj2.setZsdmc(zsdmc);
			 scwj2.setGuanlian(guanlian);
			 scwj2.setKcmc("C语言程序设计");
			 zsdid=jsonObj.getString("zsdid");
			 scwj2.setZsdid(zsdid);
			this.baseservice.update(scwj2);
			
			
			Scwj scwj3=this.baseservice.find(Scwj.class, no1);
			//关联知识点
		
			
	        String kcmc=scwj3.getKcmc();
			//知识点和笔记相关联
			List<MapNode> mapNodes=this.baseservice.find(MapNode.class);
			
			int size=(zsdid.length()-1)/4;
		
			if (mapNodes==null||mapNodes.size()<=0) {

				for (int k = 0; k < size; k++) {
					System.out.println("k："+k);
					String str4=getMap().get(String.valueOf(k+1));
					String nodeid=str4+zsdid.substring(1,4*(k+1)+1);
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
						String str=getMap().get(String.valueOf(k));
						String parentid=str+zsdid.substring(1,4*k+1);
						mapNode.setParentid(parentid);
					}
					this.baseservice.save(mapNode);
				}

			}else {
				for (int k = size; k> 0; k--) {
					String str4=getMap().get(String.valueOf(k));
					String nodeid=str4+zsdid.substring(1, 4*k+1);
					
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
						
						String str=getMap().get(String.valueOf(k-1));
						String parentid=str+zsdid.substring(1,4*(k-1)+1);
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
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			// 将文件创建到索引数据库
			IndexDao indexDao = RamIndexDaoFactory.getInstance();
			FileBean fileBean = new FileBean();
			String pubDate = TimeUtil.getCurrentDate();
			fileBean.setPubDate(pubDate);
			fileBean.setPubWriter(userid);
			fileBean.setViewPath(viewFailPath);
		    fileBean.setZsdmc(zsdmc);
		    
			File pubFile = new File(searchFailPath);
			fileBean.setPubFile(pubFile);
			fileBean.setI_id("0");
			fileBean.setKeci("0");
			fileBean.setZlid("0");
			fileBean.setTCName("C语言程序设计");
			fileBean.setZName(fileName);
			fileBean.setJName("0");
			fileBean.setFileType(fileType);
			File downLoadFile=new File(downLoadFilePath);
			fileBean.setDownLoadFile(downLoadFile);
			
			System.out.println("FileBean:"+fileBean);
			indexDao.save(fileBean);
			// indexDao.save(newFilepath);
			//将内存库中新加数据同步更新到索引库
			IndexUtil.addDocumentsFromRamToFS();
			//重新加载内存库
			RamDirectoryFactory.reloadRamDirectory();
		}

		state="0";//0代表成功
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
}  