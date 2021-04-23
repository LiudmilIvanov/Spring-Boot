package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationInit implements CommandLineRunner{

	private final AuthorRepository authorRepository;
	  private final BookRepository bookRepository;

	  @Autowired
	  ObjectMapper om;

	
	public ApplicationInit(AuthorRepository authorRepository, BookRepository bookRepository, ObjectMapper om) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.om = om;
	}



	@Override
	public void run(String... args) throws Exception {

		initJovkov();
	    initNikolaiHaitov();
	    initDimitarTalev();
	    initElinPelin();
	    initVazov();
	  }

	  private void initNikolaiHaitov() {
	    initAuthor("Николай Хайтов",
	        "Диви Разкази"
	    );
	  }

	  private void initDimitarTalev() {
	    initAuthor("Димитър Димов",
	        "Тютюн"
	    );
	  }

	  private void initElinPelin() {
	    initAuthor("Елин Пелин",
	        "Пижо и Пендо",
	        "Ян Бибиян на луната",
	        "Под манастирската лоза"
	    );
	  }

	  private void initVazov() {
	    initAuthor("Иван Вазов",
	        "Пряпорец и Гусла",
	        "Под Игото",
	        "Тъгите на България"
	        );
	  }

	  private void initJovkov() {

	    initAuthor("Йордан Йовков",
	        "Старопланински легенди",
	        "Чифликът край границата");
	  }

	  private void initAuthor(String authorName, String... books) {
	    Author author = new Author();
	    author.setName(authorName);

	    author = authorRepository.save(author);

	    List<Book> allBooks = new ArrayList<>();

	    for (String book: books) {
	      Book aBook = new Book();
	      aBook.setAuthor(author);
	      aBook.setTitle(book);
	      allBooks.add(aBook);
	    }

	    bookRepository.saveAll(allBooks);
	  }
		
	}

	
