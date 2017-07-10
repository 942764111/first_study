/*
 *@(#)xx.md5.module
 *@MD5.java.java  
 *@����ʱ��:2011-8-2����05:24:38
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.md5.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

/**
 * @MD5 <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:MD5��������ժҪ} 
 */

public class MD5Builder {
	static Logger logger = Logger.getLogger(MD5Builder.class);  
	//�������ֽ�ת����16���Ʊ�ʾ���ַ�
	char hexDigits[] = {
			'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
		};
	/** 
     * ���ļ�ȫ������MD5ժҪ 
     * @param file   Ҫ���ܵ��ļ� 
     * @return MD5ժҪ�� 
     */  
    public static String getMD5(File file) {  
        FileInputStream fis = null;  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");   
            logger.info("MD5ժҪ���ȣ�" + md.getDigestLength());  
            fis = new FileInputStream(file);  
            byte[] buffer = new byte[2048];  
            int length = -1;  
            logger.info("��ʼ����ժҪ");  
            long s = System.currentTimeMillis();  
            while ((length = fis.read(buffer)) != -1) {  
                md.update(buffer, 0, length);  
            }  
            logger.info("ժҪ���ɳɹ�,����ʱ: " + (System.currentTimeMillis() - s)  
                    + "ms");  
            byte[] b = md.digest();  
            return byteToHexStringSingle(b);//byteToHexString(b);  
            // 16λ����  
            // return buf.toString().substring(8, 24);  
        } catch (Exception ex) {  
            logger.error(ex);  
            ex.printStackTrace();  
            return null;  
        } finally {  
            try {  
                fis.close();  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
    }  
  
    /** */  
    /** 
     * ��һ��String����MD5������Ϣ 
     * @param message Ҫ���ܵ�String 
     * @return ���ɵ�MD5��Ϣ 
     */  
    public static String getMD5(String message) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            logger.info("MD5ժҪ���ȣ�" + md.getDigestLength());
            byte[] b = md.digest(message.getBytes("utf-8"));  
            return byteToHexStringSingle(b);//byteToHexString(b);  
        } catch (NoSuchAlgorithmException e) {  
            logger.error(e);  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            logger.error(e);  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /** 
     * ������byte[]����ת����ʮ�������ַ�����ʾ��ʽ 
     * @param byteArray 
     * @return 
     */  
    public static String byteToHexStringSingle(byte[] byteArray) {  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {  
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(  
                        Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }  
  
	/**
	 * ���������һ���ֽ����� �����������ֽ������ MD5 ����ַ���
	 * @param source
	 * @return 16���������ַ�����ʽ
	 * */
	public static String getMD5(byte[] source) {
		String s = null;
		//�������ֽ�ת����16���Ʊ�ʾ���ַ�
		char hexDigits[] = {
				'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
			};
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			//Md5�ļ�������һ��128λ�ĳ�������
			byte tmp[] = md.digest();
			
			//���ֽڱ�ʾ����16���ֽ�
			char str[] = new char[16*2];//ÿ���ֽ���16��ֻ��ʾ�Ļ���ʹ�������ַ�
			//���Ա�ʾ��16������Ҫ32���ַ�
			int k = 0;//��ʾת������ж�Ӧ���ַ�λ��
			for(int i=0;i<16;i++) {
				//�ӵ�һ���ֽڿ�ʼ����MD5��ÿһ���ֽ�ת����16�����ַ���ת��
				byte byte0 = tmp[i];//ȡ��i���ֽ�
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];//ȡ�ֽ��и�4λ������ת��
				// >>> Ϊ�߼����ƣ����޷������ƣ���������λһ������ 
                // ȡ�ֽ��е� 4 λ������ת�� 
                str[k++] = hexDigits[byte0 & 0xf]; 
			}
			s = new String(str); // ����Ľ��ת��Ϊ�ַ���
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**����MD5���м���
     * @param str  �����ܵ��ַ���
     * @return  ���ܺ���ַ���
     * @throws NoSuchAlgorithmException  û�����ֲ�����ϢժҪ���㷨
     * @throws UnsupportedEncodingException  
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, 
			UnsupportedEncodingException {
        //ȷ�����㷽��
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //���ܺ���ַ���
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
    
    /**�ж��û������Ƿ���ȷ
     * @param newpasswd  �û����������
     * @param oldpasswd  ���ݿ��д洢�����룭���û������ժҪ
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, 
    		UnsupportedEncodingException {
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
}
