package xx.bean;

import java.sql.Timestamp;
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
 * Jszcbh entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jszcbh", schema = "dbo", catalog = "study2")
public class Jszcbh implements java.io.Serializable {

	// Fields

	private JszcbhId id;
	private Jszc jszc;
	private Teacher teacher;
	private Timestamp dzny;

	// Constructors

	/** default constructor */
	public Jszcbh() {
	}

	/** minimal constructor */
	public Jszcbh(JszcbhId id, Jszc jszc, Teacher teacher) {
		this.id = id;
		this.jszc = jszc;
		this.teacher = teacher;
	}

	/** full constructor */
	public Jszcbh(JszcbhId id, Jszc jszc, Teacher teacher, Timestamp dzny) {
		this.id = id;
		this.jszc = jszc;
		this.teacher = teacher;
		this.dzny = dzny;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "jsbh", column = @Column(name = "jsbh", nullable = false)),
			@AttributeOverride(name = "zcbh", column = @Column(name = "zcbh", nullable = false)) })
	public JszcbhId getId() {
		return this.id;
	}

	public void setId(JszcbhId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zcbh", nullable = false, insertable = false, updatable = false)
	public Jszc getJszc() {
		return this.jszc;
	}

	public void setJszc(Jszc jszc) {
		this.jszc = jszc;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jsbh", nullable = false, insertable = false, updatable = false)
	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Column(name = "dzny", length = 16)
	public Timestamp getDzny() {
		return this.dzny;
	}

	public void setDzny(Timestamp dzny) {
		this.dzny = dzny;
	}

}