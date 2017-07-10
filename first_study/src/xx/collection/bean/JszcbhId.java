package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * JszcbhId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class JszcbhId implements java.io.Serializable {

	// Fields

	private String jsbh;
	private Integer zcbh;

	// Constructors

	/** default constructor */
	public JszcbhId() {
	}

	/** full constructor */
	public JszcbhId(String jsbh, Integer zcbh) {
		this.jsbh = jsbh;
		this.zcbh = zcbh;
	}

	// Property accessors

	@Column(name = "jsbh", nullable = false, length = 20)
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	@Column(name = "zcbh", nullable = false)
	public Integer getZcbh() {
		return this.zcbh;
	}

	public void setZcbh(Integer zcbh) {
		this.zcbh = zcbh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JszcbhId))
			return false;
		JszcbhId castOther = (JszcbhId) other;

		return ((this.getJsbh() == castOther.getJsbh()) || (this.getJsbh() != null
				&& castOther.getJsbh() != null && this.getJsbh().equals(
				castOther.getJsbh())))
				&& ((this.getZcbh() == castOther.getZcbh()) || (this.getZcbh() != null
						&& castOther.getZcbh() != null && this.getZcbh()
						.equals(castOther.getZcbh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getJsbh() == null ? 0 : this.getJsbh().hashCode());
		result = 37 * result
				+ (getZcbh() == null ? 0 : this.getZcbh().hashCode());
		return result;
	}

}