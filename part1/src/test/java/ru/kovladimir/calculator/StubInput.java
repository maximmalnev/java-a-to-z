package ru.kovladimir.calculator;

public class StubInput implements Input {

    private String[] commands;
    private int position = 0;


    public StubInput(String[] commands) {
        this.commands = commands;
    }

    public String askString() {
        return commands[position++];
    }
}
