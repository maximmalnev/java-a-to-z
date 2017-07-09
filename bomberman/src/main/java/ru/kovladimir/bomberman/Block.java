package ru.kovladimir.bomberman;

import ru.kovladimir.bomberman.player.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 19.08.2016.
 */
public class Block {

    private BlockType type;

    private boolean hasCharacter = false;

    private final List<Monster> monsters = new ArrayList<>();

    public Block(BlockType type) {
        this.type = type;
    }

    public BlockType getType() {
        return type;
    }

    public synchronized boolean hasCharacter() {
        return hasCharacter;
    }

    public synchronized void setCharacter(boolean hasCharacter) {
        this.hasCharacter = hasCharacter;
    }

    public synchronized boolean hasMonsters() {
        return monsters.size() > 0;
    }

    public synchronized void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public synchronized void deleteMonster(Monster monster) {
        monsters.remove(monster);
    }

    public synchronized void killAllMonsters() {
        for (Monster monster : monsters) {
            monster.interrupt();
        }
        monsters.clear();
    }
}
