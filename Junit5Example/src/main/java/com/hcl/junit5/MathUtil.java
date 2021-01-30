package com.hcl.junit5;

public class MathUtil {
	
	public int add(int a, int b) {
		return a + b;
	}
	
	public int subtract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
	
	public double computAreaOfCircle(int radius) {
		System.out.println(Math.PI*radius*radius);
		return Math.PI*radius*radius;
	}
}
