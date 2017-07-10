package xx.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Xlxw entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xlxw", schema = "dbo", catalog = "study2")
public class Xlxw implements java.io.Serializable {

	// Fields

	private Integer xlbh;
	private String xlmc;
	private String type;
	

	// Constructors

	/** default constructor */
	public Xlxw() {
	}

	/** minimal constructor */
	public Xlxw(Integer xlbh) {
		this.xlbh = xlbh;
	}

	/** full constructor */
	public Xlxw(Integer xlbh, String xlmc, String type) {
		this.xlbh = xlbh;
		this.xlmc = xlmc;
		this.type = type;
		
	}

	// Property accessors
	@Id
	@Column(name = "xlbh", unique = true, nullable = false)
	public Integer getXlbh() {
		return this.xlbh;
	}

	public void setXlbh(Integer xlbh) {
		this.xlbh = xlbh;
	}

	@Column(name = "xlmc")
	public String getXlmc() {
		return this.xlmc;
	}

	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}