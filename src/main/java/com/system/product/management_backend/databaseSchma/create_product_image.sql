CREATE TABLE first_db.product_image(
    seq SERIAL NOT NULL,
    code character(13) NOT NULL,
    image_encoded text NOT NULL,
    PRIMARY KEY(seq),
    CONSTRAINT product_price_code_fkey FOREIGN key(code) REFERENCES product_code(code)
);
COMMENT ON TABLE first_db.product_image IS '商品圖片';
COMMENT ON COLUMN first_db.product_image.seq IS '圖片序號';
COMMENT ON COLUMN first_db.product_image.code IS '商品編號';
COMMENT ON COLUMN first_db.product_image.image_encoded IS '商品圖片 base64 編碼';