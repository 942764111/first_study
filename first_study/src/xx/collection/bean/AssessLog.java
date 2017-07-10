package xx.collection.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jxnr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "accesslog", catalog = "study3")
public class AssessLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String ipaddress;
	private String logintime;
	public AssessLog() {
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

	/**
	 * @return the username
	 */
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the ipaddress
	 */
	@Column(name = "ipaddress")
	public String getIpaddress() {
		return ipaddress;
	}

	/**
	 * @param ipaddress the ipaddress to set
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	/**
	 * @return the logintime
	 */
	@Column(name = "logintime")
	public String getLogintime() {
		return logintime;
	}

	/**
	 * @param logintime the logintime to set
	 */
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	
	
}