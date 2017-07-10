/*
 *@(#)xx.kgt.bean
 *@Dtjl.java.java  
 *@创建时间:2012-3-31下午08:53:24
 *@作者：张晓莉
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.kgt.bean;

import javax.persistence.Entity;

/**
 * @Dtjl <code>{类名称}</code>
 * @author  {张晓莉}
 * @version {版本,常用时间代替}
 * @{功能描述:答题记录} 
 */
@Entity
public class Dtjl {

	//试卷名称
	private String papername;
	
	//测试者名称
	private String studentname;

	/**
	 * @return the papername
	 */
	public String getPapername() {
		return papername;
	}

	/**
	 * @param papername the papername to set
	 */
	public void setPapername(String papername) {
		this.papername = papername;
	}

	/**
	 * @return the studentname
	 */
	public String getStudentname() {
		return studentname;
	}

	/**
	 * @param studentname the studentname to set
	 */
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	
	
}
