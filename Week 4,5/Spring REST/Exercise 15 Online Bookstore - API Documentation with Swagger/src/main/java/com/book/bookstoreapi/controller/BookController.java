package com.book.bookstoreapi.controller;

import com.book.bookstoreapi.dto.BookDTO;
import com.book.bookstoreapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book Management", description = "Operations related to managing books")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Create a new book", responses = {
            @ApiResponse(responseCode = "201", description = "Book created successfully",
                    content = @Content(schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Get a book by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Book found",
                    content = @Content(schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(
            @PathVariable Long id,
            @RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader) {
        BookDTO bookDTO = bookService.getBookById(id);
        addLinks(bookDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, acceptHeader)
                .body(bookDTO);
    }

    @Operation(summary = "Get all books", responses = {
            @ApiResponse(responseCode = "200", description = "List of books",
                    content = @Content(schema = @Schema(implementation = BookDTO.class, type = "array"))),
    })
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(
            @RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader) {
        List<BookDTO> books = bookService.getAllBooks();
        books.forEach(this::addLinks);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, acceptHeader)
                .body(books);
    }

    @Operation(summary = "Update a book", responses = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully",
                    content = @Content(schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.updateBook(id, bookDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete a book", responses = {
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
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
