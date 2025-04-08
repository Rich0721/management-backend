CREATE TABLE first_db.product_detail(
    seq SERIAL NOT NULL,
    code character(13) NOT NULL,
    price numeric(10,2) NOT NULL,
    description text NOT NULL,
    content text NOT NULL,
    cost numeric(10,2),
    category text,
    PRIMARY KEY(seq),
    CONSTRAINT product_detail_code_fkey FOREIGN key(code) REFERENCES product_code(code)
);

COMMENT ON TABLE first_db.product_detail IS '商品介紹資料';
COMMENT ON COLUMN first_db.product_detail.seq IS '介紹序號';
COMMENT ON COLUMN first_db.product_detail.code IS '商品編號';
COMMENT ON COLUMN first_db.product_detail.price IS '商品價格';
COMMENT ON COLUMN first_db.product_detail.description IS '商品簡介';
COMMENT ON COLUMN first_db.product_detail.content IS '商品內容';
COMMENT ON COLUMN first_db.product_detail.cost IS '商品成本';
COMMENT ON COLUMN first_db.product_detail.category IS '商品類別';