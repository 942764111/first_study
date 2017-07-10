package xx.collection.bean;

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
 * Jie entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jie", catalog = "study3")
public class Jie implements java.io.Serializable {

	// Fields

	private JieId id;
	private CourseChapter courseChapter;
	private String zmc;
	private String zms;

	// Constructors

	/** default constructor */
	public Jie() {
	}

	
	public Jie(String zmc,JieId id) {
		this.zmc=zmc;
		this.id=id;
	}
	/** minimal constructor */
	public Jie(JieId id, CourseChapter courseChapter) {
		this.id = id;
		this.courseChapter = courseChapter;
	}

	/** full constructor */
	public Jie(JieId id, CourseChapter courseChapter, String zmc, String zms) {
		this.id = id;
		this.courseChapter = courseChapter;
		this.zmc = zmc;
		this.zms = zms;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "zbh", column = @Column(name = "zbh", nullable = false)),
			@AttributeOverride(name = "CId", column = @Column(name = "c_id", nullable = false)) })
	public JieId getId() {
		return this.id;
	}

	public void setId(JieId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zbh", nullable = false, insertable = false, updatable = false)
	public CourseChapter getCourseChapter() {
		return this.courseChapter;
	}

	public void setCourseChapter(CourseChapter courseChapter) {
		this.courseChapter = courseChapter;
	}

	@Column(name = "zmc", length = 20)
	public String getZmc() {
		return this.zmc;
	}

	public void setZmc(String zmc) {
		this.zmc = zmc;
	}

	@Column(name = "zms", length = 200)
	public String getZms() {
		return this.zms;
	}

	public void setZms(String zms) {
		this.zms = zms;
	}

}