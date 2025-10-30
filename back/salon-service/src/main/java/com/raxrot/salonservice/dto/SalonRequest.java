package com.raxrot.salonservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SalonRequest{
    @NotBlank(message = "Salon name is required")
    @Size(min = 2, max = 100, message = "Salon name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\+?[0-9\\-\\s]{7,15}$",
            message = "Invalid phone number format"
    )
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "City is required")
    private String city;
}
