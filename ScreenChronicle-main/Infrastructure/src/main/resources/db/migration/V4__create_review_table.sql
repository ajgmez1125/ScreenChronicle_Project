CREATE TABLE review (
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT,
    user_id BIGINT,
    director VARCHAR(50),
    rating INTEGER(2),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);