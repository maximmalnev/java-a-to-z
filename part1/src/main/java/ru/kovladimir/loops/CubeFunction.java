package ru.kovladimir.loops;

public class CubeFunction {
	private int a;
	private int b;
	private int c;
	
	public CubeFunction(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public static void main(String[] args) {
		CubeFunction fun = new CubeFunction(2, 5, 3);
		int x1 = Integer.parseInt(args[0]);
		int x2 = Integer.parseInt(args[1]);
		
		for (int i = x1; i <= x2; i++) {
			System.out.println(fun.calculate(i));
		}
	
	}
	
	public float calculate(int x) {
		return (float)((a * Math.pow(x, 2)) + (b * x) + c);
	}
}