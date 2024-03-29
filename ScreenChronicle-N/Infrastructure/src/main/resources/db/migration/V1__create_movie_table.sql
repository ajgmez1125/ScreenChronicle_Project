CREATE TABLE customer (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    date VARCHAR(20),
    length INTEGER(50),
    director VARCHAR(50),
    description VARCHAR(500)
);