/*
 *@(#)xx.random
 *@RandomFilename.java.java  
 *@����ʱ��:2011-9-23����10:03:44
 *@���ߣ�tlq
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package xx.random;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
/**
 * @RandomFilename <code>{������}</code>
 * @author  {����}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

public class RandomFilename {

	public static String randomfile(){
		String nowTimeStr = ""; //���浱ǰʱ�� 
		
		SimpleDateFormat sDateFormat; 
		Random r = new Random(); 
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; //��ȡ����� 

		//HttpSession hs = ServletActionContext.getRequest().getSession();
		sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //ʱ���ʽ���ĸ�ʽ 
		sDateFormat.setLenient(false);
	    nowTimeStr = sDateFormat.format(new Date()); //��ǰʱ��
	    
	    return nowTimeStr+rannum;
	}
}
