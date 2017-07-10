/*
 *@(#)xx.download.utils
 *@FileUtils.java.java  
 *@创建时间:2012-2-22上午09:12:59
 *@作者：hp
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.download.utils;

import java.io.File;

import javax.persistence.Entity;

/**
 * @FileUtils <code>{类名称}</code>
 * @author  {gq}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
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
	//判断文件是否已经存在，1表示已经存在，0表示不存在
	public int isExists(){
		File f=new File(path+File.separator+filename);
		if(f.exists()){
			return 1;
		}else{
			return 0;
		}
	}
	//成功添加了文件的，返回true，失败时，返回false
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
