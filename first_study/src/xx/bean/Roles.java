package xx.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roles", schema = "dbo", catalog = "study2")
public class Roles implements java.io.Serializable {

	// Fields

	private Integer roleid;
	private String rolename;
	private String miaoshu;

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** minimal constructor */
	public Roles(Integer roleid) {
		this.roleid = roleid;
	}

	/** full constructor */
	public Roles(Integer roleid, String rolename, String miaoshu
			) {
		this.roleid = roleid;
		this.rolename = rolename;
		this.miaoshu = miaoshu;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "roleid", unique = true, nullable = false)
	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Column(name = "rolename")
	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name = "miaoshu")
	public String getMiaoshu() {
		return this.miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}


}