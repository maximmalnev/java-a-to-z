package ru.kovladimir.arrays;

public class DeleteDublicates {
    public static void main(String[] args) {
        DeleteDublicates dup = new DeleteDublicates();
        String[] values = new String[] {"Nick", "Mary", "Ann", "Mary",
                "Jack", "Nick", "Kate", "Kate"};
        for (String value : values) {
            System.out.print(value + " ");
        }
        System.out.println();

        String[] result = dup.delete(values);

        for (String value : result) {
            System.out.print(value + " ");
        }
    }

    public String[] delete(String[] values) {

        int countDublicates = 0;
		
        String[] copyOfValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            copyOfValues[i] = values[i];
        }

        for (int i = 0; i < copyOfValues.length - 1; i++) {
            if (copyOfValues[i] != null) {
                for (int j = i + 1; j < copyOfValues.length; j++) {
                    if (copyOfValues[i].equals(copyOfValues[j])) {
                        copyOfValues[j] = null;
						countDublicates++;
                    }
                }
            }
        }

		String[] arrayWithoutDublicates = new String[copyOfValues.length - countDublicates];
        int j = 0; //counter of array without dublicates
        for (int i = 0; i < copyOfValues.length; i++) {
            if (copyOfValues[i] != null) {
                arrayWithoutDublicates[j] = values[i];
                j++;
            }
        }

        return arrayWithoutDublicates;
    }
}