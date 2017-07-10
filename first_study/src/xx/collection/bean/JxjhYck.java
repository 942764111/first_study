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
 * JxjhYck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jxjh_yck", catalog = "study3")
public class JxjhYck implements java.io.Serializable {

	// Fields

	private Integer no;
	private CourseChapter courseChapter;
	private String yckms;
	private Integer xsh;
	private String jxjhSz;
	

	// Constructors

	/** default constructor */
	public JxjhYck() {
	}

	/** full constructor */
	public JxjhYck(CourseChapter courseChapter, String yckms, Integer xsh,
			String jxjhSz) {
		this.courseChapter = courseChapter;
		this.yckms = yckms;
		this.xsh = xsh;
		this.jxjhSz = jxjhSz;
		
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "no", unique = true, nullable = false)
	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zbh")
	public CourseChapter getCourseChapter() {
		return this.courseChapter;
	}

	public void setCourseChapter(CourseChapter courseChapter) {
		this.courseChapter = courseChapter;
	}

	@Column(name = "yckms")
	public String getYckms() {
		return this.yckms;
	}

	public void setYckms(String yckms) {
		this.yckms = yckms;
	}

	@Column(name = "xsh")
	public Integer getXsh() {
		return this.xsh;
	}

	public void setXsh(Integer xsh) {
		this.xsh = xsh;
	}

	@Column(name = "jxjh_sz")
	public String getJxjhSz() {
		return this.jxjhSz;
	}

	public void setJxjhSz(String jxjhSz) {
		this.jxjhSz = jxjhSz;
	}

	

}