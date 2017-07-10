package xx.bean;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mz", schema = "dbo", catalog = "study2")
public class Mz_simple implements java.io.Serializable {

	// Fields

	private Integer mzbh;
	private String mzmc;
	
	// Constructors

	/** default constructor */
	public Mz_simple() {
	}

	/** minimal constructor */
	public Mz_simple(Integer mzbh) {
		this.mzbh = mzbh;
	}

	/** full constructor */
	public Mz_simple(Integer mzbh, String mzmc, Set<Teacher> teachers) {
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