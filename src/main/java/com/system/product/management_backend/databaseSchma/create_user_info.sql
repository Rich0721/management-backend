CREATE TABLE user_info(  
    seq SERIAL NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_permission VARCHAR(255) NOT NULL CHECK (user_permission IN ('admin', 'user', 'guest')),
    PRIMARY KEY(username)
);
COMMENT ON TABLE user_info IS '使用者資訊';
COMMENT ON COLUMN user_info.username IS '使用者名稱';
COMMENT ON COLUMN user_info.password IS '使用者密碼';
COMMENT ON COLUMN user_info.user_permission IS '使用者權限';