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
INSERT INTO `suppliers` (id,name,description) VALUES (2,'Media markt','niemiecki koncern'),
 (3,'Samsung','sklep Samsung'),
 (4,'Dell','serwis laptopów');
CREATE TABLE "products"
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL,
    description TEXT,
    price DOUBLE DEFAULT 0.00 NOT NULL,
    category_id INT, supplier_id INTEGER NULL,
    CONSTRAINT products_categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id)
);
INSERT INTO `products` (id,name,description,price,category_id,supplier_id) 
VALUES (3,'smartfon','tani smartfon',345.0,1,1),
 (10,'laptop','laptop Dell',3000.0,2,2),
 (11,'telewizor','Samsung 4K',5000.0,3,3),
 (13,'smartfon','drogi smartfon',800.0,1,2),
 (14,'telewizor ','mały telewizor',7000.0,3,3),
 (15,'laptop','stary laptop',300.0,2,4);
CREATE TABLE categories
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    department VARCHAR(255) NOT NULL
);
INSERT INTO `categories` (id,name,description,department) VALUES (1,'smartfony','kategoria smartfony','smartfony'),
 (2,'laptopy','kategoria laptopy','laptopy'),
 (3,'telewizory','kategoria telewizory','telewizory');
CREATE UNIQUE INDEX suppliers_id_uindex ON suppliers (id);
COMMIT;