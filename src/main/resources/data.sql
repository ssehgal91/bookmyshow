INSERT INTO movie (id, name, language, genre)
VALUES (1, 'Inception', 'English', 'Sci-Fi'),
       (2, 'Avengers: Endgame', 'English', 'Action');

INSERT INTO theatre (id, name, city)
VALUES (1, 'The Grand Theatre', 'Delhi'),
       (2, 'Cineplex', 'Delhi'),
       (3, 'PVR Ansal', 'Noida'),
       (4, 'PVR', 'Gurgaon');

INSERT INTO show (movie_id, theatre_id, show_time, price, show_date, available_seats, city)
VALUES (1, 1, '14:00:00', 100, '2024-09-18', 10, 'Delhi'),
       (1, 2, '16:30:00', 200, '2024-09-18', 20, 'Delhi'),
       (2, 1, '15:00:00', 150, '2024-09-18', 30, 'Delhi');

INSERT INTO seat (id, available, price, show_id)
VALUES (1, true, 100.00, 1),
       (2, true, 100.00, 1),
       (3, true, 100.00, 1),
       (4, true, 100.00, 2);