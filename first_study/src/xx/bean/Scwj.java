package xx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Scwj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "scwj", catalog = "study2")
public class Scwj implements java.io.Serializable {

	// Fields

	private Integer no;
	private String zlbh;
	private String filename;
	private String scwj;

	// Constructors

	/** default constructor */
	public Scwj() {
	}

	/** minimal constructor */
	public Scwj(Integer no) {
		this.no = no;
	}

	/** full constructor */
	public Scwj(Integer no, String zlbh, String filename, String scwj) {
		this.no = no;
		this.zlbh = zlbh;
		this.filename = filename;
		this.scwj = scwj;
	}

	// Property accessors
	@Id
	@Column(name = "no", unique = true, nullable = false)
	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(name = "zlbh", length = 16)
	public String getZlbh() {
		return this.zlbh;
	}

	public void setZlbh(String zlbh) {
		this.zlbh = zlbh;
	}

	@Column(name = "filename", length = 30)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "scwj")
	public String getScwj() {
		return this.scwj;
	}

	public void setScwj(String scwj) {
		this.scwj = scwj;
	}

}