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
 * Mz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mz", schema = "dbo", catalog = "study2")
public class Mz implements java.io.Serializable {

	// Fields

	private Integer mzbh;
	private String mzmc;
	

	// Constructors

	/** default constructor */
	public Mz() {
	}

	/** minimal constructor */
	public Mz(Integer mzbh) {
		this.mzbh = mzbh;
	}

	/** full constructor */
	public Mz(Integer mzbh, String mzmc) {
		this.mzbh = mzbh;
		this.mzmc = mzmc;
		
	}

	// Property accessors
	@Id
	@Column(name = "mzbh", unique = true, nullable = false)
	public Integer getMzbh() {
		return this.mzbh;
	}

	public void setMzbh(Integer mzbh) {
		this.mzbh = mzbh;
	}

	@Column(name = "mzmc")
	public String getMzmc() {
		return this.mzmc;
	}

	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}


}