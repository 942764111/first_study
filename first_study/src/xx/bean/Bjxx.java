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
 * Bjxx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bjxx", schema = "dbo", catalog = "study2")
public class Bjxx implements java.io.Serializable {

	// Fields

	private Integer bjbh;
	private Zyxx zyxx;
	private String bjmc;
	private Timestamp rxny;
	

	// Constructors

	/** default constructor */
	public Bjxx() {
	}

	/** minimal constructor */
	public Bjxx(Integer bjbh) {
		this.bjbh = bjbh;
	}

	/** full constructor */
	public Bjxx(Integer bjbh, Zyxx zyxx, String bjmc, Timestamp rxny) {
		this.bjbh = bjbh;
		this.zyxx = zyxx;
		this.bjmc = bjmc;
		this.rxny = rxny;
		
	}

	// Property accessors
	@Id
	@Column(name = "bjbh", unique = true, nullable = false)
	public Integer getBjbh() {
		return this.bjbh;
	}

	public void setBjbh(Integer bjbh) {
		this.bjbh = bjbh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zybh")
	public Zyxx getZyxx() {
		return this.zyxx;
	}

	public void setZyxx(Zyxx zyxx) {
		this.zyxx = zyxx;
	}

	@Column(name = "bjmc")
	public String getBjmc() {
		return this.bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	@Column(name = "rxny", length = 16)
	public Timestamp getRxny() {
		return this.rxny;
	}

	public void setRxny(Timestamp rxny) {
		this.rxny = rxny;
	}


}