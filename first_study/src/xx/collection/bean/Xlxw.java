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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Xlxw entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xlxw", catalog = "study3")
public class Xlxw implements java.io.Serializable {

	// Fields

	private Integer xlbh;
	private String xlmc;
	private String type;

	// Constructors

	/** default constructor */
	public Xlxw() {
	}

	/** full constructor */
	public Xlxw(String xlmc, String type) {
		this.xlmc = xlmc;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "xlbh", unique = true, nullable = false)
	public Integer getXlbh() {
		return this.xlbh;
	}

	public void setXlbh(Integer xlbh) {
		this.xlbh = xlbh;
	}

	@Column(name = "xlmc", length = 6)
	public String getXlmc() {
		return this.xlmc;
	}

	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}

	@Column(name = "type", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}