package com.bibliotech.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotech.core.model.Book;
import com.bibliotech.core.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Book getBookById(String id) {
		if (id != null) {
			return bookRepository.findById(id).orElse(null);
		}
		return null;
	}
	
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	
	public Book updateBook(String id, Book book) {
		Book bookFromDB = bookRepository.findById(id).orElse(null);
		if (bookFromDB != null) {
			
			// Update fields as needed
			bookFromDB.setTitle(book.getTitle());
			bookFromDB.setDescription(book.getDescription());
			// Save the updated book
			return bookRepository.save(bookFromDB);
		}else {
			return null; // Book not found
		}
	}
	
	public void deleteBook(String id) {
		bookRepository.deleteById(id);
	}
	
}
