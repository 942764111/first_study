package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Scwj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zsd2", catalog = "study3")
public class Zsd2 implements java.io.Serializable {

	// Fields

	private String zsdid;
	private String zsdmc;
	private String kcid;
	private Jie jie;
	private String zsdms;

	/** default constructor */
	public Zsd2() {
	}


	/**
	 * @return the zsdid
	 */
	// Property accessors
		@Id
		@Column(name = "zsdid", unique = true, nullable = false)
	public String getZsdid() {
		return zsdid;
	}


	/**
	 * @param zsdid the zsdid to set
	 */
	public void setZsdid(String zsdid) {
		this.zsdid = zsdid;
	}


	/**
	 * @return the zsdmc
	 */
	@Column(name = "zsdmc")
	public String getZsdmc() {
		return zsdmc;
	}


	/**
	 * @param zsdmc the zsdmc to set
	 */
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}


	/**
	 * @return the kcid
	 */
	@Column(name = "kcid")
	public String getKcid() {
		return kcid;
	}


	/**
	 * @param kcid the kcid to set
	 */
	public void setKcid(String kcid) {
		this.kcid = kcid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "zbh", referencedColumnName = "zbh", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "c_id", referencedColumnName = "c_id", nullable = false, insertable = false, updatable = false) })
	public Jie getJie() {
		return this.jie;
	}

	public void setJie(Jie jie) {
		this.jie = jie;
	}


	/**
	 * @return the zsdms
	 */
	@Column(name = "zsdms")
	public String getZsdms() {
		return zsdms;
	}


	/**
	 * @param zsdms the zsdms to set
	 */
	public void setZsdms(String zsdms) {
		this.zsdms = zsdms;
	}

	
	
	
}