package org.hum.doublefish.model;

import java.io.Serializable;
import java.util.Arrays;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean b1;
	private boolean b2;
	private byte by1;
	private byte by2;
	private Character c1;
	private char c2;
	private Short s1;
	private short s2;
	private int i1;
	private Integer i2;
	private Long l1;
	private long l2;
	private float f1;
	private Float f2;
	private Double d1;
	private double d2;
	private Integer[] IntegerArr;
	private int[] intArr;
	private Short[] shortWrapArr;
	private short[] shortArr;
	private Long[] longWrapArr;
	private long[] longArr;
	private Float[] floatWrapArr;
	private float[] floatArr;
	private Double[] doubleWrapArr;
	private double[] doubleArr;
	private Byte[] byteWrapArr;
	private byte[] byteArr;
	private Boolean[] booleanWrapArr;
	private boolean[] booleanArr;
	private Character[] CharacterArr;
	private char[] charArr;

	public Boolean getB1() {
		return b1;
	}

	public void setB1(Boolean b1) {
		this.b1 = b1;
	}

	public boolean isB2() {
		return b2;
	}

	public void setB2(boolean b2) {
		this.b2 = b2;
	}

	public byte getBy1() {
		return by1;
	}

	public void setBy1(byte by1) {
		this.by1 = by1;
	}

	public byte getBy2() {
		return by2;
	}

	public void setBy2(byte by2) {
		this.by2 = by2;
	}

	public Character getC1() {
		return c1;
	}

	public void setC1(Character c1) {
		this.c1 = c1;
	}

	public char getC2() {
		return c2;
	}

	public void setC2(char c2) {
		this.c2 = c2;
	}

	public Short getS1() {
		return s1;
	}

	public void setS1(Short s1) {
		this.s1 = s1;
	}

	public short getS2() {
		return s2;
	}

	public void setS2(short s2) {
		this.s2 = s2;
	}

	public int getI1() {
		return i1;
	}

	public void setI1(int i1) {
		this.i1 = i1;
	}

	public Integer getI2() {
		return i2;
	}

	public void setI2(Integer i2) {
		this.i2 = i2;
	}

	public Long getL1() {
		return l1;
	}

	public void setL1(Long l1) {
		this.l1 = l1;
	}

	public long getL2() {
		return l2;
	}

	public void setL2(long l2) {
		this.l2 = l2;
	}

	public float getF1() {
		return f1;
	}

	public void setF1(float f1) {
		this.f1 = f1;
	}

	public Float getF2() {
		return f2;
	}

	public void setF2(Float f2) {
		this.f2 = f2;
	}

	public Double getD1() {
		return d1;
	}

	public void setD1(Double d1) {
		this.d1 = d1;
	}

	public double getD2() {
		return d2;
	}

	public void setD2(double d2) {
		this.d2 = d2;
	}

	public Integer[] getIntegerArr() {
		return IntegerArr;
	}

	public void setIntegerArr(Integer[] integerArr) {
		IntegerArr = integerArr;
	}

	public int[] getIntArr() {
		return intArr;
	}

	public void setIntArr(int[] intArr) {
		this.intArr = intArr;
	}

	public Short[] getShortWrapArr() {
		return shortWrapArr;
	}

	public void setShortWrapArr(Short[] shortWrapArr) {
		this.shortWrapArr = shortWrapArr;
	}

	public short[] getShortArr() {
		return shortArr;
	}

	public void setShortArr(short[] shortArr) {
		this.shortArr = shortArr;
	}

	public Long[] getLongWrapArr() {
		return longWrapArr;
	}

	public void setLongWrapArr(Long[] longWrapArr) {
		this.longWrapArr = longWrapArr;
	}

	public long[] getLongArr() {
		return longArr;
	}

	public void setLongArr(long[] longArr) {
		this.longArr = longArr;
	}

	public Float[] getFloatWrapArr() {
		return floatWrapArr;
	}

	public void setFloatWrapArr(Float[] floatWrapArr) {
		this.floatWrapArr = floatWrapArr;
	}

	public float[] getFloatArr() {
		return floatArr;
	}

	public void setFloatArr(float[] floatArr) {
		this.floatArr = floatArr;
	}

	public Double[] getDoubleWrapArr() {
		return doubleWrapArr;
	}

	public void setDoubleWrapArr(Double[] doubleWrapArr) {
		this.doubleWrapArr = doubleWrapArr;
	}

	public double[] getDoubleArr() {
		return doubleArr;
	}

	public void setDoubleArr(double[] doubleArr) {
		this.doubleArr = doubleArr;
	}

	public Byte[] getByteWrapArr() {
		return byteWrapArr;
	}

	public void setByteWrapArr(Byte[] byteWrapArr) {
		this.byteWrapArr = byteWrapArr;
	}

	public byte[] getByteArr() {
		return byteArr;
	}

	public void setByteArr(byte[] byteArr) {
		this.byteArr = byteArr;
	}

	public Boolean[] getBooleanWrapArr() {
		return booleanWrapArr;
	}

	public void setBooleanWrapArr(Boolean[] booleanWrapArr) {
		this.booleanWrapArr = booleanWrapArr;
	}

	public boolean[] getBooleanArr() {
		return booleanArr;
	}

	public void setBooleanArr(boolean[] booleanArr) {
		this.booleanArr = booleanArr;
	}

	public Character[] getCharacterArr() {
		return CharacterArr;
	}

	public void setCharacterArr(Character[] characterArr) {
		CharacterArr = characterArr;
	}

	public char[] getCharArr() {
		return charArr;
	}

	public void setCharArr(char[] charArr) {
		this.charArr = charArr;
	}

	@Override
	public String toString() {
		return "Data [b1=" + b1 + ", b2=" + b2 + ", by1=" + by1 + ", by2=" + by2 + ", c1=" + c1 + ", c2=" + c2 + ", s1="
				+ s1 + ", s2=" + s2 + ", i1=" + i1 + ", i2=" + i2 + ", l1=" + l1 + ", l2=" + l2 + ", f1=" + f1 + ", f2="
				+ f2 + ", d1=" + d1 + ", d2=" + d2 + ", IntegerArr=" + Arrays.toString(IntegerArr) + ", intArr="
				+ Arrays.toString(intArr) + ", shortWrapArr=" + Arrays.toString(shortWrapArr) + ", shortArr="
				+ Arrays.toString(shortArr) + ", longWrapArr=" + Arrays.toString(longWrapArr) + ", longArr="
				+ Arrays.toString(longArr) + ", floatWrapArr=" + Arrays.toString(floatWrapArr) + ", floatArr="
				+ Arrays.toString(floatArr) + ", doubleWrapArr=" + Arrays.toString(doubleWrapArr) + ", doubleArr="
				+ Arrays.toString(doubleArr) + ", byteWrapArr=" + Arrays.toString(byteWrapArr) + ", byteArr="
				+ Arrays.toString(byteArr) + ", booleanWrapArr=" + Arrays.toString(booleanWrapArr) + ", booleanArr="
				+ Arrays.toString(booleanArr) + ", CharacterArr=" + Arrays.toString(CharacterArr) + ", charArr="
				+ Arrays.toString(charArr) + "]";
	}
}
