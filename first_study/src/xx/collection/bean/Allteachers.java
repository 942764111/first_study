package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Allteachers entity. @author gq
 */
@Entity
@Table(name = "allteachers", catalog = "study3")
public class Allteachers implements java.io.Serializable {

	// Fields

	private Integer id;
	private String jsbh;

	// Constructors

	/** default constructor */
	public Allteachers() {
	}

	/** full constructor */
	public Allteachers(String jsbh) {
		this.jsbh = jsbh;
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

	@Column(name = "jsbh", nullable = false, length = 20)
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

}