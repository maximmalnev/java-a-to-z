package ru.kovladimir.final_task;

public class Task {

    public static void main(String[] args) {
        Task task = new Task();
        boolean result = task.contains("lo world", "orld");
        System.out.println(result);
    }

    public boolean contains(String origin, String substr) {

        for (int i = 0; i < origin.length() - substr.length() + 1; i++) {
            //save old i
            int oldI = i;
            // interator of substr
            int j = 0;
            //count number of matching symbols
            int count = 0;

            while ((j < substr.length()) && (origin.charAt(i) == substr.charAt(j))) {
                i++;
                j++;
                count++;
            }
            if (count == substr.length()) {
                return true;
            } else {
                i = oldI;
            }
        }
        return false;
    }
}
