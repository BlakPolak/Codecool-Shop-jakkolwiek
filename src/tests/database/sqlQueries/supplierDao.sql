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
COMMIT;