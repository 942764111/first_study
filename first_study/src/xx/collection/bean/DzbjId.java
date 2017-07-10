package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DzbjId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DzbjId implements java.io.Serializable {

	// Fields

	private String userId;
	private String classno;
	private Integer tmbh;

	// Constructors

	/** default constructor */
	public DzbjId() {
	}

	/** full constructor */
	public DzbjId(String userId, String classno, Integer tmbh) {
		this.userId = userId;
		this.classno = classno;
		this.tmbh = tmbh;
	}

	// Property accessors

	@Column(name = "UserId", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "classno", nullable = false, length = 20)
	public String getClassno() {
		return this.classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	@Column(name = "tmbh", nullable = false)
	public Integer getTmbh() {
		return this.tmbh;
	}

	public void setTmbh(Integer tmbh) {
		this.tmbh = tmbh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DzbjId))
			return false;
		DzbjId castOther = (DzbjId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getClassno() == castOther.getClassno()) || (this
						.getClassno() != null
						&& castOther.getClassno() != null && this.getClassno()
						.equals(castOther.getClassno())))
				&& ((this.getTmbh() == castOther.getTmbh()) || (this.getTmbh() != null
						&& castOther.getTmbh() != null && this.getTmbh()
						.equals(castOther.getTmbh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getClassno() == null ? 0 : this.getClassno().hashCode());
		result = 37 * result
				+ (getTmbh() == null ? 0 : this.getTmbh().hashCode());
		return result;
	}

}