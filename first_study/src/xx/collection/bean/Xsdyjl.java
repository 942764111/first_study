package xx.collection.bean;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Xsdyjl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xsdyjl", catalog = "study3")
public class Xsdyjl implements java.io.Serializable {

	// Fields

	private XsdyjlId id;
	private Sjnr sjnr;
	private Userinfo userinfo;
	private Date BTime;
	private Date ETime;
	private String pd;
	private String xzsz;
	private String cwpdsz;
	private String cwxzsz;
	private String cztcwsz;
	private String cztsz;

	// Constructors

	/** default constructor */
	public Xsdyjl() {
	}

	/** minimal constructor */
	public Xsdyjl(XsdyjlId id, Sjnr sjnr, Userinfo userinfo) {
		this.id = id;
		this.sjnr = sjnr;
		this.userinfo = userinfo;
	}

	/** full constructor */
	public Xsdyjl(XsdyjlId id, Sjnr sjnr, Userinfo userinfo, Date BTime,
			Date ETime, String pd, String xzsz, String cwpdsz, String cwxzsz,
			String cztcwsz,String cztsz) {
		this.id = id;
		this.sjnr = sjnr;
		this.userinfo = userinfo;
		this.BTime = BTime;
		this.ETime = ETime;
		this.pd = pd;
		this.xzsz = xzsz;
		this.cwpdsz = cwpdsz;
		this.cwxzsz = cwxzsz;
		this.cztcwsz = cztcwsz;
		this.cztsz=cztsz;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "UserId", nullable = false, length = 10)),
			@AttributeOverride(name = "sjno", column = @Column(name = "sjno", nullable = false)) })
	public XsdyjlId getId() {
		return this.id;
	}

	public void setId(XsdyjlId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sjno", nullable = false, insertable = false, updatable = false)
	public Sjnr getSjnr() {
		return this.sjnr;
	}

	public void setSjnr(Sjnr sjnr) {
		this.sjnr = sjnr;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId", nullable = false, insertable = false, updatable = false)
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "b_time", length = 19)
	public Date getBTime() {
		return this.BTime;
	}

	public void setBTime(Date BTime) {
		this.BTime = BTime;
	}

	@Column(name = "e_time", length = 19)
	public Date getETime() {
		return this.ETime;
	}

	public void setETime(Date ETime) {
		this.ETime = ETime;
	}

	@Column(name = "pd", length = 200)
	public String getPd() {
		return this.pd;
	}

	public void setPd(String pd) {
		this.pd = pd;
	}

	@Column(name = "xzsz", length = 200)
	public String getXzsz() {
		return this.xzsz;
	}

	public void setXzsz(String xzsz) {
		this.xzsz = xzsz;
	}

	@Column(name = "cwpdsz", length = 200)
	public String getCwpdsz() {
		return this.cwpdsz;
	}

	public void setCwpdsz(String cwpdsz) {
		this.cwpdsz = cwpdsz;
	}

	@Column(name = "cwxzsz", length = 200)
	public String getCwxzsz() {
		return this.cwxzsz;
	}

	public void setCwxzsz(String cwxzsz) {
		this.cwxzsz = cwxzsz;
	}

	@Column(name = "cztcwsz", length = 600)
	public String getCztcwsz() {
		return this.cztcwsz;
	}

	public void setCztcwsz(String cztcwsz) {
		this.cztcwsz = cztcwsz;
	}

	@Column(name = "cztsz", length = 600)
	public String getCztsz() {
		return cztsz;
	}

	public void setCztsz(String cztsz) {
		this.cztsz = cztsz;
	}
	

}