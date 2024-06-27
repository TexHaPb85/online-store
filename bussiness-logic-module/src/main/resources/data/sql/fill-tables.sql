INSERT INTO roles (role_name) VALUES
                                  ('ADMIN'),
                                  ('CLIENT');

INSERT INTO users (login, password, name, role_id) VALUES
                                                       ('admin1', 'password123', 'Admin One', 1),
                                                       ('client1', 'password123', 'Client One', 2),
                                                       ('admin2', 'password123', 'Admin Two', 1),
                                                       ('client2', 'password123', 'Client Two', 2),
                                                       ('admin3', 'password123', 'Admin Three', 1),
                                                       ('client3', 'password123', 'Client Three', 2),
                                                       ('admin4', 'password123', 'Admin Four', 1),
                                                       ('client4', 'password123', 'Client Four', 2),
                                                       ('admin5', 'password123', 'Admin Five', 1),
                                                       ('client5', 'password123', 'Client Five', 2);

INSERT INTO categories (category_name, parent_category_id) VALUES
                                                               ('Electronics', NULL),
                                                               ('Computers', 1),
                                                               ('Laptops', 2),
                                                               ('Clothing', NULL),
                                                               ('Shirts', 4),
                                                               ('Pants', 4),
                                                               ('Footwear', 4),
                                                               ('Books', NULL),
                                                               ('Fiction', 8),
                                                               ('Non-Fiction', 8);

INSERT INTO items (item_name, description, price, rate, instances_left) VALUES
                                                                            ('Laptop A', 'A high-end laptop', 999.99, 4.5, 10),
                                                                            ('Laptop B', 'A mid-range laptop', 599.99, 4.0, 15),
                                                                            ('T-shirt', 'Comfortable cotton T-shirt', 19.99, 4.2, 50),
                                                                            ('Jeans', 'Slim-fit denim jeans', 39.99, 4.0, 30),
                                                                            ('Sneakers', 'Casual sneakers', 59.99, 4.5, 20),
                                                                            ('Book A', 'Best-selling fiction novel', 14.99, 4.8, 100),
                                                                            ('Book B', 'Informative non-fiction book', 24.99, 4.3, 80),
                                                                            ('Smartphone', 'Latest smartphone model', 799.99, 4.7, 25),
                                                                            ('Headphones', 'High-quality over-ear headphones', 149.99, 4.6, 40),
                                                                            ('Watch', 'Elegant wristwatch', 299.99, 4.4, 35);

INSERT INTO carts (owner_login) VALUES
                                    ('client1'),
                                    ('client2'),
                                    ('client3'),
                                    ('client4'),
                                    ('client5');

INSERT INTO cart_item (cart_id, item_id, quantity) VALUES
                                                       (1, 1, 1),
                                                       (1, 2, 2),
                                                       (2, 3, 3),
                                                       (3, 4, 1),
                                                       (3, 5, 2),
                                                       (4, 6, 1),
                                                       (5, 7, 2),
                                                       (5, 8, 1),
                                                       (5, 9, 3),
                                                       (5, 10, 2);

INSERT INTO category_item (category_id, item_id) VALUES
                                                     (3, 1),
                                                     (3, 2),
                                                     (5, 3),
                                                     (6, 4),
                                                     (7, 5),
                                                     (8, 6),
                                                     (8, 7),
                                                     (1, 8),
                                                     (1, 9),
                                                     (1, 10);