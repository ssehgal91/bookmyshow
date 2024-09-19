package com.movie.bookmyshow.dto;

import java.util.List;

public class BookingResponseDTO {
    private Long id;
    private String customerName;
    private String message;

    // Default constructor
    public BookingResponseDTO() {
    }
    public BookingResponseDTO(String message) {
        this.message = message;
    }

    public BookingResponseDTO(Long id, String customerName,String message) {
        this.id = id;
        this.customerName = customerName;
        this.message = message;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
