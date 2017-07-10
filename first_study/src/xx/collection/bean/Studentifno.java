package xx.collection.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Studentifno entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "studentifno", catalog = "study3")
public class Studentifno implements java.io.Serializable {

	// Fields

	private String SNo;
	private Bjxx bjxx;
	private Userinfo userinfo;
	private String SName;
	private String xszw;
	private String SSex;
	private Date rxny;
	private String email;
	private String handphone;

	// Constructors

	/** default constructor */
	public Studentifno() {
	}

	/** minimal constructor */
	public Studentifno(String SNo) {
		this.SNo = SNo;
	}

	/** full constructor */
	public Studentifno(String SNo, Bjxx bjxx, Userinfo userinfo, String SName,
			String xszw, String SSex, Date rxny, String email, String handphone) {
		this.SNo = SNo;
		this.bjxx = bjxx;
		this.userinfo = userinfo;
		this.SName = SName;
		this.xszw = xszw;
		this.SSex = SSex;
		this.rxny = rxny;
		this.email = email;
		this.handphone = handphone;
	}

	// Property accessors
	@Id
	@Column(name = "s_no", unique = true, nullable = false, length = 16)
	public String getSNo() {
		return this.SNo;
	}

	public void setSNo(String SNo) {
		this.SNo = SNo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bjbh")
	public Bjxx getBjxx() {
		return this.bjxx;
	}

	public void setBjxx(Bjxx bjxx) {
		this.bjxx = bjxx;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "s_name", length = 50)
	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	@Column(name = "xszw", length = 20)
	public String getXszw() {
		return this.xszw;
	}

	public void setXszw(String xszw) {
		this.xszw = xszw;
	}

	@Column(name = "s_sex", length = 1)
	public String getSSex() {
		return this.SSex;
	}

	public void setSSex(String SSex) {
		this.SSex = SSex;
	}

	@Column(name = "rxny", length = 19)
	public Date getRxny() {
		return this.rxny;
	}

	public void setRxny(Date rxny) {
		this.rxny = rxny;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "handphone", length = 12)
	public String getHandphone() {
		return this.handphone;
	}

	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}

}