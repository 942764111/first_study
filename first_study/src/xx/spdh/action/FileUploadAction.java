package xx.spdh.action;  

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import xx.collection.bean.FileRoot;
import xx.collection.bean.Scwj;
import xx.collection.bean.Wjlx;
import xx.page.module.Corver2Mp3;
import xx.page.module.String2File;
import xx.quanxian.service.BaseService;

import com.googlecode.jsonplugin.annotations.JSON;
import com.jintai.data.ScwjData;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author fish 
 */  
public class FileUploadAction extends ActionSupport  
{  
	private List<File> Filedata; // 默认的客户端文件对象,命名不符合java规范fileData  
	private List<String> FiledataFileName; // 客户端文件名  
	private List<String> imageContentType; // 客户端文件名类型  
	private String lxm;
	private Date scrq;
	private String no;
	private String fileName;
	private String muluid;
	private List<Map<String, String>> rows;

	@Resource(name="string2File")
	private String2File string2File;




	/**
	 * @return the muluid
	 */
	public String getMuluid() {
		return muluid;
	}

	/**
	 * @param muluid the muluid to set
	 */
	public void setMuluid(String muluid) {
		this.muluid = muluid;
	}

	public List<Map<String, String>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}

	@JSON(serialize=false)
	public String getLxm() {
		return lxm;
	}

	public void setLxm(String lxm) {
		this.lxm = lxm;
	}
	@JSON(serialize=false)
	public Date getScrq() {
		return scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}
	@JSON(serialize=false)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	@JSON(serialize=false)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Resource(name="baseService")
	private BaseService baseservice;
	

	@JSON(serialize=false)
	public List<File> getFiledata()  
	{  
		return Filedata;  
	}  

	public void setFiledata(List<File> filedata)  
	{  
		Filedata = filedata;  
	}  
	@JSON(serialize=false)
	public List<String> getFiledataFileName()  
	{  
		return FiledataFileName;  
	}  

	public void setFiledataFileName(List<String> filedataFileName)  
	{  
		FiledataFileName = filedataFileName;  
	}  
	@JSON(serialize=false)
	public List<String> getImageContentType()  
	{  
		return imageContentType;  
	}  

	public void setImageContentType(List<String> imageContentType)  
	{  
		this.imageContentType = imageContentType;  
	}  

	@Action(value="scwjUpload",results={@Result(name="success",type="json")})
	public String execute() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");

		String extName="";
		String nowTimeStr="";
		String fileName2="";
		String root="";
		String oldfileroot="";
		String searchfileroot="";
		String filePath="";
		String oldfilepath="";
		String searchfilepath="";
		String thumbnailspath="";
        String filetype="";
		if (Filedata == null || Filedata.size() == 0)  
		{  
			return null;  
		}  
		for (int i = 0; i < Filedata.size(); ++i)  
		{  

			String ofileName = FiledataFileName.get(i); // 文件真名  
			extName = ofileName.substring(ofileName.lastIndexOf("."));
			
			lxm=extName;
			SimpleDateFormat sDateFormat; 

			SimpleDateFormat sDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			sDateFormat1.setLenient(false);
			String nowTimeStr1 = sDateFormat1.format(new Date());

			sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
			sDateFormat.setLenient(false);
			nowTimeStr = sDateFormat.format(new Date());
            Random random=new Random();
            int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
			int j = ofileName.indexOf(".");
			fileName2 = ofileName.substring(0, j);
			scrq = sDateFormat1.parse(nowTimeStr1);
			fileName = fileName2 + "_" + nowTimeStr + extName;
			String preFileName=nowTimeStr+rannum;
           
			String hql2 = "from Wjlx where kzm like '%"+extName+"%'";
			List<Wjlx> wjlxlist = this.baseservice.findHql(Wjlx.class, hql2);

			String fileType = wjlxlist.get(0).getLxm();

			if(fileType.equals("动画")) {

				root = ServletActionContext.getServletContext().getRealPath("/upload/previewFile/images/" + userid+"/");
              
				filePath = "upload\\previewFile\\images\\" + userid + "\\" + preFileName+".swf";

				oldfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Originalfile/images/"+userid+"/");
				searchfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Searchfile/images/"+userid+"/");
				
				oldfilepath="Originalfile\\images\\"+userid+"\\"+fileName;
				searchfilepath="Searchfile\\images\\"+userid+"\\"+preFileName+".txt";
				filetype="3";
			} 
			//类型为文本
			else if (fileType.equals("文本")){
				filetype="1";
                if (extName.equals(".zip")||extName.equals(".rar")) {
                	root = ServletActionContext.getServletContext().getRealPath("//upload//previewFile//texts//" + userid+"//");

    				filePath = "upload\\previewFile\\texts\\" + userid + "\\" + preFileName+".zip";
    				oldfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Originalfile/texts/"+userid+"/");
    				System.out.println("测试数据:"+oldfileroot);
    				searchfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Searchfile/texts/"+userid+"/");
    				oldfilepath="Originalfile\\texts\\"+userid+"\\"+fileName;
    				searchfilepath="Searchfile\\texts\\"+userid+"\\"+fileName2+"_"+nowTimeStr+".txt";
    				
				}
                else if (extName.equals(".pdf")) {
                	root = ServletActionContext.getServletContext().getRealPath("//upload//previewFile//texts//" + userid+"//");

    				filePath = "upload\\previewFile\\texts\\" + userid + "\\" + preFileName+".swf";
    				oldfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Originalfile/texts/"+userid+"/");
    				System.out.println("测试数据:"+oldfileroot);
    				searchfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Searchfile/texts/"+userid+"/");
    				oldfilepath="Originalfile\\texts\\"+userid+"\\"+fileName;
    				searchfilepath="Searchfile\\texts\\"+userid+"\\"+fileName2+"_"+nowTimeStr+".txt";
				
                }
                else {
					root = ServletActionContext.getServletContext().getRealPath("//upload//previewFile//texts//" + userid+"//");

					filePath = "upload\\previewFile\\texts\\" + userid + "\\" + preFileName+".swf";
					oldfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Originalfile/texts/"+userid+"/");
					System.out.println("测试数据:"+oldfileroot);
					searchfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Searchfile/texts/"+userid+"/");
					oldfilepath="Originalfile\\texts\\"+userid+"\\"+fileName;
					searchfilepath="Searchfile\\texts\\"+userid+"\\"+fileName;
					
				}
				
			} 
			//类型为视频
			else if (fileType.equals("视频")) {
				filetype="2";
				root = ServletActionContext.getServletContext().getRealPath("/upload/previewFile/videos/" + userid+"/");
				filePath = "upload\\previewFile\\videos\\" + userid + "\\" + preFileName+".flv";
				oldfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Originalfile/videos/"+userid+"/");
				searchfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Searchfile/videos/"+userid+"/");
				oldfilepath="Originalfile\\videos\\"+userid+"\\"+fileName;
				searchfilepath="Searchfile\\videos\\"+userid+"\\"+preFileName+".txt";
				
			} 
			//类型为音频
			else if (fileType.equals("音频")) {
				filetype="5";
				root = ServletActionContext.getServletContext().getRealPath("/upload/previewFile/voices/" + userid+"/");

				filePath = "upload\\previewFile\\voices\\"+userid+"\\"+preFileName+".mp3";
				oldfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Originalfile/voices/"+userid+"/");
				searchfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Searchfile/voices/"+userid+"/");
				oldfilepath="Originalfile\\voices\\"+userid+"\\"+fileName;
				searchfilepath="Searchfile\\voices\\"+userid+"\\"+preFileName+".txt";
				
			}else if (fileType.equals("图纸")) {
				filetype="4";
				root = ServletActionContext.getServletContext().getRealPath("/upload/previewFile/drawing/" + userid+"/");
				HttpServletRequest request=ServletActionContext.getRequest();
				String path=request.getRequestURI();
				filePath = "upload\\Originalfile\\drawing\\"+userid+"\\"+fileName;
				oldfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Originalfile/drawing/"+userid+"/");
				searchfileroot = ServletActionContext.getServletContext().getRealPath("/upload/Searchfile/drawing/"+userid+"/");
				oldfilepath="Originalfile\\drawing\\"+userid+"\\"+fileName;
				searchfilepath="Searchfile\\drawing\\"+userid+"\\"+preFileName+".txt";
				
			} 
			
			else {
				System.out.println("类型为其他情况，系统不支持");
				continue;
				//其他情况
			}
			System.out.println("跳出本次循环");
			// 将上传的文件保存到服务器的硬盘上  
			thumbnailspath=ServletActionContext.getServletContext().getRealPath("/upload/Thumbnails/"+userid+"/"+preFileName+".png");
		   
			String	thumbnails="/upload/Thumbnails/"+userid+"/"+preFileName+".png";
			
		    File tFile=new File(thumbnailspath);
			FileUtils.forceMkdir(tFile.getParentFile()); // 创建上传文件所在的父目录  
			
			
			InputStream is = new  BufferedInputStream(new FileInputStream(Filedata.get(i)));  
			HttpServletRequest request = ServletActionContext.getRequest(); // 获得ServletRequest对象  

						
			File tempFile1 = new File(root+"/"+fileName);  
			FileUtils.forceMkdir(tempFile1.getParentFile()); // 创建上传文件所在的父目录  


			File tempFile2 = new File(oldfileroot+"/"+fileName);  
			FileUtils.forceMkdir(tempFile2.getParentFile()); // 创建上传文件所在的父目录  
			OutputStream os2 = new BufferedOutputStream(new FileOutputStream(tempFile2));

			File tempFile3 = new File(searchfileroot+"/"+fileName);  
			FileUtils.forceMkdir(tempFile3.getParentFile()); // 创建上传文件所在的父目录  



			int len = 0;  
			byte[] buffer = new byte[500];  

			
			if (fileType.equals("视频")||fileType.equals("音频")||fileType.equals("动画")) {

				
				//将视频转成MP3
				if (fileType.equals("音频")) {
					File tempFile5 = new File(root+"/"+preFileName+".mp3");  
					FileUtils.forceMkdir(tempFile5.getParentFile());
					
					if (extName.equals(".mp3")) {
						//无需转换
						OutputStream os4 = new BufferedOutputStream(new FileOutputStream(tempFile5));
						while (-1 != (len = is.read(buffer)))  
						{  
							os4.write(buffer, 0, len);  
							os2.write(buffer, 0, len); 
						} 
						os4.flush();  
						os4.close();	
					} else {
						while (-1 != (len = is.read(buffer)))  
							{  
								os2.write(buffer, 0, len);  
			
							} 
                        //需要将文件进行转换
						Corver2Mp3 corver2Mp3=new Corver2Mp3();
						corver2Mp3.corver(oldfileroot+"/"+fileName, root+"/"+preFileName+".mp3");
					}
				}else{
					while (-1 != (len = is.read(buffer)))  
					{  
						os2.write(buffer, 0, len);  
	
					} 
				}
				
				is.close();  
				os2.flush();  
				os2.close();	
			} else if (fileType.equals("图纸")) {
				while (-1 != (len = is.read(buffer)))  
				{  
					os2.write(buffer, 0, len);  
				}  
				is.close(); 
				os2.flush();  
				os2.close();
			}else {
				if (extName.equals(".swf")) {
					OutputStream os1 = new BufferedOutputStream(new FileOutputStream(tempFile3));
					OutputStream os3 = new BufferedOutputStream(new FileOutputStream(tempFile3));
					while (-1 != (len = is.read(buffer)))  
					{  
						os1.write(buffer, 0, len);  
						os2.write(buffer, 0, len);  
						os3.write(buffer, 0, len);  
					}  
					is.close(); 
					os1.flush();  
					os1.close();
					os2.flush();  
					os2.close();
					os3.flush();  
					os3.close();
				} else {
					OutputStream os3 = new BufferedOutputStream(new FileOutputStream(tempFile3));
					while (-1 != (len = is.read(buffer)))  
					{  
						os2.write(buffer, 0, len);  
						os3.write(buffer, 0, len);  
					}  
					is.close();  
					os2.flush();  
					os2.close();
					os3.flush();  
					os3.close();
				}

			}
			
			
			
			
			String out = "";//获取文件的路径;
			if (extName.equals(".swf")) {
				//不需要做任何转换
				System.out.println("上传的文件不需要做任何转换");
			}else if (extName.equals(".wmv")||extName.equals(".avi")||extName.equals(".mpg")||extName.equals(".3gp")||extName.equals(".mov")||extName.equals(".asf")||extName.equals(".asx")||extName.equals(".mp4")||extName.equals(".flv")) {
				//将进行转换成flv。
				FileRoot fRoot=new FileRoot();
				fRoot.setInputfile(oldfileroot+"/"+fileName);
				fRoot.setOutputfile(root+"/"+preFileName+".flv");
				fRoot.setThumbnail(thumbnailspath);
				fRoot.setType("视频");
				this.baseservice.save(fRoot);
				
			}else if (extName.equals(".zip")||extName.equals(".rar")||extName.equals(".mp3")||extName.equals(".dwg")) {
				System.out.println("上传的是压缩包");
			}else{
				FileRoot fRoot=new FileRoot();
				fRoot.setInputfile(oldfileroot+"/"+fileName);
				fRoot.setOutputfile(root+"/"+preFileName+".swf");
				fRoot.setThumbnail(thumbnailspath);
				fRoot.setType("文本");
				this.baseservice.save(fRoot);
			}

			Scwj scwj = new Scwj();
			scwj.setFilepath(filePath);
			scwj.setOldfilepath(oldfilepath);
			scwj.setSearchfilepath(searchfilepath);
			scwj.setUserId(userid);
			scwj.setUploadTime(nowTimeStr1);
			scwj.setShareNum("0");
			scwj.setDownLoadNum("0");
			scwj.setViewNum("0");
			scwj.setFiletype(filetype);
			scwj.setMuluid(muluid);
			scwj.setKcmc("C语言程序设计");//后期需要做活，用户上传资料时，选择课程名称
			if (extName.equals(".zip")||extName.equals(".rar")) {
				thumbnails="upload/Thumbnails/yasuobao.jpg";
			}
			
			System.out.println("fileType:"+fileType);
			if (fileType.equals("音频")) {
				thumbnails="upload/Thumbnails/yinpin.jpg";
			}
			if (fileType.equals("图纸")) {
				thumbnails="upload/Thumbnails/cad.png";
			}
			scwj.setThumbnails(thumbnails);
			scwj.setFilename(ofileName);
			this.baseservice.save(scwj);
			String hql = "select max(no) from Scwj where filename='" + ofileName + "'";
			int j1 = this.baseservice.findHql(Integer.class, hql).get(0);//获取最大的顺序号
			no = String.valueOf(j1);

			Map<String, String> map=new HashMap<String, String>();
			map.put("fileName", ofileName);
			map.put("no", no);
			map.put("scrq", nowTimeStr);
			map.put("lxm", lxm);
			map.put("jxnr", "jxnr");
			map.put("filePath", filePath);
			ScwjData.setList(userid, map);
			
			
		
	}
           System.out.println("ceshishis:"+ScwjData.getList(userid));
		return SUCCESS;  
	}  
	@Action(value="uploadnext",results={@Result(name="success",type="json")})
	public String uploadnext() throws Exception  
	{  
		HttpSession hs = ServletActionContext.getRequest().getSession();
		String userid = (String)hs.getAttribute("uid");
		rows=ScwjData.getList(userid);
		System.out.println("rwos:"+rows);
		ScwjData.remove(userid);
		return SUCCESS;  
	}
	
	@Action(value="coverSwf",results={@Result(name="success",type="json")})
	public String coverSwf() throws Exception  
	{  
//		HttpSession hs = ServletActionContext.getRequest().getSession();
//		String userid = (String)hs.getAttribute("uid");
//		List<Map<String, String>> fileroots=FileRootData.getList(userid);
//		System.err.println("fileroots:"+fileroots);
//		if (fileroots==null||fileroots.size()==0) {
//			System.out.println("没有需要进行转换的文本文件");
//		}else{
//			ConvertToPdfServiceImple.cover(fileroots);
//			FileRootData.remove(userid); 
//		}
//
//		List<Map<String, String>> fileroots1=FileRootData.getList1(userid);
//		if (fileroots1==null||fileroots1.size()==0) {
//			System.out.println("没有需要进行转换的音频文件");
//		}else{
//			VideoPictureUtil videoPictureUtil=new VideoPictureUtil();
//			for (int i = 0; i < fileroots1.size(); i++) {
//				Map<String, String>map=fileroots1.get(i);
//				String inputfile=map.get("inputfile");
//				String thumbnailspath=map.get("thumbnail");
//			
//				videoPictureUtil.getVideoImage(inputfile, thumbnailspath);
//			}
//			
//			ConvertToFlvServiceImple convertSingleVideo=new ConvertToFlvServiceImple();
//			
//			for (int i = 0; i < fileroots1.size(); i++) {
//				Map<String, String>map=fileroots1.get(i);
//				String inputfile=map.get("inputfile");
//				String outputFile=map.get("outputfile");
//			
//				convertSingleVideo.convert(inputfile, outputFile);
//				
//			}
//
//			
//			
//
//			FileRootData.remove1(userid); 
//		}

		return SUCCESS;  
	}
	
	

}  