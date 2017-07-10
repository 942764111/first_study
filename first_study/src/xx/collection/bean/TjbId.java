package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TjbId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TjbId implements java.io.Serializable {

	// Fields

	private String zmc;
	private String CName;

	// Constructors

	/** default constructor */
	public TjbId() {
	}

	/** full constructor */
	public TjbId(String zmc, String CName) {
		this.zmc = zmc;
		this.CName = CName;
	}

	// Property accessors

	@Column(name = "zmc", nullable = false, length = 80)
	public String getZmc() {
		return this.zmc;
	}

	public void setZmc(String zmc) {
		this.zmc = zmc;
	}

	@Column(name = "c_name", nullable = false, length = 80)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TjbId))
			return false;
		TjbId castOther = (TjbId) other;

		return ((this.getZmc() == castOther.getZmc()) || (this.getZmc() != null
				&& castOther.getZmc() != null && this.getZmc().equals(
				castOther.getZmc())))
				&& ((this.getCName() == castOther.getCName()) || (this
						.getCName() != null
						&& castOther.getCName() != null && this.getCName()
						.equals(castOther.getCName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getZmc() == null ? 0 : this.getZmc().hashCode());
		result = 37 * result
				+ (getCName() == null ? 0 : this.getCName().hashCode());
		return result;
	}

}