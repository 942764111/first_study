package xx.collection.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Tjb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tjb", catalog = "study3")
public class Tjb implements java.io.Serializable {

	// Fields

	private TjbId id;
	private Integer cztSl;
	private Integer dmtSl;
	private Integer pdSl;
	private Integer tkSl;
	private Integer xzSl;
	private Integer zyscSl;

	// Constructors

	/** default constructor */
	public Tjb() {
	}

	/** minimal constructor */
	public Tjb(TjbId id) {
		this.id = id;
	}

	/** full constructor */
	public Tjb(TjbId id, Integer cztSl, Integer dmtSl, Integer pdSl,
			Integer tkSl, Integer xzSl, Integer zyscSl) {
		this.id = id;
		this.cztSl = cztSl;
		this.dmtSl = dmtSl;
		this.pdSl = pdSl;
		this.tkSl = tkSl;
		this.xzSl = xzSl;
		this.zyscSl = zyscSl;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "zmc", column = @Column(name = "zmc", nullable = false, length = 80)),
			@AttributeOverride(name = "CName", column = @Column(name = "c_name", nullable = false, length = 80)) })
	public TjbId getId() {
		return this.id;
	}

	public void setId(TjbId id) {
		this.id = id;
	}

	@Column(name = "czt_sl")
	public Integer getCztSl() {
		return this.cztSl;
	}

	public void setCztSl(Integer cztSl) {
		this.cztSl = cztSl;
	}

	@Column(name = "dmt_sl")
	public Integer getDmtSl() {
		return this.dmtSl;
	}

	public void setDmtSl(Integer dmtSl) {
		this.dmtSl = dmtSl;
	}

	@Column(name = "pd_sl")
	public Integer getPdSl() {
		return this.pdSl;
	}

	public void setPdSl(Integer pdSl) {
		this.pdSl = pdSl;
	}

	@Column(name = "tk_sl")
	public Integer getTkSl() {
		return this.tkSl;
	}

	public void setTkSl(Integer tkSl) {
		this.tkSl = tkSl;
	}

	@Column(name = "xz_sl")
	public Integer getXzSl() {
		return this.xzSl;
	}

	public void setXzSl(Integer xzSl) {
		this.xzSl = xzSl;
	}

	@Column(name = "zysc_sl")
	public Integer getZyscSl() {
		return this.zyscSl;
	}

	public void setZyscSl(Integer zyscSl) {
		this.zyscSl = zyscSl;
	}

}