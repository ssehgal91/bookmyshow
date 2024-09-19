package com.movie.bookmyshow.decorator;

import com.movie.bookmyshow.dto.BookingRequestDTO;
import com.movie.bookmyshow.exception.BookingException;
import org.springframework.stereotype.Component;

@Component
public class VIPBookingDecorator extends BookingDecorator {

    @Override
    public void decorateBooking(BookingRequestDTO requestDTO) throws BookingException {
        // Apply VIP enhancements
        System.out.println("Applying VIP features to the booking.");
    }
}
