package ru.kovladimir.arrays;

public class Rotater {
	public static void main(String[] args) {
		Rotater rotater = new Rotater();
		int[][] values = new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[0].length; j++) {
				System.out.print(values[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		int[][] result = rotater.rotate(values);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}
	}
	
	public int[][] rotate(int[][] values) {
		
		//Make a copy of values
		int[][] rotatedArray = new int[values.length][values[0].length];
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[0].length; j++) {
				rotatedArray[i][j] = values[i][j];
			}
		}
		
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[0].length; j++) {
				rotatedArray[i][j] = values[values.length - 1 - j][i];
			}
		}
		
		return rotatedArray;
	}
}