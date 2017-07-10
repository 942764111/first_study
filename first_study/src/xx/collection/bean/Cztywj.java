package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Cztywj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cztywj", catalog = "study3")
public class Cztywj implements java.io.Serializable {

	// Fields

	private Integer bh;
	private Cztd cztd;
	private byte[] file;
	private String ms;
	private String wjmc;

	// Constructors

	/** default constructor */
	public Cztywj() {
	}

	/** full constructor */
	public Cztywj(Cztd cztd, byte[] file, String ms, String wjmc) {
		this.cztd = cztd;
		this.file = file;
		this.ms = ms;
		this.wjmc = wjmc;
	}

	public Cztywj( byte[] file, String ms, String wjmc) {
		this.file = file;
		this.ms = ms;
		this.wjmc = wjmc;
	}
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bh", unique = true, nullable = false)
	public Integer getBh() {
		return this.bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "th")
	public Cztd getCztd() {
		return this.cztd;
	}

	public void setCztd(Cztd cztd) {
		this.cztd = cztd;
	}

	@Column(name = "file")
	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@Column(name = "ms", length = 50)
	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	@Column(name = "wjmc", length = 40)
	public String getWjmc() {
		return this.wjmc;
	}

	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}

}