package com.bibliotech.core.request;

import lombok.Data;

@Data
public class BookRequest {
	  private String title;
	  private boolean isAvailable;
	  private String description;
}
