package ru.kovladimir.arrays;

public class Sorter {
	public static void main(String[] args) {
		int[] array = new int[] {4, 7, 2, 9, 3};
		Sorter sorter = new Sorter();
		
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
		int[] result = sorter.sort(array);
		for (int i : result) {
			System.out.print(i + " ");
		}
	}
	
	public int[] sort(int[] array) {
		
		//Make a copy of array
		int[] copyOfArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			copyOfArray[i] = array[i];
		}
		
		for (int i = 0; i < copyOfArray.length - 1; i++) {
			for (int j = 0; j < copyOfArray.length - 1 - i; j++) {
				if (copyOfArray[j] > copyOfArray[j + 1]) {
					int temp = copyOfArray[j];
					copyOfArray[j] = copyOfArray[j + 1];
					copyOfArray[j + 1] = temp;
				}
			}
		}
		
		return copyOfArray;
	}
}