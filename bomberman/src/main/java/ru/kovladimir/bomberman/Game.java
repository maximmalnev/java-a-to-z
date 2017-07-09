package ru.kovladimir.bomberman;

import ru.kovladimir.bomberman.player.AbstractPlayer;
import ru.kovladimir.bomberman.player.Character;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vladimir on 21.08.2016.
 */
public class Game {

    public void start() {
        Field field = new Field(15 , 15);
        AbstractPlayer character = new Character(0, 0, field);
        AbstractPlayer monster = new Character(5, 5, field);
        character.start();
        monster.start();
        // TODO: 21.08.2016 if end game then executor shutdown
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

}
