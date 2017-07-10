package xx.collection.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userinfo", catalog = "study3")
public class Userinfo implements java.io.Serializable {

	// Fields

	private String userId;
	private Roles roles;
	private String userPw;
	private String safeQuestion;
	private String safeAnswer;
	private String type;
	private String typeId;
	

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public Userinfo(String userId, Roles roles, String userPw,
			String safeQuestion, String safeAnswer, String type, String typeId) {
		this.userId = userId;
		this.roles = roles;
		this.userPw = userPw;
		this.safeQuestion = safeQuestion;
		this.safeAnswer = safeAnswer;
		this.type = type;
		this.typeId = typeId;
	}

	// Property accessors
	@Id
	@Column(name = "UserId", unique = true, nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid")
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@Column(name = "UserPW", length = 20)
	public String getUserPw() {
		return this.userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	@Column(name = "SafeQuestion", length = 200)
	public String getSafeQuestion() {
		return this.safeQuestion;
	}

	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}

	@Column(name = "SafeAnswer", length = 20)
	public String getSafeAnswer() {
		return this.safeAnswer;
	}

	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}

	@Column(name = "Type", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TypeId", length = 20)
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}


}