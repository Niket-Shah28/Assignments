package com.aurionpro.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Library {
	
	private static HashSet<Book> availableBooks = new HashSet<>();
	private static HashSet<Book> issuedBooks = new HashSet<>();
	
	private Book createBookObj(Scanner scanner) {
		
		String title, author, publisher;
		int year;
		double price;
		try {
			System.out.println("Enter the Title of Book: ");
			title = scanner.nextLine();
			System.out.println("Enter the Author Name: ");
			author = scanner.nextLine();
			System.out.println("Enter the Publisher Name: ");
			publisher = scanner.nextLine();
			System.out.println("Enter the Publication Year: ");
			year = scanner.nextInt();
			System.out.println("Enter the Price of the book: ");
			price = scanner.nextDouble();
			if(year > java.time.Year.now().getValue()) {
				throw new InvalidYearException();
			}
			if(price < 0) {
				throw new NegativePriceException();
			}
		}
		catch(InvalidYearException exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		catch(NegativePriceException exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		return new Book(title, author, publisher, year, price);
	}
	
	public void addBook(Scanner scanner){
		Book book = createBookObj(scanner);
		
		if(book == null) {
			System.out.println("Invalid details");
			return;
		}
		
		availableBooks.add(book);
		System.out.println("Book Added Successfully");
	}
	
	public void displayAvailableBooks() {
		System.out.println();
		System.out.println();
		for(Book book: availableBooks) {
			System.out.println(book.getTitle());
		}
		System.out.println();
		System.out.println();
	}
	
	public void displayIssuedBooks() {
		System.out.println();
		System.out.println();
		for(Book book: issuedBooks) {
			System.out.println(book.getTitle());
		}
		System.out.println();
		System.out.println();
	}
	
	public void issueBook(UUID bookId) {
		Book requiredBook = null;
		for(Book book: availableBooks) {
			if(book.getBookId().equals(bookId)) {
				requiredBook = book;
				break;
			}
		}
		if(requiredBook == null) {
			System.out.println("Book Not Available");
			return;
		}
		availableBooks.remove(requiredBook);
		issuedBooks.add(requiredBook);
	}
	
	public void returnBook(UUID bookId) {
		Book requiredBook = null;
		for(Book book: issuedBooks) {
			if(book.getBookId().equals(bookId)) {
				requiredBook = book;
				break;
			}
		}
		if(requiredBook == null) {
			System.out.println("Book Never Issued");
			return;
		}
		availableBooks.add(requiredBook);
		issuedBooks.remove(requiredBook);
	}
	
	public List<Book> getAllBooks(){
		HashSet<Book> books = new HashSet<>(availableBooks);
		books.addAll(issuedBooks);
		return new ArrayList<>(books);
	}
	
	public Map<UUID, String> getAvailableBooksId(){
		Map<UUID, String> availableBooksID = new LinkedHashMap<>();
		for(Book book: availableBooks) {
			availableBooksID.put(book.getBookId(), book.getTitle());
		}
		return availableBooksID;
	}
	
	public Map<UUID, String> getIssuedBooksId(){
		Map<UUID, String> issuedBooksID = new LinkedHashMap<>();
		for(Book book: issuedBooks) {
			issuedBooksID.put(book.getBookId(), book.getTitle());
		}
		return issuedBooksID;
	}
}
