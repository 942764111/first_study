package xx.collection.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Jxjh entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jxjh", catalog = "study3")
public class Jxjh implements java.io.Serializable {

	// Fields

	private Integer id;
	private Teacher teacher;
	private CourseChapter courseChapter;
	private String xq;
	

	// Constructors

	/** default constructor */
	public Jxjh() {
	}

	/** full constructor */
	public Jxjh(Teacher teacher, CourseChapter courseChapter, String xq) {
		this.teacher = teacher;
		this.courseChapter = courseChapter;
		this.xq = xq;
		
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jsbh")
	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kch")
	public CourseChapter getCourseChapter() {
		return this.courseChapter;
	}

	public void setCourseChapter(CourseChapter courseChapter) {
		this.courseChapter = courseChapter;
	}

	@Column(name = "xq")
	public String getXq() {
		return this.xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}


}