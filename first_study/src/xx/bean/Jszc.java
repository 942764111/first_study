package xx.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Jszc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jszc", schema = "dbo", catalog = "study2")
public class Jszc implements java.io.Serializable {

	// Fields

	private Integer zcbh;
	private String zcmc;
	private String zcjb;
	

	// Constructors

	/** default constructor */
	public Jszc() {
	}

	/** minimal constructor */
	public Jszc(Integer zcbh) {
		this.zcbh = zcbh;
	}

	/** full constructor */
	public Jszc(Integer zcbh, String zcmc, String zcjb) {
		this.zcbh = zcbh;
		this.zcmc = zcmc;
		this.zcjb = zcjb;
		
	}

	// Property accessors
	@Id
	@Column(name = "zcbh", unique = true, nullable = false)
	public Integer getZcbh() {
		return this.zcbh;
	}

	public void setZcbh(Integer zcbh) {
		this.zcbh = zcbh;
	}

	@Column(name = "zcmc")
	public String getZcmc() {
		return this.zcmc;
	}

	public void setZcmc(String zcmc) {
		this.zcmc = zcmc;
	}

	@Column(name = "zcjb")
	public String getZcjb() {
		return this.zcjb;
	}

	public void setZcjb(String zcjb) {
		this.zcjb = zcjb;
	}


}