package xx.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Xlxwbh entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xlxwbh", schema = "dbo", catalog = "study2")
public class Xlxwbh implements java.io.Serializable {

	// Fields

	private Integer bh;
	private Teacher teacher;
	private Schoollist schoollist;
	private Xlxw xlxw;
	private Timestamp rxny;
	private Timestamp byny;
	private Timestamp pyfs;
	private String department;
	private String type;

	// Constructors

	/** default constructor */
	public Xlxwbh() {
	}

	/** minimal constructor */
	public Xlxwbh(Integer bh, Teacher teacher, Xlxw xlxw) {
		this.bh = bh;
		this.teacher = teacher;
		this.xlxw = xlxw;
	}

	/** full constructor */
	public Xlxwbh(Integer bh, Teacher teacher, Schoollist schoollist,
			Xlxw xlxw, Timestamp rxny, Timestamp byny, Timestamp pyfs,
			String department, String type) {
		this.bh = bh;
		this.teacher = teacher;
		this.schoollist = schoollist;
		this.xlxw = xlxw;
		this.rxny = rxny;
		this.byny = byny;
		this.pyfs = pyfs;
		this.department = department;
		this.type = type;
	}

	// Property accessors
	@Id
	@Column(name = "bh", unique = true, nullable = false)
	public Integer getBh() {
		return this.bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jsbh", nullable = false)
	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "xxbh")
	public Schoollist getSchoollist() {
		return this.schoollist;
	}

	public void setSchoollist(Schoollist schoollist) {
		this.schoollist = schoollist;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "xlbh", nullable = false)
	public Xlxw getXlxw() {
		return this.xlxw;
	}

	public void setXlxw(Xlxw xlxw) {
		this.xlxw = xlxw;
	}

	@Column(name = "rxny", length = 16)
	public Timestamp getRxny() {
		return this.rxny;
	}

	public void setRxny(Timestamp rxny) {
		this.rxny = rxny;
	}

	@Column(name = "byny", length = 16)
	public Timestamp getByny() {
		return this.byny;
	}

	public void setByny(Timestamp byny) {
		this.byny = byny;
	}

	@Column(name = "pyfs", length = 16)
	public Timestamp getPyfs() {
		return this.pyfs;
	}

	public void setPyfs(Timestamp pyfs) {
		this.pyfs = pyfs;
	}

	@Column(name = "department")
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}