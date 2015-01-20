package com.infotop.eshop.utilities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

public class JsonHelper {

	public static Gson gson = new Gson();

	// conveter JSON to object single
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object toObject(String josnString, Class clazz) {
		System.out.println("--jsonstring --" + josnString);
		Object claz = null;
		try {
			if (josnString != null) {
				claz = gson.fromJson(josnString, clazz);
				System.out.println("--jsonstring --" + claz);
			}
		} catch (Exception e) {
		}

		return claz;
	}

	// conveter object to JSON single
	public static String toJson(Object object) {
		String jsonString = null;
		try {
			if (object != null) {
				jsonString = gson.toJson(object);
			}
		} catch (Exception e) {
		}
		return jsonString;
	}

	// conveter list<object> to JSONArray single
	public static JsonObject toJsonList(List<Object> listObject, Class clazz) {

		JsonObject jsonA = null;
		try {
			if (listObject != null) {
				String jd = gson.toJson(listObject);

				JsonObject js = new JsonObject();
				js.addProperty(clazz.getSimpleName(), jd);
				jsonA = js;
			}

		} catch (Exception e) {
		}
		return jsonA;
	}

	// conveter json to list<object> single

	@SuppressWarnings("rawtypes")
	public static List<Object> toList(JsonObject json, Class calzz) {
		List<Object> list = new ArrayList<Object>();

		try {
			if (json != null) {
				JsonPrimitive jp = json.getAsJsonPrimitive(calzz
						.getSimpleName());
				// System.out.println("==value=     ===== =>>> "+jp.getAsString()
				// );
				Type type = new TypeToken<List<Object>>() {
				}.getType();
				List<Object> objectList = gson.fromJson(jp.getAsString(), type);
				for (Object object : objectList) {
					Map<String, String> hd = (Map<String, String>) object;
					list.add(toObject(hd.toString(), calzz));
				}
			}

		} catch (Exception e) {
		}
		return list;
	}

}
