package xx.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ZinfoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ZinfoId implements java.io.Serializable {

	// Fields

	private Integer zbh;
	private Integer CId;

	// Constructors

	/** default constructor */
	public ZinfoId() {
	}

	/** full constructor */
	public ZinfoId(Integer zbh, Integer CId) {
		this.zbh = zbh;
		this.CId = CId;
	}

	// Property accessors

	@Column(name = "zbh", nullable = false)
	public Integer getZbh() {
		return this.zbh;
	}

	public void setZbh(Integer zbh) {
		this.zbh = zbh;
	}

	@Column(name = "c_id", nullable = false)
	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ZinfoId))
			return false;
		ZinfoId castOther = (ZinfoId) other;

		return ((this.getZbh() == castOther.getZbh()) || (this.getZbh() != null
				&& castOther.getZbh() != null && this.getZbh().equals(
				castOther.getZbh())))
				&& ((this.getCId() == castOther.getCId()) || (this.getCId() != null
						&& castOther.getCId() != null && this.getCId().equals(
						castOther.getCId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getZbh() == null ? 0 : this.getZbh().hashCode());
		result = 37 * result
				+ (getCId() == null ? 0 : this.getCId().hashCode());
		return result;
	}

}