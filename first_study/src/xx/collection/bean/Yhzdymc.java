package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Yhzdymc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yhzdymc", catalog = "study3")
public class Yhzdymc implements java.io.Serializable {

	// Fields

	private Integer zdyflno;
	private Userinfo userinfo;
	private String zdyflmc;
	private Integer zysl;
	

	// Constructors

	/** default constructor */
	public Yhzdymc() {
	}

	/** minimal constructor */
	public Yhzdymc(Integer zdyflno, Userinfo userinfo) {
		this.zdyflno = zdyflno;
		this.userinfo = userinfo;
	}

	/** full constructor */
	public Yhzdymc(Integer zdyflno, Userinfo userinfo, String zdyflmc,
			Integer zysl) {
		this.zdyflno = zdyflno;
		this.userinfo = userinfo;
		this.zdyflmc = zdyflmc;
		this.zysl = zysl;
		
	}

	// Property accessors
	@Id
	@Column(name = "zdyflno", unique = true, nullable = false)
	@GeneratedValue
	public Integer getZdyflno() {
		return this.zdyflno;
	}

	public void setZdyflno(Integer zdyflno) {
		this.zdyflno = zdyflno;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId", nullable = false)
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "zdyflmc", length = 20)
	public String getZdyflmc() {
		return this.zdyflmc;
	}

	public void setZdyflmc(String zdyflmc) {
		this.zdyflmc = zdyflmc;
	}

	@Column(name = "zysl")
	public Integer getZysl() {
		return this.zysl;
	}

	public void setZysl(Integer zysl) {
		this.zysl = zysl;
	}



}