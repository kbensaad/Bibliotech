package com.bibliotech.core.request;

import java.util.List;

import lombok.Data;

@Data
public class BookCategoryRequest {

    private String description;
    private String name;
    private List<BookRequest> books;

}