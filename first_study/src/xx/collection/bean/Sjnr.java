package xx.collection.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Sjnr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sjnr", catalog = "study3")
public class Sjnr implements java.io.Serializable {

	// Fields

	private Integer sjno;
	private Jxjh jxjh;
	private CourseChapter courseChapter;
	private String pdsz;
	private String xzsz;
	private String tksz;
	private String cztsz;
	private boolean dq;
	private Date cjsj;
	private String zxx;
	private Sjfx sjfx;

	// Constructors

	/** default constructor */
	public Sjnr() {
	}

	/** minimal constructor */
	public Sjnr(Integer sjno) {
		this.sjno = sjno;
	}

	/** full constructor */
	public Sjnr(Integer sjno, Jxjh jxjh, CourseChapter courseChapter,
			String pdsz, String xzsz, String tksz, String cztsz, boolean dq,
			Date cjsj, String zxx, Sjfx sjfx) {
		this.sjno = sjno;
		this.jxjh = jxjh;
		this.courseChapter = courseChapter;
		this.pdsz = pdsz;
		this.xzsz = xzsz;
		this.tksz = tksz;
		this.cztsz = cztsz;
		this.dq = dq;
		this.cjsj = cjsj;
		this.zxx = zxx;
		this.sjfx = sjfx;
	}

	// Property accessors
	@Id
	@Column(name = "sjno", unique = true, nullable = false)
	public Integer getSjno() {
		return this.sjno;
	}

	public void setSjno(Integer sjno) {
		this.sjno = sjno;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jxjhid")
	public Jxjh getJxjh() {
		return this.jxjh;
	}

	public void setJxjh(Jxjh jxjh) {
		this.jxjh = jxjh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zbh")
	public CourseChapter getCourseChapter() {
		return this.courseChapter;
	}

	public void setCourseChapter(CourseChapter courseChapter) {
		this.courseChapter = courseChapter;
	}

	@Column(name = "pdsz", length = 1000)
	public String getPdsz() {
		return this.pdsz;
	}

	public void setPdsz(String pdsz) {
		this.pdsz = pdsz;
	}

	@Column(name = "xzsz", length = 1000)
	public String getXzsz() {
		return this.xzsz;
	}

	public void setXzsz(String xzsz) {
		this.xzsz = xzsz;
	}

	@Column(name = "tksz", length = 500)
	public String getTksz() {
		return this.tksz;
	}

	public void setTksz(String tksz) {
		this.tksz = tksz;
	}

	@Column(name = "cztsz", length = 1000)
	public String getCztsz() {
		return this.cztsz;
	}

	public void setCztsz(String cztsz) {
		this.cztsz = cztsz;
	}

	@Column(name = "dq")
	public boolean getDq() {
		return this.dq;
	}

	public void setDq(boolean dq) {
		this.dq = dq;
	}

	@Column(name = "cjsj", length = 19)
	public Date getCjsj() {
		return this.cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	@Column(name = "zxx")
	public String getZxx() {
		return this.zxx;
	}

	public void setZxx(String zxx) {
		this.zxx = zxx;
	}


	@OneToOne(fetch = FetchType.EAGER, mappedBy = "sjnr")
	public Sjfx getSjfx() {
		return this.sjfx;
	}

	public void setSjfx(Sjfx sjfx) {
		this.sjfx = sjfx;
	}

}