package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ZidingyibijimuluId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ZidingyibijimuluId implements java.io.Serializable {

	// Fields

	private String userId;
	private String classno;

	// Constructors

	/** default constructor */
	public ZidingyibijimuluId() {
	}

	/** full constructor */
	public ZidingyibijimuluId(String userId, String classno) {
		this.userId = userId;
		this.classno = classno;
	}

	// Property accessors

	@Column(name = "UserId", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "classno", nullable = false, length = 11)
	public String getClassno() {
		return this.classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ZidingyibijimuluId))
			return false;
		ZidingyibijimuluId castOther = (ZidingyibijimuluId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getClassno() == castOther.getClassno()) || (this
						.getClassno() != null
						&& castOther.getClassno() != null && this.getClassno()
						.equals(castOther.getClassno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getClassno() == null ? 0 : this.getClassno().hashCode());
		return result;
	}

}