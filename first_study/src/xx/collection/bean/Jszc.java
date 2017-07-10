package xx.collection.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jszc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jszc", catalog = "study3")
public class Jszc implements java.io.Serializable {

	// Fields

	private Integer zcbh;
	private String zcmc;
	private String zcjb;

	// Constructors

	/** default constructor */
	public Jszc() {
	}

	/** full constructor */
	public Jszc(String zcmc, String zcjb) {
		this.zcmc = zcmc;
		this.zcjb = zcjb;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "zcbh", unique = true, nullable = false)
	public Integer getZcbh() {
		return this.zcbh;
	}

	public void setZcbh(Integer zcbh) {
		this.zcbh = zcbh;
	}

	@Column(name = "zcmc", length = 8)
	public String getZcmc() {
		return this.zcmc;
	}

	public void setZcmc(String zcmc) {
		this.zcmc = zcmc;
	}

	@Column(name = "zcjb", length = 2)
	public String getZcjb() {
		return this.zcjb;
	}

	public void setZcjb(String zcjb) {
		this.zcjb = zcjb;
	}
}