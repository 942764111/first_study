package xx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Pd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pd", catalog = "study2")
public class Pd implements java.io.Serializable {

	// Fields

	private String th;
	private Integer zsdbh;
	private Integer zbh;
	private Integer CId;
	private String tg;
	private Integer da;
	private String zsd;
	private Integer csrcs;
	private Integer zqrcs;
	private Integer nyd;

	// Constructors

	/** default constructor */
	public Pd() {
	}

	/** minimal constructor */
	public Pd(String th) {
		this.th = th;
	}

	/** full constructor */
	public Pd(String th, Integer zsdbh, Integer zbh, Integer CId, String tg,
			Integer da, String zsd, Integer csrcs, Integer zqrcs, Integer nyd) {
		this.th = th;
		this.zsdbh = zsdbh;
		this.zbh = zbh;
		this.CId = CId;
		this.tg = tg;
		this.da = da;
		this.zsd = zsd;
		this.csrcs = csrcs;
		this.zqrcs = zqrcs;
		this.nyd = nyd;
	}

	// Property accessors
	@Id
	@Column(name = "th", unique = true, nullable = false, length = 16)
	public String getTh() {
		return this.th;
	}

	public void setTh(String th) {
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

	@Column(name = "tg", length = 200)
	public String getTg() {
		return this.tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

	@Column(name = "da")
	public Integer getDa() {
		return this.da;
	}

	public void setDa(Integer da) {
		this.da = da;
	}

	@Column(name = "zsd", length = 8)
	public String getZsd() {
		return this.zsd;
	}

	public void setZsd(String zsd) {
		this.zsd = zsd;
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

}