package ru.kovladimir.chess;

public abstract class Figure {

	private String description;

	public Figure(String description) {
		this.description = description;
	}

	public Figure() {
		this("no description");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	abstract boolean canBeMovedFromTo(int fromX, int fromY, int toX, int toY);
}