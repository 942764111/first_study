package cn.mina;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://www.cnblogs.com/pctzhang/archive/2012/02/19/2358496.html
 * @author hyz
 *
 */
public class WebSocketIoHandler extends IoHandlerAdapter {

    public static final String INDEX_KEY = WebSocketIoHandler.class.getName() + ".INDEX";
    private static Logger log = LoggerFactory.getLogger(WebSocketIoHandler.class);
    private ChatServer chatServer = new ChatServer();
 

	public void messageReceived(IoSession session, Object message) throws Exception {
    	
    	
    	IoBuffer buffer = (IoBuffer)message;
    	byte[] b = new byte[buffer.limit()];  
    	buffer.get(b); 
    	Long sid = session.getId();
    	
    	if (!chatServer.ioSessionMap.containsKey(sid)) {  //新用户则进行握手协议
    		
    		log.info("创建新用户：" + sid);
    		
    		chatServer.ioSessionMap.put(sid, session);
    		
        	String m = new String(buffer.array());
			String sss = getSecWebSocketAccept(m);
			
			buffer.clear();
			buffer.put(sss.getBytes("utf-8"));
			buffer.flip();
			session.write(buffer);
			buffer.free();     	
        	
    	} else {  //发送消息
    		
    		chatServer.receivedMsg(session, b);
        	
    	}
    }
	
	

    public void sessionOpened(IoSession session) throws Exception {
        session.setAttribute(INDEX_KEY, 0);
    }
    
    public void sessionClosed(IoSession session) throws Exception {
    	chatServer.exitRoom(session);
    	
    }
    
    

    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception {   
        System.out.println( "IDLE " + session.getIdleCount( status ));   
    } 
    
	public static String getSecWebSocketAccept(String key) {
		String secKey = getSecWebSocketKey(key);

		String guid = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		secKey += guid;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(secKey.getBytes("iso-8859-1"), 0, secKey.length());
			byte[] sha1Hash = md.digest();
			secKey = base64Encode(sha1Hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String rtn = "HTTP/1.1 101 Switching Protocols\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept: "
				+ secKey + "\r\n\r\n";
		return rtn;
	}
	
	public static String getSecWebSocketKey(String req) {
		Pattern p = Pattern.compile("^(Sec-WebSocket-Key:).+",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher m = p.matcher(req);
		if (m.find()) {
			String foundstring = m.group();
			return foundstring.split(":")[1].trim();
		} else {
			return null;
		}

	}


	public static String base64Encode(byte[] input) {
		return new String(org.apache.mina.util.Base64.encodeBase64(input));
	}

	

}
