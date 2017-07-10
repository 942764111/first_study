package xx.email;


	import java.util.Date; 
	import java.util.Properties; 

	import javax.mail.Message; 
	import javax.mail.Session; 
	import javax.mail.Transport; 
	import javax.mail.internet.InternetAddress; 
	import javax.mail.internet.MimeMessage; 

public class SendMail { 
	/** 
	* ���ͼ��ʼ� 
	* @param str_from�������˵�ַ 
	* @param str_to:�ռ��˵�ַ 
	* @param str_title���ʼ����� 
	* @param str_content���ʼ����� 
	*/ 
	public static void send(String str_from,String str_to,String str_title,String str_content) { 

	// str_content="<a href='www.163.com'>htmlԪ��</a>"; //for testing send html mail! 

	try { 
	//�����ʼ��Ự 
	Properties props=new Properties(); //������һ���ļ��д洢��-ֵ�Եģ����м���ֵ���õȺŷָ��ģ� 
	//�洢�����ʼ�����������Ϣ 
	props.put("mail.smtp.host","smtp.163.com"); 
	//ͬʱͨ����֤ 
	props.put("mail.smtp.auth","true"); 
	//���������½�һ���ʼ��Ự 
	Session s=Session.getInstance(props,null); 
	s.setDebug(true); //�������ӡһЩ������Ϣ�� 

	//���ʼ��Ự�½�һ����Ϣ���� 
	MimeMessage message=new MimeMessage(s); 

	//�����ʼ� 
	InternetAddress from= new InternetAddress(str_from); //pukeyouxintest2@163.com 
	message.setFrom(from); //���÷����˵ĵ�ַ 
	// 
	// //�����ռ���,���������������ΪTO 
	InternetAddress to=new InternetAddress(str_to); //pukeyouxintest3@163.com 
	message.setRecipient(Message.RecipientType.TO, to); 

	//���ñ��� 
	message.setSubject(str_title); //javaѧϰ 

	//�����ż����� 
	// message.setText(str_content); //�����ı��ʼ� //����� 
	message.setContent(str_content, "text/html;charset=gbk"); //����HTML�ʼ� //<b>���</b><br><p>��Һ�</p> 
	//���÷���ʱ�� 
	message.setSentDate(new Date()); 

	//�洢�ʼ���Ϣ 
	message.saveChanges(); 

	//�����ʼ� 
	Transport transport=s.getTransport("smtp"); 
	//��smtp��ʽ��¼����,��һ�������Ƿ����ʼ��õ��ʼ�������SMTP��ַ,�ڶ�������Ϊ�û���,����������Ϊ���� 
	transport.connect("smtp.163.com","sunhanfei62@163.com","sunhanfei0602"); 
	//�����ʼ�,���еڶ�����������������õ��ռ��˵�ַ 
	transport.sendMessage(message,message.getAllRecipients()); 
	transport.close(); 

	} catch (Exception e) { 
	e.printStackTrace(); 
	} 
	} 

	public static void main(String[] args) { 
	//�����õģ��������д������д��ȥ���� 
	System.out.println("start send mail����");
	send(Constant.mailAddress,"1499938098@qq.com","ë��ͷ֪ʶ�����ϵͳ","�����ε���֤��Ϊ564312����ע�Ᵽ�ܣ���������Ҫ�����κ�������"); 
	
	int random=(int)(Math.random()*1000000); 
	 System.out.println(random);
	} 
	 




}
