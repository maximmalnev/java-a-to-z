package ru.kovladimir.loops;

public class Factorial {
	
	public static void main(String[] args) {
		Factorial fac = new Factorial();
		int x = 5;
		System.out.println("Factorial of " + x + " = " + fac.factorial(x));
	}
	
	public int factorial(int x) {
		int result = 1;
		for (int i = 2; i <= x; i++) {
			result *= i;
		}
		return result;
	}
}