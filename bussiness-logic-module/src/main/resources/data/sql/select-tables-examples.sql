-- Select all users with their roles
SELECT u.login, u.name, r.role_name
FROM users u
         INNER JOIN roles r ON u.role_id = r.role_id;

-- Select categories with the count of items in each category
SELECT c.category_name, COUNT(ci.item_id) AS item_count
FROM categories c
         LEFT JOIN category_item ci ON c.category_id = ci.category_id
GROUP BY c.category_name
ORDER BY item_count DESC;

-- Select items along with their average rating
SELECT item_name, AVG(rate) AS average_rating
FROM items
GROUP BY item_name
ORDER BY average_rating DESC;

-- Select users who have more than one item in their cart
SELECT u.login, COUNT(ci.item_id) AS items_in_cart
FROM users u
         JOIN carts ca ON u.login = ca.owner_login
         JOIN cart_item ci ON ca.cart_id = ci.cart_id
GROUP BY u.login
HAVING COUNT(ci.item_id) > 1;

-- Select categories and their subcategories using a self join
SELECT c1.category_name AS parent_category, c2.category_name AS subcategory
FROM categories c1
         LEFT JOIN categories c2 ON c1.category_id = c2.parent_category_id;

-- Select items and their available instances sorted by price
SELECT item_name, instances_left
FROM items
WHERE instances_left > 0
ORDER BY price;

-- Select the top 5 users with the highest number of items in their cart
SELECT u.login, COUNT(ci.item_id) AS items_in_cart
FROM users u
         JOIN carts ca ON u.login = ca.owner_login
         JOIN cart_item ci ON ca.cart_id = ci.cart_id
GROUP BY u.login
ORDER BY items_in_cart DESC
LIMIT 5;

-- Using SQL functions (calculating total price for each cart)
SELECT ca.cart_id, SUM(i.price * ci.quantity) AS total_price
FROM carts ca
         JOIN cart_item ci ON ca.cart_id = ci.cart_id
         JOIN items i ON ci.item_id = i.item_id
GROUP BY ca.cart_id;
