CREATE TABLE categories (
                            category_id INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(100) NOT NULL
);

CREATE TABLE books (
   book_id INT PRIMARY KEY AUTO_INCREMENT,
   title VARCHAR(255) NOT NULL,
   author VARCHAR(255) NOT NULL,
   isbn VARCHAR(20),
   price DECIMAL(10,2),
   category_id INT,
   FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE book_category (
   book_id INT,
   category_id INT,
   PRIMARY KEY (book_id, category_id),
   FOREIGN KEY (book_id) REFERENCES books(book_id),
   FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
