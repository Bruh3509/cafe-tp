ALTER TABLE user
    DROP COLUMN passport_id;

ALTER TABLE user CHANGE user_id passport_id BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE user
    ADD COLUMN password VARCHAR(100) NOT NULL,
    ADD COLUMN username VARCHAR(30) UNIQUE;
