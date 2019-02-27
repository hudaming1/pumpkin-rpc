package org.hum.pumpkin.test.serialization.doublefish;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Utils {

	public static Map<String, Field> sortFeildByName(Field[] fields) {
		if (fields == null || fields.length == 0) {
			return Collections.emptyMap();
		}
		Map<String, Field> fieldMap = new TreeMap<>();
		for (Field field : fields) {
			fieldMap.put(field.getName(), field);
		}
		return fieldMap;
	}
}
