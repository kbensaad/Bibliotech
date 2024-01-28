package com.bibliotech.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotech.core.model.BookCategory;


public interface BookCategoryRepository extends JpaRepository<BookCategory, String> {

}
