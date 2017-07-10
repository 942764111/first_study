package xx.email;


import java.util.*;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendHtml {
	
	public static void sendHtml(){
		Properties props = new Properties();
		props.put("mail.smtp.host",Constant.mailServer);
		props.put("mail.smtp.auth", "true");
		Session s = Session.getInstance(props);
		s.setDebug(true);
		
		MimeMessage message = new MimeMessage(s);
		try {
			InternetAddress from = new InternetAddress(Constant.mailAddress);
			message.setFrom(from);
			InternetAddress to = new InternetAddress("1499938098@qq.com");
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject("链接百度");
			message.setSentDate(new Date());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BodyPart bp = new MimeBodyPart();
		Multipart mm = new MimeMultipart();
		try {
			bp.setContent("这是html格式邮件的内容<a href='http://www.baidu.com/'>百度主页</a>","text/html;charset=gbk	");
			mm.addBodyPart(bp);
			message.setContent(mm);
			message.saveChanges();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Transport transport = s.getTransport("smtp");
			transport.connect(Constant.mailServer,Constant.mailCount,Constant.mailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sendHtml();
	}

}
