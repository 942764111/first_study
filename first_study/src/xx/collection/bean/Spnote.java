package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Spnote entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "spnote", catalog = "study3")
public class Spnote implements java.io.Serializable {

	// Fields

	private Integer no;
	private String nr;

	// Constructors

	/** default constructor */
	public Spnote() {
	}

	/** full constructor */
	public Spnote(String nr) {
		this.nr = nr;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "no", unique = true, nullable = false)
	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(name = "nr", length = 300)
	public String getNr() {
		return this.nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

}