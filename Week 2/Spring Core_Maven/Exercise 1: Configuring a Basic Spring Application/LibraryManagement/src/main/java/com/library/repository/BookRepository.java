package com.library.repository;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<String> books = new ArrayList<>();

    public void addBook(String book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public List<String> getAllBooks() {
        return books;
    }

    public void removeBook(String book) {
        books.remove(book);
        System.out.println("Book removed: " + book);
    }
}
