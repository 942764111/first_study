package xx.bean;

import java.sql.Timestamp;
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
@Table(name = "studentifno", schema = "dbo", catalog = "study2")
public class Studentifno implements java.io.Serializable {

	// Fields

	private String SNo;
	private Bjxx bjxx;
	private UserInfo userInfo;
	private String SName;
	private String xszw;
	private String SSex;
	private Timestamp rxny;
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
	public Studentifno(String SNo, Bjxx bjxx, UserInfo userInfo, String SName,
			String xszw, String SSex, Timestamp rxny, String email,
			String handphone) {
		this.SNo = SNo;
		this.bjxx = bjxx;
		this.userInfo = userInfo;
		this.SName = SName;
		this.xszw = xszw;
		this.SSex = SSex;
		this.rxny = rxny;
		this.email = email;
		this.handphone = handphone;
	}

	// Property accessors
	@Id
	@Column(name = "s_no", unique = true, nullable = false)
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
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "s_name")
	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	@Column(name = "xszw")
	public String getXszw() {
		return this.xszw;
	}

	public void setXszw(String xszw) {
		this.xszw = xszw;
	}

	@Column(name = "s_sex")
	public String getSSex() {
		return this.SSex;
	}

	public void setSSex(String SSex) {
		this.SSex = SSex;
	}

	@Column(name = "rxny", length = 16)
	public Timestamp getRxny() {
		return this.rxny;
	}

	public void setRxny(Timestamp rxny) {
		this.rxny = rxny;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "handphone")
	public String getHandphone() {
		return this.handphone;
	}

	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}

}