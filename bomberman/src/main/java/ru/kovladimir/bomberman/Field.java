package ru.kovladimir.bomberman;

/**
 * Created by Vladimir on 19.08.2016.
 */
public class Field {

    private final int WIDTH;

    private final int HEIGHT;

    private Block[][] blocks;


    public Field(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        initBlocks();
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public Block getBlock(int x, int y) {
        return blocks[y][x];
    }

    public void initBlocks() {
        blocks = new Block[HEIGHT][WIDTH];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blocks[i][j] = new Block(BlockType.EMPTY);
            }
        }
    }


}

