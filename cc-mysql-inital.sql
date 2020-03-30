CREATE database IF NOT EXISTS db_craftercodebase;
CREATE USER IF NOT EXISTS 'ccuser'@'%' IDENTIFIED BY 'ccpass';
GRANT ALL ON db_craftercodebase.* TO 'ccuser'@'%';