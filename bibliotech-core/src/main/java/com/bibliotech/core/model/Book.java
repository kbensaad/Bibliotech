package com.bibliotech.core.model;

import java.util.UUID;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Book {

	@Id
	@Column(nullable = false,unique = true)
	private String id;
	
	@PrePersist
	public void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
	}
	
	private String title;

	@ManyToOne
	@JoinColumn(name="category_id")
    @JsonBackReference
	private BookCategory category;
	
	private String description;
	
	private boolean isAvailable;
	
}
