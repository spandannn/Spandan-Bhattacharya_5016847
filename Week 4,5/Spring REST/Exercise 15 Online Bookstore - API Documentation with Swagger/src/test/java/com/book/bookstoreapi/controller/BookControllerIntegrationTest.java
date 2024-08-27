package com.book.bookstoreapi.controller;

import com.book.bookstoreapi.dto.BookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void createBook_ShouldReturnCreated() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test Book");
        bookDTO.setAuthor("Test Author");

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getBookById_ShouldReturnBook() throws Exception {
        long bookId = 1L;

        mockMvc.perform(get("/books/{id}", bookId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    public void getAllBooks_ShouldReturnListOfBooks() throws Exception {
        mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void updateBook_ShouldReturnUpdatedBook() throws Exception {
        long bookId = 1L; // Use a valid book ID for testing
        BookDTO bookDTO = new BookDTO(); // Create a valid BookDTO instance
        bookDTO.setTitle("Updated Test Book");
        bookDTO.setAuthor("Updated Test Author");

        mockMvc.perform(put("/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Test Book"))
                .andExpect(jsonPath("$.author").value("Updated Test Author"));
    }

    @Test
    public void deleteBook_ShouldReturnNoContent() throws Exception {
        long bookId = 1L; // Use a valid book ID for testing

        mockMvc.perform(delete("/books/{id}", bookId))
                .andExpect(status().isNoContent());
    }
}
