package com.easysearching.web.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.easysearching.configuration.ConfigurationCache;
import com.easysearching.configuration.ConfigurationConstant;
import com.easysearching.lucene.beans.FileBean;
import com.easysearching.lucene.dao.IndexDao;
import com.easysearching.lucene.factory.RamDirectoryFactory;
import com.easysearching.lucene.factory.RamIndexDaoFactory;
import com.easysearching.lucene.util.IndexUtil;
import com.easysearching.util.TimeUtil;
import com.easysearching.web.json.bean.ResponseObject;

public class FileUploadUtil extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileUploadUtil() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResponseObject responseObject = new ResponseObject();
		System.out.println("执行了");
		
		long size = 0;
		PrintWriter out = response.getWriter();
		// 允许上传的文件格式列表
		final String[] enableFileTypes = ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_UPLOAD_ENABLE_TYPE).split("\\|");

		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		factory.setSizeThreshold(Integer.parseInt(ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_UPLOAD_THRESHOLD_SIZE)));
		// 设置存放临时文件的目录
		factory.setRepository(new File(ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_UPLOAD_TEMP_PATH)));
		// 用以上工厂实例化上传组件
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 设置最大上传尺寸
		sfu.setSizeMax(Integer.parseInt(ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_UPLOAD_MAX_SIZE)));
		// 设置文件路径编码
		sfu.setHeaderEncoding("UTF-8");
		// 从request得到 所有 上传域的列表
		List<FileItem> fileItems = null;
		try {
			fileItems = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// 处理文件尺寸过大的异常
			if (e instanceof SizeLimitExceededException) {
				//JSUtil.back(out, "uploading size more than assign max upload size:"
				//		+ Integer.parseInt(ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_UPLOAD_MAX_SIZE)) / (1024 * 1024) + "MB");
				//out.print("{success:true,errorcode:'001',errorinfo:'"+Integer.parseInt(ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_UPLOAD_MAX_SIZE)) / (1024 * 1024) + "MB'}");
				responseObject.setSuccess(true);
				responseObject.setResponseCode("001");
				responseObject.setResponseInfo(Integer.parseInt(ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_UPLOAD_MAX_SIZE)) / (1024 * 1024)+"MB");
				out.print(JSONObject.fromObject(responseObject).toString());
				return;
			}
			e.printStackTrace();
		}

		if (fileItems == null || fileItems.size() == 0) {
			JSUtil.back(out, "please select the file you want to upload at first!");
			return;
		}

		String pubWriter = "anonymous";
		String pubDate = TimeUtil.getCurrentDate();
		File pubFile = null;
		for (FileItem fileItem : fileItems) {
			String path = null;

			// 得到当前文件
			// 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
			if (fileItem == null || fileItem.isFormField()) {
				if ("pubWriter".equals(fileItem.getFieldName())) {
					pubWriter = new String(fileItem.getString().getBytes("ISO-8859-1"),"utf-8");
					
				}

			} else {
				// 得到文件完整的路径
				path = fileItem.getName();
				System.out.println("path:" + path);
				// 得到文件的大小
				size = fileItem.getSize();

				if ("".equals(path) || size == 0) {
					JSUtil.back(out, "please select the file you want to upload at first!");
					return;
				}
				// 得到去除路径的文件名
				String fileName = path.substring(path.lastIndexOf("\\") + 1);

				System.out.println("fileName:" + fileName);

				// 得到文件的扩展名
				String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

				if (null == fileType) {
					JSUtil.back(out, "illegality type!");
					return;
				}

				// 拒绝接受规定文件格式之外的文件类型
				boolean isEnableFileType = false;
				for (int i = 0; i < enableFileTypes.length; i++) {
					if (enableFileTypes[i].equals(fileType)) {
						isEnableFileType = true;
						break;
					}
				}
				if (!isEnableFileType) {
					responseObject.setSuccess(true);
					responseObject.setResponseCode("002");
					out.print(JSONObject.fromObject(responseObject).toString());
					//out.print("{success:true,errorcode:'002'}");
					//JSUtil.back(out, "the file type is not allowed to upload");
					return;
				}
				
				String prefix = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + TimeUtil.getSystemDateTimeNumber();
				String newFilepath = ConfigurationCache.getConfiguration(ConfigurationConstant.FILE_UPLOAD_PATH) + "/" + prefix + "." + fileType;
				pubFile = new File(newFilepath);
				System.out.println("newFilePath:" + newFilepath);
				try {
					fileItem.write(new File(newFilepath));
				} catch (Exception e) {
					JSUtil.back(out, "a exception occured when uploading file!");
					e.printStackTrace();
				}

				// 将文件创建到索引数据库
				IndexDao indexDao = RamIndexDaoFactory.getInstance();
				FileBean fileBean = new FileBean();
				fileBean.setPubDate(pubDate);
				fileBean.setPubWriter(pubWriter);
				fileBean.setPubFile(pubFile);
				
				indexDao.save(fileBean);
				// indexDao.save(newFilepath);
				//将内存库中新加数据同步更新到索引库
				IndexUtil.addDocumentsFromRamToFS();
				//重新加载内存库
				RamDirectoryFactory.reloadRamDirectory();
 
				System.out.println("pubWriter:"+pubWriter);
				//System.out.println("{success:true,file:'"+fileName+"'}");
				//out.print("{success:true,errorcode:'000'}");
				//JSUtil.location(out, "It is successful to upload the file!", "upload.jsp");
				responseObject.setSuccess(true);
				responseObject.setResponseCode("000");
				System.out.println(JSONObject.fromObject(responseObject).toString());
				out.print(JSONObject.fromObject(responseObject).toString());
				return;
			}
		}

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

}
