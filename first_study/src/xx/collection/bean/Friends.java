package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Friends entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "friends", catalog = "study3")
public class Friends implements java.io.Serializable {

	// Fields
	private Integer no;
	private String friendid;
	private String friendname;
	private String mark;
    private String userid;
    
	@Id
	@GeneratedValue
	@Column(name = "no",unique=true ,nullable = false)
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}
    
    @Column(name = "userid", length = 10)
    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	// Constructors


	
	// Property accessors



	
	
	@Column(name = "friendid")
	public String getfriendid() {
		return this.friendid;
	}

	public void setfriendid(String friendid) {
		this.friendid = friendid;
	}

	@Column(name = "friendname", length = 10)
	public String getfriendname() {
		return this.friendname;
	}

	public void setfriendname(String friendname) {
		this.friendname = friendname;
	}

	@Column(name = "mark", length = 100)
	public String getmark() {
		return this.mark;
	}

	public void setmark(String mark) {
		this.mark = mark;
	}

}