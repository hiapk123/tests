package com.uestc.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name", "张三");
		jsonArray.add(jsonObject);
		jsonArray.add("test2");
		jsonArray.add("test3");
		jsonArray.add("test4");
		System.out.println(jsonArray.toString());
	}
}
