package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookService", BookService.class);

        // Test the configuration
        bookService.addBook("Mr Oliver's Diary");
        bookService.addBook("1984");
        bookService.listBooks();
        bookService.removeBook("1984");
        bookService.listBooks();
    }
}
