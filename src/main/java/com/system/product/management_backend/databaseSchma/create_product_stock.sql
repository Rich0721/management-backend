CREATE TABLE first_db.product_stock(  
    seq SERIAL NOT NULL,
    code VARCHAR(13) NOT NULL,
    name VARCHAR(255) NOT NULL,
    numbers INTEGER NOT NULL,
    PRIMARY KEY(seq),
    CONSTRAINT product_stock_code_fkey FOREIGN key(code) REFERENCES product_code(code)
);
COMMENT ON TABLE first_db.product_stock IS '庫存盤點數量';
COMMENT ON COLUMN first_db.product_stock.seq IS '庫存編號';
COMMENT ON COLUMN first_db.product_stock.code IS '產品編號';
COMMENT ON COLUMN first_db.product_stock.name IS '產品名稱';
COMMENT ON COLUMN first_db.product_stock.numbers IS '庫存數量';
COMMENT ON CONSTRAINT product_stock_code_fkey ON first_db.product_stock IS '產品編號，參考產品編號表的產品編號欄位';