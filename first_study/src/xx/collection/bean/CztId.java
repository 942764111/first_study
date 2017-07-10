package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CztId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CztId implements java.io.Serializable {

	// Fields

	private Integer sxh;
	private Integer dtTh;

	// Constructors

	/** default constructor */
	public CztId() {
	}

	/** full constructor */
	public CztId(Integer sxh, Integer dtTh) {
		this.sxh = sxh;
		this.dtTh = dtTh;
	}

	// Property accessors

	@Column(name = "sxh", nullable = false)
	public Integer getSxh() {
		return this.sxh;
	}

	public void setSxh(Integer sxh) {
		this.sxh = sxh;
	}

	@Column(name = "dt_th", nullable = false)
	public Integer getDtTh() {
		return this.dtTh;
	}

	public void setDtTh(Integer dtTh) {
		this.dtTh = dtTh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CztId))
			return false;
		CztId castOther = (CztId) other;

		return ((this.getSxh() == castOther.getSxh()) || (this.getSxh() != null
				&& castOther.getSxh() != null && this.getSxh().equals(
				castOther.getSxh())))
				&& ((this.getDtTh() == castOther.getDtTh()) || (this.getDtTh() != null
						&& castOther.getDtTh() != null && this.getDtTh()
						.equals(castOther.getDtTh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSxh() == null ? 0 : this.getSxh().hashCode());
		result = 37 * result
				+ (getDtTh() == null ? 0 : this.getDtTh().hashCode());
		return result;
	}

}