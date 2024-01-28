package com.bibliotech.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotech.core.model.Book;


public interface BookRepository extends JpaRepository<Book, String> {
}
