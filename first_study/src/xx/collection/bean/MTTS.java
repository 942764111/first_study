/*
 *@(#)xx.collection.bean
 *@MTTS.java.java  
 *@����ʱ��:2011-11-8����04:25:36
 *@���ߣ�tongkesong
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.collection.bean;

import java.util.Date;

/**
 * @MTTS <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
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
