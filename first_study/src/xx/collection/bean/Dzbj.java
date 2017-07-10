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
 * Dzbj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dzbj", catalog = "study3")
public class Dzbj implements java.io.Serializable {

	// Fields

	private DzbjId id;
	private Zidingyibijimulu zidingyibijimulu;
	private Integer xssxh;
	private String tmnr;
	private byte[] tmimage;
	private String zllx;
	private String zlid;
	private String biaojilx;
	private String biaojixianlx;
	private String biaojiys;
	private String weizhi;
	private Integer zuobiaoY;
	private Integer zuobiaoX;
	private Integer zuobiaoX1;
	private Integer zuobiaoY1;
	private Integer zuobiaoX2;
	

	private Integer zuobiaoY2;
	private String path;
	private String zsdid;


	// Constructors

	/**
	 * @return the filepath
	 */
	

	/** default constructor */
	public Dzbj() {
	}

	/** minimal constructor */
	public Dzbj(DzbjId id, Zidingyibijimulu zidingyibijimulu) {
		this.id = id;
		this.zidingyibijimulu = zidingyibijimulu;
	}

	/** full constructor */
	public Dzbj(DzbjId id, Zidingyibijimulu zidingyibijimulu, Integer xssxh,
			String tmnr, byte[] tmimage, String zllx, String zlid,
			String biaojilx, String biaojixianlx, String biaojiys,
			String weizhi, Integer zuobiaoY, Integer zuobiaoX,
			Integer zuobiaoX1, Integer zuobiaoY1, Integer zuobiaoX2,
			Integer zuobiaoY2, String path) {
		this.id = id;
		this.zidingyibijimulu = zidingyibijimulu;
		this.xssxh = xssxh;
		this.tmnr = tmnr;
		this.tmimage = tmimage;
		this.zllx = zllx;
		this.zlid = zlid;
		this.biaojilx = biaojilx;
		this.biaojixianlx = biaojixianlx;
		this.biaojiys = biaojiys;
		this.weizhi = weizhi;
		this.zuobiaoY = zuobiaoY;
		this.zuobiaoX = zuobiaoX;
		this.zuobiaoX1 = zuobiaoX1;
		this.zuobiaoY1 = zuobiaoY1;
		this.zuobiaoX2 = zuobiaoX2;
		this.zuobiaoY2 = zuobiaoY2;
		this.path = path;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "UserId", nullable = false, length = 10)),
			@AttributeOverride(name = "classno", column = @Column(name = "classno", nullable = false, length = 20)),
			@AttributeOverride(name = "tmbh", column = @Column(name = "tmbh", nullable = false)) })
	public DzbjId getId() {
		return this.id;
	}

	public void setId(DzbjId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "classno", referencedColumnName = "classno", nullable = false, insertable = false, updatable = false) })
	public Zidingyibijimulu getZidingyibijimulu() {
		return this.zidingyibijimulu;
	}

	public void setZidingyibijimulu(Zidingyibijimulu zidingyibijimulu) {
		this.zidingyibijimulu = zidingyibijimulu;
	}

	@Column(name = "xssxh")
	public Integer getXssxh() {
		return this.xssxh;
	}

	public void setXssxh(Integer xssxh) {
		this.xssxh = xssxh;
	}

	@Column(name = "tmnr", length = 400)
	public String getTmnr() {
		return this.tmnr;
	}

	public void setTmnr(String tmnr) {
		this.tmnr = tmnr;
	}

	@Column(name = "tmimage")
	public byte[] getTmimage() {
		return this.tmimage;
	}

	public void setTmimage(byte[] tmimage) {
		this.tmimage = tmimage;
	}

	@Column(name = "zllx", length = 8)
	public String getZllx() {
		return this.zllx;
	}

	public void setZllx(String zllx) {
		this.zllx = zllx;
	}

	@Column(name = "zlid", length = 10)
	public String getZlid() {
		return this.zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	@Column(name = "biaojilx", length = 8)
	public String getBiaojilx() {
		return this.biaojilx;
	}

	public void setBiaojilx(String biaojilx) {
		this.biaojilx = biaojilx;
	}

	@Column(name = "biaojixianlx", length = 8)
	public String getBiaojixianlx() {
		return this.biaojixianlx;
	}

	public void setBiaojixianlx(String biaojixianlx) {
		this.biaojixianlx = biaojixianlx;
	}

	@Column(name = "biaojiys", length = 3)
	public String getBiaojiys() {
		return this.biaojiys;
	}

	public void setBiaojiys(String biaojiys) {
		this.biaojiys = biaojiys;
	}

	@Column(name = "weizhi", length = 8)
	public String getWeizhi() {
		return this.weizhi;
	}

	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

	@Column(name = "zuobiao_y")
	public Integer getZuobiaoY() {
		return this.zuobiaoY;
	}

	public void setZuobiaoY(Integer zuobiaoY) {
		this.zuobiaoY = zuobiaoY;
	}

	@Column(name = "zuobiao_x")
	public Integer getZuobiaoX() {
		return this.zuobiaoX;
	}

	public void setZuobiaoX(Integer zuobiaoX) {
		this.zuobiaoX = zuobiaoX;
	}

	@Column(name = "zuobiao_x1")
	public Integer getZuobiaoX1() {
		return this.zuobiaoX1;
	}

	public void setZuobiaoX1(Integer zuobiaoX1) {
		this.zuobiaoX1 = zuobiaoX1;
	}

	@Column(name = "zuobiao_y1")
	public Integer getZuobiaoY1() {
		return this.zuobiaoY1;
	}

	public void setZuobiaoY1(Integer zuobiaoY1) {
		this.zuobiaoY1 = zuobiaoY1;
	}

	@Column(name = "zuobiao_x2")
	public Integer getZuobiaoX2() {
		return this.zuobiaoX2;
	}

	public void setZuobiaoX2(Integer zuobiaoX2) {
		this.zuobiaoX2 = zuobiaoX2;
	}

	@Column(name = "zuobiao_y2")
	public Integer getZuobiaoY2() {
		return this.zuobiaoY2;
	}

	public void setZuobiaoY2(Integer zuobiaoY2) {
		this.zuobiaoY2 = zuobiaoY2;
	}
	/**
	 * @return the zsdid
	 */
	@Column(name = "zsdid", length = 50)
	public String getZsdid() {
		return zsdid;
	}

	/**
	 * @param zsdid the zsdid to set
	 */
	public void setZsdid(String zsdid) {
		this.zsdid = zsdid;
	}

	@Column(name = "path", length = 50)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}