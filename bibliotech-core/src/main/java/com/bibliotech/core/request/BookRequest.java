package com.bibliotech.core.request;

import lombok.Data;
import java.util.List;

@Data
public class BookRequest {
	  private String title;
	  private boolean isAvailable;
	  private String description;
	  private List<AuthorRequest> authors;
}
