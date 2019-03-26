package org.hum.pumpkin.test.serialization.doublefish;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FieldUtils {
	
	public static List<Field> convert(Field[] fields) {
		if (fields == null || fields.length == 0) {
			return new ArrayList<>();
		}
		List<Field> list = new ArrayList<>();
		for (Field field : fields) {
			list.add(field);
		}
		return list;
	}

	public static List<Field> filterSerializeFields(List<Field> fields) {
		List<Field> fieldArr = new ArrayList<>(fields.size());
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
				continue;
			}
			fieldArr.add(field);
		}
		return fieldArr;
	}

	public static Map<String, Field> sortFeildByName(List<Field> fields) {
		if (fields == null || fields.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<String, Field> fieldMap = new TreeMap<>();
		for (Field field : fields) {
			fieldMap.put(field.getName(), field);
		}
		return fieldMap;
	}
}
