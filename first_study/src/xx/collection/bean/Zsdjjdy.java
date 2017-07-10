package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Zsdjjdy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zsdjjdy", catalog = "study3")
public class Zsdjjdy implements java.io.Serializable {

	// Fields

	private Integer zsdbh;
	private String fxdnr;
	private String jjdy;
	

	// Constructors

	/** default constructor */
	public Zsdjjdy() {
	}

	/** minimal constructor */
	public Zsdjjdy(Integer zsdbh) {
		this.zsdbh = zsdbh;
	}

	/** full constructor */
	public Zsdjjdy(Integer zsdbh, String fxdnr, String jjdy) {
		this.zsdbh = zsdbh;
		this.fxdnr = fxdnr;
		this.jjdy = jjdy;
		
	}

	// Property accessors
	@Id
	@Column(name = "zsdbh", unique = true, nullable = false)
	public Integer getZsdbh() {
		return this.zsdbh;
	}

	public void setZsdbh(Integer zsdbh) {
		this.zsdbh = zsdbh;
	}

	@Column(name = "fxdnr", length = 600)
	public String getFxdnr() {
		return this.fxdnr;
	}

	public void setFxdnr(String fxdnr) {
		this.fxdnr = fxdnr;
	}

	@Column(name = "jjdy", length = 600)
	public String getJjdy() {
		return this.jjdy;
	}

	public void setJjdy(String jjdy) {
		this.jjdy = jjdy;
	}


}