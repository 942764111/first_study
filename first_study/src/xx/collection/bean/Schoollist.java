package xx.collection.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Schoollist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schoollist", catalog = "study3")
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
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "xxbh", unique = true, nullable = false)
	public Integer getXxbh() {
		return this.xxbh;
	}

	public void setXxbh(Integer xxbh) {
		this.xxbh = xxbh;
	}

	@Column(name = "xxmc", length = 20)
	public String getXxmc() {
		return this.xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	@Column(name = "xxms", length = 200)
	public String getXxms() {
		return this.xxms;
	}

	public void setXxms(String xxms) {
		this.xxms = xxms;
	}

}