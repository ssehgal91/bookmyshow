CREATE TABLE movie (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       language VARCHAR(50),
                       genre VARCHAR(50)
);

CREATE TABLE theatre (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         city VARCHAR(100) NOT NULL
);

CREATE TABLE show (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      movie_id BIGINT,
                      theatre_id BIGINT,
                      show_time TIME NOT NULL,
                      price DECIMAL(10, 2) NOT NULL,
                      show_date DATE NOT NULL,
                      available_seats INT,
                      city VARCHAR(100),
                      FOREIGN KEY (movie_id) REFERENCES movie(id),
                      FOREIGN KEY (theatre_id) REFERENCES theatre(id)
);

-- Create the seat table
CREATE TABLE seat (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      available BOOLEAN NOT NULL,
                      price DECIMAL(10, 2) NOT NULL,
                      show_id BIGINT,
                      FOREIGN KEY (show_id) REFERENCES show(id)
);