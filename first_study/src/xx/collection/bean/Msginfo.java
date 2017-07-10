package xx.collection.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Msginfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msginfo", catalog = "study3")
public class Msginfo implements java.io.Serializable {

	// Fields

	private Integer msgid;
	private Userinfo userinfo;
	private String senderid;
	private String msgTitle;
	private String msgcomments;
	private Date sendtime;
	private Date receivedtime;
	private Integer msgstate;

	// Constructors

	/** default constructor */
	public Msginfo() {
	}

	/** minimal constructor */
	public Msginfo(Integer msgid) {
		this.msgid = msgid;
	}

	/** full constructor */
	public Msginfo(Integer msgid, Userinfo userinfo, String senderid,
			String msgTitle, String msgcomments, Date sendtime,
			Date receivedtime, Integer msgstate) {
		this.msgid = msgid;
		this.userinfo = userinfo;
		this.senderid = senderid;
		this.msgTitle = msgTitle;
		this.msgcomments = msgcomments;
		this.sendtime = sendtime;
		this.receivedtime = receivedtime;
		this.msgstate = msgstate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "msgid", unique = true, nullable = false)
	public Integer getMsgid() {
		return this.msgid;
	}

	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "senderid", length = 10)
	public String getSenderid() {
		return this.senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	@Column(name = "msgTitle", length = 20)
	public String getMsgTitle() {
		return this.msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	@Column(name = "msgcomments", length = 200)
	public String getMsgcomments() {
		return this.msgcomments;
	}

	public void setMsgcomments(String msgcomments) {
		this.msgcomments = msgcomments;
	}

	@Column(name = "sendtime", length = 19)
	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "receivedtime", length = 19)
	public Date getReceivedtime() {
		return this.receivedtime;
	}

	public void setReceivedtime(Date receivedtime) {
		this.receivedtime = receivedtime;
	}

	@Column(name = "msgstate")
	public Integer getMsgstate() {
		return this.msgstate;
	}

	public void setMsgstate(Integer msgstate) {
		this.msgstate = msgstate;
	}

}