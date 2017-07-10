
package xx.email;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

public class B
{
	 public void send(String email,String content) throws MessagingException, IOException
	    {

	        Map<String,String> map= new HashMap<String,String>();
	        A mail = new A("1499938098@qq.com","lynhelwdttjpgcca");
	        map.put("mail.smtp.host", "smtp.qq.com");

	        //暂时未成功，需要调试
	        /*SendMail mail = new SendMail("14789****@sina.cn","***miya");
	        map.put("mail.smtp.host", "smtp.sina.com");*/
	        map.put("mail.smtp.auth", "true");
	        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        map.put("mail.smtp.port", "465");
	        map.put("mail.smtp.socketFactory.port", "465");
	        mail.setPros(map);
	        mail.initMessage();
	        /*
	         * 添加收件人有三种方法：
	         * 1,单人添加(单人发送)调用setRecipient(str);发送String类型
	         * 2,多人添加(群发)调用setRecipients(list);发送list集合类型
	         * 3,多人添加(群发)调用setRecipients(sb);发送StringBuffer类型
	         */
	        List<String> list = new ArrayList<String>();
	        list.add(email);
	       
	        mail.setRecipients(list);
	     
	        mail.setSubject("验证码通知");
	        //mail.setText("谢谢合作");
	        mail.setDate(new Date());
	        mail.setFrom("毛线头知识库管理系统");
//	      mail.setMultipart("D:你你你.txt");
	        mail.setContent(content, "text/html; charset=UTF-8");
	     
	        System.out.println(mail.sendMessage());
	    }

    public static void main(String[] args) throws MessagingException, IOException
    {

        Map<String,String> map= new HashMap<String,String>();
        A mail = new A("1499938098@qq.com","lynhelwdttjpgcca");
        map.put("mail.smtp.host", "smtp.qq.com");

        //暂时未成功，需要调试
        /*SendMail mail = new SendMail("14789****@sina.cn","***miya");
        map.put("mail.smtp.host", "smtp.sina.com");*/
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        /*
         * 添加收件人有三种方法：
         * 1,单人添加(单人发送)调用setRecipient(str);发送String类型
         * 2,多人添加(群发)调用setRecipients(list);发送list集合类型
         * 3,多人添加(群发)调用setRecipients(sb);发送StringBuffer类型
         */
        List<String> list = new ArrayList<String>();
        list.add("296285717@qq.com");
        //list.add("***92@sina.cn");
        list.add("sunhanfei62@163.com");
        mail.setRecipients(list);
     
        mail.setSubject("测试邮箱");
        //mail.setText("谢谢合作");
        mail.setDate(new Date());
        mail.setFrom("MY");
//      mail.setMultipart("D:你你你.txt");
        mail.setContent("谢谢合作", "text/html; charset=UTF-8");
     
        System.out.println(mail.sendMessage());
    }

}