package com.aurionpro.model;

import java.util.Scanner;

public class TicTacToeTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Do You want to play:\n1. YES\n2. NO\nEnter Your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 2) {
				break;
			}
			System.out.println("Player 1 Choose the Mark:");
			System.out.println("1. X\n2. O\nEnter Your Choice");
			int markChoice = scanner.nextInt();
			
			String player1Mark = (markChoice == 1)?"X":"O";
			Player player1 = new Player(player1Mark, 1);
			
			String player2Mark = (player1Mark.equals("X"))?"O":"X";
			Player player2 = new Player(player2Mark, 2);
			
			PlayTicTacToe.run(player1, player2, scanner);
		}
	}
}
