package com.easysearching.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtil {
	
	public  static Timestamp getSysDateTime(){
		return new Timestamp(System.currentTimeMillis());
	}

	public  static String formatDateTime(Timestamp now){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(now.getTime()));
	}
	
	public  static String getSystemDateTimeNumber(){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return dateFormat.format(System.currentTimeMillis());
	}
	
	public  static Timestamp convertStringToTimestamp(String datetime){
		return Timestamp.valueOf(datetime);
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(System.currentTimeMillis());
	}
	
	public static void main(String[] args) {
		System.out.println("<font color='red'>".length());
		String content = "my name is yuyang hello world";
		System.out.println(content.subSequence(content.length()-5, content.length()));
		//System.out.println(TimeUtil.getCurrentDate());
	}
}
