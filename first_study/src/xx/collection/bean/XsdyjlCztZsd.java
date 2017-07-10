package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * XsdyjlCztZsd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xsdyjl_czt_zsd", catalog = "study3")
public class XsdyjlCztZsd implements java.io.Serializable {

	// Fields

	private Integer no;
	private Integer sjno;
	private String userId;
	private Integer sxh;
	private Integer dtTh;
	private Integer zsdbh;
	private String zqf;

	// Constructors

	/** default constructor */
	public XsdyjlCztZsd() {
	}

	/** minimal constructor */
	public XsdyjlCztZsd(Integer sjno, String userId, Integer sxh, Integer dtTh,
			Integer zsdbh) {
		this.sjno = sjno;
		this.userId = userId;
		this.sxh = sxh;
		this.dtTh = dtTh;
		this.zsdbh = zsdbh;
	}

	/** full constructor */
	public XsdyjlCztZsd(Integer sjno, String userId, Integer sxh, Integer dtTh,
			Integer zsdbh, String zqf) {
		this.sjno = sjno;
		this.userId = userId;
		this.sxh = sxh;
		this.dtTh = dtTh;
		this.zsdbh = zsdbh;
		this.zqf = zqf;
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

	@Column(name = "sjno", nullable = false)
	public Integer getSjno() {
		return this.sjno;
	}

	public void setSjno(Integer sjno) {
		this.sjno = sjno;
	}

	@Column(name = "UserId", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "sxh", nullable = false)
	public Integer getSxh() {
		return this.sxh;
	}

	public void setSxh(Integer sxh) {
		this.sxh = sxh;
	}

	@Column(name = "dt_th", nullable = false)
	public Integer getDtTh() {
		return this.dtTh;
	}

	public void setDtTh(Integer dtTh) {
		this.dtTh = dtTh;
	}

	@Column(name = "zsdbh", nullable = false)
	public Integer getZsdbh() {
		return this.zsdbh;
	}

	public void setZsdbh(Integer zsdbh) {
		this.zsdbh = zsdbh;
	}

	@Column(name = "zqf", length = 10)
	public String getZqf() {
		return this.zqf;
	}

	public void setZqf(String zqf) {
		this.zqf = zqf;
	}

}