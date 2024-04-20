CREATE TABLE library(
    library_ID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    image longblob NULL,
    street VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    PRIMARY KEY (library_ID)
);

CREATE TABLE publisher (
    publisher_ID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    country VARCHAR(255),
    phone_number LONG,
    PRIMARY KEY (publisher_ID)
);

CREATE TABLE author (
	author_ID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255) NULL,
    password VARCHAR(255) null,
    description VARCHAR(255),
    image longblob NULL,
    country VARCHAR(255),
    PRIMARY KEY (author_ID)
);

CREATE TABLE book (
	isbn BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    language VARCHAR(255),
    publication_date DATE,
    image LONGBLOB,
    page_number INT,
    price DECIMAL(5,2),
    description VARCHAR(255),
    author_ID INT,
    library_ID INT,
    publisher_ID INT,
    PRIMARY KEY (isbn),
    FOREIGN KEY (author_ID) REFERENCES author(author_ID),
    FOREIGN KEY (library_ID) REFERENCES library(library_ID),
    FOREIGN KEY (publisher_ID) REFERENCES publisher(publisher_ID)
);

CREATE TABLE category(
	category_ID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY(category_ID)
);

CREATE TABLE book_category (
    book_id BIGINT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (book_id, category_id),
    FOREIGN KEY (book_id) REFERENCES book(isbn),
    FOREIGN KEY (category_id) REFERENCES category(category_ID)
);

CREATE TABLE user (
	user_ID INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    balance DECIMAL(8,2) DEFAULT 0.00,
    user_type VARCHAR(255) DEFAULT 'user',
    street VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    PRIMARY KEY (user_ID)
);

CREATE TABLE user_orders (
	order_ID INT NOT NULL,
    date DATETIME,
    total_price DECIMAL(8,2),
    user_ID INT,
    PRIMARY KEY (order_ID),
    FOREIGN KEY (user_ID) REFERENCES user (user_ID)
);

CREATE TABLE book_order_details (
    order_ID INT,
    isbn BIGINT,
    quantity INT,
    PRIMARY KEY (order_ID, isbn),
    FOREIGN KEY (order_ID) REFERENCES user_orders(order_ID),
    FOREIGN KEY (isbn) REFERENCES book(isbn)
);

CREATE TABLE subscription (
    subscription_ID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    price DECIMAL(6,2),
    start_time DATETIME,
    end_time DATETIME,
    user_ID INT,
    PRIMARY KEY (subscription_ID),
    FOREIGN KEY (user_ID) REFERENCES user(user_ID)
);

CREATE TABLE rating (
    rating_ID INT NOT NULL AUTO_INCREMENT,
    review DECIMAL(2,1),
    comment VARCHAR(255),
    date DATETIME,
    isbn BIGINT,
    user_ID INT,
    PRIMARY KEY (rating_ID),
    FOREIGN KEY (isbn) REFERENCES book(isbn),
    FOREIGN KEY (user_ID) REFERENCES user(user_ID)
);

CREATE TABLE credit_cards (
    card_ID INT NOT NULL AUTO_INCREMENT,
    user_ID INT,
    card_number VARCHAR(20),
    PRIMARY KEY (card_ID),
    FOREIGN KEY (user_ID) REFERENCES user(user_ID)
);
