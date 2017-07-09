package ru.kovladimir.tictactoe.game;

/**
 * All possible field types.
 */
public enum FieldType {
    CROSS {
        @Override
        public String toString() {
            return "X";
        }
    },
    NOUGHT {
        @Override
        public String toString() {
            return "O";
        }
    },
    NONE {
        @Override
        public String toString() {
            return ".";
        }
    }
}
