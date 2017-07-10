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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Czt entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "czt", catalog = "study3")
public class Czt implements java.io.Serializable {

	// Fields

	private CztId id;
	private Cztd cztd;
	private String tg;
	private Integer xtfz;
	private Integer nyd;
	private Integer csrcs;
	private Integer zqrcs;
	

	// Constructors

	/** default constructor */
	public Czt() {
	}

	/** minimal constructor */
	public Czt(CztId id, Cztd cztd) {
		this.id = id;
		this.cztd = cztd;
	}

	/** full constructor */
	public Czt(CztId id, Cztd cztd, String tg, Integer xtfz, Integer nyd,
			Integer csrcs, Integer zqrcs) {
		this.id = id;
		this.cztd = cztd;
		this.tg = tg;
		this.xtfz = xtfz;
		this.nyd = nyd;
		this.csrcs = csrcs;
		this.zqrcs = zqrcs;
		
	}
	
	public Czt(CztId id, String tg, Integer xtfz, Integer nyd,
			Integer csrcs, Integer zqrcs) {
		this.id = id;
		this.tg = tg;
		this.xtfz = xtfz;
		this.nyd = nyd;
		this.csrcs = csrcs;
		this.zqrcs = zqrcs;
		
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "sxh", column = @Column(name = "sxh", nullable = false)),
			@AttributeOverride(name = "dtTh", column = @Column(name = "dt_th", nullable = false)) })
	public CztId getId() {
		return this.id;
	}

	public void setId(CztId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dt_th", nullable = false, insertable = false, updatable = false)
	public Cztd getCztd() {
		return this.cztd;
	}

	public void setCztd(Cztd cztd) {
		this.cztd = cztd;
	}

	@Column(name = "tg", length =80)
	public String getTg() {
		return this.tg;
	}

	public void setTg(String tg) {
		this.tg = tg;
	}

	@Column(name = "xtfz")
	public Integer getXtfz() {
		return this.xtfz;
	}

	public void setXtfz(Integer xtfz) {
		this.xtfz = xtfz;
	}

	@Column(name = "nyd")
	public Integer getNyd() {
		return this.nyd;
	}

	public void setNyd(Integer nyd) {
		this.nyd = nyd;
	}

	@Column(name = "csrcs")
	public Integer getCsrcs() {
		return this.csrcs;
	}

	public void setCsrcs(Integer csrcs) {
		this.csrcs = csrcs;
	}

	@Column(name = "zqrcs")
	public Integer getZqrcs() {
		return this.zqrcs;
	}

	public void setZqrcs(Integer zqrcs) {
		this.zqrcs = zqrcs;
	}

	

}