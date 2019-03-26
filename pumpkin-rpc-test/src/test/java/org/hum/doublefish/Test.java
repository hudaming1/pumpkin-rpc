package org.hum.doublefish;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerializationOutput;
import org.hum.pumpkin.test.serialization.doublefish.DoubleFishSerialzationInput;

public class Test {

	public static class A implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private Integer id;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "A [id=" + id + "]";
		}
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException, InstantiationException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DoubleFishSerializationOutput dfoutput = new DoubleFishSerializationOutput(outputStream);
		A a = new A();
		a.setId(1);
		dfoutput.writeObject(a);
		
		byte[] byteArray = outputStream.toByteArray();
		DoubleFishSerialzationInput dfInput = new DoubleFishSerialzationInput(new ByteArrayInputStream(byteArray));
		A obj = dfInput.readObject(A.class);
		System.out.println(obj);
	}
}
