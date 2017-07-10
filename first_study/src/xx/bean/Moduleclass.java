package xx.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Moduleclass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "moduleclass", schema = "dbo", catalog = "study2")
public class Moduleclass implements java.io.Serializable {

	// Fields

	private String mclassname;
	private String comments;
	private Integer classolder;
	private String flimage;


	// Constructors

	/** default constructor */
	public Moduleclass() {
	}

	/** minimal constructor */
	public Moduleclass(String mclassname) {
		this.mclassname = mclassname;
	}

	/** full constructor */
	public Moduleclass(String mclassname, String comments, Integer classolder,
			String flimage) {
		this.mclassname = mclassname;
		this.comments = comments;
		this.classolder = classolder;
		this.flimage = flimage;
		
	}

	// Property accessors
	@Id
	@Column(name = "mclassname", unique = true, nullable = false)
	public String getMclassname() {
		return this.mclassname;
	}

	public void setMclassname(String mclassname) {
		this.mclassname = mclassname;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "classolder")
	public Integer getClassolder() {
		return this.classolder;
	}

	public void setClassolder(Integer classolder) {
		this.classolder = classolder;
	}

	@Column(name = "flimage")
	public String getFlimage() {
		return this.flimage;
	}

	public void setFlimage(String flimage) {
		this.flimage = flimage;
	}

}