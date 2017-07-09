package ru.kovladimir.chess;

public class ChessDesk {
	private int maxX;
	private int maxY;
	private Figure[][] figures;
	
	public ChessDesk(int x, int y) {
		this.maxX = x;
		this.maxY = y;
		this.figures = new Figure[x][y];
	}
	
	public boolean add(Figure figure, int x, int y) {
		boolean wasAdded = false;
		if (isPossibleAddition(x, y)) {
			executeAddition(figure, x, y);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean move(int fromX, int fromY, int toX, int toY) {
		boolean wasMoved = false;
		if (isPossibleMove(fromX, fromY, toX, toY)) {
			executeMoving(fromX, fromY, toX, toY);
			wasMoved = true;
		}
		return wasMoved;
	}

	private void executeAddition(Figure figure, int x, int y) {
		figures[x][y] = figure;
	}

	private void executeMoving(int fromX, int fromY, int toX, int toY) {
		figures[toX][toY] = figures[fromX][fromY];
		figures[fromX][fromY] = null;
	}

	private boolean isPossibleAddition(int x, int y) {
		return isValidatePosition(x, y) && !hasFigureInThisPosition(x, y);
	}

	private boolean isPossibleMove(int fromX, int fromY, int toX, int toY) {
		return isValidatePosition(fromX, fromY) &&
				isValidatePosition(toX, toY) &&
				hasFigureInThisPosition(fromX, fromY) &&
				!hasFigureInThisPosition(toX, toY) &&
				figures[fromX][fromY].canBeMovedFromTo(fromX, fromY, toX, toY);
	}

	private boolean isValidatePosition(int x, int y) {
		return x >= 0 && x < maxX && y >= 0 && y < maxY;
	}

	private boolean hasFigureInThisPosition(int x, int y) {
		return figures[x][y] != null;
	}
}