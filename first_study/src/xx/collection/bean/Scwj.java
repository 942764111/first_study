package xx.collection.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Scwj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "scwj", catalog = "study3")
public class Scwj implements java.io.Serializable {

	// Fields

	private Integer no;
	private Dmtzl dmtzl;
	private String filename;
	private byte[] files;
	private String filepath;//文件在线预览存储路径
	private String oldfilepath;//原始文件存储路径。
	private String searchfilepath;//查询文件存储路径
	private String thumbnails;
	private String fjxx;
	private Integer totalNum;

    private String zlms;//描述
    private String zsdmc;
    private String uploadTime;
    private String shareNum;
    private String downLoadNum;
    private String viewNum;
    private String userId;
    private String guanlian;
    private String kcmc;
    private String zsdid;
    private String filetype;
    private String fileusertype;
    /**
	 * @return the muluid
	 */


	private String muluid;
	// Constructors

	/**
	 * @return the kcmc
	 */
	

	/**
	 * @return the filetype
	 */


	/** default constructor */
	public Scwj() {
	}

	/** full constructor */
	public Scwj(Dmtzl dmtzl, String filename, byte[] files, String filepath,
			String thumbnails, String fjxx, Integer totalNum,String thumbnail,String describe,String zsdmc,
			String uploadTime,String shareTime,String downLoadNum,String viewNum,String kcmc) {
		this.dmtzl = dmtzl;
		this.filename = filename;
		this.files = files;
		this.filepath = filepath;
		this.thumbnails = thumbnails;
		this.fjxx = fjxx;
		this.totalNum = totalNum;
		this.kcmc=kcmc;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zlbh")
	public Dmtzl getDmtzl() {
		return this.dmtzl;
	}

	public void setDmtzl(Dmtzl dmtzl) {
		this.dmtzl = dmtzl;
	}

	@Column(name = "filename", length = 200)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Column(name = "kcmc")
	public String getKcmc() {
		return kcmc;
	}

	/**
	 * @param kcmc the kcmc to set
	 */
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	
	@Column(name = "muluid")
	public String getMuluid() {
		return muluid;
	}

	/**
	 * @param muluid the muluid to set
	 */
	public void setMuluid(String muluid) {
		this.muluid = muluid;
	}
	@Column(name = "files")
	public byte[] getFiles() {
		return this.files;
	}

	public void setFiles(byte[] files) {
		this.files = files;
	}

	@Column(name = "filepath")
	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Column(name = "thumbnails")
	public String getThumbnails() {
		return this.thumbnails;
	}

	public void setThumbnails(String thumbnails) {
		this.thumbnails = thumbnails;
	}

	@Column(name = "fjxx")
	public String getFjxx() {
		return this.fjxx;
	}

	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
	}

	/**
	 * @return the totalNum
	 */
	@Column(name = "totalNum")
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	@Column(name = "oldfilepath")
	public String getOldfilepath() {
		return oldfilepath;
	}

	public void setOldfilepath(String oldfilepath) {
		this.oldfilepath = oldfilepath;
	}
	@Column(name = "searchfilepath")
	public String getSearchfilepath() {
		return searchfilepath;
	}

	public void setSearchfilepath(String searchfilepath) {
		this.searchfilepath = searchfilepath;
	}
	

	@Column(name = "zlms")
	public String getZlms() {
		return zlms;
	}

	public void setZlms(String zlms) {
		this.zlms = zlms;
	}

	@Column(name = "zsdmc")
	public String getZsdmc() {
		return zsdmc;
	}
//
	public void setZsdmc(String zsdmc) {
		this.zsdmc = zsdmc;
	}
	@Column(name = "uploadtime")
	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Column(name = "sharenum")
	public String getShareNum() {
		return shareNum;
	}

	public void setShareNum(String shareNum) {
		this.shareNum = shareNum;
	}
	@Column(name = "downloadnum")
	public String getDownLoadNum() {
		return downLoadNum;
	}

	public void setDownLoadNum(String downLoadNum) {
		this.downLoadNum = downLoadNum;
	}
	@Column(name = "viewnum")
	public String getViewNum() {
		return viewNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}
	@Column(name = "userid")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the guanlian
	 */
	@Column(name = "guanlian")
	public String getGuanlian() {
		return guanlian;
	}

	/**
	 * @param guanlian the guanlian to set
	 */
	public void setGuanlian(String guanlian) {
		this.guanlian = guanlian;
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

	/**
	 * @return the fileusertype
	 */
	@Column(name = "fileusertype")
	public String getFileusertype() {
		return fileusertype;
	}

	/**
	 * @param fileusertype the fileusertype to set
	 */
	public void setFileusertype(String fileusertype) {
		this.fileusertype = fileusertype;
	}
	
}