package com.movie.bookmyshow.repository;

import com.movie.bookmyshow.entity.Movie;
import com.movie.bookmyshow.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieIdAndCityAndShowDate(Long  movieId, String city, LocalDate date);
}