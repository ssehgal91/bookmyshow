package com.movie.bookmyshow.decorator;

import com.movie.bookmyshow.dto.BookingRequestDTO;
import com.movie.bookmyshow.exception.BookingException;

public abstract class BookingDecorator {

    public abstract void decorateBooking(BookingRequestDTO requestDTO) throws BookingException;
}
