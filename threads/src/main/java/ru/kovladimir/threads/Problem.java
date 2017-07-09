package ru.kovladimir.threads;

public class Problem {

    public static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.printf("%d%n", ++value);
                }
            }).start();
        }
    }
}
