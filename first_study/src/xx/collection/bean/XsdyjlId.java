package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * XsdyjlId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class XsdyjlId implements java.io.Serializable {

	// Fields

	private String userId;
	private Integer sjno;

	// Constructors

	/** default constructor */
	public XsdyjlId() {
	}

	/** full constructor */
	public XsdyjlId(String userId, Integer sjno) {
		this.userId = userId;
		this.sjno = sjno;
	}

	// Property accessors

	@Column(name = "UserId", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "sjno", nullable = false)
	public Integer getSjno() {
		return this.sjno;
	}

	public void setSjno(Integer sjno) {
		this.sjno = sjno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof XsdyjlId))
			return false;
		XsdyjlId castOther = (XsdyjlId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getSjno() == castOther.getSjno()) || (this.getSjno() != null
						&& castOther.getSjno() != null && this.getSjno()
						.equals(castOther.getSjno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getSjno() == null ? 0 : this.getSjno().hashCode());
		return result;
	}

}