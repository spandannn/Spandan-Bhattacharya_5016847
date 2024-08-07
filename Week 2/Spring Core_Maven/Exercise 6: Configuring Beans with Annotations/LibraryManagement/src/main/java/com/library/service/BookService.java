package com.library.service;

import org.springframework.stereotype.Service;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Other methods
    public void addBook(String book) {
        bookRepository.addBook(book);
    }

    public void listBooks() {
        bookRepository.listBooks();
    }

    public void removeBook(String book) {
        bookRepository.removeBook(book);
    }
}
