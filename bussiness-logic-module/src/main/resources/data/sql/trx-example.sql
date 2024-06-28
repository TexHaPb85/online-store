BEGIN;

-- Delete carts referencing items in the removing category
DELETE FROM carts
WHERE cart_id IN (
    SELECT DISTINCT ci.cart_id
    FROM category_item ci
             JOIN items i ON ci.item_id = i.item_id
             JOIN categories c ON ci.category_id = c.category_id
    WHERE c.category_id = <category_id_to_remove>
);

-- Delete items in the removing category
DELETE FROM items
WHERE item_id IN (
    SELECT item_id
    FROM category_item
    WHERE category_id = <category_id_to_remove>
);

-- Delete entries from category_item table for the removing category
DELETE FROM category_item
WHERE category_id = <category_id_to_remove>;

-- Delete entries from categories table
DELETE FROM categories
WHERE category_id = <category_id_to_remove>;

COMMIT;