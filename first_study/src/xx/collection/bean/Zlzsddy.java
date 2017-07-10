package xx.collection.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Zlzsddy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zlzsddy", catalog = "study3")
public class Zlzsddy implements java.io.Serializable {

	// Fields

	private ZlzsddyId id;
	private Dmtzl dmtzl;
	private Zsd zsd;
	private String weizhi;

	// Constructors

	/** default constructor */
	public Zlzsddy() {
	}

	/** minimal constructor */
	public Zlzsddy(ZlzsddyId id, Dmtzl dmtzl, Zsd zsd) {
		this.id = id;
		this.dmtzl = dmtzl;
		this.zsd = zsd;
	}

	/** full constructor */
	public Zlzsddy(ZlzsddyId id, Dmtzl dmtzl, Zsd zsd, String weizhi) {
		this.id = id;
		this.dmtzl = dmtzl;
		this.zsd = zsd;
		this.weizhi = weizhi;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "zlbh", column = @Column(name = "zlbh", nullable = false)),
			@AttributeOverride(name = "zsdbh", column = @Column(name = "zsdbh", nullable = false)),
			@AttributeOverride(name = "zbh", column = @Column(name = "zbh", nullable = false)),
			@AttributeOverride(name = "CId", column = @Column(name = "c_id", nullable = false)) })
	public ZlzsddyId getId() {
		return this.id;
	}

	public void setId(ZlzsddyId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zlbh", nullable = false, insertable = false, updatable = false)
	public Dmtzl getDmtzl() {
		return this.dmtzl;
	}

	public void setDmtzl(Dmtzl dmtzl) {
		this.dmtzl = dmtzl;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "zsdbh", referencedColumnName = "zsdbh", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "zbh", referencedColumnName = "zbh", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "c_id", referencedColumnName = "c_id", nullable = false, insertable = false, updatable = false) })
	public Zsd getZsd() {
		return this.zsd;
	}

	public void setZsd(Zsd zsd) {
		this.zsd = zsd;
	}

	@Column(name = "weizhi", length = 20)
	public String getWeizhi() {
		return this.weizhi;
	}

	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

}