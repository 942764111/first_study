package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Xz_sim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xz", catalog = "study3")
public class Xz_sim implements java.io.Serializable {

	// Fields

	private Integer th;
	private Integer zsdbh;
	private Integer zbh;
	private Integer CId;
	private String tg;
	private String xx1;
	private String xx2;
	private String xx3;
	private String xx4;
	private Integer ddx;
	private String da;
	private Integer csrcs;
	private Integer zqrcs;
	private Integer nyd;
	private String md5;

	// Constructors

	/** default constructor */
	public Xz_sim() {
	}

	/** full constructor */
	public Xz_sim(Integer zsdbh, Integer zbh, Integer CId, String tg,
			String xx1, String xx2, String xx3, String xx4, Integer ddx,
			String da, Integer csrcs, Integer zqrcs, Integer nyd, String md5) {
		this.zsdbh = zsdbh;
		this.zbh = zbh;
		this.CId = CId;
		this.tg = tg;
		this.xx1 = xx1;
		this.xx2 = xx2;
		this.xx3 = xx3;
		this.xx4 = xx4;
		this.ddx = ddx;
		this.da = da;
		this.csrcs = csrcs;
		this.zqrcs = zqrcs;
		this.nyd = nyd;
		this.md5 = md5;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "th", unique = true, nullable = false)
	public Integer getTh() {
		return this.th;
	}

	public void setTh(Integer th) {
		this.th = th;
	}

	@Column(name = "zsdbh")
	public Integer getZsdbh() {
		return this.zsdbh;
	}

	public void setZsdbh(Integer zsdbh) {
		this.zsdbh = zsdbh;
	}

	@Column(name = "zbh")
	public Integer getZbh() {
		return this.zbh;
	}

	public void setZbh(Integer zbh) {
		this.zbh = zbh;
	}

	@Column(name = "c_id")
	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	@Column(name = "tg", length = 300)
	public String getTg() {
		return this.tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

	@Column(name = "xx1", length = 200)
	public String getXx1() {
		return this.xx1;
	}

	public void setXx1(String xx1) {
		this.xx1 = xx1;
	}

	@Column(name = "xx2", length = 200)
	public String getXx2() {
		return this.xx2;
	}

	public void setXx2(String xx2) {
		this.xx2 = xx2;
	}

	@Column(name = "xx3", length = 200)
	public String getXx3() {
		return this.xx3;
	}

	public void setXx3(String xx3) {
		this.xx3 = xx3;
	}

	@Column(name = "xx4", length = 200)
	public String getXx4() {
		return this.xx4;
	}

	public void setXx4(String xx4) {
		this.xx4 = xx4;
	}

	@Column(name = "ddx")
	public Integer getDdx() {
		return this.ddx;
	}

	public void setDdx(Integer ddx) {
		this.ddx = ddx;
	}

	@Column(name = "da", length = 20)
	public String getDa() {
		return this.da;
	}

	public void setDa(String da) {
		this.da = da;
	}

	@Column(name = "csrcs")
	public Integer getCsrcs() {
		return this.csrcs;
	}

	public void setCsrcs(Integer csrcs) {
		this.csrcs = csrcs;
	}

	@Column(name = "zqrcs")
	public Integer getZqrcs() {
		return this.zqrcs;
	}

	public void setZqrcs(Integer zqrcs) {
		this.zqrcs = zqrcs;
	}

	@Column(name = "nyd")
	public Integer getNyd() {
		return this.nyd;
	}

	public void setNyd(Integer nyd) {
		this.nyd = nyd;
	}

	@Column(name = "md5", length = 200)
	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}