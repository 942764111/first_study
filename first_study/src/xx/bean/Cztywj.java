package xx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cztywj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cztywj", catalog = "study2")
public class Cztywj implements java.io.Serializable {

	// Fields

	private Integer bh;
	private String th;
	private String file;
	private String ms;

	// Constructors

	/** default constructor */
	public Cztywj() {
	}

	/** minimal constructor */
	public Cztywj(Integer bh) {
		this.bh = bh;
	}

	/** full constructor */
	public Cztywj(Integer bh, String th, String file, String ms) {
		this.bh = bh;
		this.th = th;
		this.file = file;
		this.ms = ms;
	}

	// Property accessors
	@Id
	@Column(name = "bh", unique = true, nullable = false)
	public Integer getBh() {
		return this.bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	@Column(name = "th", length = 16)
	public String getTh() {
		return this.th;
	}

	public void setTh(String th) {
		this.th = th;
	}

	@Column(name = "file")
	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Column(name = "ms", length = 50)
	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

}