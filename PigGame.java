package com.aurionpro.model;
import java.util.Random;
import java.util.Scanner;

public class PigGame {
	
	private static final Integer MAX_TURNS = 5, MAX_NUMBER = 6;
	private static Integer score = 0;
	private static Boolean hasWon;
	
	/* It returns True if player can continue to play else False */
	public static boolean validateRoll(Integer diceScore) {
		if(diceScore == 1) {
			System.out.println("You rolled a 1. TURN OVER");
			System.out.println("Turn Score: "+score);
			score = 0;
			return false;
		}
		
		score += diceScore;
		System.out.println("Current Score: "+ score);
		System.out.println("Dice Value: "+diceScore);
		
		if(score >= 20) {
			System.out.println("YOU WIN");
			hasWon = true;
			return false;
		}
		return true;
	}
	
	public static void playTurn(Scanner scanner) {
		Integer diceScore;
		Random random = new Random();
		Boolean canPlay = true;
		while(true && canPlay) {
			System.out.println("Do You want to Roll or Hold ?\n1. Roll\n2. Hold\nEnter Your Choice: ");
			Integer choice = scanner.nextInt();
			if(choice == 1) {
				diceScore = random.nextInt(MAX_NUMBER) + 1;
				canPlay = validateRoll(diceScore);
				continue;
			}
			
			if(choice == 2) {
				System.out.println("TURN OVER");
				System.out.println("Turn Score: "+score);
				return;
			}
			
			System.out.println("Invalid Choice");
		}
	}
	
	public static void playPigGame(Scanner scanner) {
		Integer turns = MAX_TURNS;
		System.out.println("You get 5 turns");
		System.out.println("In each turn, roll as much as you like");
		System.out.println("Rolling a 1 or holding ends the turn.");
		while(true && !hasWon && turns > 0) {
			System.out.println("TURN "+(MAX_TURNS - turns + 1));
			playTurn(scanner);
			turns -= 1;
		}
		score = 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Do You want to play the game?\n1. Yes\n2. No\nEnter Your Choice: ");
			Integer choice = scanner.nextInt();
			if(choice == 1) {
				playPigGame(scanner);
				continue;
			}
			if(choice == 2) {
				break;
			}
			System.out.println("Invalid Choice");
		}
		scanner.close();
	}
}
