package org.hum.pumpkin.test.serialization.doublefish;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Map.Entry;

import org.hum.pumpkin.test.serialization.doublefish.implement.AbstractSerialization;

public class DoubleFishSerializationOutput {

	private DataOutputStream outputStream;

	public DoubleFishSerializationOutput(OutputStream outputStream) {
		this.outputStream = new DataOutputStream(outputStream);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void writeObject(Object object) throws IllegalArgumentException, IllegalAccessException, IOException {
		if (object == null) {
			throw new IllegalArgumentException("object cannot be null.");
		} else if (!Serializable.class.isAssignableFrom(object.getClass())) {
			throw new IllegalArgumentException("object must be implements class-type[java.io.Serializabe]");
		}
		// 1.write magic_number
		outputStream.writeInt(Const.MAGIC_NUMBER);
		
		// 2.sort all field
		Map<String, Field> sortedFieldMap = FieldUtils.sortFeildByName(FieldUtils.filterSerializeFields(FieldUtils.convert(object.getClass().getDeclaredFields())));
		
		// 2.write all fields
		for (Entry<String, Field> fieldEntry : sortedFieldMap.entrySet()) {
			Field field = fieldEntry.getValue();
			if (Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			AbstractSerialization abstractSerialization = AbstractSerialization.get(field.getType());
			abstractSerialization.write(outputStream, field.get(object));
		}
		
		if (outputStream != null) {
			outputStream.close();
		}
	}
}
