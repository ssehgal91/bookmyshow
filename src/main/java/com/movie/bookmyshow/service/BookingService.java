package com.movie.bookmyshow.service;

import com.movie.bookmyshow.decorator.BookingDecorator;
import com.movie.bookmyshow.decorator.VIPBookingDecorator;
import com.movie.bookmyshow.dto.BookingRequestDTO;
import com.movie.bookmyshow.dto.BookingResponseDTO;
import com.movie.bookmyshow.dto.DiscountDTO;
import com.movie.bookmyshow.dto.ShowResponseDTO;
import com.movie.bookmyshow.entity.*;
import com.movie.bookmyshow.exception.BookingException;
import com.movie.bookmyshow.factory.DiscountFactory;
import com.movie.bookmyshow.repository.BookingRepository;
import com.movie.bookmyshow.repository.MovieRepository;
import com.movie.bookmyshow.repository.SeatRepository;
import com.movie.bookmyshow.repository.ShowRepository;
import com.movie.bookmyshow.strategy.AfternoonShowDiscountStrategy;
import com.movie.bookmyshow.strategy.DiscountStrategy;
import com.movie.bookmyshow.strategy.ThirdTicketDiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private VIPBookingDecorator vipBookingDecorator;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private BookingRepository bookingRepository;

    // Browse shows in a city on a specific date
    public List<ShowResponseDTO> browseShows(String movieName, String city, String date) {

        LocalDate showDate = LocalDate.parse(date);

        // Fetch movie by name
        Movie movie = movieRepository.findByName(movieName);
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }

        // Fetch shows based on movie, city, and date
        List<Show> shows = showRepository.findByMovieIdAndCityAndShowDate(movie.getId(), city, showDate);

        // Convert Show entities to ShowResponseDTOs
        return shows.stream()
                .map(show -> {
                    ShowResponseDTO dto = new ShowResponseDTO();
                    dto.setShowId(show.getId());
                    dto.setMovieName(show.getMovie().getName());
                    dto.setTheatreName(show.getTheatre().getName());
                    dto.setShowDate(show.getShowDate());
                    dto.setShowTime(show.getShowTime());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public DiscountDTO calculateDiscounts(BookingRequestDTO bookingRequest) {
        Show show = showRepository.findById(bookingRequest.getShowId())
                .orElseThrow(() -> new BookingException("Show not found"));

        double totalPrice = 0.0;
        List<Seat> seats = seatRepository.findAllById(bookingRequest.getSeatIds());
        if (seats.size() != bookingRequest.getSeatIds().size()) {
            throw new BookingException("Some seats are not found");
        }

        // Calculate price per seat
        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            double seatPrice = seat.getPrice();
            LocalTime showTime = show.getShowTime();
            boolean isAfternoonShow = showTime.isAfter(LocalTime.NOON) && showTime.isBefore(LocalTime.of(17, 0));

            // Create and apply discount strategies
            DiscountStrategy afternoonStrategy = new AfternoonShowDiscountStrategy();
            DiscountStrategy thirdSeatStrategy = new ThirdTicketDiscountStrategy(i);

            // Apply discount strategies
            seatPrice = afternoonStrategy.applyDiscount(seatPrice, isAfternoonShow);
            seatPrice = thirdSeatStrategy.applyDiscount(seatPrice, isAfternoonShow);

            totalPrice += seatPrice;
        }

        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setTotalPrice(totalPrice);
        discountDTO.setDiscountDetails("50% discount on every third seat applied and 20% discount for afternoon shows applied.");

        return discountDTO;
    }

    public BookingResponseDTO bookTickets(BookingRequestDTO requestDTO, boolean isVIP) {
        try {
            // Core booking process
            Booking booking= bookTicketsCore(requestDTO);

            // Apply decorator enhancements
            if (isVIP) {
                vipBookingDecorator.decorateBooking(requestDTO);
            }

            return new BookingResponseDTO(booking.getId(), booking.getCustomerName(),"Booking confirmed");
        } catch (BookingException e) {
            return new BookingResponseDTO(e.getMessage());
        }
    }

    private Booking bookTicketsCore(BookingRequestDTO requestDTO) throws BookingException {
        Show show = showRepository.findById(requestDTO.getShowId())
                .orElseThrow(() -> new BookingException("Show not found"));

        // Check availability of requested seats
        List<Seat> availableSeats = seatRepository.findByShowIdAndAvailable(requestDTO.getShowId(), true);
        if (availableSeats.size() < requestDTO.getSeatIds().size()) {
            throw new BookingException("Not enough seats available");
        }

        List<Seat> bookedSeats = new ArrayList<>();
        for (Long seatId : requestDTO.getSeatIds()) {
            //requested Seat Id is not available
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new BookingException("Seat not found"));
            if (!seat.isAvailable()) {
                throw new BookingException("Seat " + seatId + " is no longer available");
            }
            seat.setAvailable(false);
            seatRepository.save(seat);
            bookedSeats.add(seat);
        }

        // Create and save the booking
        Booking booking = new Booking();
        booking.setShow(show);
        booking.setSeats(bookedSeats);
        booking.setCustomerName(requestDTO.getCustomerName());
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("Confirmed");
        bookingRepository.save(booking);

        return booking;
    }
}
