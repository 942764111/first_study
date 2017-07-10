package xx.collection.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Scwj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gongxiang", catalog = "study3")
public class Gongxiang implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userid;
	
	private String filepath;//分享内容，主要包括地址，或者是json数据
	private String filename;
	private String picurl;
	private String zlid;
	/** default constructor */
	public Gongxiang() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "filepath")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Column(name = "filename")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "zlid")
	public String getZlid() {
		return zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	/**
	 * @return the picurl
	 */
	@Column(name = "picurl")
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	// Property accessor
}