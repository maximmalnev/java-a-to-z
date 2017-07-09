package ru.kovladimir.bomberman;

/**
 * Created by Vladimir on 23.08.2016.
 */
public class Bomb {

    private int x;
    private int y;
    private Field field;

    public Bomb(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.field = field;
    }

    public void setBomb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blast();
            }
        }).start();
    }

    private void blast() {
        field.getBlock(x, y).killAllMonsters();
        field.getBlock(x + 1, y).killAllMonsters();
        field.getBlock(x - 1, y).killAllMonsters();
        field.getBlock(x, y + 1).killAllMonsters();
        field.getBlock(x, y - 1).killAllMonsters();
    }

}
