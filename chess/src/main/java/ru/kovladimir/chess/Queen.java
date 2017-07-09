package ru.kovladimir.chess;

public class Queen extends Figure {

	public Queen(String description) {
		super(description);
	}

	public Queen() {

	}

	@Override
	boolean canBeMovedFromTo(int fromX, int fromY, int toX, int toY) {
		// logic
		return true;
	}

}