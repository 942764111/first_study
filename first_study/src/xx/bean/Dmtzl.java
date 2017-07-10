package xx.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dmtzl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dmtzl", catalog = "study2")
public class Dmtzl implements java.io.Serializable {

	// Fields

	private String zlbh;
	private String userId;
	private String lxm;
	private String zlmc;
	private String filename;
	private String zlms;
	private String zlly;
	private String zlscm;
	private String zmfilename;
	private Timestamp scrq;
	private Integer llcs;
	private Integer cssl;
	private Integer changdu;

	// Constructors

	/** default constructor */
	public Dmtzl() {
	}

	/** minimal constructor */
	public Dmtzl(String zlbh) {
		this.zlbh = zlbh;
	}

	/** full constructor */
	public Dmtzl(String zlbh, String userId, String lxm, String zlmc,
			String filename, String zlms, String zlly, String zlscm,
			String zmfilename, Timestamp scrq, Integer llcs, Integer cssl,
			Integer changdu) {
		this.zlbh = zlbh;
		this.userId = userId;
		this.lxm = lxm;
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
	}

	// Property accessors
	@Id
	@Column(name = "zlbh", unique = true, nullable = false, length = 16)
	public String getZlbh() {
		return this.zlbh;
	}

	public void setZlbh(String zlbh) {
		this.zlbh = zlbh;
	}

	@Column(name = "UserId", length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "lxm", length = 8)
	public String getLxm() {
		return this.lxm;
	}

	public void setLxm(String lxm) {
		this.lxm = lxm;
	}

	@Column(name = "zlmc", length = 30)
	public String getZlmc() {
		return this.zlmc;
	}

	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}

	@Column(name = "filename", length = 20)
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

	@Column(name = "zlly", length = 10)
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

	@Column(name = "zmfilename", length = 20)
	public String getZmfilename() {
		return this.zmfilename;
	}

	public void setZmfilename(String zmfilename) {
		this.zmfilename = zmfilename;
	}

	@Column(name = "scrq", length = 19)
	public Timestamp getScrq() {
		return this.scrq;
	}

	public void setScrq(Timestamp scrq) {
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

}