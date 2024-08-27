package com.book.bookstoreapi.controller;

import com.book.bookstoreapi.dto.BookDTO;
import com.book.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id, @RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader) {
        BookDTO bookDTO = bookService.getBookById(id);
        addLinks(bookDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, acceptHeader)
                .body(bookDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(@RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader) {
        List<BookDTO> books = bookService.getAllBooks();
        books.forEach(this::addLinks);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, acceptHeader)
                .body(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.updateBook(id, bookDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void addLinks(BookDTO bookDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(bookDTO.getId(), HttpHeaders.CONTENT_TYPE)).withSelfRel();
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks(HttpHeaders.CONTENT_TYPE)).withRel("all-books");

        bookDTO.add(selfLink);
        bookDTO.add(allBooksLink);

    }
}
