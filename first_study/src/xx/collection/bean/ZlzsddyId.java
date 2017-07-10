package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ZlzsddyId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ZlzsddyId implements java.io.Serializable {

	// Fields

	private Integer zlbh;
	private Integer zsdbh;
	private Integer zbh;
	private Integer CId;

	// Constructors

	/** default constructor */
	public ZlzsddyId() {
	}

	/** full constructor */
	public ZlzsddyId(Integer zlbh, Integer zsdbh, Integer zbh, Integer CId) {
		this.zlbh = zlbh;
		this.zsdbh = zsdbh;
		this.zbh = zbh;
		this.CId = CId;
	}

	// Property accessors

	@Column(name = "zlbh", nullable = false)
	public Integer getZlbh() {
		return this.zlbh;
	}

	public void setZlbh(Integer zlbh) {
		this.zlbh = zlbh;
	}

	@Column(name = "zsdbh", nullable = false)
	public Integer getZsdbh() {
		return this.zsdbh;
	}

	public void setZsdbh(Integer zsdbh) {
		this.zsdbh = zsdbh;
	}

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
		if (!(other instanceof ZlzsddyId))
			return false;
		ZlzsddyId castOther = (ZlzsddyId) other;

		return ((this.getZlbh() == castOther.getZlbh()) || (this.getZlbh() != null
				&& castOther.getZlbh() != null && this.getZlbh().equals(
				castOther.getZlbh())))
				&& ((this.getZsdbh() == castOther.getZsdbh()) || (this
						.getZsdbh() != null
						&& castOther.getZsdbh() != null && this.getZsdbh()
						.equals(castOther.getZsdbh())))
				&& ((this.getZbh() == castOther.getZbh()) || (this.getZbh() != null
						&& castOther.getZbh() != null && this.getZbh().equals(
						castOther.getZbh())))
				&& ((this.getCId() == castOther.getCId()) || (this.getCId() != null
						&& castOther.getCId() != null && this.getCId().equals(
						castOther.getCId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getZlbh() == null ? 0 : this.getZlbh().hashCode());
		result = 37 * result
				+ (getZsdbh() == null ? 0 : this.getZsdbh().hashCode());
		result = 37 * result
				+ (getZbh() == null ? 0 : this.getZbh().hashCode());
		result = 37 * result
				+ (getCId() == null ? 0 : this.getCId().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ZlzsddyId [zlbh=" + zlbh + ", zsdbh=" + zsdbh + ", zbh=" + zbh
				+ ", CId=" + CId + "]";
	}

}