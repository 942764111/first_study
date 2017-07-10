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
@Table(name = "commonfile", catalog = "study3")
public class CommonFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private String zlmc;
	private String path;
	private Integer zlid;
    private String uid;
    
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "zlmc")
	public String getZlmc() {
		return zlmc;
	}
	public void setZlmc(String zlmc) {
		this.zlmc = zlmc;
	}
	@Column(name = "path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name = "zlid")
	public Integer getZlid() {
		return zlid;
	}
	public void setZlid(Integer zlid) {
		this.zlid = zlid;
	}
	@Column(name = "uid")
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	

	// Property accessors
	

}