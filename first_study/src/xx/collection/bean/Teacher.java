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
 * Teacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher", catalog = "study3")
public class Teacher implements java.io.Serializable {

	// Fields

	private String jsbh;
	private Xuyan xuyan;
	private Mz mz;
	private Jslb jslb;
	private Userinfo userinfo;
	private String jsxm;
	private String jspy;
	private String jsxb;
	private String jsjg;
	private String zzmm;
	private Date dtsj;
	private Date rjny;
	private Date cjgzsj;
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
			Userinfo userinfo, String jsxm, String jspy, String jsxb,
			String jsjg, String zzmm, Date dtsj, Date rjny, Date cjgzsj,
			String sbd, String jssfz, String homephone, String officephone,
			String handphone, String email, String jszp) {
		this.jsbh = jsbh;
		this.xuyan = xuyan;
		this.mz = mz;
		this.jslb = jslb;
		this.userinfo = userinfo;
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
	@Column(name = "jsbh", unique = true, nullable = false, length = 20)
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
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "jsxm", length = 50)
	public String getJsxm() {
		return this.jsxm;
	}

	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

	@Column(name = "jspy", length = 3)
	public String getJspy() {
		return this.jspy;
	}

	public void setJspy(String jspy) {
		this.jspy = jspy;
	}

	@Column(name = "jsxb", length = 1)
	public String getJsxb() {
		return this.jsxb;
	}

	public void setJsxb(String jsxb) {
		this.jsxb = jsxb;
	}

	@Column(name = "jsjg", length = 10)
	public String getJsjg() {
		return this.jsjg;
	}

	public void setJsjg(String jsjg) {
		this.jsjg = jsjg;
	}

	@Column(name = "zzmm", length = 20)
	public String getZzmm() {
		return this.zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	@Column(name = "dtsj", length = 19)
	public Date getDtsj() {
		return this.dtsj;
	}

	public void setDtsj(Date dtsj) {
		this.dtsj = dtsj;
	}

	@Column(name = "rjny", length = 19)
	public Date getRjny() {
		return this.rjny;
	}

	public void setRjny(Date rjny) {
		this.rjny = rjny;
	}

	@Column(name = "cjgzsj", length = 19)
	public Date getCjgzsj() {
		return this.cjgzsj;
	}

	public void setCjgzsj(Date cjgzsj) {
		this.cjgzsj = cjgzsj;
	}

	@Column(name = "sbd", length = 8)
	public String getSbd() {
		return this.sbd;
	}

	public void setSbd(String sbd) {
		this.sbd = sbd;
	}

	@Column(name = "jssfz", length = 18)
	public String getJssfz() {
		return this.jssfz;
	}

	public void setJssfz(String jssfz) {
		this.jssfz = jssfz;
	}

	@Column(name = "homephone", length = 12)
	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	@Column(name = "officephone", length = 12)
	public String getOfficephone() {
		return this.officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	@Column(name = "handphone", length = 11)
	public String getHandphone() {
		return this.handphone;
	}

	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}

	@Column(name = "email", length = 100)
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