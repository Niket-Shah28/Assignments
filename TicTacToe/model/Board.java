package com.aurionpro.model;

public class Board {
	private Cell[][] grid;
	
	public Board() {
		grid = new Cell[3][3];
		for(int row = 0; row < 3; row++) {
			for(int column = 0; column < 3; column++) {
				grid[row][column] = new Cell();
			}
		}
	}
	
	public Cell getCell(int row, int column) {
		return grid[row][column];
	}
	
	public void markCell(int row, int column, String mark) {
		grid[row][column].setMark(mark);
	}
	
	public Cell[][] getGrid(){
		return grid;
	}
	
	public void displayBoard() {
	    for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 3; col++) {
	            Cell cell = grid[row][col];
	            if (!cell.getMarkStatus()) {
	                System.out.print("   ");
	            } else {
	                System.out.print(" " + cell.getMark() + " ");
	            }

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
	
	public boolean isFilled() {
		for(int row = 0; row < 3; row++) {
			for(int column = 0; column < 3; column++) {
				if(!grid[row][column].getMarkStatus()) {
					return false;
				}
			}
		}
		return true;
	}
}
