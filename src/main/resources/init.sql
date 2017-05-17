BEGIN TRANSACTION;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
CREATE TABLE suppliers
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT,
    description TEXT
);
INSERT INTO `suppliers` (id,name,description) VALUES (1,'Piekarnia Domowa','dobre bułeczki'),
 (2,'Media markt','niemiecki koncern');
CREATE TABLE "products"
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL,
    description TEXT,
    price DOUBLE DEFAULT 0.00 NOT NULL,
    category_id INT, supplier_id INTEGER NULL,
    CONSTRAINT products_categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id)
);
INSERT INTO `products` (id,name,description,price,category_id,supplier_id) VALUES (1,'chleb','dobry chlebek',23.0,1,1),
 (2,'bułka','średna buła',2.0,1,1),
 (3,'smartfon','tani smartfon',345.0,2,2);
CREATE TABLE categories
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    department VARCHAR(255) NOT NULL
);
INSERT INTO `categories` (id,name,description,department) VALUES (1,'jedzenie','kategoria jedzeniowa','departament jedzenia'),
 (2,'elektronika','kategoria elektronika','departament elektroniki');
CREATE UNIQUE INDEX suppliers_id_uindex ON suppliers (id);
COMMIT;
