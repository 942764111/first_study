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

public class FileRootData {

	public static Map<String, List<Map<String, String>>> mapdata=new HashMap<String, List<Map<String, String>>>();//�ĵ�ת��

	public static Map<String, List<Map<String, String>>> mapdata1=new HashMap<String, List<Map<String, String>>>();//��Ƶת��

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
