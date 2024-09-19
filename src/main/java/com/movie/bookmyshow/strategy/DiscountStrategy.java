package com.movie.bookmyshow.strategy;

import java.time.LocalTime;

public interface DiscountStrategy {
    double applyDiscount(double ticketPrice, boolean isAfternoonShow);
}
