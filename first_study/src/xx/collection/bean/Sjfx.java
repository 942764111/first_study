package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Sjfx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sjfx", catalog = "study3")
public class Sjfx implements java.io.Serializable {

	// Fields

	private Integer sjno;
	private Sjnr sjnr;
	private String sjfx;

	// Constructors

	/** default constructor */
	public Sjfx() {
	}

	/** minimal constructor */
	public Sjfx(Integer sjno, Sjnr sjnr) {
		this.sjno = sjno;
		this.sjnr = sjnr;
	}

	/** full constructor */
	public Sjfx(Integer sjno, Sjnr sjnr, String sjfx) {
		this.sjno = sjno;
		this.sjnr = sjnr;
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

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Sjnr getSjnr() {
		return this.sjnr;
	}

	public void setSjnr(Sjnr sjnr) {
		this.sjnr = sjnr;
	}

	@Column(name = "sjfx", length = 600)
	public String getSjfx() {
		return this.sjfx;
	}

	public void setSjfx(String sjfx) {
		this.sjfx = sjfx;
	}

}