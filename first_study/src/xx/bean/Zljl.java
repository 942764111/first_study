package xx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Zljl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zljl", catalog = "study2")
public class Zljl implements java.io.Serializable {

	// Fields

	private Integer zljlNo;
	private String userId;
	private String zlbh;
	private Integer weizhi;
	private Integer openTime;
	private Boolean sfjx;

	// Constructors

	/** default constructor */
	public Zljl() {
	}

	/** minimal constructor */
	public Zljl(Integer zljlNo) {
		this.zljlNo = zljlNo;
	}

	/** full constructor */
	public Zljl(Integer zljlNo, String userId, String zlbh, Integer weizhi,
			Integer openTime, Boolean sfjx) {
		this.zljlNo = zljlNo;
		this.userId = userId;
		this.zlbh = zlbh;
		this.weizhi = weizhi;
		this.openTime = openTime;
		this.sfjx = sfjx;
	}

	// Property accessors
	@Id
	@Column(name = "zljl_no", unique = true, nullable = false)
	public Integer getZljlNo() {
		return this.zljlNo;
	}

	public void setZljlNo(Integer zljlNo) {
		this.zljlNo = zljlNo;
	}

	@Column(name = "UserId", length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "zlbh", length = 16)
	public String getZlbh() {
		return this.zlbh;
	}

	public void setZlbh(String zlbh) {
		this.zlbh = zlbh;
	}

	@Column(name = "weizhi")
	public Integer getWeizhi() {
		return this.weizhi;
	}

	public void setWeizhi(Integer weizhi) {
		this.weizhi = weizhi;
	}

	@Column(name = "open_time")
	public Integer getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(Integer openTime) {
		this.openTime = openTime;
	}

	@Column(name = "sfjx")
	public Boolean getSfjx() {
		return this.sfjx;
	}

	public void setSfjx(Boolean sfjx) {
		this.sfjx = sfjx;
	}

}