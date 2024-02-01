package com.bibliotech.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.bibliotech.core.model.Author;
import com.bibliotech.core.model.Book;
import com.bibliotech.core.model.BookCategory;
import com.bibliotech.core.request.AuthorRequest;
import com.bibliotech.core.request.BookCategoryRequest;
import com.bibliotech.core.request.BookRequest;
import com.bibliotech.core.service.BookCategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class BookCategoryController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @GetMapping
    public List<BookCategory> getAllCategories() {
        return bookCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public BookCategory getCategoryById(@PathVariable String id) {
        return bookCategoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable String id) {
        bookCategoryService.deleteCategory(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('librarian')")
    public BookCategory saveCategory(@RequestBody BookCategoryRequest categoryRequest) {
        // Create a BookCategory instance from the request
        BookCategory category = new BookCategory();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        // Create Book instances and associate them with the category
        List<BookRequest> bookRequests = categoryRequest.getBooks();
        if (bookRequests != null) {
            List<Book> booksList = new ArrayList<>();
            for (BookRequest bookRequest : bookRequests) {
                Book book = new Book();
                book.setTitle(bookRequest.getTitle());
                book.setDescription(bookRequest.getDescription());

                // Associate the book with the category
                book.setCategory(category);
                
                // Add the authors list to book
                if ( bookRequest.getAuthors() != null) {
                   List<Author> authorList = new ArrayList<>();
	                for (AuthorRequest authorRequest : bookRequest.getAuthors() ) {
	                	Author author = new Author();
	                	author.setFirstName(authorRequest.getFirstName());
	                	author.setLastName(authorRequest.getLastName());
	                	authorList.add(author);
	                }
	                book.setAuthors(authorList);
                }
                // Add the book to the list of books
                booksList.add(book);

            }
            // Add the book to the category's list of books
            category.setBooks(booksList);
        }

        // Save the category and associated books
        return bookCategoryService.saveCategory(category);
    }
}
