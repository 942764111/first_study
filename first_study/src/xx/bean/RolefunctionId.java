package xx.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RolefunctionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RolefunctionId implements java.io.Serializable {

	// Fields

	private Integer roleid;
	private String actionname;

	// Constructors

	/** default constructor */
	public RolefunctionId() {
	}

	/** full constructor */
	public RolefunctionId(Integer roleid, String actionname) {
		this.roleid = roleid;
		this.actionname = actionname;
	}

	// Property accessors

	@Column(name = "roleid", nullable = false)
	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Column(name = "actionname", nullable = false)
	public String getActionname() {
		return this.actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolefunctionId))
			return false;
		RolefunctionId castOther = (RolefunctionId) other;

		return ((this.getRoleid() == castOther.getRoleid()) || (this
				.getRoleid() != null
				&& castOther.getRoleid() != null && this.getRoleid().equals(
				castOther.getRoleid())))
				&& ((this.getActionname() == castOther.getActionname()) || (this
						.getActionname() != null
						&& castOther.getActionname() != null && this
						.getActionname().equals(castOther.getActionname())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		result = 37
				* result
				+ (getActionname() == null ? 0 : this.getActionname()
						.hashCode());
		return result;
	}

}