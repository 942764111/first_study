package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CourseChapter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course_chapter", catalog = "study3")
public class CourseChapter implements java.io.Serializable {

	// Fields

	private Integer zbh;
	private String CName;
	private String kcms;
	private String TCName;

	// Constructors

	/** default constructor */
	public CourseChapter() {
	}

	/** minimal constructor */
	public CourseChapter(Integer zbh) {
		this.zbh = zbh;
	}
	//这个是手动添加的
	public CourseChapter(String CName,int zbh) {
		this.CName=CName;
		this.zbh=zbh;
	}

	/** full constructor */
	public CourseChapter(Integer zbh, String CName, String kcms, String TCName) {
		this.zbh = zbh;
		this.CName = CName;
		this.kcms = kcms;
		this.TCName = TCName;
	}

	// Property accessors
	@Id
	@Column(name = "zbh", unique = true, nullable = false)
	public Integer getZbh() {
		return this.zbh;
	}

	public void setZbh(Integer zbh) {
		this.zbh = zbh;
	}

	@Column(name = "c_name", length = 20)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "kcms", length = 200)
	public String getKcms() {
		return this.kcms;
	}

	public void setKcms(String kcms) {
		this.kcms = kcms;
	}

	@Column(name = "t_c_name", length = 30)
	public String getTCName() {
		return this.TCName;
	}

	public void setTCName(String TCName) {
		this.TCName = TCName;
	}

	@Override
	public String toString() {
		return "CourseChapter [zbh=" + zbh + ", CName=" + CName + ", kcms="
				+ kcms + ", TCName=" + TCName + "]";
	}

}