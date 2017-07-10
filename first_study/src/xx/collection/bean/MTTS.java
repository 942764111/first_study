/*
 *@(#)xx.collection.bean
 *@MTTS.java.java  
 *@创建时间:2011-11-8下午04:25:36
 *@作者：tongkesong
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.collection.bean;

import java.util.Date;

/**
 * @MTTS <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class MTTS {
	private Integer msgid;
	private Userinfo userinfo;
	private String senderid;
	private String msgTitle;
	private String msgcomments;
	private String sendtime;
	private String receivedtime;
	private Integer msgstate;
	public Integer getMsgid() {
		return msgid;
	}
	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgcomments() {
		return msgcomments;
	}
	public void setMsgcomments(String msgcomments) {
		this.msgcomments = msgcomments;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getReceivedtime() {
		return receivedtime;
	}
	public void setReceivedtime(String receivedtime) {
		this.receivedtime = receivedtime;
	}
	public Integer getMsgstate() {
		return msgstate;
	}
	public void setMsgstate(Integer msgstate) {
		this.msgstate = msgstate;
	}

}
