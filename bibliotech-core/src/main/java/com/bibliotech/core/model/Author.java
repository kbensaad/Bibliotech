package com.bibliotech.core.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Author {
	
	@Id
	@Column(nullable = false, unique = true)
	private String id;
	
	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID().toString();
	}
	
	private String firstName;
	private String lastName;
	
    @ManyToMany(mappedBy = "authors")
	private Set<Book> books;
}
