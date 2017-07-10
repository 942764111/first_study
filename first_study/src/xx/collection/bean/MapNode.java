package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Scwj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mapnode", catalog = "study3")
public class MapNode implements java.io.Serializable {

	// Fields

	private String nodeid;
	private String nodename;
	private String parentid;
	private String userid;
	private String type; 
	// Constructors
	


	/** default constructor */
	public MapNode() {
	}
	/**
	 * @return the nodeid
	 */
	@Id
	@Column(name = "nodeid", unique = true, nullable = false)
	public String getNodeid() {
		return nodeid;
	}
	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	/**
	 * @return the nodename
	 */
	@Column(name = "nodename")
	public String getNodename() {
		return nodename;
	}
	/**
	 * @param nodename the nodename to set
	 */
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	/**
	 * @return the parentid
	 */
	@Column(name = "parentid")
	public String getParentid() {
		return parentid;
	}
	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * @return the userid
	 */
	@Column(name = "userid")
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the type
	 */
	@Column(name = "type")
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	
	/**
	 * @return the type
	 */
	

	// Property accessors
	
	
}