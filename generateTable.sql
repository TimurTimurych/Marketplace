CREATE TABLE sellers (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         contact_info VARCHAR(255) NOT NULL,
                         address VARCHAR(255)
);

CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          price DECIMAL(10, 2) NOT NULL,
                          stock_quantity INT NOT NULL,
                          seller_id INT NOT NULL,
                          FOREIGN KEY (seller_id) REFERENCES sellers (id)
);
