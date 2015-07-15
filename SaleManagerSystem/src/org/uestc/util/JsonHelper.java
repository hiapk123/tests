package org.uestc.util;

import net.sf.json.JSONArray;

public class JsonHelper {
	public static String toJson(Object resultobj) {
		if (resultobj != null) {
			JSONArray obj = JSONArray.fromObject(resultobj);
			return (obj.toString());
		}
		return "";
	}
}
