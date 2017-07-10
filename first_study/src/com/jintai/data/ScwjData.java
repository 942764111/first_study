/*
 *@(#)com.jintai.data
 *@FileRootData.java.java  
 *@����ʱ��:2016-4-13����10:58:22
 *@���ߣ�������
 *@Copyright 2009-2010 �ӱ�����ѧԺ��Ϣ��ѧ�빤��ѧԺ������ 
 */

package com.jintai.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

/**
 * @FileRootData <code>{������}</code>
 * @author  {������}
 * @version {�汾,����ʱ�����}
 * @{��������:} 
 */

public class ScwjData {

	private static Map<String, List<Map<String, String>>> mapdata=new HashMap<String, List<Map<String, String>>>();
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
			
			if(mapdata.containsKey(key)){
				List<Map<String, String>> list=mapdata.get(key);
				list.add(map);
				
			}else{
				List<Map<String, String>>list=new ArrayList<Map<String, String>>();
				list.add(map);
				mapdata.put(key, list);
			}
			
			
		}
	}
	public static void remove(String key) {
		mapdata.remove(key);
	}
	
	
}
