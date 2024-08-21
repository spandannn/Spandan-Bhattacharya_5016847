package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetAllBooks");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetBookById");
        return book.map(b -> new ResponseEntity<>(b, headers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CreateBook");
        return new ResponseEntity<>(savedBook, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> book = bookRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "UpdateBook");
        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setTitle(bookDetails.getTitle());
            updatedBook.setAuthor(bookDetails.getAuthor());
            updatedBook.setPrice(bookDetails.getPrice());
            updatedBook.setIsbn(bookDetails.getIsbn());
            return new ResponseEntity<>(bookRepository.save(updatedBook), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "DeleteBook");
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
