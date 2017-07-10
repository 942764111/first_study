package xx.collection.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jxnr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zysc2", catalog = "study3")
public class Collect implements java.io.Serializable {

	// Fields

	private Integer id;
	private String scsj;
	private String zymc;
	private String zyms;
	private String zlid;
	private String userid;
	private String path;
	private String filepath;
	private String zsdid;
	private String filetype;
	public Collect() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "scsj")
	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}
	@Column(name = "zymc")
	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	@Column(name = "zyms")
	public String getZyms() {
		return zyms;
	}

	public void setZyms(String zyms) {
		this.zyms = zyms;
	}
	@Column(name = "zlid")
	public String getZlid() {
		return zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
	}
	@Column(name = "userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the filepath
	 */
	@Column(name = "filepath")
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the zsdid
	 */
	@Column(name = "zsdid")
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
	 * @return the filetype
	 */
	@Column(name = "filetype")
	public String getFiletype() {
		return filetype;
	}

	/**
	 * @param filetype the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	
}