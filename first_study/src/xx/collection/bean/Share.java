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
@Table(name = "share", catalog = "study3")
public class Share implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userid;
	private String gaiyao;
	private String zhichi;
	private String fandui;
	private String sharetime;//分享时间
	private String sharetype;//分享类型，文档，视频，思维导图三种
	private String filepath;//分享内容，主要包括地址，或者是json数据
	private String filename;
	private String zhichiuser;
	private String fanduiuser;
	private String zlid;
	private String zsdid;
	private String filetype;
	/** default constructor */
	public Share() {
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

	@Column(name = "userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "gaiyao")
	public String getGaiyao() {
		return gaiyao;
	}

	public void setGaiyao(String gaiyao) {
		this.gaiyao = gaiyao;
	}

	@Column(name = "zhichi")
	public String getZhichi() {
		return zhichi;
	}

	public void setZhichi(String zhichi) {
		this.zhichi = zhichi;
	}

	

	@Column(name = "sharetime")
	public String getSharetime() {
		return sharetime;
	}

	public void setSharetime(String sharetime) {
		this.sharetime = sharetime;
	}

	@Column(name = "sharetype")
	public String getSharetype() {
		return sharetype;
	}

	public void setSharetype(String sharetype) {
		this.sharetype = sharetype;
	}

	@Column(name = "fandui")
	public String getFandui() {
		return fandui;
	}

	public void setFandui(String fandui) {
		this.fandui = fandui;
	}

	@Column(name = "filepath")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Column(name = "filename")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "zhichiuser")
	public String getZhichiuser() {
		return zhichiuser;
	}

	public void setZhichiuser(String zhichiuser) {
		this.zhichiuser = zhichiuser;
	}

	@Column(name = "fanduiuser")
	public String getFanduiuser() {
		return fanduiuser;
	}

	public void setFanduiuser(String fanduiuser) {
		this.fanduiuser = fanduiuser;
	}

	@Column(name = "zlid")
	public String getZlid() {
		return zlid;
	}

	public void setZlid(String zlid) {
		this.zlid = zlid;
	}

	/**
	 * @return the zsdid
	 */
	@Column(name = "zsdid")
	public String getZsdid() {
		return zsdid;
	}

	/**
	 * @param zsdid the zsdid to set
	 */
	public void setZsdid(String zsdid) {
		this.zsdid = zsdid;
	}

	/**
	 * @return the filetype
	 */
	@Column(name = "filetype")
	public String getFiletype() {
		return filetype;
	}

	/**
	 * @param filetype the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	// Property accessor
}