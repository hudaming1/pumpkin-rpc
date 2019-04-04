package org.hum.doublefish;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.hum.doublefish.model.Data;
import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerializationOutput;
import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerialzationInput;

public class SimpleDataTest {

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
		data.setBy1((byte) 11);
		data.setBy2((byte) 12);
		data.setIntArr(new int[] { 1, 1, 0 });
		data.setIntegerArr(new Integer[] { 9, 1, 1 });
		data.setShortArr(new short[] { 1, 2, 3 });
		data.setShortWrapArr(new Short[] { 2, 3, 4 });
		data.setLongWrapArr(new Long[] { 2L, 3L, 4L });
		data.setLongArr(new long[] { 3L, 5L, 1L });
		data.setFloatArr(new float[] { 1.1F, 1.2F, 1.3F });
		data.setFloatWrapArr(new Float[] { 2.1F, 2.2F, 2.3F });
		data.setDoubleArr(new double[] { 3.1D, 3.2D, 3.3D });
		data.setDoubleWrapArr(new Double[] { 4.1D, 4.2D, 4.3D });
		data.setBooleanArr(new boolean[] { true, false, true });
		data.setBooleanWrapArr(new Boolean[] { true, false, false, true });
		data.setByteArr(new byte[] { 5, 6, 7 });
		data.setByteWrapArr(new Byte[] { 7, 8, 9 });
		data.setCharArr(new char[] { 'a', 'b', 'c' });
		data.setCharacterArr(new Character[] { 'd', 'e', 'f' });
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
