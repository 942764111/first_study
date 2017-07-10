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
@Table(name = "corverfile", catalog = "study3")
public class FileRoot implements java.io.Serializable {

	// Fields

	private Integer id;
	private String inputfile;
	private String outputfile;
	private String thumbnail;
	private String type;//


	// Constructors

	/** default constructor */
	public FileRoot() {
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

	@Column(name = "inputfile")
	public String getInputfile() {
		return inputfile;
	}


	public void setInputfile(String inputfile) {
		this.inputfile = inputfile;
	}

	@Column(name = "outputfile")
	public String getOutputfile() {
		return outputfile;
	}


	public void setOutputfile(String outputfile) {
		this.outputfile = outputfile;
	}

	@Column(name = "thumbnail")
	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	
}