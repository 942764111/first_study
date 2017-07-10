/*
 *@(#)xx.collection.bean
 *@GroupInfo.java.java  
 *@����ʱ��:2011-8-22����09:54:25
 *@���ߣ�tongkesong
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @GroupInfo <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */
@Entity
@Table(name = "groupinfo", catalog = "study3")
public class GroupInfo implements java.io.Serializable {
	
	private Integer id;
	private Userinfo userinfo;
	private String groupname;
	private String memberg;
	private String memberid;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	@Column(name = "groupname", length = 10)
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	@Column(name = "memberg", length = 10)
	public String getMemberg() {
		return memberg;
	}
	public void setMemberg(String memberg) {
		this.memberg = memberg;
	}
	@Column(name = "memberid", length = 11)
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	
	
	
   
}
