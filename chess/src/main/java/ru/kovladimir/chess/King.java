package ru.kovladimir.chess;

public class King extends Figure {

	public King(String description) {
		super(description);
	}

    public King() {

    }

    @Override
    boolean canBeMovedFromTo(int fromX, int fromY, int toX, int toY) {
        // logic
        return true;
    }
}