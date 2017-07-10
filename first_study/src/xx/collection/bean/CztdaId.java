package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CztdaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CztdaId implements java.io.Serializable {

	// Fields

	private Integer bzxh;
	private Integer dtTh;
	private Integer sxh;

	// Constructors

	/** default constructor */
	public CztdaId() {
	}

	/** full constructor */
	public CztdaId(Integer bzxh, Integer dtTh, Integer sxh) {
		this.bzxh = bzxh;
		this.dtTh = dtTh;
		this.sxh = sxh;
	}

	// Property accessors

	@Column(name = "bzxh", nullable = false)
	public Integer getBzxh() {
		return this.bzxh;
	}

	public void setBzxh(Integer bzxh) {
		this.bzxh = bzxh;
	}

	@Column(name = "dt_th", nullable = false)
	public Integer getDtTh() {
		return this.dtTh;
	}

	public void setDtTh(Integer dtTh) {
		this.dtTh = dtTh;
	}

	@Column(name = "sxh", nullable = false)
	public Integer getSxh() {
		return this.sxh;
	}

	public void setSxh(Integer sxh) {
		this.sxh = sxh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CztdaId))
			return false;
		CztdaId castOther = (CztdaId) other;

		return ((this.getBzxh() == castOther.getBzxh()) || (this.getBzxh() != null
				&& castOther.getBzxh() != null && this.getBzxh().equals(
				castOther.getBzxh())))
				&& ((this.getDtTh() == castOther.getDtTh()) || (this.getDtTh() != null
						&& castOther.getDtTh() != null && this.getDtTh()
						.equals(castOther.getDtTh())))
				&& ((this.getSxh() == castOther.getSxh()) || (this.getSxh() != null
						&& castOther.getSxh() != null && this.getSxh().equals(
						castOther.getSxh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBzxh() == null ? 0 : this.getBzxh().hashCode());
		result = 37 * result
				+ (getDtTh() == null ? 0 : this.getDtTh().hashCode());
		result = 37 * result
				+ (getSxh() == null ? 0 : this.getSxh().hashCode());
		return result;
	}

}