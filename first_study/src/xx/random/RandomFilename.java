/*
 *@(#)xx.random
 *@RandomFilename.java.java  
 *@创建时间:2011-9-23上午10:03:44
 *@作者：tlq
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package xx.random;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
/**
 * @RandomFilename <code>{类名称}</code>
 * @author  {作者}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class RandomFilename {

	public static String randomfile(){
		String nowTimeStr = ""; //保存当前时间 
		
		SimpleDateFormat sDateFormat; 
		Random r = new Random(); 
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; //获取随机数 

		//HttpSession hs = ServletActionContext.getRequest().getSession();
		sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //时间格式化的格式 
		sDateFormat.setLenient(false);
	    nowTimeStr = sDateFormat.format(new Date()); //当前时间
	    
	    return nowTimeStr+rannum;
	}
}
