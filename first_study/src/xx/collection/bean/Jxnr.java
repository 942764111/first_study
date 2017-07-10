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
 * Jxnr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jxnr", catalog = "study3")
public class Jxnr implements java.io.Serializable {

	// Fields

	private Integer id;
	private JxjhYck jxjhYck;
	private String wjmc;
	private String wjms;
	private String filepath;
	private String jxjhSz;
	private Integer zlid;

	// Constructors

	/** default constructor */
	public Jxnr() {
	}

	/** full constructor */
	public Jxnr(JxjhYck jxjhYck, String wjmc, String wjms, String filepath,
			String jxjhSz, Integer zlid) {
		this.jxjhYck = jxjhYck;
		this.wjmc = wjmc;
		this.wjms = wjms;
		this.filepath = filepath;
		this.jxjhSz = jxjhSz;
		this.zlid = zlid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "jh_id")
	public JxjhYck getJxjhYck() {
		return this.jxjhYck;
	}

	public void setJxjhYck(JxjhYck jxjhYck) {
		this.jxjhYck = jxjhYck;
	}

	@Column(name = "wjmc")
	public String getWjmc() {
		return this.wjmc;
	}

	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}

	@Column(name = "wjms", length = 1000)
	public String getWjms() {
		return this.wjms;
	}

	public void setWjms(String wjms) {
		this.wjms = wjms;
	}

	@Column(name = "filepath")
	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Column(name = "jxjh_sz")
	public String getJxjhSz() {
		return this.jxjhSz;
	}

	public void setJxjhSz(String jxjhSz) {
		this.jxjhSz = jxjhSz;
	}

	@Column(name = "zlid")
	public Integer getZlid() {
		return this.zlid;
	}

	public void setZlid(Integer zlid) {
		this.zlid = zlid;
	}

}