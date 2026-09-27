-- V10__add_user_institution_scope.sql
-- Add institution_id column to user table for institution-level authorization

-- Add institution_id column with NOT NULL constraint
-- Note: user is a reserved keyword in MySQL, so we use backticks
ALTER TABLE `user`
ADD COLUMN institution_id BIGINT NOT NULL AFTER hospital_id,
ADD CONSTRAINT fk_user_institution
FOREIGN KEY (institution_id) REFERENCES institution(id)
ON DELETE RESTRICT
ON UPDATE CASCADE,
ADD INDEX idx_user_institution (institution_id);
