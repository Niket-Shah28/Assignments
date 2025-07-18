package com.aurionpro.model;

import java.util.Scanner;

public class PlayTicTacToe {
	
	public static void displayBoardWithIndexes() {
		int index = 1;
		for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 3; col++) {
	        	System.out.print(" " + index++ + " ");
	            if (col < 2) {
	                System.out.print("|");
	            }
	        }
	        System.out.println();
	        if (row < 2) {
	            System.out.println("---+---+---");
	        }
	    }
	}
	
	private static boolean rowCheck(int row, String mark, Board board) {
		for(int column = 0; column < 3; column++) {
			Cell cell = board.getCell(row, column);
			if(!cell.getMarkStatus() || !cell.getMark().contentEquals(mark)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean columnCheck(int column, String mark, Board board) {
		for(int row = 0; row < 3; row++) {
			Cell cell = board.getCell(row, column);
			if(!cell.getMarkStatus() || !cell.getMark().contentEquals(mark)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean leftDiagonalCheck(String mark, Board board) {
		int row = 0, column = 0;
		while(row < 3 && column <3) {
			Cell cell = board.getCell(row, column);
			if(!cell.getMarkStatus() || !cell.getMark().contentEquals(mark)) {
				return false;
			}
			row ++;
			column ++;
		}
		return true;
	}
	
	private static boolean rightDiagonalCheck(String mark, Board board) {
		int row = 0, column = 2;
		while(row < 3 && column > -1) {
			Cell cell = board.getCell(row, column);
			if(!cell.getMarkStatus() || !cell.getMark().contentEquals(mark)) {
				return false;
			}
			row ++;
			column --;
		}
		return true;
	}
	
	private static boolean checkWin(int row, int column, String mark, Board board) {
		boolean rowStatus = rowCheck(row, mark, board);
		boolean columnStatus = columnCheck(column, mark, board);
		if(row == 0 || column == 0 || (row == 1 && column == 1) || (row == 2 && column == 2)) {
			boolean leftDiagonalStatus = leftDiagonalCheck(mark, board);
			boolean rightDiagonalStatus = rightDiagonalCheck(mark, board);
			return rowStatus || columnStatus || leftDiagonalStatus || rightDiagonalStatus;
		}
		return rowStatus || columnStatus;
	}
	
	public static void run(Player player1, Player player2, Scanner scanner) {
		System.out.println();
		displayBoardWithIndexes();
		System.out.println();
		System.out.println("Enter the indexes that represent the cell to mark the cell in Your Turn");
		System.out.println();
		
		Board board = new Board();
		Player currentPlayer = player1;
		boolean hasWon = false;
		
		while(!board.isFilled()) {
			System.out.println();
			board.displayBoard();
			System.out.println();
			System.out.println("PLAYER "+currentPlayer.getIndex()+ " ENTER THE INDEX OF CELL YOU WANT TO FILL: ");
			int choice = scanner.nextInt() - 1;
			int row = choice / 3;
			int column = choice % 3;
			try {
				board.markCell(row, column, currentPlayer.getMark());
				hasWon = checkWin(row, column, currentPlayer.getMark(), board);
				if(hasWon) {
					System.out.println();
					board.displayBoard();
					System.out.println();
					System.out.println("PLAYER "+currentPlayer.getIndex()+" WINS !!");
					System.out.println();
					break;
				}
				currentPlayer = (currentPlayer == player1)?player2:player1;
			}
			catch(IllegalStateException exception) {
				System.out.println();
				System.out.println(exception.getMessage());
				System.out.println();
			}
		}
		if(!hasWon) {
			System.out.println();
			board.displayBoard();
			System.out.println();
			System.out.println("DRAW");
			System.out.println();
		}
	}
}
