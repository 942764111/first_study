package xx.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UserInfo", schema = "dbo", catalog = "study2")
public class UserInfo implements java.io.Serializable {

	// Fields

	private String userId;
	private Integer roleid;
	private String userPw;
	private String safeQuestion;
	private String safeAnswer;
	private String type;
	private String typeId;
	

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public UserInfo(String userId, Integer roleid, String userPw,
			String safeQuestion, String safeAnswer, String type, String typeId) {
		this.userId = userId;
		this.roleid = roleid;
		this.userPw = userPw;
		this.safeQuestion = safeQuestion;
		this.safeAnswer = safeAnswer;
		this.type = type;
		this.typeId = typeId;
		
	}

	// Property accessors
	@Id
	@Column(name = "UserId", unique = true, nullable = false)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "roleid")
	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Column(name = "UserPW")
	public String getUserPw() {
		return this.userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	@Column(name = "SafeQuestion")
	public String getSafeQuestion() {
		return this.safeQuestion;
	}

	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}

	@Column(name = "SafeAnswer")
	public String getSafeAnswer() {
		return this.safeAnswer;
	}

	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}

	@Column(name = "Type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TypeId")
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}



}