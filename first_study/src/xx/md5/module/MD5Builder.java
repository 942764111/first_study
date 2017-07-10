/*
 *@(#)xx.md5.module
 *@MD5.java.java  
 *@创建时间:2011-8-2下午05:24:38
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
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
 * @MD5 <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:MD5加密生成摘要} 
 */

public class MD5Builder {
	static Logger logger = Logger.getLogger(MD5Builder.class);  
	//用来将字节转换成16进制表示的字符
	char hexDigits[] = {
			'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
		};
	/** 
     * 对文件全文生成MD5摘要 
     * @param file   要加密的文件 
     * @return MD5摘要码 
     */  
    public static String getMD5(File file) {  
        FileInputStream fis = null;  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");   
            logger.info("MD5摘要长度：" + md.getDigestLength());  
            fis = new FileInputStream(file);  
            byte[] buffer = new byte[2048];  
            int length = -1;  
            logger.info("开始生成摘要");  
            long s = System.currentTimeMillis();  
            while ((length = fis.read(buffer)) != -1) {  
                md.update(buffer, 0, length);  
            }  
            logger.info("摘要生成成功,总用时: " + (System.currentTimeMillis() - s)  
                    + "ms");  
            byte[] b = md.digest();  
            return byteToHexStringSingle(b);//byteToHexString(b);  
            // 16位加密  
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
     * 对一段String生成MD5加密信息 
     * @param message 要加密的String 
     * @return 生成的MD5信息 
     */  
    public static String getMD5(String message) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            logger.info("MD5摘要长度：" + md.getDigestLength());
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
     * 独立把byte[]数组转换成十六进制字符串表示形式 
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
	 * 传入参数：一个字节数组 传出参数：字节数组的 MD5 结果字符串
	 * @param source
	 * @return 16进制整数字符串格式
	 * */
	public static String getMD5(byte[] source) {
		String s = null;
		//用来将字节转换成16进制表示的字符
		char hexDigits[] = {
				'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
			};
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			//Md5的计算结果是一个128位的长度整数
			byte tmp[] = md.digest();
			
			//用字节表示就是16个字节
			char str[] = new char[16*2];//每个字节用16几只表示的话，使用两个字符
			//所以表示成16进制需要32个字符
			int k = 0;//表示转换结果中对应的字符位置
			for(int i=0;i<16;i++) {
				//从第一个字节开始，对MD5的每一个字节转换成16进制字符的转换
				byte byte0 = tmp[i];//取第i个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];//取字节中高4位的数字转换
				// >>> 为逻辑右移（即无符号右移），将符号位一起右移 
                // 取字节中低 4 位的数字转换 
                str[k++] = hexDigits[byte0 & 0xf]; 
			}
			s = new String(str); // 换后的结果转换为字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException  
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, 
			UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
    
    /**判断用户密码是否正确
     * @param newpasswd  用户输入的密码
     * @param oldpasswd  数据库中存储的密码－－用户密码的摘要
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
