package com.book.bookstoreapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class CustomerDTO extends RepresentationModel<BookDTO> {

    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Address is required")
    @Size(min = 1, max = 255, message = "Address must be between 1 and 255 characters")
    private String address;
}
