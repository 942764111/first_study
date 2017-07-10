package xx.collection.bean;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jslb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jslb", catalog = "study3")
public class Jslb implements java.io.Serializable {

	// Fields

	private Integer jslb;
	private String lbmc;
	private String lbbz;
	// Constructors

	/** default constructor */
	public Jslb() {
	}

	/** full constructor */
	public Jslb(String lbmc, String lbbz, Set<Teacher> teachers) {
		this.lbmc = lbmc;
		this.lbbz = lbbz;
		
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "jslb", unique = true, nullable = false)
	public Integer getJslb() {
		return this.jslb;
	}

	public void setJslb(Integer jslb) {
		this.jslb = jslb;
	}

	@Column(name = "lbmc", length = 10)
	public String getLbmc() {
		return this.lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	@Column(name = "lbbz", length = 200)
	public String getLbbz() {
		return this.lbbz;
	}

	public void setLbbz(String lbbz) {
		this.lbbz = lbbz;
	}

}