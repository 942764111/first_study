/*
 *@(#)xx.download.utils
 *@FileUtils.java.java  
 *@����ʱ��:2012-2-22����09:12:59
 *@���ߣ�hp
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.download.utils;

import java.io.File;

import javax.persistence.Entity;

/**
 * @FileUtils <code>{������}</code>
 * @author  {gq}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Entity
public class FileUtils {
	private String path;
	private String filename;
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
	public FileUtils() {
		
	}
	/**
	 * @param path
	 * @param filename
	 */
	public FileUtils(String path, String filename) {
		
		this.path = path;
		this.filename = filename;
	}
	//�ж��ļ��Ƿ��Ѿ����ڣ�1��ʾ�Ѿ����ڣ�0��ʾ������
	public int isExists(){
		File f=new File(path+File.separator+filename);
		if(f.exists()){
			return 1;
		}else{
			return 0;
		}
	}
	//�ɹ�������ļ��ģ�����true��ʧ��ʱ������false
	public File addFile(){
		File f=new File(path+File.separator+filename);
		try{
			 f.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return f;
	}
	

}
