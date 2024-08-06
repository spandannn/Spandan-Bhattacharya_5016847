package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String book) {
        bookRepository.addBook(book);
    }

    public void listBooks() {
        for (String book : bookRepository.getAllBooks()) {
            System.out.println("Book: " + book);
        }
    }

    public void removeBook(String book) {
        bookRepository.removeBook(book);
    }
}
