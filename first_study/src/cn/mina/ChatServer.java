package cn.mina;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatServer {

	private static Logger log = LoggerFactory.getLogger(ChatServer.class);
	public  Map<Long, IoSession> ioSessionMap = new HashMap<Long, IoSession>();
	public  static Map<Long, String> userInfo = new HashMap<Long, String>();
	private IoSession ioSession;
	private HashMap<String, String> msgMap;

	//接收消息
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void receivedMsg(IoSession session, byte[] b) {
	
		try {

			ioSession = session;
			msgMap = (HashMap<String, String>) JSONObject
					.toBean(JSONObject.fromObject(decode(b).trim()), HashMap.class);

			if ("login".equals(msgMap.get("msgtype"))) { // 相当于进入房间

				userInfo.put(session.getId(),  msgMap.get("username")); // 保存用户名
				System.out.println("username:"+msgMap.get("username"));
				this.enterRoom();

			} else if("chat".equals(msgMap.get("msgtype"))){ //普通聊天	

				this.chat2All();

			}else if("exit".equals(msgMap.get("msgtype"))){ //离开房间	
				session.close();
				this.exitRoom(session);
				//cale
			}

		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
			log.debug("服务器发送消息出错");
		}

	}


	/**
	 * 群聊
	 */
	public void chat2All(){

		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());

		String msg = msgMap.get("msg");

		String usermsg = "<div class='itemdiv dialogdiv'><div class='user'><img alt='Jim's Avatar' src='../../css/ace/avatar4.png' /></div>"

	+"<div class='body'><div class='time'><i class='icon-time'></i><span class='grey'>"+dateStr+"</span></div><div class='name'><a href='#'>"+userInfo.get(ioSession.getId())+"</a></div>"+
	"<div class='text'>"+msg+"</div><div class='tools'><a href='#' class='btn btn-minier btn-info'><i class='icon-only icon-share-alt'></i></a></div></div></div>";

		msgMap.put("msg", usermsg);


		sendMsg2OtherAll(JSONObject.fromObject(msgMap).toString());
		usermsg = "<div class='itemdiv dialogdiv'><div class='user'><img alt='Jim's Avatar' src='../../css/ace/avatar4.png' /></div>"

			+"<div class='body'><div class='time'><i class='icon-time'></i><span class='grey'>"+dateStr+"</span></div><div class='name'><a href='#' style='color:red'>"+userInfo.get(ioSession.getId())+"</a></div>"+
			"<div class='text'>"+msg+"</div><div class='tools'><a href='#' class='btn btn-minier btn-info'><i class='icon-only icon-share-alt'></i></a></div></div></div>";

		msgMap.put("msg", usermsg);

		sendMsg2Self(JSONObject.fromObject(msgMap).toString());

	}

	public void enterRoom(){

		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());


		String usermsg = "<div class='itemdiv dialogdiv'><div class='user'><img alt='Jim's Avatar' src='../../css/ace/avatar4.png' /></div>"

+"<div class='body'><div class='time'><i class='icon-time'></i><span class='grey'>"+dateStr+"</span></div><div class='name'><a href='#' style='color:#EE00EE'>【系统通知】</a></div>"+
"<div class='text'>"+userInfo.get(ioSession.getId())+" 进来了。。。</div><div class='tools'><a href='#' class='btn btn-minier btn-info'><i class='icon-only icon-share-alt'></i></a></div></div></div>";
		msgMap = new HashMap<String, String>();
		msgMap.put("msg", usermsg);
		msgMap.put("msgtype", "chat");

		sendMsg2OtherAll(JSONObject.fromObject(msgMap).toString());

		sendMsg2Self(JSONObject.fromObject(msgMap).toString());

	}

	/**
	 * 用户下线
	 */
	public void exitRoom(IoSession session){

		if(!ioSessionMap.containsKey(session.getId())){
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());


		String usermsg = "<div class='itemdiv dialogdiv'><div class='user'><img alt='Jim's Avatar' src='../../css/ace/avatar4.png' /></div>"

	+"<div class='body'><div class='time'><i class='icon-time'></i><span class='grey'>"+dateStr+"</span></div><div class='name'><a href='#' style='color:#EE00EE'>【系统通知】</a></div>"+
	"<div class='text'>"+userInfo.get(ioSession.getId())+" 下线了。。。</div><div class='tools'><a href='#' class='btn btn-minier btn-info'><i class='icon-only icon-share-alt'></i></a></div></div></div>";

		msgMap.put("msg", usermsg);
		msgMap.put("msgtype", "chat");

		sendMsg2OtherAll(JSONObject.fromObject(msgMap).toString());

		userInfo.remove(session.getId());


	}

	//发送消息给自己
	public  void sendMsg2Self(String sendMsg){

		try {
			IoBuffer ioBuffer = IoBuffer.allocate(8,false); //需要  重新获取个IoBuffer
			ioBuffer.setAutoExpand(true);  //设置自动扩容，否则会无法put
			ioBuffer.clear();
			byte[] bb = encode(sendMsg);
			ioBuffer.put(bb);
			ioBuffer.flip();
			if(ioSession.isConnected()){
				ioSession.write(ioBuffer.duplicate());
			}
			ioBuffer.free();
		} catch (Exception e) {
			log.debug("服务器回复本人信息出错！");
		}


	}

	//发送消息给其他所有用户
	public void sendMsg2OtherAll(String sendMsg){

		try {

			IoBuffer ioBuffer = IoBuffer.allocate(8,false); //需要  重新获取个IoBuffer
			ioBuffer.setAutoExpand(true);  //设置自动扩容，否则可能会无法put
			ioBuffer.clear();
			byte[] bb = encode(sendMsg);
			ioBuffer.put(bb);
			ioBuffer.flip();

			synchronized (ioSessionMap) {
				Collection<IoSession> ioSessionSet = ioSessionMap.values();
				for (IoSession is : ioSessionSet) {
					if(is.getId() != ioSession.getId() && is.isConnected() ){
						is.write(ioBuffer.duplicate());
					}
				}
			}

			ioBuffer.free();

		} catch (Exception e) {
			log.debug("服务器发送信息出错！");
		}

	}



	public String decode(byte[] receivedDataBuffer)
			throws UnsupportedEncodingException {

		String result = null;

		// 计算非空位置
		int lastStation = receivedDataBuffer.length - 1;

		// 利用掩码对org-data进行异或
		int frame_masking_key = 1;
		for (int i = 6; i <= lastStation; i++) {
			frame_masking_key = i % 4;
			frame_masking_key = frame_masking_key == 0 ? 4 : frame_masking_key;
			frame_masking_key = frame_masking_key == 1 ? 5 : frame_masking_key;
			receivedDataBuffer[i] = (byte) (receivedDataBuffer[i] ^ receivedDataBuffer[frame_masking_key]);
		}

		result = new String(receivedDataBuffer, 6, lastStation - 5, "UTF-8");

		return result;

	}

	// / 对传入数据进行无掩码转换
	public  byte[] encode(String msg) throws UnsupportedEncodingException {
		// 掩码�?��位置
		int masking_key_startIndex = 2;

		byte[] msgByte = msg.getBytes("UTF-8");

		// 计算掩码�?��位置
		if (msgByte.length <= 125) {
			masking_key_startIndex = 2;
		} else if (msgByte.length > 65536) {
			masking_key_startIndex = 10;
		} else if (msgByte.length > 125) {
			masking_key_startIndex = 4;
		}

		// 创建返回数据
		byte[] result = new byte[msgByte.length + masking_key_startIndex];

		// �?��计算ws-frame
		// frame-fin + frame-rsv1 + frame-rsv2 + frame-rsv3 + frame-opcode
		result[0] = (byte) 0x81; // 129

		// frame-masked+frame-payload-length
		// 从第9个字节开始是 1111101=125,掩码是第3-�?个数�?
		// 从第9个字节开始是 1111110>=126,掩码是第5-�?个数�?
		if (msgByte.length <= 125) {
			result[1] = (byte) (msgByte.length);
		} else if (msgByte.length > 65536) {
			result[1] = 0x7F; // 127
		} else if (msgByte.length > 125) {
			result[1] = 0x7E; // 126
			result[2] = (byte) (msgByte.length >> 8);
			result[3] = (byte) (msgByte.length % 256);
		}

		// 将数据编码放到
		for (int i = 0; i < msgByte.length; i++) {
			result[i + masking_key_startIndex] = msgByte[i];
		}

		return result;
	}


}
