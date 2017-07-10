package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sj", catalog = "study3")
public class Sj implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer no;
	private Integer xzth;
	private Integer pdth;
	private Integer cwth;

	// Constructors

	/** default constructor */
	public Sj() {
	}

	/** minimal constructor */
	public Sj(Integer no) {
		this.no = no;
	}

	/** full constructor */
	public Sj(Integer no, Integer xzth, Integer pdth, Integer cwth) {
		this.no = no;
		this.xzth = xzth;
		this.pdth = pdth;
		this.cwth = cwth;
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

	@Column(name = "no", nullable = false)
	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(name = "xzth")
	public Integer getXzth() {
		return this.xzth;
	}

	public void setXzth(Integer xzth) {
		this.xzth = xzth;
	}

	@Column(name = "pdth")
	public Integer getPdth() {
		return this.pdth;
	}

	public void setPdth(Integer pdth) {
		this.pdth = pdth;
	}

	@Column(name = "cwth")
	public Integer getCwth() {
		return this.cwth;
	}

	public void setCwth(Integer cwth) {
		this.cwth = cwth;
	}

}