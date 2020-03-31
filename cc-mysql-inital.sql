-- For mysql

-- Creates the new database
CREATE database IF NOT EXISTS db_craftercodebase;

-- Creates the user
CREATE USER IF NOT EXISTS 'ccuser'@'%' IDENTIFIED BY 'ccpass';

-- Gives all privileges to the new user on the newly created database
GRANT ALL ON db_craftercodebase.* TO 'ccuser'@'%';