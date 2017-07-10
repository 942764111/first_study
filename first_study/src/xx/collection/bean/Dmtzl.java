package xx.collection.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Dmtzl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dmtzl", catalog = "study3")
public class Dmtzl implements java.io.Serializable {

	// Fields

	private Integer zlbh;
	private Wjlx wjlx;
	private Userinfo userinfo;
	private String zlmc;
	private String filename;
	private String zlms;
	private String zlly;
	private String zlscm;
	private String zmfilename;
	private Date scrq;
	private Integer llcs;
	private Integer cssl;
	private Integer changdu;
	private String zlmd5;
	private Integer totalNum;
	

	// Constructors

	/** default constructor */
	public Dmtzl() {
	}

	/** full constructor */
	public Dmtzl(Wjlx wjlx, Userinfo userinfo, String zlmc, String filename,
			String zlms, String zlly, String zlscm, String zmfilename,
			Date scrq, Integer llcs, Integer cssl, Integer changdu,
			String zlmd5, Integer totalNum) {
		this.wjlx = wjlx;
		this.userinfo = userinfo;
		this.zlmc = zlmc;
		this.filename = filename;
		this.zlms = zlms;
		this.zlly = zlly;
		this.zlscm = zlscm;
		this.zmfilename = zmfilename;
		this.scrq = scrq;
		this.llcs = llcs;
		this.cssl = cssl;
		this.changdu = changdu;
		this.zlmd5 = zlmd5;
		this.totalNum = totalNum;
		
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "zlbh", unique = true, nullable = false)
	public Integer getZlbh() {
		return this.zlbh;
	}

	public void setZlbh(Integer zlbh) {
		this.zlbh = zlbh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lxm")
	public Wjlx getWjlx() {
		return this.wjlx;
	}

	public void setWjlx(Wjlx wjlx) {
		this.wjlx = wjlx;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "zlmc", length = 30)
	public String getZlmc() {
		return this.zlmc;
	}

	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}

	@Column(name = "filename", length = 80)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "zlms", length = 200)
	public String getZlms() {
		return this.zlms;
	}

	public void setZlms(String zlms) {
		this.zlms = zlms;
	}

	@Column(name = "zlly", length = 80)
	public String getZlly() {
		return this.zlly;
	}

	public void setZlly(String zlly) {
		this.zlly = zlly;
	}

	@Column(name = "zlscm", length = 100)
	public String getZlscm() {
		return this.zlscm;
	}

	public void setZlscm(String zlscm) {
		this.zlscm = zlscm;
	}

	@Column(name = "zmfilename", length = 80)
	public String getZmfilename() {
		return this.zmfilename;
	}

	public void setZmfilename(String zmfilename) {
		this.zmfilename = zmfilename;
	}

	@Column(name = "scrq", length = 19)
	public Date getScrq() {
		return this.scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}

	@Column(name = "llcs")
	public Integer getLlcs() {
		return this.llcs;
	}

	public void setLlcs(Integer llcs) {
		this.llcs = llcs;
	}

	@Column(name = "cssl")
	public Integer getCssl() {
		return this.cssl;
	}

	public void setCssl(Integer cssl) {
		this.cssl = cssl;
	}

	@Column(name = "changdu")
	public Integer getChangdu() {
		return this.changdu;
	}

	public void setChangdu(Integer changdu) {
		this.changdu = changdu;
	}

	@Column(name = "zlmd5", length = 200)
	public String getZlmd5() {
		return this.zlmd5;
	}

	public void setZlmd5(String zlmd5) {
		this.zlmd5 = zlmd5;
	}

	/**
	 * @return the totalNum
	 */
	@Column(name = "totalNum")
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}