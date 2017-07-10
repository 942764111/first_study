package xx.collection.bean;

import java.util.Date;
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
@Table(name = "bjxx", catalog = "study3")
public class Bjxx implements java.io.Serializable {

	// Fields

	private Integer bjbh;
	private Zyxx zyxx;
	private String bjmc;
	private Date rxny;
	

	// Constructors

	/** default constructor */
	public Bjxx() {
	}

	/** minimal constructor */
	public Bjxx(Integer bjbh) {
		this.bjbh = bjbh;
	}

	/** full constructor */
	public Bjxx(Integer bjbh, Zyxx zyxx, String bjmc, Date rxny) {
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

	@Column(name = "bjmc", length = 20)
	public String getBjmc() {
		return this.bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	@Column(name = "rxny", length = 19)
	public Date getRxny() {
		return this.rxny;
	}

	public void setRxny(Date rxny) {
		this.rxny = rxny;
	}


}