package xx.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Zsd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zsd", catalog = "study2")
public class Zsd implements java.io.Serializable {

	// Fields

	private ZsdId id;
	private String zsdmc;
	private String zsdms;
	private Integer sfzd;
	private Integer sfdn;

	// Constructors

	/** default constructor */
	public Zsd() {
	}

	/** minimal constructor */
	public Zsd(ZsdId id) {
		this.id = id;
	}

	/** full constructor */
	public Zsd(ZsdId id, String zsdmc, String zsdms, Integer sfzd, Integer sfdn) {
		this.id = id;
		this.zsdmc = zsdmc;
		this.zsdms = zsdms;
		this.sfzd = sfzd;
		this.sfdn = sfdn;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "zsdbh", column = @Column(name = "zsdbh", nullable = false)),
			@AttributeOverride(name = "zbh", column = @Column(name = "zbh", nullable = false)),
			@AttributeOverride(name = "CId", column = @Column(name = "c_id", nullable = false)) })
	public ZsdId getId() {
		return this.id;
	}

	public void setId(ZsdId id) {
		this.id = id;
	}

	@Column(name = "zsdmc", length = 20)
	public String getZsdmc() {
		return this.zsdmc;
	}

	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}

	@Column(name = "zsdms", length = 200)
	public String getZsdms() {
		return this.zsdms;
	}

	public void setZsdms(String zsdms) {
		this.zsdms = zsdms;
	}

	@Column(name = "sfzd")
	public Integer getSfzd() {
		return this.sfzd;
	}

	public void setSfzd(Integer sfzd) {
		this.sfzd = sfzd;
	}

	@Column(name = "sfdn")
	public Integer getSfdn() {
		return this.sfdn;
	}

	public void setSfdn(Integer sfdn) {
		this.sfdn = sfdn;
	}

}