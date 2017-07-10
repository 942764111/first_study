package xx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Wjlx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wjlx", catalog = "study2")
public class Wjlx implements java.io.Serializable {

	// Fields

	private String lxm;
	private String 描述;
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
	public Wjlx(String lxm, String 描述, String kzm) {
		this.lxm = lxm;
		this.描述 = 描述;
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

	@Column(name = "描述", length = 50)
	public String get描述() {
		return this.描述;
	}

	public void set描述(String 描述) {
		this.描述 = 描述;
	}

	@Column(name = "kzm", length = 5)
	public String getKzm() {
		return this.kzm;
	}

	public void setKzm(String kzm) {
		this.kzm = kzm;
	}

}