package com.movie.bookmyshow.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class BookingRequestDTO {

    @NotNull(message = "Show ID cannot be null")
    private Long showId;

    @NotNull(message = "Seat IDs cannot be null")
    private List<Long> seatIds;

    @NotNull(message = "Customer name cannot be null")
    @Size(min = 1, max = 100, message = "Customer name must be between 1 and 100 characters")
    private String customerName;

    // Getters and Setters

    public @NotNull(message = "Customer name cannot be null") @Size(min = 1, max = 100, message = "Customer name must be between 1 and 100 characters") String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@NotNull(message = "Customer name cannot be null") @Size(min = 1, max = 100, message = "Customer name must be between 1 and 100 characters") String customerName) {
        this.customerName = customerName;
    }

    public @NotNull(message = "Seat IDs cannot be null") List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(@NotNull(message = "Seat IDs cannot be null") List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public @NotNull(message = "Show ID cannot be null") Long getShowId() {
        return showId;
    }

    public void setShowId(@NotNull(message = "Show ID cannot be null") Long showId) {
        this.showId = showId;
    }
}
