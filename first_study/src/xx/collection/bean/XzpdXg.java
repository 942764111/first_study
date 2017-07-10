package xx.collection.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * XzpdXg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xzpd_xg", catalog = "study3")
public class XzpdXg implements java.io.Serializable {

	// Fields

	private XzpdXgId id;
	private Integer zqno;
	private Integer cwno;
	private String fxdnr;
	private String jjdy;
	

	// Constructors

	/** default constructor */
	public XzpdXg() {
	}

	/** minimal constructor */
	public XzpdXg(XzpdXgId id) {
		this.id = id;
	}

	/** full constructor */
	public XzpdXg(XzpdXgId id, Integer zqno, Integer cwno, String fxdnr,
			String jjdy) {
		this.id = id;
		this.zqno = zqno;
		this.cwno = cwno;
		this.fxdnr = fxdnr;
		this.jjdy = jjdy;
		
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "th", column = @Column(name = "th", nullable = false, length = 50)),
			@AttributeOverride(name = "lx", column = @Column(name = "lx", nullable = false)),
			@AttributeOverride(name = "zsdbh", column = @Column(name = "zsdbh", nullable = false)),
			@AttributeOverride(name = "sjno1", column = @Column(name = "sjno1", nullable = false)) })
	public XzpdXgId getId() {
		return this.id;
	}

	public void setId(XzpdXgId id) {
		this.id = id;
	}

	@Column(name = "zqno")
	public Integer getZqno() {
		return this.zqno;
	}

	public void setZqno(Integer zqno) {
		this.zqno = zqno;
	}

	@Column(name = "cwno")
	public Integer getCwno() {
		return this.cwno;
	}

	public void setCwno(Integer cwno) {
		this.cwno = cwno;
	}

	@Column(name = "fxdnr", length = 600)
	public String getFxdnr() {
		return this.fxdnr;
	}

	public void setFxdnr(String fxdnr) {
		this.fxdnr = fxdnr;
	}

	@Column(name = "jjdy", length = 600)
	public String getJjdy() {
		return this.jjdy;
	}

	public void setJjdy(String jjdy) {
		this.jjdy = jjdy;
	}


}