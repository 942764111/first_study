package xx.collection.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Modules entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "modules", catalog = "study3")
public class Modules implements java.io.Serializable {

	// Fields

	private String mname;
	private Moduleclass moduleclass;
	private String comments;
	private Integer molderid;
	private byte[] mimage;

	// Constructors

	/** default constructor */
	public Modules() {
	}

	/** minimal constructor */
	public Modules(String mname) {
		this.mname = mname;
	}

	/** full constructor */
	public Modules(String mname, Moduleclass moduleclass, String comments,
			Integer molderid, byte[] mimage) {
		this.mname = mname;
		this.moduleclass = moduleclass;
		this.comments = comments;
		this.molderid = molderid;
		this.mimage = mimage;
	}

	// Property accessors
	@Id
	@Column(name = "mname", unique = true, nullable = false, length = 10)
	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mclassname")
	public Moduleclass getModuleclass() {
		return this.moduleclass;
	}

	public void setModuleclass(Moduleclass moduleclass) {
		this.moduleclass = moduleclass;
	}

	@Column(name = "comments", length = 200)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "molderid")
	public Integer getMolderid() {
		return this.molderid;
	}

	public void setMolderid(Integer molderid) {
		this.molderid = molderid;
	}

	@Column(name = "mimage")
	public byte[] getMimage() {
		return this.mimage;
	}

	public void setMimage(byte[] mimage) {
		this.mimage = mimage;
	}
}