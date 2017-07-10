package xx.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Cztda entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cztda", catalog = "study2")
public class Cztda implements java.io.Serializable {

	// Fields

	private CztdaId id;

	// Constructors

	/** default constructor */
	public Cztda() {
	}

	/** full constructor */
	public Cztda(CztdaId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "cztTh", column = @Column(name = "czt_th")),
			@AttributeOverride(name = "sxh", column = @Column(name = "sxh")),
			@AttributeOverride(name = "bzxh", column = @Column(name = "bzxh")),
			@AttributeOverride(name = "zsdbh", column = @Column(name = "zsdbh")),
			@AttributeOverride(name = "zbh", column = @Column(name = "zbh")),
			@AttributeOverride(name = "CId", column = @Column(name = "c_id")),
			@AttributeOverride(name = "bufz", column = @Column(name = "bufz")) })
	public CztdaId getId() {
		return this.id;
	}

	public void setId(CztdaId id) {
		this.id = id;
	}

}