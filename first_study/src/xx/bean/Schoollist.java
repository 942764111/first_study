package xx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Schoollist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schoollist", schema = "dbo", catalog = "study2")
public class Schoollist implements java.io.Serializable {

	// Fields

	private Integer xxbh;
	private String xxmc;
	private String xxms;
	

	// Constructors

	/** default constructor */
	public Schoollist() {
	}

	/** minimal constructor */
	public Schoollist(Integer xxbh) {
		this.xxbh = xxbh;
	}

	/** full constructor */
	public Schoollist(Integer xxbh, String xxmc, String xxms) {
		this.xxbh = xxbh;
		this.xxmc = xxmc;
		this.xxms = xxms;
		
	}

	// Property accessors
	@Id
	@Column(name = "xxbh", unique = true, nullable = false)
	public Integer getXxbh() {
		return this.xxbh;
	}

	public void setXxbh(Integer xxbh) {
		this.xxbh = xxbh;
	}

	@Column(name = "xxmc")
	public String getXxmc() {
		return this.xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	@Column(name = "xxms")
	public String getXxms() {
		return this.xxms;
	}

	public void setXxms(String xxms) {
		this.xxms = xxms;
	}

	

}