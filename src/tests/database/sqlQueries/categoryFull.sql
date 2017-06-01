BEGIN TRANSACTION;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

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
COMMIT;