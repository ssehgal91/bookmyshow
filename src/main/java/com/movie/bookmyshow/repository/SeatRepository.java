package com.movie.bookmyshow.repository;


import com.movie.bookmyshow.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Find all available seats for a given show ID
    List<Seat> findByShowIdAndAvailable(Long showId, boolean available);
}
