package xx.collection.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cztd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cztd", catalog = "study3")
public class Cztd implements java.io.Serializable {

	// Fields

	private Integer th;
	private String tg;
	private Integer dtfz;
	private String md5;


	// Constructors

	/** default constructor */
	public Cztd() {
	}

	/** minimal constructor */
	public Cztd(Integer th) {
		this.th = th;
	}

	/** full constructor */
	public Cztd(Integer th, String tg, Integer dtfz, String md5) {
		this.th = th;
		this.tg = tg;
		this.dtfz = dtfz;
		this.md5 = md5;

	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "th", unique = true, nullable = false)
	public Integer getTh() {
		return this.th;
	}

	public void setTh(Integer th) {
		this.th = th;
	}

	@Column(name = "tg", length = 800)
	public String getTg() {
		return this.tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

	@Column(name = "dtfz")
	public Integer getDtfz() {
		return this.dtfz;
	}

	public void setDtfz(Integer dtfz) {
		this.dtfz = dtfz;
	}

	@Column(name = "md5", length = 16)
	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}


}