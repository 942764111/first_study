package xx.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Zinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zinfo", schema = "dbo", catalog = "study2")
public class Zinfo implements java.io.Serializable {

	// Fields

	private ZinfoId id;
	private Kcxx kcxx;
	private String zmc;
	private String zms;

	// Constructors

	/** default constructor */
	public Zinfo() {
	}

	/** minimal constructor */
	public Zinfo(ZinfoId id, Kcxx kcxx) {
		this.id = id;
		this.kcxx = kcxx;
	}

	/** full constructor */
	public Zinfo(ZinfoId id, Kcxx kcxx, String zmc, String zms) {
		this.id = id;
		this.kcxx = kcxx;
		this.zmc = zmc;
		this.zms = zms;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "zbh", column = @Column(name = "zbh", nullable = false)),
			@AttributeOverride(name = "CId", column = @Column(name = "c_id", nullable = false)) })
	public ZinfoId getId() {
		return this.id;
	}

	public void setId(ZinfoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "c_id", nullable = false, insertable = false, updatable = false)
	public Kcxx getKcxx() {
		return this.kcxx;
	}

	public void setKcxx(Kcxx kcxx) {
		this.kcxx = kcxx;
	}

	@Column(name = "zmc")
	public String getZmc() {
		return this.zmc;
	}

	public void setZmc(String zmc) {
		this.zmc = zmc;
	}

	@Column(name = "zms")
	public String getZms() {
		return this.zms;
	}

	public void setZms(String zms) {
		this.zms = zms;
	}

}