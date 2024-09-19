package com.movie.bookmyshow.strategy;

import java.time.LocalTime;

// 20% discount for afternoon shows (12:00 PM to 5:00 PM)
public class AfternoonShowDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(double ticketPrice, boolean isAfternoonShow) {
        if (isAfternoonShow) {
            return ticketPrice * 0.8; // 20% discount
        }
        return ticketPrice;
    }
}