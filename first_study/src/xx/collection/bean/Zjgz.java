package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Zjgz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zjgz", catalog = "study3")
public class Zjgz implements java.io.Serializable {

	// Fields

	private Integer no;
	private Jie jie;
	private Integer tk;
	private Integer xz;
	private Integer pd;
	private Integer cz;
	private Integer rytbl;
	private Integer zdbl;
	private Integer ntbl;

	// Constructors

	/** default constructor */
	public Zjgz() {
	}

	/** minimal constructor */
	public Zjgz(Integer no) {
		this.no = no;
	}

	/** full constructor */
	public Zjgz(Integer no, Jie jie, Integer tk, Integer xz, Integer pd,
			Integer cz, Integer rytbl, Integer zdbl, Integer ntbl) {
		this.no = no;
		this.jie = jie;
		this.tk = tk;
		this.xz = xz;
		this.pd = pd;
		this.cz = cz;
		this.rytbl = rytbl;
		this.zdbl = zdbl;
		this.ntbl = ntbl;
	}

	// Property accessors
	@Id
	@Column(name = "no", unique = true, nullable = false)
	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( { @JoinColumn(name = "zbh", referencedColumnName = "zbh"),
			@JoinColumn(name = "c_id", referencedColumnName = "c_id") })
	public Jie getJie() {
		return this.jie;
	}

	public void setJie(Jie jie) {
		this.jie = jie;
	}

	@Column(name = "tk")
	public Integer getTk() {
		return this.tk;
	}

	public void setTk(Integer tk) {
		this.tk = tk;
	}

	@Column(name = "xz")
	public Integer getXz() {
		return this.xz;
	}

	public void setXz(Integer xz) {
		this.xz = xz;
	}

	@Column(name = "pd")
	public Integer getPd() {
		return this.pd;
	}

	public void setPd(Integer pd) {
		this.pd = pd;
	}

	@Column(name = "cz")
	public Integer getCz() {
		return this.cz;
	}

	public void setCz(Integer cz) {
		this.cz = cz;
	}

	@Column(name = "rytbl")
	public Integer getRytbl() {
		return this.rytbl;
	}

	public void setRytbl(Integer rytbl) {
		this.rytbl = rytbl;
	}

	@Column(name = "zdbl")
	public Integer getZdbl() {
		return this.zdbl;
	}

	public void setZdbl(Integer zdbl) {
		this.zdbl = zdbl;
	}

	@Column(name = "ntbl")
	public Integer getNtbl() {
		return this.ntbl;
	}

	public void setNtbl(Integer ntbl) {
		this.ntbl = ntbl;
	}

}