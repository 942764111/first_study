package com.easysearching.web.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JsonUtil {

	public void json2java() {

		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd" }));
		String jsonStr = "[{\"name\": \"husband\", \"age\": \"26\", \"born\": \"1984-01-12\"},{\"name\": \"wife\", \"age\": \"20\", \"born\": \"1990-05-01\"}]";
		Collection<JsonBean> list = JSONArray.toCollection(JSONArray.fromObject(jsonStr), JsonBean.class);
		// DateUtil.getFormatDate(date,fmtstr)日期转字符串这里不再写代码了
	}

	public void json2list() {
		String listStr = "[\"apple\",\"orange\"]";
		Collection<String> strlist = JSONArray.toCollection(JSONArray.fromObject(listStr));
		for (String str : strlist) {
			System.out.println(str);
		}
		String mapStr = "{\"age\":30,\"name\":\"Michael\",\"baby\":[\"Lucy\",\"Lily\"]}";
		Map<String, Object> map = (Map) JSONObject.toBean(JSONObject.fromObject(mapStr), Map.class);
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	public void list2json() {
		List<String> list = new ArrayList<String>();
		list.add("apple");
		list.add("orange");
		JSONArray jarr = JSONArray.fromObject(list);
		System.out.println("list->json:" + jarr.toString());
	}

	public void map2json() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Michael");
		map.put("baby", new String[] { "Lucy", "Lily" });
		map.put("age", 30);
		JSONObject jo = JSONObject.fromObject(map);
		System.out.println("map->json:" + jo.toString());
	}

	public void bean2json() {
		JsonBean bean = new JsonBean();
		bean.setName("NewBaby");
		bean.setAge(1);
		bean.setBirthday(new Date());
		JSONObject jo = JSONObject.fromObject(bean);
		System.out.println("bean->json:" + jo.toString());
	}

	public void bean2json2() {
		JsonBean bean = new JsonBean();
		bean.setName("NewBaby");
		bean.setAge(1);
		bean.setBirthday(new Date());
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = JSONObject.fromObject(bean, jsonConfig);
		System.out.println("bean->json:" + jo.toString());
	}

	public static void main(String[] args) {
		JsonUtil json = new JsonUtil();
		json.bean2json2();
		json.json2list();

	}

}
