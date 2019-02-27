package org.hum.pumpkin.test.serialization.doublefish;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.hum.pumpkin.test.serialization.doublefish.implement.AbstractOutput;

public class DoubleFishSerializationOutput {

	private DataOutputStream outputStream;
	private static final int MAGIC_NUMBER = 20190225;

	public DoubleFishSerializationOutput(OutputStream outputStream) {
		this.outputStream = new DataOutputStream(outputStream);
	}

	public void writeObject(Object object) throws IllegalArgumentException, IllegalAccessException, IOException {
		if (object == null) {
			throw new IllegalArgumentException("object cannot be null.");
		} else if (!Serializable.class.isAssignableFrom(object.getClass())) {
			throw new IllegalArgumentException("object must be implements class-type[java.io.Serializabe]");
		}
		// 1.write magic_number
		outputStream.writeInt(MAGIC_NUMBER);
		
		// 2.sort all field
		Map<String, Field> sortedFieldMap = sortFeildByName(object.getClass().getDeclaredFields());
		
		// 2.write all fields
		for (Entry<String, Field> fieldEntry : sortedFieldMap.entrySet()) {
			Field field = fieldEntry.getValue();
			if (Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			AbstractOutput.get(field.getType()).write(outputStream, field.get(object));
		}
	}
	
	private Map<String, Field> sortFeildByName(Field[] fields) {
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
