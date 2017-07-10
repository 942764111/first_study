package com.easysearching.web.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class FileDownloadUtil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
	}

	public void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决中文乱码问题

		String filePath = request.getParameter("filePath");

		if ("".equals(filePath) || filePath == null) {
			return;
		}

		System.out.println("转码前filePath:" + filePath);

		filePath = new String(filePath.getBytes("ISO8859-1"), "UTF-8");

		System.out.println("转码后filePath:" + filePath);

		response.setContentType("text/html;charset=UTF8");
		// filePath = new String(EncodingUtil.ConvertISOToGBK(filePath));
		System.out.println("设置编码后filePath:" + filePath);

		String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);

		System.out.println("fileName:" + fileName);
		// 创建file对象

		File file = new File(filePath);

		// 设置response的编码方式
		response.setContentType("application/x-msdownload");
		response.setContentLength((int) file.length());
		System.out.println("fileName:" + fileName);
		System.out.println("filePath:" + filePath);
		// 设置附加文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] b = new byte[1024];
		long k = 0;
		FileInputStream fis = null;
		BufferedInputStream buf = null;
		OutputStream outputStream = null;
		try {
			fis = new FileInputStream(file);
			buf = new BufferedInputStream(fis);
			outputStream = response.getOutputStream();
			while (k < file.length()) {
				int i = buf.read(b, 0, 1024);
				k += i;
				outputStream.write(b, 0, i);
			}
			outputStream.flush();
		} catch (Exception e) {
			System.out.println("用户取消下载!");
			e.printStackTrace();
		} finally {
			buf.close();
			fis.close();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = request.getParameter("filePath");
		if ("".equals(filePath) || filePath == null) {
			return;
		}
		filePath = new String(filePath.getBytes("ISO8859-1"), "UTF-8");		
		SmartUpload smartUpload= new SmartUpload();
		smartUpload.initialize(this.getServletConfig(), request, response);
		smartUpload.setContentDisposition(null); 
		try {
			smartUpload.downloadFile(filePath);
		} catch (IOException ioe) {
			System.out.println("用户取消下载");
		} catch (SmartUploadException sme) {
			System.out.println(sme.getMessage());
		} catch (ServletException se) {
			System.out.println(se.getMessage());
		}
		return;
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}
