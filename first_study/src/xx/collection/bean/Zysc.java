package xx.collection.bean;

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
 * Zysc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zysc", catalog = "study3")
public class Zysc implements java.io.Serializable {

	// Fields

	private ZyscId id;
	private Yhzdymc yhzdymc;
	private Userinfo userinfo;
	private Integer zylx;
	private Integer zybh;
	private String sskcmc;
	private String zyms;

	// Constructors

	/** default constructor */
	public Zysc() {
	}

	/** minimal constructor */
	public Zysc(ZyscId id, Userinfo userinfo) {
		this.id = id;
		this.userinfo = userinfo;
	}

	/** full constructor */
	public Zysc(ZyscId id, Yhzdymc yhzdymc, Userinfo userinfo, Integer zylx,
			Integer zybh, String sskcmc, String zyms) {
		this.id = id;
		this.yhzdymc = yhzdymc;
		this.userinfo = userinfo;
		this.zylx = zylx;
		this.zybh = zybh;
		this.sskcmc = sskcmc;
		this.zyms = zyms;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "UserId", nullable = false, length = 10)),
			@AttributeOverride(name = "sxh", column = @Column(name = "sxh", nullable = false)) })
	public ZyscId getId() {
		return this.id;
	}

	public void setId(ZyscId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zdyflno")
	public Yhzdymc getYhzdymc() {
		return this.yhzdymc;
	}

	public void setYhzdymc(Yhzdymc yhzdymc) {
		this.yhzdymc = yhzdymc;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId", nullable = false, insertable = false, updatable = false)
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "zylx")
	public Integer getZylx() {
		return this.zylx;
	}

	public void setZylx(Integer zylx) {
		this.zylx = zylx;
	}

	@Column(name = "zybh")
	public Integer getZybh() {
		return this.zybh;
	}

	public void setZybh(Integer zybh) {
		this.zybh = zybh;
	}

	@Column(name = "sskcmc", length = 20)
	public String getSskcmc() {
		return this.sskcmc;
	}

	public void setSskcmc(String sskcmc) {
		this.sskcmc = sskcmc;
	}

	@Column(name = "zyms", length = 200)
	public String getZyms() {
		return this.zyms;
	}

	public void setZyms(String zyms) {
		this.zyms = zyms;
	}

}