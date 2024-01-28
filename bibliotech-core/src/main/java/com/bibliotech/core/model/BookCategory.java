package com.bibliotech.core.model;

import java.util.List;
import java.util.UUID;

import org.aspectj.weaver.tools.Trace;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class BookCategory {

	@Id
	@Column(nullable = false,unique = true)
	private String id;
	
	@PrePersist
	public void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
	}
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = true, length = 4000)
	private String description;
	
    @JsonManagedReference
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books;
}
