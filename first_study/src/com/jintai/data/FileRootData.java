/*
 *@(#)com.jintai.data
 *@FileRootData.java.java  
 *@创建时间:2016-4-13上午10:58:22
 *@作者：朱永科
 *@Copyright 2009-2010 河北北方学院信息科学与工程学院科研所 
 */

package com.jintai.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

/**
 * @FileRootData <code>{类名称}</code>
 * @author  {朱永科}
 * @version {版本,常用时间代替}
 * @{功能描述:} 
 */

public class FileRootData {

	public static Map<String, List<Map<String, String>>> mapdata=new HashMap<String, List<Map<String, String>>>();//文档转换

	public static Map<String, List<Map<String, String>>> mapdata1=new HashMap<String, List<Map<String, String>>>();//视频转换

	public static List<Map<String, String>> getList(String key) {

		List<Map<String, String>> list=mapdata.get(key);

		return list;
	}

	public static void setList(String key,Map<String, String> map) {
		if (mapdata.isEmpty()) {
			List<Map<String, String>>list=new ArrayList<Map<String, String>>();
			list.add(map);
			mapdata.put(key, list);
		}else {
			List<Map<String, String>> list=mapdata.get(key);
			list.add(map);
		}

	
	}
	public static void remove(String key) {
	
		mapdata.remove(key);
	
	}


	public static List<Map<String, String>> getList1(String key) {

		List<Map<String, String>> list=mapdata1.get(key);

		return list;
	}

	public static void setList1(String key,Map<String, String> map) {
		if (mapdata1.isEmpty()) {
			List<Map<String, String>>list=new ArrayList<Map<String, String>>();
			list.add(map);
			mapdata1.put(key, list);
		}else {
			List<Map<String, String>> list=mapdata1.get(key);
			list.add(map);
		}
	}
	public static void remove1(String key) {
		mapdata1.remove(key);

	}




}
