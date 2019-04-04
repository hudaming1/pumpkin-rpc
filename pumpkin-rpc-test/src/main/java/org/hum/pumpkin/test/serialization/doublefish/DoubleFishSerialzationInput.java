package org.hum.pumpkin.test.serialization.doublefish;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.hum.pumpkin.test.serialization.doublefish.implement.AbstractSerialization;

public class DoubleFishSerialzationInput {

	private DataInputStream dis;
	
	public DoubleFishSerialzationInput(InputStream is) {
		dis = new DataInputStream(is);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T readObject(Class<T> classType) throws IOException, InstantiationException, IllegalAccessException {
		if (classType == null) {
			throw new IllegalArgumentException("classType mustn't be null");
		} else if (!(dis.readInt() == Const.MAGIC_NUMBER)) {
			throw new DoubleFishSerialException("unknown data-type");
		}

		// 2.sort all field
		Map<String, Field> sortedFieldMap = FieldUtils.sortFeildByName(FieldUtils.filterSerializeFields(FieldUtils.convert(classType.getDeclaredFields())));
		
		// 3.new instances
		T newInstance = classType.newInstance();
		
		// 4.set value
		for (Entry<String, Field> entry : sortedFieldMap.entrySet()) {
			Field field = entry.getValue();
			field.setAccessible(true);
			Class type = field.getType();
			field.set(newInstance, AbstractSerialization.get(field.getType()).read(dis, type));
		}
		
		if (dis != null) {
			dis.close();
		}
		return newInstance;
	}
}
