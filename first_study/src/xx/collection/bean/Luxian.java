package xx.collection.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Scwj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "luxian", catalog = "study3")
public class Luxian implements java.io.Serializable {

	// Fields

	private Integer id;
	private String kecheng;
	private String fangxiang;
	private String shunxu;
	private String picurl;
	private String miaoshu;
	/**
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	/**
	 * @return the miaoshu
	 */
	public String getMiaoshu() {
		return miaoshu;
	}

	/**
	 * @param miaoshu the miaoshu to set
	 */
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	/** default constructor */
	public Luxian() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the kecheng
	 */
	@Column(name = "kecheng")
	public String getKecheng() {
		return kecheng;
	}

	/**
	 * @param kecheng the kecheng to set
	 */
	public void setKecheng(String kecheng) {
		this.kecheng = kecheng;
	}

	/**
	 * @return the fangxiang
	 */
	@Column(name = "fangxiang")
	public String getFangxiang() {
		return fangxiang;
	}

	/**
	 * @param fangxiang the fangxiang to set
	 */
	public void setFangxiang(String fangxiang) {
		this.fangxiang = fangxiang;
	}

	/**
	 * @return the shunxu
	 */
	@Column(name = "shunxu")
	public String getShunxu() {
		return shunxu;
	}

	/**
	 * @param shunxu the shunxu to set
	 */
	public void setShunxu(String shunxu) {
		this.shunxu = shunxu;
	}

	

	// Property accessor
}