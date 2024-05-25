CREATE DATABASE bookbar;

USE bookbar;

CREATE TABLE libraries (
   id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL,
   address VARCHAR(255),
   city VARCHAR(255),
   state VARCHAR(255),
   zip_code VARCHAR(20),
   phone VARCHAR(20),
   email VARCHAR(255),
   opening_hours VARCHAR(255),
   PRIMARY KEY (id)
);

CREATE TABLE publishers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(20),
    phone VARCHAR(20),
    email VARCHAR(255),
    website VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE authors (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    biography TEXT,
    nationality VARCHAR(255),
    birth_date DATE,
    death_date DATE,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    language VARCHAR(255),
    publication_date DATE,
    image LONGBLOB,
    page_number INT,
    price DECIMAL(8,2),
    description TEXT,
    author_id BIGINT,
    library_id BIGINT,
    publisher_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES authors(id),
    FOREIGN KEY (library_id) REFERENCES libraries(id),
    FOREIGN KEY (publisher_id) REFERENCES publishers(id)
);

CREATE TABLE categories (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book_categories (
    id BIGINT NOT NULL AUTO_INCREMENT,
    book_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    UNIQUE KEY (book_id, category_id)
);

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    balance DECIMAL(8,2) DEFAULT 0.00,
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
    street VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE user_orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date DATETIME,
    total_price DECIMAL(8,2),
    user_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE book_order_details (
    order_id BIGINT,
    book_id BIGINT,
    quantity INT,
    PRIMARY KEY (order_id, book_id),
    FOREIGN KEY (order_id) REFERENCES user_orders(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE user_subscriptions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    subscription_id BIGINT,
    start_date DATETIME,
    end_date DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE ratings (
    id BIGINT NOT NULL AUTO_INCREMENT,
    review DECIMAL(2,1),
    comment TEXT,
    date DATETIME,
    book_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE credit_cards (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    card_number VARCHAR(20),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE book_files (
    id BIGINT NOT NULL AUTO_INCREMENT,
    filename VARCHAR(255),
    size BIGINT,
    format VARCHAR(255),
    book_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE gift_cards (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    value INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_gift_cards (
    user_id BIGINT,
    gift_card_id BIGINT,
    date DATETIME,
    PRIMARY KEY (user_id, gift_card_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (gift_card_id) REFERENCES gift_cards(id)
);

CREATE TABLE refresh_tokens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

