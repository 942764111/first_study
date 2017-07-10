package xx.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Rolefunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rolefunction", schema = "dbo", catalog = "study2")
public class Rolefunction implements java.io.Serializable {

	// Fields

	private RolefunctionId id;
	private Roles roles;
	private Functions functions;

	// Constructors

	/** default constructor */
	public Rolefunction() {
	}

	/** full constructor */
	public Rolefunction(RolefunctionId id, Roles roles, Functions functions) {
		this.id = id;
		this.roles = roles;
		this.functions = functions;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "roleid", column = @Column(name = "roleid", nullable = false)),
			@AttributeOverride(name = "actionname", column = @Column(name = "actionname", nullable = false)) })
	public RolefunctionId getId() {
		return this.id;
	}

	public void setId(RolefunctionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid", nullable = false, insertable = false, updatable = false)
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "actionname", nullable = false, insertable = false, updatable = false)
	public Functions getFunctions() {
		return this.functions;
	}

	public void setFunctions(Functions functions) {
		this.functions = functions;
	}

}