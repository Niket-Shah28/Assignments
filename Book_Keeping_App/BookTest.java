package com.aurionpro.assignment;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.UUID;

public class BookTest {
	public static void main(String[] args) {
		
		Library library = new Library();
		
		Scanner scanner = new Scanner(System.in);
		
		List<Book> books;
		
		for(int i = 0; i < 2; i++) {
			library.addBook(scanner);
			scanner.nextLine();
		}
		outerWhile:
		while(true) {
			System.out.println("1. Add new Book\n2. Display Available Books\n3. Display Issued Books\n"
								+ "4. Issue Book\n5. Return a Book\n6. Display Books in Asceding Order of Authors"
								+"\n7. Display Books in Descending Order of Titles\n8. Exit\nEnter Your Choice:");
			int choice = scanner.nextInt();
			switch(choice) {
				case 1:
					scanner.nextLine();
					library.addBook(scanner);
					break;
				case 2:
					library.displayAvailableBooks();
					break;
				case 3:
					library.displayIssuedBooks();
					break;
				case 4:
					Map<UUID, String>availableBooks = library.getAvailableBooksId();
					System.out.println("Available Books");
					UUID bookIdToBeIssued = displayAndGetBookId(availableBooks, scanner);
					library.issueBook(bookIdToBeIssued);
					break;
				case 5:
					Map<UUID, String>issuedBooks = library.getIssuedBooksId();
					System.out.println("Issued Books");
					UUID bookIdToBeReturned = displayAndGetBookId(issuedBooks, scanner);
					library.returnBook(bookIdToBeReturned);
					break;
				case 6:
					
					books = library.getAllBooks();
					Collections.sort(books);
					displayBooks(books);
					break;
				case 7:
					books = library.getAllBooks();
					Collections.sort(books, new BookComparator());
					displayBooks(books);
					break;
				case 8:
					break outerWhile;
				default:
					System.out.println("Invalid Choice");
			}
		}
	}
	
	public static void displayBooks(List<Book> books) {
		System.out.println();
		System.out.println();
		System.out.printf("%-30s | %-20s\n", "Title", "Author");
		System.out.println("-----------------------------------------------");
		for(Book book: books) {
			System.out.printf("%-30s | %-20s\n", book.getTitle(), book.getAuthor());
		}
		System.out.println();
		System.out.println();
	}
	public static UUID displayAndGetBookId(Map<UUID, String> books, Scanner scanner) {
		System.out.println();
		System.out.println();
		for(Entry<UUID, String> entry: books.entrySet()) {
			System.out.println(entry.getValue());
		}
		System.out.println("Enter Your Choice: ");
		int choice = scanner.nextInt() - 1;
		int index = 0;
		for(Entry<UUID, String> entry: books.entrySet()) {
			if(index == choice) {
				return entry.getKey();
			}
			index ++;
		}
		System.out.println();
		System.out.println();
		return null;
	}
}
