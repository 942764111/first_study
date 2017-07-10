package xx.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CztId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CztId implements java.io.Serializable {

	// Fields

	private Integer sxh;
	private String cztTh;

	// Constructors

	/** default constructor */
	public CztId() {
	}

	/** full constructor */
	public CztId(Integer sxh, String cztTh) {
		this.sxh = sxh;
		this.cztTh = cztTh;
	}

	// Property accessors

	@Column(name = "sxh", nullable = false)
	public Integer getSxh() {
		return this.sxh;
	}

	public void setSxh(Integer sxh) {
		this.sxh = sxh;
	}

	@Column(name = "czt_th", nullable = false, length = 16)
	public String getCztTh() {
		return this.cztTh;
	}

	public void setCztTh(String cztTh) {
		this.cztTh = cztTh;
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
				&& ((this.getCztTh() == castOther.getCztTh()) || (this
						.getCztTh() != null
						&& castOther.getCztTh() != null && this.getCztTh()
						.equals(castOther.getCztTh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSxh() == null ? 0 : this.getSxh().hashCode());
		result = 37 * result
				+ (getCztTh() == null ? 0 : this.getCztTh().hashCode());
		return result;
	}

}