package org.hum.doublefish;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hum.doublefish.model.Data;
import org.hum.doublefish.model.User;
import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerializationOutput;
import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerialzationInput;

public class DataTest {

	private static Data data = new Data();
	
	static {
		data.setB1(true);
		data.setB2(true);
		data.setC1('c');
		data.setC2('d');
		data.setD1(1d);
		data.setD2(2d);
		data.setF1(3f);
		data.setF2(4f);
		data.setI1(5);
		data.setI2(6);
		data.setL1(7L);
		data.setL2(8L);
		data.setS1((short) 9);
		data.setS2((short) 10);
		data.setIntArr(new int[] { 1, 1, 0 });
		data.setIntegerArr(new Integer[] { 9, 1, 1 });
	}
	

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException, InstantiationException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DoubleFishSerializationOutput dfoutput = new DoubleFishSerializationOutput(outputStream);
		dfoutput.writeObject(data);
		byte[] byteArray = outputStream.toByteArray();
		DoubleFishSerialzationInput dfInput = new DoubleFishSerialzationInput(new ByteArrayInputStream(byteArray));
		Data obj = dfInput.readObject(Data.class);
		System.out.println(obj);
	}
}
