package xx.collection.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Zidingyibijimulu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zidingyibijimulu", catalog = "study3")
public class Zidingyibijimulu implements java.io.Serializable {

	// Fields

	private ZidingyibijimuluId id;
	private Userinfo userinfo;
	private String classname;
	private String beizhu;
	private Set<Dzbj> dzbjs = new HashSet<Dzbj>(0);
    private String zlid;
	// Constructors

	/** default constructor */
	public Zidingyibijimulu() {
	}

	/** minimal constructor */
	public Zidingyibijimulu(ZidingyibijimuluId id, Userinfo userinfo) {
		this.id = id;
		this.userinfo = userinfo;
	}

	/** full constructor */
	public Zidingyibijimulu(ZidingyibijimuluId id, Userinfo userinfo,
			String classname, String beizhu, Set<Dzbj> dzbjs) {
		this.id = id;
		this.userinfo = userinfo;
		this.classname = classname;
		this.beizhu = beizhu;
		this.dzbjs = dzbjs;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "UserId", nullable = false, length = 10)),
			@AttributeOverride(name = "classno", column = @Column(name = "classno", nullable = false, length = 11)) })
	public ZidingyibijimuluId getId() {
		return this.id;
	}

	public void setId(ZidingyibijimuluId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId", nullable = false, insertable = false, updatable = false)
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "classname", length = 20)
	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Column(name = "beizhu", length = 200)
	public String getBeizhu() {
		return this.beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "zidingyibijimulu")
	public Set<Dzbj> getDzbjs() {
		return this.dzbjs;
	}

	@Column(name = "zlid")
	public void setDzbjs(Set<Dzbj> dzbjs) {
		this.dzbjs = dzbjs;
	}

	public String getZlid() {
		return zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

}