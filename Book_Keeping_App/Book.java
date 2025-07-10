package com.aurionpro.assignment;

import java.util.UUID;

public class Book implements Comparable<Book> {
	
	private final UUID bookId;
	private String title;
	private String author;
	private String publisher;
	private int publicationYear;
	private double price;
	
	/* Constructor */
	public Book(String title, String author, String publisher, int publicationYear, double price) {
		this.bookId = UUID.randomUUID();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.price = price;
	}

	/* Getters & Setters */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public UUID getBookId() {
		return bookId;
	}

	/* To String */
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", publicationYear=" + publicationYear + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Book book) {
		return this.author.compareTo(book.getAuthor());
	}
}
