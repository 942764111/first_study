package xx.collection.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Zljl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zljl", catalog = "study3")
public class Zljl implements java.io.Serializable {

	// Fields

	private Integer zljlNo;
	private Jxjh jxjh;
	private Dmtzl dmtzl;
	private Userinfo userinfo;
	private Integer weizhi;
	private Date openTime;
	private boolean sfjx;
	private String path;
	private String img;
	// Constructors

	/** default constructor */
	public Zljl() {
	}

	/** minimal constructor */
	public Zljl(Jxjh jxjh) {
		this.jxjh = jxjh;
	}

	/** full constructor */
	public Zljl(Jxjh jxjh, Dmtzl dmtzl, Userinfo userinfo, Integer weizhi,
			Date openTime, boolean sfjx, String path,String img) {
		this.jxjh = jxjh;
		this.dmtzl = dmtzl;
		this.userinfo = userinfo;
		this.weizhi = weizhi;
		this.openTime = openTime;
		this.sfjx = sfjx;
		this.path = path;
		this.img=img;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "zljl_no", unique = true, nullable = false)
	public Integer getZljlNo() {
		return this.zljlNo;
	}

	public void setZljlNo(Integer zljlNo) {
		this.zljlNo = zljlNo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jxjh_id", nullable = false)
	public Jxjh getJxjh() {
		return this.jxjh;
	}

	public void setJxjh(Jxjh jxjh) {
		this.jxjh = jxjh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zlbh")
	public Dmtzl getDmtzl() {
		return this.dmtzl;
	}

	public void setDmtzl(Dmtzl dmtzl) {
		this.dmtzl = dmtzl;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "weizhi")
	public Integer getWeizhi() {
		return this.weizhi;
	}

	public void setWeizhi(Integer weizhi) {
		this.weizhi = weizhi;
	}

	@Column(name = "open_time", length = 19)
	public Date getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	@Column(name = "sfjx")
	public boolean getSfjx() {
		return this.sfjx;
	}

	public void setSfjx(boolean sfjx) {
		this.sfjx = sfjx;
	}

	@Column(name = "path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Column(name = "img")
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}