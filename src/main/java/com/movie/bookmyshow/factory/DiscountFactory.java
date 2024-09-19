package com.movie.bookmyshow.factory;

import com.movie.bookmyshow.strategy.AfternoonShowDiscountStrategy;
import com.movie.bookmyshow.strategy.DiscountStrategy;
import com.movie.bookmyshow.strategy.ThirdTicketDiscountStrategy;

public class DiscountFactory {
    public static DiscountStrategy getDiscountStrategy(String discountType, int ticketNumber) {
        switch (discountType) {
            case "AFTERNOON_SHOW":
                return new AfternoonShowDiscountStrategy();
            case "THIRD_TICKET":
                return new ThirdTicketDiscountStrategy(ticketNumber);
            default:
                throw new IllegalArgumentException("Unknown discount type: " + discountType);
        }
    }
}
