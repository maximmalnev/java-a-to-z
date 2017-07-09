package ru.kovladimir.chess;

public class ChessStart {
	public static void main(String[] args) {
		ChessDesk desk = new ChessDesk(8, 8);
		desk.add(new King(), 0, 5);
		desk.add(new Queen(), 0, 4);
		boolean wasMoved = desk.move(0, 5, 0, 6);
		String result = wasMoved ? "The figure was moved" : "The figure was not moved";
		System.out.println(result);
	}
}