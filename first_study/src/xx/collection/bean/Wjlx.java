package xx.collection.bean;

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
 * Wjlx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wjlx", catalog = "study3")
public class Wjlx implements java.io.Serializable {

	// Fields

	private String lxm;
	private String ms;
	private String kzm;
	// Constructors

	/** default constructor */
	public Wjlx() {
	}

	/** minimal constructor */
	public Wjlx(String lxm) {
		this.lxm = lxm;
	}

	/** full constructor */
	public Wjlx(String lxm, String ms, String kzm) {
		this.lxm = lxm;
		this.ms = ms;
		this.kzm = kzm;

	}

	// Property accessors
	@Id
	@Column(name = "lxm", unique = true, nullable = false, length = 8)
	public String getLxm() {
		return this.lxm;
	}

	public void setLxm(String lxm) {
		this.lxm = lxm;
	}

	@Column(name = "ms", length = 50)
	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	@Column(name = "kzm", length = 5)
	public String getKzm() {
		return this.kzm;
	}

	public void setKzm(String kzm) {
		this.kzm = kzm;
	}

}