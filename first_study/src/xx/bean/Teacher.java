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
 * Teacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher", schema = "dbo", catalog = "study2")
public class Teacher implements java.io.Serializable {

	// Fields

	private String jsbh;
	private Xuyan xuyan;
	private Mz mz;
	private Jslb jslb;
	private UserInfo userInfo;
	private String jsxm;
	private String jspy;
	private String jsxb;
	private String jsjg;
	private String zzmm;
	private Timestamp dtsj;
	private Timestamp rjny;
	private Timestamp cjgzsj;
	private String sbd;
	private String jssfz;
	private String homephone;
	private String officephone;
	private String handphone;
	private String email;
	private String jszp;
	

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(String jsbh) {
		this.jsbh = jsbh;
	}

	/** full constructor */
	public Teacher(String jsbh, Xuyan xuyan, Mz mz, Jslb jslb,
			UserInfo userInfo, String jsxm, String jspy, String jsxb,
			String jsjg, String zzmm, Timestamp dtsj, Timestamp rjny,
			Timestamp cjgzsj, String sbd, String jssfz, String homephone,
			String officephone, String handphone, String email, String jszp) {
		this.jsbh = jsbh;
		this.xuyan = xuyan;
		this.mz = mz;
		this.jslb = jslb;
		this.userInfo = userInfo;
		this.jsxm = jsxm;
		this.jspy = jspy;
		this.jsxb = jsxb;
		this.jsjg = jsjg;
		this.zzmm = zzmm;
		this.dtsj = dtsj;
		this.rjny = rjny;
		this.cjgzsj = cjgzsj;
		this.sbd = sbd;
		this.jssfz = jssfz;
		this.homephone = homephone;
		this.officephone = officephone;
		this.handphone = handphone;
		this.email = email;
		this.jszp = jszp;
		
	}

	// Property accessors
	@Id
	@Column(name = "jsbh", unique = true, nullable = false)
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jsxy")
	public Xuyan getXuyan() {
		return this.xuyan;
	}

	public void setXuyan(Xuyan xuyan) {
		this.xuyan = xuyan;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jsmz")
	public Mz getMz() {
		return this.mz;
	}

	public void setMz(Mz mz) {
		this.mz = mz;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jslb")
	public Jslb getJslb() {
		return this.jslb;
	}

	public void setJslb(Jslb jslb) {
		this.jslb = jslb;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "jsxm")
	public String getJsxm() {
		return this.jsxm;
	}

	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

	@Column(name = "jspy")
	public String getJspy() {
		return this.jspy;
	}

	public void setJspy(String jspy) {
		this.jspy = jspy;
	}

	@Column(name = "jsxb")
	public String getJsxb() {
		return this.jsxb;
	}

	public void setJsxb(String jsxb) {
		this.jsxb = jsxb;
	}

	@Column(name = "jsjg")
	public String getJsjg() {
		return this.jsjg;
	}

	public void setJsjg(String jsjg) {
		this.jsjg = jsjg;
	}

	@Column(name = "zzmm")
	public String getZzmm() {
		return this.zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	@Column(name = "dtsj", length = 16)
	public Timestamp getDtsj() {
		return this.dtsj;
	}

	public void setDtsj(Timestamp dtsj) {
		this.dtsj = dtsj;
	}

	@Column(name = "rjny", length = 16)
	public Timestamp getRjny() {
		return this.rjny;
	}

	public void setRjny(Timestamp rjny) {
		this.rjny = rjny;
	}

	@Column(name = "cjgzsj", length = 16)
	public Timestamp getCjgzsj() {
		return this.cjgzsj;
	}

	public void setCjgzsj(Timestamp cjgzsj) {
		this.cjgzsj = cjgzsj;
	}

	@Column(name = "sbd")
	public String getSbd() {
		return this.sbd;
	}

	public void setSbd(String sbd) {
		this.sbd = sbd;
	}

	@Column(name = "jssfz")
	public String getJssfz() {
		return this.jssfz;
	}

	public void setJssfz(String jssfz) {
		this.jssfz = jssfz;
	}

	@Column(name = "homephone")
	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	@Column(name = "officephone")
	public String getOfficephone() {
		return this.officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	@Column(name = "handphone")
	public String getHandphone() {
		return this.handphone;
	}

	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "jszp")
	public String getJszp() {
		return this.jszp;
	}

	public void setJszp(String jszp) {
		this.jszp = jszp;
	}

	

}