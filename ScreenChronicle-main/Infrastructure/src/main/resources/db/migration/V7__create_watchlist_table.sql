CREATE TABLE watchlist (
    watchlist_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    movie_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (movie_id) REFERENCES movie (movie_id)
);