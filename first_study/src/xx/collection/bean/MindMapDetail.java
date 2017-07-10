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
@Table(name = "mapdetail", catalog = "study3")
public class MindMapDetail implements java.io.Serializable {

	private Integer id;
	private String nodeid;
	private String mapid;
	private String imgurl;
	private String name;
	private String type;
	private String teacher;
	private String coursetime;
	private String gaishu;
	private String jianyi;
	private String userid;
	// Constructors

	/** default constructor */
	public MindMapDetail() {
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

	@Column(name = "nodeid")
	public String getNodeid() {
		return nodeid;
	}


	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	@Column(name = "mapid")
	public String getMapid() {
		return mapid;
	}

	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	@Column(name = "imgurl")
	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "teacher")
	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	@Column(name = "coursetime")
	public String getCoursetime() {
		return coursetime;
	}

	public void setCoursetime(String coursetime) {
		this.coursetime = coursetime;
	}
	@Column(name = "gaishu")
	public String getGaishu() {
		return gaishu;
	}

	public void setGaishu(String gaishu) {
		this.gaishu = gaishu;
	}
	@Column(name = "jianyi")
	public String getJianyi() {
		return jianyi;
	}

	public void setJianyi(String jianyi) {
		this.jianyi = jianyi;
	}
	@Column(name = "userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	// Property accessors
	
	
}