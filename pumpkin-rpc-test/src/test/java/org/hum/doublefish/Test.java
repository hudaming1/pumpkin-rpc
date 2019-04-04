package org.hum.doublefish;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.hum.doublefish.model.User;
import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerializationOutput;
import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerialzationInput;

public class Test {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException, InstantiationException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DoubleFishSerializationOutput dfoutput = new DoubleFishSerializationOutput(outputStream);
		User user = new User();
		user.setId(null);
		user.setUid(1000000000000L);
		dfoutput.writeObject(user);

		byte[] byteArray = outputStream.toByteArray();
		System.out.println("serialized size:" + byteArray.length);
		System.out.println(Arrays.toString(byteArray));
		
		DoubleFishSerialzationInput dfInput = new DoubleFishSerialzationInput(new ByteArrayInputStream(byteArray));
		User obj = dfInput.readObject(User.class);
		System.out.println(obj);
	}
}
