package xx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cztd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cztd", catalog = "study2")
public class Cztd implements java.io.Serializable {

	// Fields

	private String th;
	private String tg;
	private Integer dtfz;

	// Constructors

	/** default constructor */
	public Cztd() {
	}

	/** minimal constructor */
	public Cztd(String th) {
		this.th = th;
	}

	/** full constructor */
	public Cztd(String th, String tg, Integer dtfz) {
		this.th = th;
		this.tg = tg;
		this.dtfz = dtfz;
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

	@Column(name = "tg", length = 300)
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

}