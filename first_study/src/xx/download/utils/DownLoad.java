/*
 *@(#)xx.download.utils
 *@DownLoad.java.java  
 *@����ʱ��:2012-2-22����09:13:34
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.download.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.persistence.Entity;

/**
 * @DownLoad <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Entity
public class DownLoad {
	private String urlPath;
	private String path;
	private String filename;
	/**
	 * @return the urlPath
	 */
	public String getUrlPath() {
		return urlPath;
	}
	/**
	 * @param urlPath the urlPath to set
	 */
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the filename
	 */
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
	 * 
	 */
	public DownLoad() {
		
	}
	/**
	 * @param urlPath
	 * @param path
	 * @param filename
	 */
	public DownLoad(String urlPath, String path, String filename) {
		
		this.urlPath = urlPath;
		this.path = path;
		this.filename = filename;
	}
	//�ú�����������, -1�����������ļ�����, 0�����������ļ��ɹ�, 1�������ļ��Ѿ�����
	public int downLoadFiles(){
		FileUtils fileUtils=new FileUtils(path,filename); 
		if(fileUtils.isExists()==1){
			return 1;
		}else{
			FileInputStream ip=null;
			FileOutputStream op=null;
			File file=fileUtils.addFile();
			try {
				 ip=new FileInputStream(urlPath);
				 op=new FileOutputStream(file);
				byte buffer [] = new byte[4 * 1024];
				while((ip.read(buffer)) != -1){
					op.write(buffer);
				}
				op.flush();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}finally{
				try {
					op.close();
					ip.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
				
			}
			return 0;
		}
	}
	
	

}
