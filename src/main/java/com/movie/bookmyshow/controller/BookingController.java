package com.movie.bookmyshow.controller;

import com.movie.bookmyshow.dto.BookingRequestDTO;
import com.movie.bookmyshow.dto.BookingResponseDTO;
import com.movie.bookmyshow.dto.DiscountDTO;
import com.movie.bookmyshow.dto.ShowResponseDTO;
import com.movie.bookmyshow.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Browse theatres running a movie in a specific city and date
    @GetMapping("/browse-theatres")
    public ResponseEntity<List<ShowResponseDTO>> browseTheatres(
            @RequestParam String movieName,
            @RequestParam String city,
            @RequestParam String date) {

        List<ShowResponseDTO> shows = bookingService.browseShows(movieName, city, date);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/calculate-discounts")
    public ResponseEntity<DiscountDTO> calculateDiscounts(
            @RequestParam("showId") Long showId,
            @RequestParam(value = "seatIds", required = false) List<Long> seatIds) {

        BookingRequestDTO bookingRequest = new BookingRequestDTO();
        bookingRequest.setShowId(showId);
        bookingRequest.setSeatIds(seatIds);

        DiscountDTO response = bookingService.calculateDiscounts(bookingRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookTickets(
            @Valid @RequestBody BookingRequestDTO bookingRequestDTO,
            @RequestParam(value = "type", defaultValue = "standard") String bookingType) {
        boolean isVIP = "VIP".equalsIgnoreCase(bookingType);
        BookingResponseDTO bookingResponse = bookingService.bookTickets(bookingRequestDTO, isVIP);
        HttpStatus status = bookingResponse.getMessage().equals("Booking confirmed") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(bookingResponse, status);
    }
}
