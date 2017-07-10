package xx.collection.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Zsd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zsd", catalog = "study3")
public class Zsd implements java.io.Serializable {

	// Fields

	private ZsdId id;
	private Jie jie;
	private String zsdmc;
	private String zsdms;
	private String zsdkey;
	private Integer sfzd;
	private Integer sfdn;

	// Constructors

	/** default constructor */
	public Zsd() {
	}

	//手动添加的
	public Zsd(ZsdId id,String zsdmc) {
		this.id=id;
		this.zsdmc=zsdmc;
	}
	/** minimal constructor */
	public Zsd(ZsdId id, Jie jie) {
		this.id = id;
		this.jie = jie;
	}
	

	/** full constructor */
	public Zsd(ZsdId id, Jie jie, String zsdmc, String zsdms, String zsdkey,
			Integer sfzd, Integer sfdn) {
		this.id = id;
		this.jie = jie;
		this.zsdmc = zsdmc;
		this.zsdms = zsdms;
		this.zsdkey = zsdkey;
		this.sfzd = sfzd;
		this.sfdn = sfdn;
	}
	
	public Zsd(ZsdId id, String zsdmc, String zsdms, String zsdkey,
			Integer sfzd, Integer sfdn) {
		this.id = id;
		this.zsdmc = zsdmc;
		this.zsdms = zsdms;
		this.zsdkey = zsdkey;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "zbh", referencedColumnName = "zbh", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "c_id", referencedColumnName = "c_id", nullable = false, insertable = false, updatable = false) })
	public Jie getJie() {
		return this.jie;
	}

	public void setJie(Jie jie) {
		this.jie = jie;
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

	@Column(name = "zsdkey", length = 200)
	public String getZsdkey() {
		return this.zsdkey;
	}

	public void setZsdkey(String zsdkey) {
		this.zsdkey = zsdkey;
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