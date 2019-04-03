package org.hum.doublefish.model;

import java.io.Serializable;
import java.util.Arrays;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean b1;
	private boolean b2;
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

	@Override
	public String toString() {
		return "Data [b1=" + b1 + ", b2=" + b2 + ", c1=" + c1 + ", c2=" + c2 + ", s1=" + s1 + ", s2=" + s2 + ", i1="
				+ i1 + ", i2=" + i2 + ", l1=" + l1 + ", l2=" + l2 + ", f1=" + f1 + ", f2=" + f2 + ", d1=" + d1 + ", d2="
				+ d2 + ", IntegerArr=" + Arrays.toString(IntegerArr) + ", intArr=" + Arrays.toString(intArr) + "]";
	}
}
