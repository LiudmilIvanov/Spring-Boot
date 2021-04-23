package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;

@RestController
public class BooksController implements AuthorsNameSpace{

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	
	
	@Autowired
	public BooksController(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}



	@GetMapping("/{authorId}/books")
	public ResponseEntity<List<Book>> getAuthorBooks(@PathVariable Long authorId) {
		Optional<Author> author = authorRepository.findById(authorId);
		
		
		return author
				.map(Author::getBooks)
				.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	} 
	
	@GetMapping("/{authorId}/books/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable Long authorId, Long bookId) {
		Optional<Book> theBook= bookRepository.findById(bookId);

		
		return theBook.filter(b -> b.getAuthor().getId() == authorId)
				.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
				
				
				
	}
	
	
}
