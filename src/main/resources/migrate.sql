BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS suppliers
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT,
    description TEXT
);

CREATE TABLE IF NOT EXISTS "products"
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL,
    description TEXT,
    price DOUBLE DEFAULT 0.00 NOT NULL,
    category_id INT, supplier_id INTEGER NULL,
    CONSTRAINT products_categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id)
);
CREATE TABLE IF NOT EXISTS categories
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    department VARCHAR(255) NOT NULL
);
COMMIT;
