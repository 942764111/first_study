package xx.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CztdaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CztdaId implements java.io.Serializable {

	// Fields

	private String cztTh;
	private Integer sxh;
	private Integer bzxh;
	private Integer zsdbh;
	private Integer zbh;
	private Integer CId;
	private Integer bufz;

	// Constructors

	/** default constructor */
	public CztdaId() {
	}

	/** full constructor */
	public CztdaId(String cztTh, Integer sxh, Integer bzxh, Integer zsdbh,
			Integer zbh, Integer CId, Integer bufz) {
		this.cztTh = cztTh;
		this.sxh = sxh;
		this.bzxh = bzxh;
		this.zsdbh = zsdbh;
		this.zbh = zbh;
		this.CId = CId;
		this.bufz = bufz;
	}

	// Property accessors

	@Column(name = "czt_th")
	public String getCztTh() {
		return this.cztTh;
	}

	public void setCztTh(String cztTh) {
		this.cztTh = cztTh;
	}

	@Column(name = "sxh")
	public Integer getSxh() {
		return this.sxh;
	}

	public void setSxh(Integer sxh) {
		this.sxh = sxh;
	}

	@Column(name = "bzxh")
	public Integer getBzxh() {
		return this.bzxh;
	}

	public void setBzxh(Integer bzxh) {
		this.bzxh = bzxh;
	}

	@Column(name = "zsdbh")
	public Integer getZsdbh() {
		return this.zsdbh;
	}

	public void setZsdbh(Integer zsdbh) {
		this.zsdbh = zsdbh;
	}

	@Column(name = "zbh")
	public Integer getZbh() {
		return this.zbh;
	}

	public void setZbh(Integer zbh) {
		this.zbh = zbh;
	}

	@Column(name = "c_id")
	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	@Column(name = "bufz")
	public Integer getBufz() {
		return this.bufz;
	}

	public void setBufz(Integer bufz) {
		this.bufz = bufz;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CztdaId))
			return false;
		CztdaId castOther = (CztdaId) other;

		return ((this.getCztTh() == castOther.getCztTh()) || (this.getCztTh() != null
				&& castOther.getCztTh() != null && this.getCztTh().equals(
				castOther.getCztTh())))
				&& ((this.getSxh() == castOther.getSxh()) || (this.getSxh() != null
						&& castOther.getSxh() != null && this.getSxh().equals(
						castOther.getSxh())))
				&& ((this.getBzxh() == castOther.getBzxh()) || (this.getBzxh() != null
						&& castOther.getBzxh() != null && this.getBzxh()
						.equals(castOther.getBzxh())))
				&& ((this.getZsdbh() == castOther.getZsdbh()) || (this
						.getZsdbh() != null
						&& castOther.getZsdbh() != null && this.getZsdbh()
						.equals(castOther.getZsdbh())))
				&& ((this.getZbh() == castOther.getZbh()) || (this.getZbh() != null
						&& castOther.getZbh() != null && this.getZbh().equals(
						castOther.getZbh())))
				&& ((this.getCId() == castOther.getCId()) || (this.getCId() != null
						&& castOther.getCId() != null && this.getCId().equals(
						castOther.getCId())))
				&& ((this.getBufz() == castOther.getBufz()) || (this.getBufz() != null
						&& castOther.getBufz() != null && this.getBufz()
						.equals(castOther.getBufz())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCztTh() == null ? 0 : this.getCztTh().hashCode());
		result = 37 * result
				+ (getSxh() == null ? 0 : this.getSxh().hashCode());
		result = 37 * result
				+ (getBzxh() == null ? 0 : this.getBzxh().hashCode());
		result = 37 * result
				+ (getZsdbh() == null ? 0 : this.getZsdbh().hashCode());
		result = 37 * result
				+ (getZbh() == null ? 0 : this.getZbh().hashCode());
		result = 37 * result
				+ (getCId() == null ? 0 : this.getCId().hashCode());
		result = 37 * result
				+ (getBufz() == null ? 0 : this.getBufz().hashCode());
		return result;
	}

}