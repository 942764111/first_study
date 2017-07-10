package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * XzpdXgId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class XzpdXgId implements java.io.Serializable {

	// Fields

	private String th;
	private Integer lx;
	private Integer zsdbh;
	private Integer sjno1;

	// Constructors

	/** default constructor */
	public XzpdXgId() {
	}

	/** full constructor */
	public XzpdXgId(String th, Integer lx, Integer zsdbh, Integer sjno1) {
		this.th = th;
		this.lx = lx;
		this.zsdbh = zsdbh;
		this.sjno1 = sjno1;
	}

	// Property accessors

	@Column(name = "th", nullable = false, length = 50)
	public String getTh() {
		return this.th;
	}

	public void setTh(String th) {
		this.th = th;
	}

	@Column(name = "lx", nullable = false)
	public Integer getLx() {
		return this.lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	@Column(name = "zsdbh", nullable = false)
	public Integer getZsdbh() {
		return this.zsdbh;
	}

	public void setZsdbh(Integer zsdbh) {
		this.zsdbh = zsdbh;
	}

	@Column(name = "sjno1", nullable = false)
	public Integer getSjno1() {
		return this.sjno1;
	}

	public void setSjno1(Integer sjno1) {
		this.sjno1 = sjno1;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof XzpdXgId))
			return false;
		XzpdXgId castOther = (XzpdXgId) other;

		return ((this.getTh() == castOther.getTh()) || (this.getTh() != null
				&& castOther.getTh() != null && this.getTh().equals(
				castOther.getTh())))
				&& ((this.getLx() == castOther.getLx()) || (this.getLx() != null
						&& castOther.getLx() != null && this.getLx().equals(
						castOther.getLx())))
				&& ((this.getZsdbh() == castOther.getZsdbh()) || (this
						.getZsdbh() != null
						&& castOther.getZsdbh() != null && this.getZsdbh()
						.equals(castOther.getZsdbh())))
				&& ((this.getSjno1() == castOther.getSjno1()) || (this
						.getSjno1() != null
						&& castOther.getSjno1() != null && this.getSjno1()
						.equals(castOther.getSjno1())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTh() == null ? 0 : this.getTh().hashCode());
		result = 37 * result + (getLx() == null ? 0 : this.getLx().hashCode());
		result = 37 * result
				+ (getZsdbh() == null ? 0 : this.getZsdbh().hashCode());
		result = 37 * result
				+ (getSjno1() == null ? 0 : this.getSjno1().hashCode());
		return result;
	}

}