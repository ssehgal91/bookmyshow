package com.movie.bookmyshow.strategy;

public class ThirdTicketDiscountStrategy implements DiscountStrategy {
    private final int ticketNumber;

    public ThirdTicketDiscountStrategy(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public double applyDiscount(double ticketPrice, boolean isAfternoonShow) {
        if (ticketNumber % 3 == 2) {
            return ticketPrice * 0.5; // 50% discount
        }
        return ticketPrice;
    }
}