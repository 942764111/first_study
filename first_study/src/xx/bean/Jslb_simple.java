package xx.bean;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jslb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jslb", schema = "dbo", catalog = "study2")
public class Jslb_simple implements java.io.Serializable {

	// Fields

	private Integer jslb;
	private String lbmc;
	private String lbbz;
	
	// Constructors

	/** default constructor */
	public Jslb_simple() {
	}

	/** minimal constructor */
	public Jslb_simple(Integer jslb) {
		this.jslb = jslb;
	}

	/** full constructor */
	public Jslb_simple(Integer jslb, String lbmc, String lbbz, Set<Teacher> teachers) {
		this.jslb = jslb;
		this.lbmc = lbmc;
		this.lbbz = lbbz;
		
	}

	// Property accessors
	@Id
	@Column(name = "jslb", unique = true, nullable = false)
	public Integer getJslb() {
		return this.jslb;
	}

	public void setJslb(Integer jslb) {
		this.jslb = jslb;
	}

	@Column(name = "lbmc")
	public String getLbmc() {
		return this.lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	@Column(name = "lbbz")
	public String getLbbz() {
		return this.lbbz;
	}

	public void setLbbz(String lbbz) {
		this.lbbz = lbbz;
	}

}