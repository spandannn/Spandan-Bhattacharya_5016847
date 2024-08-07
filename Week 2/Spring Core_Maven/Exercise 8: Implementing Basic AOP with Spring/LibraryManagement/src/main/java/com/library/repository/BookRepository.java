package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private List<String> books = new ArrayList<>();

    public void addBook(String book) {
        books.add(book);
    }

    public void listBooks() {
        books.forEach(System.out::println);
    }

    public void removeBook(String book) {
        books.remove(book);
    }
}
