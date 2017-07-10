/*
 *@(#)xx.spdh.action
 *@UploadAction.java.java  
 *@����ʱ��:2011-8-3����04:27:42
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.spdh.action;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import xx.adminservice.AdminService;
import xx.collection.bean.Dmtzl;
import xx.collection.bean.Jxnr;
import xx.collection.bean.Scwj;
import xx.collection.bean.Yhzdymc;
import xx.collection.bean.Zlzsddy;
import xx.collection.bean.Zysc;
import xx.page.module.Image2PdfService;
import xx.page.module.Pdf2SwfService;
import xx.page.module.String2File;
import xx.quanxian.service.BaseService;
import xx.spdh.bean.ScWj;

import com.flagstone.transform.MovieTag;
import com.flagstone.transform.ShowFrame;
import com.googlecode.jsonplugin.annotations.JSON;
import com.lowagie.text.pdf.PdfReader;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ScwjAction <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:��Ƶ��������ɾ�Ĳ�} 
 */

@Controller
@Scope("prototype")
@Namespace("")
@ParentPackage("default-package")
@SuppressWarnings("serial")
public class ScwjAction extends ActionSupport{

	@Resource(name="baseService")
	private BaseService baseservice;
	@Resource(name="adminService")
	private AdminService adminService;
	@Resource(name="pdf2swf")
	private Pdf2SwfService pdf2swf;
	
	@Resource(name="image2PdfService")
	private Image2PdfService image2PdfService;
	@Resource(name="string2File")
	private String2File string2File;
	
	private Scwj scwj;
	private Dmtzl dmtzl;
	private List<Scwj> scwjlist = new ArrayList<Scwj>();
	private List<ScWj> rows = new ArrayList<ScWj>();//���󼯺�    
	private List<Scwj> scwjb;

	private int page;//��ǰҳ
	private int rows_s;//ÿһҳ��ʾ������
	private int total;//��¼����


	private String no;
	private int zlbh;
	private String zlms;
	private String userId;
	private String filename;
	private String zlly;
	private String zlscm;
	private String zmfilename;
	private List<File> fileupload;
	private List<String> fileuploadFileName;
	private String fileName;
	private Date scrq;
	private String changdu;
	private String filePath;

	private String zlmc1;
	private String lxm;

	private List<Map<String, String>>zlmczlbh=new ArrayList<Map<String,String>>();


	public List<Map<String, String>> getZlmczlbh() {
		return zlmczlbh;
	}
	public void setZlmczlbh(List<Map<String, String>> zlmczlbh) {
		this.zlmczlbh = zlmczlbh;
	}

	public static final String ROOT = "upload\\";

	private String zlmc;
	private String CName;
	private String zmc;
	private String zsdmc;
	private String weizhi;

	private String scwjtype;
	private String scwjword;
	private String tip;
	private String jsessionid;
	private String mc;

	private String a;
	private static int total1;

	private String ziliaorows;
	private String newFileName;
  

	public String getNewFileName() {
		return newFileName;
	}
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}
	public String getZiliaorows() {
		return ziliaorows;
	}
	public void setZiliaorows(String ziliaorows) {
		this.ziliaorows = ziliaorows;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the scrq
	 */
	public Date getScrq() {
		return scrq;
	}
	/**
	 * @param scrq the scrq to set
	 */
	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}

	/**
	 * @return the changdu
	 */
	public String getChangdu() {
		return changdu;
	}
	/**
	 * @param changdu the changdu to set
	 */
	public void setChangdu(String changdu) {
		this.changdu = changdu;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the lxm
	 */
	public String getLxm() {
		return lxm;
	}
	/**
	 * @param lxm the lxm to set
	 */
	public void setLxm(String lxm) {
		this.lxm = lxm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}

	public static int getTotal1() {
		return total1;
	}
	public static void setTotal1(int total1) {
		ScwjAction.total1 = total1;
	}

	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}

	@JSON(serialize=false)
	public BaseService getBaseservice() {
		return baseservice;
	}
	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}

	@JSON(serialize=false)
	public List<Scwj> getScwjlist() {
		return scwjlist;
	}
	public void setScwjlist(List<Scwj> scwjlist) {
		this.scwjlist = scwjlist;
	}

	@JSON(serialize=false)
	public Scwj getScwj() {
		return scwj;
	}
	public void setScwj(Scwj scwj) {
		this.scwj = scwj;
	}

	public List<ScWj> getRows() {
		return rows;
	}
	public void setRows(List<ScWj> rows) {
		this.rows = rows;
	}

	@JSON(serialize=false)
	public List<Scwj> getScwjb() {
		return scwjb;
	}
	public void setScwjb(List<Scwj> scwjb) {
		this.scwjb = scwjb;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public int getRows_s() {
		return rows_s;
	}
	public void setRows_s(int rows_s) {
		this.rows_s = rows_s;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	public Integer getZlbh() {
		return zlbh;
	}
	public void setZlbh(Integer zlbh) {
		this.zlbh = zlbh;
	}

	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getZlmc() {
		return zlmc;
	}
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}

	@JSON(serialize=false)
	public String getZlly() {
		return zlly;
	}
	public void setZlly(String zlly) {
		this.zlly = zlly;
	}

	@JSON(serialize=false)
	public String getZlscm() {
		return zlscm;
	}
	public void setZlscm(String zlscm) {
		this.zlscm = zlscm;
	}

	@JSON(serialize=false)
	public String getZmfilename() {
		return zmfilename;
	}
	public void setZmfilename(String zmfilename) {
		this.zmfilename = zmfilename;
	}

	@JSON(serialize=false)
	public String getZlms() {
		return zlms;
	}
	public void setZlms(String zlms) {
		this.zlms = zlms;
	}

	@JSON(serialize=false)
	public List<File> getFileupload() {
		return fileupload;
	}
	public void setFileupload(List<File> fileupload) {
		this.fileupload = fileupload;
	}

	@JSON(serialize=false)
	public List<String> getFileuploadFileName() {
		return fileuploadFileName;
	}
	public void setFileuploadFileName(List<String> fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public String getCName() {
		return CName;
	}
	public void setCName(String name) {
		CName = name;
	}
	public String getZmc() {
		return zmc;
	}
	public void setZmc(String zmc) {
		this.zmc = zmc;
	}
	public String getZsdmc() {
		return zsdmc;
	}
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	public String getWeizhi() {
		return weizhi;
	}
	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

	@JSON(serialize=false)
	public String getScwjtype() {
		return scwjtype;
	}
	public void setScwjtype(String scwjtype) {
		this.scwjtype = scwjtype;
	}

	@JSON(serialize=false)
	public String getScwjword() {
		return scwjword;
	}
	public void setScwjword(String scwjword) {
		this.scwjword = scwjword;
	}

	@JSON(serialize=false)
	public String getJsessionid() {
		return jsessionid;
	}
	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	@JSON(serialize=false)
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	//��������ת�����ֽ���
	//	private byte[] InputStreamToByte(InputStream is) throws IOException {  
	//		   ByteArrayOutputStream bytestream = new ByteArrayOutputStream();  
	//		   int ch;  
	//		   while ((ch = is.read()) != -1) {  
	//		    bytestream.write(ch);  
	//		   }  
	//		   byte imgdata[] = bytestream.toByteArray();  
	//		   bytestream.close();  
	//		   return imgdata;  
	//	}

	private byte[] getBytes(byte[] bytes, int offset, int length) {
		byte requiredBytes[] = new byte[length];
		for (int i = offset, j = 0; j < length; i++, j++) {
			requiredBytes[j] = bytes[i];
		}
		return requiredBytes;
	}

	private int getOffset(byte[] target, byte[] src) {
		int i = 0;
		int j = 0;
		int k = 0;

		for (i = 0; i < src.length; i++) {
			k = i;
			for (j = 0; j < target.length; k++, j++) {
				if (target[j] != src[k]) {
					break;
				}
			}
			if (j == target.length)
				return i;
		}
		return 0;
	}

	private double getDouble(byte[] bytes, String property) {
		int offset = getOffset(property.getBytes(), bytes);
		if (offset != -1) {

			return ByteBuffer.wrap(
					getBytes(bytes, offset + property.length() + 1, 8))
					.getDouble();
		} else {
			return -1;
		}
	}

	/**
	 * �����ļ���������ȡpdf��ʽ����ҳ��
	 * @param total
	 */
	public int readPdf(String filename) throws Exception{
		PdfReader reader = new PdfReader(filename);
		int pagecount=reader.getNumberOfPages();
		return pagecount;
	}

	/**
	 * �����ļ���������ȡswf��ʽ���ĵ���ҳ��
	 * @param total
	 */
	public int numberOfFrames(final List<MovieTag> list) {       
		int total = 0;       
		for (MovieTag object : list){           
			if (object instanceof ShowFrame)
			{               
				total++;  
			}       
		} 
		return total;
	}

	/**
	 * �����ļ���������ȡflv��ʽ��Ƶ����ʱ��
	 * @param total
	 */
	private int getMetaData(InputStream is) throws Exception {
		byte bytes[] = new byte[1024];
		is.read(bytes);

		double duration = getDouble(bytes, "duration");
		DecimalFormat f = new DecimalFormat("00");
		//		String str = f.format((int) duration) + f.format((int) duration % 60);
		int total = Integer.parseInt(f.format((int) duration));
		return total;
	}

	/**
	 * ����·������һϵ�е�Ŀ¼
	 * 
	 * @param path
	 */
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

	/**
	 * @{������:updatescwj}
	 * @param {���������} {�������˵��}
	 * @return {SUCCESS} {���ز���˵��}
	 * @throws JSONException 
	 * @throws ParseException 
	 * @{�����Ĺ���/��������:���ϴ��ļ�����Ϣ����dmtzl����}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
//	@Action(value="updatescwj",results={@Result(name="success",type="json")})
//	public String updatescwj() throws JSONException, ParseException {
//		ziliaorows=this.getZiliaorows();
//
//		HttpSession hs = ServletActionContext.getRequest().getSession();
//		String userid = (String)hs.getAttribute("uid");//�û�id
//		JSONArray jsonArray = new JSONArray(rows);
//
//		int iSize = jsonArray.length();
//		System.out.println("Size:" + iSize);
//		for (int i = 0; i < iSize; i++) {
//			JSONObject jsonObj = jsonArray.getJSONObject(i);
//
//			String md5;
//			zlmc=(String)jsonObj.get("fileName");//��������
//			md5=zlmc;
//			
//			MD5Builder md5b = new MD5Builder();
//			try {
//				String zlmd5 = md5b.EncoderByMd5(md5);
//				Userinfo userinfo = new Userinfo();
//				userinfo.setUserId(userid);
//
//				lxm=(String) jsonObj.get("lxm");//�ļ�����
//				System.out.println("lxm:"+lxm);
//				String hql2 = "from Wjlx where kzm like '%"+lxm+"%'";
//				List<Wjlx> wjlxlist = this.baseservice.findHql(Wjlx.class, hql2);
//				String aa = wjlxlist.get(0).getLxm();
//
//				System.out.println("aa:"+aa);
//				Wjlx wjlx = new Wjlx();
//				wjlx.setLxm(aa);
//
//				Dmtzl dmtzl = new Dmtzl();
//				dmtzl.setUserinfo(userinfo);
//				dmtzl.setWjlx(wjlx);
//				dmtzl.setZlmc(zlmc);
//				
//				fileName=(String) jsonObj.get("fileName");
//				dmtzl.setFilename(fileName);
//				System.out.println("fileName:"+fileName);
//				zlms=(String)jsonObj.get("zlms");
//				dmtzl.setZlms(zlms);
//				dmtzl.setZlly("");
//				dmtzl.setZlscm("");
//				
//				String riqi=(String) jsonObj.get("scrq");//�ϴ�ʱ��
//				String riqi1=riqi.replace("T", " ");
//				SimpleDateFormat sDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//				sDateFormat1.setLenient(false);
//				
//				scrq=sDateFormat1.parse(riqi1);
//				dmtzl.setScrq(scrq);
//				
//				dmtzl.setZlmd5(zlmd5);
//				int no1 = Integer.parseInt((String) jsonObj.get("no"));//���ϱ��
//				Scwj scwj = this.baseservice.find(Scwj.class, no1);
//				int totalNum = scwj.getTotalNum();
//				if(totalNum < 0) {
//					totalNum = 0;
//				}
//				
//				dmtzl.setTotalNum(totalNum);
//
//				this.baseservice.save(dmtzl);
//
//				//����scwj��
				//String hql = "select max(zlbh) from Dmtzl where filename='" + fileName + "'";
				//int j = this.baseservice.findHql(Integer.class, hql).get(0);//��ȡ�������ϱ��
//				System.out.println("j:"+j);
//				Dmtzl dmt= new Dmtzl();
//				dmt.setZlbh(j);
//				scwj.setDmtzl(dmt);
//				this.baseservice.update(scwj);
//
//				//��֪ʶ����в���
//				
//				zsdmc=(String)jsonObj.get("zsdmc");
//				
//				
//				filePath=(String)jsonObj.get("filePath");
//				tip=(String)jsonObj.get("tip");
//				if(tip=="jxnr"||tip.equals("jxnr")){
//					Jxnr jr = new Jxnr();
//					jr.setWjms(zlms);
//					jr.setWjmc(fileName);
//					jr.setFilepath(filePath);
//					JxjhYck jh = new JxjhYck();
//					jh.setNo((Integer)hs.getAttribute("keci"));
//
//					jr.setJxjhSz((Integer)hs.getAttribute("i_d")+"");
//					jr.setZlid(j);
//					jr.setJxjhYck(jh);
//					this.baseservice.save(jr);
//					zlbh=j;
//					mc = zlmc1;
//					int kkch= (Integer)hs.getAttribute("kkch");
//					CourseChapter cc=this.baseservice.find(CourseChapter.class, kkch);
//					CName=cc.getCName();zmc=cc.getTCName();
//					System.out.println("CName:"+CName+" zmc:"+zmc);
//
//					//������һ��
//					
//					String zlbh1=String.valueOf(zlbh);
//					ZlzsddyId id1 = new ZlzsddyId();
//					if(zlbh1==null||zlbh1==""){
//						//ͨ��zlmc����zlbh
//						String[] keys1 = new String[1];
//						keys1[0] = "zlmc";
//						Object[] values1 = new Object[1];
//						values1[0] = zlmc;
//						List<Integer> zlbh = this.baseservice.find(Integer.class, "Dmtzl", "id.zlbh", keys1, values1);
//						id1.setZlbh(zlbh.get(0));
//					}else{
//						id1.setZlbh(Integer.valueOf(zlbh));
//					}
//					//ͨ��CName����zbh
//					String[] keys2 = new String[1];
//					keys2[0] = "CName";
//					Object[] values2 = new Object[1];
//					values2[0] = CName;
//					List<Integer> zbh = this.baseservice.find(Integer.class, "CourseChapter", "zbh", keys2, values2);
//					
//					//����֪ʶ�����ƻ�ȡzmc
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
//					//��Jie���в������
//					String[] keys3 = new String[1];
//					keys3[0] = "zmc";//zmc��ʾ������
//					Object[] values3 = new Object[1];
//					values3[0] = zmc;
//					List<Integer> CId = this.baseservice.find(Integer.class, "Jie", "id.CId", keys3, values3);
//					
//					//ͨ��zsdmc����zsdbh
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
//					zlzsddy.setWeizhi(this.getWeizhi());
//					zlzsddy.setId(id1);
//					
//					this.baseservice.save(zlzsddy);
//					
//					
//					
//					
//				
////					String oldfileroot = "";
////					String oldfilepath="";
////					String extName=lxm;
////					String pubfile="";
////					String downLoadFilePath="";
////					SimpleDateFormat sDateFormat; 
////					Random r = new Random(); 
////
////					int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;
////					sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
////					sDateFormat.setLenient(false);
////					String nowTimeStr = sDateFormat.format(new Date());
////
////					
////					if(extName.equals(".jpg")||extName.equals(".jpeg")||extName.equals(".bmp")
////							||extName.equals(".gif")||extName.equals(".png")) {	
////						oldfileroot = ServletActionContext.getRequest().getRealPath("/upload/Originalfile/images/"+userid+"/");
////						downLoadFilePath=oldfileroot+"/"+fileName;
////						oldfilepath="previewFile\\\\images\\\\"+userid+"\\\\"+newFileName;
////						pubfile=oldfileroot+"/"+fileName;
////					} 
////					//����Ϊ�ı�
////					else if (extName.equals(".pdf")||extName.equals(".ppt")
////							||extName.equals(".xls")||extName.equals(".doc")||extName.equals(".docx")){
////						oldfilepath="previewFile\\\\texts\\\\"+userid+"\\\\"+newFileName;
////						oldfileroot = ServletActionContext.getRequest().getRealPath("/upload/Originalfile/texts/"+userid+"/");
////						downLoadFilePath=oldfileroot+"/"+fileName;
////						pubfile=oldfileroot+"/"+fileName;
////					} 
////					//����Ϊ��Ƶ
////					else if (extName.equals(".flv")||extName.equals(".swf")||extName.equals(".wmv")||extName.equals(".avi")||extName.equals("mpg")||extName.equals("3gp")||extName.equals("mov")||extName.equals("mp4")||extName.equals("asf")||extName.equals("asx")) {
////						oldfilepath="previewFile\\\\videos\\\\"+userid+"\\\\"+newFileName;
////						oldfileroot = ServletActionContext.getRequest().getRealPath("/upload/Originalfile/videos/"+userid+"/");
////						downLoadFilePath=oldfileroot+"/"+fileName;
////						pubfile=oldfileroot+"/"+fileName.replace(extName, ".txt");
////					
////						File file=new File(pubfile);
////						try {
////							string2File.writeTxtFile(zlms, file);
////						} catch (Exception e) {
////							// TODO Auto-generated catch block
////							e.printStackTrace();
////						}
////					} 
////					//����Ϊ��Ƶ
////					else if (extName.equals(".mp3")||extName.equals(".wma")) {
////						oldfilepath="Originalfile\\\\voices\\\\"+userid+"\\\\"+newFileName;
////						oldfileroot = ServletActionContext.getRequest().getRealPath("/upload/Originalfile/voices/"+userid+"/");
////						downLoadFilePath=oldfileroot+"/"+fileName;
////						pubfile=oldfileroot+"/"+fileName;
////					} else {
////						//�������
////					}
////				
////					// ���ļ��������������ݿ�
////					IndexDao indexDao = RamIndexDaoFactory.getInstance();
////					FileBean fileBean = new FileBean();
////					String pubDate = TimeUtil.getCurrentDate();
////					fileBean.setPubDate(pubDate);
////					fileBean.setPubWriter(jsessionid);
////					fileBean.setViewPath(oldfilepath);
////					
////					fileBean.setI_id(String.valueOf(hs.getAttribute("i_d")));
////					fileBean.setKeci(String.valueOf(hs.getAttribute("keci")));
////					fileBean.setZlid(String.valueOf(j));
////				
////					fileBean.setZlmc(zlmc);				
////					File pubFile = new File(pubfile);
////					fileBean.setPubFile(pubFile);
////					System.out.println("publicfile:"+pubfile);
////					System.out.println("downLoadFilePath:"+downLoadFilePath);
////					File downLoadFile=new File(downLoadFilePath);
////					fileBean.setDownLoadFile(downLoadFile);
////					indexDao.save(fileBean);
////					// indexDao.save(newFilepath);
////					//���ڴ�����¼�����ͬ�����µ�������
////					IndexUtil.addDocumentsFromRamToFS();
////					//���¼����ڴ��
////					RamDirectoryFactory.reloadRamDirectory();
////
//				}
//			} catch (NoSuchAlgorithmException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////
//
////		}
//
//		}
//		return SUCCESS;
//	}

	//�����û��Ƿ���д�ز���Ϣ
	@Action(value="/check",results={@Result(name="success",type="json")})
	public String check(){
		String fp = filePath;
		String root = ServletActionContext.getRequest().getRealPath("/upload");
		String fPath = root + "\\" + fp;
		deleteFile(fPath);
		int no1 = 0;
		no1 = Integer.parseInt(no);
		scwj = this.baseservice.find(Scwj.class, no1);
		this.baseservice.delete(scwj);
		return SUCCESS;
	}


	/** 
	 * ɾ�������ļ� 
	 * @param   fPath    ��ɾ���ļ����ļ��� 
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
	 */  
	public boolean deleteFile(String fPath) {  
		boolean flag = false;  
		File file = new File(fPath);  
		System.out.println(file);
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
		if (file.isFile() && file.exists()) {  
			file.delete();  
			flag = true;  
		}  
		return flag;  
	}

	/**
	 * @{deletescwj.action}
	 * @param {} {��ʾ��ý��������Ϣ}
	 * @return {SUCCESS} {��ʾ���ж�ý��������Ϣ} 
	 * @{ɾ���ز��ļ�}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@Action(value="/deletescwj",results={@Result(name="success",type="json")})
	public String deletedmtzl(){

		//		String[] keys1 = new String[1];
		//		keys1[0] = "no";
		//		Object[] values1 = new Object[1];
		//		values1[0] = no;
		//		List<String> fp1 = this.baseservice.find(String.class, "Scwj", "filepath", keys1, values1);
		//		String fp = fp1.get(0);
		String hql = "select filepath from Scwj where no=" + no;
		String fp = this.baseservice.findHql(String.class, hql).get(0);

		String root = ServletActionContext.getRequest().getRealPath("/upload");
		String fPath = root + "\\" + fp;
		deleteFile(fPath);
		int no1 = 0;
		no1 = Integer.valueOf(no);
		scwj = this.baseservice.find(Scwj.class, no1);

		String[] keys3 = new String[1];
		keys3[0] = "zlbh";
		Object[] values3 = new Object[1];
		values3[0] = zlbh;
		List<Zlzsddy> zlzsddy = this.baseservice.find(Zlzsddy.class, "Zlzsddy", keys3, values3);

		for(int i=0;i<zlzsddy.size();i++) {
			this.baseservice.delete(zlzsddy.get(i));
		}

		dmtzl = this.baseservice.find(Dmtzl.class, zlbh);

		String[] keys0 = new String[1];
		keys0[0] = "zlid";
		Object[] values0 = new Object[1];
		values0[0] = zlbh;
		List<Integer> jxnr_id = this.baseservice.find(Integer.class, "Jxnr", "id", keys0, values0);
		if(jxnr_id.size()==0) {

		} else {
			Jxnr jxnr = this.baseservice.find(Jxnr.class, jxnr_id.get(0));
			this.baseservice.delete(jxnr);
		}

		this.baseservice.delete(scwj);
		this.baseservice.delete(dmtzl);
		//ɾ��ʱҪ���ǵ���Դ�Ƿ��ղص����������������
		List<Zysc> sclist = new ArrayList<Zysc>();
		sclist = this.baseservice.findHql(Zysc.class, "from Zysc z where z.zylx=4 and z.zybh="+dmtzl.getZlbh());
		if(sclist.size()>0){
			for(int i=0;i<sclist.size();i++){
				Zysc zz = new Zysc();
				zz = sclist.get(i);
				List<Yhzdymc> yhzdymc = new ArrayList<Yhzdymc>();                          //��һ���û��Զ������
				yhzdymc = this.baseservice.findHql(Yhzdymc.class, "from Yhzdymc y where y.zdyflno="+zz.getYhzdymc().getZdyflno());  
				Yhzdymc yhzdymc1 = new Yhzdymc();
				yhzdymc1 = yhzdymc.get(0);                                  //�õ������Ӧ���Զ������
				int old = yhzdymc1.getZysl();                               //�ó���ǰ����Դ����
				int xin = old-1;                                           //��ȡ�µ�����
				yhzdymc1.setZysl(xin);                                       //���ն�����û��Զ�����ำֵ
				this.baseservice.update(yhzdymc1);
				zz.setZylx(14);//�������ݿ�
				this.baseservice.update(zz);
			}
		}
		return SUCCESS;
	}

	//�����ѯ�Ƿ�Ϊ��,���ó���������
	@Action(value="/searchscwj1",results={@Result(name="root",type="json")})
	public String searchscwj1(){
		String hql = "select count(*) from Dmtzl where "+scwjtype+" like '%"+scwjword+"%'";
		total1 = this.baseservice.getTotalSql(hql);
		if(total1!=0){
			a="1";
		}else{
			a="0";
		}
		HttpSession hs = ServletActionContext.getRequest().getSession();
		hs.setAttribute("total1", total1);
		return "root";
	}

	//�ز��ļ���ѯ
	@Action(value="/searchscwj",results={@Result(name="success",type="json")})
	public String searchscwj(){
		HttpSession hs = ServletActionContext.getRequest().getSession();
		Integer total1 = (Integer)hs.getAttribute("total1");
		scwjlist = this.baseservice.findByTypage(Scwj.class,"Scwj",scwjtype,scwjword, "order by filename asc", page, rows_s);
		total = total1;//��¼����
		for(int i=0;i<scwjlist.size();i++){
			ScWj element = new ScWj();
			element.setStr1(scwjlist.get(i).getFilename());
			element.setStr2(scwjlist.get(i).getDmtzl().getZlms());
			element.setStr3(scwjlist.get(i).getDmtzl().getZlmc());
			rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ
		}
		return SUCCESS;
	}

	/**
	 * @{��������scwjlist}
	 * @param {���������} {�������˵��}
	 * @return {���ز�����} {���ز���˵��}
	 * @{�����Ĺ���/��������:}
	 * @exception {˵����ĳ�����,������ʲô�쳣}
	 */
	@Action(value="/scwjlist",results={@Result(name="root",type="json")})
	public String scwjlist(){
		scwjlist=this.baseservice.findAll(Scwj.class, "Scwj", page, rows_s);	//�����������
		total=this.baseservice.getTotal("Scwj");  //��¼�����ļ�¼  
		for(int i=0; i<scwjlist.size(); i++ ) {
			//int j = scwjlist.get(i).getFilename().indexOf(".");
			//filename = scwjlist.get(i).getFilename();//.substring(0, j);
			ScWj element = new ScWj();
			element.setStr1(scwjlist.get(i).getFilename());
			element.setStr2(scwjlist.get(i).getDmtzl().getZlms());
			element.setStr3(scwjlist.get(i).getDmtzl().getZlmc());
			element.setStr4(scwjlist.get(i).getDmtzl().getZlscm());
			element.setStr5(scwjlist.get(i).getDmtzl().getZlly());
			element.setStr6(scwjlist.get(i).getDmtzl().getWjlx().getLxm());
			element.setStr7(scwjlist.get(i).getDmtzl().getUserinfo().getUserId());
			element.setDt(scwjlist.get(i).getDmtzl().getScrq());
			element.setInt1(scwjlist.get(i).getDmtzl().getZlbh());
			element.setInt2(scwjlist.get(i).getDmtzl().getCssl());
			element.setInt3(scwjlist.get(i).getDmtzl().getChangdu());
			element.setNo(scwjlist.get(i).getNo());
			rows.add(element);   //�����ݷŽ�rows��ʵ�ַ�ҳ��ѯ��ʾ
		}
		return "root";
	}




}
