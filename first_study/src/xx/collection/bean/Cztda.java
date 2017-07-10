package xx.collection.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Cztda entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cztda", catalog = "study3")
public class Cztda implements java.io.Serializable {

	// Fields

	private CztdaId id;
	private Czt czt;
	private Zsd zsd;
	private Integer bufz;
	private String sheetname;
	private String wz;
	private String da;
	private String cda;
	private String carea;
	private Integer yzsx;

	// Constructors

	/** default constructor */
	public Cztda() {
	}

	/** minimal constructor */
	public Cztda(CztdaId id, Czt czt) {
		this.id = id;
		this.czt = czt;
	}


	public Cztda(CztdaId id, Czt czt, Zsd zsd, Integer bufz, String sheetname,
			String wz, String da, String cda, String carea, Integer yzsx) {
		this.id = id;
		this.czt = czt;
		this.zsd = zsd;
		this.bufz = bufz;
		this.sheetname = sheetname;
		this.wz = wz;
		this.da = da;
		this.cda = cda;
		this.carea = carea;
		this.yzsx = yzsx;
	}

	public Cztda(CztdaId id, Integer bufz, String sheetname,
			String wz, String da, String cda, String carea, Integer yzsx) {
		this.id = id;
		this.bufz = bufz;
		this.sheetname = sheetname;
		this.wz = wz;
		this.da = da;
		this.cda = cda;
		this.carea = carea;
		this.yzsx = yzsx;
	}

	

	public Cztda(CztdaId id, String zsdmc,int zsdbh,int zbh,int cid, Integer bufz, String sheetname,
			String wz, String da, String cda, String carea, Integer yzsx) {
		this.id = id;
		this.zsd = new Zsd();
		zsd.setZsdmc(zsdmc);
		ZsdId zid=new ZsdId();
		zid.setZsdbh(zsdbh);
		zid.setZbh(zbh);
		zid.setCId(cid);
		zsd.setId(zid);
		this.bufz = bufz;
		this.sheetname = sheetname;
		this.wz = wz;
		this.da = da;
		this.cda = cda;
		this.carea = carea;
		this.yzsx = yzsx;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "bzxh", column = @Column(name = "bzxh", nullable = false)),
			@AttributeOverride(name = "dtTh", column = @Column(name = "dt_th", nullable = false)),
			@AttributeOverride(name = "sxh", column = @Column(name = "sxh", nullable = false)) })
	public CztdaId getId() {
		return this.id;
	}

	public void setId(CztdaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "sxh", referencedColumnName = "sxh", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "dt_th", referencedColumnName = "dt_th", nullable = false, insertable = false, updatable = false) })
	public Czt getCzt() {
		return this.czt;
	}

	public void setCzt(Czt czt) {
		this.czt = czt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "zsdbh", referencedColumnName = "zsdbh"),
			@JoinColumn(name = "zbh", referencedColumnName = "zbh"),
			@JoinColumn(name = "c_id", referencedColumnName = "c_id") })
	public Zsd getZsd() {
		return this.zsd;
	}

	public void setZsd(Zsd zsd) {
		this.zsd = zsd;
	}

	@Column(name = "bufz")
	public Integer getBufz() {
		return this.bufz;
	}

	public void setBufz(Integer bufz) {
		this.bufz = bufz;
	}

	@Column(name = "sheetname", length = 18)
	public String getSheetname() {
		return this.sheetname;
	}

	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}

	@Column(name = "wz", length = 10)
	public String getWz() {
		return this.wz;
	}

	public void setWz(String wz) {
		this.wz = wz;
	}

	@Column(name = "da", length = 30)
	public String getDa() {
		return this.da;
	}

	public void setDa(String da) {
		this.da = da;
	}

	@Column(name = "cda", length = 50)
	public String getCda() {
		return this.cda;
	}

	public void setCda(String cda) {
		this.cda = cda;
	}

	@Column(name = "carea", length = 20)
	public String getCarea() {
		return this.carea;
	}

	public void setCarea(String carea) {
		this.carea = carea;
	}

	@Column(name = "yzsx")
	public Integer getYzsx() {
		return this.yzsx;
	}

	public void setYzsx(Integer yzsx) {
		this.yzsx = yzsx;
	}

}