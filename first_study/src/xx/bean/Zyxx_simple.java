package xx.bean;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Zyxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zyxx", schema = "dbo", catalog = "study2")
public class Zyxx_simple implements java.io.Serializable {

	// Fields

	private Integer zybh;
	private Xuyan_simple xuyan;
	private String zymc;
	
	
	// Constructors

	/** default constructor */
	public Zyxx_simple() {
	}

	/** minimal constructor */
	public Zyxx_simple(Integer zybh) {
		this.zybh = zybh;
	}

	/** full constructor */
	public Zyxx_simple(Integer zybh, Xuyan_simple xuyan, String zymc) {
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

	@Column(name = "zymc")
	public String getZymc() {
		return this.zymc;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "xybh")
	public Xuyan_simple getXuyan() {
		return xuyan;
	}

	public void setXuyan(Xuyan_simple xuyan) {
		this.xuyan = xuyan;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

}