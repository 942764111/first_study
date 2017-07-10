package xx.collection.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Cndir entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dirManagement", catalog = "study3")
public class DirManagement implements java.io.Serializable {

	// Fields

	private Integer classno;
	private Userinfo userinfo;
	private String classname;
	private String beizhu;
	private Set<DirManagement> children = new HashSet<DirManagement>();
	private DirManagement parent;

	// Constructors

	/** default constructor */
	public DirManagement() {
	}

	/** full constructor */
	public DirManagement(Userinfo userinfo, String classname,
			String beizhu) {
		this.userinfo = userinfo;
		this.classname = classname;
		this.beizhu = beizhu;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "classno", unique = true, nullable = false)
	public Integer getClassno() {
		return this.classno;
	}

	public void setClassno(Integer classno) {
		this.classno = classno;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "classname")
	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Column(name = "beizhu")
	public String getBeizhu() {
		return this.beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	/**
	 * @return the children
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="parent", fetch=FetchType.EAGER)
	public Set<DirManagement> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<DirManagement> children) {
		this.children = children;
	}

	/**
	 * @return the parent
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="parent_id")
	public DirManagement getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(DirManagement parent) {
		this.parent = parent;
	}

}