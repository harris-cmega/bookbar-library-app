CREATE DATABASE bookbar;
DROP DATABASE bookbar;
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
                            PRIMARY KEY (id),
                            INDEX (name)
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
                         UNIQUE (name),
                         INDEX (name)
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
                       FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE SET NULL,
                       FOREIGN KEY (library_id) REFERENCES libraries(id) ON DELETE SET NULL,
                       FOREIGN KEY (publisher_id) REFERENCES publishers(id) ON DELETE SET NULL,
                       INDEX (title),
                       INDEX (author_id),
                       INDEX (library_id),
                       INDEX (publisher_id)
);

CREATE TABLE categories (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id),
                            INDEX (name)
);

CREATE TABLE book_categories (
                                 id BIGINT NOT NULL AUTO_INCREMENT,
                                 book_id BIGINT NOT NULL,
                                 category_id BIGINT NOT NULL,
                                 PRIMARY KEY (id),
                                 FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
                                 FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
                                 UNIQUE KEY (book_id, category_id),
                                 INDEX (book_id),
                                 INDEX (category_id)
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
                       PRIMARY KEY (id),
                       INDEX (username),
                       INDEX (email)
);

CREATE TABLE orders (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        total_price DECIMAL(8,2),
                        user_id BIGINT,
                        PRIMARY KEY (id),
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                        INDEX (user_id)
);

CREATE TABLE order_details (
                               order_id BIGINT,
                               book_id BIGINT,
                               quantity INT,
                               PRIMARY KEY (order_id, book_id),
                               FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                               FOREIGN KEY (book_id) REFERENCES books(id),
                               INDEX (order_id),
                               INDEX (book_id)
);

CREATE TABLE user_subscriptions (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    user_id BIGINT,
                                    subscription_id BIGINT,
                                    start_date TIMESTAMP,
                                    end_date TIMESTAMP,
                                    PRIMARY KEY (id),
                                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                    INDEX (user_id)
);

CREATE TABLE ratings (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         rating DECIMAL(2,1),
                         comment TEXT,
                         rating_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         book_id BIGINT,
                         user_id BIGINT,
                         PRIMARY KEY (id),
                         FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
                         FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                         INDEX (book_id),
                         INDEX (user_id)
);

CREATE TABLE credit_cards (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              user_id BIGINT,
                              card_number VARCHAR(20),
                              PRIMARY KEY (id),
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                              INDEX (user_id)
);

CREATE TABLE book_files (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            filename VARCHAR(255),
                            size BIGINT,
                            format VARCHAR(255),
                            epub_file LONGBLOB,
                            book_id BIGINT,
                            PRIMARY KEY (id),
                            FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
                            INDEX (book_id)
);

CREATE TABLE gift_cards (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            value INT NOT NULL,
                            PRIMARY KEY (id),
                            INDEX (name)
);

CREATE TABLE user_gift_cards (
                                 user_id BIGINT,
                                 gift_card_id BIGINT,
                                 assigned_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (user_id, gift_card_id),
                                 FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                 FOREIGN KEY (gift_card_id) REFERENCES gift_cards(id) ON DELETE CASCADE,
                                 INDEX (user_id),
                                 INDEX (gift_card_id)
);

CREATE TABLE refresh_tokens (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                token VARCHAR(255) NOT NULL,
                                expiry_date TIMESTAMP NOT NULL,
                                user_id BIGINT NOT NULL,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                INDEX (user_id)
);

CREATE TABLE cart (
								user_id BIGINT NOT NULL PRIMARY KEY,
								FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE cart_items (
							id BIGINT auto_increment primary key,
							cart_id bigint not null,
							book_id bigint not null,
							foreign key (cart_id) references cart(user_id) on delete cascade,
							foreign key (book_id) references books(id)
);

CREATE TABLE payment (
							id BIGINT AUTO_INCREMENT PRIMARY KEY,
							user_email VARCHAR(255) NOT NULL,
							amount DOUBLE NOT NULL,
							first_name VARCHAR(255),
							last_name VARCHAR(255)
);

CREATE TABLE ordered_products (
							payment_id BIGINT,
							product VARCHAR(255),
							FOREIGN KEY (payment_id) REFERENCES payment(id)
);
