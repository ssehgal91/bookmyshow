package com.movie.bookmyshow.entity;

import jakarta.validation.constraints.NotNull;

public class Ticket {

    @NotNull(message = "Seat ID cannot be null")
    private Long seatId;

    private Show show;
    private double price;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(@NotNull(message = "Seat ID cannot be null") Long seatId) {
        this.seatId = seatId;
    }

    // Getters and Setters
    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
