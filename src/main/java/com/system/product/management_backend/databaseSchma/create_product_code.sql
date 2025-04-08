CREATE TABLE first_db.product_code(
    code character(13) NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY(code)
);

COMMENT ON TABLE first_db.product_code IS '商品集';
COMMENT ON COLUMN first_db.product_code.code IS '商品編號，以unix編碼';
COMMENT ON COLUMN first_db.product_code.name IS '商品名稱';