package xx.bean;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

/**
 * Xuyan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xuyan", schema = "dbo", catalog = "study2")
public class Xuyan_simple implements java.io.Serializable {

	// Fields

	private Integer xybh;
	private String xymc;

	// Constructors

	/** default constructor */
	public Xuyan_simple() {
	}

	/** minimal constructor */
	public Xuyan_simple(Integer xybh) {
		this.xybh = xybh;
	}

	/** full constructor */
	public Xuyan_simple(Integer xybh, String xymc) {
		this.xybh = xybh;
		this.xymc = xymc;

	}

	// Property accessors
	@Id
	@Column(name = "xybh", unique = true, nullable = false)
	public Integer getXybh() {
		return this.xybh;
	}

	public void setXybh(Integer xybh) {
		this.xybh = xybh;
	}

	@Column(name = "xymc")
	public String getXymc() {
		return this.xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
}