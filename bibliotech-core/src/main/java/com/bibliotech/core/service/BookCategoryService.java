package com.bibliotech.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotech.core.model.BookCategory;
import com.bibliotech.core.repository.BookCategoryRepository;

@Service
public class BookCategoryService {

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	public List<BookCategory> getAllCategories() {
		return bookCategoryRepository.findAll();
	}

	public BookCategory getCategoryById(String id) {
		return bookCategoryRepository.findById(id).orElse(null);
	}

	public BookCategory saveCategory(BookCategory category) {
		return bookCategoryRepository.save(category);
	}

	public void deleteCategory(String id) {
		bookCategoryRepository.deleteById(id);
	}
}
