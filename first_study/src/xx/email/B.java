
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

	        //��ʱδ�ɹ�����Ҫ����
	        /*SendMail mail = new SendMail("14789****@sina.cn","***miya");
	        map.put("mail.smtp.host", "smtp.sina.com");*/
	        map.put("mail.smtp.auth", "true");
	        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        map.put("mail.smtp.port", "465");
	        map.put("mail.smtp.socketFactory.port", "465");
	        mail.setPros(map);
	        mail.initMessage();
	        /*
	         * ����ռ��������ַ�����
	         * 1,�������(���˷���)����setRecipient(str);����String����
	         * 2,�������(Ⱥ��)����setRecipients(list);����list��������
	         * 3,�������(Ⱥ��)����setRecipients(sb);����StringBuffer����
	         */
	        List<String> list = new ArrayList<String>();
	        list.add(email);
	       
	        mail.setRecipients(list);
	     
	        mail.setSubject("��֤��֪ͨ");
	        //mail.setText("лл����");
	        mail.setDate(new Date());
	        mail.setFrom("ë��ͷ֪ʶ�����ϵͳ");
//	      mail.setMultipart("D:������.txt");
	        mail.setContent(content, "text/html; charset=UTF-8");
	     
	        System.out.println(mail.sendMessage());
	    }

    public static void main(String[] args) throws MessagingException, IOException
    {

        Map<String,String> map= new HashMap<String,String>();
        A mail = new A("1499938098@qq.com","lynhelwdttjpgcca");
        map.put("mail.smtp.host", "smtp.qq.com");

        //��ʱδ�ɹ�����Ҫ����
        /*SendMail mail = new SendMail("14789****@sina.cn","***miya");
        map.put("mail.smtp.host", "smtp.sina.com");*/
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        /*
         * ����ռ��������ַ�����
         * 1,�������(���˷���)����setRecipient(str);����String����
         * 2,�������(Ⱥ��)����setRecipients(list);����list��������
         * 3,�������(Ⱥ��)����setRecipients(sb);����StringBuffer����
         */
        List<String> list = new ArrayList<String>();
        list.add("296285717@qq.com");
        //list.add("***92@sina.cn");
        list.add("sunhanfei62@163.com");
        mail.setRecipients(list);
     
        mail.setSubject("��������");
        //mail.setText("лл����");
        mail.setDate(new Date());
        mail.setFrom("MY");
//      mail.setMultipart("D:������.txt");
        mail.setContent("лл����", "text/html; charset=UTF-8");
     
        System.out.println(mail.sendMessage());
    }

}