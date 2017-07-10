package xx.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Zyxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zyxx", schema = "dbo", catalog = "study2")
public class Zyxx implements java.io.Serializable {

	// Fields

	private Integer zybh;
	private Xuyan xuyan;
	private String zymc;
	

	// Constructors

	/** default constructor */
	public Zyxx() {
	}

	/** minimal constructor */
	public Zyxx(Integer zybh) {
		this.zybh = zybh;
	}

	/** full constructor */
	public Zyxx(Integer zybh, Xuyan xuyan, String zymc) {
		this.zybh = zybh;
		this.xuyan = xuyan;
		this.zymc = zymc;
		
	}

	// Property accessors
	@Id
	@Column(name = "zybh", unique = true, nullable = false)
	public Integer getZybh() {
		return this.zybh;
	}

	public void setZybh(Integer zybh) {
		this.zybh = zybh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "xybh")
	public Xuyan getXuyan() {
		return this.xuyan;
	}

	public void setXuyan(Xuyan xuyan) {
		this.xuyan = xuyan;
	}

	@Column(name = "zymc")
	public String getZymc() {
		return this.zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}


}