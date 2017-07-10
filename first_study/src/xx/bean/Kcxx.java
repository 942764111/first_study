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
 * Kcxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "kcxx", schema = "dbo", catalog = "study2")
public class Kcxx implements java.io.Serializable {

	// Fields

	private Integer CId;
	private String CName;
	private String kcms;
	

	// Constructors

	/** default constructor */
	public Kcxx() {
	}

	/** minimal constructor */
	public Kcxx(Integer CId) {
		this.CId = CId;
	}

	/** full constructor */
	public Kcxx(Integer CId, String CName, String kcms) {
		this.CId = CId;
		this.CName = CName;
		this.kcms = kcms;
		
	}

	// Property accessors
	@Id
	@Column(name = "c_id", unique = true, nullable = false)
	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	@Column(name = "c_name")
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "kcms")
	public String getKcms() {
		return this.kcms;
	}

	public void setKcms(String kcms) {
		this.kcms = kcms;
	}


}