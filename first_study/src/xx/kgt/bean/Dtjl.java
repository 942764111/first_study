/*
 *@(#)xx.kgt.bean
 *@Dtjl.java.java  
 *@����ʱ��:2012-3-31����08:53:24
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.kgt.bean;

import javax.persistence.Entity;

/**
 * @Dtjl <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:�����¼} 
 */
@Entity
public class Dtjl {

	//�Ծ�����
	private String papername;
	
	//����������
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
