package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ZyscId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ZyscId implements java.io.Serializable {

	// Fields

	private String userId;
	private Integer sxh;

	// Constructors

	/** default constructor */
	public ZyscId() {
	}

	/** full constructor */
	public ZyscId(String userId, Integer sxh) {
		this.userId = userId;
		this.sxh = sxh;
	}

	// Property accessors

	@Column(name = "UserId", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		if (!(other instanceof ZyscId))
			return false;
		ZyscId castOther = (ZyscId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getSxh() == castOther.getSxh()) || (this.getSxh() != null
						&& castOther.getSxh() != null && this.getSxh().equals(
						castOther.getSxh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getSxh() == null ? 0 : this.getSxh().hashCode());
		return result;
	}

}