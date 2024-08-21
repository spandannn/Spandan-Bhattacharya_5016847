package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping
    public List<EntityModel<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                        linkTo(methodOn(BookController.class).getAllBooks()).withRel("books")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel(),
                        linkTo(methodOn(BookController.class).getAllBooks()).withRel("books")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setPrice(bookDetails.getPrice());
                    Book updatedBook = bookRepository.save(book);
                    EntityModel<Book> resource = EntityModel.of(updatedBook,
                            linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel(),
                            linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
