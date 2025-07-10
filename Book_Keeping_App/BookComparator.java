package com.aurionpro.assignment;

import java.util.Comparator;

public class BookComparator implements Comparator<Book>{

	@Override
	public int compare(Book book1, Book book2) {
		// TODO Auto-generated method stub
		return book2.getTitle().compareTo(book1.getTitle());
	}
	

}
