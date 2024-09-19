package com.movie.bookmyshow.util;

import com.movie.bookmyshow.entity.Show;
import com.movie.bookmyshow.entity.Ticket;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TicketUtility {
    public static List<Ticket> createTickets(Long showId, List<Double> ticketPrices, List<String> showTimes) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketPrices.size(); i++) {
            Ticket ticket = new Ticket();
            ticket.setPrice(ticketPrices.get(i));

            Show show = new Show(); // You may need to fetch Show details from the repository
            show.setId(showId);
            show.setShowTime(LocalTime.parse(showTimes.get(i)));

            ticket.setShow(show);
            tickets.add(ticket);
        }
        return tickets;
    }
}
