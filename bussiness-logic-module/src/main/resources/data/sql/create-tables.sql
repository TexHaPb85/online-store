-- Create the 'roles' table
CREATE TABLE roles (
                      role_id SERIAL PRIMARY KEY,
                      role_name VARCHAR(10) NOT NULL UNIQUE CHECK (role_name IN ('ADMIN', 'CLIENT'))
);

-- Create the 'users' table with a foreign key to the 'roles' table
CREATE TABLE "users" (
                        login VARCHAR(50) PRIMARY KEY,
                        password VARCHAR(100) NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        role_id INTEGER NOT NULL,
                        FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

-- Create the 'categories' table
CREATE TABLE categories (
                          category_id SERIAL PRIMARY KEY,
                          category_name VARCHAR(100) NOT NULL,
                          parent_category_id INTEGER,
                          FOREIGN KEY (parent_category_id) REFERENCES categories (category_id)
);

-- Create the 'items' table
CREATE TABLE items (
                      item_id SERIAL PRIMARY KEY,
                      item_name VARCHAR(100) NOT NULL,
                      description TEXT,
                      price DOUBLE PRECISION NOT NULL,
                      rate REAL,
                      instances_left INTEGER NOT NULL
);

-- Create the 'carts' table
CREATE TABLE carts (
                      cart_id SERIAL PRIMARY KEY,
                      owner_login VARCHAR(50) NOT NULL,
                      FOREIGN KEY (owner_login) REFERENCES "users" (login)
);

-- Create the 'cart_item' table for the many-to-many relationship between carts and items
CREATE TABLE cart_item (
                           cart_id INTEGER NOT NULL,
                           item_id INTEGER NOT NULL,
                           quantity INTEGER NOT NULL,
                           PRIMARY KEY (cart_id, item_id),
                           FOREIGN KEY (cart_id) REFERENCES carts (cart_id) ON DELETE CASCADE,
                           FOREIGN KEY (item_id) REFERENCES items (item_id) ON DELETE CASCADE
);

-- Create the 'category_item' table for the many-to-many relationship between categories and items
CREATE TABLE category_item (
                               category_id INTEGER NOT NULL,
                               item_id INTEGER NOT NULL,
                               PRIMARY KEY (category_id, item_id),
                               FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE CASCADE,
                               FOREIGN KEY (item_id) REFERENCES items (item_id) ON DELETE CASCADE
);
