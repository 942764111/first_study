package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tk", catalog = "study3")
public class Tk implements java.io.Serializable {

	// Fields

	private Integer th;
	private Zsd zsd;
	private String tg;
	private String da;
	private Integer csrcs;
	private Integer zqrcs;
	private Integer nyd;
	private String md5;

	// Constructors

	/** default constructor */
	public Tk() {
	}

	/** full constructor */
	public Tk(Zsd zsd, String tg, String da, Integer csrcs, Integer zqrcs,
			Integer nyd, String md5) {
		this.zsd = zsd;
		this.tg = tg;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "zsdbh", referencedColumnName = "zsdbh"),
			@JoinColumn(name = "zbh", referencedColumnName = "zbh"),
			@JoinColumn(name = "c_id", referencedColumnName = "c_id") })
	public Zsd getZsd() {
		return this.zsd;
	}

	public void setZsd(Zsd zsd) {
		this.zsd = zsd;
	}

	@Column(name = "tg", length = 300)
	public String getTg() {
		return this.tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

	@Column(name = "da", length = 200)
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